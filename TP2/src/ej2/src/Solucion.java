import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Solucion{
	private Nodo comienzo;
	private Nodo fin;
	//private BoundedIntegerMap<Integer> calculados;
	//private boolean ya_estuve[];
	private static int absoluto(int numero){
		if(numero<0){
			numero = -numero;
		}
		return numero;
	}
	private int calcular_segundos_desde_alternativo(){
		Queue<Nodo> pila = new LinkedList<Nodo>();
		Nodo actual = this.fin;
		this.fin.camino_minimo = 0;
		this.fin.ya_calculado = true;
		//int distancia = 0;
		do{
			//System.out.printf("portal mapeado: %d camino_minimo: %d\n",actual.identificacion,actual.camino_minimo);
			//actual.camino_minimo = distancia;
			//actual.ya_estuve = true;
			//actual.ya_calculado = true;
			if(actual.fantasma==0){
				Iterator<Nodo> it = actual.aristas_caminado.iterator();
				while(it.hasNext()){
					Nodo nodo = it.next();
					int camino = absoluto(actual.posicion - nodo.posicion);
					if(!nodo.ya_calculado){
						//pila.add(new Tupla<Integer, Nodo>(absoluto(actual.b.posicion - nodo.posicion),nodo));
						if(camino == 1){
							pila.add(nodo);
							nodo.camino_minimo = actual.camino_minimo+1;
							nodo.ya_calculado = true;
							if(nodo==this.comienzo){
								return nodo.camino_minimo;
							}
						}else if(camino!=0){
							//System.out.printf("camino: %d\n",camino);
							pila.add(Nodo.nodo_fantasma(camino, nodo,actual.camino_minimo+1));
						}else{
							System.out.printf("problema!!!!!!!!!!!!!!!!!!!!\n");
						}
					}
				}
				it = actual.aristas_teletranspoorte.iterator();
				while(it.hasNext()){
					Nodo nodo = it.next();
					if(!nodo.ya_calculado){
						//nodo.camino_minimo = actual.camino_minimo+1;
						pila.add(Nodo.nodo_fantasma(2, nodo,actual.camino_minimo+1));
					}
				}
			}else if(actual.fantasma==1){
				Iterator<Nodo> it = actual.aristas_teletranspoorte.iterator();
				Nodo nodo = it.next();
				if(!nodo.ya_calculado){
					pila.add(nodo);
					nodo.camino_minimo = actual.camino_minimo + 1;
					nodo.ya_calculado = true;
					if(nodo==this.comienzo){
						return nodo.camino_minimo;
					}
				}
			}else{
				actual.fantasma--;
				actual.camino_minimo++;
				pila.add(actual);
			}
			actual = pila.poll();
		}while(actual!=null);
		
		return this.comienzo.camino_minimo;
	}
	private int calcular_segundos_desde(Nodo posicion){ //O(P-#ya_estive(1))
		posicion.ya_estuve = true;
		Iterator<Nodo> it = posicion.aristas_teletranspoorte.iterator();
		Iterator<Nodo> it2 = posicion.aristas_caminado.iterator();
		int valor_minimo = -1;
		this.comienzo.ya_estuve = true; //O(1) acceso a un array
		if(posicion==this.fin){
			posicion.ya_estuve = false;
			return 0;
		}
		while(it.hasNext() || it2.hasNext()){
			Nodo nodo;
			boolean teleport;
			if(it2.hasNext()){
				nodo= it2.next();
				teleport = false;
			}else{
				nodo= it.next();
				teleport = true;
			}
			if(!nodo.ya_estuve){ //O(1) acceso a un array
				if(nodo.ya_calculado){
					int recursion = nodo.camino_minimo; //O(1)
					if(recursion!=-1){
						int nuevo_valor;
						if(teleport){
							nuevo_valor = 2 + recursion;
						}else{
							nuevo_valor = absoluto(posicion.posicion - nodo.posicion) + recursion;
						}
						if(valor_minimo == -1 || valor_minimo > nuevo_valor){
							valor_minimo = nuevo_valor;
						}
					}
				}else{
					int valor_recursion = calcular_segundos_desde(nodo); // recursion
					if(valor_recursion!=-1){
						int nuevo_valor;
						if(teleport){
							nuevo_valor = 2 + valor_recursion;
						}else{
							nuevo_valor = absoluto(posicion.posicion - nodo.posicion) + valor_recursion;
						}
						if(valor_minimo == -1 || valor_minimo > nuevo_valor){
							valor_minimo = nuevo_valor;
						}
					}
				}
			}
		}
		posicion.camino_minimo = valor_minimo;
		posicion.ya_calculado = true;
		posicion.ya_estuve = false;
		return valor_minimo;
	}
	public int calcular_segundos(){
		return this.calcular_segundos_desde_alternativo();
		//return this.calcular_segundos_desde(this.comienzo); //O(p)
	}
	public Solucion(Nodo com,Nodo f,int tam){
		this.comienzo = com;
		this.fin = f;
		//this.calculados = new BoundedIntegerMap<Integer>(tam+1);
		/*this.ya_estuve = new boolean [tam];
		for(int i=0;i<tam;i++){
			this.ya_estuve[i] = false;
		}*/
	}
}

//(1)