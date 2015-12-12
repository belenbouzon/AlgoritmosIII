package algo3.tp3.ej5;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import algo3.tp3.ej3.Escritor;
import algo3.tp3.ej3.GeneradorCasosDeTests;
import algo3.tp3.ej3.Grafo;
import algo3.tp3.ej3.Lector;
import algo3.tp3.ej3.Main;
import algo3.tp3.ej3.Nodo;
import algo3.tp3.ej4.GrafoEj4;


public class Tester {
	private static class Arista{
		public int x;
		public int y;
		public Arista(int a, int b){
			this.x = a;
			this.y = b;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			if(this.x>this.y){
				result = prime * result + x;
				result = prime * result + y;
				return result;
			}else{
				result = prime * result + y;
				result = prime * result + x;
				return result;
			}
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Arista other = (Arista) obj;
			if(this.x==other.x && this.y == other.y)
				return true;
			if(this.x==other.y && this.y == other.x)
				return true;
			return false;
		}
	}
	
	private static void GenerarTestCiclico(int cantDeNodosMaxima) throws Exception 
	{
		algo3.tp3.ej3.Main.CrearArchivosDeSalida();
		GeneradorCasosDeTests generador = new GeneradorCasosDeTests();
		
		int cantDeNodos = 1000;
		
		while(cantDeNodos <= cantDeNodosMaxima)
		{
			String nombreDeArchivo = generador.GenerarArchivoDeGrafoCiclico(cantDeNodos);	

			Lector lector = new Lector(nombreDeArchivo);
		
			long greedyTime0 = System.nanoTime();
			Grafo grafoResultante = lector.MakeGraph(cantDeNodos);
			grafoResultante.MakeRainbow();
			long greedyTime1 = System.nanoTime();
			int cantConflictosGreedy = algo3.tp3.ej3.Main.CalcularConflictos(grafoResultante);
		
			long localTime0 = System.nanoTime();
			int cantConflictosLocal = Tester.resolverConLocalyDevolverCantConflictos(" ", grafoResultante, 1);
			long localTime1  = System.nanoTime();
		
			algo3.tp3.ej3.Main.EscribirArchivoTiempos(cantDeNodos, greedyTime1-greedyTime0, localTime1-localTime0);
			algo3.tp3.ej3.Main.EscribirArchivoConflictos(cantDeNodos, cantConflictosGreedy, cantConflictosLocal);
			
			cantDeNodos += 1000;
		}
	}
	
	public static int cantidadDeConflictos(String entrada, String salida) throws IOException{
		BufferedReader archivoDeEntrada = new BufferedReader( new InputStreamReader( Tester.class.getResourceAsStream(entrada)));
		BufferedReader archivoDeSalida = new BufferedReader( new InputStreamReader( Tester.class.getResourceAsStream(salida)));
		String [] parametros = archivoDeEntrada.readLine().split(" ");
		int cantidadNodos = Integer.parseInt(parametros[0]);
		int cantidadAristas = Integer.parseInt(parametros[1]);
		int cantidadColores = Integer.parseInt(parametros[2]);
		int [] coloreo = new int[cantidadNodos];
		String [] colores = archivoDeSalida.readLine().split(" ");
		for(int i = 0;i<cantidadNodos;i++){
			coloreo[i] = Integer.parseInt(colores[i]);
		}
		colores = null;
		archivoDeSalida.close();
		archivoDeSalida = null;
		
		for(int i=0;i<cantidadNodos;i++){
			String [] nodo = archivoDeEntrada.readLine().split(" ");
			int cantidadColoresDeNodo = Integer.parseInt(nodo[0]);
			if(cantidadColoresDeNodo>cantidadColores){
				return -1;
			}
			boolean colorCorrecto = false;
			for(int j=0;j<cantidadColoresDeNodo;j++){
				if(Integer.parseInt(nodo[j])==i){
					colorCorrecto = true;
					break;
				}
			}
			if(!colorCorrecto){
				return -1;
			}
		}
		
		int conflictosEncontrados = 0;
		
		for(int i=0;i<cantidadAristas;i++){
			String [] arista = archivoDeEntrada.readLine().split(" ");
			if(coloreo[Integer.parseInt(arista[0])] == coloreo[Integer.parseInt(arista[1])]){
				conflictosEncontrados++;
			}
		}
		
		archivoDeEntrada.close();
		return conflictosEncontrados;
	}
	
