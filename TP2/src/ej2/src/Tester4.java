import java.util.ArrayList;
import org.junit.Test;

public class Tester4 {
	public static long promediar(ArrayList<Long> lista,int cantidad_de_veces){
		long res = 0;
		for(int i = 1;i<cantidad_de_veces;i++){
			res += lista.get(i)/5;
		}
		return res;
	}
	
	@Test
	public static void testear(String parametros,String datos,ArrayList<Long> lista,int cantidad_de_veces) throws Exception{
		Lector recuperar_nodos = new Lector(parametros,datos);
		for(int i = 0;i<cantidad_de_veces;i++){
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
		//int cant = 0;
		GeneradorRandomDeEntradas gen = new GeneradorRandomDeEntradas(0,0,0);
		for(int j = 0;j<Integer.parseInt(entrada[0]);j++){
			gen.generar_lineas_rectas_pisos_movimiento();
		}
		for(int j = Integer.parseInt(entrada[0]);j<Integer.parseInt(entrada[1]); j++){
			ArrayList<Long> lista = new ArrayList<Long>(cantidad_de_veces);
			//for(int i = 1;i<=cantidad_de_veces;i++){
			String parametros = "";
			parametros += Integer.toString(j+1);
			parametros += " 0";
			Tester4.testear(parametros,gen.generar_lineas_rectas_pisos_movimiento().substring(1),lista,cantidad_de_veces);
			//}
			System.out.printf("%d %d\n",j, Tester4.promediar(lista,cantidad_de_veces));
		}
	}
}
