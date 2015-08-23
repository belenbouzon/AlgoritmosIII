import java.util.TreeMap;
import java.util.TreeSet;

public class Amistades {
	private TreeMap<Character, TreeSet<Character> > registro_amistades;
	TreeSet<Character> ultimo_conjunto_utilizado;
	char ultima_exploradora_utilizada;
	public boolean es_amiga_de(char exploradora1,char exploradora2){
		if(exploradora1==this.ultima_exploradora_utilizada){
			return this.ultimo_conjunto_utilizado.contains(exploradora2);
		}else{
			this.ultimo_conjunto_utilizado = this.registro_amistades.get(exploradora1);
			this.ultima_exploradora_utilizada = exploradora1;
			return this.ultimo_conjunto_utilizado.contains(exploradora2);
		}
	}
	public void definir_amistad(char exploradora, char[] amigas){
		TreeSet<Character> amistades_de_exploradora = new TreeSet<Character> ();
		for(int i = 0;amigas[i]!=';';i++){
			amistades_de_exploradora.add(amigas[i]);			
		}
		this.registro_amistades.put(exploradora, amistades_de_exploradora);
	}
	public TreeSet<Character> amigas_de(char exploradora){
		return this.registro_amistades.get(exploradora);
	}
	public Amistades(){
		this.registro_amistades = new TreeMap<Character, TreeSet<Character> > ();
	}
	public boolean tiene_amigas(char exploradora){
		return (this.registro_amistades.containsKey(exploradora));
	}
}
