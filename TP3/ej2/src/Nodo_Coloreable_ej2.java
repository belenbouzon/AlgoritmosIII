import java.util.ArrayList;
//import java.util.LinkedHashSet;
import java.util.Set;

public class Nodo_Coloreable_ej2 extends Nodo_Abstracto {

	public int identidad;
	public int cantidad_colores;
	public Set<Nodo_Coloreable> adyacentes;
	public ArrayList<Integer> colores;

	public Nodo_Coloreable_ej2(int id, int cantColores){
		this.identidad = id;
		this.cantidad_colores = cantColores;
		//this.adyacentes = new LinkedHashSet<Nodo_Coloreable>();
		this.colores = new ArrayList<Integer>(cantColores);
	}
	
	@Override
	public void agregar_adyacentes(Nodo_Abstracto otro) {}
}
