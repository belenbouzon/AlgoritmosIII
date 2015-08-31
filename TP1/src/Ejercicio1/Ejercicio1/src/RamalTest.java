import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.junit.Test;


public class RamalTest {

	ArrayList<Ciudad> ciudadesAleatorias = GenerarCiudadesAleatorias();
	Ramal ramal = new Ramal(ciudadesAleatorias);

	@Test
	public void testAlcanzaParaUnirUnaCiudadMas() 
	{
		Ciudad c1 = new Ciudad(0);
		Ciudad c2 = new Ciudad(10);
		Ciudad c3 = new Ciudad(20);
		Ciudad c4 = new Ciudad(23);
		
		ArrayList <Ciudad> ciudades = new ArrayList <Ciudad>();
		ciudades.add(c1);
		ciudades.add(c2);
		ciudades.add(c3);
		ciudades.add(c4);
		
		Ramal r1 = new Ramal(ciudades);
		r1.indiceCiudadActual = 0;
		assertTrue (r1.AlcanzaParaUnirUnaCiudadMas(1,20));
		assertFalse(r1.AlcanzaParaUnirUnaCiudadMas(2,18));
		assertTrue(r1.AlcanzaParaUnirUnaCiudadMas(2,20));
		assertFalse (r1.AlcanzaParaUnirUnaCiudadMas(4,24));
		
		r1.indiceCiudadActual = 2;
		assertFalse(r1.AlcanzaParaUnirUnaCiudadMas(2,20));
		assertTrue (r1.AlcanzaParaUnirUnaCiudadMas(0,3));
		assertTrue (r1.AlcanzaParaUnirUnaCiudadMas(1,12));
		
		r1.indiceCiudadActual = 3;
		Random rand = new Random(100);
		Random unidos = new Random(4);
		assertFalse(r1.AlcanzaParaUnirUnaCiudadMas(unidos.nextInt(),rand.nextInt()));
		
	}

	@Test
	public void testAvanzarCiudadBase() 
	{
		assertTrue( ramal.indiceCiudadActual == 0);
		ramal.AvanzarCiudadBase();
		assertTrue(ramal.indiceCiudadActual == 1);
		for (int i = 0; i < ramal.ciudades.size() + 2; i++)
		{
			ramal.AvanzarCiudadBase();
		}
		assertTrue(ramal.indiceCiudadActual == ramal.ciudades.size() -1);
	}

	
	@Test
	public void testHayCiudadesMasLejanas() 
	{
		Ramal r1 = new Ramal(ciudadesAleatorias);
		
		int i = 1;
		for (i = 1; i< r1.ciudades.size() -1 ; i++)
		{
			r1.AvanzarCiudadBase();
			assertTrue(r1.HayCiudadesMasLejanas());
		}
		r1.AvanzarCiudadBase();
		assertFalse(r1.HayCiudadesMasLejanas());
	}

	@Test
	public void testDistanciaEntreCiudades()
	{

		Ciudad c1 = new Ciudad(0);
		Ciudad c2 = new Ciudad(1);
		Ciudad c3 = new Ciudad(20);
		Ciudad c4 = new Ciudad(31);
		
		ArrayList <Ciudad> ciudades = new ArrayList <Ciudad>();
		ciudades.add(c1);
		ciudades.add(c2);
		ciudades.add(c3);
		ciudades.add(c4);
		
		Ramal r1 = new Ramal(ciudades);
		
		assertTrue(r1.DistanciaEntreCiudades(0,1) == 1);
		assertTrue(r1.DistanciaEntreCiudades(2,2) == 0);
		assertTrue(r1.DistanciaEntreCiudades(1,3) == 30);
		assertTrue(
				r1.DistanciaEntreCiudades(0,1) + 
				r1.DistanciaEntreCiudades(1,2) +
				r1.DistanciaEntreCiudades(2,3) == 
				r1.DistanciaEntreCiudades(0,3));

	}

	@Test
	public void testEsLaUltimaCiudad() 
	{
		
		Ciudad c1 = new Ciudad(0);
		Ciudad c2 = new Ciudad(1);
		Ciudad c3 = new Ciudad(20);
		Ciudad c4 = new Ciudad(31);
		
		ArrayList <Ciudad> ciudades = new ArrayList <Ciudad>();
		ciudades.add(c1);
		ciudades.add(c2);
		ciudades.add(c3);
		ciudades.add(c4);
		
		Ramal r1 = new Ramal(ciudades);
		
		assertTrue(r1.EsLaUltimaCiudad(3));
		assertFalse(r1.EsLaUltimaCiudad(2));
		assertFalse(r1.EsLaUltimaCiudad(1));
		assertFalse(r1.EsLaUltimaCiudad(0));
		
		Ramal r2 = new Ramal(new ArrayList <Ciudad>());
		r2.ciudades.add(new Ciudad(4));
		assertTrue(r2.EsLaUltimaCiudad(0));

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<Ciudad> GenerarCiudadesAleatorias()
	{
		
	    Random rand = new Random();
	    short cant = (short) rand.nextInt(10);
	    List<Ciudad> ciudadesAleatorias = new ArrayList<Ciudad>();

			for (int i = 0; i < cant; i++)
			{
				int km = rand.nextInt(100);
				ciudadesAleatorias.add(new Ciudad(km));
			}
			ciudadesAleatorias.add(new Ciudad(0));

			List ciudadesSinRepetir =  OrdenarYEliminarRepetidos(ciudadesAleatorias);
			
			return (ArrayList<Ciudad>) ciudadesSinRepetir;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List OrdenarYEliminarRepetidos(List<Ciudad> ciudadesAleatorias)
	{
		Map<Integer, Ciudad> mapCiudades = new HashMap<Integer, Ciudad>(ciudadesAleatorias.size());
		 
		 for(Ciudad c : ciudadesAleatorias) 
		 {
			 mapCiudades.put(c.GetKilometro(), c);
		 }
		 
		 List ciudadesSinRepetir = new ArrayList<Ciudad>();
		 for(Entry<Integer, Ciudad> c : mapCiudades.entrySet()) 
		 {
			 ciudadesSinRepetir.add((Ciudad)c.getValue());
		 }
		 
		 Collections.sort(ciudadesSinRepetir, new Ciudad());
		 
		 return ciudadesSinRepetir;
	}

		public static ArrayList<Ciudad> GenerarCiudadesAleatorias(int cantEstaciones)
		{
			
		    Random rand = new Random();
		    int cant = cantEstaciones;
		    List<Ciudad> ciudadesAleatorias = new ArrayList<Ciudad>();
			ciudadesAleatorias.add(new Ciudad(0));
			int anterior = 0;

				for (int i = 0; i < cant; i++)
				{
					int km = rand.nextInt(100) + anterior;
					ciudadesAleatorias.add(new Ciudad(km));
					anterior = km;
				}
								
				return (ArrayList<Ciudad>) ciudadesAleatorias;
		}		
}
