import java.util.ArrayList;

public class UnionFind {

   private ArrayList<Integer> _parent;
   private ArrayList<Integer> _rank;
   
   public UnionFind( int n ) {
	  // Crea un bosque de n nodos.
	  this._parent = new ArrayList<Integer>(n);
	  this._rank = new ArrayList<Integer>(n);
	  // Cada nodo tiene rank 0 al principio.
	  // Cada nodo es su propio padre al principio.
	  for(int i = 0; i < n; i++){
		  this._parent.add(i);
		  this._rank.add(0);
	  }  
   }

   public final int findSet( int i ) {
	// Si el padre es el mismo nodo, devuelvo ese nodo.
	// Si no, busco el padre del padre hasta llegar a la raiz
	// y actualizo el padre del nodo.
	int parent = this._parent.get(i);
	int element = i;
	while(parent != element){
		element = parent;
		parent = this._parent.get(element);
	}
	this._parent.set(i, parent);
	return parent;
   }

   public final boolean isSameSet( int i, int j ) {
	   // Devuelve si 2 nodos pertenecen o no al mismo conjunto.
	   int root_i = this.findSet(i);
	   int root_j = this.findSet(j);
	   return root_i == root_j;
   }

   public final void unionSet( int i, int j ) {
	  // El que tenga menor rank pasara a formar parte del que tenga mayor rank.
	  // Si ambos tienen igual rank es lo mismo cual uno a cual, pero debo
	  // aumentar el rank del que sea el padre.
	  int root_i = this.findSet(i);
	  int root_j = this.findSet(j);
	  int rank_i = this._rank.get(root_i);
	  int rank_j = this._rank.get(root_j);
	  
	  if(rank_i < rank_j){
	     this._parent.set(root_i, root_j);
	  }else{
	     this._parent.set(root_j, root_i);
	  }
	  
	  if(rank_i == rank_j){
		  this._rank.set(root_i, rank_i + 1);
	  }
   }
}
