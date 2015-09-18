import java.util.LinkedHashSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Set;

public class Nodo{
	public int identificacion;
	public Set<Nodo> aristas_caminado;
	public Set<Nodo> aristas_teletranspoorte;
	public int posicion;
	/*public Nodo(int n,Set<Entry<Nodo,Integer>> ar){
		this.identificacion =n;
		this.aristas = ar;
	}*/
	public void agregar_arista(Nodo nod){
		this.aristas_caminado.add(nod);
	}
	public void agregar_punto_teletransporte(Nodo nod){
		this.aristas_teletranspoorte.add(nod);
	}
	public Nodo(int n,Nodo nod,int pos){
		this.identificacion = n;
		this.aristas_caminado = new LinkedHashSet<Nodo>();
		this.aristas_teletranspoorte = new LinkedHashSet<Nodo>();
		this.aristas_teletranspoorte.add(nod);
	}
	public Nodo(int n,int pos){
		this.identificacion = n;
		this.posicion = pos;
		this.aristas_caminado = new LinkedHashSet<Nodo>();
		this.aristas_teletranspoorte = new LinkedHashSet<Nodo>();
	}
}