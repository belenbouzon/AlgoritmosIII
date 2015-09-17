import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

public class Solucion{
	private Nodo comienzo;
	private Nodo fin;
	private TreeMap<Nodo,Integer> calculados;
	private TreeSet<Nodo> ya_estuve;
	private int calcular_segundos_desde(Nodo posicion){
		Iterator<Entry<Nodo,Integer> > it = posicion.aristas.iterator();
		int valor_minimo = -1;
		ya_estuve.add(this.comienzo);
		if(posicion==this.fin){
			return 0;
		}
		while(it.hasNext()){
			Entry<Nodo,Integer> nodo = it.next();
			if(!ya_estuve.contains(nodo.getKey())){
				System.out.printf("desde:%d hasta:%d\n", posicion.piso,nodo.getKey().piso);
				if(this.calculados.containsKey(nodo.getKey())){
					int recursion = this.calculados.get(nodo.getKey());
					if(recursion!=-1){
						int nuevo_valor = nodo.getValue() + recursion;
						if(valor_minimo == -1 || (valor_minimo > nuevo_valor && nuevo_valor!=-1)){
							valor_minimo = nuevo_valor;
						}
					}
				}else{
					ya_estuve.add(nodo.getKey());
					int valor_recursion = calcular_segundos_desde(nodo.getKey());
					ya_estuve.remove(nodo.getKey());
					if(valor_recursion!=-1){
						int nuevo_valor = nodo.getValue() + valor_recursion;
						if(valor_minimo == -1 || (valor_minimo > nuevo_valor && nuevo_valor!=-1)){
							valor_minimo = nuevo_valor;
						}
					}
					this.calculados.put(nodo.getKey(),valor_recursion);
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