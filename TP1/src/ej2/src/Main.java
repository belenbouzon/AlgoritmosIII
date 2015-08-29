import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		int[] conj;
		int val;
		int mediana;
		int tam;
		Heap heap = new Heap();

		if (args.length < 1){
            System.out.printf("Debe pasar el nombre del archivo de input como parámetro. Además puede pasar el flag --tiempos después del nombre de archivo, seguido de un número entero, para correr las mediciones de tiempos esa cantidad de veces\n");
            System.out.printf("USO: java Main INPUT [--tiempos N]\n");
        }

		Lector lect = new Lector(args[0]);

		while (!lect.archivo_termino()) {
			conj = lect.LeerConjunto();
			if (lect.archivo_termino()) {
				return;
			}
			mediana = 0;
			tam = conj.length;
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
				System.out.printf("%d", mediana);
				if(i + 1 != tam){
					System.out.printf(" ");
				}
			}
			System.out.printf("\n");
			heap.ClearHeap();
		}
	}
}
