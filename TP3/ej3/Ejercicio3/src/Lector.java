import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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
		int[] nodosAristasColores = ToIntegerArray(this.archivo.readLine().split(" "));

		grafo.setCantidadDeNodos(nodosAristasColores[0]);
		grafo.setCantidadDeAristas(nodosAristasColores[1]);
		grafo.setCantidadDeColores(nodosAristasColores[2]);
		
		try { grafo.setNodos(this.ObtenerListaDeNodos(grafo.getCantidadDeNodos()));} 
		catch (IOException e) { System.out.println("Se produjo un error al generar los nodos del grafo.");}
		
		grafo.setMatrizDeAdyacencia(this.GenerarMatrizDeAdyacencia(grafo.getCantidadDeNodos(), grafo.getCantidadDeAristas()));

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

	private ArrayList<Nodo> ObtenerListaDeNodos(int cantidadDeNodos) throws IOException 
	{
		ArrayList<Nodo> res = new ArrayList<Nodo>();
		
		for(int j = 0; j < cantidadDeNodos; j++)
			res.add(CrearNodo(j));

		return res;
	}

	private Nodo CrearNodo(int j) throws IOException {
		String[] linea = this.archivo.readLine().split(" ");
		Nodo nodo = new Nodo();
		nodo.setCantidadDeColoresPosibles(Integer.valueOf(linea[0]));
		nodo.setColoresPosibles(ToIntegerArray(Arrays.copyOfRange(linea, 1, linea.length)));
		nodo.setId(j);
		nodo.setColor(-1);
		//nodo.setPadre(-1);
		//nodo.setVisitado(false);
		return nodo;
	}

	private int[] ToIntegerArray(String[] split) 
	{
		int[] res = new int[split.length];
		for (int i = 0; i < split.length; i ++)
			res[i] = Integer.parseInt(split[i]);
		return res;
	}



}