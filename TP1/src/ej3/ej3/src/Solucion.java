import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;

public class Solucion {
	Fogon fogon;
	int suma_distancias;
	TreeSet<Character> exploradoras_con_amigas;
	TreeSet<Character> exploradoras_sin_amigas;
	TreeSet<Character> exploradoras_agregadas;
	Amistades am;
	public Solucion(){
		am = new Amistades();
		suma_distancias = -1;
		this.exploradoras_con_amigas = new TreeSet<Character>();
		this.exploradoras_sin_amigas = new TreeSet<Character>();
		this.exploradoras_agregadas = new TreeSet<Character>();
		
	}
	private void encontrar_ronda(int iesimo,int cant_exploradoras_sin_amigas){
		Iterator<Character> it = this.exploradoras_con_amigas.iterator();
		if(suma_distancias!=-1 && suma_distancias <= fogon.distancias_actuales()){
			return;
		}else if(fogon.esta_completo() && (suma_distancias==-1 || suma_distancias > fogon.distancias_actuales())){
			suma_distancias = fogon.distancias_actuales();
			fogon.crear_backup();
			return;
		}else if(fogon.esta_completo()){
			return;
		}
		while(it.hasNext()){
			char nueva = it.next();
			while(it.hasNext() && this.exploradoras_agregadas.contains(nueva)){
				nueva = it.next();
			}
			if(!this.exploradoras_agregadas.contains(nueva)){
				this.exploradoras_agregadas.add(nueva);
				fogon.colocar_exploradora(nueva, iesimo+1);
				this.encontrar_ronda(iesimo+1,cant_exploradoras_sin_amigas);
				fogon.limpiar_posicion(iesimo+1);
				this.exploradoras_agregadas.remove(nueva);
			}
		}
		if(cant_exploradoras_sin_amigas>0){
			char n = (char) -1;
			fogon.colocar_exploradora(n, iesimo+1);
			this.encontrar_ronda(iesimo+1,cant_exploradoras_sin_amigas-1);
			fogon.limpiar_posicion(iesimo+1);
		}
	}
	private void procesar_amistad(String entrada){
		int i = 0;
		while(i < entrada.length()){
			TreeSet<Character> nuevo_conjunto = new TreeSet<Character>();
			Character exploradora = entrada.charAt(i);
			i ++;
			while(i < entrada.length() && entrada.charAt(i)!=';'){
				if(entrada.charAt(i)!= ' '){
					nuevo_conjunto.add(entrada.charAt(i));
				}
				i++;
			}
			i++;
			if(nuevo_conjunto.size()!=0){
				this.exploradoras_con_amigas.add(exploradora);
			}else{
				this.exploradoras_sin_amigas.add(exploradora);
			}
			this.am.definir_amistad(exploradora, nuevo_conjunto);
		}
	}
	public char[] generar_solucion(List<String> entrada){
		Iterator<String> iterador_datos = entrada.iterator();
		while(iterador_datos.hasNext()){
			this.procesar_amistad(iterador_datos.next());
		}

		this.fogon = new Fogon(am,this.exploradoras_con_amigas.size()+this.exploradoras_sin_amigas.size());
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
		LinkedHashSet<Tuple <Character,Integer> > solucion_preprocesada = this.fogon.devolver_ronda();
		Iterator<Tuple <Character,Integer> > it = solucion_preprocesada.iterator();
		while(it.hasNext()){
			Tuple <Character,Integer> t = it.next();
			res[t.y] = t.x;
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
