import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Solucion {
	Fogon fogon;
	int suma_distancias;
	TreeSet<Character> exploradoras_con_amigas;
	TreeSet<Character> exploradoras_sin_amigas;
	Amistades am;
	public Solucion(){
		am = new Amistades();
		//fogon = new Fogon(am,0);
		suma_distancias = -1;
		this.exploradoras_con_amigas = new TreeSet<Character>();
		this.exploradoras_sin_amigas = new TreeSet<Character>();
		
	}
	private void encontrar_ronda(int iesimo,int cant_exploradoras_sin_amigas){
		//LinkedHashSet<Character> copia_conj = new LinkedHashSet<Character>();
		//copia_conj.addAll(this.exploradoras_con_amigas);
		//Iterator<Character> it = copia_conj.iterator();
		Iterator<Character> it = this.exploradoras_con_amigas.iterator();
		//System.out.printf("iteracion");
		if(suma_distancias!=-1 && suma_distancias <= fogon.distancias_actuales()){
			return;
		}else if(fogon.esta_completo() && (suma_distancias==-1 || suma_distancias > fogon.distancias_actuales())){
			System.out.print("cree backup\n");
			suma_distancias = fogon.distancias_actuales();
			fogon.crear_backup();
			return;
		}else if(fogon.esta_completo()){
			System.out.printf("problema");
			return;
		}
		while(it.hasNext()){
			fogon.colocar_exploradora(it.next(), iesimo+1);
			this.encontrar_ronda(iesimo+1,cant_exploradoras_sin_amigas);
			fogon.limpiar_posicion(iesimo+1);
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
			i += 2;
			System.out.printf("letra: %c\n", entrada.charAt(i));
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
	public char[] generar_solucion(String entrada){
		this.procesar_amistad(entrada);
		this.fogon = new Fogon(am,this.exploradoras_con_amigas.size()+this.exploradoras_sin_amigas.size());
		System.out.printf("cantidad exploradoras: %d%n", this.exploradoras_con_amigas.size()+this.exploradoras_sin_amigas.size());
		this.fogon.colocar_exploradora(this.exploradoras_con_amigas.first(), 0);
		this.exploradoras_con_amigas.pollFirst();
		this.encontrar_ronda(0, this.exploradoras_sin_amigas.size());
		if(this.suma_distancias<=this.fogon.distancias_actuales() || !this.fogon.esta_completo()){
			this.fogon.recuperar_backup();
		}
		if(!this.fogon.esta_completo()){
			System.out.printf("fogon no completo!!!!!!\n");
		}
		int c_ex = this.exploradoras_con_amigas.size() + this.exploradoras_sin_amigas.size() + 1;
		char res [] = new char [c_ex];
		for(int j = 0;j<c_ex;j++){
			res[j] = (char) -1;
		}
		TreeSet<Tuple <Character,Integer> > solucion_preprocesada = this.fogon.devolver_ronda();
		Iterator<Tuple <Character,Integer> > it = solucion_preprocesada.iterator();
		while(it.hasNext()){
			System.out.printf("qq: %d%n\n", solucion_preprocesada.size());
			Tuple <Character,Integer> t = it.next();
			res[t.y] = t.x;
		}
		Iterator<Character> sin_amigas = this.exploradoras_sin_amigas.iterator();
		for(int j = 0;j<c_ex;j++){
			if(res[j]==(char)-1){
				if(it.hasNext()){
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
		System.out.printf("]");
		return res;
	}
}
