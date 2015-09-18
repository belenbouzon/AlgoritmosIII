import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

public class Solucion{
	private Nodo comienzo;
	private Nodo fin;
	private TreeMap<Nodo,Integer> calculados;
	private TreeSet<Nodo> ya_estuve;
	private static int absoluto(int numero){
		if(numero<0){
			numero = -numero;
		}
		return numero;
	}
	private int calcular_segundos_desde(Nodo posicion){
		Iterator<Nodo> it = posicion.aristas_teletranspoorte.iterator();
		Iterator<Nodo> it2 = posicion.aristas_caminado.iterator();
		int valor_minimo = -1;
		ya_estuve.add(this.comienzo);
		if(posicion==this.fin){
			return 0;
		}
		while(it.hasNext() && it2.hasNext()){
			Nodo nodo;
			if(it.hasNext()){
				nodo= it.next();
			}else{
				nodo= it2.next();
			}
			if(!ya_estuve.contains(nodo)){
				System.out.printf("desde:%d hasta:%d\n", posicion.identificacion,nodo.identificacion);
				if(this.calculados.containsKey(nodo)){
					int recursion = this.calculados.get(nodo);
					if(recursion!=-1){
						int nuevo_valor = absoluto(posicion.posicion - nodo.posicion) + recursion;
						if(valor_minimo == -1 || (valor_minimo > nuevo_valor && nuevo_valor!=-1)){
							valor_minimo = nuevo_valor;
						}
					}
				}else{
					ya_estuve.add(nodo);
					int valor_recursion = calcular_segundos_desde(nodo);
					ya_estuve.remove(nodo);
					if(valor_recursion!=-1){
						int nuevo_valor = absoluto(posicion.posicion - nodo.posicion) + valor_recursion;
						if(valor_minimo == -1 || (valor_minimo > nuevo_valor && nuevo_valor!=-1)){
							valor_minimo = nuevo_valor;
						}
					}
					this.calculados.put(nodo,valor_recursion);
				}
			}
		}
		System.out.printf("\n");
		return valor_minimo;
	}
	public int calcular_segundos(){
		return calcular_segundos_desde(this.comienzo);
	}
	public Solucion(Nodo com,Nodo f){
		this.comienzo = com;
		this.fin = f;
		this.calculados = new TreeMap<Nodo,Integer>(new ComparadorNodo());
		this.ya_estuve = new TreeSet<Nodo>(new ComparadorNodo());
	}
}