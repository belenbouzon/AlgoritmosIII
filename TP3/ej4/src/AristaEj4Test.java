import static org.junit.Assert.*;

import org.junit.Test;

public class AristaEj4Test {

	@Test
	public void testEqualsObject() {
		NodoConVecinos n1 = new NodoConVecinos(1);
		NodoConVecinos n2 = new NodoConVecinos(2);
		
		AristaEj4 prueba = new AristaEj4(n1,n2);
		AristaEj4 pruebaInversa = new AristaEj4(n2,n1);
		
		assertTrue(prueba.equals(pruebaInversa));
	}

}
