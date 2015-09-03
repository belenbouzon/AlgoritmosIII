public class Main {
	public static void main(String[] args) throws Exception{
        Tiempos.mideTiempos = false;
        if (args.length > 1 && args[1].equalsIgnoreCase("--tiempos")){
            Tiempos.mideTiempos = true;
        }
		Lector lec = new Lector(args[0]);
        String problema = lec.leer_palabra();
        while(problema != null){
            Solucion sol = new Solucion();
		    sol.generar_solucion(problema);
            // Arrgh, no hay free(sol) !!!
            problema = lec.leer_palabra();
        }
	}
}
