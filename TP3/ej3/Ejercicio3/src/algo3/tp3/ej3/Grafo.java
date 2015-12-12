package algo3.tp3.ej3;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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
			colaNodos.add(PicANode(this)); //O(n)
			
			while (!colaNodos.isEmpty()) //O(n)
			{
				Nodo nodoActual = colaNodos.removeFirst(); //O(1)
				
				if (!nodoActual.isVisitado())
				{
					colaNodos.addAll(this.getVecinosDe(nodoActual)); //O(n)
					LinkedList<Integer> coloresRestantes = nodoActual.getColoresRestantes(); //O(1)
					PintarNodo(nodoActual, coloresRestantes, this); //O(c*n*log(n))
					nodosPintados ++;
					nodoActual.setVisitado(true); //O(1)
				}
			}
		}
		//Ej3Utils.PrintGraph(this);
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
	
	private static void PintarNodo(Nodo nodoActual, LinkedList<Integer> coloresRestantes, Grafo grafo) //O(c*n*log(n))
	{
		int colorAPintar = CalcularColorMenosPerjudicial(nodoActual, grafo); //O(c*n*log(n))
		nodoActual.setColor(colorAPintar); 
	}

	private static int CalcularColorMenosPerjudicial(Nodo nodoActual, Grafo grafo) //O(c*n*log(n))
	{
		int colorAPintar = nodoActual.getColoresRestantes().getFirst();
		Double pesoColor = CalcularPeso(colorAPintar, grafo.getVecinosDe(nodoActual));
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


	private static Double CalcularPeso(int color, List<Nodo> vecinos) 
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
	
	
	public static void AgregarAristas(String nombreDeArchivo, int cantidadDeAristas, int cantNodos) throws IOException 
	{
		File grafoModificable = new File("C:\\Users\\Bel\\Documents\\GitHub\\AlgoritmosIII\\TP3\\ej3\\Ejercicio3\\bin\\"  + nombreDeArchivo);
		FileWriter fw1 = new FileWriter(grafoModificable, true);
		
		HashSet<Arista> aristas = Ej3Utils.GenerarAristas(cantidadDeAristas, cantNodos);
		Iterator<Arista> iteradorArista = aristas.iterator();
		while(iteradorArista.hasNext())
		{
			Arista arista = iteradorArista.next();
			iteradorArista.remove();
			fw1.append(String.valueOf(arista.desde) + " " + String.valueOf(arista.hasta) + "\n");
		}
		fw1.close();
	}
}
