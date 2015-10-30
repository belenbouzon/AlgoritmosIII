import java.util.ArrayList;


public class Calculador_de_Coloracion_Ej1 {
	public ArrayList<Nodo> grafo_original;
	public ArrayList<Nodo_Grafo_Dirigido> grafo_dirigido;
	public int cantidad_colores;
	public int cantidad_nodos;
	public int cantidad_aristas;
	
	private Nodo_Grafo_Dirigido buscar_nodo_dirigido(Nodo nodo,TreeSet<Nodo_Grafo_Dirigido> ya_creados){
		Iterator<Integer> it = nodo.colores.iterador_de_colores();
		Nodo_Grafo_Dirigido res = new Nodo_Grafo_Dirigido(nodo.identidad);
		res.color = it.next();
		res.hermano = null;
		if(ya_creados.contains(res)){
			res = ya_creados.ceiling(res);
		}
		if(it.hasNext()){
			res.hermano = new Nodo_Grafo_Dirigido(nodo.identidad);
			res.hermano.color = it.next();
			if(ya_creados.contains(res.hermano)){
				res.hermano = ya_creados.ceiling(res.hermano);
			}
			res.hermano.hermano = res;
		}

		return res;
	}

	public Calculador_de_Coloracion_Ej1(int cant_colores, int cant_nodos, int cant_aristas, ArrayList<Nodo> nodos_de_grafo){
		this.cantidad_colores = cant_colores;
		this.cantidad_nodos = cant_nodos;
		this.cantidad_aristas = cant_aristas;
		this.grafo_original = nodos_de_grafo;
	}
	public boolean generar_grafo_dirigido(){
		this.grafo_dirigido = new ArrayList(this.cant_nodos*2);
		TreeSet<Nodo_Grafo_Dirigido> ya_creados = new TreeSet<Nodo_Grafo_Dirigido>();
		for(int i=0;i<this.cant_nodos;i++){
			Nodo actual = grafo_original.get(i);
			Iterator<Nodo> it = actual.adyacentes.iterator();
			while(it.hasNext()){
				Nodo vecino = it.next();
				Indice_colores col = this.colores.colores_en_comun(vecino.colores);
				if(col.cantidad_colores==1){
					int color = col.iterador_de_colores.next();
					Nodo_Grafo_Dirigido actual_dirigido = buscar_nodo_dirigido(actual);
					Nodo_Grafo_Dirigido actual_dirigido_otro;
					if(actual_dirigido.color != color){
						actual_dirigido_otro = actual_dirigido;
						actual_dirigido = actual_dirigido.hermano;
					}else{
						actual_dirigido_otro = actual_dirigido.hermano;
					}
					Nodo_Grafo_Dirigido vecino_dirigido = buscar_nodo_dirigido(vecino);
					Nodo_Grafo_Dirigido vecino_dirigido_otro;
					if(vecino_dirigido.color != color){
						vecino_dirigido_otro = vecino_dirigido;
						vecino_dirigido = vecino_dirigido.hermano;
					}else{
						vecino_dirigido_otro = vecino_dirigido.hermano;
					}
					if(actual_dirigido_otro==null && vecino_dirigido_otro==null){
						return false;
					}else if(actual_dirigido_otro==null){
						actual_dirigido.adyacentes.add(vecino_dirigido_otro);

						if(actual_dirigido.valor_fijado && !actual_dirigido.valor_de_verdad){
							return false;
						}

						if(vecino_dirigido_otro.valor_fijado && !vecino_dirigido_otro.valor_de_verdad){
							return false;
						}

						if(vecino_dirigido.valor_fijado && vecino_dirigido.valor_de_verdad){
							return false;
						}

						actual_dirigido.valor_fijado = true;
						actual_dirigido.valor_de_verdad = true;

						vecino_dirigido_otro.valor_fijado = true;
						vecino_dirigido_otro.valor_de_verdad = true;

						vecino_dirigido.valor_fijado = true;
						vecino_dirigido.valor_de_verdad = false;
					}else if()
				}
			}
		}

	}
	
}
