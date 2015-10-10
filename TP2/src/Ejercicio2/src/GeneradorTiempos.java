import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class GeneradorTiempos {
	
	/*Modificar de acuerdo a los valores con los que se quiera probar*/

	private int cantidadDePisos = 100;
	private int longitudDePasillos = 20;
	private int escala = 15000;

	@Test
	public void GeneradorResultadosVariandoCantPortales() throws Exception
	{

		int cantMinimaPortales = cantidadDePisos * longitudDePasillos;
		int cantMaximaPortales = cantidadDePisos * longitudDePasillos / 2 * ((cantidadDePisos * longitudDePasillos) -1) - 450000;
			
		//GenerarCasosDeTests gen = new GenerarCasosDeTests(cantidadDePisos, longitudDePasillos, escala); //creo los archivos de entrada

		//CorrerTodosLosCasos(50/*CantVeces*/, cantMinimaPortales, cantMaximaPortales, escala);
		
		ProcesarResultados(2000, 992000, escala);
	}

	private void ProcesarResultados(int cantMinimaPortales, int cantMaximaPortales, int escala) throws Exception {
		
    	Escritor escritor = new Escritor("MEDICIONES.out");
	    escritor.EscribirLinea("Cantidad Mínima de Portales: " + Integer.toString(cantMinimaPortales));
	    escritor.NuevaLinea();
	    escritor.EscribirLinea("Escala entre cada uno: " + Integer.toString(escala));
	    escritor.NuevaLinea();
	    escritor.EscribirLinea("Cantidad Máxima de Portales: " + Integer.toString(cantMaximaPortales));
	    escritor.NuevaLinea();
	    escritor.Fin();
	    
		/*Para cada P recupero todos los resultados y obtengo los promedios sin outliers*/
		
		for (int i = cantMinimaPortales; i < cantMaximaPortales; i += escala)
		{
			/*Leer todas las lineas del archivo propio del P que se mira*/
			String nombreArchivo = "RES"+ Integer.toString(i) + "Portales.out";
			ArrayList<Long> mediciones = new ArrayList<Long>();
			Lector lector = new Lector(nombreArchivo, true);
			while(!lector.hasEnded)
			{
				String medicion = lector.GetLine();
				if (medicion == null || medicion == " ")
					break;
				mediciones.add(Long.parseLong(medicion.trim()));
			}
			
			/*Eliminamos outliers*/
			for (int k = 0; k < 5; k++)
			{
			int minIndex = mediciones.indexOf(Collections.min(mediciones));
			mediciones.remove(minIndex);
			int maxIndex = mediciones.indexOf(Collections.max(mediciones));
			mediciones.remove(maxIndex);
			}
			
			long suma = 0;
			
			for (int j = 0; j < mediciones.size(); j++)
				suma += mediciones.get(j);
			
			long promedio = suma/mediciones.size();
			
			/*Escribimos los resultados finales*/
			
			String path = getClass().getResource( "" ).getPath();
			File archivo = new File( path + "MEDICIONES.out");
		    FileWriter fw = new FileWriter(archivo, true);
		    fw.append(Long.toString(promedio));
		    fw.append("\n");
		    fw.close();

		}		
	}

	private void CorrerTodosLosCasos(int cantVeces, int cantMinimaPortales, int cantMaximaPortales, int escala) throws Exception {
		for (int i = cantMinimaPortales; i < cantMaximaPortales; i+=escala) //el intervalo que corresponda a los archivos creados
		{
			String[] parametros = new String[]{String.valueOf(i)+"Portales.out"};
			for (int j = 0; j < cantVeces; j ++)
			{
				Main.main(parametros);
			}
		}
	}

}

	
	
	
	/*
	 * 		GeneradorDeTestsPortales gen = new GeneradorDeTestsPortales();
	 * 		crea los archivos que coinciden en n y l y varian en p.-
	 *		Se llaman p.ToString() + "Portales"
	 */
