import java.util.ArrayList;
import java.util.HashSet;
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
		
		return 0;
	}
	
	public int vecindad2(Arista target){
		return 0;
	}


	
	/*
	public static void AgregarAristas(String nombreDeArchivo, int cantidadDeAristas, int cantNodoConVecinoss) throws IOException 
	{
		File grafoModificable = new File("C:\\Users\\Bel\\Documents\\GitHub\\AlgoritmosIII\\TP3\\ej3\\Ejercicio3\\bin\\"  + nombreDeArchivo);
		FileWriter fw1 = new FileWriter(grafoModificable, true);
		
		HashSet<Arista> aristas = Ej3Utils.GenerarAristas(cantidadDeAristas, cantNodoConVecinoss);
		Iterator<Arista> iteradorArista = aristas.iterator();
		while(iteradorArista.hasNext())
		{
			Arista arista = iteradorArista.next();
			iteradorArista.remove();
			fw1.append(String.valueOf(arista.desde) + " " + String.valueOf(arista.hasta) + "\n");
		}
		fw1.close();
	}
	*/
}
