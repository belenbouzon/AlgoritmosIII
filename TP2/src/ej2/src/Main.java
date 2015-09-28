public class Main{
	public static void main(String [] entrada) throws Exception{
		Lector recuperar_nodos = new Lector(entrada[0]);
		long inicio = System.nanoTime();
		recuperar_nodos.procesar_entrada();
		Solucion sol = new Solucion(recuperar_nodos.primer_nodo(),recuperar_nodos.ultimo_nodo(),recuperar_nodos.cantidad_pisos()*recuperar_nodos.largo_pasillos());
		int res = sol.calcular_segundos();
		long fin = System.nanoTime();
		System.out.printf("%d\n",res);
		if(entrada.length>1 /*&& entrada[1]=="--enable-debbuger"*/){
			System.out.printf("%d\n", fin-inicio);
		}
	}
}