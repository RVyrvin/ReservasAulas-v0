package org.iesalandalus.programacion.reservasaulas;

public enum Tramo {
	MANANA {
		@Override
		public String toString() {
			return "Mañana";
		}
		
	},
	TARDE {
		@Override
		public String toString() {
			return "Tarde";
		}
	}
}
