import java.util.HashSet;
import java.util.Set;


public class Nodo_Grafo_Dirigido {
	public int identidad;
	public Set<Nodo> adyacentes;
	public Nodo_Grafo_Dirigido(int id){
		this.identidad = id;
		this.adyacentes = new HashSet<Nodo>();
	}
}