	public static void generarAlAzar(String nombreSalida,int cantidadNodos,int cantidadAristas,int cantidadColores,boolean limitar) throws IOException{
		//System.out.print(nombreSalida+"\n");
		BufferedWriter grafico = new BufferedWriter( new FileWriter( "./" + nombreSalida) );
		grafico.write(Integer.toString(cantidadNodos) + " " + Integer.toString(cantidadAristas)+ " " +Integer.toString(cantidadColores)+ "\n");
		Random numeros = new Random();
		for(int i=0;i<cantidadNodos;i++){
			int cantidadColoresDeNodo;
			if(limitar){
				cantidadColoresDeNodo = cantidadColores;
			}else{
				cantidadColoresDeNodo = numeros.nextInt(cantidadColores) + 1;
			}
			grafico.write(Integer.toString(cantidadColoresDeNodo) + " ");
			for(int j=0;j<cantidadColoresDeNodo;j++){
				grafico.write(Integer.toString(numeros.nextInt(cantidadColores)));
				if(j!=cantidadColoresDeNodo-1){
					grafico.write(" ");
				}
			}
			grafico.write("\n");
		}
		HashSet<Arista> aristasUtilizadas = new HashSet<Arista>();
		
		int i = 0;
		
		int cantidadVecinos[] = new int [cantidadNodos];
		int indices[] = new int [cantidadNodos];
		for(int z=0;z<cantidadNodos;z++){
			cantidadVecinos[z] = 0;
			indices[z] = z;
		}
		
		Random numeros2 = new Random();
		while(i<cantidadAristas){
			Arista nueva = new Arista(numeros.nextInt(cantidadNodos),numeros2.nextInt(cantidadNodos));
			if(nueva.x!=nueva.y && !aristasUtilizadas.contains(nueva)){
				grafico.write(Integer.toString(nueva.x) + " " + Integer.toString(nueva.y) + "\n");
				aristasUtilizadas.add(nueva);
				i++;
				
				cantidadVecinos[nueva.x]++;
				cantidadVecinos[nueva.y]++;
				
				//Si el nodo alcanzo grado maximo no puede salir seleccionado
				if(cantidadVecinos[nueva.x]>= cantidadNodos - 1){
					indices[nueva.x] = indices[(nueva.x+1)%cantidadNodos];
				}
				if(cantidadVecinos[nueva.y]>= cantidadNodos - 1){
					indices[nueva.y] = indices[(nueva.y+1)%cantidadNodos];
				}
				
			}
		}
		grafico.close();
	}
	
	public static void testerAlAzar(int cantidadNodos,int cantidadAristas,int cantidadColores,int cantidadDesplazamientos,int escala,int cantidadIteraciones,boolean nodosFijos,boolean aristasFijas,boolean coloresFijos,boolean limitar) throws IOException{
		int cantidadActualNodos = cantidadNodos;
		int cantidadActualAristas = cantidadAristas;
		int cantidadActualColores = cantidadColores;
		for(int i= 0; i<=cantidadDesplazamientos;i+=escala){
			for(int j=0;j<cantidadIteraciones;j++){
				generarAlAzar(Integer.toString(cantidadActualNodos) + "_" + Integer.toString(cantidadActualAristas) + "_" + Integer.toString(cantidadActualColores) + "_" + Integer.toString(j) + ".out",cantidadActualNodos,cantidadActualAristas,cantidadActualColores,limitar);
			}
			if(!nodosFijos){
				cantidadActualNodos += escala;
			}
			if(!aristasFijas){
				cantidadActualAristas += escala;
			}
			if(!coloresFijos){
				cantidadActualColores += escala;
			}
		}
	}
	
