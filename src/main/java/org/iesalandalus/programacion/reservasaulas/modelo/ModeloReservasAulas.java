package org.iesalandalus.programacion.reservasaulas.modelo;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dao.Aulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Profesores;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Reservas;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Tramo;

public class ModeloReservasAulas {

	private Aulas aulas;
	private Profesores profesores;
	private Reservas reservas;

	public ModeloReservasAulas() {
		this.aulas = new Aulas();
		this.profesores = new Profesores();
		this.reservas = new Reservas();

		// datos para probar
		/*
		 * Aula aula1 = new Aula("aula1"); Aula aula2 = new Aula("aula2");
		 * 
		 * this.aulas.insertar(new Aula(aula1)); this.aulas.insertar(new Aula(aula2));
		 * 
		 * Profesor roman = new Profesor("roman","roman@mail.com","666555444"); Profesor
		 * ramon = new Profesor("ramon","ramon@mail.com","950777444");
		 * 
		 * this.profesores.insertar(roman); this.profesores.insertar(ramon);
		 * 
		 * LocalDate dia1 = LocalDate.of(2000, 12, 12); LocalDate dia2 =
		 * LocalDate.of(2100, 10, 10); LocalDate dia3 = LocalDate.of(2200, 05, 05);
		 * 
		 * Tramo tramoM = Tramo.MANANA; Tramo tramoT = Tramo.TARDE;
		 * 
		 * Permanencia per1 = new Permanencia(dia1, tramoM); Permanencia per2 = new
		 * Permanencia(dia1, tramoT); Permanencia per3 = new Permanencia(dia2, tramoT);
		 * Permanencia per4 = new Permanencia(dia3, tramoM); Permanencia per5 = new
		 * Permanencia(dia3, tramoT);
		 * 
		 * this.reservas.insertar(new Reserva(roman, aula1, per1));
		 * this.reservas.insertar(new Reserva(roman, aula1, per2));
		 * this.reservas.insertar(new Reserva(roman, aula2, per3));
		 * this.reservas.insertar(new Reserva(roman, aula2, per4));
		 * this.reservas.insertar(new Reserva(ramon, aula1, per5));
		 * this.reservas.insertar(new Reserva(ramon, aula1, per3));
		 * this.reservas.insertar(new Reserva(ramon, aula2, per2));
		 */
	}

	// aulas
	public Aula[] getAulas() {
		return aulas.getAulas();
	}

	public int getNumAulas() {
		return aulas.getNumAulas();
	}

	public String[] representarAulas() {
		return aulas.representar();
	}

	public Aula buscarAula(Aula aula) {
		return aulas.buscar(aula);
	}

	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		aulas.insertar(aula);
	}

	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		aulas.borrar(aula);
	}

	// profesores
	public Profesor[] getProfesores() {
		return profesores.getProfesores();
	}

	public int getNumProfesores() {
		return profesores.getNumProfesores();
	}

	public String[] representarProfesores() {
		return profesores.representar();
	}

	public Profesor buscarProfesor(Profesor profesor) {
		return profesores.buscar(profesor);
	}

	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}

	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}

	// reservas
	public Reserva[] getReservas() {
		return reservas.getReservas();
	}

	public int getNumReservas() {
		return reservas.getNumReservas();
	}

	public String[] representarReservas() {
		return reservas.representar();
	}

	public Reserva buscarReserva(Reserva reserva) {
		return reservas.buscar(reserva);
	}

	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.insertar(reserva);
	}

	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.borrar(reserva);
	}

	public Reserva[] getReservasAula(Aula aula) {
		return reservas.getReservasAula(aula);
	}

	public Reserva[] getReservaProfesor(Profesor profesor) {
		return reservas.getReservasProfesor(profesor);
	}

	public Reserva[] getReservaPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}
}
