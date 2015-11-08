import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Coloring 
{
	private static int nodosPintados = 0;
	private static LinkedList<Nodo> colaNodos = new LinkedList<Nodo>();
	
	public static void MakeRainbow(Grafo grafo) 
	{
		
		//Setear en no visitado cada uno de los nodos
		colaNodos.add(PicFirstNode(grafo));
		
		while (!colaNodos.isEmpty())
		{
				Nodo nodoActual = colaNodos.removeFirst();
				if (!nodoActual.isVisitado())
				{
					colaNodos.addAll(grafo.getVecinosDe(nodoActual));
			
					LinkedList<Integer> coloresRestantes = nodoActual.getColoresRestantes();
					int cantColoresRestantes = coloresRestantes.size();
					
					if(cantColoresRestantes == 1)
					{
						nodoActual.setColor(coloresRestantes.removeFirst());
						nodosPintados ++;
					}
					else
					{
						int colorMenosImportante = CalcularColorMenosImportante(nodoActual, grafo); 
						nodoActual.TacharColor(colorMenosImportante);
					}
			
					nodoActual.setVisitado(true);
			}
		}
	}

	private static int CalcularColorMenosImportante(Nodo nodoActual, Grafo grafo) 
	{//modificar de acuerdo a las heuristicas. Pasar a abstracto.
		Double pesoColor = 0.0;
		int colorAEliminar = -1;
		for (int color : nodoActual.getColoresRestantes())
		{
			Double peso = CalcularPeso(color, grafo.getVecinosDe(nodoActual));
			if (peso > pesoColor)
			{
				pesoColor = peso;
				colorAEliminar = color;
			}	
		}	
		return nodoActual.getColoresRestantes().getFirst(); //BORRAR esta implementacion. Ojo que ese metodo saca el primer elemento.
	}

	private static Double CalcularPeso(int color, List<Nodo> vecinos) 
	{
		LinkedList<Double> pesos = new LinkedList<Double>();
		for (Nodo nodo : vecinos)
		{
			if (nodo.getColoresRestantes().contains(color))
					pesos.add((1.0/nodo.getColoresRestantes().size()));
					
		}
		Collections.sort(pesos);
		Double pesoTotal = 0.0;
		for (int k = 0; k < pesos.size(); k++)
			pesoTotal += pesos.get(k)/(k+2);
		
		return pesoTotal;
	}

	private static Nodo PicFirstNode(Grafo grafo) 
	{ //modificar de acuerdo a las heuristicas. Pasar a abstracto.
		return grafo.getNodos().get(0);
	}
	

}