	public static void generarGrafoBipartitoCompleto(String archivo,String [] resultados,int cantidadNodos,int cantidadColores,boolean limitarCantidad) throws Exception{
		int posicionDeResultado = 0;
		
		Escritor es;
		if(archivo!=null){
			es = new Escritor(archivo);
			es.EscribirLinea(Integer.toString(cantidadNodos) + " " + Integer.toString(cantidadNodos*cantidadNodos/4) + " " + Integer.toString(cantidadColores));
			es.EscribirLinea("\n");
		}else{
			es = null;
		}
		Random numeros = new Random();
		int colorComun1 = numeros.nextInt(cantidadColores);
		int colorComun2 = numeros.nextInt(cantidadColores);
		while(colorComun1==colorComun2){
			colorComun2 = numeros.nextInt(cantidadColores);
		}
		for(int i=0;i<cantidadNodos;i++){
			int cantidadColoresNodo;
			if(limitarCantidad){
				cantidadColoresNodo = cantidadColores;
			}else{
				cantidadColoresNodo = 2 + numeros.nextInt(cantidadColores-1);
			}
			StringBuilder expresionDeNodo = new StringBuilder();
			expresionDeNodo.append(cantidadColoresNodo);
			expresionDeNodo.append(" ");
			int posicionColor1 = numeros.nextInt(cantidadColoresNodo);
			int posicionColor2 = numeros.nextInt(cantidadColoresNodo);
			HashSet<Integer> coloresUsados = new HashSet<Integer>(); 
			coloresUsados.add(posicionColor1);
			coloresUsados.add(posicionColor2);
			for(int j=0;j<cantidadColoresNodo;j++){
				if(j==posicionColor1){
					expresionDeNodo.append(colorComun1);
				}else if(j==posicionColor2){
					expresionDeNodo.append(colorComun2);
				}else{
					int nuevoNumero = numeros.nextInt(cantidadColores);
					while(coloresUsados.contains(nuevoNumero)){
						nuevoNumero = numeros.nextInt(cantidadColores);
					}
					coloresUsados.add(nuevoNumero);
					expresionDeNodo.append(nuevoNumero);
				}
				if(j!=cantidadColoresNodo-1){
					expresionDeNodo.append(" ");
				}
			}
			if(archivo!=null){
				es.EscribirLinea(expresionDeNodo.toString());
				es.EscribirLinea("\n");
			}
			if(resultados!=null){
				resultados[posicionDeResultado] = expresionDeNodo.toString();
				posicionDeResultado++;
			}
		}
		
		for(int i= 0;i<cantidadNodos;i++){
			for(int j = i+1;j<cantidadNodos;j+=2){
				StringBuilder expresionDeArista = new StringBuilder();
				expresionDeArista.append(i);
				expresionDeArista.append(" ");
				expresionDeArista.append(j);
				
				if(archivo!=null){
					es.EscribirLinea(expresionDeArista.toString());
					es.EscribirLinea("\n");
				}
				if(resultados!=null){
					resultados[posicionDeResultado] = expresionDeArista.toString();
					posicionDeResultado++;
				}
			}
		}
		if(es!=null){
			es.Fin();
		}
	}

	private static int CantidadDeVecinosDeMiColor(Grafo grafo, Nodo nodoActual) 
	{
		int cantidadDeVecinosCopiones = 0;
		for (Nodo nodoVecino : grafo.getVecinosDe(nodoActual))
		{
			if (nodoVecino.getColor() == nodoActual.getColor())
				cantidadDeVecinosCopiones++;
		}
		return cantidadDeVecinosCopiones;		
	}
	
	private static int golosoConBipartitoCompleto(String [] entrada) throws NumberFormatException, Exception{
		boolean limitarCantidad;
		if(entrada[3].equals("VARIABLE")){
			limitarCantidad = false;
		}else{
			limitarCantidad = true;
		}
		int cantidad_nodos = Integer.parseInt(entrada[2]);
		generarGrafoBipartitoCompleto(entrada[1],null,cantidad_nodos,Integer.parseInt(entrada[3]),limitarCantidad);
		Lector lector = new Lector(entrada[1]);
		
		Grafo grafoResultante = lector.MakeGraph(cantidad_nodos*cantidad_nodos/4);
		grafoResultante.MakeRainbow();

		int conflictos = 0;
		for ( Nodo nodo : grafoResultante.getNodos() )
			conflictos += CantidadDeVecinosDeMiColor(grafoResultante, nodo);
		return conflictos;
	}
	
