import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Coloring 
{
	private static int nodosPintados = 0;
	private static LinkedList<Nodo> colaNodos = new LinkedList<Nodo>();
	
	public static Grafo MakeRainbow(Grafo grafo) 
	{
		int iteraciones = 0;
		while(nodosPintados < grafo.cantidadDeNodos)
		{
			colaNodos.add(PicANode(grafo)); //O(1)
			
			while (!colaNodos.isEmpty()) //O(n)
			{
				Nodo nodoActual = colaNodos.removeFirst(); //O(1)
				
				if (!nodoActual.isVisitado())
				{
					colaNodos.addAll(grafo.getVecinosDe(nodoActual)); //O(n)
					LinkedList<Integer> coloresRestantes = nodoActual.getColoresRestantes(); //O(1)
					PintarNodo(nodoActual, coloresRestantes, grafo); //O(1)
					nodoActual.setVisitado(true); //O(1)
				}
			}
			iteraciones++;
		}
		Ej3Utils.PrintGraph(grafo, iteraciones);
		return grafo;
	}


	private static void PintarNodo(Nodo nodoActual, LinkedList<Integer> coloresRestantes, Grafo grafo) //O(1)
	{
		int colorAPintar = CalcularColorMenosPerjudicial(nodoActual, grafo);
		nodoActual.setColor(colorAPintar); 
		nodosPintados ++;
	}

	private static int CalcularColorMenosPerjudicial(Nodo nodoActual, Grafo grafo) 
	{
		Double pesoColor = 0.0;
		int colorAPintar = -1;
		for (int color : nodoActual.getColoresRestantes()) //O(c)
		{
			Double peso = CalcularPeso(color, grafo.getVecinosDe(nodoActual)); //O(nlog(n))
			if (peso >= pesoColor)
			{
				pesoColor = peso;
				colorAPintar = color;
			}	
		}	
		return colorAPintar;
	}


	private static Double CalcularPeso(int color, List<Nodo> vecinos) 
	{
		ArrayList<Double> pesos = new ArrayList<Double>();
		
		for (Nodo nodo : vecinos) //O(n)
		{
			if (nodo.getSeguimientoColoresTotales()[color]) //O(1)
					pesos.add((1.0/nodo.getColoresRestantes().size()));//O(1)(amortizado)
					
		}
		Collections.sort(pesos); //O(nlog(n))
		Double pesoTotal = 0.0;
		for (int k = 0; k < pesos.size(); k++) //O(n)
			pesoTotal += pesos.get(k)/(k+2); //O(1)
		
		return pesoTotal;
	}
	
	private static Nodo PicANode(Grafo grafo) 
	{
		Nodo next = new Nodo();
		for(Nodo nodo : grafo.getNodos())
		{
			if (!nodo.isVisitado())
			{
				next = nodo;
				break;
			}
		}
		return next;
	}
}
