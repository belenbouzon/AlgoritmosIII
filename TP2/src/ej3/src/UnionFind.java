//package uba.algo3.kruskal;

import java.util.ArrayList;

public class UnionFind {

   private ArrayList<Integer> _parent = new ArrayList<Integer>();
   private ArrayList<Integer> _rank = new ArrayList<Integer>();


    // COMPLETAR !
   public UnionFind( int n ) {
      // Crea un bosque de n nodos.
      // Cada nodo tiene rank 0 al principio.
      // Cada nodo es su propio padre el principio.
   }

   public final int findSet( int i ) {
      // Si el padre es el mismo nodo, devuelvo ese nodo.
      // Si no, llamo recursivamente hacia el padre, y actualizo el padre del
      // nodo.
   }

   public final boolean isSameSet( int i, int j ) {
      // Devuelve si 2 nodos pertenecen o no al mismo conjunto.
   }

   public final void unionSet( int i, int j ) {
      // Si no pertenecen ya al mismo conjunto, los uno.
      // El que tenga menor rank pasara a formar parte del que tenga mayor rank.
      // Si ambos tienen igual rank es lo mismo cual uno a cual, pero debo
      // aumentar el rank del que sea el padre.
}
