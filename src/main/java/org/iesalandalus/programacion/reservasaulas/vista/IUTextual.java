package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Tramo;

public class IUTextual {

	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "\\w+";
	private static final String CORREO_VALIDO = "good@mail.com";

	private ModeloReservasAulas modelo;

	public IUTextual() {
		modelo = new ModeloReservasAulas();
		Opcion.setVista(this);
	}

	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());

	}

	public void salir() {
		System.out.println("Hasta luego ...!!!");
	}

	// -----aulas-----
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.insertarAula(aula);
			System.out.println("Aula insertada correctamente");
		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.borrarAula(aula);
			System.out.println("Aula borrada correctamente");
		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula = null;
		try {
			aula = Consola.leerAula();
			aula = modelo.buscarAula(aula);
			if (aula != null) {
				System.out.println("El aula buscado es: " + aula);
			} else {
				System.out.println("No existe ningún aula con dicho nombre");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarAulas() {
		Consola.mostrarCabecera("Listar aulas");
		String[] aulas = modelo.representarAulas();
		if (aulas.length > 0) {
			for (String aula : aulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas para listar");
		}
	}

	// ----profesores--------
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			modelo.insertarProfesor(profesor);
			System.out.println("Profesor insertado correctamente");
		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar prfesor");
		try {
			String nombreProfesor = Consola.leerNombreProfesor().trim();

			Profesor profesor = null;
			profesor = modelo.buscarProfesor(new Profesor(nombreProfesor, CORREO_VALIDO));

			if (profesor != null) {
				modelo.borrarProfesor(profesor);
				System.out.println("Profesor borrado correctamente");
			} else {
				System.out.println("Profesor que queres borrar todavia no se ha registrado...");
			}

		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		try {
			String nombreProfesor = Consola.leerNombreProfesor().trim();

			Profesor profesor = null;
			profesor = modelo.buscarProfesor(new Profesor(nombreProfesor, CORREO_VALIDO));

			if (profesor != null) {
				System.out.println("El profesor buscado es: " + profesor.toString());
			} else {
				System.out.println("No existe ningún profesor con dicho nombre");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarProfesor() {
		Consola.mostrarCabecera("Listar profesores");
		String[] profesores = modelo.representarProfesores();
		if (profesores.length > 0) {
			for (String profesor : profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores para listar");
		}
	}

	// ------Reservas------

	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar reserva");
		Profesor profesor = Consola.leerProfesor();
		Reserva reserva = leerReserva(profesor);

		if (reserva != null)
			try {
				modelo.realizarReserva(reserva);
			} catch (OperationNotSupportedException e) {
				e.printStackTrace();
			}
		else
			System.out.println("La misma reserva ya existe.");

	}

	private Reserva leerReserva(Profesor profesor) {
		// Consola.mostrarCabecera("Leer reserva");

		boolean siExisteYa = false;
		Reserva reserva = null;

		// creamos la nueva reserva
		Aula aula = Consola.leerAula();
		LocalDate dia = Consola.leerDia();
		Tramo tramo = Consola.Tramo();

		reserva = new Reserva(profesor, aula, new Permanencia(dia, tramo));

		// ovtenemos reservas que ya existen
		Reserva[] reservas = modelo.getReservas();

		// buscamos reserva creada entre ya existentes
		for (int i = 0; i < reservas.length; i++) {
			if (reservas[i].equals(reserva))
				siExisteYa = true;
		}

		// si fue encontrada reserva como queremos crear, devulvemos objeto nulo
		// en caso contrario se devuelve nueva reserva la cual se puede insertar
		if (siExisteYa)
			reserva = null;

		return reserva;
	}

	public void anularReserva() {
		Consola.mostrarCabecera("Anular reserva");
		Profesor profesor = Consola.leerProfesor();
		Reserva reserva = leerReserva(profesor);

		if (reserva != null)
			try {
				modelo.anularReserva(reserva);
			} catch (OperationNotSupportedException e) {
				e.printStackTrace();
			}
		else
			System.out.println("La reserva que queser anulas, no existe");
	}

	public void listarReserva() {
		Consola.mostrarCabecera("Listar reserva");

		String[] reservasSTR = modelo.representarReservas();

		if (reservasSTR.length > 0) {
			for (String reserva : reservasSTR) {
				System.out.println(reserva.toString());
			}
		} else {
			System.out.println("No hay reservas para listar ...");
		}
	}

	public void listarReservasAulas() {

		Consola.mostrarCabecera("lista reservas de aulas");
		Aula aula = Consola.leerAula();

		Reserva[] aulas = modelo.getReservasAula(aula);

		if (aulas.length > 0) {
			for (Reserva reserva : aulas) {
				System.out.println(reserva.toString());
			}
		} else {
			System.out.println("No hay reservas de aulas para listar ...");
		}
	}

	public void listarReservasProfesores() {

		Consola.mostrarCabecera("lista reservas de profesores");
		String nombreProfesor = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(nombreProfesor, CORREO_VALIDO);

		Reserva[] profesores = modelo.getReservaProfesor(profesor);

		if (profesores.length > 0) {
			for (Reserva reserva : profesores) {
				System.out.println(reserva.toString());
			}
		} else {
			System.out.println("No hay reservas de profesores para listar ...");
		}
	}

	public void listarReservasPermanencia() {

		Consola.mostrarCabecera("lista reservas de permanencia");

		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.Tramo());
		Reserva[] permanencias = modelo.getReservaPermanencia(permanencia);

		if (permanencias.length > 0) {
			for (Reserva reserva : permanencias) {
				System.out.println(reserva.toString());
			}
		} else {
			System.out.println("No hay reservas de permanencias para listar ...");
		}
	}

	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar disponibilidad");
		boolean isDisponible = true;
		boolean aulaExiste = false;

		Aula aula = Consola.leerAula();
		LocalDate dia = Consola.leerDia();
		Tramo tramo = Consola.Tramo();

		Aula[] aulas = modelo.getAulas();

		for (Aula a : aulas) {
			if (a.equals(aula))
				aulaExiste = true;
		}

		if (aulaExiste) {

			isDisponible = modelo.consultarDisponibilidad(aula, new Permanencia(dia, tramo));

			if (isDisponible)
				System.out.println("aula " + aula.getNombre() + " esta disponible para la " + tramo.toString()
						+ " al día " + dia.toString());
			else
				System.out.println("aula " + aula.getNombre() + " no esta disponible para la " + tramo.toString()
						+ " al día " + dia.toString());
		} else {
			System.out.println("No existe la aula para la cual queres mostrar las permamemcias");
		}
	}

}
