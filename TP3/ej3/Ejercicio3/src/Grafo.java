import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Grafo {
	
	public int cantidadDeNodos;
	private ArrayList<Nodo> nodos;
	private int cantidadDeAristas;
	private int cantidadDeColores;
	ArrayList<List<Nodo>> listaDeAdyacencia;
	
	public ArrayList<Nodo> getNodos() 
	{
		return nodos;
	}
	public void setNodos(ArrayList<Nodo> nodos) 
	{
		this.nodos = nodos;
	}
	public int getCantidadDeAristas() 
	{
		return cantidadDeAristas;
	}
	public void setCantidadDeAristas(int cantidadDeAristas)
	{
		this.cantidadDeAristas = cantidadDeAristas;
	}
	public int getCantidadDeColores() 
	{
		return cantidadDeColores;
	}
	public void setCantidadDeColores(int cantidadDeColores) 
	{
		this.cantidadDeColores = cantidadDeColores;
	}
	public List<Nodo> getVecinosDe(Nodo nodo)
	{
		return this.listaDeAdyacencia.get(nodo.getId());
	}
	public int getCantidadDeNodos() 
	{
		return this.cantidadDeNodos;
	}
	public void setListaDeAdyacencia(ArrayList<List<Nodo>> listaDeAdyacencia) 
	{
		this.listaDeAdyacencia = listaDeAdyacencia;
	}
	
	public void MakeRainbow() 
	{
		int nodosPintados = 0;
		LinkedList<Nodo> colaNodos = new LinkedList<Nodo>();

		while(nodosPintados < this.cantidadDeNodos)
		{
			colaNodos.add(PicANode(this)); //O(1)
			
			while (!colaNodos.isEmpty()) //O(n)
			{
				Nodo nodoActual = colaNodos.removeFirst(); //O(1)
				
				if (!nodoActual.isVisitado())
				{
					colaNodos.addAll(this.getVecinosDe(nodoActual)); //O(n)
					LinkedList<Integer> coloresRestantes = nodoActual.getColoresRestantes(); //O(1)
					PintarNodo(nodoActual, coloresRestantes, this); //O(1)
					nodosPintados ++;
					nodoActual.setVisitado(true); //O(1)
				}
			}
		}
		Ej3Utils.PrintGraph(this);
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
	
	private static void PintarNodo(Nodo nodoActual, LinkedList<Integer> coloresRestantes, Grafo grafo) //O(1)
	{
		int colorAPintar = CalcularColorMenosPerjudicial(nodoActual, grafo);
		nodoActual.setColor(colorAPintar); 
	}

	private static int CalcularColorMenosPerjudicial(Nodo nodoActual, Grafo grafo) 
	{
		Double pesoColor = 1.0;
		int colorAPintar = -1;
		for (int color : nodoActual.getColoresRestantes()) //O(c)
		{
			Double peso = CalcularPeso(color, grafo.getVecinosDe(nodoActual)); //O(nlog(n))
			if (peso <= pesoColor)
			{
				pesoColor = peso;
				colorAPintar = color;
			}	
		}	
		return colorAPintar;
	}


	private static Double CalcularPeso(int color, List<Nodo> vecinos) //da mayor valor cuanto mas riesgoso es pintar del color pasado por parametro
	{
		ArrayList<Double> pesos = new ArrayList<Double>();
		
		for (Nodo nodo : vecinos) //O(n)
		{
			if (nodo.LeImportaQueSuVecinoSePinteDelColor(color)) //O(1)
					pesos.add(nodo.PeligroDePintarUnVecinoDelColor(color));//O(1)(amortizado)
					
		}
		Collections.sort(pesos); //O(nlog(n))
		Double pesoTotal = 0.0;
		for (int k = 0; k < pesos.size(); k++) //O(n)
			pesoTotal += pesos.get(k)/(k+2); //O(1)
		
		return pesoTotal;
	}
}
