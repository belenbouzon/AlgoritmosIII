package algo3.tp3.ej4;


import java.util.HashSet;
import java.util.LinkedList;
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
		assert(this.getColor() > 0);		// en el ej3 se usan colores entre 1 y c (en vez de entre 0 y c-1)
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
	
	public Integer conflictosColor(int color){
		Integer res = 0;
		for (NodoConVecinos n: this._vecinos){
			if (n.getColor() == color)
				res += 1;
		}
		return res;
	}
	
	public LinkedList<NodoConVecinos> posiblesSwaps(){
		LinkedList<NodoConVecinos> res = new LinkedList<NodoConVecinos>();
		for (NodoConVecinos n: this._vecinos){
			if (n.getColor() != this.getColor()){
				if ((n.get_coloresPosibles().contains(this.getColor())) && this.get_coloresPosibles().contains(n.getColor()))
					res.add(n);
			}
		}
		return res;
	}
	
	public HashSet<AristaEj4> conflictos() throws Exception{
		HashSet<AristaEj4> res = new HashSet<AristaEj4>();
		for (NodoConVecinos n: this._vecinos){
			if (n.getColor() == this.getColor())
				res.add(new AristaEj4(n,this));
		}
		return res;
	}
	

	 
}
