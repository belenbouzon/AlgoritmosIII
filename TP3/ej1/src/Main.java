import java.io.IOException;


public class Main {
	public static void main(String [] entrada) throws IOException{
		if(entrada.length==0){
			System.err.print("error, ingrese nombre de archivo");
			return;
		}
		Lector lec = new Lector(entrada[0]);
		lec.inicializar_lector();
		Calculador_de_Coloracion_Ej1 res = new Calculador_de_Coloracion_Ej1(lec.cantidad_nodos(),lec.nodos_del_grafo());
		String solucion = res.obtener_resolucion();
		System.out.print(solucion);
		System.out.print("\n");
	}
}