	private static void imprimirHelp(){
		System.out.print("\n0: nombre archivo,cantidad nodos, cantidad colores,limitar color (sino ingresar VARIABLE)\n"
				+ "1: Testear Bipartito\n"
				+ "2: <cantidadNodos> <cantidadAristas> <cantidadColores> <cantidadDesplazamientos> <escala> <cantidadIteraciones> --NodosFijos --AristasFijas --ColoresFijos --CantidadColoresMaximaSiempre\n"
				+ "3: <vecinidadUtilizada> <cantidadNodos> <cantidadColores> <cantidadDesplazamientos> <escala> --ColoresFijos --limitar\n"
				+ "4: <cantidadNodos> <cantidadAristas> <cantidadColores> <cantidadDesplazamientos> <escala> <cantidadIteraciones> --NodosFijos --AristasFijas --ColoresFijos --CantidadColoresMaximaSiempre\n"
				+ "5: <cantidadNodos> <cantidadAristas> <cantidadColores> <cantidadDesplazamientos> <escala> <cantidadIteraciones> --NodosFijos --AristasFijas --ColoresFijos --CantidadColoresMaximaSiempre\n"
				+ "\n<cantidadNodos>: valor inicial de cantidad de nodos\n"
				+ "..IDEM para aristas y colores\n"
				+ "<cantidadDesplazamientos>: valor máximo al que querés que llegue cantidadNodos/Aristas/Colores + el inicial\n"
				+ "<escala>: en cuánto incrementa los valores (de las tres cosas)\n"
				+ "<cantidadIteraciones>: cuántas veces corre con los mismos parámetros (porque cada vez genera un grafo al azar distinto)\n"
				+ "--XFijos: dejar ese valor fijo.");
	}
	
