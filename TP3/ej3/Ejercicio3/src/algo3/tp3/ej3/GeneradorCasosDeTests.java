package algo3.tp3.ej3;
public class GeneradorCasosDeTests
{
	
	private int cantNodos;
	private int cantAristas;
	private int cantTotalColores;
	public GeneradorCasosDeTests(){}
	
	public String GenerarGrafoByCantNodos(int cantDeNodos) throws Exception
	{
		
		this.cantNodos        = cantDeNodos;
		this.cantAristas      = cantDeNodos*(cantDeNodos-1)/10;
		this.cantTotalColores = cantDeNodos; 
		
		if (this.cantAristas > ((this.cantNodos - 1) * this.cantNodos) / 2 )
			throw new Exception ("Hay más aristas de las que puede tener un grafo completo.");
		
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

	public String GenerarArchivoDeGrafoByCantAristas(int cantDeNodos, int cantDeAristas) throws Exception 
	{
		this.cantNodos        = cantDeNodos;
		this.cantAristas      = cantDeAristas;
		this.cantTotalColores = cantDeNodos/2; 
		
		if (this.cantAristas > ((this.cantNodos - 1) * this.cantNodos) / 2 )
			throw new Exception ("Hay más aristas de las que puede tener un grafo completo.");
		
		String nombreDeArchivo = String.valueOf(cantDeAristas) + "Aristas.out";
		CrearArchivoDeGrafo(nombreDeArchivo);
		
		return nombreDeArchivo;
	}

	public String GenerarArchivoDeGrafoByCantColores(int cantDeNodos, int cantDeAristas, int cantColores) throws Exception 
	{
		this.cantNodos        = cantDeNodos;
		this.cantAristas      = cantDeAristas;
		this.cantTotalColores = cantColores; 
		
		long maxAristas = (((long) this.cantNodos) * ((long) this.cantNodos - 1))/2; 
		if (this.cantAristas > maxAristas)
			throw new Exception ("Hay más aristas de las que puede tener un grafo completo.");
		
		String nombreDeArchivo = String.valueOf(cantColores) + "Colores.out";
		CrearArchivoDeGrafo(nombreDeArchivo);
		
		return nombreDeArchivo;
	}

	public String GenerarArchivoDeGrafoSinAristas(int cantDeNodos) throws Exception 
	{
		return GenerarArchivoDeGrafoByCantAristas(cantDeNodos,0);
		
	}	    	
}
