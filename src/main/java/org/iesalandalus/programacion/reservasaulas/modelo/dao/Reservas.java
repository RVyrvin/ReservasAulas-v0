package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

public class Reservas {
	
	private static final int MAX_RESERVAS = 10;
	private int numReservas;
	private Reserva[] coleccionReservas;
	
	
	public Reservas() {
		this.coleccionReservas = new Reserva[Reservas.MAX_RESERVAS];
		this.numReservas = 0;
	}
	
	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}
	
	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		} else {
			this.coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
			this.numReservas = reservas.numReservas;
		}
	}
	
	private Reserva[] copiaProfundaReservas(Reserva[] reserva) {
		Reserva[] cpy = new Reserva[this.numReservas];
		for (int i=0; i<reserva.length && reserva[i]!=null; i++) {
			cpy[i]=new Reserva(reserva[i]);
		}
		return cpy;
	}
	
	public Reserva[] getReservas() {
		return copiaProfundaReservas(this.coleccionReservas);
	}
	
	public int getNumReservas() {
		return this.numReservas;
	}
	
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		} else {
			int index = buscarIndiceReserva(reserva);
			if (!indiceNoSuperaTamano(index) && index<Reservas.MAX_RESERVAS) {
				this.coleccionReservas[index] = new Reserva(reserva);
				this.numReservas++;
			} else {
				if (indiceNoSuperaCapacidad(index)) {
					throw new OperationNotSupportedException("La reserva ya existe.");
				} else {
					throw new OperationNotSupportedException("No se aceptan más reservas.");
				}
			}
		}
	}
	
	private int buscarIndiceReserva(Reserva reserva) {
		int index = 0;
		boolean reservaEncontrada = false;
		while(indiceNoSuperaTamano(index) && !reservaEncontrada) {
			if (this.coleccionReservas[index].equals(reserva)) {
				reservaEncontrada = true;
			} else {
				index++;
			}			
		}
		return index;
	}
	
	private boolean indiceNoSuperaTamano(int index) {
		return index < this.numReservas ? true : false;
	}
	
	private boolean indiceNoSuperaCapacidad(int index) {
		return index < Reservas.MAX_RESERVAS ? true : false;
	}
	
	public Reserva buscar(Reserva reserva) {
		int index = 0;
		index = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(index)) {
			return new Reserva(this.coleccionReservas[index]);
		} else {
			return null;
		}		
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		} else {
			int index = 0;
			index = buscarIndiceReserva(reserva);
			if (indiceNoSuperaTamano(index)) {
				desplazarUnaPosisionHaciaIzquierda(index);
			} else {
				throw new OperationNotSupportedException("La reserva a anular no existe.");
			}
		}
	}
	
	private void desplazarUnaPosisionHaciaIzquierda(int index) {
		for (int i = index; i < this.numReservas - 1; i++) {
			this.coleccionReservas[i] = this.coleccionReservas[i + 1];
		}
		this.coleccionReservas[this.numReservas - 1] = null;
		this.numReservas--;
	}
	
	public String[] representar() {
		String[] str = new String[this.numReservas];
		for (int i = 0; indiceNoSuperaTamano(i); i++) {
			str[i] = coleccionReservas[i].toString();
		}
		return str;
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {
		Reserva[] reservaProfesor = new Reserva[this.numReservas];
		for (int i = 0; i<reservaProfesor.length; i++) {
			if (this.coleccionReservas[i].getProfesor().equals(profesor)) {
				reservaProfesor[i] = this.coleccionReservas[i];
			} else {
				reservaProfesor[i] = null;
			}
		}
		return reservaProfesor;
	}
	
	public Reserva[] getReservasAula(Aula aula) {
		Reserva[] reservaAula = new Reserva[this.numReservas];
		for (int i = 0; i<reservaAula.length; i++) {
			if (this.coleccionReservas[i].getAula().equals(aula)) {
				reservaAula[i] = this.coleccionReservas[i];
			} else {
				reservaAula[i] = null;
			}
		}
		return reservaAula;
	}
	
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservaPermanencia = new Reserva[this.numReservas];
		for (int i = 0; i<reservaPermanencia.length; i++) {
			if (this.coleccionReservas[i].getPermanencia().equals(permanencia)) {
				reservaPermanencia[i] = this.coleccionReservas[i];
			} else {
				reservaPermanencia[i] = null;
			}
		}
		return reservaPermanencia;
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		boolean isDisponible = true;
		
		if (aula == null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		if (permanencia == null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		
		for (int i = 0; i < this.numReservas; i++) {
			if (this.coleccionReservas[i].getAula().equals(aula) && this.coleccionReservas[i].getPermanencia().equals(permanencia))
				isDisponible = false;
		}
		return isDisponible;
	}
}
