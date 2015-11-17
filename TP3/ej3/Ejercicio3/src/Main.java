public class Main 
{
	public static void main(String[] args) throws Exception 
	{	
		Escritor archivoDeTiempos = new Escritor("resultadosDeTiempos.out");
		Escritor archivoDeConflictos = new Escritor("resultadosDeConflictos.out");
		
		int cantDeNodos = 1000;
		int escala = 1000;
		int cantMaximaDeNodos = 100000;
		
		while (cantDeNodos < cantMaximaDeNodos)
		{
			GeneradorCasosDeTestsByNodos generador = new GeneradorCasosDeTestsByNodos();
			String nombreArchivo = generador.GenerarGrafoByCantNodos(cantDeNodos);
			Grafo grafoResultante = new Grafo();
			
			Lector lector = new Lector(nombreArchivo);	
			long time0 = System.nanoTime();
			grafoResultante = lector.MakeGraph();
			grafoResultante = Coloring.MakeRainbow(grafoResultante);
			long time1 = System.nanoTime();
			
			long elapsed = time1-time0;

			
			archivoDeTiempos.EscribirLinea(String.valueOf(cantDeNodos) + " " + String.valueOf(elapsed)+ "\n");
			int cantidadDeConflictos = CalcularConflictos(grafoResultante);
			archivoDeConflictos.EscribirLinea(String.valueOf(cantDeNodos) + " " + String.valueOf(cantidadDeConflictos)+ "\n");
			cantDeNodos += escala;
		}
		
		archivoDeTiempos.Fin();
		archivoDeConflictos.Fin();
		
		System.out.println("Proceso finalizado");
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
