import static org.junit.Assert.*;

import org.junit.Test;

public class Tester_correctitud {
	
	@Test
	public   void test_oficial_1(){
		System.out.print("test_oficial_1...");
		Lector lec = new Lector("10 10","0 10 10 1");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(21,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
	}
	@Test
	public   void test_oficial_2(){
		System.out.print("test_oficial_2...");
		Lector lec = new Lector("4 4","0 1 1 2; 1 2 3 1; 2 3 4 0; 3 4 2 1");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(18,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
	}
	@Test
	public   void encontre_un_atajo(){
		System.out.print("encontre_un_atajo...");
		Lector lec = new Lector("12 13","0 7 4 8; 0 2 5 1; 5 5 9 0; 9 8 7 2; 7 0 4 1; 4 9 11 4; 11 6 12 8");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(21,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
	}
	@Test
	public   void y_como_llegue_aqui(){
		System.out.print("y_como_llegue_aqui?...");
		Lector lec = new Lector("8 10"," 0 5 1 3;0 7 7 2; 1 4 3 8; 3 5 1 3;7 5 8 0");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(24,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
	}
	
	@Test
	public   void piensa_con_portales(){
		System.out.print("piensa_con_portales...");
		Lector lec = new Lector("8 10","0 0 8 10; 0 5 3 8; 3 9 5 0; 5 5 8 8");
		//Lector lec = new Lector("8 10","0 0 8 10");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(2,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
		
	}
	@Test
	public   void linea_recta(){
		System.out.print("linea recta...");
		Lector lec = new Lector("8 10","0 3 5 9; 5 9 1 4; 1 4 7 5; 7 5 8 7");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(14,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
	}
	
	@Test
	public   void alguien_quedo_en_el_medio(){
		System.out.print("alguien_quedo_en_el_medio?...");
		Lector lec = new Lector("8 10","0 3 2 0; 3 4 5 2; 5 1 4 4; 2 2 6 0; 4 1 5 9; 6 2 8 5");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(18,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
	}
	@Test
	public   void salidas_de_emergencia(){
		System.out.print("salidas_de_emergencia...");
		Lector lec = new Lector("8 10","1 0 0 10; 2 10 3 0; 1 10 2 0;3 10 4 0;5 10 6 0;5 0 4 10 ;6 10 7 0;7 10 8 0");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(106,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
		
	}
	@Test
	public   void la_venganza_de_los_ciclos(){
		System.out.print("la_venganza_de_los_ciclos...");
		Lector lec = new Lector("8 10","0 3 3 4; 3 5 6 7;3 5 0 8;6 7 8 4; 6 7 6 0; 8 4 6 0");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(16,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
	}
	@Test
	public   void no_quiero_caminar(){
		System.out.print("no_quiero_caminar...");
		Lector lec = new Lector("8 10","5 1 5 9; 5 10 7 0; 7 10 8 8;5 0 0 3; 7 2 7 8");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(21,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
	}
	@Test
	public   void cuello_botella(){
		System.out.print("cuello_botella...");
		Lector lec = new Lector("8 10","0 3 3 4; 2 1 3 4; 1 5 3 4; 8 7 3 4");
		lec.procesar_entrada();
		Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
		try{
			assertEquals(10,sol.calcular_segundos());
			System.out.printf("funciono\n");
		}catch(AssertionError e){
			System.out.printf("error: %s\n",e.getMessage());
		}
	}
	
}

