import java.util.ArrayList;

public class Grafo {
	
	private int cantidadDeNodos;
	private boolean[][] matrizDeAdyacencia; //pago una vez n^2 para construirla para q al preguntar si un nodo es adyacente a otro
											//no tenga que recorrer toda la lista de nodos vecinos.
	private ArrayList<Nodo> nodos;
	private int cantidadDeAristas;
	private int cantidadDeColores;
	
	
	public int getCantidadDeNodos() {
		return cantidadDeNodos;
	}
	public void setCantidadDeNodos(int cantidadDeNodos) {
		this.cantidadDeNodos = cantidadDeNodos;
	}
	public boolean[][] getMatrizDeAdyacencia() {
		return matrizDeAdyacencia;
	}
	public void setMatrizDeAdyacencia(boolean[][] matrizDeAdyacencia) {
		this.matrizDeAdyacencia = matrizDeAdyacencia;
	}
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

}
