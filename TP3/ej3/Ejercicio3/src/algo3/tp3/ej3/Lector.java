package algo3.tp3.ej3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Lector {
	
	private BufferedReader archivo;
	
	public Lector(String archivo) throws Exception
	{
		this.setArchivo(new BufferedReader(  new FileReader("./" + archivo)));
	}
	
	public Grafo MakeGraph(int cantAristas) throws IOException 
	{
		Grafo grafo = new Grafo();
		int[] nodosAristasColores = Ej3Utils.ToIntegerArray(this.getArchivo().readLine().split(" "));
		grafo.cantidadDeNodos = nodosAristasColores[0];
		if (cantAristas == -1)
			grafo.setCantidadDeAristas(nodosAristasColores[1]);
		else
			grafo.setCantidadDeAristas(cantAristas);
		grafo.setCantidadDeColores(nodosAristasColores[2]);
		
		try { grafo.setNodos(this.ObtenerListaDeNodos(grafo.cantidadDeNodos, grafo.getCantidadDeColores()));} 
		catch (IOException e) { System.out.println("Se produjo un error al generar los nodos del grafo.");}
		boolean[][] matrizDeAdyacencia = GenerarMatrizDeAdyacencia(grafo.getCantidadDeNodos(), grafo.getCantidadDeAristas(), false);
		grafo.setListaDeAdyacencia(Ej3Utils.matrizDeAdyacenciaToListDeAdyacencia(matrizDeAdyacencia, grafo.getNodos()));
		
		return grafo;
	}
		
	public boolean[][] GenerarMatrizDeAdyacencia(int cantidadDeNodos, int cantidadDeAristas, boolean imprimirAristas) 
	{
		boolean[][] res = new boolean[cantidadDeNodos][cantidadDeNodos];

		if (imprimirAristas)
		{
			StringBuilder aristasDescripcion = new StringBuilder();
			aristasDescripcion.append("Aristas [");
			for (int i = 0; i < cantidadDeAristas; i++)
				CompletarDatosDeArista(res, aristasDescripcion);
			aristasDescripcion.append("] \n");
			System.out.print(aristasDescripcion.toString().replace(",]", "]"));
		}
		else
		{
			for (int i = 0; i < cantidadDeAristas; i++)
				CompletarDatosDeArista(res);
		}
		
		return res;
	}
	
	private void CompletarDatosDeArista(boolean [][] matriz) 
	{
		String[] linea = new String[2];
		
		try { linea = this.getArchivo().readLine().split(" ");}
		catch (IOException e) {System.out.println("Ocurrió un error al intentar completar la matriz de adyacencia.");}
		
		int inicio = Integer.parseInt(linea[0]);
		int destino = Integer.parseInt(linea[1]);
		matriz[inicio][destino] = true;
		matriz[destino][inicio] = true;
	}

	private void CompletarDatosDeArista(boolean [][] matriz, StringBuilder aristasDescripcion) 
	{
		String[] linea = new String[2];
		
		try { linea = this.getArchivo().readLine().split(" ");}
		catch (IOException e) {System.out.println("Ocurrió un error al intentar completar la matriz de adyacencia.");}
		
		int inicio = Integer.parseInt(linea[0]);
		int destino = Integer.parseInt(linea[1]);
		matriz[inicio][destino] = true;
		matriz[destino][inicio] = true;
		aristasDescripcion.append("(" + inicio + "," + destino + "),");
	}


	ArrayList<Nodo> ObtenerListaDeNodos(int cantidadDeNodos, int cantidadTotalDeColores) throws IOException 
	{
		ArrayList<Nodo> res = new ArrayList<Nodo>();
		for(int j = 0; j < cantidadDeNodos; j++)
		{
			String[] linea = this.getArchivo().readLine().split(" ");
			res.add(new Nodo(j, cantidadTotalDeColores, Ej3Utils.ToIntegerArray(Arrays.copyOfRange(linea, 1, linea.length))));
		}
		return res;
	}

	public BufferedReader getArchivo() {
		return archivo;
	}

	public void setArchivo(BufferedReader archivo) {
		this.archivo = archivo;
	}







}
