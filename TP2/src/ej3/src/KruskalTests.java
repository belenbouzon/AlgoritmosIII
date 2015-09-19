//package uba.algo3.kruskal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Test;

public class KruskalTests {

   // Esta funcion es solo para testeo, podria quitarse luego
   public static boolean hayArista( ArrayList<Arista> ars, int nod1, int nod2, int peso ) {
      boolean ret = false;
      for (Arista a : ars) {
         if (a.peso() == peso) {
            if (( a.nodo1() == nod1 && a.nodo2() == nod2 ) || ( a.nodo1() == nod2 && a.nodo2() == nod1 )) {
               ret = true;
               break;
            }
         }
      }

      return ret;
   }

   ////////////////////////// Tests //////////////////////////

   @Test
   public void testGrafo() {
      GrafoListaAdy graf = new GrafoListaAdy( 5 );
      assertEquals( 5, graf.cantNodos() );
      assertEquals( 0, graf.cantAristas() );
      graf.agregarArista( 1, 0, 2 );
      assertEquals( 5, graf.cantNodos() );
      assertEquals( 1, graf.cantAristas() );
      graf.agregarArista( 2, 0, 0 );
      assertEquals( 5, graf.cantNodos() );
      assertEquals( 2, graf.cantAristas() );
      graf.agregarArista( 3, 0, 3 );
      assertEquals( 5, graf.cantNodos() );
      assertEquals( 3, graf.cantAristas() );
      graf.agregarArista( 0, 2, 4 ); // Esta no deberia agregar
      assertEquals( 5, graf.cantNodos() );
      assertEquals( 3, graf.cantAristas() );
      graf.agregarArista( 2, 1, 1 );
      assertEquals( 5, graf.cantNodos() );
      assertEquals( 4, graf.cantAristas() );
      graf.agregarArista( 4, 1, 4 );
      assertEquals( 5, graf.cantNodos() );
      assertEquals( 5, graf.cantAristas() );
      graf.agregarArista( 4, 3, 5 );
      assertEquals( 5, graf.cantNodos() );
      assertEquals( 6, graf.cantAristas() );

      assertEquals( 5, graf.cantNodos() );
      assertEquals( 6, graf.cantAristas() );

      assertEquals( 3, graf.grado( 0 ) );
      assertEquals( 3, graf.grado( 1 ) );
      assertEquals( 2, graf.grado( 2 ) );
      assertEquals( 2, graf.grado( 3 ) );
      assertEquals( 2, graf.grado( 4 ) );

      assertTrue( graf.sonAdyacentes( 0, 1 ) );
      assertTrue( graf.sonAdyacentes( 0, 2 ) );
      assertTrue( graf.sonAdyacentes( 0, 3 ) );
      assertFalse( graf.sonAdyacentes( 0, 4 ) );
      assertTrue( graf.sonAdyacentes( 1, 2 ) );
      assertFalse( graf.sonAdyacentes( 1, 3 ) );
      assertTrue( graf.sonAdyacentes( 1, 4 ) );
      assertFalse( graf.sonAdyacentes( 2, 3 ) );
      assertFalse( graf.sonAdyacentes( 2, 4 ) );
      assertTrue( graf.sonAdyacentes( 3, 4 ) );

      ArrayList<Arista> aristas = graf.aristas();

      assertEquals( graf.cantAristas(), aristas.size() ); // Mismo cardinal

      for (Arista a : aristas) { // Todas las de la lista estan en el grafo
         assertTrue( graf.sonAdyacentes( a.nodo1(), a.nodo2() ) );
      }

      for (int i = 0; i < graf.cantNodos(); i++) { // Todas las del grafo estan
                                                   // en la lista
         for (Adyacencia ad : graf.adyacentes( i )) {
            assertTrue( hayArista( aristas, i, ad.nodo(), ad.peso() ) );
            assertTrue( hayArista( aristas, ad.nodo(), i, ad.peso() ) );
         }
      }
   }

   @Test
   public void testGrafoKruskal() {
      GrafoListaAdy graf1 = new GrafoListaAdy( 1 );
      assertEquals( 0, graf1.kruskal() );

      GrafoListaAdy graf2 = new GrafoListaAdy( 2 );
      graf2.agregarArista( 0, 1, 4 );
      assertEquals( 4, graf2.kruskal() );

      GrafoListaAdy graf3 = new GrafoListaAdy( 3 );
      graf3.agregarArista( 0, 1, 8 );
      graf3.agregarArista( 0, 2, 2 );
      graf3.agregarArista( 1, 2, 4 );
      assertEquals( 6, graf3.kruskal() );

      GrafoListaAdy graf4 = new GrafoListaAdy( 4 );
      graf4.agregarArista( 0, 1, 3 );
      graf4.agregarArista( 0, 2, 5 );
      graf4.agregarArista( 0, 3, 1 );
      graf4.agregarArista( 2, 1, 4 );
      graf4.agregarArista( 3, 1, 9 );
      graf4.agregarArista( 2, 3, 7 );
      assertEquals( 8, graf4.kruskal() );

      GrafoListaAdy graf5 = new GrafoListaAdy( 5 );
      graf5.agregarArista( 0, 1, 6 );
      graf5.agregarArista( 0, 2, 6 );
      graf5.agregarArista( 0, 3, 5 );
      graf5.agregarArista( 0, 4, 12 );
      graf5.agregarArista( 2, 1, 3 );
      graf5.agregarArista( 3, 1, 1 );
      graf5.agregarArista( 4, 1, 1 );
      graf5.agregarArista( 2, 3, 10 );
      graf5.agregarArista( 2, 4, 8 );
      graf5.agregarArista( 3, 4, 8 );
      assertEquals( 10, graf5.kruskal() );

      GrafoListaAdy graf6 = new GrafoListaAdy( 6 );
      graf6.agregarArista( 0, 1, 4 );
      graf6.agregarArista( 0, 2, 1 );
      graf6.agregarArista( 0, 3, 1 );
      graf6.agregarArista( 0, 4, 6 );
      graf6.agregarArista( 0, 5, -1 );
      graf6.agregarArista( 1, 2, 5 );
      graf6.agregarArista( 1, 3, 7 );
      graf6.agregarArista( 1, 4, 1 );
      graf6.agregarArista( 1, 5, 5 );
      graf6.agregarArista( 2, 3, 1 );
      graf6.agregarArista( 2, 4, 6 );
      graf6.agregarArista( 2, 5, -4 );
      graf6.agregarArista( 3, 4, 8 );
      graf6.agregarArista( 3, 5, 0 );
      graf6.agregarArista( 4, 5, 14 );
      assertEquals( 0, graf6.kruskal() );
   }

   ////////////////////////// --- //////////////////////////

   public static int Main() {

      // --- Oreon ---
      int T;
      T = Integer.parseInt( ConsoleInput.readToWhiteSpace( true ) );
      while (T-- != 0) {
         int n;
         // Cantidad de ciudades
         n = Integer.parseInt( ConsoleInput.readToWhiteSpace( true ) );
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               int soldados;
               // Leo la cantidad de soldados para cada tunel.
               soldados = Integer.parseInt( ConsoleInput.readToWhiteSpace( true ) );

            }
         }
      }

      return 0;
   }
}
