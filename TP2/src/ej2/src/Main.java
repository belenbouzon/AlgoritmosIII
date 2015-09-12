public class Main{
	public static void main(String [] entrada) throws Exception{
		Lector recuperar_nodos = new Lector(entrada[0]);
		Solucion sol = new Solucion(recuperar_nodos.primer_nodo(),recuperar_nodos.ultimo_nodo());
		int res = sol.calcular_segundos();
		System.out.printf("%d\n",res);
	}
}