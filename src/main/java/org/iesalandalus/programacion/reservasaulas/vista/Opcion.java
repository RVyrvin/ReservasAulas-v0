package org.iesalandalus.programacion.reservasaulas.vista;

public enum Opcion {
	SALIR("Salir") {
		public void ejecutar() {

		}
	},
	INSERTAR_AULA("Insertar aula") {
		public void ejecutar() {

		}
	},
	BORRAR_AULA("Borrar aula") {
		public void ejecutar() {

		}
	},
	BUSCAR_AULA("Buscar aula") {
		public void ejecutar() {

		}
	},
	LISTAR_AULAS("Listar aula") {
		public void ejecutar() {

		}
	},
	INSERTAR_PROFESOR("Insertar aula") {
		public void ejecutar() {

		}
	},
	BORRAR_PROFESOR("Borrar Profesor") {
		public void ejecutar() {

		}
	},
	BUSCAR_PROFESOR("Buscar profesor") {
		public void ejecutar() {

		}
	},
	LISTAR_PROFESORES("Listar profesores") {
		public void ejecutar() {

		}
	},
	INSERTAR_RESERVA("Insertar reserva") {
		public void ejecutar() {

		}
	},
	BORRAR_RESERVA("Borrar reserva") {
		public void ejecutar() {

		}
	},
	LISTAR_RESERVA("Listar reserva") {
		public void ejecutar() {

		}
	},
	LISTAR_RESERVAS_AULA("Listar reservas aula") {
		public void ejecutar() {

		}
	},
	LISTAR_RESERVAS_PROFESOR("Listar reservas profesor") {
		public void ejecutar() {

		}
	},
	LISTAR_RESERVAS_PERMANENCIA("Listar reservas permanencia") {
		public void ejecutar() {

		}
	},
	CONSULTAR_DISPONIBILIDAD("Consular disponibilidad") {
		public void ejecutar() {

		}
	};

	private String mensajeAMostrar;
	private static IUTextual vista;

	private Opcion(String mensajeAMostrar) {
		this.mensajeAMostrar = mensajeAMostrar;
	}

	public String getMensaje() {
		return this.mensajeAMostrar;
	}

	public abstract void ejecutar();

	protected static void setVista(IUTextual vista) {
		Opcion.vista = vista;
	}

	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), mensajeAMostrar);
	}

	public static Opcion getOpcionSegunOrdinal(int ordinal) {
		if (esOrdinalValido(ordinal))
			return values()[ordinal];
		else
			throw new IllegalArgumentException("Ordinal de la opción no válido");
	}

	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1);
	}

}
