import java.util.LinkedHashSet;
import java.util.Set;

public class Nodo {
	
	public int identidad;
	public int cantidad_colores;
	public Set<Nodo> adyacentes;
	public ColoresPosibles colores;
	
	public Nodo(int id){
		this.identidad = id;
		this.cantidad_colores = 0;
		this.adyacentes = new LinkedHashSet<Nodo>();
		this.colores = new ColoresPosiblesEj1();
	}
}
