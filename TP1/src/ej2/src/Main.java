import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		String[] array;
		int val;
		int mediana;
		int tam;
		Heap heap = new Heap();
		//leo el archivo.
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in) );
		  
		String line;
		while ( ( line = in.readLine() ) != null ) {
			array = line.split(" ");
			mediana = 0;
			tam = array.length;
			for(int i = 0; i < tam; i++){
				val = Integer.parseInt(array[i]);			
				//System.out.printf("val: %d \n", val);
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
