package algo3.tp3.ej3;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ej3Utils {

	public static int[] ToIntegerArray(String[] split) 
	{
		int[] res = new int[split.length];
		for (int i = 0; i < split.length; i ++)
			res[i] = Integer.parseInt(split[i]);
		return res;
	}
	
	public static void DibujarMatrizDeAdyacencia(boolean[][] matrix) 
	{
		int n = matrix.length;
		
		for (int i = 0; i < n; i++) 
		{
		    for (int j = 0; j < n; j++) 
		    {
		        System.out.print(matrix[i][j]? 1 + " " : 0  + " ");
		    }
		    System.out.print("\n");
		}
	}
	
	public static ArrayList<List<Nodo>> matrizDeAdyacenciaToListDeAdyacencia(boolean[][] matriz, ArrayList<Nodo> nodos)
	{
		//Ej3Utils.DibujarMatrizDeAdyacencia(matriz);
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
	
	public static void PrintGraph(Grafo grafo)
	{
		StringBuilder res = new StringBuilder();
		res.append("[");

		for(Nodo nodo : grafo.getNodos())
			res.append("(" + nodo.getId() + "," + nodo.getColor() + "),");

		res.append("] \n");
		String stringIteracion = res.toString().replace(",]", "]");
		System.out.print(stringIteracion);
		

	}
	
	public static HashSet<Arista> GenerarAristas(int cantAristas, int cantNodos) 
	{
		HashSet<Arista> aristas = new HashSet<Arista>();
		Random random = new Random();
		while (aristas.size() < cantAristas)
		{
			int inicio = random.nextInt(cantNodos);
			int destino = random.nextInt(cantNodos);
			if (inicio != destino)
				aristas.add(new Arista(inicio,destino));
		}
		return aristas;
	}
}
