package algo3.tp3.ej4;

import algo3.tp3.ej3.GeneradorCasosDeTests;
import algo3.tp3.ej3.Grafo;
import algo3.tp3.ej3.Lector;

public class ExperimentosComplejidad {

	public static void main(String[] args) throws Exception {
		
		int colores;
		int n;
		int aristas;

		
		// vamos aumentando la cantidad de nodos
		System.out.println("### TESTS VECINDAD 1");
		System.out.println("Vamos aumentando la cantidad de nodos, manteniendo la cantidad de colores en 50. La cantidad de aristas para cada caso es máxima (grafo completo).");
		System.out.println("===========================");
		System.out.println("n,m,c,tiempo greedy,conflictos greedy,tiempo busqueda local v1, conflictos busqueda local v1");
		GeneradorCasosDeTests generador = new algo3.tp3.ej3.GeneradorCasosDeTests();
		
		
		
		colores = 50;
		for (n = 100; n<5000 ; n = (int) (n*1.1)){
			aristas = (int) (((long) (n)) * ((long) (n-1))/2); 
			String caso = generador.GenerarArchivoDeGrafoByCantColores(n, aristas, colores);
			ExperimentosComplejidad.resolverEImprimirTiempos(caso, 1, n, aristas, colores);
		}
		
		
		
		System.out.println("===========================");
		// vamos aumentando la cantidad de aristas
		System.out.println("### TESTS VECINDAD 1");
		System.out.println("Vamos aumentando la cantidad de aristas, manteniendo la cantidad de colores en 50 y la cantidad de nodos en "
				+ "2000.");
		System.out.println("===========================");
		System.out.println("n,m,c,tiempo greedy,conflictos greedy,tiempo busqueda local v1, conflictos busqueda local v1");
		
		colores = 50;
		n = 2000;
		for (aristas = 100; aristas<1999000 ; aristas = (int) (aristas*1.2)){
			for (int iteracion=0; iteracion<5; iteracion++){
				String caso = generador.GenerarArchivoDeGrafoByCantColores(n, aristas, colores);
				ExperimentosComplejidad.resolverEImprimirTiempos(caso, 1, n, aristas, colores);
			}
		}
		
		System.out.println("===========================");

		System.out.println("### TESTS VECINDAD 1");
		System.out.println("Vamos aumentando la cantidad de colores, manteniendo la cantidad de nodos en 1000 (grafo completo)");
		System.out.println("===========================");
		System.out.println("n,m,c,tiempo greedy,conflictos greedy,tiempo busqueda local v1, conflictos busqueda local v1");
		
		n = 500;
		aristas = (int) (n*(n-1))/2;
		for (colores = 1; colores <= n ; colores = (int) Math.ceil(colores*1.2)){
			for (int iteracion=0; iteracion<5; iteracion++){
				String caso = generador.GenerarArchivoDeGrafoByCantColores(n, aristas, colores);
				ExperimentosComplejidad.resolverEImprimirTiempos(caso, 1, n, aristas, colores);
			}
		}
		
		System.out.println("===========================");
		// vamos aumentando la cantidad de nodos
		System.out.println("### TESTS VECINDAD 2");
		System.out.println("Vamos aumentando la cantidad de nodos, manteniendo la cantidad de colores en 50. La cantidad de aristas para cada caso es máxima (grafo completo).");
		System.out.println("===========================");
		System.out.println("n,m,c,tiempo greedy,conflictos greedy,tiempo busqueda local v2, conflictos busqueda local v2");
		
		colores = 50;
		for (n = 100; n<5000 ; n = (int) (n*1.1)){
			aristas = (int) (((long) (n)) * ((long) (n-1))/2); 
			String caso = generador.GenerarArchivoDeGrafoByCantColores(n, aristas, colores);
			ExperimentosComplejidad.resolverEImprimirTiempos(caso, 2, n, aristas, colores);
			
		}
		
		
		
		System.out.println("===========================");
		// vamos aumentando la cantidad de aristas
		System.out.println("### TESTS VECINDAD 2");
		System.out.println("Vamos aumentando la cantidad de aristas, manteniendo la cantidad de colores en 50 y la cantidad de nodos en "
				+ "2000.");
		System.out.println("===========================");
		System.out.println("n,m,c,tiempo greedy,conflictos greedy,tiempo busqueda local v1, conflictos busqueda local v1");
		
		colores = 50;
		n = 2000;
		for (aristas = 100; aristas<1999000 ; aristas = (int) (aristas*1.2)){
			for (int iteracion=0; iteracion<5; iteracion++){
				String caso = generador.GenerarArchivoDeGrafoByCantColores(n, aristas, colores);
				ExperimentosComplejidad.resolverEImprimirTiempos(caso, 2, n, aristas, colores);
			}
		}
		
		System.out.println("===========================");

		System.out.println("### TESTS VECINDAD 2");
		System.out.println("Vamos aumentando la cantidad de colores, manteniendo la cantidad de nodos en 1000 (grafo completo)");
		System.out.println("===========================");
		System.out.println("n,m,c,tiempo greedy,conflictos greedy,tiempo busqueda local v1, conflictos busqueda local v1");
		
		n = 500;
		aristas = (int) (n*(n-1))/2;
		for (colores = 1; colores <= n ; colores = (int) Math.ceil(colores*1.2)){
			for (int iteracion=0; iteracion<5; iteracion++){
				String caso = generador.GenerarArchivoDeGrafoByCantColores(n, aristas, colores);
				ExperimentosComplejidad.resolverEImprimirTiempos(caso, 2, n, aristas, colores);
			}
		}
		
		System.out.println("===========================");


	}
	private static void resolverEImprimirTiempos(String caso, int vecindad, int n, int aristas, int colores) throws Exception{
		//Con estas tres lineas leemos el input, y ya en grafoResultante nos queda el grafo resuelto con goloso.
		Lector lector = new Lector(caso);
		Grafo grafoResultante = lector.MakeGraph(-1);
		long greedyT0 = System.nanoTime();
		grafoResultante.MakeRainbow();
		long greedyT1 = System.nanoTime();
		
		int greedyConflictos = algo3.tp3.ej3.Main.CalcularConflictos(grafoResultante);
		long tiempoGreedy = greedyT1 - greedyT0;
		
		GrafoEj4 convertido = new GrafoEj4(grafoResultante);
		long busquedaLocalT0 = System.nanoTime();
		
		if (vecindad == 1){
			convertido.ResolverConVecindad1();
		}else {
			convertido.ResolverConVecindad2();
		}
		
		
		long busquedaLocalT1 = System.nanoTime();
		
		long tiempoBusquedaLocal = busquedaLocalT1 - busquedaLocalT0;
		int busquedaLocalConflictos = convertido.getCantConflictos();
		
		System.out.println(Integer.toString(n) + "," +
				Integer.toString(aristas) + "," +
				Integer.toString(colores) + "," +
				Long.toString(tiempoGreedy) + "," +
				Integer.toString(greedyConflictos) + "," +
				Long.toString(tiempoBusquedaLocal) + "," +
				Integer.toString(busquedaLocalConflictos));
		
	}
}
