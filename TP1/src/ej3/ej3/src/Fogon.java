import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Fogon {
	private TreeMap<Character,Integer> exploradoras_con_amigas;
	private int distancia_amistades;
	private int cantidad_exploradoras_actuales;
	private int cantidad_exploradoras_totales;
	private int nivel_de_desorden_alfabetico;
	public static int calcular_distancia(int ex1,int ex2,int n){
		int res2 = ex2 - ex1;
		if(res2<0){
			res2 = -res2;
		}
		int res;
		if(ex1>ex2){
			res = (n-ex1) + ex2;
		}else{
			res = (n-ex2) + ex1;
		}
		if(res>res2){
			res = res2;
		}
		return res;
	}
	public Fogon(int cantidad_exploradoras){
		this.cantidad_exploradoras_actuales = 0;
		this.cantidad_exploradoras_totales = cantidad_exploradoras;
		this.exploradoras_con_amigas = new TreeMap<Character,Integer> ();
		this.nivel_de_desorden_alfabetico = 0;
	}
	private int calcular_alteracion_distancias(Exploradora exploradora,int posicion){ //O(a ln a)
		int res = 0;
		Iterator<Character> it = exploradora.amigas_de();
		while(it.hasNext()){ //Se realizan a iteraciones por (1). El ciclo tarda O(a ln a) en concretarse
			char ex = it.next();
			if(this.exploradoras_con_amigas.containsKey(ex)){ //O(a ln a) (1)
				res += calcular_distancia(posicion,this.exploradoras_con_amigas.get(ex),this.cantidad_exploradoras_totales); //O(ln a) (1)(2)
			}
		}
		return res;
	}
	public void colocar_exploradora(Exploradora exploradora,int posicion){ //O(a ln a)
		this.cantidad_exploradoras_actuales++;
		if(exploradora.letra!=-1){
			this.distancia_amistades += this.calcular_alteracion_distancias(exploradora, posicion); //O(a ln a)
			this.nivel_de_desorden_alfabetico += posicion * (int)exploradora.letra;
			this.exploradoras_con_amigas.put(exploradora.letra,posicion); //O(ln a) (1)(3)
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
	public void quitar_exploradora(Exploradora exploradora){ //O(a ln a)
		this.cantidad_exploradoras_actuales--;
		if((exploradora.letra)!=(char)-1){
			Integer posicion = this.exploradoras_con_amigas.get(exploradora.letra); //O(ln a) (1)(3)
			this.nivel_de_desorden_alfabetico -= posicion * (int)exploradora.letra;
			this.distancia_amistades -= this.calcular_alteracion_distancias(exploradora, posicion);//O(a ln a)
			this.exploradoras_con_amigas.remove(exploradora.letra);
		}
	}
	public Map<Character,Integer> devolver_ronda(){
		return this.exploradoras_con_amigas;
	}
	public void limpiar_posicion(int posicion){
		
	}
	public int dame_desorden_alfabetico(){
		return this.nivel_de_desorden_alfabetico;
	}
	public int calcular_distancia_maxima(Exploradora exploradora){//O(ln a)
		Iterator<Character> it = exploradora.amigas_de();
		int res = 0;
		while(it.hasNext()){
			char exploradora_actual = it.next();
			int distancia = calcular_distancia(this.exploradoras_con_amigas.get(exploradora.letra),this.exploradoras_con_amigas.get(exploradora_actual),this.cantidad_exploradoras_totales); //O(ln a)
			if(distancia > res){
				res = distancia;
			}
		}
		return res;
	}
};
//(1) O(ln a) la busqueda e inserción en un TreeMap es logaritmica
// (2) el conjunto de amigas de una exploradora a lo sumo contiene a elementos
//(3) el conjunto de exploradoras con amigas a lo sumo contiene a elementos
