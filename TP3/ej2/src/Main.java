import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Main {

	private static int cantNodos;
	private static ArrayList<Nodo_Coloreable_ej2> grafo;
	private static ArrayList<Nodo_Coloreable> grafo2colores;
	private static String solucion;
	private static Boolean podas = false;

	public static void main(String[] args) throws Exception{
		long time0;
		long time1;
		Boolean mideTiempos = false;
		String variable = "";

		MostrarIndicaciones(args.length);

		if (args.length > 2 && args[1].equalsIgnoreCase("--tiempos"))
		{
		   mideTiempos = true;
		   // variable selecciona que se imprime: cant nodos, aristas o colores.
		   variable = args[2];
		   // sirve para medir performance sin podas.
		   if (args.length > 3 && args[3].equals("-p")) {
		   		podas = true;
		   }
		}else{
			// si no mide tiempos, siempre corre con podas
			podas = true;
		}

		LectorEj2 reader = new LectorEj2(args[0], podas);

		//while para mas de una instancia?
		time0 = System.nanoTime();
		boolean puedeHaberSol = reader.inicializar_lector();
		cantNodos = reader.cantNodos();
		grafo = reader.getGrafo();
		grafo2colores = reader.getGrafo2colores();
		//si no hay podas asume que siempre puede haber solucion.
		solucion = "X";
		if(puedeHaberSol){
			//Ordenamos los nodos para que queden primero los de menor cantidad de colores.
			if (podas) {
				Collections.sort(grafo, new ColorComparator());
			}
			
			listColoring(0);
		}
				
		if(mideTiempos){
			time1 = System.nanoTime();
			time1 = time1-time0;
			int cant = cantNodos;
			if (variable.equals("Aristas")) {
				cant = reader.cantAristas();
			}else if(variable.equals("Colores")){
				cant = reader.cantColores();
			}
			System.out.printf("%d-%s\n", cant, Long.toString(time1));
		}else{
			//imprimir solucion
			// genera el .out con el mismo nombre que la entrada.
			Escritor writer = new Escritor(args[0]);
			writer.EscribirSolucion(solucion);
		}
		return;
	}

	private static boolean listColoring(int count){
		if (count == cantNodos) {
			//llamo a 2listColoring
			solucion = new Calculador_de_Coloracion_Ej1(cantNodos, grafo2colores).obtener_resolucion();
			boolean x = !solucion.equals("X");
			if (!podas) {x = false;}
			return x;
		}
		//Count se utiliza para recorrer la lista en orden.
		Nodo_Coloreable_ej2 nodo = grafo.get(count);
		ListIterator<Integer> it = nodo.colores.listIterator();
		//uso nodo.identidad porque las listas no estan en el mismo orden.
		ColoresPosibles coloresSeleccionados = grafo2colores.get(nodo.identidad).colores;
		count++; //aumento el count para llamar al proximo nodo.

		while(it.hasNext()){
			int color1 = it.next();
			//Agrego color1 a la lista de colores
			coloresSeleccionados.set_color(0, color1);
			// ListIterator<Integer> it2 = nodo.colores.listIterator(it.nextIndex());
			if(it.hasNext()){
				int color2 = it.next();
				//Agrego color2
				coloresSeleccionados.set_color(1, color2);
			}
			// cuando no hay podas nunca entra al if y recorre todo el arbol de soluciones.
			if(listColoring(count)){
				return true;
			}
		}
		return false;
	}

	private static void MostrarIndicaciones(int length) throws Exception{
		if (length < 1){
		    System.out.printf("Debe pasar el nombre del archivo de input como parametro. Ademas puede pasar el flag --tiempos despues del nombre de archivo para obtener las mediciones de tiempos\n");
		    System.out.printf("USO: java Main INPUT [--tiempos <variable> -p]\n");
		    System.out.printf("variable: Nodos, Aristas o Colores. Se utiliza con --tiempos para que imprima la cantidad de la variable.\n");
		    System.out.printf("-p indica que se quiere correr el algoritmo con podas\n");
		    System.out.printf("Por default se corre con podas si no se usa el flag --tiempos\n");
			System.exit(0);
		}
   }
}
