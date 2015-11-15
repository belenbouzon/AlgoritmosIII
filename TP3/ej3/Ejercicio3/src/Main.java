public class Main 
{
	public static void main(String[] args) throws Exception 
	{	
		GeneradorCasosDeTestsByNodos generador = new GeneradorCasosDeTestsByNodos(15);
		Lector lector = new Lector("15Nodos.out");	
		Grafo grafo = lector.MakeGraph();
		grafo = Coloring.MakeRainbow(grafo);
	}
}
