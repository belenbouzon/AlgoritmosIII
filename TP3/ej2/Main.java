import ../ej1/*;

public class Main {

	private int cantNodos;
	private ArrayList<Nodo_coloreable> grafo;

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
		// genera el .out con el mismo nombre que la entrada.
		// Escritor esc = new Escritor(args[0]);

		//while para mas de una instancia?
		reader.inicializar_lector();
		cantNodos = reader.cantNodos();
		grafo = reader.getGrafo();

		return listColoring(0);
	}

	private static ArrayList<Nodo_coloreable> listColoring(int id){
		//llamar recursivamente con el indice del nodo.
		Nodo_coloreable nodo = grafo[id];
		Iterator<Integer> it = nodo.iterador_de_colores();
	}

	private static void MostrarIndicaciones(int length) throws Exception{
		if (length < 1){
		    System.out.printf("Debe pasar el nombre del archivo de input como parametro. Ademas puede pasar el flag --tiempos despues del nombre de archivo para obtener las mediciones de tiempos\n");
		    System.out.printf("USO: java Main INPUT [--tiempos]\n");
			System.exit(0);
		}
   }
}