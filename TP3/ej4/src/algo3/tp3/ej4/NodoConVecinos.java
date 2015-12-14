package algo3.tp3.ej4;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
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
	
	public int cantConflictos(){					// Total: O(cantidad de vecinos), que es O(n)
		Integer res = 0;							// O(1)
		for (NodoConVecinos n: this._vecinos){		// O(n) iteraciones
			if (n.getColor() == this.getColor())				// O(1)
				res += 1;							// O(1)
		}
		return res;									// O(1)
	}
	
	public LinkedList<NodoConVecinos> posiblesSwaps(){	// total: O(n*c)
		LinkedList<NodoConVecinos> res = new LinkedList<NodoConVecinos>();		// O(1)
		for (NodoConVecinos n: this._vecinos){									// O(n) iteraciones. 
			if (n.getColor() != this.getColor()){								// O(1)
				if ((n.get_coloresPosibles().contains(this.getColor())) && this.get_coloresPosibles().contains(n.getColor()))	// O(c)
					res.add(n);													// O(1)
			}
		}
		return res;																// O(1)
	}
	
	public HashSet<AristaEj4> conflictos() throws Exception{
		HashSet<AristaEj4> res = new HashSet<AristaEj4>();
		for (NodoConVecinos n: this._vecinos){
			if (n.getColor() == this.getColor())
				res.add(new AristaEj4(n,this));
		}
		return res;
	}
	
	private void swapColor(NodoConVecinos otro){
		int otroColor = otro.getColor();
		otro.setColor(this.getColor());
		this.setColor(otroColor);
	}
	
	public Swap buscarMejorSwap(){		// O(n*c + n*n)
		LinkedList<NodoConVecinos> candidatos = this.posiblesSwaps();					// O(n*c)
		NodoConVecinos mejorSwap = null;												// O(1)
		int mejorMejora = 0;															// O(1)																	
		int conflictosPropios = this.cantConflictos();									// O(n)
		for (NodoConVecinos v: candidatos){												// O(n) iteraciones. Total: O(n*n)
			int conflictosSinSwap = conflictosPropios + v.cantConflictos();		// O(n)
			this.swapColor(v);	// hago swap de colores (sin cambiar otras estructuras). O(1)
			int conflictosConSwap = this.cantConflictos() + v.cantConflictos();	// O(n)
			this.swapColor(v);	// revierto el swap de colores					O (1)
			
			if (conflictosConSwap < conflictosSinSwap){								// O(1)
				if ((conflictosSinSwap-conflictosConSwap) > mejorMejora){				// O(1)
					mejorSwap = v;													// O(1)
					mejorMejora = conflictosSinSwap-conflictosConSwap;				// O(1)
				}
			}
		}
		return new Swap(mejorSwap, mejorMejora);
	}
	
	public Hashtable<Integer, ArrayList<NodoConVecinos>> conflictosPorColor(){	// O(c+n)
		Hashtable<Integer, ArrayList<NodoConVecinos>> conflictosPorColor = new Hashtable<Integer, ArrayList<NodoConVecinos>>();  // O(1)
		
		for (int color: this.get_coloresPosibles()){						// O(c)
			ArrayList<NodoConVecinos> vacia = new ArrayList<NodoConVecinos>();
			conflictosPorColor.put(color, vacia);
		}
		
		for (NodoConVecinos v: this.vecinos()){							// O(n)
			if (conflictosPorColor.containsKey(v.getColor())){
				ArrayList<NodoConVecinos> nuevovalor = conflictosPorColor.get(v.getColor());
				nuevovalor.add(v);
				conflictosPorColor.put(v.getColor(), nuevovalor);
			}
		}
		
		return conflictosPorColor;
	}

	 
}
