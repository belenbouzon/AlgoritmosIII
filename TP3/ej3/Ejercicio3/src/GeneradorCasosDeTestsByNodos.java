public class GeneradorCasosDeTestsByNodos
{
	
	private int cantNodos;
	private int cantAristas;
	private int cantTotalColores;
	GeneradorCasosDeTestsByNodos(){}
	
	public String GenerarGrafoByCantNodos(int cantDeNodos) throws Exception
	{
		
		this.cantNodos        = cantDeNodos;
		this.cantAristas      = cantDeNodos*(cantDeNodos-1)/10;
		this.cantTotalColores = cantDeNodos; 
		
		String nombreDeArchivo = String.valueOf(cantNodos) + "Nodos.out";
		CrearArchivoDeGrafo(nombreDeArchivo);
		
		return nombreDeArchivo;
	}

	private void CrearArchivoDeGrafo(String nombreDeArchivo) throws Exception 
	{
		
		Escritor escritor = new Escritor(nombreDeArchivo);
		escritor.EscribirString(String.valueOf(cantNodos) + " " + String.valueOf(cantAristas) + " " + String.valueOf(cantTotalColores) + "\n");
		
		for (int i = 0; i < cantNodos; i++)
			escritor.PrintNewNode(cantTotalColores);
		
		escritor.GenerateAndPrintAristas(cantAristas, cantNodos);
		escritor.Fin();	
	}	    	
}