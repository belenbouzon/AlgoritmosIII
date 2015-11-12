import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


public class Tester {
	public static class Nodo_Tester extends Nodo_Abstracto{
		public int id;
		public int color_1;
		public Integer color_2;
		public Set<Nodo_Tester> adyacentes;
		@Override
		public void agregar_adyacentes(Nodo_Abstracto otro) {
			Nodo_Tester casteado = (Nodo_Tester) otro;
			this.adyacentes.add(casteado);
			casteado.adyacentes.add(this);
			
		}
		public Nodo_Tester(int ident){
			this.id = ident;
			this.adyacentes = new LinkedHashSet<Nodo_Tester>();
		}
		public Nodo_Tester(int ident,int color){
			this.id = ident;
			this.adyacentes = new LinkedHashSet<Nodo_Tester>();
			this.color_1 = color;
			this.color_2 = null;
		}
		public Nodo_Tester(int ident,int color_1,int color_2){
			this.id = ident;
			this.adyacentes = new LinkedHashSet<Nodo_Tester>();
			this.color_1 = color_1;
			this.color_2 = color_2;
		}
	}
	public static void generar_grafo_aleatorio_ej1(int cant_nodos,int cant_aristas){
		ArrayList<Nodo_Tester> nodos = new ArrayList<Nodo_Tester>(cant_nodos);
		HashSet<Integer> conj = new HashSet<Integer>();
		for(int i = 0;i<cant_nodos;i++){
			nodos.add(new Nodo_Tester(i));
		}
		for(Nodo_Tester nodo: nodos){
			
		}
	}
	public static void imprimir_a_archivo(String archivo,List<Nodo_Tester> lista_nodo,int cantidad_nodos,int cantidad_aristas,int cantidad_colores){
		try {
			Escritor es = new Escritor(archivo);
			es.EscribirLinea(Integer.toString(cantidad_nodos));
			es.EscribirLinea(" ");
			es.EscribirLinea(Integer.toString(cantidad_aristas));
			es.EscribirLinea(" ");
			es.EscribirLinea(Integer.toString(cantidad_colores));
			es.NuevaLinea();
			for(Nodo_Tester nodo: lista_nodo){
				if(nodo.color_2==null){
					String salida = "1 ";
					salida += Integer.toString(nodo.color_1);
					es.EscribirLinea(salida);
					es.NuevaLinea();
				}else{
					String salida = "2 ";
					salida += Integer.toString(nodo.color_1);
					salida += " ";
					salida += Integer.toString(nodo.color_2);
					es.EscribirLinea(salida);
					es.NuevaLinea();
				}
			}
			for(Nodo_Tester nodo: lista_nodo){
				for(Nodo_Tester vecino: nodo.adyacentes){
					if(vecino.id > nodo.id){
						String linea = Integer.toString(nodo.id);
						linea += " ";
						linea += Integer.toString(vecino.id);
						es.EscribirLinea(linea);
						es.NuevaLinea();
					}
				}
			}
			es.Fin();
		} catch (Exception e) {
			System.out.print("error al crear escritor\n");
			System.out.printf("%s\n", archivo);
			e.printStackTrace();
		}
	}
	private static void vincular_nodos(List<Nodo_Tester> l,int nodo_1,int nodo_2){
		l.get(nodo_1).agregar_adyacentes(l.get(nodo_2));
	}
	@Test
	public static boolean los_unicos(){
		ArrayList<Nodo_Tester> lista = new ArrayList<Nodo_Tester>(9);
		for(int i = 0;i<9;i++){
			lista.add(new Nodo_Tester(i,i));
		}
		vincular_nodos(lista,0,1);
		vincular_nodos(lista,0,3);
		vincular_nodos(lista,3,7);
		vincular_nodos(lista,2,7);
		vincular_nodos(lista,5,8);
		vincular_nodos(lista,4,3);
		
		imprimir_a_archivo("los_unicos.txt",lista,lista.size(),6,lista.size());
		
		
		try {
			Lector lec = new Lector("los_unicos.txt");
			lec.inicializar_lector();
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_colores,lec.cantidad_nodos,lec.cantidad_aristas,lec.nodos_del_grafo);
			String solucion = res.obtener_resolucion();
			System.out.print(solucion);
			System.out.print("\n");
			if(solucion.equals("0 1 2 3 4 5 6 7 8 ")){
				System.out.print("exito\n");
				return true;
			}else{
				System.out.print("error\n");
				return false;
			}
		} catch (IOException e) {
			System.out.print("no pudo abrise el archivo (los únicos)\n");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static void color_comun(){
		ArrayList<Nodo_Tester> lista = new ArrayList<Nodo_Tester>(9);
		for(int i = 0;i<9;i++){
			lista.add(new Nodo_Tester(i,i,9));
			//lista.add(new Nodo_Tester(i,9,i));
		}
		vincular_nodos(lista,0,1);
		vincular_nodos(lista,0,3);
		vincular_nodos(lista,3,7);
		vincular_nodos(lista,2,7);
		vincular_nodos(lista,5,8);
		vincular_nodos(lista,4,3);
		
		imprimir_a_archivo("colores_en_comun.txt",lista,lista.size(),6,lista.size()+1);
		
		
		try {
			Lector lec = new Lector("colores_en_comun.txt");
			lec.inicializar_lector();
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_colores,lec.cantidad_nodos,lec.cantidad_aristas,lec.nodos_del_grafo);
			String solucion = res.obtener_resolucion();
			System.out.print(solucion);
			System.out.print("\n");
		} catch (IOException e) {
			System.out.print("no pudo abrise el archivo (colores común)\n");
			e.printStackTrace();
		}
		
	}
	
	public static void el_triangulo(){
		ArrayList<Nodo_Tester> lista = new ArrayList<Nodo_Tester>(3);
		lista.add(new Nodo_Tester(0,0,1));
		lista.add(new Nodo_Tester(1,0,2));
		lista.add(new Nodo_Tester(2,0,2));
		vincular_nodos(lista,0,1);
		vincular_nodos(lista,0,2);
		vincular_nodos(lista,1,2);
		imprimir_a_archivo("el_triangulo.txt",lista,3,3,3);
		try {
			Lector lec = new Lector("el_triangulo.txt");
			lec.inicializar_lector();
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_colores,lec.cantidad_nodos,lec.cantidad_aristas,lec.nodos_del_grafo);
			String solucion = res.obtener_resolucion();
			System.out.print(solucion);
			System.out.print("\n");
		} catch (IOException e) {
			System.out.print("no pudo abrise el archivo (el triangulo)\n");
			e.printStackTrace();
		}
		
		
		
		lista.add(new Nodo_Tester(3,1));
		vincular_nodos(lista,3,0);
		imprimir_a_archivo("el_triangulo_2.txt",lista,4,4,3);
		try {
			Lector lec = new Lector("el_triangulo_2.txt");
			lec.inicializar_lector();
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_colores,lec.cantidad_nodos,lec.cantidad_aristas,lec.nodos_del_grafo);
			String solucion = res.obtener_resolucion();
			System.out.print(solucion);
			System.out.print("\n");
		} catch (IOException e) {
			System.out.print("no pudo abrise el archivo (el triangulo)\n");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String [] entrada) {
		//los_unicos();
		//color_comun();
		el_triangulo();
	}
}
