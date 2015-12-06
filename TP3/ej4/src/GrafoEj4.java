import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

public class GrafoEj4 {
	private ArrayList<NodoConVecinos> _nodos;
	private int _cantidadDeColores;
	private HashSet<Arista> _conflictos;
	
	public int getCantConflictos(){
		return this._conflictos.size();
	}
	
	public NodoConVecinos getNodo(int id){
		// obtiene un nodo del grafo a partir de su ID
		return this._nodos.get(id);
	}
	
	public ArrayList<NodoConVecinos> getNodos() {
		return _nodos;
	}



	public int getCantidadDeColores() 
	{
		return _cantidadDeColores;
	}
	public void setCantidadDeColores(int cantidadDeColores) 
	{
		this._cantidadDeColores = cantidadDeColores;
	}
	
	public GrafoEj4(Grafo otro){
		// Convierte un grafo del ej3 en un grafo del ej4
		this._cantidadDeColores = otro.getCantidadDeColores();
		this._nodos = new ArrayList<NodoConVecinos>(this._cantidadDeColores);
		this._conflictos = new HashSet<Arista>();
		
		
		// agrego todos los nodos (sin aristas)
		for (Iterator<Nodo> n = otro.getNodos().iterator() ; n.hasNext(); ){
			// convierto el Nodo a NodoConVecinos
			Nodo original = n.next();
			NodoConVecinos convertido = new NodoConVecinos(original);
			
			// Agrego el Nodo al grafo
			this._nodos.add(convertido);
		}
		
		
		// Armo la lista de adyacencia del nodo. Tengo que ciclar de nuevo
		// porque primero necesito que esten todos los nodos creados.
		for (Iterator<Nodo> n = otro.getNodos().iterator() ; n.hasNext(); ){
			// convierto el Nodo a NodoConVecinos
			Nodo original = n.next();
			NodoConVecinos convertido = this.getNodo(original.getId());
			
			for (Iterator <Nodo> v = otro.getVecinosDe(original).iterator(); v.hasNext(); ){
				Nodo vecinoOriginal = v.next();
				NodoConVecinos vecinoConvertido = this.getNodo(vecinoOriginal.getId());
				convertido.agregarVecinos(vecinoConvertido);
				// Chequeo si hay conflicto.
				if (convertido.getColor() == vecinoConvertido.getColor()){
					// Hay conflicto!
					Arista c = new Arista(convertido,vecinoConvertido);
					this._conflictos.add(c);
					// Vamos a estar agregando cada conflicto dos veces, pero como el equals de Arista chequea la igualdad, no se agrega dos veces al Set.
				}
			}
		}
		
	}
	

	
	
	public int vecindad1(Arista target){
		/*
		 * Intentamos alterar el grafo para reducir el conflicto que se halla en la Arista target.
		 * Lo hacemos tratando de cambiar el color de alguno de los dos nodos sin introducir más conflictos.
		 * El valor de retorno es la cantidad de conflictos en los que redujimos el grafo (siempre será mayor
		 * o igual a cero).
		 */
		/*
		 * 1. obtener conjunto de colores posibles para Nodo n1
		 * 2. crear HashTable<int, int> conflictosPorColor (inicializar en 0) 
		 * 3. para cada v en vecinos de n1
		 *     3.1. conflictosPorColor[v.getColor()] += 1
		 * hacer lo mismo para Nodo n2
		 */
		Hashtable<Integer, ArrayList<NodoConVecinos>> conflictosPorColorN1 = new Hashtable<Integer, ArrayList<NodoConVecinos>>();
		Hashtable<Integer, ArrayList<NodoConVecinos>> conflictosPorColorN2 = new Hashtable<Integer, ArrayList<NodoConVecinos>>();
		for (int color: target.getN1().get_coloresPosibles()){
			ArrayList<NodoConVecinos> vacia = new ArrayList<NodoConVecinos>();
			conflictosPorColorN1.put(color, vacia);
		}
		for (int color: target.getN2().get_coloresPosibles()){
			ArrayList<NodoConVecinos> vacia = new ArrayList<NodoConVecinos>();
			conflictosPorColorN2.put(color, vacia);
		}
		
		for (NodoConVecinos v: target.getN1().vecinos()){
			if (conflictosPorColorN1.containsKey(v.getColor())){
				ArrayList<NodoConVecinos> nuevovalor = conflictosPorColorN1.get(v.getColor());
				nuevovalor.add(v);
				conflictosPorColorN1.put(v.getColor(), nuevovalor);
			}
		}
		for (NodoConVecinos v: target.getN2().vecinos()){
			if (conflictosPorColorN2.containsKey(v.getColor())){
				ArrayList<NodoConVecinos> nuevovalor = conflictosPorColorN2.get(v.getColor());
				nuevovalor.add(v);
				conflictosPorColorN2.put(v.getColor(), nuevovalor);
			}
		}
		
		Integer candidatoN1 = minimo(conflictosPorColorN1);
		Integer candidatoN2 = minimo(conflictosPorColorN2);
		
		if (conflictosPorColorN1.get(candidatoN1).size() <= conflictosPorColorN2.get(candidatoN2).size()){
			return this.cambiarColor(candidatoN1, conflictosPorColorN1, target.getN1());
		} else{
			return this.cambiarColor(candidatoN2, conflictosPorColorN2, target.getN2());
		}
	}
	
	public int vecindad2(Arista target){
		return 0;
	}
	
	private Integer minimo(Hashtable<Integer, ArrayList<NodoConVecinos>> in){
		Integer res = null;
		for (int k: in.keySet()){
			if (in.get(k).size() < in.get(res).size())
				res = k;
		}
		return res;
	}



	private Integer cambiarColor(Integer nuevoColor, Hashtable<Integer, ArrayList<NodoConVecinos>> conflictosPorColor, NodoConVecinos target){
		Integer diferencia = 0;
		if (nuevoColor != target.getColor()){
			
			// sacamos de _conflictos los conflictos removidos
			for (NodoConVecinos c: conflictosPorColor.get(target.getColor())){
				Arista remover = new Arista(target, c);
				this._conflictos.remove(remover);
			}
			// agregamos a _conflictos los conflictos nuevos
			for (NodoConVecinos c: conflictosPorColor.get(nuevoColor)){
				Arista agregar = new Arista(target, c);
				this._conflictos.add(agregar);
			}
			
			diferencia = conflictosPorColor.get(target.getColor()).size() - conflictosPorColor.get(nuevoColor).size();
					
			// cambiamos el color de target
			target.setColor(nuevoColor);
			
		}
		return diferencia;
	}
	
	public void ResolverConVecindad1(){
		
	}
	
	
}