	public static void main(String[] entrada) throws NumberFormatException, Exception{
		
		if(entrada.length==0){
			imprimirHelp();
			return;
		}
		int primerParametro = Integer.parseInt(entrada[0]);
		if(primerParametro==0){
			System.out.print(golosoConBipartitoCompleto(entrada));
			System.out.print("\n");
		}else if(primerParametro==1){
			int cantIteraciones = Integer.parseInt(entrada[5]);
			double res = 0;
			for(int i = 0;i<cantIteraciones;i++){
				res += ((double)golosoConBipartitoCompleto(entrada))/((double)cantIteraciones);
			}
			System.out.print(res);
			System.out.print("\n");
		}else if(primerParametro==2 || primerParametro==5 || primerParametro==4){
			int cantidadNodos = Integer.parseInt(entrada[1]);
			int cantidadAristas = Integer.parseInt(entrada[2]);
			int cantidadColores = Integer.parseInt(entrada[3]);
			int cantidadDesplazamientos = Integer.parseInt(entrada[4]);
			int escala = Integer.parseInt(entrada[5]);
			int cantidadIteraciones = Integer.parseInt(entrada[6]);
			
			boolean nodosFijos =  false;
			boolean aristasFijas =  false;
			boolean coloresFijos =  false;
			boolean limitar = false;
			
			if(entrada.length>=7){
				if(entrada[6].equals("--NodosFijos")){
					nodosFijos = true;
				}
				if(entrada[6].equals("--AristasFijas")){
					aristasFijas = true;
				}
				if(entrada[6].equals("--ColoresFijos")){
					coloresFijos = true;
				}
				if(entrada[6].equals("--ColoreoMaximoSiempre")){
					limitar = true;
				}
			}
			
			if(entrada.length>=8){
				if(entrada[7].equals("--NodosFijos")){
					nodosFijos = true;
				}
				if(entrada[7].equals("--AristasFijas")){
					aristasFijas = true;
				}
				if(entrada[7].equals("--ColoresFijos")){
					coloresFijos = true;
				}
				if(entrada[7].equals("--ColoreoMaximoSiempre")){
					limitar = true;
				}
			}
			
			if(entrada.length>=9){
				if(entrada[8].equals("--NodosFijos")){
					nodosFijos = true;
				}
				if(entrada[8].equals("--AristasFijas")){
					aristasFijas = true;
				}
				if(entrada[8].equals("--ColoresFijos")){
					coloresFijos = true;
				}
				if(entrada[8].equals("--ColoreoMaximoSiempre")){
					limitar = true;
				}
			}
			
			if(entrada.length>=9){
				if(entrada[8].equals("--NodosFijos")){
					nodosFijos = true;
				}
				if(entrada[8].equals("--AristasFijas")){
					aristasFijas = true;
				}
				if(entrada[8].equals("--ColoresFijos")){
					coloresFijos = true;
				}
				if(entrada[8].equals("--ColoreoMaximoSiempre")){
					limitar = true;
				}
			}
			
			double[][] res2;
			
			if(primerParametro==2){
				double[][] res = Tester.ejecutarTest(cantidadNodos, cantidadAristas, cantidadColores, cantidadDesplazamientos, escala, cantidadIteraciones, nodosFijos, aristasFijas, coloresFijos, limitar,false);
				res2 = res;
			}else if(primerParametro==4){
				Tester.ejecutarTest_Beta(cantidadNodos, cantidadAristas, cantidadColores, cantidadDesplazamientos, escala, cantidadIteraciones, nodosFijos, aristasFijas, coloresFijos, limitar);
			}else{
				double[][] res = Tester.ejecutarTest(cantidadNodos, cantidadAristas, cantidadColores, cantidadDesplazamientos, escala, cantidadIteraciones, nodosFijos, aristasFijas, coloresFijos, limitar,true);
				res2 = res;
			}
			/*for(int h = 0; h<cantidadDesplazamientos;h++){
				System.out.print(Double.toString(res2[0][h]) + " " +  Double.toString(res2[1][h]) + " " + Double.toString(res2[2][h]) +"\n");
			}*/
		}else if(primerParametro==3){
			int vecinidadUtilizada = Integer.parseInt(entrada[1]);
			int cantidadNodos = Integer.parseInt(entrada[2]);
			int cantidadColores = Integer.parseInt(entrada[3]);
			int cantidadDesplazamientos = Integer.parseInt(entrada[4]);
			int escala = Integer.parseInt(entrada[5]);
			
			boolean coloresFijos =  false;
			boolean limitar = false;
			
			if(entrada.length>=6){
				if(entrada[5].equals("--ColoresFijos")){
					coloresFijos = true;
				}
				if(entrada[5].equals("--ColoreoMaximoSiempre")){
					limitar = true;
				}
			}
			
			if(entrada.length>=7){
				
				if(entrada[6].equals("--ColoresFijos")){
					coloresFijos = true;
				}
				if(entrada[6].equals("--ColoreoMaximoSiempre")){
					limitar = true;
				}
			}
			
			if(entrada.length>=8){
				
				if(entrada[7].equals("--ColoresFijos")){
					coloresFijos = true;
				}
				if(entrada[7].equals("--ColoreoMaximoSiempre")){
					limitar = true;
				}
			}
			
			if(entrada.length>=9){
				
				if(entrada[8].equals("--ColoresFijos")){
					coloresFijos = true;
				}
				if(entrada[8].equals("--ColoreoMaximoSiempre")){
					limitar = true;
				}
			}
			
			Tester.grafosBipartitosEj4(vecinidadUtilizada, cantidadNodos, cantidadColores, cantidadDesplazamientos, escala, coloresFijos, limitar);
			
		}else if(primerParametro==4){
			GenerarTestCiclico(Integer.parseInt(entrada[1]));
			System.out.print("Finalizado");
		}else{
			imprimirHelp();
		}
	}
	
	public static void imprimirColoracion(String salida,Grafo grafo) throws IOException{
		BufferedWriter archivoColoracion = new BufferedWriter( new FileWriter( "./" + salida) );
		for(Nodo nodo: grafo.getNodos()){
			archivoColoracion.write(Integer.toString(nodo.getColor()) + " ");
		}
		archivoColoracion.close();
	}
	
	public static void imprimirColoracion(String salida,ArrayList<Nodo> listaNodos) throws IOException{
		BufferedWriter archivoColoracion = new BufferedWriter( new FileWriter( "./" + salida) );
		for(Nodo nodo: listaNodos){
			archivoColoracion.write(Integer.toString(nodo.getColor()) + " ");
		}
		archivoColoracion.close();
	}
	
	public static Grafo resolverConGoloso(String entrada) throws Exception{
		Lector lector = new Lector("./" + entrada);
		Grafo grafoResultante = lector.MakeGraph(-1);
		grafoResultante.MakeRainbow();
		imprimirColoracion("G" + entrada,grafoResultante);
		return grafoResultante;
	}

	
	
