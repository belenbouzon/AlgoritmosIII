import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Lector {
	
	private BufferedReader archivo;
	
	public Lector(String archivo) throws Exception
	{
		this.archivo = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(archivo)));
		//this.path = getClass().getResource( "" ).getPath();
	}
	
	public Grafo MakeGraph() throws IOException 
	{
		Grafo grafo = new Grafo();
		int[] nodosAristasColores = Ej3Utils.ToIntegerArray(this.archivo.readLine().split(" "));
		grafo.cantidadDeNodos = nodosAristasColores[0];
		grafo.setCantidadDeAristas(nodosAristasColores[1]);
		grafo.setCantidadDeColores(nodosAristasColores[2]);
		
		try { grafo.setNodos(this.ObtenerListaDeNodos(grafo.cantidadDeNodos, grafo.getCantidadDeColores()));} 
		catch (IOException e) { System.out.println("Se produjo un error al generar los nodos del grafo.");}
		boolean[][] matrizDeAdyacencia = GenerarMatrizDeAdyacencia(grafo.getCantidadDeNodos(), grafo.getCantidadDeAristas());
		grafo.setListaDeAdyacencia(matrizDeAdyacenciaToListDeAdyacencia(matrizDeAdyacencia, grafo.getNodos()));
		
		return grafo;
	}
		
	private boolean[][] GenerarMatrizDeAdyacencia(int cantidadDeNodos, int cantidadDeAristas) 
	{
		boolean[][] res = new boolean[cantidadDeNodos][cantidadDeNodos];
		for (int i = 0; i < cantidadDeAristas; i++)
			CompletarDatosDeArista(res);
		return res;
	}

	private void CompletarDatosDeArista(boolean [][] matriz) {
		String[] linea = new String[2];
		
		try { linea = this.archivo.readLine().split(" ");}
		catch (IOException e) {System.out.println("Ocurri� un error al intentar completar la matriz de adyacencia.");}
		
		int inicio = Integer.parseInt(linea[0]);
		int destino = Integer.parseInt(linea[1]);
		matriz[inicio][destino] = true;
		matriz[destino][inicio] = true;
	}

	private ArrayList<Nodo> ObtenerListaDeNodos(int cantidadDeNodos, int cantidadTotalDeColores) throws IOException 
	{
		ArrayList<Nodo> res = new ArrayList<Nodo>();
		for(int j = 0; j < cantidadDeNodos; j++)
		{
			String[] linea = this.archivo.readLine().split(" ");
			res.add(new Nodo(j, cantidadTotalDeColores, Ej3Utils.ToIntegerArray(Arrays.copyOfRange(linea, 1, linea.length-1))));
		}
		return res;
	}




private ArrayList<List<Nodo>> matrizDeAdyacenciaToListDeAdyacencia(boolean[][] matriz, ArrayList<Nodo> nodos)
{
	ArrayList<List<Nodo>> res = new ArrayList<List<Nodo>>(nodos.size());
	for (int i = 0; i < nodos.size(); i++)
		res.add(i, new LinkedList<Nodo>());
	
    for (Nodo nodo: nodos)  
	{
    	for (int j = 0; j < nodos.size(); j++)
    	{
    		if (matriz[j][nodo.getId()])
    			res.get(j).add(nodo);
    	}
	}
    return res;
}

}
