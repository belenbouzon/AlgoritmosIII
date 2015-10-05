import java.util.ArrayList;
import org.junit.Test;

public class Tester2 {

	public static long promediar(ArrayList<Long> lista,int cantidad_de_veces){
			long res = 0;
			for(int i = 1;i<cantidad_de_veces;i++){
				res += lista.get(i)/100000;
			}
			return res;
		}
		
		@Test
		public static void testear(String archivo,ArrayList<Long> lista,int cantidad_de_iteraciones) throws Exception{
			//System.out.printf("%s\n", archivo);
			Lector recuperar_nodos = new Lector(archivo);
			for(int i = 0;i<cantidad_de_iteraciones;i++){
				long inicio = System.nanoTime();
				recuperar_nodos.procesar_entrada();
				Solucion sol = new Solucion(recuperar_nodos.primer_nodo(),recuperar_nodos.ultimo_nodo(),recuperar_nodos.cantidad_pisos()*recuperar_nodos.largo_pasillos());
				sol.calcular_segundos();
				long fin = System.nanoTime();
				lista.add(fin-inicio);
			}
		}
		public static void main (String [] entrada) throws Exception{
			int cantidad_de_archivos = Integer.parseInt(entrada[1]);
			int cantidad_de_iteraciones = Integer.parseInt(entrada[2]);
			//ArrayList<Long> lista = new ArrayList<Long>(cantidad_de_iteraciones);
			int numero = 1;
			int valor_eje_x = 10;
			for(int i = 1;i<=cantidad_de_archivos;i++){
				System.gc();
				ArrayList<Long> lista = new ArrayList<Long>(cantidad_de_iteraciones);
				String lote = entrada[0];
				String cadena = "";
				cadena = String.valueOf(numero);
				cadena= Integer.toString(numero);
				lote += "_";
				lote += cadena;
				lote += ".txt";
				numero++;
				valor_eje_x++;
				Tester2.testear(lote,lista,cantidad_de_iteraciones);
				System.out.printf("%d %d\n", valor_eje_x, Tester2.promediar(lista,cantidad_de_iteraciones));
			}
		}
}