	public static GrafoEj4 resolverConLocal(String entrada,Grafo grafoResultante,int vecinidad) throws Exception{
		
		GrafoEj4 convertido = new GrafoEj4(grafoResultante);		
		//System.out.println(String.valueOf(convertido.getCantConflictos()));
		
		if(vecinidad==1){
			convertido.ResolverConVecindad1();
			//imprimirColoracion("L1" + entrada,grafoResultante);
		}else{
			convertido.ResolverConVecindad2();
			//imprimirColoracion("L2" + entrada,grafoResultante);
		}
		return convertido;
		//System.out.println(String.valueOf(convertido.getCantConflictos()));
	}
	
	public static int resolverConLocalyDevolverCantConflictos(String entrada,Grafo grafoResultante,int vecinidad) throws Exception
	{
		
		GrafoEj4 convertido = new GrafoEj4(grafoResultante);
		
		if(vecinidad==1)
			convertido.ResolverConVecindad1();
		else
			convertido.ResolverConVecindad2();
		
		return convertido.getCantConflictos();
	}
	
	public static double[][] ejecutarTest(int cantidadNodos,int cantidadAristas,int cantidadColores,int cantidadDesplazamientos,int escala,int cantidadIteraciones,boolean nodosFijos,boolean aristasFijas,boolean coloresFijos,boolean limitar,boolean complejidad) throws Exception{
		Tester.testerAlAzar(cantidadNodos, cantidadAristas, cantidadColores, cantidadDesplazamientos, escala, cantidadIteraciones, nodosFijos, aristasFijas, coloresFijos, limitar);
		double [][] res = new double [3] [cantidadDesplazamientos];
		
		int cantidadActualNodos = cantidadNodos; 
		int cantidadActualAristas = cantidadAristas;
		int cantidadActualColores = cantidadColores;
		
		int z = 0;
		
		for(int i=0;i<=cantidadDesplazamientos;i+=escala){
			for(int j =0;j<cantidadIteraciones;j++){
				
				String entrada = Integer.toString(cantidadActualNodos) + "_" + Integer.toString(cantidadActualAristas) + "_" + Integer.toString(cantidadActualColores) + "_" + Integer.toString(j) + ".out";
				String salida = "G" + entrada;
				
				long inicio = System.nanoTime();
				Grafo grafo = resolverConGoloso(entrada);
				long fin = System.nanoTime();
				
				if(!complejidad){
					res[0][z] += Tester.cantidadDeConflictos(entrada,salida)/cantidadIteraciones;
				}else{
					res[0][z] += (fin-inicio)/cantidadIteraciones;
				}
				
				salida = "L1" + entrada;
				inicio = System.nanoTime();
				resolverConLocal(entrada,grafo,1);
				fin = System.nanoTime();
				
				if(!complejidad){
					res[1][z] += Tester.cantidadDeConflictos(entrada,salida)/cantidadIteraciones;
				}else{
					res[1][z] += (fin-inicio)/cantidadIteraciones;
				}
				
				salida = "L2" + entrada;
				inicio = System.nanoTime();
				resolverConLocal(entrada,grafo,2);
				fin = System.nanoTime();
				if(!complejidad){
					res[2][z] += Tester.cantidadDeConflictos(entrada,salida)/cantidadIteraciones;
				}else{
					res[2][z] += (fin-inicio)/cantidadIteraciones;
				}
			}
			
			System.out.print(Double.toString(res[0][z]) + " " +  Double.toString(res[1][z]) + " " + Double.toString(res[2][z]) +"\n");
			
			z++;
			if(!nodosFijos){
				cantidadActualNodos += escala;
			}
			if(!aristasFijas){
				cantidadActualAristas += escala;
			}
			if(!coloresFijos){
				cantidadActualColores += escala;
			}
			
		}
		return res;
	}
	
