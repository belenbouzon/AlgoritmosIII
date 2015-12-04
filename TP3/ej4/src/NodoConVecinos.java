

import java.util.LinkedHashSet;
import java.util.Set;

public class NodoConVecinos extends Nodo {
	private Set<NodoConVecinos> _vecinos;
	/* no vamos a usar en este ejercicio las siguientes estructuras de datos:
	 * seguimientoColoresTotales
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
	public NodoConVecinos(int j, int cantidadDeColoresDelGrafo, int[] colores){
		this.setColores(colores, cantidadDeColoresDelGrafo);
		this.setId(j);
		this.setColor(-1);
		this.setVisitado(false);
		this._vecinos = new LinkedHashSet<NodoConVecinos>();
	}
	public NodoConVecinos(Nodo otro){
		// creamos el NodoConVecinos a partir de uno del ej3.
		// OJO: no le marcamos los vecinos. Es lo hacemos con agregarVecinos.
		this.setId(otro.getId());
		this.setVisitado(false);  // no lo usamos
		this.setColor(otro.getColor());
		
	}
	

	 
}
