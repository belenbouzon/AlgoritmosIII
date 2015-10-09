public class Main{
	public static void main(String [] entrada) throws Exception{
		Lector recuperar_nodos = new Lector(entrada[0]);
		long inicio = System.nanoTime();
		recuperar_nodos.procesar_entrada();
		Solucion sol = new Solucion(recuperar_nodos.primer_nodo(),recuperar_nodos.ultimo_nodo());
		int res = sol.calcular_segundos();
		long fin = System.nanoTime();
		System.out.printf("%d\n",res);
		if(entrada.length>1 /*&& entrada[1]=="--enable-debbuger"*/){
			System.out.printf("%d\n", fin-inicio);
			
		Escritor escritor = new Escritor("RES"+entrada[0]);
		escritor.Append(String.valueOf(recuperar_nodos.cantPortales) + " ");
		escritor.EscribirString(String.valueOf(fin-inicio));
		escritor.NuevaLinea();
		escritor.Fin();
		}
	}
}