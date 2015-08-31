import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		int[] conj;
		int val;
		int mediana;
		int tam;
		Heap heap = new Heap();
		// Si no tiene el flag --tiempos, se ejecuta solo una vez.
		int cantEjec = 1;
		int j;
        long time0;
        long time1;
        Boolean mideTiempos = false;

        MostrarIndicaciones(args.length);

        if (args.length > 1 && args[1].equalsIgnoreCase("--tiempos"))
        {
            mideTiempos = true;
            cantEjec = Integer.parseInt(args[2]);
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
			//Para correr varias veces el algoritmo y testear performance.
			j = 0;
			while(j < cantEjec){
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
					if(!mideTiempos){
						esc.EscribirInt(mediana);
					}
				}

				if(mideTiempos){
					time1 = System.nanoTime();
					//separo las instancias del problema.
					if(j == 0){
						esc.EscribirInt(j);
						esc.NuevaLinea();
					}
					esc.EscribirLong(time1-time0);
				}
				esc.NuevaLinea();
				heap.ClearHeap();
				j++;
	        }
		}
        esc.Fin();
	}
	
	private static void MostrarIndicaciones(int length){
		if (length < 1){
		    System.out.printf("Debe pasar el nombre del archivo de input como parametro. Ademas puede pasar el flag --tiempos despues del nombre de archivo, seguido de un numero entero, para correr las mediciones de tiempos esa cantidad de veces\n");
		    System.out.printf("USO: java Main INPUT [--tiempos N]\n");
		}
	}
}
