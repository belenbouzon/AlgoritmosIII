import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Solucion{
	private Nodo comienzo;
	private Nodo fin;
	private static int absoluto(int numero){
		if(numero<0){
			numero = -numero;
		}
		return numero;
	}
	private void apilar_nodo(Nodo nodo,int relajar_arista,Queue<Nodo> pila,int camino_minimo){ //O(1)
		if(relajar_arista == 1){
			pila.add(nodo); //O(1)
			nodo.camino_minimo = camino_minimo;
			nodo.ya_calculado = true;
		}else{
			pila.add(Nodo.nodo_fantasma(relajar_arista, nodo,camino_minimo)); //O(1)
		}
	}
	private void procesar_nodo_fantasma(Nodo actual,Queue<Nodo> pila){ //O(1)
		if(actual.fantasma==1){
			Iterator<Nodo> it = actual.aristas_teletranspoorte.iterator(); //O(1)
			Nodo nodo = it.next(); //O(1)
			if(!nodo.ya_calculado){
				pila.add(nodo); //O(1)
				nodo.camino_minimo = actual.camino_minimo + 1;
				nodo.ya_calculado = true;
			}
		}else{
			actual.fantasma--;
			actual.camino_minimo++;
			pila.add(actual); //O(1)
		}
	}
	public int calcular_segundos(){
		Queue<Nodo> pila = new LinkedList<Nodo>(); //O(1)
		Nodo actual = this.fin;
		this.fin.camino_minimo = 0;
		this.fin.ya_calculado = true;
		do{ //O(P+NL) (**)
			if(actual.fantasma==0){
				Iterator<Nodo> it = actual.aristas_caminado.iterator();//O(1)
				while(it.hasNext()){ //O(1) aristas_caminando posee a lo sumo dos elementos
					Nodo nodo = it.next();
					int camino = absoluto(actual.posicion - nodo.posicion);
					if(!nodo.ya_calculado){
						apilar_nodo(nodo,camino,pila,actual.camino_minimo+1); //O(1)
					}
				}
				
				it = actual.aristas_teletranspoorte.iterator(); //O(1)
				while(it.hasNext()){ //O(ti) (*)
					Nodo nodo = it.next(); //O(1)
					if(!nodo.ya_calculado){
						apilar_nodo(nodo,2,pila,actual.camino_minimo+1); //O(1)
					}
				}
			}else {
				procesar_nodo_fantasma(actual,pila); //O(1)
			}

			actual = pila.poll();
		}while(actual!=null);
		return this.comienzo.camino_minimo;
	}

	public Solucion(Nodo com,Nodo f){
		this.comienzo = com;
		this.fin = f;
	}
}
//(*) ti: cantidad de portales que poseen uno de sus origenes en la posicion representada por el nodo. La sumatoria de todos los ti equivale a 2P, ya que cada portal equivale a una arista entre dos nodos.
//(**)el algoritmo itera a traves de todos los nodos del grafico. Se crean nodos fantasma por cada portal, y cada piso, a lo sumo se completa los L nodos. En cada iteracion se revisan los destinos de teletransporte desde un nodo.
