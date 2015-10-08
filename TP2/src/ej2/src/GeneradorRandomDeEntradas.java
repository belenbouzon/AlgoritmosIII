import java.util.LinkedHashSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class GeneradorRandomDeEntradas {
	private Random numeros;
	private int pisos;
	private int longitud_pasillo;
	private int cantidad_portales;
	private boolean contiene_cero;
	private boolean contiene_sima;
	private int ramdom_1;
	private int ramdom_2;
	public GeneradorRandomDeEntradas(int p,int longit, int cant){
		this.pisos = p;
		this.longitud_pasillo = longit;
		this.cantidad_portales = cant;
		//this.numeros = new Random(391);
		this.numeros = new Random();
	}
	public void imprimir_entrada_2(){
		System.out.printf("%d %d\n", this.pisos,this.longitud_pasillo);
		LinkedHashSet<DobleTupla> ya_utilizados = new LinkedHashSet<DobleTupla>();
		for(int i=0;i<this.cantidad_portales;i++){
			DobleTupla t = new DobleTupla(this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.pisos+1));
			while(ya_utilizados.contains(t)){
				t = new DobleTupla(this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.pisos+1));
			}
			System.out.printf("%d %d %d %d", t.a,t.b,t.c,t.d);
			if(i!=this.cantidad_portales-1){
				System.out.printf(";");
			}
		}
	}
	public void imprimir_entrada(){
		System.out.printf("%d %d\n", this.pisos,this.longitud_pasillo);
		TreeMap<Integer,TreeSet<Integer>> ya_utilizados = new TreeMap<Integer,TreeSet<Integer>>();
		for(int i=0;i<this.cantidad_portales-1;i++){
			int nuevo_piso = this.numeros.nextInt(this.pisos+1);
			int nueva_longitud = this.numeros.nextInt(this.longitud_pasillo+1);
			int nuevo_piso_2 = this.numeros.nextInt(this.pisos+1);
			int nueva_longitud_2 = this.numeros.nextInt(this.longitud_pasillo+1);
			
			while(ya_utilizados.containsKey(nuevo_piso)){
				nuevo_piso = this.numeros.nextInt(this.pisos+1);
			}
			
			if(i==0){
				this.ramdom_1 = nuevo_piso;
			}
			if(i==1){
				this.ramdom_2 = nuevo_piso;
			}
			if(nuevo_piso==0){
				this.contiene_cero = true;
			}
			if(nuevo_piso==this.pisos){
				this.contiene_sima = true;
			}
			
			if(ya_utilizados.containsKey(nuevo_piso)){
				TreeSet<Integer> subconjunto = ya_utilizados.get(nuevo_piso);
				while(subconjunto.contains(nueva_longitud)){
					nueva_longitud = this.numeros.nextInt(this.longitud_pasillo+1);
				}
			}else{
				TreeSet<Integer> subconjunto = new TreeSet<Integer>();
				subconjunto.add(nueva_longitud);
				ya_utilizados.put(nuevo_piso, subconjunto);
			}
			System.out.printf(" %d %d %d %d;", nuevo_piso,nueva_longitud,nuevo_piso_2,nueva_longitud_2);
		}
		if(!this.contiene_cero){
			System.out.printf(" %d %d %d %d;", 0,this.numeros.nextInt(this.longitud_pasillo+1),this.ramdom_1,this.numeros.nextInt(this.longitud_pasillo+1));
		}else{
			System.out.printf(" %d %d %d %d;", this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.longitud_pasillo+1),this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.longitud_pasillo+1));
		}
		if(!this.contiene_sima){
			System.out.printf(" %d %d %d %d", this.pisos,this.numeros.nextInt(this.longitud_pasillo+1),this.ramdom_2,this.numeros.nextInt(this.longitud_pasillo+1));
		}else{
			System.out.printf(" %d %d %d %d", this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.longitud_pasillo+1),this.numeros.nextInt(this.pisos+1),this.numeros.nextInt(this.longitud_pasillo+1));
		}
	}
	public void imprimir_entrada_3(){
		System.out.printf("%d %d\n", this.pisos,this.longitud_pasillo);
		LinkedHashSet<DobleTupla> ya_utilizados = new LinkedHashSet<DobleTupla>();
		int ultimo = 0;
		boolean ultimo_considerado = false;
		for(int i=0;i<this.cantidad_portales;i++){
			int nuevo_pasillo_1 = this.numeros.nextInt(this.pisos+1);
			int nuevo_piso = this.numeros.nextInt(this.pisos+1);
			int nuevo_pasillo_2 = this.numeros.nextInt(this.pisos+1);
			DobleTupla t = new DobleTupla(ultimo,nuevo_pasillo_1,nuevo_piso,nuevo_pasillo_2);
			while(ya_utilizados.contains(t)){
				nuevo_pasillo_1 = this.numeros.nextInt(this.pisos+1);
				nuevo_piso = this.numeros.nextInt(this.pisos+1);
				nuevo_pasillo_2 = this.numeros.nextInt(this.pisos+1);
				t = new DobleTupla(ultimo,nuevo_pasillo_1,nuevo_piso,nuevo_pasillo_2);
			}
			if(nuevo_piso == this.pisos){
				ultimo_considerado = true;
			}
			if(i==this.cantidad_portales-1 && !ultimo_considerado){
				nuevo_piso = this.pisos;
			}
			System.out.printf(" %d %d %d %d", ultimo,nuevo_pasillo_1,nuevo_piso,nuevo_pasillo_2);
			ultimo = nuevo_piso;
			ya_utilizados.add(t);
			if(i!=this.cantidad_portales-1){
				System.out.printf(";");
			}
		}
	}
	public String imprimir_entrada_a_string(){
		String res = "";
		//System.out.printf("%d %d\n", this.pisos,this.longitud_pasillo);
		LinkedHashSet<DobleTupla> ya_utilizados = new LinkedHashSet<DobleTupla>();
		int ultimo = 0;
		boolean ultimo_considerado = false;
		for(int i=0;i<this.cantidad_portales;i++){
			int nuevo_pasillo_1 = this.numeros.nextInt(this.pisos+1);
			int nuevo_piso = this.numeros.nextInt(this.pisos+1);
			int nuevo_pasillo_2 = this.numeros.nextInt(this.pisos+1);
			DobleTupla t = new DobleTupla(ultimo,nuevo_pasillo_1,nuevo_piso,nuevo_pasillo_2);
			while(ya_utilizados.contains(t)){
				nuevo_pasillo_1 = this.numeros.nextInt(this.pisos+1);
				nuevo_piso = this.numeros.nextInt(this.pisos+1);
				nuevo_pasillo_2 = this.numeros.nextInt(this.pisos+1);
				t = new DobleTupla(ultimo,nuevo_pasillo_1,nuevo_piso,nuevo_pasillo_2);
			}
			if(nuevo_piso == this.pisos){
				ultimo_considerado = true;
			}
			if(i==this.cantidad_portales-1 && !ultimo_considerado){
				nuevo_piso = this.pisos;
			}
			res += " ";
			res += Integer.toString(ultimo);
			res += " ";
			res += Integer.toString(nuevo_pasillo_1);
			res += " ";
			res += Integer.toString(nuevo_piso);
			res += " ";
			res += Integer.toString(nuevo_pasillo_2);
			
			
			//System.out.printf(" %d %d %d %d", ultimo,nuevo_pasillo_1,nuevo_piso,nuevo_pasillo_2);
			ultimo = nuevo_piso;
			ya_utilizados.add(t);
			if(i!=this.cantidad_portales-1){
				res += ";";
			}
		}
		return res;
	}
	public static void main(String [] entrada){
		GeneradorRandomDeEntradas entrada_generada = new GeneradorRandomDeEntradas(Integer.parseInt(entrada[0]),Integer.parseInt(entrada[1]),Integer.parseInt(entrada[2]));
		entrada_generada.imprimir_entrada_3();
	}
	
}
