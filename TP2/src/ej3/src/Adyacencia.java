//package uba.algo3.kruskal;

////////////////////////// CLASE Adyacencia //////////////////////////

public class Adyacencia {

   private int _nod;
   private int _peso;

   public Adyacencia( int nod, int p ) {
      this._nod = nod;
      this._peso = p;
   }

   public final int peso() {
      return _peso;
   }

   public final int nodo() {
      return _nod;
   }
}