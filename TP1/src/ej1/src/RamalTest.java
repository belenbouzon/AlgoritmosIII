import static org.junit.Assert.*;

import org.junit.Test;


public class RamalTest 
{

	@Test
	public void test() 
	{
		
		Ramal ramal = new RamalDefault();

			assertEquals(0, ramal.DistanciaEntreCiudades(0,0));

			assertEquals(3, ramal.DistanciaEntreCiudades(0,1));

			assertEquals(2, ramal.DistanciaEntreCiudades(1,2));
			
			assertEquals(5, ramal.DistanciaEntreCiudades(0,2));

			assertEquals(9, ramal.DistanciaEntreCiudades(0,4));

	}

}
