package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;

public class Profesores {

	private static final int MAX_PROFESORES = 3;
	private int numProfesores;
	private Profesor[] coleccionProfesor;

	public Profesores() {
		this.coleccionProfesor = new Profesor[Profesores.MAX_PROFESORES];
		this.numProfesores = 0;
	}

	public Profesores(Profesores profesores) {
		setProfesores(profesores);
	}

	private void setProfesores(Profesores profesores) {
		if (profesores == null) {
			throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
		} else {
			this.coleccionProfesor = copiaProfundaProfesores(profesores.coleccionProfesor);
			this.numProfesores = profesores.numProfesores;
		}
	}

	private Profesor[] copiaProfundaProfesores(Profesor[] profesor) {
		//Profesor[] cpyProfesor = new Profesor[profesor.length];
		Profesor[] cpyProfesor = new Profesor[this.numProfesores];
		for (int i = 0; i < profesor.length && profesor[i] != null; i++) {
			cpyProfesor[i] = new Profesor(profesor[i]);
		}
		return cpyProfesor;
	}

	public Profesor[] getProfesores() {
		return copiaProfundaProfesores(this.coleccionProfesor);
	}

	public int getNumProfesores() {
		return this.numProfesores;
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
		} else {
			int index = buscarIndiceProfesor(profesor);
			if (!indiceNoSuperaTamano(index) && index<Profesores.MAX_PROFESORES) {
				this.coleccionProfesor[index] = new Profesor(profesor);
				this.numProfesores++;
			} else {
				if (indiceNoSuperaCapacidad(index)) {
					throw new OperationNotSupportedException("El profesor ya existe.");
				} else {
					throw new OperationNotSupportedException("No se aceptan más profesores.");
				}
			}
		}
	}

	private int buscarIndiceProfesor(Profesor profesor) {
		int index = 0;
		boolean profesorEncontrado = false;
		while (indiceNoSuperaTamano(index) && !profesorEncontrado) {
			if (this.coleccionProfesor[index].equals(profesor)) {
				profesorEncontrado = true;
			} else {
				index++;
			}
		}
		return index;
	}

	private boolean indiceNoSuperaTamano(int index) {
		return index < this.numProfesores ? true : false;
	}

	private boolean indiceNoSuperaCapacidad(int index) {
		return index < Profesores.MAX_PROFESORES ? true : false;
	}

	public Profesor buscar(Profesor profesor) {
		int index = 0;
		index = buscarIndiceProfesor(profesor);
		if (indiceNoSuperaTamano(index)) {
			return new Profesor(this.coleccionProfesor[index]);
		} else {
			return null;
		}
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
		} else {
			int index = buscarIndiceProfesor(profesor);
			if (indiceNoSuperaTamano(index)) {
				desplazarUnaPosicionHaciaIzquierda(index);
			} else {
				throw new OperationNotSupportedException("El profesor a borrar no existe.");
			}
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int index) {
		for (int i = index; i < this.numProfesores - 1; i++) {
			this.coleccionProfesor[i] = this.coleccionProfesor[i + 1];
		}
		this.coleccionProfesor[this.numProfesores - 1] = null;
		this.numProfesores--;
	}

	public String[] representar() {
		String[] str = new String[this.numProfesores];
		for (int i = 0; indiceNoSuperaTamano(i); i++) {
			str[i] = coleccionProfesor[i].toString();
		}
		return str;
	}

}
