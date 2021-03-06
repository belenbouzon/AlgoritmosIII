import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		int[] conj;
		int val;
		int mediana;
		int tam;
		Heap heap = new Heap();
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
			conj = lect.LeerConjunto();
			if (lect.archivo_termino()) {
				break;
			}
			mediana = 0;
			tam = conj.length;
			time0 = System.nanoTime();

			for(int i = 0; i < tam; i++){
				val = conj[i];
				if(val >= mediana){
					heap.insertMinHeap(val);
				}else{
					heap.insertMaxHeap(val);
				}
				
				heap.balancearHeaps();
				
				mediana = heap.calcularMediana();
				//imprimir solucion
				esc.EscribirInt(mediana);
			}

			if(mideTiempos){
				time1 = System.nanoTime();
				time1 = time1-time0;
				System.out.printf("%d-%s\n", tam, Long.toString(time1));
			}
			esc.NuevaLinea();
			heap.ClearHeap();
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
