public class Main {
	public static void main(String[] args) throws Exception{
		Solucion sol = new Solucion();
		Lector lec = new Lector(args[0]);
		sol.generar_solucion(lec.leer_palabra());
	}
}
