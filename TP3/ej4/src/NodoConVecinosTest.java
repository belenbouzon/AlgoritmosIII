import static org.junit.Assert.*;

import org.junit.Test;

public class NodoConVecinosTest {

	

	@Test
	public void testAgregarVecinos() {
		NodoConVecinos n1 = new NodoConVecinos(1);
		NodoConVecinos n2 = new NodoConVecinos(2);
		
		n1.agregarVecinos(n2);
		
		assertTrue(n1.vecinos().contains(n2));
		assertTrue(n2.vecinos().contains(n1));
		
		n1.agregarVecinos(n2);

		assertEquals(1, n1.vecinos().size());
		assertEquals(1, n2.vecinos().size());
		
		NodoConVecinos n3 = new NodoConVecinos(3);
		
		n1.agregarVecinos(n3);
		
		assertTrue(n1.vecinos().contains(n3));
		assertTrue(n1.vecinos().contains(n2));
	}

	


	@Test
	public void testNodoConVecinosNodo() {
		int[] coloresNodo1 = {1,3,5,7,9};
		Nodo nodo1 = new Nodo(1, 10, coloresNodo1);
		nodo1.setColor(1);
		
		NodoConVecinos nodoConVecinos1 = new NodoConVecinos(nodo1);
		
		assertEquals(1, nodoConVecinos1.getColor());
		assertEquals(nodo1.getId(), nodoConVecinos1.getId());
		assertEquals(coloresNodo1.length, nodoConVecinos1.get_coloresPosibles().size());
		for (int c: coloresNodo1){
			assertTrue(nodoConVecinos1.get_coloresPosibles().contains(c));
		}
		
	}

}
