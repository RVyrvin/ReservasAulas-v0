package org.iesalandalus.programacion.reservasaulas;

public class Reserva {
	
	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;
	
	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		this.setProfesor(profesor);
		this.setAula(aula);
		this.setPermanencia(permanencia);
	}
	
	public Reserva(Reserva reserva) {
		if (reserva == null)
			throw new IllegalArgumentException("No se puede copiar una reserva nula.");
		else {
			this.setProfesor(reserva.profesor);
			this.setAula(reserva.aula);
			this.setPermanencia(reserva.permanencia);
		}
			
	}
	

	public Profesor getProfesor() {
		return profesor;
	}

	private void setProfesor(Profesor profesor) {
		if (profesor == null)
			throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
		else
			this.profesor = profesor;
	}

	public Aula getAula() {
		return aula;
	}

	private void setAula(Aula aula) {
		if (aula == null)
			throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
		else
			this.aula = aula;
	}

	public Permanencia getPermanencia() {
		return permanencia;
	}

	private void setPermanencia(Permanencia permanencia) {
		if (permanencia == null)
			throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
		else
			this.permanencia = permanencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aula == null) ? 0 : aula.hashCode());
		result = prime * result + ((permanencia == null) ? 0 : permanencia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (aula == null) {
			if (other.aula != null)
				return false;
		} else if (!aula.equals(other.aula))
			return false;
		if (permanencia == null) {
			if (other.permanencia != null)
				return false;
		} else if (!permanencia.equals(other.permanencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[profesor=" + profesor + ", aula=" + aula + ", permanencia=" + permanencia + "]";
	}
}
