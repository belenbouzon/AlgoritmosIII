
public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//Con estas tres lineas leemos el input, y ya en grafoResultante nos queda el grafo resuelto con goloso.
		Lector lector = new Lector(args[0]);
		Grafo grafoResultante = lector.MakeGraph(-1);
		
				

		
		grafoResultante.MakeRainbow();
		
		GrafoEj4 convertido = new GrafoEj4(grafoResultante);
		
		System.out.println("Conversion finalizada");
		
		convertido.ResolverConVecindad1();
		
		
	}


}
