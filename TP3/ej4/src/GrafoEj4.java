import java.util.ArrayList;
import java.util.Set;

public class GrafoEj4 {
	private ArrayList<NodoConVecinos> _nodos;
	private int _cantidadDeColores;
	private Set<NodoConVecinos> _conflictos;
	
	public int cantConflictos(){
		return this._conflictos.size();
	}
	public GrafoEj4(Grafo otro){
		// Convierte un grafo del ej3 en un grafo del ej4
		
	}
	
	
	public ArrayList<NodoConVecinos> getNodos() 
	{
		return _nodos;
	}



	public int getCantidadDeColores() 
	{
		return _cantidadDeColores;
	}
	public void setCantidadDeColores(int cantidadDeColores) 
	{
		this._cantidadDeColores = cantidadDeColores;
	}
	
	
	


	
	/*
	public static void AgregarAristas(String nombreDeArchivo, int cantidadDeAristas, int cantNodoConVecinoss) throws IOException 
	{
		File grafoModificable = new File("C:\\Users\\Bel\\Documents\\GitHub\\AlgoritmosIII\\TP3\\ej3\\Ejercicio3\\bin\\"  + nombreDeArchivo);
		FileWriter fw1 = new FileWriter(grafoModificable, true);
		
		HashSet<Arista> aristas = Ej3Utils.GenerarAristas(cantidadDeAristas, cantNodoConVecinoss);
		Iterator<Arista> iteradorArista = aristas.iterator();
		while(iteradorArista.hasNext())
		{
			Arista arista = iteradorArista.next();
			iteradorArista.remove();
			fw1.append(String.valueOf(arista.desde) + " " + String.valueOf(arista.hasta) + "\n");
		}
		fw1.close();
	}
	*/
}
