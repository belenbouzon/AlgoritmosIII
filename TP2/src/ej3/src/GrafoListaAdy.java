//package uba.algo3.kruskal;
import java.util.ArrayList;
import java.util.Collections;

////////////////////////// CLASE GrafoListaAdy //////////////////////////

public class GrafoListaAdy {

   private ArrayList<ArrayList<Adyacencia>> _graf = new ArrayList<ArrayList<Adyacencia>>();
   private int _nodos;
   private int _aristas;

   // O(n)
   public GrafoListaAdy( int n ) {
      _graf = new ArrayList<ArrayList<Adyacencia>>( n );
      _nodos = n;
      _aristas = 0;
   }

   // O(n + m)
   public GrafoListaAdy( int n, ArrayList<Arista> aristas ) {
      _graf = new ArrayList<ArrayList<Adyacencia>>( n );
      _nodos = n;
      _aristas = 0;
      for (Arista a : aristas) {
         agregarAristaRapida( a );
      }
   }

   // O(1) --- Toma una arista
   public final void agregarAristaRapida( Arista ars ) {
      Adyacencia ady1 = new Adyacencia( ars.nodo1(), ars.peso() );
      Adyacencia ady2 = new Adyacencia( ars.nodo2(), ars.peso() );

      _graf.get( ars.nodo1() ).add( ady2 );
      _graf.get( ars.nodo2() ).add( ady1 );

      _aristas++;
   }

   // O(1) --- Toma los datos de una arista
   public final void agregarAristaRapida( int nod1, int nod2, int p ) {
      Adyacencia ady1 = new Adyacencia( nod1, p );
      Adyacencia ady2 = new Adyacencia( nod2, p );

      _graf.get( nod1 ).add( ady2 );
      _graf.get( nod2 ).add( ady1 );

      _aristas++;
   }

   // O(sonAdyacentes)
   public final void agregarArista( Arista ars ) {
      if (!sonAdyacentes( ars.nodo1(), ars.nodo2() ) && ars.nodo1() != ars.nodo2()) {
         agregarAristaRapida( ars );
      }
   }

   // O(sonAdyacentes)
   public final void agregarArista( int nod1, int nod2, int p ) {
      if (!sonAdyacentes( nod1, nod2 ) && nod1 != nod2) {
         agregarAristaRapida( nod1, nod2, p );
      }
   }

   // O(1)
   public final ArrayList<Adyacencia> adyacentes( int nod ) {
      return _graf.get( nod );
   }

   // O(1)
   public final int grado( int nod ) {
      return adyacentes( nod ).size();
   }

   // O(min{d(nod1), d(nod2)})
   public final boolean sonAdyacentes( int nod1, int nod2 ) {
      boolean ret = false;
      if (grado( nod1 ) < grado( nod2 )) {
         for (Adyacencia ad : adyacentes( nod1 )) {
            if (ad.nodo() == nod2) {
               ret = true;
               break;
            }
         }
      } else {
         for (Adyacencia ad : adyacentes( nod2 )) {
            if (ad.nodo() == nod1) {
               ret = true;
               break;
            }
         }
      }
      return ret;
   }

   // O(n + m)
   public final ArrayList<Arista> aristas() {
      ArrayList<Arista> ret = new ArrayList<Arista>();
      for(int nod=0; nod<_graf.size(); nod++){
      //for (ArrayList<Adyacencia> nodAdy : _graf) {
         //int nod = nodAdy - _graf.get( 0 );
    	  ArrayList<Adyacencia> nodAdy = _graf.get( nod );
         for (Adyacencia ad : nodAdy) {
            if (ad.nodo() > nod) {
               Arista ars = new Arista( nod, ad.nodo(), ad.peso() );
               ret.add( ars );
            }
         }
      }
      return ret;
   }

   // O(1)
   public final int cantNodos() {
      return _nodos;
   }

   // O(1)
   public final int cantAristas() {
      return _aristas;
   }

   // O(m.log(m))
   public final int kruskal() {
      // COMPLETAR !
	   return 0;
      // COMPLETAR !
   }
}
