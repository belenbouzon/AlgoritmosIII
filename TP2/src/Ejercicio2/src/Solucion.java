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
	private void apilar_nodo(Nodo nodo,int relajar_arista,Queue<Nodo> pila,int camino_minimo){
		if(relajar_arista == 1){
			pila.add(nodo);
			nodo.camino_minimo = camino_minimo;
			nodo.ya_calculado = true;
		}else{
			pila.add(Nodo.nodo_fantasma(relajar_arista, nodo,camino_minimo));
		}
	}
	private void procesar_nodo_fantasma(Nodo actual,Queue<Nodo> pila){
		if(actual.fantasma==1){
			Iterator<Nodo> it = actual.aristas_teletranspoorte.iterator();
			Nodo nodo = it.next();
			if(!nodo.ya_calculado){
				pila.add(nodo);
				nodo.camino_minimo = actual.camino_minimo + 1;
				nodo.ya_calculado = true;
			}
		}else{
			actual.fantasma--;
			actual.camino_minimo++;
			pila.add(actual);
		}
	}
	public int calcular_segundos(){
		Queue<Nodo> pila = new LinkedList<Nodo>();
		Nodo actual = this.fin;
		this.fin.camino_minimo = 0;
		this.fin.ya_calculado = true;
		do{
			if(actual.fantasma==0){
				Iterator<Nodo> it = actual.aristas_caminado.iterator();
				while(it.hasNext()){
					Nodo nodo = it.next();
					int camino = absoluto(actual.posicion - nodo.posicion);
					if(!nodo.ya_calculado){
						apilar_nodo(nodo,camino,pila,actual.camino_minimo+1);
					}
				}
				
				it = actual.aristas_teletranspoorte.iterator();
				while(it.hasNext()){
					Nodo nodo = it.next();
					if(!nodo.ya_calculado){
						apilar_nodo(nodo,2,pila,actual.camino_minimo+1);
					}
				}
			}else {
				procesar_nodo_fantasma(actual,pila);
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

