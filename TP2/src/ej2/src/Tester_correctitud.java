import static org.junit.Assert.assertEquals;
import org.junit.Test;
//import org.junit.internal.ArrayComparisonFailure;


public class Tester_correctitud {
	@Test
	public static void encontre_un_atajo(){
		Lector lec = new Lector("12 13","0 7 4 8; 0 5 5 1; 5 5 9 0; 9 8 7 2; 7 0 4 1; 4 9 11 4; 11 6 12 8;"); // 7+2+1+2+2+2+5
		//Lector lec = new Lector("12 13","4 9 11 4; 11 6 12 8; 0 7 4 8"); // 7+2+1+2+2+2+5
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo(),0);
		assertEquals(21,sol.calcular_segundos());
	}
	@Test
	public static void y_como_llegue_aqui(){
		Lector lec = new Lector("8 10","0 5 7 2; 0 7 1 3; 1 4 3 8; 3 5 1 3;7 5 8 0");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo(),0);
		assertEquals(23,sol.calcular_segundos());
	}
	
	@Test
	public static void piensa_con_portales(){
		Lector lec = new Lector("8 10","0 0 8 10; 0 5 3 8; 3 9 5 0; 5 5 8 8");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo(),0);
		assertEquals(2,sol.calcular_segundos());
	}
	@Test
	public static void linea_recta(){
		Lector lec = new Lector("8 10","0 3 5 9; 5 9 1 4; 1 4 7 5; 7 5 8 7");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo(),0);
		assertEquals(14,sol.calcular_segundos());
	}
	
	@Test
	public static void alguien_quedo_en_el_medio(){
		Lector lec = new Lector("8 10","0 3 2 0; 3 4 5 2; 5 1 4 4; 2 2 6 0; 4 1 5 9; 6 2 8 5");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo(),0);
		assertEquals(18,sol.calcular_segundos());
	}
	@Test
	public static void salidas_de_emergencia(){
		Lector lec = new Lector("8 10","1 0 0 10; 2 10 3 0; 1 10 2 0;3 10 4 0;5 10 6 0;5 0 4 10 ;6 10 7 0;7 10 8 0");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo(),0);
		assertEquals(106,sol.calcular_segundos());
		
	}
	@Test
	public static void la_venganza_de_los_ciclos(){
		Lector lec = new Lector("8 10","0 3 3 4; 3 5 6 7;3 5 0 8;6 7 8 4; 6 7 6 0");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo(),0);
		assertEquals(10,sol.calcular_segundos());
	}
	
	public static void main(String [] entrada){
		System.out.print("encontre_un_atajo...");
		Tester_correctitud.encontre_un_atajo();
		System.out.printf("funciono\n");
		/*System.out.print("y_como_llegue_aqui?");
		Tester_correctitud.y_como_llegue_aqui();
		System.out.printf("funciono\n");*/
		System.out.print("piensa_con_portales...");
		Tester_correctitud.piensa_con_portales();
		System.out.printf("funciono\n");
		System.out.print("linea recta...");
		Tester_correctitud.linea_recta();
		System.out.printf("funciono\n");
		System.out.print("alguien_quedo_en_el_medio?...");
		Tester_correctitud.alguien_quedo_en_el_medio();
		System.out.printf("funciono\n");
		System.out.print("salidas_de_emergencia...");
		Tester_correctitud.salidas_de_emergencia();
		System.out.printf("funciono\n");
		System.out.print("la_venganza_de_los_ciclos...");
		Tester_correctitud.la_venganza_de_los_ciclos();
		System.out.printf("funciono\n");
	}
}
