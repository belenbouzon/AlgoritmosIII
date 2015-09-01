import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class Exploradora {
	public char letra;
	public TreeSet<Character> amistades;
	public Exploradora(char l,TreeSet<Character> am){
		this.letra = l;
		this.amistades = am;
	}
	public Exploradora(){
		this.letra = (char)-1;
		this.amistades = new TreeSet<Character>();
	}
	public boolean es_amiga_de(char exploradora){
		return this.amistades.contains(exploradora);
	}
	public static Comparator<Exploradora> comparador(){
		return new ComparadorExploradoras();
	}
	public Iterator<Character> amigas_de(){
		return this.amistades.iterator();
	}
	public void aniadir_amiga(char nueva_amiga){
		this.amistades.add(nueva_amiga);
	}
	public void aniadir_grupo_de_amigas(Set<Character> nuevas_amigas){
		this.amistades.addAll(nuevas_amigas);
	}
}
