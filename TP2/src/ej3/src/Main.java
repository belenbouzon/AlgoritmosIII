import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception{
		List<Pasillo> ps;
		int nodos;
		Grafo grafo;
		long time0;
		long time1;
		Boolean mideTiempos = false;

		MostrarIndicaciones(args.length);

		if (args.length > 1 && args[1].equalsIgnoreCase("--tiempos"))
		{
		   mideTiempos = true;
		}

		Lector lect = new Lector(args[0]);
		// genera el .out con el mismo nombre que la entrada.
		Escritor esc = new Escritor(args[0]);

		while (!lect.archivo_termino()) {
			ps = lect.LeerLista();
			if (lect.archivo_termino()) {
				break;
			}
			
			time0 = System.nanoTime();

			//SOLVE
			nodos = lect.CantIntersecciones();
			grafo = new Grafo(nodos, ps);
			esc.EscribirInt(grafo.kruskal());

			if(mideTiempos){
				time1 = System.nanoTime();
				time1 = time1-time0;
				System.out.printf("%d-%s\n", nodos, Long.toString(time1));
			}
			esc.NuevaLinea();
		}
		esc.Fin();
   }

   private static void MostrarIndicaciones(int length) throws Exception{
		if (length < 1){
		    System.out.printf("Debe pasar el nombre del archivo de input como parametro. Ademas puede pasar el flag --tiempos despues del nombre de archivo para obtener las mediciones de tiempos\n");
		    System.out.printf("USO: java Main INPUT [--tiempos]\n");
			System.exit(0);
		}
   }

}