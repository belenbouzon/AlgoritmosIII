package algo3.tp3.ej3;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main 
{
	public static void main(String[] args) throws Exception 
	{	
		/*Parametros*/
		int cantDeNodos = 1000;
		/*int cantDeAristas = 800000; 
		int escala = 10000;
		int cantMaximaDeAristas = 1000*999;
		int cantColores = 4;
		int cantMaximaDeColores = 1001;*/
		
		//GenerarTestEnFuncionDeColores(cantColores, cantMaximaDeColores, cantDeNodos, cantDeAristas, escala);
		
		//GenerarTestEnFuncionDeAristas(cantDeNodos, cantColores, escala);
/*		int cantDeNodosMaxima = 2000;
		GenerarTestCiclico(cantDeNodosMaxima);
*/
		
		System.out.println("Proceso finalizado");
	}

/*	private static void GenerarTestCiclico(int cantDeNodosMaxima) throws Exception 
	{
		CrearArchivosDeSalida();
		GeneradorCasosDeTests generador = new GeneradorCasosDeTests();
		String nombreDeArchivo = generador.GenerarArchivoDeGrafoCiclico(cantDeNodosMaxima);	
		
		int cantDeNodos = 1000;
		
		while(cantDeNodos <= cantDeNodosMaxima)
		{
			Lector lector = new Lector(nombreDeArchivo);
		
			long greedyTime0 = System.nanoTime();
			Grafo grafoResultante = lector.MakeGraph(cantDeNodos);
			grafoResultante.MakeRainbow();
			long greedyTime1 = System.nanoTime();
			int cantConflictosGreedy = CalcularConflictos(grafoResultante);
		
			long localTime0 = System.nanoTime();
			int cantConflictosLocal = Tester.resolverConLocalyDevolverCantConflictos(" ", grafoResultante, 1);
			long localTime1  = System.nanoTime();
		
			EscribirArchivoTiempos(cantDeNodos, greedyTime1-greedyTime0, localTime1-localTime0);
			EscribirArchivoConflictos(cantDeNodos, cantConflictosGreedy, cantConflictosLocal);
			
			cantDeNodos += 1000;
		}
	}*/


	public static void EscribirArchivoConflictos(int cantDeNodos, int cantConflictosGreedy, int cantConflictosLocal) throws IOException 
	{
		File archivoConflictos = new File(pathConflictos);
		FileWriter fw1 = new FileWriter(archivoConflictos, true);
		fw1.append(String.valueOf(cantDeNodos) + " " + String.valueOf(cantConflictosGreedy) + " " + String.valueOf(cantConflictosLocal) + "\n");
		fw1.close();
	}

	public static void EscribirArchivoTiempos(int cantDeNodos, long tiempoGreedy, long tiempoLocal) throws IOException 
	{
		File archivoTiempos = new File(pathTiempos);
		FileWriter fw = new FileWriter(archivoTiempos, true);
		fw.append(String.valueOf(cantDeNodos) + " " + String.valueOf(tiempoGreedy) + " " + String.valueOf(tiempoLocal)+ "\n");
		fw.close();
	}

	private static void GenerarTestEnFuncionDeAristas(int cantDeNodos, int cantDeColores, int escala) throws Exception 
	{
		int cantMaximaDeAristas = cantDeNodos*(cantDeNodos-1);
		
		CrearArchivosDeSalida();
		GeneradorCasosDeTests generador = new GeneradorCasosDeTests();
		String nombreDeArchivo = generador.GenerarArchivoDeGrafoSinAristas(cantDeNodos);	//la cant de colores es x default la mitad de los nodos
		
		int cantidadDeAristas = 0;
		
		while(cantidadDeAristas <= cantMaximaDeAristas)
		{
			long elapsedTime;
			Lector lector = new Lector(nombreDeArchivo);
			
			long time0 = System.nanoTime();
			Grafo grafoResultante = lector.MakeGraph(cantidadDeAristas);
			grafoResultante.MakeRainbow();
			long time1 = System.nanoTime();
			
			elapsedTime = (time1-time0);
	
			EscribirArchivoTiempos(cantidadDeAristas, elapsedTime);
			EscribirArchivoConflictos(cantidadDeAristas, grafoResultante);
			
			Grafo.AgregarAristas(nombreDeArchivo, escala, cantDeNodos);
			
			cantidadDeAristas += escala;
			System.out.println("cantidad de Aristas: " + String.valueOf(cantidadDeAristas) + "\n");
		}		
	}

	public static void CrearArchivosDeSalida() throws Exception, IOException 
	{
		Escritor archivoDeTiempos = new Escritor("resultadosDeTiempos.out");
		archivoDeTiempos.Fin();

		Escritor archivoDeConflictos = new Escritor("resultadosDeConflictos.out");
		archivoDeConflictos.Fin();
	}

	private void GenerarTestEnFuncionDeColores(int cantColores, int cantMaximaDeColores, int cantDeNodos, int cantDeAristas, int escala) throws Exception, IOException {
		CrearArchivosDeSalida();
		
		while (cantColores <= cantMaximaDeColores)
		{
			
			GeneradorCasosDeTests generador = new GeneradorCasosDeTests();
			String nombreArchivo = generador.GenerarArchivoDeGrafoByCantColores(cantDeNodos, cantDeAristas, cantColores);
			
			Lector lector = new Lector(nombreArchivo);	
			long elapsed = 0;
		
			long time0 = System.nanoTime();
			Grafo grafoResultante = lector.MakeGraph(-1);
			grafoResultante.MakeRainbow();
			long time1 = System.nanoTime();
			
			elapsed += (time1-time0);

			EscribirArchivoTiempos(cantColores, elapsed);
			EscribirArchivoConflictos(cantColores, grafoResultante);
			
			cantColores += escala;
			System.out.println("Procesando COLORES: " + String.valueOf(cantColores) + "\n");
		}
	}

	private static void EscribirArchivoConflictos(int cantDeNodos, Grafo grafo) throws IOException {
		File archivoConflictos = new File(pathConflictos);
		int cantidadDeConflictos = CalcularConflictos(grafo);
		FileWriter fw1 = new FileWriter(archivoConflictos, true);
		fw1.append(String.valueOf(cantDeNodos) + " " + String.valueOf(cantidadDeConflictos)+ "\n");
		fw1.close();
	}

	private static void EscribirArchivoTiempos(int cantDeNodos, long elapsed) throws IOException {
		File archivoTiempos = new File(pathTiempos);
		FileWriter fw = new FileWriter(archivoTiempos, true);
		fw.append(String.valueOf(cantDeNodos) + " " + String.valueOf(elapsed) + "\n");
		fw.close();
	}

	public static int CalcularConflictos(Grafo grafo) 
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
	private static String pathTiempos = "C:\\Users\\Bel\\Documents\\GitHub\\AlgoritmosIII\\TP3\\ej5\\" + "resultadosDeTiempos.out";
	private static String pathConflictos =  "C:\\Users\\Bel\\Documents\\GitHub\\AlgoritmosIII\\TP3\\ej5\\"  + "resultadosDeConflictos.out";
}
