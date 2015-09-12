import java.util.LinkedHashSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Set;

public class Nodo{
	public int piso;
	public Set<Entry<Nodo,Integer>> aristas;
	public Nodo(int n,Set<Entry<Nodo,Integer>> ar){
		this.piso =n;
		this.aristas = ar;
	}
	public void agregar_arista(Nodo nod, int pos){
		SimpleEntry<Nodo,Integer> nuevo = new SimpleEntry<Nodo,Integer>(nod,pos);
		this.aristas.add(nuevo);
	}
	public Nodo(int n,Nodo nod,int pos){
		this.piso = n;
		this.aristas = new LinkedHashSet<Entry<Nodo,Integer>>();
		SimpleEntry<Nodo,Integer> nuevo = new SimpleEntry<Nodo,Integer>(nod,pos);
		this.aristas.add(nuevo);
	}
	public Nodo(int n){
		this.piso = n;
		this.aristas = new  LinkedHashSet<Entry<Nodo,Integer>>();
	}
}