import java.io.IOException;

public class GeneradorCasosDeTestsByNodos
{
	
	private int cantNodos;
	private int cantAristas;
	private int cantTotalColores;

	GeneradorCasosDeTestsByNodos(int cantDeNodos) throws Exception
	{
		
		this.cantNodos        = cantDeNodos;
		this.cantAristas      = cantDeNodos*(cantDeNodos-1)/10;
		this.cantTotalColores = cantDeNodos + 10; 
		
		GenerarCasoValidoConCantDeNodos();
	}

	private void GenerarCasoValidoConCantDeNodos() throws IOException, Exception 
	{
		CrearArchivoDeGrafo();

		while (!EsConexo(String.valueOf(cantNodos) + "Nodos.out"))
			CrearArchivoDeGrafo();
	}

	private void CrearArchivoDeGrafo() throws Exception 
	{
		
		Escritor escritor = new Escritor(String.valueOf(cantNodos) + "Nodos.out");
		escritor.EscribirString(String.valueOf(cantNodos) + " " + String.valueOf(cantAristas) + " " + String.valueOf(cantTotalColores) + "\n");
		
		for (int i = 0; i < cantNodos; i++)
			escritor.PrintNewNode(cantTotalColores);
		
		
		escritor.GenerateAndPrintAristas(cantAristas, cantNodos);
		escritor.Fin();	
	}

	private boolean EsConexo(String archivoGrafo) throws Exception 
	{
		Lector lector = new Lector(archivoGrafo);
		int[] nodosAristasColores = Ej3Utils.ToIntegerArray(lector.getArchivo().readLine().split(" "));
		lector.ObtenerListaDeNodos(nodosAristasColores[0], nodosAristasColores[2]);
		boolean[][] rsult = lector.GenerarMatrizDeAdyacencia(nodosAristasColores[0], nodosAristasColores[1]);
		int x = 0;
		if (rsult.length > 0) 
		{
			for (int i = 0; i < rsult.length - 1; i++) 
			{
				for (int j = 0; j < rsult.length - 1; j++) 
				{
					if (i != j) 
					{
						if (!rsult[i][j] && !rsult[j][i]) 
							x += 1;
					} 
				}
				if (x == rsult.length - 1) 
					return false;
				x = 0;
			}
			return true;
		}
		return false;
	}
	
	
	    	
}