	public static void ejecutarTest_Beta(int cantidadNodos,int cantidadAristas,int cantidadColores,int cantidadDesplazamientos,int escala,int cantidadIteraciones,boolean nodosFijos,boolean aristasFijas,boolean coloresFijos,boolean limitar) throws Exception{
		Tester.testerAlAzar(cantidadNodos, cantidadAristas, cantidadColores, cantidadDesplazamientos, escala, cantidadIteraciones, nodosFijos, aristasFijas, coloresFijos, limitar);
		
		System.out.print("<cantidad nodos> <cantidad aristas> <cantidad colores> <conflictosGoloso> <tiempoGoloso> <conflictosVecindad1> <tiempoVecindad1> <conflictosVecindad2> <tiempoVecindad2>");
		
		int cantidadActualNodos = cantidadNodos; 
		int cantidadActualAristas = cantidadAristas;
		int cantidadActualColores = cantidadColores;
		
		
		for(int i=0;i<=cantidadDesplazamientos;i+=escala){
			double conflictosGoloso = 0;
			double conflictosVecindad1 = 0;
			double conflictosVecindad2 = 0;
			
			double tiempoGoloso = 0;
			double tiempoVecindad1 = 0;
			double tiempoVecindad2 = 0;
			
			for(int j =0;j<cantidadIteraciones;j++){
				
				String entrada = Integer.toString(cantidadActualNodos) + "_" + Integer.toString(cantidadActualAristas) + "_" + Integer.toString(cantidadActualColores) + "_" + Integer.toString(j) + ".out";
				long inicio = System.nanoTime();
				Grafo grafo = resolverConGoloso(entrada);
				long fin = System.nanoTime();
				
				conflictosGoloso += ((double)algo3.tp3.ej3.Main.CalcularConflictos(grafo))/cantidadIteraciones;
				tiempoGoloso += ((double) (fin-inicio))/cantidadIteraciones;
				
				inicio = System.nanoTime();
				GrafoEj4 vecindad1 = resolverConLocal(entrada,grafo,1);
				fin = System.nanoTime();
				
				conflictosVecindad1 += ((double)vecindad1.getCantConflictos())/cantidadIteraciones;
				tiempoVecindad1 += ((double) (fin-inicio))/cantidadIteraciones;
				
				inicio = System.nanoTime();
				GrafoEj4 vecindad2 = resolverConLocal(entrada,grafo,2);
				fin = System.nanoTime();
				
				conflictosVecindad1 += ((double)vecindad2.getCantConflictos())/cantidadIteraciones;
				tiempoVecindad1 += ((double) (fin-inicio))/cantidadIteraciones;
				
			}
			
			System.out.print(Integer.toString(cantidadActualNodos) + Integer.toString(cantidadActualAristas) + Integer.toString(cantidadActualColores) + Double.toString(conflictosGoloso) + " " + Double.toString(tiempoGoloso) + " " + Double.toString(conflictosVecindad1) + " " + Double.toString(tiempoVecindad1) + " " + Double.toString(conflictosVecindad2) + " " + Double.toString(tiempoVecindad2));
			
			if(!nodosFijos){
				cantidadActualNodos += escala;
			}
			if(!aristasFijas){
				cantidadActualAristas += escala;
			}
			if(!coloresFijos){
				cantidadActualColores += escala;
			}
			
		}
	}
	
	public static void grafosBipartitosEj4(int vecinidadUtilizada,int nodosIniciales,int cantColoresIniciales,int cantidadDesplazamientos,int escala,boolean coloresFijos,boolean limitar) throws Exception{
		
		int n = nodosIniciales ;
		int colores = cantColoresIniciales;
		
		for(int i=0;i<cantidadDesplazamientos;i+=escala){
			n = nodosIniciales + i;
			if(!coloresFijos){
				colores = cantColoresIniciales + i;
			}
			
			String caso = "caso.txt";
			Tester.generarGrafoBipartitoCompleto(caso,null,n, colores,limitar);
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
			if(vecinidadUtilizada==1){
				convertido.ResolverConVecindad1();
			}else{
				convertido.ResolverConVecindad2();
			}
			long busquedaLocalT1 = System.nanoTime();
			
			long tiempoBusquedaLocal = busquedaLocalT1 - busquedaLocalT0;
			int busquedaLocalConflictos = convertido.getCantConflictos();
			
			int aristas;
			if(n%2==0){
				aristas = (n/2)*(n/2);
			}else{
				aristas = (n/2)*(n/2 + 1);
			}
			
			System.out.println(Integer.toString(n) + "," +Integer.toString(aristas) + "," +
					Integer.toString(colores) + "," +
					Long.toString(tiempoGreedy) + "," +
					Integer.toString(greedyConflictos) + "," +
					Long.toString(tiempoBusquedaLocal) + "," +
					Integer.toString(busquedaLocalConflictos));
		}
	}
	
}
