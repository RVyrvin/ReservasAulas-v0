package org.iesalandalus.programacion.reservasaulas;

import javax.naming.OperationNotSupportedException;

public class Aulas {

	private static final int MAX_AULAS = 3;
	private int numAulas;
	private Aula[] coleccionAulas;

	public Aulas() {
		coleccionAulas = new Aula[MAX_AULAS];
		numAulas = 0;
	}

	public Aulas(Aulas aulas) {
		setAulas(aulas);
	}

	private void setAulas(Aulas aulas) {
		if (aulas == null)
			throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
		else {
			this.numAulas = aulas.numAulas;
			this.coleccionAulas = copiaProfundaAulas(aulas.coleccionAulas);
		}
	}

	private Aula[] copiaProfundaAulas(Aula[] aula) {
		Aula[] cpyAlua = new Aula[aula.length];
		for (int i = 0; i < aula.length && aula[i] != null; i++) {
			cpyAlua[i] = new Aula(aula[i]);
		}
		return cpyAlua;
	}

	public Aula[] getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}

	public int getNumAulas() {
		return numAulas;
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null)
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
		else {
			int index = buscarIndiceAula(aula);
			boolean indexTamano = indiceNoSuperaTamano(index);
			if (!indexTamano && index < Aulas.MAX_AULAS) {
				this.coleccionAulas[index] = new Aula(aula);
				this.numAulas++;
			} else {
				if (indiceNoSuperaCapacidad(index)) {
					throw new OperationNotSupportedException("El aula ya existe.");
				} else {
					throw new OperationNotSupportedException("No se aceptan más aulas.");
				}
			}
		}

	}

	private int buscarIndiceAula(Aula aula) {
		int index = 0;
		boolean aulaEncontrada = false;
		while (indiceNoSuperaTamano(index) && !aulaEncontrada) {
			if (this.coleccionAulas[index].equals(aula)) {
				aulaEncontrada = true;
			} else {
				index++;
			}
		}
		return index;
	}

	private boolean indiceNoSuperaTamano(int index) {
		return index < this.numAulas ? true : false;
	}

	private boolean indiceNoSuperaCapacidad(int index) {
		return index < Aulas.MAX_AULAS ? true : false;
	}

	public Aula buscar(Aula aula) {
		int index = 0;
		index = buscarIndiceAula(aula);
		if (indiceNoSuperaTamano(index))
			return new Aula(this.coleccionAulas[index]);
		else
			return null;
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
		} else {
			int index = buscarIndiceAula(aula);
			if (indiceNoSuperaTamano(index)) {
				desplazarUnaPosicionHaciaIzquierda(index);
			} else {
				throw new OperationNotSupportedException("El aula a borrar no existe.");
			}
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int index) {
		for (int i = index; i < this.numAulas - 1; i++) {
			this.coleccionAulas[i] = this.coleccionAulas[i + 1];
		}
		this.coleccionAulas[this.numAulas - 1] = null;
		this.numAulas--;
	}

	public String[] representar() {
		String[] str = new String[this.numAulas];
		for (int index = 0; indiceNoSuperaTamano(index); index++) {
			str[index] = this.coleccionAulas[index].toString();
		}
		return str;
	}
}
