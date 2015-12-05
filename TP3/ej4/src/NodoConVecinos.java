

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NodoConVecinos extends Nodo {
	private HashSet<NodoConVecinos> _vecinos;
	/* no vamos a usar en este ejercicio las siguientes estructuras de datos:
	 * coloresDescartados
	 * coloresRestantes
	 * visitado
	*/
	
	
	public Set<NodoConVecinos> vecinos(){
		return this._vecinos;
	}
	public void agregarVecinos(NodoConVecinos otro){
		this._vecinos.add(otro);
		otro._vecinos.add(this);
	}
	/*
	public NodoConVecinos(int j, int cantidadDeColoresDelGrafo, int[] colores){
		this.setColores(colores, cantidadDeColoresDelGrafo);
		this.setId(j);
		this.setColor(-1);
		this.setVisitado(false);
		this._vecinos = new HashSet<NodoConVecinos>();
	}
	*/
	public NodoConVecinos(Nodo otro){
		// creamos el NodoConVecinos a partir de uno del ej3.
		// OJO: no le marcamos los vecinos. Es lo hacemos con agregarVecinos.
		this.setId(otro.getId());
		this.setColor(otro.getColor());
		this.seguimientoColoresTotales = Arrays.copyOf(otro.getSeguimientoColoresTotales(), otro.getSeguimientoColoresTotales().length);
		this._vecinos = new HashSet<NodoConVecinos>(); 
		
	}
	

	 
}
