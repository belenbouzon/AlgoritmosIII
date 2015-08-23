import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Fogon {
	Amistades amistades;
	private TreeSet<Character> exploradoras;
	private Set<Tuple <Character,Integer> > exploradoras_con_amigas;
	private Set<Tuple <Character,Integer> > backup;
	private int distancia_amistades;
	private int cantidad_exploradoras_actuales;
	private int cantidad_exploradoras_totales;
	private int calcular_distancia(int ex1,int ex2){
		return 0;
	}
	public Fogon(Amistades amigas, int cantidad_exploradoras){
		this.amistades = amigas;
		this.cantidad_exploradoras_actuales = 0;
		this.cantidad_exploradoras_totales = cantidad_exploradoras;
	}
	public void colocar_exploradora(char exploradora,int posicion){
		if(exploradora==-1 || this.exploradoras.contains(exploradora)){
			this.cantidad_exploradoras_actuales++;
		}
		Character exploradora_c = exploradora;
		Integer posicion_i = posicion;
		if(exploradora!=-1){
			Iterator<Tuple<Character,Integer> > it = this.exploradoras_con_amigas.iterator();
			while(it.hasNext()){
				Tuple<Character,Integer> t = it.next();
				if(this.amistades.es_amiga_de(exploradora, t.x)){
					this.distancia_amistades += calcular_distancia(posicion,t.y);
				}
			}
			
			Tuple<Character,Integer> tupla = new Tuple<Character,Integer> (exploradora_c,posicion_i);
			this.exploradoras_con_amigas.add(tupla);
		}
	}
	public int distancias_actuales(){
		return this.distancia_amistades;
	}
	public boolean esta_completo(){
		return (this.cantidad_exploradoras_actuales==this.cantidad_exploradoras_totales);
	}
	public void crear_backup(){
		this.backup.clear();
		this.backup.addAll(this.exploradoras_con_amigas);
	}
	public void recuperar_backup(){
		this.exploradoras_con_amigas.clear();
		this.exploradoras_con_amigas.addAll(this.backup);
	}
	public void limpiar_posicion(int pos){
		Iterator<Tuple<Character,Integer> > it = this.exploradoras_con_amigas.iterator();
		while(it.hasNext()){
			Tuple<Character,Integer> t = it.next();
			if(t.y==pos){
				it.remove();
				return;
			}
		}
	}
	public Set<Tuple <Character,Integer> > devolver_ronda(){
		return this.exploradoras_con_amigas;
	}
};
