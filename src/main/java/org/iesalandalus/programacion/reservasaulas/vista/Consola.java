package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {

	}

	public static void mostrarMenu() {
		mostrarCabecera("Gestión de clientes");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "%d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}

	public static Aula leerAula() {
		Aula aula;
		System.out.print("Introduce el nombre da la aula: ");
		String nombreAula = leerNombreAula();
		aula = new Aula(nombreAula);
		return aula;
	}

	public static String leerNombreAula() {
		return Entrada.cadena();
	}

	public static Profesor leerProfesor() {
		Profesor profesor;
		String nombreProfesor = leerNombreProfesor();
		System.out.print("Introduce el correo: ");
		String correoProfesor = Entrada.cadena();
		System.out.print("Introduce el telefono: ");
		String telefonoProfesor = Entrada.cadena();
		if (telefonoProfesor.trim().equals(""))
			profesor = new Profesor(nombreProfesor, correoProfesor);
		else
			profesor = new Profesor(nombreProfesor, correoProfesor, telefonoProfesor);

		return profesor;
	}

	public static String leerNombreProfesor() {
		System.out.print("Introduce el nombre del profesor: ");
		return Entrada.cadena();
	}

	public static Tramo Tramo() {
		System.out.print("Introduce el tramo (M/T): ");
		if (Entrada.caracter() == 'M')
			return Tramo.MANANA;
		else
			return Tramo.TARDE;
	}

	public static LocalDate leerDia() {
		LocalDate data;
		System.out.print("Introduce el anno (yyyy): ");
		int anno = Entrada.entero();
		System.out.print("Introduce el mes (mm): ");
		int mes = Entrada.entero();
		System.out.print("Introduce el día (dd): ");
		int dia = Entrada.entero();
		data = LocalDate.of(anno, mes, dia);
		return data;
	}

}
