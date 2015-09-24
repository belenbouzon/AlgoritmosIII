import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.junit.Test;

public class TestEj3 {
	
	private int _nodos;

   @Test
   public void fileWritingRunTest() throws IOException {
      // Con este test se lee el archivo de entrada proporcioando por la catedra 
      //    y se genera la salida
      BufferedReader is = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream( "Tp2Ej3.in" ) ) );
      BufferedWriter os = new BufferedWriter( new FileWriter( getClass().getResource( "" ).getPath() + "Tp2Ej3.test.out" ) );
      
      String line;
      while ( ( line = is.readLine() ) != null ) {
         os.append( run( line ) ).append( '\n' );
      }
      os.close();
      
   }

   @Test
   public void fileTestingRunTest() throws IOException {
      // Con este test se compara un archivo de entrada con el formato de la catedra 
      //    contra otro archivo con valores esperados
      BufferedReader source  = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream( "Tp2Ej3.in" ) ) );
      BufferedReader control = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream( "Tp2Ej3.out" ) ) );
      
      String line;
      while ( ( line = source.readLine() ) != null ) {
         assertEquals( control.readLine(), run( line ) );
      }
      
   }

   private String run( String line ) {
	   List<Pasillo> pasillos = parsePasillos(line);
	   return String.valueOf( new Ejercicio3(_nodos, pasillos).solve() );
   }

   private List<Pasillo> parsePasillos( String line ) {
      ArrayList<Pasillo> pasillos = new ArrayList<Pasillo>();
      Pasillo p;

      StringTokenizer st = new StringTokenizer( line, ";" );
      while ( st.hasMoreTokens() ) {
         StringTokenizer pasillo = new StringTokenizer( st.nextToken(), " " );
         p = new Pasillo(Integer.parseInt(pasillo.nextToken()), 
        		 		 Integer.parseInt(pasillo.nextToken()), 
        		 		 Integer.parseInt(pasillo.nextToken()));
         pasillos.add(p);
			//calculo cant de nodos.
			if(p.getExtremo1() > _nodos){
				_nodos = p.getExtremo1();
			}
			if(p.getExtremo2() > _nodos){
				_nodos = p.getExtremo2();
			}
      }
      _nodos++;

      return pasillos;
   }


   
   @Test
   public void test0() {
      ArrayList<Pasillo> pasillos = new ArrayList<Pasillo>();
      pasillos.add( new Pasillo( 1, 2, 3 ) );
      pasillos.add( new Pasillo( 2, 3, 3 ) );
      pasillos.add( new Pasillo( 3, 1, 3 ) );
      int nodos = 4;

      assertEquals( 3, new Ejercicio3(nodos, pasillos).solve() );
   }

   @Test
   public void test1() {
      ArrayList<Pasillo> pasillos = new ArrayList<Pasillo>();
      pasillos.add( new Pasillo( 1, 2, 8 ) );
      pasillos.add( new Pasillo( 1, 5, 70 ) );
      pasillos.add( new Pasillo( 1, 4, 63 ) );
      pasillos.add( new Pasillo( 2, 3, 53 ) );
      pasillos.add( new Pasillo( 2, 5, 54 ) );
      pasillos.add( new Pasillo( 3, 4, 10 ) );
      pasillos.add( new Pasillo( 3, 5, 12 ) );
      pasillos.add( new Pasillo( 4, 5, 22 ) );
      int nodos = 6;

      assertEquals( 52, new Ejercicio3(nodos, pasillos).solve() );
   }
   
   @Test
   public void test2() {
      ArrayList<Pasillo> pasillos = new ArrayList<Pasillo>();
      pasillos.add( new Pasillo(1, 5, 10) );
      pasillos.add( new Pasillo(1, 2, 11) );
      pasillos.add( new Pasillo(2, 3, 2) );
      pasillos.add( new Pasillo(4, 3, 30) );
      pasillos.add( new Pasillo(4, 5, 5) );
      pasillos.add( new Pasillo(1, 4, 50) );
      pasillos.add( new Pasillo(4, 2, 25) );
      pasillos.add( new Pasillo(1, 3, 45) );
      pasillos.add( new Pasillo(5, 2, 1) );
      pasillos.add( new Pasillo(3, 5, 12) );
      int nodos = 6;

      assertEquals( 59, new Ejercicio3(nodos, pasillos).solve() );
   }
}
