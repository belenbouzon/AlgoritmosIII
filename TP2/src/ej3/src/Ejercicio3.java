import java.util.List;
//Esta clase se usa solo para correr los tests.
//para tiempos o leer de archivo usar Main.
public class Ejercicio3 {

	private List<Pasillo> pasillos;
	private int nodos;
	
	public Ejercicio3(int n, List<Pasillo> ps){
			pasillos = ps;
			nodos = n;
	}

   public int solve() {
	   Grafo g = new Grafo(nodos, pasillos);
	   return g.kruskal();
   }

}