
public class Test_Complejidad_Extras {
	public static long[] peor_caso(int longitud_pasillos, int pisos_minimos,int pisos_maximo,int escala, int cantidad_iteraciones){
		long[] resultados = new long [(pisos_maximo-pisos_minimos)/escala + 1];
		for(int i = 0;i<resultados.length;i++){
			resultados[i] = 0;
		}
		String fin_parametros = " ";
		fin_parametros += Integer.toString(longitud_pasillos);
		String portales = "";
		
		for(int i = 0;i<pisos_minimos-1;i++){
			portales += Integer.toString(i);
			portales += " ";
			portales += Integer.toString(longitud_pasillos);
			portales += " ";
			portales += Integer.toString(i+1);
			portales += " 0;";
		}
		
		int N = pisos_minimos;
		int i_esimo = 0;
		while(N<=pisos_maximo){
			String parametros = Integer.toString(N);
			parametros += fin_parametros;
			
			portales += Integer.toString(N-1);
			portales += " ";
			portales += Integer.toString(longitud_pasillos);
			portales += " ";
			portales += Integer.toString(N);
			portales += " 0;";
			
			Lector lec = new Lector(parametros,portales);
			for(int i = 0;i<cantidad_iteraciones;i++){
				long inicio = System.nanoTime();
				lec.procesar_entrada();
				Solucion sol = new Solucion(lec.primer_nodo(),lec.ultimo_nodo());
				sol.calcular_segundos();
				long fin = System.nanoTime();
				
				resultados[i_esimo] += (fin-inicio)/100;
			}
			i_esimo++;
			N+= escala;
		}
		return resultados;
	}
	public static void main(String[] entrada) throws Exception{
		int longitud_pasillos = 400;
		int pisos_minimos = 1000;
		int pisos_maximo = 100000;
		int escala = 1000;
		int cantidad_iteraciones = 100;
		long[] res = peor_caso(longitud_pasillos,pisos_minimos,pisos_maximo,escala,cantidad_iteraciones);
		Escritor escr = new Escritor("peor_caso.txt");
		int pisos = pisos_minimos;
		for(int i = 0;i<res.length;i++){
			String linea = "";
			linea += Integer.toString(pisos);
			linea += " ";
			linea += Long.toString(res[i]);
			escr.EscribirLinea(linea);
			if(i!=res.length-1){
				escr.NuevaLinea();
			}
			pisos += escala;
		}
		escr.Fin();
	}
}
