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
	TreeSet<Integer> exploradoras_sin_amigas;
	TreeSet<Integer> exploradoras_agregadas;
	Map<Integer,Integer> mejor_distribucion_encontrada;
	int menor_desorden_alfabetico_encontrado;
	private int calcular_distancia_maxima_2(Exploradora exploradora, Map<Integer, Integer> solucion_preprocesada){
		Iterator<Integer> it = exploradora.amigas_de();  // O(1)
		int res = 0;
		while(it.hasNext()){
			int exploradora_actual = it.next();
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
		this.exploradoras_sin_amigas = new TreeSet<Integer>();
		this.exploradoras_agregadas = new TreeSet<Integer>();
		this.mejor_distribucion_encontrada = new TreeMap<Integer,Integer>();
	}
	private void encontrar_ronda(int iesimo,int cant_exploradoras_sin_amigas){ //O((e-iesimo-1)!e a ln a)
		Iterator<Exploradora> it = this.exploradoras_con_amigas.iterator();
		if(suma_distancias!=-1 && suma_distancias <= fogon.distancias_actuales()){
			return;
		}else if(fogon.esta_completo() && (suma_distancias==-1 || suma_distancias >= fogon.distancias_actuales())){
			if(suma_distancias==-1 || suma_distancias > fogon.distancias_actuales() || this.menor_desorden_alfabetico_encontrado < fogon.dame_desorden_alfabetico()){
				suma_distancias = fogon.distancias_actuales();
				this.mejor_distribucion_encontrada.clear();
				this.mejor_distribucion_encontrada.putAll(fogon.devolver_ronda()); //¿O(a ln a)?
				this.menor_desorden_alfabetico_encontrado = fogon.dame_desorden_alfabetico();
			}
			return;
		}else if(fogon.esta_completo()){
			return;
		}
		while(it.hasNext()){ //O((e-iesimo)!e a ln a) en potencia, el algoritmo prueba todas las permutaciones en el rango [i+1,e-1], las cuales son(e-i-1)!.
			Exploradora nueva = it.next();
			while(it.hasNext() && this.exploradoras_agregadas.contains(nueva.letra)){ //O(a ln a) Se realizan a iteraciones (1). El costo de evaluar la condicion es O(ln a) (1)(3) 
				nueva = it.next();
			}
			if(!this.exploradoras_agregadas.contains(nueva.letra)){
				this.exploradoras_agregadas.add(nueva.letra); //O(a ln a) (1)(3)
				fogon.colocar_exploradora(nueva, iesimo+1);
				this.encontrar_ronda(iesimo+1,cant_exploradoras_sin_amigas);//recursion 
				fogon.quitar_exploradora(nueva); //O(a ln a) (1)(3)
				this.exploradoras_agregadas.remove(nueva.letra); //O(ln a) (1)(3)
			}
		}
		if(cant_exploradoras_sin_amigas>0){
			Exploradora exploradora_vacia = new Exploradora();
			fogon.colocar_exploradora(exploradora_vacia, iesimo+1); //O(a ln a)
			this.encontrar_ronda(iesimo+1,cant_exploradoras_sin_amigas-1);
			fogon.quitar_exploradora(exploradora_vacia); //O(a ln a)
		}
	}
	private void modificar_amiga(Exploradora exploradora, TreeSet<Integer> nuevo_conjunto){//O(a ln a)
		Iterator<Integer> it = nuevo_conjunto.iterator();
		Exploradora amiga = new Exploradora((int)0,null);
		while(it.hasNext()){ //O(a ln a) El ciclo tiene a iteraciones (1)
			amiga.letra = it.next();
			if(!this.exploradoras_con_amigas.contains(amiga)){ //O(ln a)(1)(3)
				TreeSet<Integer> conjunto_de_nueva_amiga = new TreeSet<Integer>();
				conjunto_de_nueva_amiga.add(exploradora.letra); //O(1) el conjunto acaba de ser creado
				Exploradora nueva_exploradoraExploradora = new Exploradora(amiga.letra, conjunto_de_nueva_amiga);   //TODO: WTF ese nombre de variable??
				this.exploradoras_con_amigas.add(nueva_exploradoraExploradora); //O(ln a)(1)(3)
			}else{
				Exploradora a_modificar = this.exploradoras_con_amigas.ceiling(amiga);
				if(!a_modificar.es_amiga_de(amiga.letra)){//O(ln a)
					a_modificar.aniadir_amiga(exploradora.letra); //O(ln a)
				}
				if(this.exploradoras_sin_amigas.contains(a_modificar.letra)){
					this.exploradoras_sin_amigas.remove(a_modificar.letra);
				}
			}
		}
	}
	private void procesar_amistad(String entrada){ //O(e+ a ln a)
		int i = 0;
		String [] conjuntos_exploradoras = entrada.split(";");
		while(i < conjuntos_exploradoras.length){ //O(e+a) (5)
			TreeSet<Integer> nuevo_conjunto = new TreeSet<Integer>();
			String [] numeros = conjuntos_exploradoras[i].split(" ");
			i++;
			System.out.print(numeros[0]);
			System.out.printf("\n");
			int letra_exploradora = Integer.parseInt(numeros[0]);
			for(int j=0;j<numeros.length;j++){
				if(numeros[j].length()!=0){
					nuevo_conjunto.add(Integer.parseInt(numeros[j]));
				}
			}
			Exploradora exploradora = new Exploradora(letra_exploradora,nuevo_conjunto);
			if(nuevo_conjunto.size()!=0 && !this.exploradoras_con_amigas.contains(exploradora)){ //O(ln a)(1)(3)
				this.exploradoras_con_amigas.add(exploradora); //O(ln a)(1)(3)
				this.modificar_amiga(exploradora,nuevo_conjunto); //O(a ln a)
			}else if(nuevo_conjunto.size()!=0){
				this.exploradoras_con_amigas.ceiling(exploradora).aniadir_grupo_de_amigas(nuevo_conjunto); //O(ln a) (1)(3) la función celling es similar a la contain, con la diferencia que retorna por referencia el objeto en si en caso de encontrarlo
				this.modificar_amiga(exploradora,nuevo_conjunto); //O(a ln a)
				if(this.exploradoras_sin_amigas.contains(exploradora.letra)){
					this.exploradoras_sin_amigas.remove(exploradora.letra); //O(ln e) (3) el conjunto exploradoras_sin_amigas a lo sumo tiene e elementos
				}
			}else{
				this.exploradoras_sin_amigas.add(letra_exploradora); //O(ln e) (3) el conjunto exploradoras_sin_amigas a lo sumo tiene e elementos
			}
		}
	}
    public int cantidad_de_amistades(){
        System.out.printf("Exploradoras con amigas: %d\n",this.exploradoras_con_amigas.size());
        int amistades = 0;
        Iterator<Exploradora> it = this.exploradoras_con_amigas.iterator();
        while (it.hasNext()){
            Exploradora e = it.next();
            amistades = amistades + e.amistades.size();
            Iterator<Integer> amigas = e.amistades.iterator();
            System.out.printf("%c:",e.letra);
            while (amigas.hasNext()){
                System.out.printf("%c",amigas.next());
            }
            System.out.printf("\n");
        }
        return amistades/2;
    }
	public void generar_solucion(String entrada){ //O((e-1)!a ln a)
        if (Tiempos.mideTiempos){
            Tiempos.time0 = System.nanoTime();
        }
		this.procesar_amistad(entrada); //O(e+ a ln a)
		
		int tamanio_de_fogon = this.exploradoras_con_amigas.size()+this.exploradoras_sin_amigas.size();
		this.fogon = new Fogon(tamanio_de_fogon);
		this.fogon.colocar_exploradora(this.exploradoras_con_amigas.first(), 0); //O(a ln a)
		this.exploradoras_agregadas.add(this.exploradoras_con_amigas.first().letra); //¿O(ln a)?
		this.encontrar_ronda(0, this.exploradoras_sin_amigas.size()); //O((e-1)! e a ln a) = O(e! a ln a) 
		
		ArrayList<Integer> lista_resultado = new ArrayList<Integer>(tamanio_de_fogon);
		for(int k = 0;k<tamanio_de_fogon;k++){//O(e) el tamanio del fogon es de e exploradoras
			lista_resultado.add((int)-1); //O(1) (4)
		}
		
		Map<Integer,Integer> solucion_preprocesada = this.mejor_distribucion_encontrada;
		Set<Integer> conjunto_exploradoras = solucion_preprocesada.keySet();
		Iterator<Integer> it = conjunto_exploradoras.iterator();
		while(it.hasNext()){ //O(e ln a) Se realizan e el tamanio del conjunto de exploradoras no es mayor que e
			int ex = it.next();
			lista_resultado.set(solucion_preprocesada.get(ex), ex); //O(ln a) (1) (4) solucion_preprocesada contiene solo exploradoras con amigas, que no pueden superar a
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
		Iterator<Integer> sin_amigas = this.exploradoras_sin_amigas.iterator();
		for(int j = 0;j<tamanio_de_fogon;j++){ //O(e) el tamanio del fogon es de e exploradoras
			if(lista_resultado.get(j)==(char)-1){//O(1) (4)
				if(sin_amigas.hasNext()){
					lista_resultado.set(j,sin_amigas.next());//O(1) (4) 
				}
			}
		}
		if (!Tiempos.mideTiempos) {
            System.out.printf("%d ",distancia_maxima);
        }
		Iterator<Integer> iterador_final = lista_resultado.iterator();
		while(iterador_final.hasNext()){ //O(e) el tamanio de la lista resultado es el tamanio del fogon
            Integer n = iterador_final.next();
            if (!Tiempos.mideTiempos){
			    System.out.printf("%d", n);
            }
		}
        if (Tiempos.mideTiempos){
            Tiempos.time1 = System.nanoTime() - Tiempos.time0;
            System.out.printf("%d,%d,%d",fogon.size(),this.cantidad_de_amistades(),Tiempos.time1);
        }
        System.out.printf("\n");
	}
}

//(1) O(ln a) la busqueda e inserción en un TreeMap es logaritmica
//(2) el conjunto de amigas de una exploradora a lo sumo contiene a elementos
//(3) el conjunto de exploradoras con amigas y exploradoras agregadas a lo sumo contiene a elementos
//(4) utilizar add, get y set en un ArrayList es O(1) siempre que no se exeda la longitud actual del ArrayList
//(5) el string pasado como parametro contiene a las exploradoras y sus amistades
//(6) el conjunto de exploradoras con amigas y exploradoras agregadas a lo sumo contiene e elementos


