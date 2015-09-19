package uba.algo3.kruskal;

import java.util.ArrayList;

public class UnionFind {

   private ArrayList<Integer> _parent = new ArrayList<Integer>();
   private ArrayList<Integer> _rank = new ArrayList<Integer>();


    // COMPLETAR !
   public UnionFind( int n ) {
      // Crea un bosque de n nodos.
      this._parent = new ArrayList<Integer>(n);
      this._rank = new ArrayList<Integer>(n);
      // Cada nodo tiene rank 0 al principio.
      for(int i = 0;i<n;i++){
         this._parent.add(i);
         this._rank.add(0);
      }
      // Cada nodo es su propio padre el principio.
   }

   public final int findSet( int i ) {
      // Si el padre es el mismo nodo, devuelvo ese nodo.
      // Si no, llamo recursivamente hacia el padre, y actualizo el padre del
      // nodo.
      if(i==this._parent.get(i)){
         return i;
      }else{
         int padre = this.findSet(this._parent.get(i));
         this._parent.set(i,padre);
         return padre;
      }
   }

   public final boolean isSameSet( int i, int j ) {
      // Devuelve si 2 nodos pertenecen o no al mismo conjunto.
      return (this.findSet(i)==this.findSet(j));
   }

   public final void unionSet( int i, int j ) {
      // Si no pertenecen ya al mismo conjunto, los uno.
      // El que tenga menor rank pasara a formar parte del que tenga mayor rank.
      // Si ambos tienen igual rank es lo mismo cual uno a cual, pero debo
      // aumentar el rank del que sea el padre.
      int padre_i = this.findSet(i);
      int padre_j = this.findSet(j);
      if(this._rank.get(padre_i)<this._rank.get(padre_j)){
         this._parent.set(padre_i,padre_j);
      }else if(this._rank.get(padre_i)==this._rank.get(padre_j)){
         this._parent.set(padre_i,padre_j);
         this._rank.set(padre_i,this._rank.get(padre_i) + 1);
      }else{
         this._parent.set(padre_j,padre_i);
      }
}
}