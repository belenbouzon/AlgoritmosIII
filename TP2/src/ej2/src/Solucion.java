import java.util.Iterator;

public class Solucion{
	private Nodo comienzo;
	private Nodo fin;
	private BoundedIntegerMap<Integer> calculados;
	//private TreeSet<Nodo> ya_estuve;
	private boolean ya_estuve[];
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
		ya_estuve[this.comienzo.identificacion] = true;
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
				//System.out.printf("desde:%d hasta:%d\n", posicion.identificacion,nodo.identificacion);
				teleport = false;
			}
			if(!ya_estuve[nodo.identificacion]){
				//System.out.printf("desde:%d hasta:%d\n", posicion.identificacion,nodo.identificacion);
				if(this.calculados.containsKey(nodo.identificacion)){
					int recursion = this.calculados.get(nodo.identificacion);
					if(recursion!=-1){
						int nuevo_valor = absoluto(posicion.posicion - nodo.posicion) + recursion;
						if(valor_minimo == -1 || (valor_minimo > nuevo_valor && nuevo_valor!=-1)){
							valor_minimo = nuevo_valor;
						}
					}
				}else{
					ya_estuve[nodo.identificacion] = true;
					int valor_recursion = calcular_segundos_desde(nodo);
					ya_estuve[nodo.identificacion] = false;
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
					this.calculados.put(nodo.identificacion,valor_recursion);
				}
			}
		}
		//System.out.printf("\n");
		return valor_minimo;
	}
	public int calcular_segundos(){
		return calcular_segundos_desde(this.comienzo);
	}
	public Solucion(Nodo com,Nodo f,int tam){
		this.comienzo = com;
		this.fin = f;
		this.calculados = new BoundedIntegerMap<Integer>(tam+1);
		this.ya_estuve = new boolean [tam];
		for(int i=0;i<tam;i++){
			this.ya_estuve[i] = false;
		}
	}
}