package algo3.tp3.ej4;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import algo3.tp3.ej3.Grafo;
import algo3.tp3.ej3.Lector;
import algo3.tp3.ej3.Nodo;


public class GrafoEj4 {
	private ArrayList<NodoConVecinos> _nodos;
	private int _cantidadDeColores;
	private HashSet<AristaEj4> _conflictos;
	
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


	public int getCantidadDeColores() {
		return _cantidadDeColores;
	}
	public void setCantidadDeColores(int cantidadDeColores) {
		this._cantidadDeColores = cantidadDeColores;
	}
	
	public GrafoEj4(Grafo otro) throws Exception{
		// Convierte un grafo del ej3 en un grafo del ej4
		this._cantidadDeColores = otro.getCantidadDeColores();
		this._nodos = new ArrayList<NodoConVecinos>(this._cantidadDeColores);
		this._conflictos = new HashSet<AristaEj4>();
		
		
		// agrego todos los nodos (sin aristas)
		for (Iterator<Nodo> n = otro.getNodos().iterator() ; n.hasNext(); ){
			// convierto el Nodo a NodoConVecinos
			Nodo original = n.next();
			assert(original.getColor() > 0);		// No podemos admitir un nodo que no esté coloreado.
			assert(original.getColor() <= this._cantidadDeColores);
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
					AristaEj4 c = new AristaEj4(convertido,vecinoConvertido);
					this._conflictos.add(c);
					// Vamos a estar agregando cada conflicto dos veces, pero como el equals de Arista chequea la igualdad, no se agrega dos veces al Set.
				}
			}
		}
		
	}
	

	
	
	public int vecindad1(AristaEj4 target) throws Exception{
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
		
		Hashtable<Integer, ArrayList<NodoConVecinos>> conflictosPorColorN1 = new Hashtable<Integer, ArrayList<NodoConVecinos>>();  // O(1)
		Hashtable<Integer, ArrayList<NodoConVecinos>> conflictosPorColorN2 = new Hashtable<Integer, ArrayList<NodoConVecinos>>();	// O(1)
		for (int color: target.getN1().get_coloresPosibles()){						// O(c)
			ArrayList<NodoConVecinos> vacia = new ArrayList<NodoConVecinos>();
			conflictosPorColorN1.put(color, vacia);
		}
		for (int color: target.getN2().get_coloresPosibles()){						// O(c)
			ArrayList<NodoConVecinos> vacia = new ArrayList<NodoConVecinos>();
			conflictosPorColorN2.put(color, vacia);
		}
		
		for (NodoConVecinos v: target.getN1().vecinos()){							// O(n)
			if (conflictosPorColorN1.containsKey(v.getColor())){
				ArrayList<NodoConVecinos> nuevovalor = conflictosPorColorN1.get(v.getColor());
				nuevovalor.add(v);
				conflictosPorColorN1.put(v.getColor(), nuevovalor);
			}
		}
		for (NodoConVecinos v: target.getN2().vecinos()){							// O(n)
			if (conflictosPorColorN2.containsKey(v.getColor())){
				ArrayList<NodoConVecinos> nuevovalor = conflictosPorColorN2.get(v.getColor());
				nuevovalor.add(v);
				conflictosPorColorN2.put(v.getColor(), nuevovalor);
			}
		}
		
		Integer candidatoN1 = minimo(conflictosPorColorN1);							// O(c)
		Integer candidatoN2 = minimo(conflictosPorColorN2);							// O(c)
		
		if (conflictosPorColorN1.get(candidatoN1).size() <= conflictosPorColorN2.get(candidatoN2).size()){	// O(1)
			return this.cambiarColor(candidatoN1, conflictosPorColorN1, target.getN1());
		} else{
			return this.cambiarColor(candidatoN2, conflictosPorColorN2, target.getN2());
		}
	}
	
	public int vecindad2(AristaEj4 target) throws Exception{
		/* En esta vecindad lo que hacemos es, para cada uno de los nodos que está en 
		 * la arista conflictiva, buscamos cuál vecino de ellos tiene más colores posibles
		 * compartidos con el nodo. Y, para cada uno de ellos, vemos si un swap de colores
		 * 
		 * Para nodo 1
		 * 1. obtener conjunto de vecinos que pueden ser pintados con el color de nodo1 y 
		 * que no están ya pintados con ese color
		 * 2. para cada uno de ellos, computar en cuánto cambiaría la cantidad total de conflictos
		 * el hacer el swap
		 * 
		 * Hacer lo mismo para nodo 2
		 * 
		 * Elegir cuál de los dos swaps hacer
		 */
		
	
		// en mejorMejora guardamos la cantidad de conflictos TOTALES que resolvemos haciendo el swap
		// en mejorSwap guardamos el nodo con el que habría que hacer ese swap
		
		Swap mejorSwapN1 = target.getN1().buscarMejorSwap();
		Swap mejorSwapN2 = target.getN2().buscarMejorSwap();


		
		if (mejorSwapN1.getMejora() > mejorSwapN2.getMejora()){												// O(1)
			if (mejorSwapN1.getN() != null)		// O(1)
				return swapColores(target.getN1(), mejorSwapN1.getN());						// O(n)
		} else{
			if (mejorSwapN2.getN() != null)		// O(1)
				return swapColores(target.getN2(), mejorSwapN2.getN());						// O(n)
		}
		
		return 0; // No encontré un buen swap
	}
	
	private Integer minimo(Hashtable<Integer, ArrayList<NodoConVecinos>> in){
		Integer res = in.keys().nextElement();
		for (int k: in.keySet()){
			if (in.get(k).size() < in.get(res).size())
				res = k;
		}
		return res;
	}
	
	private Integer swapColores(NodoConVecinos n1, NodoConVecinos n2) throws Exception{		// O(n)
		// Intercambiar los colores entre n1 y n2, devolviendo la cantidad de conflictos que se resolvieron
		
		// TODO: se puede optimizar esto..
		
		assert(n1.getColor() != n2.getColor());
		
		Integer res = 0; // acá tendremos la cantidad total de conflictos resueltos
		
		// saco de _conflictos los conflictos resueltos
		HashSet<AristaEj4> sacar = n1.conflictos();
		res += sacar.size();
		this._conflictos.removeAll(sacar);
		sacar = n2.conflictos();
		res += sacar.size();
		this._conflictos.removeAll(sacar);
		
		// cambio los colores
		
		Integer swap = n1.getColor();
		n1.setColor(n2.getColor());
		n2.setColor(swap);
		
		// agrego a _conflictos los conflictos nuevos
		
		HashSet<AristaEj4> poner = n1.conflictos();
		res -= poner.size();
		this._conflictos.addAll(poner);
		poner = n2.conflictos();
		res -= poner.size();
		this._conflictos.addAll(poner);
		
		return res;
	}


	private Integer cambiarColor(Integer nuevoColor, Hashtable<Integer, ArrayList<NodoConVecinos>> conflictosPorColor, NodoConVecinos target) throws Exception{
		/*
		 * Cambio el color del nodo target por nuevoColor. En conflictosPorColor recibo una tabla que ya tiene calculados los vecinos con los que target
		 * va a tener conflicto para cada color posible. (Nos interesan los del color actual y los de nuevoColor).
		 */
		Integer diferencia = 0;
		if (nuevoColor != target.getColor()){
			
			// sacamos de _conflictos los conflictos removidos
			for (NodoConVecinos c: conflictosPorColor.get(target.getColor())){
				AristaEj4 remover = new AristaEj4(target, c);
				this._conflictos.remove(remover);
			}
			// agregamos a _conflictos los conflictos nuevos
			for (NodoConVecinos c: conflictosPorColor.get(nuevoColor)){
				AristaEj4 agregar = new AristaEj4(target, c);
				this._conflictos.add(agregar);
			}
			
			diferencia = conflictosPorColor.get(target.getColor()).size() - conflictosPorColor.get(nuevoColor).size();
					
			// cambiamos el color de target
			target.setColor(nuevoColor);
			
		}
		return diferencia;
	}
	
	public void ResolverConVecindad1() throws Exception{
		/*
		 * Aplicamos la vecindad 1 a todos los conflictos del grafo.
		 */
		LinkedList<AristaEj4> cola = new LinkedList<AristaEj4>();  	// O(1)
		for (AristaEj4 c: this._conflictos)							// O(m)
			cola.add(c);
		
		for (AristaEj4 c: cola){			// El for entero termina costando O(m * (n+c))
			this.vecindad1(c);				// O(n+c)
		}
	}
	
	public void ResolverConVecindad2() throws Exception{
		LinkedList<AristaEj4> cola = new LinkedList<AristaEj4>();
		for (AristaEj4 c: this._conflictos){
			cola.add(c);
		}
		
		for (AristaEj4 c: cola){
			this.vecindad2(c);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		/*
		 * Resuelvo el problema que me pasen como parámetro (leo de archivo)
		 */
		
		
		if (args.length < 1){
			System.out.println("Debe pasar como parámetro el nombre de archivo del input.\n");
			return;
		}
			
		Lector lector = new Lector(args[0]);
		Grafo grafoResultante = lector.MakeGraph(-1);
		grafoResultante.MakeRainbow();
				
		GrafoEj4 convertido = new GrafoEj4(grafoResultante);
		convertido.ResolverConVecindad1();
		
		String output = new String();
		
		for (NodoConVecinos n: convertido._nodos)
			output += Integer.toString(n.getColor()) + " ";
		output += "\n";
		
		System.out.println(output);
		
				
	}
	
}
