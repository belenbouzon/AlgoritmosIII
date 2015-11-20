import java.util.ArrayList;
import java.util.ListIterator;

public class Main {

	private static int cantNodos;
	private static ArrayList<Nodo_Coloreable_ej2> grafo;
	private static ArrayList<Nodo_Coloreable> grafo2colores;
	private static String solucion;

	public static void main(String[] args) throws Exception{
		long time0;
		long time1;
		Boolean mideTiempos = false;

		MostrarIndicaciones(args.length);

		if (args.length > 1 && args[1].equalsIgnoreCase("--tiempos"))
		{
		   mideTiempos = true;
		}

		Lector reader = new Lector(args[0]);

		//while para mas de una instancia?
		time0 = System.nanoTime();
		reader.inicializar_lector();
		cantNodos = reader.cantNodos();
		grafo = reader.getGrafo();
		grafo2colores = reader.getGrafo2colores();

		listColoring(0);
		
		if(mideTiempos){
			time1 = System.nanoTime();
			time1 = time1-time0;
			System.out.printf("%d-%s\n", cantNodos, Long.toString(time1));
		}
		//imprimir solucion
		// genera el .out con el mismo nombre que la entrada.
		Escritor writer = new Escritor(args[0]);
		writer.EscribirSolucion(solucion);
		//System.out.printf("%s\n", solucion);
		return;
	}

	private static boolean listColoring(int id){
		if (id == cantNodos) {
			//llamo a 2listColoring
			solucion = new Calculador_de_Coloracion_Ej1(cantNodos, grafo2colores).obtener_resolucion();
			return !solucion.equals("X");
		}

		Nodo_Coloreable_ej2 nodo = grafo.get(id);
		ListIterator<Integer> it = nodo.colores.listIterator();
		ColoresPosibles coloresSeleccionados = grafo2colores.get(id).colores;
		id++; //aumento el id para llamar al proximo nodo.

		if(nodo.cantidad_colores < 2){
			//si hay un solo color directamente llamo a listColoring.
			coloresSeleccionados.set_color(0, it.next());
			return listColoring(id);
		}else{		
			while(it.hasNext()){
				int color1 = it.next();
				ListIterator<Integer> it2 = nodo.colores.listIterator(it.nextIndex());
				//Agrego color1 a la lista de colores
				coloresSeleccionados.set_color(0, color1);
				while(it2.hasNext()){
					int color2 = it2.next();
					//Agrego color2
					coloresSeleccionados.set_color(1, color2);
					if(listColoring(id)){
						return true;
					}
				}
			}
		}
		return false;
	}

	private static void MostrarIndicaciones(int length) throws Exception{
		if (length < 1){
		    System.out.printf("Debe pasar el nombre del archivo de input como parametro. Ademas puede pasar el flag --tiempos despues del nombre de archivo para obtener las mediciones de tiempos\n");
		    System.out.printf("USO: java Main INPUT [--tiempos]\n");
			System.exit(0);
		}
   }
}
