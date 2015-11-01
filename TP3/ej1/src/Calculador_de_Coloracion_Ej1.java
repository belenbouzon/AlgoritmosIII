import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;


public class Calculador_de_Coloracion_Ej1 {
	public ArrayList<Nodo> grafo_original;
	public ArrayList<Nodo_Grafo_Dirigido> grafo_dirigido;
	public int cantidad_colores;
	public int cantidad_nodos;
	public int cantidad_aristas;
	private Nodo_Grafo_Dirigido nodo_dirigido_de_color(Nodo nodo,int color,TreeSet<Nodo_Grafo_Dirigido> ya_creados){
		Nodo_Grafo_Dirigido buscado = new Nodo_Grafo_Dirigido(nodo.identidad,color);
		if(ya_creados.contains(buscado)){
			buscado = ya_creados.ceiling(buscado);
		}
		return buscado;
	}
	private void crear_nodo_dirigido(Nodo nodo,TreeSet<Nodo_Grafo_Dirigido> ya_creados){
		Iterator<Integer> it = nodo.colores.iterador_de_colores();
		int color = it.next(); 
		Nodo_Grafo_Dirigido nuevo_afirmacion = new Nodo_Grafo_Dirigido(nodo.identidad,color);
		Nodo_Grafo_Dirigido nuevo_negacion = new Nodo_Grafo_Dirigido(nodo.identidad,color);
		nuevo_afirmacion.hermano = nuevo_negacion;
		nuevo_negacion.hermano = nuevo_afirmacion;
		ya_creados.add(nuevo_afirmacion);
		this.grafo_dirigido.add(nuevo_afirmacion);
		if(!it.hasNext()){
			nuevo_negacion.agregar_adyacentes(nuevo_afirmacion);
		}else{
			color = it.next();
			Nodo_Grafo_Dirigido nuevo_afirmacion_B = new Nodo_Grafo_Dirigido(nodo.identidad,color);
			Nodo_Grafo_Dirigido nuevo_negacion_B = new Nodo_Grafo_Dirigido(nodo.identidad,color);
			nuevo_afirmacion.agregar_adyacentes(nuevo_negacion_B);
			nuevo_afirmacion_B.agregar_adyacentes(nuevo_negacion);
			nuevo_negacion.agregar_adyacentes(nuevo_afirmacion_B);
			nuevo_negacion_B.agregar_adyacentes(nuevo_afirmacion);
			ya_creados.add(nuevo_afirmacion_B);
			this.grafo_dirigido.add(nuevo_afirmacion_B);
		}
	}
	public Calculador_de_Coloracion_Ej1(int cant_colores, int cant_nodos, int cant_aristas, ArrayList<Nodo> nodos_de_grafo){
		this.cantidad_colores = cant_colores;
		this.cantidad_nodos = cant_nodos;
		this.cantidad_aristas = cant_aristas;
		this.grafo_original = nodos_de_grafo;
	}
	public void relacionar_nodos(Nodo nodo,TreeSet<Nodo_Grafo_Dirigido> ya_creados){
		Iterator<Nodo> it = nodo.adyacentes.iterator();
		while(it.hasNext()){
			Nodo vecino = it.next();
			Indice_colores colores_en_comun = nodo.colores.colores_en_comun(vecino.colores);
			Iterator<Integer> it_colores = colores_en_comun.iterador_de_colores();
			while(it_colores.hasNext()){
				int color = it_colores.next();
				Nodo_Grafo_Dirigido nodo_color_1 = this.nodo_dirigido_de_color(nodo, color, ya_creados);
				Nodo_Grafo_Dirigido nodo_color_2 = this.nodo_dirigido_de_color(vecino, color, ya_creados);
				nodo_color_1.agregar_adyacentes(nodo_color_2.hermano);
				nodo_color_2.hermano.agregar_adyacentes(nodo_color_1);
				
				nodo_color_2.agregar_adyacentes(nodo_color_1.hermano);
				nodo_color_1.hermano.agregar_adyacentes(nodo_color_2);
			}
		}
	}
	
	public boolean generar_grafo_dirigido(){
		TreeSet<Nodo_Grafo_Dirigido> ya_creados = new TreeSet<Nodo_Grafo_Dirigido>(); 
		Iterator<Nodo> it = this.grafo_original.iterator();
		while(it.hasNext()){
			crear_nodo_dirigido(it.next(), ya_creados);
		}
		it = this.grafo_original.iterator();
		while(it.hasNext()){
			relacionar_nodos(it.next(),ya_creados);
		}
		return true;
	}
	
	public void auxiliar_DFS_original(Nodo_Grafo_Dirigido nodo,Stack<Nodo_Grafo_Dirigido> pila,TreeSet<Nodo_Grafo_Dirigido> esta_en_pila){
		if(!esta_en_pila.contains(nodo)){
			Iterator<Nodo_Grafo_Dirigido> it = nodo.adyacentes.iterator();
			while(it.hasNext()){
				Nodo_Grafo_Dirigido nuevo = it.next();
				//if(!esta_en_pila.contains(nuevo)){
				auxiliar_DFS_original(nuevo,pila,esta_en_pila);
				//}
			}
			pila.add(nodo);
			esta_en_pila.add(nodo);
		}
	}
	
	public void DFS_original(){
		Stack<Nodo_Grafo_Dirigido> pila = new Stack<Nodo_Grafo_Dirigido>();
		TreeSet<Nodo_Grafo_Dirigido> esta_en_pila = new TreeSet<Nodo_Grafo_Dirigido>();
		Iterator<Nodo_Grafo_Dirigido> it = this.grafo_dirigido.iterator();
		while(it.hasNext()){
			auxiliar_DFS_original(it.next(),pila,esta_en_pila);
		}
	}
	public void DFS_inverso(Stack<Nodo_Grafo_Dirigido> pila){
		while(!pila.empty()){
			Nodo_Grafo_Dirigido nodo = pila.pop();
		}
		
	}
	
	
}
