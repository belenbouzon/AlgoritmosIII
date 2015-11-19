import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import java.util.Random;

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

	public static String[][] imprimir_a_string (List<Nodo_Tester> lista_nodo,int cantidad_nodos,int cantidad_aristas,int cantidad_colores){
		int nodo_iesimo = 0;
		String[][] res = new String[2][];
		res[0] = new String[cantidad_nodos];
		res[1] = new String[cantidad_aristas];
		for(Nodo_Tester nodo: lista_nodo){
			if(nodo.color_2==null){
				String salida = "1 " + Integer.toString(nodo.color_1);
				res[0][nodo_iesimo] = salida;
			}else{
				String salida = "2 " + Integer.toString(nodo.color_1) + " " + Integer.toString(nodo.color_2);
				res[0][nodo_iesimo] = salida;
			}
			nodo_iesimo++;
		}
		int arista_iesima = 0;
		for(Nodo_Tester nodo: lista_nodo){
			for(Nodo_Tester vecino: nodo.adyacentes){
				if(vecino.id > nodo.id){
					String linea = Integer.toString(nodo.id) + " " + Integer.toString(vecino.id);
					res[1][arista_iesima] = linea;
					arista_iesima++;
				}
			}
		}
		return res;
	}

	private static boolean vincular_nodos(List<Nodo_Tester> l,int nodo_1,int nodo_2){
		if(l.get(nodo_1).adyacentes.contains(l.get(nodo_2))){
			return false;
		}
		
		l.get(nodo_1).agregar_adyacentes(l.get(nodo_2));
		return true;
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
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_nodos(),lec.nodos_del_grafo());
			String solucion = res.obtener_resolucion();
			System.out.print(solucion);
			System.out.print("\n");
			if(solucion.trim().equals("0 1 2 3 4 5 6 7 8")){
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
	
	@Test
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
		demostrar_correctitud("colores_en_comun.txt",lista,ejecutar("colores_en_comun.txt"));
		
	}
	
	@Test
	public static void el_triangulo(){
		ArrayList<Nodo_Tester> lista = new ArrayList<Nodo_Tester>(3);
		lista.add(new Nodo_Tester(0,0,1));
		lista.add(new Nodo_Tester(1,0,2));
		lista.add(new Nodo_Tester(2,0,2));
		vincular_nodos(lista,0,1);
		vincular_nodos(lista,0,2);
		vincular_nodos(lista,1,2);
		imprimir_a_archivo("el_triangulo.txt",lista,3,3,3);
		demostrar_correctitud("el triangulo",lista,ejecutar("el_triangulo.txt"));
		
		lista.add(new Nodo_Tester(3,1));
		vincular_nodos(lista,3,0);
		imprimir_a_archivo("el_triangulo_2.txt",lista,4,4,3);
		Tester.demostrar_correctitud_no_coloreable("triangulo con punta",ejecutar("el_triangulo_2.txt"));
		
	}

	@Test
	public static void el_pentagono(){
		System.out.print("el_pentagono comienza\n");
		ArrayList<Nodo_Tester> lista = new ArrayList<Nodo_Tester>(5);
		lista.add(new Nodo_Tester(0,5,0));
		lista.add(new Nodo_Tester(1,5,1));
		lista.add(new Nodo_Tester(2,6,2));
		lista.add(new Nodo_Tester(3,6,3));
		lista.add(new Nodo_Tester(4,5,4));
		vincular_nodos(lista,0,1);
		vincular_nodos(lista,0,2);
		vincular_nodos(lista,0,3);
		vincular_nodos(lista,0,4);

		vincular_nodos(lista,1,2);
		vincular_nodos(lista,1,3);
		vincular_nodos(lista,1,4);

		vincular_nodos(lista,2,3);
		vincular_nodos(lista,2,4);

		vincular_nodos(lista,3,4);

		imprimir_a_archivo("el_pentagono.txt",lista,5,10,7);
		demostrar_correctitud("el pentagono",lista, ejecutar("el_pentagono.txt"));
		System.out.print("el_pentagono finaliza\n");
	}

	@Test
	public static String ejecutar(String entrada){
		try {
			Lector lec = new Lector(entrada);
			lec.inicializar_lector();
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_nodos(),lec.nodos_del_grafo());
			String solucion = res.obtener_resolucion();
			System.out.print(solucion);
			System.out.print("\n");
			return solucion;
		} catch (IOException e) {
			System.out.printf("no pudo abrise el archivo (%s)\n",entrada);
			e.printStackTrace();
			return null;
		}
	}

	@Test
	public static long ejecutar_para_test(String entrada){
		try {
			Lector lec = Lector.crear_lector_cargado(entrada);
			long inicio = System.nanoTime();
			lec.inicializar_lector();
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_nodos(),lec.nodos_del_grafo());
			res.obtener_resolucion();
			long fin = System.nanoTime();
			return fin - inicio;
		} catch (IOException e) {
			System.out.printf("no pudo abrise el archivo (%s)\n",entrada);
			e.printStackTrace();
			return 0;
		}
	}

	@Test
	public static void el_senior_de_los_anillos(){
		System.out.print("el_señor_de_los_anillos comienza\n");
		ArrayList<Nodo_Tester> lista = new ArrayList<Nodo_Tester>(5);
		lista.add(new Nodo_Tester(0,0,2));
		lista.add(new Nodo_Tester(1,1,2));
		lista.add(new Nodo_Tester(2,0,3));
		lista.add(new Nodo_Tester(3,1,3));
		lista.add(new Nodo_Tester(4,0,4));
		lista.add(new Nodo_Tester(5,1,4));
		lista.add(new Nodo_Tester(6,0,5));
		lista.add(new Nodo_Tester(7,1,5));

		vincular_nodos(lista,0,1);
		vincular_nodos(lista,1,2);
		vincular_nodos(lista,2,3);
		vincular_nodos(lista,3,4);
		vincular_nodos(lista,4,5);
		vincular_nodos(lista,6,7);
		vincular_nodos(lista,7,0);

		imprimir_a_archivo("el_senior_de_los_anillos.txt",lista,8,7,6);
		demostrar_correctitud("el senior de los anillos",lista,ejecutar("el_senior_de_los_anillos.txt"));
		System.out.print("el_señor_de_los_anillos finaliza\n");

		
	}
	
	private static int recuperar_indice(int[] indices,int i){
		if(indices[i]!=i){
			int res = recuperar_indice(indices,indices[i]);
			indices[i] = res;
			return res;
		}else{
			return i;
		}
	}
	

	public static ArrayList<Nodo_Tester> generar_grafo_conexion_aleatoria(String salida_de_archivo, int cant_nodos,int cant_aristas,int variacion_segundo_color){
		Random rand = new Random();
		ArrayList<Nodo_Tester> lista = new ArrayList<Nodo_Tester>(cant_nodos);
		int[] indices = new int[cant_nodos];
		for(int i = 0;i<cant_nodos;i++){
			Nodo_Tester nuevo = new Nodo_Tester(i,rand.nextInt(variacion_segundo_color),i);
			lista.add(nuevo);
			indices[i] = i;
		}

		int i = 0;
		while(i<=cant_aristas){
			Nodo_Tester nodo_1 = lista.get(recuperar_indice(indices,rand.nextInt(cant_nodos)));
			Nodo_Tester nodo_2 = lista.get(recuperar_indice(indices,rand.nextInt(cant_nodos)));
			if(nodo_1!=nodo_2 && vincular_nodos(lista,nodo_1.id,nodo_2.id)){
				i++;
				
				//Si el nodo alcanzo grado maximo no puede salir seleccionado
				if(nodo_1.adyacentes.size()>= cant_nodos-1){
					indices[nodo_1.id] = indices[(nodo_1.id+1)%cant_nodos];
				}
				if(nodo_2.adyacentes.size()>= cant_nodos-1){
					indices[nodo_2.id] = indices[(nodo_2.id+1)%cant_nodos];
				}
			}
		}
		int color_maximo;
		if(cant_nodos > variacion_segundo_color){
			color_maximo = cant_nodos;
		}else{
			color_maximo = variacion_segundo_color;
		}
		if(salida_de_archivo!=null){
			imprimir_a_archivo(salida_de_archivo, lista,cant_nodos,cant_aristas,color_maximo);
		}
		return lista;
	}

	public static boolean combinacion_correcta(Nodo_Tester nod, int[] colores){
		int color_de_nodo = colores[nod.id];
		if(color_de_nodo!=nod.color_1 && color_de_nodo!=nod.color_2){
			return false;
		}
		for(Nodo_Tester vecino: nod.adyacentes){
			if(color_de_nodo==colores[vecino.id]){
				return false;
			}
		}
		return true;
	}
	
	public static void demostrar_correctitud_no_coloreable(String nombre, String solucion){
		System.out.printf("%s.....",nombre);
		if(solucion.trim().equals("X")){
			System.out.print("correcto\n");
		}else{
			System.out.print("fallo\n");
			System.out.printf("solución fallida: %s\n", solucion);
		}
	}

	public static void demostrar_correctitud(String nombre,ArrayList<Nodo_Tester> lista_nodos, String solucion){
		System.out.printf("%s.....",nombre);
		int [] colores = new int [lista_nodos.size()];
		String [] colores_string = solucion.split(" ");
		for(int i = 0; i<lista_nodos.size();i++){
			colores[i] = Integer.parseInt(colores_string[i]);
		}
		for(Nodo_Tester nod: lista_nodos){
			if(!combinacion_correcta(nod,colores)){
				System.out.print("fallo\n");
			}
		}
		System.out.print("correcto\n");
	}

	public static void generar_y_comprobar(String salida_de_archivo, int cant_nodos,int cant_aristas,int variacion_segundo_color){
		ArrayList<Nodo_Tester> lista = generar_grafo_conexion_aleatoria(salida_de_archivo,cant_nodos,cant_aristas,variacion_segundo_color);
		String solucion = ejecutar(salida_de_archivo);
		demostrar_correctitud(salida_de_archivo,lista,solucion);
	}

	public static long ejecutar_sin_archivo(int cant_nodos,int cant_aristas,int variacion_segundo_color){
			ArrayList<Nodo_Tester> lista = generar_grafo_conexion_aleatoria(null,cant_nodos,cant_aristas,variacion_segundo_color);

			int cant_colores;
			if(cant_nodos > variacion_segundo_color){
				cant_colores = cant_nodos;
			}else{
				cant_colores = variacion_segundo_color;
			}
			String[][] entrada_de_lector = imprimir_a_string(lista,cant_nodos,cant_aristas,cant_colores);

			Lector lec = new Lector(cant_nodos,cant_aristas,cant_colores,entrada_de_lector[0],entrada_de_lector[1]);
			long inicio = System.nanoTime();
			try {
				lec.inicializar_lector();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_nodos(),lec.nodos_del_grafo());
			res.obtener_resolucion();
			long fin = System.nanoTime();
			return fin-inicio;
	}
	
	public static long ejecutar_sin_archivo_B(ArrayList<Nodo_Tester> lista, int cant_nodos,int cant_aristas,int cant_colores){
		String[][] entrada_de_lector = imprimir_a_string(lista,cant_nodos,cant_aristas,cant_colores);

		Lector lec = new Lector(cant_nodos,cant_aristas,cant_colores,entrada_de_lector[0],entrada_de_lector[1]);
		long inicio = System.nanoTime();
		try {
			lec.inicializar_lector();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_nodos(),lec.nodos_del_grafo());
		res.obtener_resolucion();
		long fin = System.nanoTime();
		return fin-inicio;
	}
	
	public static ArrayList<Nodo_Tester> generar_grafo_completo(int cantidad_inicial,int cantidad_final){
		ArrayList<Nodo_Tester> res = new ArrayList<Nodo_Tester>(cantidad_final);
		for(int i = 0;i<cantidad_inicial;i++){
			res.add(new Nodo_Tester(i,0,i+1));
		}
		for(int i = 0;i<cantidad_inicial;i++){
			for(int j=i+1;j<cantidad_inicial;j++){
				vincular_nodos(res,i,j);
			}
		}
		return res;
	}
	
	public static void expandir_grafo_completo(ArrayList<Nodo_Tester> lista,int escala){
		int tamanio_anterior = lista.size();
		for(int i = 0;i<escala;i++){
			lista.add(new Nodo_Tester(i+tamanio_anterior,0,i+tamanio_anterior+1));
		}
		for(int i = 0;i<escala;i++){
			for(int j=0;j<tamanio_anterior;j++){
				vincular_nodos(lista,j,i+tamanio_anterior);
			}
			for(int j=i+1;j<escala;j++){
				vincular_nodos(lista,j+tamanio_anterior,i+tamanio_anterior);
			}
		}
	}
	
	public static void testear_grafo_bipartito_completo(int cant_nodos){
		ArrayList<Nodo_Tester> list = new ArrayList<Nodo_Tester>(cant_nodos);
		for(int i = 0;i<cant_nodos;i++){
			list.add(new Nodo_Tester(i,0,1));
		}
		for(int i = 0;i<cant_nodos;i+=2){
			for(int j=1;j<cant_nodos;j+=2){
				vincular_nodos(list,i,j);
			}
		}
		int cant_aristas;
		if(cant_nodos%2==1){
			cant_aristas = (cant_nodos/2)*(cant_nodos/2 +1);
		}else{
			cant_aristas = (cant_nodos/2)*(cant_nodos/2);
		}
		imprimir_a_archivo("grafo_bipartito.txt", list, cant_nodos, cant_aristas, 2);
		demostrar_correctitud("grafo bipartito",list,ejecutar("grafo_bipartito.txt"));
		vincular_nodos(list,0,2);
		imprimir_a_archivo("grafo_casi_bipartito.txt", list, cant_nodos, cant_aristas, 2);
		demostrar_correctitud_no_coloreable("grafo casi bipartito", ejecutar("grafo_casi_bipartito.txt"));
	}

	public static void main(String [] entrada){
		if(entrada.length==0){
			System.out.print("0: generar y comprobar\n1:variación nodos <archivo> <nodos minimo> <nodos maximo> <aristas> <variacion segundo color> <cantidad iteraciones> <escala>\n2:variación aristas <archivo> <nodos> <aristas minimo> <aristas maximo> <variacion segundo color> <cantidad iteraciones> <escala>\n3:variación nodos sin archivo <nodos minimo> <nodos maximo> <aristas> <variacion segundo color> <cantidad iteraciones> <escala>\n4:variación aristas sin archivo <nodos> <aristas minimo> <aristas maximo> <variacion segundo color> <cantidad iteraciones> <escala>\n5:variación nodos y aristas <archivo> <nodos minimo> <nodos maximo> <aristas_minimo> <variacion segundo color> <cantidad iteraciones> <escala>\n6:variación nodos y aristas sin archivo <nodos_minimo> <nodos_maximo> <aristas minimo> <variacion segundo color> <cantidad iteraciones> <escala>\n 7: grafo completo: <nodos_minimo> <nodos_maximo> <cantidad iteraciones> <escala>\n8: Testar grafo bipartito completo: <cantidad_nodos>\n");
			return;
		}
		int primer_parametro = Integer.parseInt(entrada[0]);
		if(primer_parametro==0){
			generar_y_comprobar(entrada[1],Integer.parseInt(entrada[2]),Integer.parseInt(entrada[3]),Integer.parseInt(entrada[4]));
		}else if(primer_parametro==1){
			int nodos_minimo = Integer.parseInt(entrada[2]);
			int nodos_maximo = Integer.parseInt(entrada[3]);
			int cant_iteraciones = Integer.parseInt(entrada[6]);
			int escala = Integer.parseInt(entrada[7]);

			double [] soluciones = new double[nodos_maximo-nodos_minimo+1];
			for(int i=0;i<nodos_maximo-nodos_minimo+1;i++){
				soluciones[i] = 0;
			}
			
			int aristas = Integer.parseInt(entrada[4]);
			int cant_valor_secundario = Integer.parseInt(entrada[5]);
			
			for(int nodos = nodos_minimo;nodos<=nodos_maximo;nodos+=escala){
				for(int i = 0;i<cant_iteraciones;i++){
					generar_grafo_conexion_aleatoria(entrada[1],nodos,aristas,cant_valor_secundario);
					soluciones[nodos-nodos_minimo] += ((double)ejecutar_para_test(entrada[1]))/100000;
				}
				System.out.printf("%d %f\n",nodos,soluciones[nodos-nodos_minimo]);
			}
		}else if(primer_parametro==2){
			int aristas_minimo = Integer.parseInt(entrada[3]);
			int aristas_maximo = Integer.parseInt(entrada[4]);
			int cant_iteraciones = Integer.parseInt(entrada[6]);
			int escala = Integer.parseInt(entrada[7]);

			double [] soluciones = new double[aristas_maximo-aristas_minimo+1];
			for(int i=0;i<aristas_maximo-aristas_minimo+1;i++){
				soluciones[i] = 0;
			}

			int nodos = Integer.parseInt(entrada[2]);
			int cant_valor_secundario = Integer.parseInt(entrada[5]);
			
			for(int aristas = aristas_minimo;aristas<=aristas_maximo;aristas+=escala){
				for(int i = 0;i<cant_iteraciones;i++){
					generar_grafo_conexion_aleatoria(entrada[1],nodos,aristas,cant_valor_secundario);
					soluciones[aristas-aristas_minimo] += ((double)ejecutar_para_test(entrada[1]))/100000;
				}
				System.out.printf("%d %f\n",aristas,soluciones[aristas-aristas_minimo]);
			}
		}else if(primer_parametro==3){
			int nodos_minimo = Integer.parseInt(entrada[1]);
			int nodos_maximo = Integer.parseInt(entrada[2]);
			int cant_iteraciones = Integer.parseInt(entrada[5]);
			int escala = Integer.parseInt(entrada[6]);


			double [] soluciones = new double[nodos_maximo-nodos_minimo+1];
			for(int i=0;i<nodos_maximo-nodos_minimo+1;i++){
				soluciones[i] = 0;
			}

			int aristas = Integer.parseInt(entrada[3]);
			int cant_valor_secundario = Integer.parseInt(entrada[4]);
			for(int nodos = nodos_minimo;nodos<=nodos_maximo;nodos+=escala){
				for(int i = 0;i<cant_iteraciones;i++){
					soluciones[nodos-nodos_minimo] += ((double)ejecutar_sin_archivo(nodos,aristas,cant_valor_secundario))/100000;
				}
				System.out.printf("%d %f\n",nodos,soluciones[nodos-nodos_minimo]);
			}
		}else if(primer_parametro==4){
			int aristas_minimo = Integer.parseInt(entrada[2]);
			int aristas_maximo = Integer.parseInt(entrada[3]);
			int cant_iteraciones = Integer.parseInt(entrada[5]);
			int escala = Integer.parseInt(entrada[6]);



			double [] soluciones = new double[aristas_maximo-aristas_minimo+1];
			for(int i=0;i<aristas_maximo-aristas_minimo+1;i++){
				soluciones[i] = 0;
			}



			for(int aristas = aristas_minimo;aristas<=aristas_maximo;aristas+=escala){
				for(int i = 0;i<cant_iteraciones;i++){
					soluciones[aristas-aristas_minimo] += ((double) ejecutar_sin_archivo(Integer.parseInt(entrada[1]),aristas,Integer.parseInt(entrada[4])))/100000;
				}
				System.out.printf("%d %f\n",aristas,soluciones[aristas-aristas_minimo]);
			}
		}else if(primer_parametro==5){
			int nodos_minimo = Integer.parseInt(entrada[2]);
			int nodos_maximo = Integer.parseInt(entrada[3]);
			int cant_iteraciones = Integer.parseInt(entrada[6]);
			int aristas_minimo = Integer.parseInt(entrada[4]);
			int escala = Integer.parseInt(entrada[7]);

			double [] soluciones = new double[nodos_maximo-nodos_minimo+1];
			for(int i=0;i<nodos_maximo-nodos_minimo+1;i++){
				soluciones[i] = 0;
			}
			int iteraciones = nodos_maximo - nodos_minimo;
			int cant_valor_secundario = Integer.parseInt(entrada[5]);
			for(int it = 0;it<=iteraciones;it+=escala){
				for(int i = 0;i<cant_iteraciones;i++){
					generar_grafo_conexion_aleatoria(entrada[1],it+nodos_minimo,aristas_minimo+it,cant_valor_secundario);
					soluciones[it] += ((double)ejecutar_para_test(entrada[1]))/100000;
				}
				System.out.printf("%d %f\n",it+nodos_minimo,soluciones[it]);
			}
		}else if(primer_parametro==6){
			int nodos_minimo = Integer.parseInt(entrada[1]);
			int nodos_maximo = Integer.parseInt(entrada[2]);
			int cant_iteraciones = Integer.parseInt(entrada[5]);
			int escala = Integer.parseInt(entrada[6]);


			double [] soluciones = new double[nodos_maximo-nodos_minimo+1];
			for(int i=0;i<nodos_maximo-nodos_minimo+1;i++){
				soluciones[i] = 0;
			}

			int iteraciones = nodos_maximo - nodos_minimo;
			int aristas_minimo = Integer.parseInt(entrada[3]);
			int cant_valor_secundario = Integer.parseInt(entrada[4]);
			for(int it = 0;it<=iteraciones;it+=escala){
				for(int i = 0;i<cant_iteraciones;i++){
					soluciones[it] += ((double)ejecutar_sin_archivo(it+nodos_minimo,aristas_minimo+it,cant_valor_secundario))/100000;
				}
				System.out.printf("%d %f\n",nodos_minimo+it,soluciones[it]);
			}
		}else if(primer_parametro==7){
			int nodos_minimo = Integer.parseInt(entrada[1]);
			int nodos_maximo = Integer.parseInt(entrada[2]);
			int cant_iteraciones = Integer.parseInt(entrada[3]);
			int escala = Integer.parseInt(entrada[4]);
			ArrayList<Nodo_Tester> lista = generar_grafo_completo(nodos_minimo,nodos_maximo);
			for(int n = nodos_minimo;n<=nodos_maximo;n+=escala){
				double tiempo = 0;
				for(int i=0;i<cant_iteraciones;i++){
					tiempo += ((double)ejecutar_sin_archivo_B(lista,n,sumatoria(n),n+1))/cant_iteraciones;
				}
				expandir_grafo_completo(lista,escala);
				System.out.printf("%d %f\n",n,tiempo);
			}
		}else if(primer_parametro==8){
			int cantidad_nodos = Integer.parseInt(entrada[1]);
			testear_grafo_bipartito_completo(cantidad_nodos);
		}else{
			System.out.print("0: generar y comprobar\n1:variación nodos <archivo> <nodos minimo> <nodos maximo> <aristas> <variacion segundo color> <cantidad iteraciones> <escala>\n2:variación aristas <archivo> <nodos> <aristas minimo> <aristas maximo> <variacion segundo color> <cantidad iteraciones> <escala>\n3:variación nodos sin archivo <nodos minimo> <nodos maximo> <aristas> <variacion segundo color> <cantidad iteraciones> <escala>\n4:variación aristas sin archivo <nodos> <aristas minimo> <aristas maximo> <variacion segundo color> <cantidad iteraciones> <escala>\n5:variación nodos y aristas <archivo> <nodos minimo> <nodos maximo> <aristas_minimo> <variacion segundo color> <cantidad iteraciones> <escala>\n6:variación nodos y aristas sin archivo <nodos_minimo> <nodos_maximo> <aristas minimo> <variacion segundo color> <cantidad iteraciones> <escala>\n 7: grafo completo: <nodos_minimo> <nodos_maximo> <cantidad iteraciones> <escala>\n8: Testar grafo bipartito completo: <cantidad_nodos>\n");
		}

	}
	private static int sumatoria(int n){
		int res = 0;
		for(int i = 1;i<n;i++){
			res += i;
		}
		return res;
	}
	
	private static void prueba_especifica(){
		ArrayList<Nodo_Tester> l = new ArrayList<Nodo_Tester>(3);
		l.add(new Nodo_Tester(0,0));
		l.add(new Nodo_Tester(1,1));
		l.add(new Nodo_Tester(2,0));
		vincular_nodos(l,0,1);
		vincular_nodos(l,0,2);
		vincular_nodos(l,1,2);
		
		imprimir_a_archivo("renegado.txt",l,3,3,2);
		
		demostrar_correctitud_no_coloreable("renegado",ejecutar("renegado.txt"));
	}
}
