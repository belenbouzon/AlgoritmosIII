import java.util.LinkedHashSet;
import java.util.Set;

public class Nodo_Coloreable extends Nodo_Abstracto {
	
	public int identidad;
	public int cantidad_colores;
	public Set<Nodo_Coloreable> adyacentes;
	public ColoresPosibles colores;
	
	public Nodo_Coloreable(int id){
		this.identidad = id;
		this.cantidad_colores = 0;
		this.adyacentes = new LinkedHashSet<Nodo_Coloreable>();
		this.colores = new ColoresPosiblesEj1();
	}

	@Override
	public void agregar_adyacentes(Nodo_Abstracto otro) {
		this.adyacentes.add((Nodo_Coloreable) otro);
		otro.adyacentes.add((Nodo_Coloreable)this);
	}
	
}
