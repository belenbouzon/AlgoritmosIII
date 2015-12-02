

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NodoConVecinos extends Nodo {
	private Set<NodoConVecinos> _vecinos;
	public Set<NodoConVecinos> vecinos(){
		return this._vecinos;
	}
	public void agregarVecinos(NodoConVecinos otro){
		this._vecinos.add(otro);
		otro._vecinos.add(this);
	}
	public NodoConVecinos(int j, int cantidadDeColoresDelGrafo, int[] colores){
		this.setColores(colores, cantidadDeColoresDelGrafo);
		this.setId(j);
		this.setColor(-1);
		this.setVisitado(false);
		this._vecinos = new LinkedHashSet<NodoConVecinos>();
	}
	public NodoConVecinos(Nodo nodo){
		this.setId(nodo.getId());
	/*	private boolean visitado;
		this.setColor(nodo.getColor());
		private boolean[] seguimientoColoresTotales; //Almacena todos los colores seteando un bool para los que tiene.
		this.coloresDescartados = null;
		List<NodoConVecinos> nueva_lista = new LinkedList(nodo.getColoresRestantes());
		this.se
		*/
	}
	
	/*
	 * Con estas tres lineas leemos el input, y ya en grafoResultante nos queda el grafo resuelto con goloso.
	 	Lector lector = new Lector(nombreDeArchivo);
		Grafo grafoResultante = lector.MakeGraph(cantidadDeAristas);
		grafoResultante.MakeRainbow();
			
	  */
	 
}
