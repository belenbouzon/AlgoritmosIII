import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;


public class Calculador_de_Coloracion_Ej1 {
	public ArrayList<Nodo> grafo_original;
	public ArrayList<Nodo_Grafo_Dirigido> grafo_dirigido;
	public ArrayList<Nodo_Grafo_Dirigido_2> grafo_dirigido_compacto;
	private int cantidad_colores;
	private int cantidad_nodos;
	private int cantidad_aristas;
	public LinkedList<TreeSet<Nodo_Grafo_Dirigido>> componentes_fuertemente_conexas;
	private int [] salida_ints;
	private Nodo_Grafo_Dirigido nodo_dirigido_de_color(Nodo nodo,int color,TreeSet<Nodo_Grafo_Dirigido> ya_creados){
		Nodo_Grafo_Dirigido buscado = new Nodo_Grafo_Dirigido(nodo.identidad,color);
		if(ya_creados.contains(buscado)){
			buscado = ya_creados.ceiling(buscado);
		}else{
			buscado.hermano = new Nodo_Grafo_Dirigido(nodo.identidad,color);
			buscado.hermano.formula_afirmativa = false;
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
		nuevo_negacion.formula_afirmativa = false;
		nuevo_afirmacion.hermano = nuevo_negacion;
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
			nuevo_negacion_B.formula_afirmativa = false;
			nuevo_afirmacion_B.hermano = nuevo_negacion_B;
			nuevo_negacion_B.hermano = nuevo_afirmacion_B;
			ya_creados.add(nuevo_afirmacion_B);
			this.grafo_dirigido.add(nuevo_afirmacion_B);
		}
	}
	public Calculador_de_Coloracion_Ej1(int cant_colores, int cant_nodos, int cant_aristas, ArrayList<Nodo> nodos_de_grafo){
		this.cantidad_colores = cant_colores;
		this.cantidad_nodos = cant_nodos;
		this.cantidad_aristas = cant_aristas;
		this.grafo_original = nodos_de_grafo;
		this.salida_ints = new int [cantidad_nodos];
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
		this.grafo_dirigido = new ArrayList<Nodo_Grafo_Dirigido>(this.cantidad_nodos);
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
	
	private void auxiliar_DFS_original(Nodo_Grafo_Dirigido nodo,Stack<Nodo_Grafo_Dirigido> pila){
		if(nodo.id_componente_conexa==-2){
			nodo.id_componente_conexa = -1;
			Iterator<Nodo_Grafo_Dirigido> it = nodo.adyacentes.iterator();
			while(it.hasNext()){
				Nodo_Grafo_Dirigido nuevo = it.next();
				auxiliar_DFS_original(nuevo,pila);
			}
			pila.add(nodo);
		}
	}
	
	private void DFS_original(Stack<Nodo_Grafo_Dirigido> pila){
		Iterator<Nodo_Grafo_Dirigido> it = this.grafo_dirigido.iterator();
		while(it.hasNext()){
			auxiliar_DFS_original(it.next(),pila);
		}
	}
	private void DFS_inverso_auxiliar(Nodo_Grafo_Dirigido nodo,TreeSet<Nodo_Grafo_Dirigido> conjunto,int id_componente_conexa_actual){
		if(nodo.id_componente_conexa==-1){ 
			Iterator<Nodo_Grafo_Dirigido> it = nodo.adyacentes_inverso.iterator();
			conjunto.add(nodo);
			nodo.id_componente_conexa = id_componente_conexa_actual;
			while(it.hasNext()){
				Nodo_Grafo_Dirigido vecino = it.next();
				if(vecino.id_componente_conexa==-2){
					System.out.printf("error!\n");
				}
				DFS_inverso_auxiliar(vecino,conjunto,id_componente_conexa_actual);
			}
		}
	}
	private void DFS_inverso(Stack<Nodo_Grafo_Dirigido> pila){
		this.componentes_fuertemente_conexas = new LinkedList<TreeSet<Nodo_Grafo_Dirigido>> ();
		int id_componente_conexa_actual = 0;
		while(!pila.empty()){
			Nodo_Grafo_Dirigido nodo = pila.pop();
			if(nodo.id_componente_conexa==-2){
				System.out.printf("error!\n");
			}
			if(nodo.id_componente_conexa==-1){
				TreeSet<Nodo_Grafo_Dirigido> nuevo = new TreeSet<Nodo_Grafo_Dirigido>(); 
				Iterator<Nodo_Grafo_Dirigido> it = nodo.adyacentes_inverso.iterator();
				nuevo.add(nodo);
				nodo.id_componente_conexa = id_componente_conexa_actual;
				while(it.hasNext()){
					Nodo_Grafo_Dirigido vecino = it.next();
					if(vecino.id_componente_conexa==-2){
						System.out.printf("error!\n");
					}
					if(vecino.id_componente_conexa==-1){
						//nuevo.add(vecino);
						//vecino.id_componente_conexa = id_componente_conexa_actual;
						this.DFS_inverso_auxiliar(vecino, nuevo, id_componente_conexa_actual);
					}
				}
				this.componentes_fuertemente_conexas.add(nuevo);
				id_componente_conexa_actual++;
			}
		}
	}
	public void DFS(){
		Stack<Nodo_Grafo_Dirigido> pila = new Stack<Nodo_Grafo_Dirigido>();
		this.DFS_original(pila);
		this.DFS_inverso(pila);
	}
	private boolean generar_grafo_compacto(){
		this.grafo_dirigido_compacto = new ArrayList<Nodo_Grafo_Dirigido_2>(this.componentes_fuertemente_conexas.size());
		for(int i = 0;i<this.componentes_fuertemente_conexas.size();i++){
			this.grafo_dirigido_compacto.add(new Nodo_Grafo_Dirigido_2(i,i,null));
		}
		Iterator<TreeSet<Nodo_Grafo_Dirigido>> it = this.componentes_fuertemente_conexas.iterator();
		int i = 0;
		while(it.hasNext()){
			TreeSet<Nodo_Grafo_Dirigido> conj = it.next();
			Nodo_Grafo_Dirigido_2 nodo_compacto = this.grafo_dirigido_compacto.get(i);
			nodo_compacto.componente_conexa = conj;
			
			Iterator<Nodo_Grafo_Dirigido> it_nivel_2 = conj.iterator();
			while(it_nivel_2.hasNext()){
				Nodo_Grafo_Dirigido nodo = it_nivel_2.next();
				if(nodo.hermano.id_componente_conexa==i){
					if(!nodo_compacto.fijar_valores_de_verdad(false)){
						return false;
					}
					if(!marcar(nodo_compacto)){
						return false;
					}
				}else{
					nodo_compacto.agregar_hermano(this.grafo_dirigido_compacto.get(nodo.hermano.id_componente_conexa));
				}
				Iterator<Nodo_Grafo_Dirigido> it_nivel_3 = nodo.adyacentes.iterator();
				while(it_nivel_3.hasNext()){
					Nodo_Grafo_Dirigido vecino = it_nivel_3.next();
					if(vecino.id_componente_conexa!=i){
						nodo_compacto.agregar_adyacentes(this.grafo_dirigido_compacto.get(vecino.id_componente_conexa));
					}
				}
			}
			i++;
		}
		return true;
	}
	private boolean marcar(Nodo_Grafo_Dirigido_2 nodo){
		if(nodo.valor_de_verdad()){
			Iterator<Nodo_Grafo_Dirigido_2> it = nodo.hermanos.iterator();
			while(it.hasNext()){
				Nodo_Grafo_Dirigido_2 hermano = it.next();
				if(!(hermano.valor_esta_fijado() && !hermano.valor_de_verdad())){
					if(!hermano.fijar_valores_de_verdad(false)){
						return false;
					}
					if(!marcar(hermano)){
						return false;
					}
				}
			}
			it = nodo.adyacentes.iterator();
			while(it.hasNext()){
				Nodo_Grafo_Dirigido_2 vecino = it.next();
				/*if(vecino.valor_esta_fijado() && !vecino.valor_de_verdad()){
					return false;
				}*/
				if(!(vecino.valor_esta_fijado() && vecino.valor_de_verdad())){
					if(!vecino.fijar_valores_de_verdad(true)){
						return false;
					}
					if(!marcar(vecino)){
						return false;
					}
				}
			}
			return true;
		}else{
			Iterator<Nodo_Grafo_Dirigido_2> it = nodo.hermanos.iterator();
			while(it.hasNext()){
				Nodo_Grafo_Dirigido_2 hermano = it.next();
				if(!(hermano.valor_esta_fijado() && hermano.valor_de_verdad())){
					if(!hermano.fijar_valores_de_verdad(true)){
						return false;
					}
					if(!marcar(hermano)){
						return false;
					}
				}
			}
			
			it = nodo.adyacentes_inverso.iterator();
			while(it.hasNext()){
				Nodo_Grafo_Dirigido_2 vecino = it.next();
				/*if(vecino.valor_esta_fijado() && vecino.valor_de_verdad()){
					return false;
				}*/
				if(!(vecino.valor_esta_fijado() && !vecino.valor_de_verdad())){
					if(!vecino.fijar_valores_de_verdad(false)){
						return false;
					}
					if(!marcar(vecino)){
						return false;
					}
				}
			}
			return true;
		}
	}
	private boolean intentar_fijar_valores(){
		Iterator<Nodo_Grafo_Dirigido_2> it = this.grafo_dirigido_compacto.iterator();
		while(it.hasNext()){
			Nodo_Grafo_Dirigido_2 nodo = it.next();
			if(!nodo.valor_esta_fijado()){
				nodo.fijar_valores_de_verdad(false);
				if(!marcar(nodo)){
					return false;
				}
			}
		}
		return true;
	}
	private String imprimir_valores(){
		Iterator<Nodo_Grafo_Dirigido_2> it = this.grafo_dirigido_compacto.iterator();
		while(it.hasNext()){
			Nodo_Grafo_Dirigido_2 nodo = it.next();
			if(nodo.valor_esta_fijado() && nodo.valor_de_verdad()){
				Iterator<Nodo_Grafo_Dirigido> it_interno = nodo.componente_conexa.iterator();
				while(it_interno.hasNext()){
					Nodo_Grafo_Dirigido nodo_dirigido = it_interno.next();
					this.salida_ints[nodo_dirigido.identidad] = nodo_dirigido.color;
				}
			}
		}
		String res = "";
		for(int i = 0;i<this.salida_ints.length;i++){
			res += Integer.toString(this.salida_ints[i]);
			res += " ";
		}
		System.out.printf("%s\n", res);
		return res;
	}
	private static void test_grafos_dirigidos(){
		Nodo_Grafo_Dirigido n1 = new Nodo_Grafo_Dirigido(0,0);
		Nodo_Grafo_Dirigido n2 = new Nodo_Grafo_Dirigido(1,0);
		Nodo_Grafo_Dirigido n3 = new Nodo_Grafo_Dirigido(2,0);
		Nodo_Grafo_Dirigido n4 = new Nodo_Grafo_Dirigido(3,0);
		Nodo_Grafo_Dirigido n5 = new Nodo_Grafo_Dirigido(4,0);
		Nodo_Grafo_Dirigido n6 = new Nodo_Grafo_Dirigido(5,0);
		
		n1.agregar_adyacentes(n2);
		n2.agregar_adyacentes(n3);
		n3.agregar_adyacentes(n4);
		n3.agregar_adyacentes(n5);
		n5.agregar_adyacentes(n6);
		n6.agregar_adyacentes(n2);
		Calculador_de_Coloracion_Ej1 testeo = new Calculador_de_Coloracion_Ej1(0,0,0,null);
		testeo.grafo_dirigido = new ArrayList<Nodo_Grafo_Dirigido>(6);
		testeo.grafo_dirigido.add(n1);
		testeo.grafo_dirigido.add(n2);
		testeo.grafo_dirigido.add(n3);
		testeo.grafo_dirigido.add(n4);
		testeo.grafo_dirigido.add(n5);
		testeo.grafo_dirigido.add(n6);
		testeo.DFS();
		Iterator<TreeSet<Nodo_Grafo_Dirigido>> it = testeo.componentes_fuertemente_conexas.iterator();
		System.out.printf("hola\n");
		while(it.hasNext()){
			Iterator<Nodo_Grafo_Dirigido> iterador_conjunto = it.next().iterator();
			Nodo_Grafo_Dirigido n = iterador_conjunto.next();
			System.out.printf("conjunto: %d elementos: ", n.id_componente_conexa);
			System.out.printf("%d ", n.identidad);
			while(iterador_conjunto.hasNext()){
				System.out.printf("%d ", iterador_conjunto.next().identidad);
			}
			System.out.printf("\n");
		}
	}
	public void resolucion(){
		this.generar_grafo_dirigido();
		this.DFS();
		if(!this.generar_grafo_compacto()){
			System.out.printf("X\n");
		}else if(!this.intentar_fijar_valores()){
			System.out.printf("X\n");
		}else{
			System.out.printf("%s\n",this.imprimir_valores());
		}
		
		int nodo_imprimido = 0;
		this.grafo_dirigido.get(nodo_imprimido).print();
		Iterator<Nodo_Grafo_Dirigido> it = this.grafo_dirigido.get(nodo_imprimido).adyacentes.iterator();
		while(it.hasNext()){
			it.next().print();
		}
	}
	private static void test_colores(){
		ArrayList<Nodo> entrada = new ArrayList<Nodo>(3);
		Nodo n1 = new Nodo(0);
		n1.colores.agregar_color(1);
		n1.colores.agregar_color(2);
		n1.cantidad_colores = 2;
		
		Nodo n2 = new Nodo(1);
		n2.colores.agregar_color(1);
		n2.colores.agregar_color(3);
		n2.cantidad_colores = 2;
		
		Nodo n3 = new Nodo(2);
		n3.colores.agregar_color(2);
		n3.colores.agregar_color(3);
		n3.cantidad_colores = 2;
		
		entrada.add(n1);
		entrada.add(n2);
		entrada.add(n3);
		Calculador_de_Coloracion_Ej1 testeo = new Calculador_de_Coloracion_Ej1(3,3,3,entrada);
		testeo.resolucion();
	}
	public static void main(String [] entrada){
		//test_grafos_dirigidos();
		test_colores();
	}
	
}
