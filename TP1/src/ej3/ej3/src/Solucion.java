import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;

public class Solucion {
	Fogon fogon;
	int suma_distancias;
	TreeSet<Exploradora> exploradoras_con_amigas;
	TreeSet<Character> exploradoras_sin_amigas;
	TreeSet<Character> exploradoras_agregadas;
	Map<Character,Integer> mejor_distribucion_encontrada;
	int menor_desorden_alfabetico_encontrado;
	private int calcular_distancia_maxima_2(Exploradora exploradora, Map<Character, Integer> solucion_preprocesada){
		Iterator<Character> it = exploradora.amigas_de();
		int res = 0;
		while(it.hasNext()){
			char exploradora_actual = it.next();
			int distancia = Fogon.calcular_distancia(solucion_preprocesada.get(exploradora.letra),solucion_preprocesada.get(exploradora_actual),this.exploradoras_con_amigas.size()+this.exploradoras_sin_amigas.size()+1);
			if(distancia > res){
				res = distancia;
			}
		}
		return res;
	}
	public Solucion(){
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
		}else if(fogon.esta_completo() && (suma_distancias==-1 || suma_distancias >= fogon.distancias_actuales())){
			if(suma_distancias==-1 || suma_distancias > fogon.distancias_actuales() || this.menor_desorden_alfabetico_encontrado < fogon.dame_desorden_alfabetico()){
				suma_distancias = fogon.distancias_actuales();
				this.mejor_distribucion_encontrada.clear();
				this.mejor_distribucion_encontrada.putAll(fogon.devolver_ronda());
				this.menor_desorden_alfabetico_encontrado = fogon.dame_desorden_alfabetico();
			}
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
			Exploradora exploradora = new Exploradora(letra_exploradora,nuevo_conjunto);
			if(nuevo_conjunto.size()!=0 && !this.exploradoras_con_amigas.contains(exploradora)){
				this.exploradoras_con_amigas.add(exploradora);
				Iterator<Character> it = nuevo_conjunto.iterator();
				Exploradora amiga = new Exploradora((char)0,null);
				while(it.hasNext()){
					amiga.letra = it.next();
					if(!this.exploradoras_con_amigas.contains(amiga)){
						TreeSet<Character> conjunto_de_nueva_amiga = new TreeSet<Character>();
						conjunto_de_nueva_amiga.add(exploradora.letra);
						Exploradora nueva_exploradoraExploradora = new Exploradora(amiga.letra, conjunto_de_nueva_amiga);
						this.exploradoras_con_amigas.add(nueva_exploradoraExploradora);
					}else{
						Exploradora a_modificar = this.exploradoras_con_amigas.ceiling(amiga);
						if(!a_modificar.es_amiga_de(amiga.letra)){
							a_modificar.aniadir_amiga(exploradora.letra);
						}
					}
				}
			}else if(nuevo_conjunto.size()!=0){
				this.exploradoras_con_amigas.ceiling(exploradora).aniadir_grupo_de_amigas(nuevo_conjunto);
				Iterator<Character> it = nuevo_conjunto.iterator();
				Exploradora amiga = new Exploradora((char)0,null);
				while(it.hasNext()){
					amiga.letra = it.next();
					if(!this.exploradoras_con_amigas.contains(amiga)){
						TreeSet<Character> conjunto_de_nueva_amiga = new TreeSet<Character>();
						conjunto_de_nueva_amiga.add(exploradora.letra);
						Exploradora nueva_exploradoraExploradora = new Exploradora(amiga.letra, conjunto_de_nueva_amiga);
						this.exploradoras_con_amigas.add(nueva_exploradoraExploradora);
					}else{
						Exploradora a_modificar = this.exploradoras_con_amigas.ceiling(amiga);
						if(!a_modificar.es_amiga_de(amiga.letra)){
							a_modificar.aniadir_amiga(exploradora.letra);
						}
					}
				}
				if(this.exploradoras_sin_amigas.contains(exploradora.letra)){
					this.exploradoras_sin_amigas.remove(exploradora.letra);
				}
			}else{
				this.exploradoras_sin_amigas.add(letra_exploradora);
			}
		}
	}
	public void generar_solucion(String entrada){
		this.procesar_amistad(entrada);
		
		int tamanio_de_fogon = this.exploradoras_con_amigas.size()+this.exploradoras_sin_amigas.size();
		this.fogon = new Fogon(tamanio_de_fogon);
		this.fogon.colocar_exploradora(this.exploradoras_con_amigas.first(), 0);
		this.exploradoras_con_amigas.pollFirst();
		this.encontrar_ronda(0, this.exploradoras_sin_amigas.size());
		
		ArrayList<Character> lista_resultado = new ArrayList<Character>(tamanio_de_fogon);
		for(int k = 0;k<tamanio_de_fogon;k++){
			lista_resultado.add((char)-1);
		}
		
		Map<Character,Integer> solucion_preprocesada = this.mejor_distribucion_encontrada;
		Set<Character> conjunto_exploradoras = solucion_preprocesada.keySet();
		Iterator<Character> it = conjunto_exploradoras.iterator();
		while(it.hasNext()){
			char ex = it.next();
			lista_resultado.set(solucion_preprocesada.get(ex), ex);
		}
		
		int distancia_maxima = 0;
		Iterator<Exploradora> iterador_exploradoras = this.exploradoras_con_amigas.iterator();
		while(iterador_exploradoras.hasNext()){
			Exploradora ex = iterador_exploradoras.next();
			int distancia_actual = this.calcular_distancia_maxima_2(ex,solucion_preprocesada);
			if(distancia_actual > distancia_maxima){
				distancia_maxima = distancia_actual;
			}
		}
		Iterator<Character> sin_amigas = this.exploradoras_sin_amigas.iterator();
		for(int j = 0;j<tamanio_de_fogon;j++){
			if(lista_resultado.get(j)==null){
				if(sin_amigas.hasNext()){
					lista_resultado.set(j,sin_amigas.next());
				}
			}
		}
		System.out.printf("%d ",distancia_maxima);
		Iterator<Character> iterador_final = lista_resultado.iterator();
		while(iterador_final.hasNext()){
			System.out.printf("%c", iterador_final.next());
		}
	}
}
