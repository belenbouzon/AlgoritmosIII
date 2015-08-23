import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	Fogon fogon;
	int suma_distancias;
	TreeSet<Character> exploradoras_con_amigas;
	TreeSet<Character> exploradoras_sin_amigas;
	Amistades am;
	public Main(){
		am = new Amistades();
		//fogon = new Fogon(am,0);
		suma_distancias = -1;
		this.exploradoras_con_amigas = new TreeSet<Character>();
		this.exploradoras_sin_amigas = new TreeSet<Character>();
		
	}
	private void encontrar_ronda(int iesimo,int cant_exploradoras_sin_amigas){
		Iterator<Character> it = this.exploradoras_con_amigas.iterator();
		if(!fogon.esta_completo() || suma_distancias < fogon.distancias_actuales()){
			return;
		}else if(suma_distancias==-1 || suma_distancias > fogon.distancias_actuales()){
			suma_distancias = fogon.distancias_actuales();
			fogon.crear_backup();
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
	private int procesar_amistad(char[] entrada,Integer i){
		int cant = 0;
		char exploradora = entrada[i];
		i += 2;
		int j = i;
		while(entrada[i]!=';' || entrada[i]!=0){
			i++;
		}
		if(j==i){
			this.exploradoras_sin_amigas.add(exploradora);
		}else{
			this.exploradoras_con_amigas.add(exploradora);
		}
		int k = 0;
		char arreglo [] = new char[i-j+1];
		while(k<i-j){
			arreglo[k] = entrada[j];
			j++;
			k++;
		}
		arreglo[k] = ';';
		this.am.definir_amistad(exploradora, arreglo);
		cant++;
		return cant;
	}
	public char[] generar_solucion(char[] entrada){
		int i = 0;
		int cant = 0;
		while(entrada[i]!=0){
			cant += this.procesar_amistad(entrada,i);
		}
		this.fogon = new Fogon(am,cant);
		this.encontrar_ronda(0, this.exploradoras_sin_amigas.size());
		int c_ex = this.exploradoras_con_amigas.size() + this.exploradoras_sin_amigas.size();
		char res [] = new char [c_ex];
		for(int j = 0;j<c_ex;j++){
			res[j] = (char) -1;
		}
		Set<Tuple <Character,Integer> > solucion_preprocesada = this.fogon.devolver_ronda();
		Iterator<Tuple <Character,Integer> > it = solucion_preprocesada.iterator();
		while(it.hasNext()){
			Tuple <Character,Integer> t = it.next();
			res[t.y] = t.x;
		}
		Iterator<Character> sin_amigas = this.exploradoras_sin_amigas.iterator();
		for(int j = 0;j<c_ex;j++){
			if(res[j]==(char)-1){
				res[j] = sin_amigas.next();
			}
		}
		return res;
	}
}
