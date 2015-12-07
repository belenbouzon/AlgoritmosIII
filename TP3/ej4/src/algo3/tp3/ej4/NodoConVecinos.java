package algo3.tp3.ej4;


import java.util.HashSet;
import java.util.Set;

import algo3.tp3.ej3.Nodo;

public class NodoConVecinos extends Nodo {
	private HashSet<NodoConVecinos> _vecinos;
	private HashSet<Integer> _coloresPosibles;
	/* no vamos a usar en este ejercicio las siguientes estructuras de datos:
	 * coloresDescartados
	 * coloresRestantes
	 * visitado
	 * seguimientoColoresTotales
	*/
	
	
	public HashSet<Integer> get_coloresPosibles() {
		return _coloresPosibles;
	}
	public Set<NodoConVecinos> vecinos(){
		return this._vecinos;
	}
	public void agregarVecinos(NodoConVecinos otro){
		this._vecinos.add(otro);
		otro._vecinos.add(this);
	}
	
	public NodoConVecinos(int id){
		this.setId(id);
		this.setColor(-1);
		this._vecinos = new HashSet<NodoConVecinos>();
	}
	
	public NodoConVecinos(Nodo otro){
		// creamos el NodoConVecinos a partir de uno del ej3.
		// OJO: no le marcamos los vecinos. Es lo hacemos con agregarVecinos.
		this.setId(otro.getId());
		this.setColor(otro.getColor());
		assert(this.getColor() >= 0);
		this._coloresPosibles = new HashSet<Integer>();
		int color = 0;
		for (boolean esta : otro.getSeguimientoColoresTotales()){
			if (esta) {
				this._coloresPosibles.add(color);
			}
			color++;
		}
		
		this._vecinos = new HashSet<NodoConVecinos>(); 
		
	}
	

	 
}
