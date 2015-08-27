import java.util.PriorityQueue;

public class Heap {
	private PriorityQueue<Integer> minHeap;
	private PriorityQueue<Integer> maxHeap;
	
	public Heap(){
		this.minHeap = new PriorityQueue<Integer>();
		this.maxHeap = new PriorityQueue<Integer>(11, new MyComparator());
	}
	
	public void insertMinHeap(int val){
		this.minHeap.add(val);
	}
	
	public void insertMaxHeap(int val){
		this.maxHeap.add(val);
	}
	
	public void balancearHeaps(){
		//si la diferencia absoluta de tamaños es mayor a uno, tengo que rebalancear.
		//si es 0, 1 o -1 esta bien.
		int head;
		int balance = this.minHeap.size() - this.maxHeap.size();
		if(balance < -1){
			 //maxHeap es mas grande
			head = this.maxHeap.poll();
			this.minHeap.add(head);
		}
		
		if(balance > 1){
			//minHeap es mas grande
			head = this.minHeap.poll();
			this.maxHeap.add(head);
		}
	}
	
	public int calcularMediana(){
		int balance = this.minHeap.size() - this.maxHeap.size();
		int res = 0;
		if(balance == 0){
			res = this.minHeap.peek() + this.maxHeap.peek();
			res = res/2;
		}else if(balance > 0){
			res = this.minHeap.peek();
		}else{
			res = this.maxHeap.peek();
		}
		return res;
	}
	
	public void ClearHeap(){
		this.minHeap.clear();
		this.maxHeap.clear();
	}
}
