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
		escritor.EscribirString(String.valueOf(this.cantNodos) + " " + String.valueOf(this.cantAristas) + " " + String.valueOf(this.cantTotalColores) + "\n");
		
		for (int i = 0; i < this.cantNodos; i++)
			escritor.PrintNewNode(this.cantTotalColores);
		
		escritor.GenerateAndPrintAristas(cantAristas, cantNodos);
		escritor.Fin();	
	}

	public String GenerarArchivoDeGrafoByCantAristas(int cantDeNodos, int cantDeAristas) throws Exception 
	{
		this.cantNodos        = cantDeNodos;
		this.cantAristas      = cantDeAristas;
		this.cantTotalColores = cantDeNodos/2; 
		
		if (this.cantAristas > ((this.cantNodos - 1) * this.cantNodos) / 2 )
			throw new Exception ("Hay mas aristas de las que puede tener un grafo completo.");
		
		String nombreDeArchivo = String.valueOf(cantDeAristas) + "Aristas.out";
		CrearArchivoDeGrafo(nombreDeArchivo);
		
		return nombreDeArchivo;
	}
	
	public String GenerarArchivoDeGrafoCiclico(int cantDeNodos) throws Exception 
	{
		this.cantNodos        = cantDeNodos;
		this.cantAristas      = cantDeNodos;
		this.cantTotalColores = 2; //vamos a hacer que siempre llegue un ciclo par (que sabemos 2-coloreable) -> Los casos en que dos nodos solo tienen un color para pintarse deberian ser los unicos conflictos
		
	
		String nombreDeArchivo = String.valueOf(cantDeNodos) + "Nodos - Ciclico.out";
		Escritor escritor = new Escritor(nombreDeArchivo);
		escritor.EscribirString(String.valueOf(this.cantNodos) + " " + String.valueOf(this.cantAristas) + " " + String.valueOf(this.cantTotalColores) + "\n");
		
		for (int i = 0; i < this.cantNodos; i++)
			escritor.PrintNewNode(this.cantTotalColores);	
		
		escritor.GenerateAndPrintAristasCiclico(cantNodos);
		escritor.Fin();	
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
