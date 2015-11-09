public class Main 
{
	public static void main(String[] args) throws Exception 
	{	
		GeneradorCasosDeTestsByNodos generador = new GeneradorCasosDeTestsByNodos(50);
		Lector lector = new Lector("50Nodos.out");	
		Grafo grafo = lector.MakeGraph();
		grafo = Coloring.MakeRainbow(grafo);
	}
}
