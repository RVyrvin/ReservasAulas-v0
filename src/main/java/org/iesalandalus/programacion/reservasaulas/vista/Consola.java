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
		String cadena = "%0" + mensaje.length() + "d%n";
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
		String nombreAula;
		do {
			nombreAula = leerNombreAula();
		} while (nombreAula.trim().equals(""));
		aula = new Aula(nombreAula);
		return aula;
	}

	public static String leerNombreAula() {
		System.out.print("Introduce el nombre da la aula: ");
		return Entrada.cadena();
	}

	public static Profesor leerProfesor() {
		Profesor profesor;

		String nombreProfesor;
		do {
			nombreProfesor = leerNombreProfesor();
		} while (nombreProfesor.trim().equals(""));

		String correoProfesor;
		do {
			System.out.print("Introduce el correo: ");
			correoProfesor = Entrada.cadena();
		} while (correoProfesor.trim().equals(""));

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
		
		Tramo tramo = null;
		
		do {			
			System.out.print("Introduce el tramo (M/T): ");
			char ch = Entrada.caracter();
			if (ch == 'M' || ch == 'm') {
				tramo = Tramo.MANANA;
			} else if (ch == 'T' || ch == 't') {
				tramo = Tramo.TARDE;
			}
		} while (tramo == null);
		
		return tramo;
	}

	public static LocalDate leerDia() {
		
		LocalDate data;
		
		int anno;
		do {
			System.out.print("Introduce el anno (yyyy): ");
			anno = Entrada.entero();
		} while (String.valueOf(anno).length() != 4);

		int mes;
		do {
			System.out.print("Introduce el mes (mm): ");
			mes = Entrada.entero();
		} while (mes < 1 || mes > 12);

		int dia;
		do {
			System.out.print("Introduce el día (dd): ");
			dia = Entrada.entero();
		} while (dia < 0 || dia > 31);

		data = LocalDate.of(anno, mes, dia);
		
		return data;
	}

}
