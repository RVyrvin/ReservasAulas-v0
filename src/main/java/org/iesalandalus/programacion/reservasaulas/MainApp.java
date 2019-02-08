package org.iesalandalus.programacion.reservasaulas;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.vista.Consola;
import org.iesalandalus.programacion.reservasaulas.vista.IUTextual;

public class MainApp {

	public static void main(String[] args) {
		Consola.mostrarCabecera("Programa para la gestión de reservas");
		IUTextual vista = new IUTextual();
		vista.comenzar();
	}

}
