package algo3.tp3.ej4;

import algo3.tp3.ej3.GeneradorCasosDeTests;
import algo3.tp3.ej3.Grafo;
import algo3.tp3.ej3.Lector;

public class ExperimentosComplejidad {

	public static void main(String[] args) throws Exception {
		
		int colores;
		int n;
		int aristas;
		GeneradorCasosDeTests generador = new algo3.tp3.ej3.GeneradorCasosDeTests();

		
		
		System.out.println("### TEST INCREMENTANDO NODOS (grafos completos)");
		System.out.println("Vamos aumentando la cantidad de nodos, manteniendo la cantidad de colores en 50. La cantidad de aristas para cada caso es máxima (grafo completo).");
		System.out.println("===========================");
		System.out.println("n,m,c,tiempo greedy,conflictos greedy,tiempo busqueda local v1, conflictos busqueda local v1,tiempo busqueda local v2, conflictos busqueda local v2");
		
		
		colores = 50;
		for (n = 100; n<3000 ; n = (int) (n*1.1)){
			// vamos aumentando la cantidad de nodos y formamos un grafo completo
			aristas = (int) (((long) (n)) * ((long) (n-1))/2); 
			String caso = generador.GenerarArchivoDeGrafoByCantColores(n, aristas, colores);
			ExperimentosComplejidad.resolverEImprimirTiempos(caso, n, aristas, colores);
		}
		
		
		System.out.println("===========================");
		// vamos aumentando la cantidad de aristas
		System.out.println("### TESTS INCREMENTANDO ARISTAS");
		System.out.println("Vamos aumentando la cantidad de aristas, manteniendo la cantidad de colores en 50 y la cantidad de nodos en "
				+ "2000.");
		System.out.println("===========================");
		System.out.println("n,m,c,tiempo greedy,conflictos greedy,tiempo busqueda local v1, conflictos busqueda local v1,tiempo busqueda local v2, conflictos busqueda local v2");
		
		
		colores = 50;
		n = 3000;
		for (aristas = 100000; aristas<2249250 ; aristas = (int) (aristas*1.2)){
			for (int iteracion=0; iteracion<5; iteracion++){
				String caso = generador.GenerarArchivoDeGrafoByCantColores(n, aristas, colores);
				ExperimentosComplejidad.resolverEImprimirTiempos(caso, n, aristas, colores);
			}
		}
		
		System.out.println("===========================");

		System.out.println("### TESTS INCREMENTANDO COLORES");
		System.out.println("Vamos aumentando la cantidad de colores, manteniendo la cantidad de nodos en 1000 (grafo completo)");
		System.out.println("===========================");
		System.out.println("n,m,c,tiempo greedy,conflictos greedy,tiempo busqueda local v1, conflictos busqueda local v1,tiempo busqueda local v2, conflictos busqueda local v2");
		
		n = 500;
		aristas = (int) (n*(n-1))/2;
		for (colores = 1; colores <= n ; colores = (int) Math.ceil(colores*1.2)){
			for (int iteracion=0; iteracion<5; iteracion++){
				String caso = generador.GenerarArchivoDeGrafoByCantColores(n, aristas, colores);
				ExperimentosComplejidad.resolverEImprimirTiempos(caso, n, aristas, colores);
			}
		}
		


	}
	private static void resolverEImprimirTiempos(String caso, int n, int aristas, int colores) throws Exception{
		//Con estas tres lineas leemos el input, y ya en grafoResultante nos queda el grafo resuelto con goloso.
		Lector lector = new Lector(caso);
		Grafo grafoResultante = lector.MakeGraph(-1);
		long greedyT0 = System.nanoTime();
		grafoResultante.MakeRainbow();
		long greedyT1 = System.nanoTime();
		
		int greedyConflictos = algo3.tp3.ej3.Main.CalcularConflictos(grafoResultante);
		long tiempoGreedy = greedyT1 - greedyT0;
		
		GrafoEj4 convertido = new GrafoEj4(grafoResultante);
		
		long busquedaLocalV1T0 = System.nanoTime();
		convertido.ResolverConVecindad1();
		long busquedaLocalV1T1 = System.nanoTime();
		
		long tiempoBusquedaLocalV1 = busquedaLocalV1T1 - busquedaLocalV1T0;
		int busquedaLocalConflictosV1 = convertido.getCantConflictos();
		
		convertido = new GrafoEj4(grafoResultante);
		long busquedaLocalV2T0 = System.nanoTime();
		convertido.ResolverConVecindad2();
		long busquedaLocalV2T1 = System.nanoTime();
		
		long tiempoBusquedaLocalV2 = busquedaLocalV2T1 - busquedaLocalV2T0;
		int busquedaLocalConflictosV2 = convertido.getCantConflictos();
		
		System.out.println(Integer.toString(n) + "," +
				Integer.toString(aristas) + "," +
				Integer.toString(colores) + "," +
				Long.toString(tiempoGreedy) + "," +
				Integer.toString(greedyConflictos) + "," +
				Long.toString(tiempoBusquedaLocalV1) + "," +
				Integer.toString(busquedaLocalConflictosV1) + "," +
				Long.toString(tiempoBusquedaLocalV2) + "," +
				Integer.toString(busquedaLocalConflictosV2));
		
	}
}
