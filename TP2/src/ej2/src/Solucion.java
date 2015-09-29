import java.util.Iterator;

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
	private int calcular_segundos_desde(Nodo posicion){ //O(P-#ya_estive(1))
		Iterator<Nodo> it = posicion.aristas_teletranspoorte.iterator();
		Iterator<Nodo> it2 = posicion.aristas_caminado.iterator();
		int valor_minimo = -1;
		this.comienzo.ya_estuve = true; //O(1) acceso a un array
		if(posicion==this.fin){
			return 0;
		}
		while(it.hasNext() || it2.hasNext()){
			Nodo nodo;
			boolean teleport;
			if(it.hasNext()){
				nodo= it.next();
				teleport = true;
			}else{
				nodo= it2.next();
				teleport = false;
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
						if(valor_minimo == -1 || (valor_minimo > nuevo_valor && nuevo_valor!=-1)){
							valor_minimo = nuevo_valor;
						}
					}
				}else{
					nodo.ya_estuve = true;
					int valor_recursion = calcular_segundos_desde(nodo); // recursion
					nodo.ya_estuve = false;
					if(valor_recursion!=-1){
						int nuevo_valor;
						if(teleport){
							nuevo_valor = 2 + valor_recursion;
						}else{
							nuevo_valor = absoluto(posicion.posicion - nodo.posicion) + valor_recursion;
						}
						if(valor_minimo == -1 || (valor_minimo > nuevo_valor && nuevo_valor!=-1)){
							valor_minimo = nuevo_valor;
						}
					}
					nodo.camino_minimo = valor_recursion;//O(1)
					nodo.ya_calculado = true;
				}
			}
		}
		return valor_minimo;
	}
	public int calcular_segundos(){
		return this.calcular_segundos_desde(this.comienzo); //O(p)
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