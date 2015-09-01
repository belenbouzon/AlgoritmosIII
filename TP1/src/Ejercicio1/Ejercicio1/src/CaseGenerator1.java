import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

public class CaseGenerator1 {

	@Test
	public void test() throws Exception 
	{
		/*Genero los archivos con casos de ramales aleatorios para distinta cantidad de elementos*/
		GenerarCasosConCantidad(1);
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		sizes.add(1);

		int n = 10;
		int maximoTam = 2000;
		
		while (n < maximoTam)
		{
			GenerarCasosConCantidad(n);
			sizes.add(n);
			n += 10;
		}	
		
		/*Para cada N recupero todos los resultados y obtengo los promedios sin outliers*/
		
		for (int i = 0; i < sizes.size(); i++)
		{
			/*Leer todas las lineas del archivo propio del n que se mira*/
			String nombreArchivo = Integer.toString(sizes.get(i)) + " estaciones";
			ArrayList<Integer> mediciones = new ArrayList<Integer>();
			Lector lector = new Lector(nombreArchivo + ".out");
			while(!lector.hasEnded)
			{
				String medicion = lector.GetLine();
				if (medicion == null || medicion == " ")
					break;
				mediciones.add(Integer.parseInt(medicion.trim()));
			}
			
			/*Eliminamos outliers*/
			for (int k = 0; k < 20; k++)
			{
			int minIndex = mediciones.indexOf(Collections.min(mediciones));
			mediciones.remove(minIndex);
			int maxIndex = mediciones.indexOf(Collections.max(mediciones));
			mediciones.remove(maxIndex);
			}
			
			long suma = 0;
			
			for (int j = 0; j < mediciones.size(); j++)
				suma += mediciones.get(j);
			
			float promedio = suma/mediciones.size(); // /sizes.get(i); Acordarse hacerlo para el 1 tmb


			String path = getClass().getResource( "" ).getPath();
			File archivo = new File( path + "resultados.out");
		    
		    if (archivo.exists())
		    {
			    FileWriter fw = new FileWriter(archivo, true);
			    /*fw.append(Integer.toString(sizes.get(i)));
			    fw.append(" ");*/
			    fw.append(Float.toString(promedio));
			    fw.append("\n");
			    fw.close();
		    }
		    else
		    {
		    	Escritor escritor = new Escritor("resultados.out");
			    /*escritor.EscribirLinea(Integer.toString(sizes.get(i)));
			    escritor.EscribirLinea(" ");*/
			    escritor.EscribirLinea(Float.toString(promedio));
			    escritor.NuevaLinea();
			    escritor.Fin();
			}
		}
	}
	
	private void GenerarCasosConCantidad(int cantEstaciones) throws Exception
	{
			String nombreArchivo = Integer.toString(cantEstaciones) + " estaciones";
			ArrayList<Ciudad> ciudades = RamalTest.GenerarCiudadesAleatorias(cantEstaciones);
			Random rand = new Random();
			int longitud  = rand.nextInt(ciudades.get(ciudades.size()-1).GetKilometro()+1);
			String path = getClass().getResource( "" ).getPath();
		    File archivo = new File( path + nombreArchivo);
		    FileWriter fw = new FileWriter(archivo, true);
		    
		    if (archivo.exists())
		    {
		    	fw.append(Integer.toString(longitud));
		    	fw.append("\n");
		    	for (int j = 0; j < ciudades.size() -1; j++)
				{
					fw.append(Integer.toString(ciudades.get(j).GetKilometro()) + " ");
				}
		    	fw.append("\n");
		    	fw.close();
		    }
		   /* else
		    {
				Escritor escritor = new Escritor("prueba"); 

				escritor.EscribirInt(longitud);
				escritor.NuevaLinea();
				for (int j = 0; j < ciudades.size() -1; j++)
				{
					escritor.EscribirInt(ciudades.get(j).GetKilometro());
				}
				escritor.NuevaLinea();
				escritor.Fin();

		    }
*/
			for (int i = 0; i < 2; i++)
			{
				String parametros [] = new String [3];
				parametros[0] = nombreArchivo;
				parametros[1] = "--tiempos";
				parametros[2] = "350";
				Main.main(parametros);
			}
			
	}

}