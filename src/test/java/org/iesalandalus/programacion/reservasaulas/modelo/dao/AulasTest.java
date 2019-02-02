package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.Aula;
import org.iesalandalus.programacion.reservasaulas.Aulas;
import org.junit.Test;

public class AulasTest {
	
	private static final String NOMBRE_SALON1 = "Salón 1";
	private static final String NOMBRE_SALON2 = "Salón 2";
	private static final String NOMBRE_SALON3 = "Salón 3";
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	
	private final Aula salon1 = new Aula(NOMBRE_SALON1);
	private final Aula salon2 = new Aula(NOMBRE_SALON2);
	private final Aula salon3 = new Aula(NOMBRE_SALON3);

	@Test
	public void constructorDefectoTest() {
		Aulas aulas = new Aulas();
		assertEquals(0, aulas.getNumAulas());
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		Aulas aulas1 = new Aulas();
		Aulas aulas2;
		aulas2 = new Aulas(aulas1);
		assertEquals(0, aulas2.getNumAulas());
		assertNotEquals(aulas1.getAulas(), aulas2.getAulas());
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		Aulas aulas = null;
		Aulas aulas1 = null;
		try {
			aulas1 = new Aulas(aulas);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se pueden copiar aulas nulas.", e.getMessage());
			assertNull(aulas1);
		}
	}
	
	@Test
	public void insertarUnoValidoTest() {
		Aulas aulas = new Aulas();
		try {
			aulas.insertar(salon1);
			assertEquals(1, aulas.getNumAulas());
			assertEquals(salon1, aulas.buscar(salon1));
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void insertarNuloTest() {
		Aulas aulas = new Aulas();
		try {
			aulas.insertar(null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se puede insertar un aula nula.", e.getMessage());
			assertEquals(0, aulas.getNumAulas());
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void insertarRepetidoTest() {
		Aulas aulas = new Aulas();
		try {
			aulas.insertar(salon1);
			aulas.insertar(salon1);
			fail(ERROR_EXCEPCION);
		} catch (OperationNotSupportedException e) {
			assertEquals("El aula ya existe.", e.getMessage());
			assertEquals(1, aulas.getNumAulas());
		}
	}
	
	@Test
	public void insertarTresValidoTest() {
		Aulas aulas = new Aulas();
		try {
			aulas.insertar(salon1);
			assertEquals(1, aulas.getNumAulas());
			assertEquals(salon1, aulas.buscar(salon1));
			aulas.insertar(salon2);
			assertEquals(2, aulas.getNumAulas());
			assertEquals(salon2, aulas.buscar(salon2));
			aulas.insertar(salon3);
			assertEquals(3, aulas.getNumAulas());
			assertEquals(salon3, aulas.buscar(salon3));
			Aula[] arrayAulas = aulas.getAulas();
			assertEquals(salon1, arrayAulas[0]);
			assertEquals(salon2, arrayAulas[1]);
			assertEquals(salon3, arrayAulas[2]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void getAulasTest() {
		Aulas aulas = new Aulas();
		try {
			aulas.insertar(salon1);
			Aula[] aulas1 = aulas.getAulas();
			assertFalse(aulas1 == aulas.getAulas());
			assertFalse(aulas1[0] == aulas.getAulas()[0]);
			assertEquals(aulas1[0], aulas.getAulas()[0]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	private Aulas insertarTres() {
		Aulas aulas = new Aulas();
		try {
			aulas.insertar(salon1);
			aulas.insertar(salon2);
			aulas.insertar(salon3);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
		return aulas;
	}
	
	@Test
	public void borrarPrincipioValidoTest() {
		Aulas aulas = insertarTres();
		try {
			aulas.borrar(salon1);
			assertEquals(2, aulas.getNumAulas());
			assertNull(aulas.buscar(salon1));
			Aula[] arrayAulas = aulas.getAulas();
			assertEquals(salon2, arrayAulas[0]);
			assertEquals(salon3, arrayAulas[1]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarMedioValidoTest() {
		Aulas aulas = insertarTres();
		try {
			aulas.borrar(salon2);
			assertEquals(2, aulas.getNumAulas());
			assertNull(aulas.buscar(salon2));
			Aula[] arrayAulas = aulas.getAulas();
			assertEquals(salon1, arrayAulas[0]);
			assertEquals(salon3, arrayAulas[1]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarFinalValidoTest() {
		Aulas aulas = insertarTres();
		try {
			aulas.borrar(salon3);
			assertEquals(2, aulas.getNumAulas());
			assertNull(aulas.buscar(salon3));
			Aula[] arrayAulas = aulas.getAulas();
			assertEquals(salon1, arrayAulas[0]);
			assertEquals(salon2, arrayAulas[1]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarNuloTest() {
		Aulas aulas = insertarTres();
		try {
			aulas.borrar(null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se puede borrar un aula nula.", e.getMessage());
			assertEquals(3, aulas.getNumAulas());
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarNoValidoTest() {
		Aulas aulas = insertarTres();
		try {
			Aula aula = new Aula("Salón 4");
			aulas.borrar(aula);
			fail(ERROR_EXCEPCION);
		} catch (OperationNotSupportedException e) {
			assertEquals("El aula a borrar no existe.", e.getMessage());
			assertEquals(3, aulas.getNumAulas());
		}
	}
	
	@Test
	public void borrarInsertarTest() {
		Aulas aulas = insertarTres();
		try {
			aulas.borrar(salon1);
			assertEquals(2, aulas.getNumAulas());
			assertNull(aulas.buscar(salon1));
			aulas.insertar(salon1);
			assertEquals(3, aulas.getNumAulas());
			assertEquals(salon1, aulas.buscar(salon1));
			Aula[] arrayAulas = aulas.getAulas();
			assertEquals(salon2, arrayAulas[0]);
			assertEquals(salon3, arrayAulas[1]);
			assertEquals(salon1, arrayAulas[2]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void buscarNuloTest() {
		Aulas aulas = insertarTres();
		assertNull(aulas.buscar(null));
	}
	
	@Test
	public void representarTest() {
		Aulas aulas = insertarTres();
		String[] representacion = aulas.representar();
		assertEquals(salon1.toString(), representacion[0]);
		assertEquals(salon2.toString(), representacion[1]);
		assertEquals(salon3.toString(), representacion[2]);
	}

}
