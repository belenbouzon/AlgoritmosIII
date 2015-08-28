import java.util.List;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws Exception{
		Solucion sol = new Solucion();
		String s = "prueba.txt";
		Lector lec = new Lector(s);
		String st = lec.leer_palabra();
		List<String> datos_de_entrada = new LinkedList<String>();
		while(st!=null){
			datos_de_entrada.add(st);
			st = lec.leer_palabra();
		}
		sol.generar_solucion(datos_de_entrada);
	}
}
