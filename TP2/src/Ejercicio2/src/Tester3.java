import java.util.ArrayList;
import org.junit.Test;

public class Tester3 {
	public static long promediar(ArrayList<Long> lista,int cantidad_de_veces){
		long res = 0;
		for(int i = 1;i<cantidad_de_veces;i++){
			res += lista.get(i)/5;
		}
		return res;
	}
	
	@Test
	public static void testear(String parametros,String datos,ArrayList<Long> lista) throws Exception{
		Lector recuperar_nodos = new Lector(parametros,datos);
		for(int i = 0;i<2;i++){
			long inicio = System.nanoTime();
			recuperar_nodos.procesar_entrada();
			Solucion sol = new Solucion(recuperar_nodos.primer_nodo(),recuperar_nodos.ultimo_nodo());
			sol.calcular_segundos();
			long fin = System.nanoTime();
			lista.add(fin-inicio);
		}
	}
	public static void main (String [] entrada) throws Exception{
		int cantidad_de_veces = Integer.parseInt(entrada[2]);
		for(int j = Integer.parseInt(entrada[0]);j<Integer.parseInt(entrada[1]); j++){
			ArrayList<Long> lista = new ArrayList<Long>(cantidad_de_veces*2);
			for(int i = 1;i<=cantidad_de_veces;i++){
				GeneradorRandomDeEntradas gen = new GeneradorRandomDeEntradas(70,80,j);
				Tester3.testear("70 80",gen.imprimir_entrada_a_string(),lista);
			}
			System.out.printf("%d %d\n",j, Tester3.promediar(lista,cantidad_de_veces));
		}
	}
}
