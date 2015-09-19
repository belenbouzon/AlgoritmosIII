//package uba.algo3.kruskal;

public class Arista implements Comparable<Arista> {

   private int _nod1;
   private int _nod2;
   private int _peso;

   public Arista( int n1, int n2, int p ) {
      this._nod1 = n1;
      this._nod2 = n2;
      this._peso = p;
   }

   public final int peso() {
      return _peso;
   }

   public final int nodo1() {
      return _nod1;
   }

   public final int nodo2() {
      return _nod2;
   }

   public boolean lessThan( Arista ar ) {
      if (peso() != ar.peso()) {
         return peso() < ar.peso();
      }
      if (nodo1() != ar.nodo1()) {
         return nodo1() < ar.nodo1();
      }
      return nodo2() < ar.nodo2();
   }

   public int compareTo( Arista otherInstance ) {
      if (lessThan( otherInstance )) {
         return -1;
      } else if (otherInstance.lessThan( this )) {
         return 1;
      }

      return 0;
   }

}