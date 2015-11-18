import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main 
{
	public static void main(String[] args) throws Exception 
	{	
		Escritor archivoDeTiempos = new Escritor("resultadosDeTiempos.out");
		archivoDeTiempos.Fin();

		Escritor archivoDeConflictos = new Escritor("resultadosDeConflictos.out");
		archivoDeConflictos.Fin();
		
		int cantDeNodos = 1001;
		int cantDeAristas = 800000;  //un grafo bastante saturado
		int escala = 10;
		int cantMaximaDeAristas = 1001000;
		int cantColores = 1;
		int cantMaximaDeColores = 1001;
		
		
		while (cantColores <= cantMaximaDeColores)
		{
			
			GeneradorCasosDeTests generador = new GeneradorCasosDeTests();
			String nombreArchivo = generador.GenerarGrafoByCantColores(cantDeNodos, cantDeAristas, cantColores);
			Lector lector = new Lector(nombreArchivo);	

			Grafo grafo = new Grafo();

			long elapsed = 0;
		
			long time0 = System.nanoTime();
			Grafo grafoResultante = lector.MakeGraph();
			grafoResultante.MakeRainbow();
			long time1 = System.nanoTime();
			
			elapsed += (time1-time0);
			grafo = grafoResultante;


			EscribirArchivoTiempos(cantColores, elapsed);
			EscribirArchivoConflictos(cantColores, grafo);
			
			cantColores += escala;
			System.out.println("Procesando COLORES: " + String.valueOf(cantColores) + "\n");
		}
		
		System.out.println("Proceso finalizado");
	}

	private static void EscribirArchivoConflictos(int cantDeNodos, Grafo grafo) throws IOException {
		File archivoConflictos = new File(  "C:\\Users\\Bel\\Documents\\GitHub\\AlgoritmosIII\\TP3\\ej3\\Ejercicio3\\bin\\"  + "resultadosDeConflictos.out");
		int cantidadDeConflictos = CalcularConflictos(grafo);
		FileWriter fw1 = new FileWriter(archivoConflictos, true);
		fw1.append(String.valueOf(cantDeNodos) + " " + String.valueOf(cantidadDeConflictos)+ "\n");
		fw1.close();
	}

	private static void EscribirArchivoTiempos(int cantDeNodos, long elapsed) throws IOException {
		File archivoTiempos = new File( "C:\\Users\\Bel\\Documents\\GitHub\\AlgoritmosIII\\TP3\\ej3\\Ejercicio3\\bin\\" + "resultadosDeTiempos.out");
		FileWriter fw = new FileWriter(archivoTiempos, true);
		fw.append(String.valueOf(cantDeNodos) + " " + String.valueOf(elapsed) + "\n");
		fw.close();
	}

	private static int CalcularConflictos(Grafo grafo) 
	{
		int conflictos = 0;
		for ( Nodo nodo : grafo.getNodos() )
			conflictos += CantidadDeVecinosDeMiColor(grafo, nodo);
			
		return conflictos/2;
			
	}

	private static int CantidadDeVecinosDeMiColor(Grafo grafo, Nodo nodoActual) 
	{
		int cantidadDeVecinosCopiones = 0;
		for (Nodo nodoVecino : grafo.getVecinosDe(nodoActual))
		{
			if (nodoVecino.getColor() == nodoActual.getColor())
				cantidadDeVecinosCopiones++;
		}
		return cantidadDeVecinosCopiones;
			
	}
}
