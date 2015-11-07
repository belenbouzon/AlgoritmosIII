import java.util.LinkedList;

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
		return nodoActual.getColoresRestantes().getFirst(); //BORRAR esta implementacion. Ojo que ese metodo saca el primer elemento.
	}

	private static Nodo PicFirstNode(Grafo grafo) 
	{ //modificar de acuerdo a las heuristicas. Pasar a abstracto.
		return grafo.getNodos().get(0);
	}
	

}
