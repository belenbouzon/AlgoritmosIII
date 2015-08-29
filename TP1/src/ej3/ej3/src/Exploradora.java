import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;


public class Exploradora {
	public char letra;
	private TreeSet<Character> amistades;
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
}
