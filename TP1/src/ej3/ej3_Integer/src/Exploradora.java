import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class Exploradora {
	public int letra;
	public TreeSet<Integer> amistades;
	public Exploradora(int l,TreeSet<Integer> am){
		this.letra = l;
		this.amistades = am;
	}
	public Exploradora(){
		this.letra = (int)-1;
		this.amistades = new TreeSet<Integer>();
	}
	public boolean es_amiga_de(int exploradora){ //O(ln a) (1) (2)
		return this.amistades.contains(exploradora);
	}
	public static Comparator<Exploradora> comparador(){
		return new ComparadorExploradoras();
	}
	public Iterator<Integer> amigas_de(){
		return this.amistades.iterator();
	}
	public void aniadir_amiga(int nueva_amiga){ //O(ln a) (1) (2)
		this.amistades.add(nueva_amiga);
	}
	public void aniadir_grupo_de_amigas(Set<Integer> nuevas_amigas){ // ¿O(a ln a)?
		this.amistades.addAll(nuevas_amigas);
	}
}

// (1) la busqueda e inserción en un TreeSet es logaritmica
// (2) el conjunto de amigas de una exploradora a lo sumo contiene a elementos
