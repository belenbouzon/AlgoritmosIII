import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class GeneradorTiempos {
	
	/*Modificar de acuerdo a los valores con los que se quiera probar*/

	private int cantidadDePisos       = 1000;
	private int cantidadMaxDePisos    = 100000;
	private int cantPortales          = 1000;
	private int longitudDePasillos    = 1000;
	private int longitudMaxDePasillos = 77000;
	private int escala                = 1000;
	private int cantMinimaPortales    = 2000;
	private int cantMaximaPortales    = 992000;
	

	@Test
	public void GeneradorResultadosVariandoCantPortales() throws Exception
	{		
		GeneradorCasosDeTestsbyPortales gen = new GeneradorCasosDeTestsbyPortales(cantidadDePisos, longitudDePasillos, escala, cantMinimaPortales, cantMaximaPortales);
		CorrerTodosLosCasos(50, cantMinimaPortales, cantMaximaPortales, escala, "Portales.out");
		ProcesarResultados(cantMinimaPortales, cantMaximaPortales, escala, "Portales");
	}

	@Test
	public void GeneradorResultadosVariandoCantPisos() throws Exception
	{		
		GeneradorCasosDeTestsbyAltura gen = new GeneradorCasosDeTestsbyAltura(cantidadDePisos, cantidadMaxDePisos, escala, longitudDePasillos, cantPortales);
		CorrerTodosLosCasos(50, cantidadDePisos, cantidadMaxDePisos, escala, "Pisos.out");
		ProcesarResultados(cantidadDePisos, cantidadMaxDePisos, escala, "Pisos");
	}

	
	@Test
	public void GeneradorResultadosVariandoLongitudPasillos() throws Exception
	{		
		GeneradorCasosDeTestsbyLongitud gen = new GeneradorCasosDeTestsbyLongitud(longitudDePasillos, longitudMaxDePasillos, escala, cantPortales, cantidadDePisos);
		CorrerTodosLosCasos(50, longitudDePasillos, longitudMaxDePasillos, escala, "Pasillos.out");
		ProcesarResultados(longitudDePasillos, longitudMaxDePasillos, escala, "Pasillos");
	}

	private void ProcesarResultados(int cantMinima, int cantMaxima, int escala, String casoDeTest) throws Exception {
		
    	Escritor escritor = new Escritor("MEDICIONES" + casoDeTest + ".out");
	    escritor.EscribirLinea("Cantidad Mínima de " + casoDeTest + ": " + Integer.toString(cantMinima));
	    escritor.NuevaLinea();
	    escritor.EscribirLinea("Escala entre cada uno: " + Integer.toString(escala));
	    escritor.NuevaLinea();
	    escritor.EscribirLinea("Cantidad Máxima de " + casoDeTest + ": " + Integer.toString(cantMaxima));
	    escritor.NuevaLinea();
	    escritor.Fin();
	    
		/*Para cada P recupero todos los resultados y obtengo los promedios sin outliers*/
		
		for (int i = cantMinima; i < cantMaxima; i += escala)
		{
			/*Leer todas las lineas del archivo propio del P que se mira*/
			String nombreArchivo = "RES"+ Integer.toString(i) + casoDeTest + ".out";
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
			File archivo = new File( path + "MEDICIONES" + casoDeTest + ".out");
		    FileWriter fw = new FileWriter(archivo, true);
		    fw.append(Long.toString(promedio));
		    fw.append("\n");
		    fw.close();

		}		
	}

	private void CorrerTodosLosCasos(int cantVeces, int cantMinima, int cantMaxima, int escala, String sufijoArchivo) throws Exception {
		for (int i = cantMinima; i < cantMaxima; i+=escala) //el intervalo que corresponda a los archivos creados
		{
			String[] parametros = new String[]{String.valueOf(i)+sufijoArchivo};
			for (int j = 0; j < cantVeces; j ++)
			{
				Main.main(parametros);
			}
		}
	}

}