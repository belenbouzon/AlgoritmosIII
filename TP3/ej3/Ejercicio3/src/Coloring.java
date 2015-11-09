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
			boolean rondaPar = iteraciones % 2 == 0;
			colaNodos.add(PicFirstNode(grafo)); //O(1)
			
			while (!colaNodos.isEmpty()) //O(n)
			{
				Nodo nodoActual = colaNodos.removeFirst(); //O(1)
				
				if (rondaPar == nodoActual.isVisitado())
				{
					colaNodos.addAll(grafo.getVecinosDe(nodoActual)); //O(n)
					if (nodoActual.getColor() == -1)
					{
						LinkedList<Integer> coloresRestantes = nodoActual.getColoresRestantes(); //O(1)
						int cantColoresRestantes = coloresRestantes.size(); //O(1)
						
						if(cantColoresRestantes == 1)
							PintarNodo(nodoActual, coloresRestantes); //O(1)
						else
							DescartarUnColorDelNodo(grafo, nodoActual); //O(c*n*log(n))
					}
			
					nodoActual.setVisitado(!nodoActual.isVisitado()); //O(1)
				}
			}

			//Ej3Utils.PrintGraph(grafo, iteraciones);
			iteraciones++;
		}

		return grafo;
	}

	private static void DescartarUnColorDelNodo(Grafo grafo, Nodo nodoActual) //O(c*n*log(n))
	{
		int colorMenosImportante = CalcularColorMenosImportante(nodoActual, grafo); //O(c*n*log(n))
		nodoActual.TacharColor(colorMenosImportante);  //O(c)
	}

	private static void PintarNodo(Nodo nodoActual, LinkedList<Integer> coloresRestantes) //O(1)
	{
		nodoActual.setColor(coloresRestantes.getFirst()); 
		nodosPintados ++;
	}

	private static int CalcularColorMenosImportante(Nodo nodoActual, Grafo grafo) //O(c*n*log(n))
	{
		Double pesoColor = 0.0;
		int colorAEliminar = -1;
		for (int color : nodoActual.getColoresRestantes()) //O(c)
		{
			Double peso = CalcularPeso(color, grafo.getVecinosDe(nodoActual)); //O(nlog(n))
			if (peso >= pesoColor)
			{
				pesoColor = peso;
				colorAEliminar = color;
			}	
		}	
		return colorAEliminar;
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
	
	private static Nodo PicFirstNode(Grafo grafo) 
	{
		return grafo.getNodos().get(0);
	}
}
