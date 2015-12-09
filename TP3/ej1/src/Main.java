import java.io.IOException;


public class Main {
	public static void main(String [] entrada) throws IOException{
		if(entrada.length==0){
			System.err.print("Integrese: <nombre_de_archivo>; si quiere una medición de tiempos ingrese --imprimirTiempos como segundo parámetro\n");
			return;
		}
		if(entrada.length==1 || !entrada[1].equal("--imprimirTiempos")){
			Lector lec = new Lector(entrada[0]);
			lec.inicializar_lector();
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_nodos(),lec.nodos_del_grafo());
			String solucion = res.obtener_resolucion();
			System.out.print(solucion);
			System.out.print("\n");
		}else{
			Lector lec = Lector.crear_lector_cargado(entrada[0]);
			long inicio = System.nanoTime();
			lec.inicializar_lector();
			Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_nodos(),lec.nodos_del_grafo());
			String solucion = res.obtener_resolucion();
			long fin = System.nanoTime();
			//System.out.print(solucion);
			//System.out.print("\n");
			System.out.print(Integer.toString(fin-inicio));
			System.out.print("\n");
		}
	}
}
