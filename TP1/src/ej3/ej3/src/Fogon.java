import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Fogon {
	Amistades amistades;
	//private LinkedHashSet<Tuple <Character,Integer> > exploradoras_con_amigas;
	//private LinkedHashSet<Tuple <Character,Integer> > backup;
	private TreeSet<Tuple <Character,Integer> > exploradoras_con_amigas;
	private TreeSet<Tuple <Character,Integer> > backup;
	private int distancia_amistades_backup;
	private int distancia_amistades;
	private int cantidad_exploradoras_actuales;
	private int cantidad_exploradoras_actuales_backup;
	private int cantidad_exploradoras_totales;
	private int calcular_distancia(int ex1,int ex2,int n){
		int res = ex2 - ex1;
		return res % (n/2);
	}
	public Fogon(Amistades amigas, int cantidad_exploradoras){
		this.amistades = amigas;
		this.cantidad_exploradoras_actuales = 0;
		this.cantidad_exploradoras_totales = cantidad_exploradoras;
		this.exploradoras_con_amigas = new TreeSet<Tuple <Character,Integer> > (new ComparadorTuplasCharInt());
		this.backup = new TreeSet<Tuple <Character,Integer> > (new ComparadorTuplasCharInt());
	}
	private int calcular_alteracion_distancias(Character exploradora,int posicion){
		int res = 0;
		Iterator<Tuple<Character,Integer> > it = this.exploradoras_con_amigas.iterator();
		while(it.hasNext()){
			Tuple<Character,Integer> t = it.next();
			if(this.amistades.es_amiga_de(exploradora, t.x)){
				res += calcular_distancia(posicion,t.y,this.cantidad_exploradoras_totales);
			}
		}
		return res;
	}
	public void colocar_exploradora(char exploradora,int posicion){
		//System.out.printf("coloco_exploradora\n");
		this.cantidad_exploradoras_actuales++;
		if(exploradora!=-1){
			this.distancia_amistades += this.calcular_alteracion_distancias(exploradora, posicion);
			Tuple<Character,Integer> tupla = new Tuple<Character,Integer> (exploradora,posicion);
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
		this.distancia_amistades_backup = this.distancia_amistades;
		this.cantidad_exploradoras_actuales_backup = this.cantidad_exploradoras_actuales;
	}
	public void recuperar_backup(){
		this.exploradoras_con_amigas.clear();
		this.exploradoras_con_amigas.addAll(this.backup);
		this.distancia_amistades = this.distancia_amistades_backup;
		this.cantidad_exploradoras_actuales = this.cantidad_exploradoras_actuales_backup;
	}
	public void limpiar_posicion(int pos){
		Iterator<Tuple<Character,Integer> > it = this.exploradoras_con_amigas.iterator();
		while(it.hasNext()){
			Tuple<Character,Integer> t = it.next();
			if(t.y==pos){
				this.cantidad_exploradoras_actuales -= 1;
				this.distancia_amistades -= this.calcular_alteracion_distancias(t.x, t.y);
				it.remove();
				return;
			}
		}
	}
	public TreeSet<Tuple <Character,Integer> > devolver_ronda(){
		return this.exploradoras_con_amigas;
	}
};
