import java.util.ArrayList;
import org.junit.Test;

public class Tester {
	public static long promediar(ArrayList<Long> lista,int cantidad_de_veces){
		long res = 0;
		for(int i = 1;i<cantidad_de_veces;i++){
			res += lista.get(i)/5;
		}
		return res;
	}
	
	@Test
	public static void testear(String archivo,ArrayList<Long> lista) throws Exception{
		Lector recuperar_nodos = new Lector(archivo);
		for(int i = 0;i<2;i++){
			long inicio = System.nanoTime();
			recuperar_nodos.procesar_entrada();
			Solucion sol = new Solucion(recuperar_nodos.primer_nodo(),recuperar_nodos.ultimo_nodo());
			sol.calcular_segundos();
			long fin = System.nanoTime();
			lista.add(fin-inicio);
			if(i%100==0){
				System.gc();
			}
		}
	}
	public static void main (String [] entrada) throws Exception{
		int cantidad_de_veces = Integer.parseInt(entrada[1]);
		ArrayList<Long> lista = new ArrayList<Long>(cantidad_de_veces*2);
		int numero = 1;
		//for(int i = 1;i<=9;i++){
		for(int i = 1;i<=cantidad_de_veces;i++){
			String lote = entrada[0];
			String cadena = "";
			cadena = String.valueOf(numero);
			cadena= Integer.toString(numero);
			//lote += "_1.txt";
			lote += "_";
			lote += cadena;
			lote += ".txt";
			numero++;
			Tester.testear(lote,lista);
		}
		//System.gc();
		
		/*
		lote = entrada[0];
		lote += "_2.txt";
		Tester.testear(lote,lista);
		//System.gc();
		
		lote = entrada[0];
		lote += "_3.txt";
		Tester.testear(lote,lista);
		//System.gc();
		
		
		lote = entrada[0];
		lote += "_4.txt";
		Tester.testear(lote,lista);
		//System.gc();
		
		lote = entrada[0];
		lote += "_5.txt";
		Tester.testear(lote,lista);
		//System.gc();
		
		lote = entrada[0];
		lote += "_6.txt";
		Tester.testear(lote,lista);
		//System.gc();
		
		lote = entrada[0];
		lote += "_7.txt";
		Tester.testear(lote,lista);
		//System.gc();
		
		lote = entrada[0];
		lote += "_8.txt";
		Tester.testear(lote,lista);
		//System.gc();
		
		lote = entrada[0];
		lote += "_9.txt";
		Tester.testear(lote,lista);
		//System.gc();
		*/
		System.out.printf("%d\n", Tester.promediar(lista,cantidad_de_veces));
	}
}
