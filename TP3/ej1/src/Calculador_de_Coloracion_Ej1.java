import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class Calculador_de_Coloracion_Ej1 {
	public ArrayList<Nodo_Coloreable> grafo_original;
	public ArrayList<Nodo_Dirigido_SAT> grafo_dirigido;
	public ArrayList<Nodo_Dirigido_Compacto> grafo_dirigido_compacto;
	private int cantidad_colores;
	private int cantidad_nodos;
	private int cantidad_aristas;
	public LinkedList<TreeSet<Nodo_Dirigido_SAT>> componentes_fuertemente_conexas;
	private int [] salida_ints;
	
	private Nodo_Dirigido_SAT nodo_dirigido_de_color(Nodo_Coloreable nodo,int color,TreeSet<Nodo_Dirigido_SAT> ya_creados){
		Nodo_Dirigido_SAT buscado = new Nodo_Dirigido_SAT(nodo.identidad,color,true);
		if(ya_creados.contains(buscado)){
			//System.out.printf("hola\n");
			buscado = ya_creados.ceiling(buscado);
		}else{
			System.out.printf("problema!!\n");
			buscado.hermano = new Nodo_Dirigido_SAT(nodo.identidad,color,false);
		}
		return buscado;
	}
	
	private void crear_nodo_dirigido(Nodo_Coloreable nodo,TreeSet<Nodo_Dirigido_SAT> ya_creados){
		List<Integer> lista_colores = nodo.colores.lista_de_colores();
		int primer_color = lista_colores.get(0); 
		Nodo_Dirigido_SAT nuevo_afirmacion = new Nodo_Dirigido_SAT(nodo.identidad,primer_color,true);
		Nodo_Dirigido_SAT nuevo_negacion = new Nodo_Dirigido_SAT(nodo.identidad,primer_color,false);
		
		nuevo_afirmacion.hermano = nuevo_negacion;
		nuevo_negacion.hermano = nuevo_afirmacion;
		
		ya_creados.add(nuevo_afirmacion);
		
		this.grafo_dirigido.add(nuevo_afirmacion);
		this.grafo_dirigido.add(nuevo_negacion);
		
		if(nodo.colores.cantidad_colores()==1){
			nuevo_negacion.agregar_adyacentes(nuevo_afirmacion);
		}else{
			int segundo_color = lista_colores.get(1);
			Nodo_Dirigido_SAT nuevo_afirmacion_B = new Nodo_Dirigido_SAT(nodo.identidad,segundo_color,true);
			Nodo_Dirigido_SAT nuevo_negacion_B = new Nodo_Dirigido_SAT(nodo.identidad,segundo_color,false);
			nuevo_afirmacion.agregar_adyacentes(nuevo_negacion_B);
			nuevo_afirmacion_B.agregar_adyacentes(nuevo_negacion);
			nuevo_negacion.agregar_adyacentes(nuevo_afirmacion_B);
			nuevo_negacion_B.agregar_adyacentes(nuevo_afirmacion);
			nuevo_afirmacion_B.hermano = nuevo_negacion_B;
			nuevo_negacion_B.hermano = nuevo_afirmacion_B;
			
			
			nuevo_afirmacion.color_alternativo = nuevo_afirmacion_B.color;
			nuevo_negacion.color_alternativo = nuevo_negacion_B.color;
			nuevo_afirmacion_B.color_alternativo = nuevo_afirmacion.color;
			nuevo_negacion_B.color_alternativo = nuevo_negacion.color;
			
			ya_creados.add(nuevo_afirmacion_B);
			this.grafo_dirigido.add(nuevo_afirmacion_B);
			this.grafo_dirigido.add(nuevo_negacion_B);
		}
	}
	public Calculador_de_Coloracion_Ej1(int cant_colores, int cant_nodos, int cant_aristas, ArrayList<Nodo_Coloreable> nodos_de_grafo){
		this.cantidad_colores = cant_colores;
		this.cantidad_nodos = cant_nodos;
		this.cantidad_aristas = cant_aristas;
		this.grafo_original = nodos_de_grafo;
		this.salida_ints = new int [cantidad_nodos];
	}
	public void relacionar_nodos(Nodo_Coloreable nodo,TreeSet<Nodo_Dirigido_SAT> ya_creados){
		Iterator<Nodo_Coloreable> it = nodo.adyacentes.iterator();
		//System.out.print("I'm here\n");
		while(it.hasNext()){
			Nodo_Coloreable vecino = it.next();
			ColoresPosibles colores_en_comun = nodo.colores.colores_en_comun(vecino.colores);
			Iterator<Integer> it_colores = colores_en_comun.iterador_de_colores();
			while(it_colores.hasNext()){
				int color = it_colores.next();
				Nodo_Dirigido_SAT nodo_color_1 = this.nodo_dirigido_de_color(nodo, color, ya_creados);
				Nodo_Dirigido_SAT nodo_color_2 = this.nodo_dirigido_de_color(vecino, color, ya_creados);
				nodo_color_1.agregar_adyacentes(nodo_color_2.hermano);
				
				nodo_color_2.agregar_adyacentes(nodo_color_1.hermano);
			}
		}
	}
	
	public void generar_grafo_dirigido(){
		TreeSet<Nodo_Dirigido_SAT> ya_creados = new TreeSet<Nodo_Dirigido_SAT>(); 
		this.grafo_dirigido = new ArrayList<Nodo_Dirigido_SAT>(this.cantidad_nodos*2);
		Iterator<Nodo_Coloreable> it = this.grafo_original.iterator();
		while(it.hasNext()){
			crear_nodo_dirigido(it.next(), ya_creados);
		}
		it = this.grafo_original.iterator();
		while(it.hasNext()){
			relacionar_nodos(it.next(),ya_creados);
		}
	}
	
	private void auxiliar_DFS_original(Nodo_Dirigido_SAT nodo,Stack<Nodo_Dirigido_SAT> pila){
		if(nodo.id_componente_conexa==-2){
			nodo.id_componente_conexa = -1;
			Iterator<Nodo_Dirigido_SAT> it = nodo.adyacentes.iterator();
			while(it.hasNext()){
				Nodo_Dirigido_SAT nuevo = it.next();
				auxiliar_DFS_original(nuevo,pila);
			}
			pila.add(nodo);
		}
	}
	
	private void DFS_original(Stack<Nodo_Dirigido_SAT> pila){
		Iterator<Nodo_Dirigido_SAT> it = this.grafo_dirigido.iterator();
		while(it.hasNext()){
			auxiliar_DFS_original(it.next(),pila);
		}
	}
	private void DFS_inverso_auxiliar(Nodo_Dirigido_SAT nodo,TreeSet<Nodo_Dirigido_SAT> conjunto,int id_componente_conexa_actual){
		if(nodo.id_componente_conexa==-1){ 
			conjunto.add(nodo);
			nodo.id_componente_conexa = id_componente_conexa_actual;
			for(Nodo_Dirigido_SAT vecino: nodo.adyacentes_inverso){
				if(vecino.id_componente_conexa==-2){
					System.out.printf("error! 1\n");
				}
				DFS_inverso_auxiliar(vecino,conjunto,id_componente_conexa_actual);
			}
		}
	}
	private void DFS_inverso(Stack<Nodo_Dirigido_SAT> pila){
		this.componentes_fuertemente_conexas = new LinkedList<TreeSet<Nodo_Dirigido_SAT>> ();
		int id_componente_conexa_actual = 0;
		while(!pila.empty()){
			Nodo_Dirigido_SAT nodo = pila.pop();
			if(nodo.id_componente_conexa==-2){
				System.out.printf("error! 2\n");
			}
			if(nodo.id_componente_conexa==-1){
				TreeSet<Nodo_Dirigido_SAT> nuevo = new TreeSet<Nodo_Dirigido_SAT>(); 
				this.DFS_inverso_auxiliar(nodo, nuevo, id_componente_conexa_actual);
				this.componentes_fuertemente_conexas.add(nuevo);
				id_componente_conexa_actual++;
			}
		}
	}
	public void DFS(){
		Stack<Nodo_Dirigido_SAT> pila = new Stack<Nodo_Dirigido_SAT>();
		this.DFS_original(pila);
		this.DFS_inverso(pila);
	}
	
	private boolean generar_grafo_compacto(){
		int size = this.componentes_fuertemente_conexas.size();
		this.grafo_dirigido_compacto = new ArrayList<Nodo_Dirigido_Compacto>(size);
		
		for(int i = 0;i<size;i++){
			this.grafo_dirigido_compacto.add(new Nodo_Dirigido_Compacto(i,i,null));
		}
		
		int i = 0;
		for(TreeSet<Nodo_Dirigido_SAT> conj: this.componentes_fuertemente_conexas){
			Nodo_Dirigido_Compacto nodo_compacto = this.grafo_dirigido_compacto.get(i);
			nodo_compacto.componente_conexa = conj;
			
			for(Nodo_Dirigido_SAT nodo: conj) {
				if(nodo.hermano.id_componente_conexa==i){
					if(!marcar(nodo_compacto,false)){
						return false;
					}
				}else{
					nodo_compacto.agregar_hermano(this.grafo_dirigido_compacto.get(nodo.hermano.id_componente_conexa));
				}
				
				for(Nodo_Dirigido_SAT vecino:  nodo.adyacentes){
					if(vecino.id_componente_conexa!=i){
						nodo_compacto.agregar_adyacentes(this.grafo_dirigido_compacto.get(vecino.id_componente_conexa));
					}
				}
			}
			i++;
		}
		return true;
	}
	
	//si el metodos retorna false
	
	/**
	 * @param nodo
	 * @param valor_de_verdad
	 * @return false cuando determina que el coloreo no es posible para la entrada, true en caso contrario 
	 */
	private boolean marcar(Nodo_Dirigido_Compacto nodo,boolean valor_de_verdad){ 
		if(!nodo.fijar_valores_de_verdad(valor_de_verdad)){
			return false;
		}
		
		/*for(Nodo_Dirigido_Compacto hermano:nodo.hermanos){
			if(!(hermano.valor_fijado_en(!valor_de_verdad))){
				if(!marcar(hermano,!valor_de_verdad)){
					return false;
				}
			}
		}*/
		if(!marcar_nodos(!valor_de_verdad,nodo.hermanos)){
			return false;
		}
		
		if(valor_de_verdad){
			return marcar_nodos(valor_de_verdad, nodo.adyacentes);
		}else{
			return marcar_nodos(valor_de_verdad, nodo.adyacentes_inverso);
		}
	}
	
	
	private boolean marcar_nodos(boolean valor_de_verdad, Set<Nodo_Dirigido_Compacto> nodos_adyacentes) {
		for(Nodo_Dirigido_Compacto vecino: nodos_adyacentes){
			if(!vecino.valor_fijado_en(valor_de_verdad)){
				if(!marcar(vecino,valor_de_verdad)){
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean intentar_fijar_valores(){
		for(Nodo_Dirigido_Compacto nodo: this.grafo_dirigido_compacto){
			if(!nodo.valor_esta_fijado()){
				if(!marcar(nodo,false)){
					return false;
				}
			}
		}
		
		return true;
	}
	private String imprimir_valores(){
		boolean uno_verdadero= false;
		for(Nodo_Dirigido_Compacto nodo: this.grafo_dirigido_compacto){
			if(nodo.valor_fijado_en(true)){
				uno_verdadero = true;
				Iterator<Nodo_Dirigido_SAT> it_interno = nodo.componente_conexa.iterator();
				while(it_interno.hasNext()){
					Nodo_Dirigido_SAT nodo_dirigido = it_interno.next();
					if(nodo_dirigido.formula_afirmativa){
						this.salida_ints[nodo_dirigido.identidad] = nodo_dirigido.color;
					}else{
						this.salida_ints[nodo_dirigido.identidad] = nodo_dirigido.color_alternativo;
					}
				}
			}
		}
		StringBuilder res = new StringBuilder(); 
		if(uno_verdadero){
			for(int i = 0;i<this.salida_ints.length;i++){
				res.append(this.salida_ints[i]);
				res.append(" ");
			}
		}else{
			res.append("X");
		}
		return res.toString();
	}
	
	private static void test_grafos_dirigidos(){
		Nodo_Dirigido_SAT n1 = new Nodo_Dirigido_SAT(0,0,true);
		Nodo_Dirigido_SAT n2 = new Nodo_Dirigido_SAT(1,0,true);
		Nodo_Dirigido_SAT n3 = new Nodo_Dirigido_SAT(2,0,true);
		Nodo_Dirigido_SAT n4 = new Nodo_Dirigido_SAT(3,0,true);
		Nodo_Dirigido_SAT n5 = new Nodo_Dirigido_SAT(4,0,true);
		Nodo_Dirigido_SAT n6 = new Nodo_Dirigido_SAT(5,0,true);
		
		n1.agregar_adyacentes(n2);
		n2.agregar_adyacentes(n3);
		n3.agregar_adyacentes(n4);
		n3.agregar_adyacentes(n5);
		n5.agregar_adyacentes(n6);
		n6.agregar_adyacentes(n2);
		Calculador_de_Coloracion_Ej1 testeo = new Calculador_de_Coloracion_Ej1(0,0,0,null);
		testeo.grafo_dirigido = new ArrayList<Nodo_Dirigido_SAT>(6);
		testeo.grafo_dirigido.add(n1);
		testeo.grafo_dirigido.add(n2);
		testeo.grafo_dirigido.add(n3);
		testeo.grafo_dirigido.add(n4);
		testeo.grafo_dirigido.add(n5);
		testeo.grafo_dirigido.add(n6);
		testeo.DFS();
		Iterator<TreeSet<Nodo_Dirigido_SAT>> it = testeo.componentes_fuertemente_conexas.iterator();
		while(it.hasNext()){
			Iterator<Nodo_Dirigido_SAT> iterador_conjunto = it.next().iterator();
			Nodo_Dirigido_SAT n = iterador_conjunto.next();
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
	}
	public String obtener_resolucion(){
		this.generar_grafo_dirigido();
		this.DFS();
		if(!this.generar_grafo_compacto()){
			return "X";
		}else if(!this.intentar_fijar_valores()){
			return "X";
		}else{
			//TODO -------------------BORRAR------------
			/*System.out.print("empiezan valores de conjuntos\n");
			int i = 0;
			System.out.printf("longitudes: %d %d\n",this.grafo_dirigido_compacto.size(),this.componentes_fuertemente_conexas.size());
			for(TreeSet<Nodo_Dirigido_SAT> conj: this.componentes_fuertemente_conexas){
				for(Nodo_Dirigido_SAT nodo: conj){
					if(!nodo.formula_afirmativa){
						System.out.print("¬");
					}
					System.out.printf("%d %d | ", nodo.identidad,nodo.color);
					
				}
				System.out.print(this.grafo_dirigido_compacto.get(i).valor_fijado_en(true));
				i++;
				System.out.print("\n");
			}
			System.out.print("fin valores de conjuntos\n");*/
			//---------------------------------------------
			return this.imprimir_valores();
		}
	}
	private static void test_colores(){
		ArrayList<Nodo_Coloreable> entrada = new ArrayList<Nodo_Coloreable>(3);
		Nodo_Coloreable n1 = new Nodo_Coloreable(0);
		n1.colores.agregar_color(1);
		n1.colores.agregar_color(2);
		n1.cantidad_colores = 2;
		
		Nodo_Coloreable n2 = new Nodo_Coloreable(1);
		n2.colores.agregar_color(1);
		n2.colores.agregar_color(3);
		n2.cantidad_colores = 2;
		
		Nodo_Coloreable n3 = new Nodo_Coloreable(2);
		n3.colores.agregar_color(2);
		n3.colores.agregar_color(3);
		n3.cantidad_colores = 2;
		
		n1.adyacentes.add(n2);
		n1.adyacentes.add(n3);
		
		n2.adyacentes.add(n1);
		n2.adyacentes.add(n3);
		
		n3.adyacentes.add(n2);
		n3.adyacentes.add(n1);
		
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
