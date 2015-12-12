package algo3.tp3.ej4;

import static org.junit.Assert.*;
import algo3.tp3.ej3.*;

import org.junit.Test;

public class GrafoEj4Test {

	@Test
	public void testVecindad2() throws Exception {
		
		GrafoEj4 res = new GrafoEj4(Grafo.grafo_prueba_vecindad2());
		res.ResolverConVecindad2();
		assertEquals(0,res.getCantConflictos());
		
	}

}
