import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;

public class Solucion {
	Fogon fogon;
	int suma_distancias;
	TreeSet<Exploradora> exploradoras_con_amigas;
	TreeSet<Character> exploradoras_sin_amigas;
	TreeSet<Character> exploradoras_agregadas;
	//Amistades am;
	Map<Character,Integer> mejor_distribucion_encontrada;
	public Solucion(){
		//am = new Amistades();
		suma_distancias = -1;
		this.exploradoras_con_amigas = new TreeSet<Exploradora>(new ComparadorExploradoras());
		this.exploradoras_sin_amigas = new TreeSet<Character>();
		this.exploradoras_agregadas = new TreeSet<Character>();
		this.mejor_distribucion_encontrada = new TreeMap<Character,Integer>();
	}
	private void encontrar_ronda(int iesimo,int cant_exploradoras_sin_amigas){
		Iterator<Exploradora> it = this.exploradoras_con_amigas.iterator();
		if(suma_distancias!=-1 && suma_distancias <= fogon.distancias_actuales()){
			return;
		}else if(fogon.esta_completo() && (suma_distancias==-1 || suma_distancias > fogon.distancias_actuales())){
			suma_distancias = fogon.distancias_actuales();
			this.mejor_distribucion_encontrada.clear();
			this.mejor_distribucion_encontrada.putAll(fogon.devolver_ronda());
			return;
		}else if(fogon.esta_completo()){
			return;
		}
		while(it.hasNext()){
			Exploradora nueva = it.next();
			while(it.hasNext() && this.exploradoras_agregadas.contains(nueva.letra)){
				nueva = it.next();
			}
			if(!this.exploradoras_agregadas.contains(nueva.letra)){
				System.out.printf("Letra: %c\n",nueva.letra);
				this.exploradoras_agregadas.add(nueva.letra);
				if(!this.exploradoras_agregadas.contains(nueva.letra)){
					System.out.printf("problema con agregado a conjunto en letra: %c",nueva.letra);
				}
				fogon.colocar_exploradora(nueva, iesimo+1);
				this.encontrar_ronda(iesimo+1,cant_exploradoras_sin_amigas);
				if(!this.exploradoras_agregadas.contains(nueva.letra)){
					System.out.printf("problema con agregado a conjunto");
				}
				fogon.quitar_exploradora(nueva);
				this.exploradoras_agregadas.remove(nueva.letra);
			}
		}
		if(cant_exploradoras_sin_amigas>0){
			Exploradora exploradora_vacia = new Exploradora();
			fogon.colocar_exploradora(exploradora_vacia, iesimo+1);
			this.encontrar_ronda(iesimo+1,cant_exploradoras_sin_amigas-1);
			fogon.quitar_exploradora(exploradora_vacia);
		}
	}
	private void procesar_amistad(String entrada){
		int i = 0;
		while(i < entrada.length()){
			TreeSet<Character> nuevo_conjunto = new TreeSet<Character>();
			Character letra_exploradora = entrada.charAt(i);
			i ++;
			while(i < entrada.length() && entrada.charAt(i)!=';'){
				if(entrada.charAt(i)!= ' '){
					nuevo_conjunto.add(entrada.charAt(i));
				}
				i++;
			}
			i++;
			if(nuevo_conjunto.size()!=0){
				Exploradora exploradora = new Exploradora(letra_exploradora,nuevo_conjunto);
				this.exploradoras_con_amigas.add(exploradora);
			}else{
				this.exploradoras_sin_amigas.add(letra_exploradora);
			}
		}
	}
	public char[] generar_solucion(List<String> entrada){
		Iterator<String> iterador_datos = entrada.iterator();
		while(iterador_datos.hasNext()){
			this.procesar_amistad(iterador_datos.next());
		}

		this.fogon = new Fogon(this.exploradoras_con_amigas.size()+this.exploradoras_sin_amigas.size());
		this.fogon.colocar_exploradora(this.exploradoras_con_amigas.first(), 0);
		this.exploradoras_con_amigas.pollFirst();
		this.encontrar_ronda(0, this.exploradoras_sin_amigas.size());
		if(this.suma_distancias<this.fogon.distancias_actuales() || !this.fogon.esta_completo()){
			this.fogon.recuperar_backup();
		}
		int c_ex = this.exploradoras_con_amigas.size() + this.exploradoras_sin_amigas.size() + 1;
		char res [] = new char [c_ex];
		for(int j = 0;j<c_ex;j++){
			res[j] = (char) -1;
		}
		Map<Character,Integer> solucion_preprocesada = this.mejor_distribucion_encontrada;
		Set<Character> conjunto_exploradoras = solucion_preprocesada.keySet();
		Iterator<Character> it = conjunto_exploradoras.iterator();
		while(it.hasNext()){
			char ex = it.next();
			System.out.printf("con amigas: %c\n",ex);
			res[solucion_preprocesada.get(ex)] = ex;
		}
		Iterator<Character> sin_amigas = this.exploradoras_sin_amigas.iterator();
		for(int j = 0;j<c_ex;j++){
			if(res[j]==(char)-1){
				if(sin_amigas.hasNext()){
					res[j] = sin_amigas.next();
				}else{
					System.out.printf("erorrrrrr\n");
				}
			}
		}
		System.out.printf("[");
		int i = 0;
		while(i<c_ex){
			System.out.printf("%c, ",res[i]);
			i++;
		}
		System.out.printf("]\n");
		return res;
	}
}
