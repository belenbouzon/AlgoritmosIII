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
		while(nodosPintados < grafo.cantidadDeNodos && iteraciones < 5)
		{
			boolean rondaPar = iteraciones % 2 == 0;
			colaNodos.add(PicFirstNode(grafo));
			
			while (!colaNodos.isEmpty())
			{
					Nodo nodoActual = colaNodos.removeFirst();
					
					if (rondaPar == nodoActual.isVisitado())
					{
						colaNodos.addAll(grafo.getVecinosDe(nodoActual));
						if (nodoActual.getColor() == -1)
						{
							LinkedList<Integer> coloresRestantes = nodoActual.getColoresRestantes();
							int cantColoresRestantes = coloresRestantes.size();
							
							if(cantColoresRestantes == 1)
							{
								PintarNodo(nodoActual, coloresRestantes);
							}
							else
							{
								DescartarColorParaElNodo(grafo, nodoActual);
							}
						}
				
						nodoActual.setVisitado(!nodoActual.isVisitado());
					}
			}
			Ej3Utils.PrintGraph(grafo, iteraciones);

			iteraciones++;
		}

		return grafo;
	}

	private static void DescartarColorParaElNodo(Grafo grafo, Nodo nodoActual) {
		int colorMenosImportante = CalcularColorMenosImportante(nodoActual, grafo); 
		nodoActual.TacharColor(colorMenosImportante);
	}

	private static void PintarNodo(Nodo nodoActual, LinkedList<Integer> coloresRestantes) {
		System.out.print("Id: " + String.valueOf(nodoActual.getId()) + " Color pintado: " + String.valueOf(coloresRestantes.getFirst() + "\n"));
		nodoActual.setColor(coloresRestantes.getFirst());
		nodosPintados ++;
	}
	
	/*modificar de acuerdo a las heuristicas. Pasar a abstracto.*/
	private static int CalcularColorMenosImportante(Nodo nodoActual, Grafo grafo) 
	{
		Double pesoColor = 0.0;
		int colorAEliminar = -1;
		for (int color : nodoActual.getColoresRestantes())
		{
			Double peso = CalcularPeso(color, grafo.getVecinosDe(nodoActual));
			if (peso >= pesoColor)
			{
				pesoColor = peso;
				colorAEliminar = color;
			}	
		}	
		
		if (colorAEliminar == -1)
			colorAEliminar = nodoActual.getColoresRestantes().getFirst();
		
		System.out.print("Id: " + String.valueOf(nodoActual.getId()) + " Color a eliminar: " + String.valueOf(colorAEliminar) + "\n");
		return colorAEliminar;
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
	
	/*modificar de acuerdo a las heuristicas. Pasar a abstracto.*/
	private static Nodo PicFirstNode(Grafo grafo) 
	{
		return grafo.getNodos().get(0);
	}
}
