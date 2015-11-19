public class GeneradorCasosDeTests
{
	
	private int cantNodos;
	private int cantAristas;
	private int cantTotalColores;
	GeneradorCasosDeTests(){}
	
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

	public String GenerarGrafoByCantAristas(int cantDeNodos, int cantDeAristas) throws Exception 
	{
		this.cantNodos        = cantDeNodos;
		this.cantAristas      = cantDeAristas;
		this.cantTotalColores = cantDeNodos/2; 
		
		String nombreDeArchivo = String.valueOf(cantDeAristas) + "Aristas.out";
		CrearArchivoDeGrafo(nombreDeArchivo);
		
		return nombreDeArchivo;
	}

	public String GenerarGrafoByCantColores(int cantDeNodos, int cantDeAristas, int cantColores) throws Exception 
	{
		this.cantNodos        = cantDeNodos;
		this.cantAristas      = cantDeAristas;
		this.cantTotalColores = cantColores; 
		
		String nombreDeArchivo = String.valueOf(cantColores) + "Colores.out";
		CrearArchivoDeGrafo(nombreDeArchivo);
		
		return nombreDeArchivo;
	}	    	
}