public class Main 
{
	public static void main(String[] args) throws Exception 
	{	
		Lector lector = new Lector(args [0]);	
		Grafo grafo = lector.MakeGraph();
		
		grafo = Coloring.MakeRainbow(grafo);
		
		
	}

	
}
