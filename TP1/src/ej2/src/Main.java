
public class Main {
	public static void main(String[] args) throws Exception{
		//leer valores
		int[] arr = new int[4];
		arr[0] = 1;
		arr[1] = 3;
		arr[2] = 9;
		arr[3] = 4;
		int val;
		int mediana = 0;
		Heap heap = new Heap();
		
		for(int i = 0; i < 4; i++){
			val = arr[i];			
			
			if(val >= mediana){
				heap.insertMinHeap(val);
			}else{
				heap.insertMaxHeap(val);
			}
			
			heap.balancearHeaps();
			
			mediana = heap.calcularMediana();
			//imprimir solucion
			System.out.printf("mediana actual: %d \n", mediana);
		}
	}
}
