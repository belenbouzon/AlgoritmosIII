import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Fogon {
	//Amistades amistades;
	//private LinkedHashSet<Tuple <Character,Integer> > exploradoras_con_amigas;
	//private LinkedHashSet<Tuple <Character,Integer> > backup;
	
	private TreeMap<Character,Integer> exploradoras_con_amigas;
	private LinkedHashMap<Integer,Character> exploradoras_por_posiciones;
	private int distancia_amistades;
	private int cantidad_exploradoras_actuales;
	private int cantidad_exploradoras_totales;
	private int calcular_distancia(int ex1,int ex2,int n){
		int res = ex2 - ex1;
		if(res<0){
			res = (n/2)-res;
		}
		return res % (n/2 + 1);
	}
	public Fogon(int cantidad_exploradoras){
		//this.amistades = amigas;
		this.cantidad_exploradoras_actuales = 0;
		this.cantidad_exploradoras_totales = cantidad_exploradoras;
		this.exploradoras_con_amigas = new TreeMap<Character,Integer> ();
	}
	private int calcular_alteracion_distancias(Exploradora exploradora,int posicion){
		int res = 0;
		Iterator<Character> it = exploradora.amigas_de();
		while(it.hasNext()){
			char ex = it.next();
			if(this.exploradoras_con_amigas.containsKey(ex)){
				res += calcular_distancia(posicion,this.exploradoras_con_amigas.get(ex),this.cantidad_exploradoras_totales);
			}
		}
		return res;
	}
	public void colocar_exploradora(Exploradora exploradora,int posicion){
		this.cantidad_exploradoras_actuales++;
		if(exploradora.letra!=-1){
			this.distancia_amistades += this.calcular_alteracion_distancias(exploradora, posicion);
			this.exploradoras_con_amigas.put(exploradora.letra,posicion);
		}
	}
	public int distancias_actuales(){
		return this.distancia_amistades;
	}
	public boolean esta_completo(){
		return (this.cantidad_exploradoras_actuales==this.cantidad_exploradoras_totales);
	}
	public void crear_backup(){
		
	}
	public void recuperar_backup(){
		
	}
	public void quitar_exploradora(Exploradora exploradora){
		this.cantidad_exploradoras_actuales--;
		if((exploradora.letra)!=(char)-1){
			//System.out.printf("letra: %c\n", exploradora.letra);
			if(!this.exploradoras_con_amigas.containsKey(exploradora.letra)){
				//System.out.printf("no contiene exploradora!!\n");
			}
			Integer posicion = this.exploradoras_con_amigas.get(exploradora.letra);
			if(posicion==null){
				System.out.printf("letra sin resultado: %c\n",exploradora.letra);
			}
			this.distancia_amistades -= this.calcular_alteracion_distancias(exploradora, posicion);
			this.exploradoras_con_amigas.remove(exploradora.letra);
		}
	}
	public Map<Character,Integer> devolver_ronda(){
		return this.exploradoras_con_amigas;
	}
	public void limpiar_posicion(int posicion){
		
	}
};
