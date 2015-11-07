import java.util.ArrayList;
import java.util.List;

public class Grafo {
	
	public int cantidadDeNodos;
	//private boolean[][] matrizDeAdyacencia; //pago una vez n^2 para construirla para q al preguntar si un nodo es adyacente a otro
											//no tenga que recorrer toda la lista de nodos vecinos.
	private ArrayList<Nodo> nodos;
	private int cantidadDeAristas;
	private int cantidadDeColores;
	ArrayList<List<Nodo>> listaDeAdyacencia;
	
/*	public boolean[][] getMatrizDeAdyacencia() {
		return matrizDeAdyacencia;
	}
	public void setMatrizDeAdyacencia(boolean[][] matrizDeAdyacencia) {
		this.matrizDeAdyacencia = matrizDeAdyacencia;
	}*/
	public ArrayList<Nodo> getNodos() {
		return nodos;
	}
	public void setNodos(ArrayList<Nodo> nodos) {
		this.nodos = nodos;
	}
	public int getCantidadDeAristas() {
		return cantidadDeAristas;
	}
	public void setCantidadDeAristas(int cantidadDeAristas) {
		this.cantidadDeAristas = cantidadDeAristas;
	}
	public int getCantidadDeColores() {
		return cantidadDeColores;
	}
	public void setCantidadDeColores(int cantidadDeColores) {
		this.cantidadDeColores = cantidadDeColores;
	}
	public List<Nodo> getVecinosDe(Nodo nodo)
	{
		return this.listaDeAdyacencia.get(nodo.getId());
	}
	public int getCantidadDeNodos() {
		return this.cantidadDeNodos;
	}
	public void setListaDeAdyacencia(ArrayList<List<Nodo>> listaDeAdyacencia) {
		this.listaDeAdyacencia = listaDeAdyacencia;
	}
}
