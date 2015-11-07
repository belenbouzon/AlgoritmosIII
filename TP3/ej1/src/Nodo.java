import java.util.LinkedHashSet;
import java.util.Set;

public class Nodo {
	public int identidad;
	public int cantidad_colores;
	public Set<Nodo> adyacentes;
	public Indice_colores colores;
	public boolean pintado;
	public int color_fijado;
	public Nodo(int id){
		this.identidad = id;
		this.cantidad_colores = 0;
		this.adyacentes = new LinkedHashSet<Nodo>();
		this.colores = new Indice_colores_ej1();
		this.pintado = false;
		this.color_fijado = -1;
	}
}
