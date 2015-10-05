import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;


public class GeneradorDeEntradasExtensible {
	private Random numeros;
	private int cantidad_pisos;
	private int longitud_pasillo;
	private int cantidad_portales;
	private PrintWriter salida_a_archivo;
	private String prefijo_salida;
	public GeneradorDeEntradasExtensible(int cantidadpisos,int longitudpasillos,int maximo_portales,String prefijo){
		this.cantidad_pisos = cantidadpisos;
		this.longitud_pasillo = longitudpasillos;
		this.cantidad_portales = maximo_portales;
		this.numeros = new Random();
		this.prefijo_salida = prefijo;
	}
	private void cambiar_archivo(String nombre) throws IOException{
		this.salida_a_archivo = new PrintWriter(new BufferedWriter(new FileWriter(nombre)));
	}
	private void imprimir_entrada() throws IOException{
		//System.out.printf("%d %d\n", this.cantidad_pisos,this.longitud_pasillo);
		LinkedHashSet<DobleTupla> ya_utilizados = new LinkedHashSet<DobleTupla>();
		int ultimo = 0;
		boolean ultimo_considerado = false;
		for(int i=0;i<this.cantidad_portales;i++){
			int nuevo_pasillo_1 = this.numeros.nextInt(this.cantidad_pisos+1);
			int nuevo_piso = this.numeros.nextInt(this.cantidad_pisos+1);
			int nuevo_pasillo_2 = this.numeros.nextInt(this.cantidad_pisos+1);
			DobleTupla t = new DobleTupla(ultimo,nuevo_pasillo_1,nuevo_piso,nuevo_pasillo_2);
			while(ya_utilizados.contains(t)){
				nuevo_pasillo_1 = this.numeros.nextInt(this.cantidad_pisos+1);
				nuevo_piso = this.numeros.nextInt(this.cantidad_pisos+1);
				nuevo_pasillo_2 = this.numeros.nextInt(this.cantidad_pisos+1);
				t = new DobleTupla(ultimo,nuevo_pasillo_1,nuevo_piso,nuevo_pasillo_2);
			}
			if(nuevo_piso == this.cantidad_pisos){
				ultimo_considerado = true;
			}
			if(i==this.cantidad_portales-1 && !ultimo_considerado){
				nuevo_piso = this.cantidad_pisos;
			}
			//System.out.printf(" %d %d %d %d", ultimo,nuevo_pasillo_1,nuevo_piso,nuevo_pasillo_2);
			ultimo = nuevo_piso;
			ya_utilizados.add(t);
		}
		int numero = 1;
		for(int i = 0; i<this.cantidad_portales;i++){
			String siguiente_archivo = this.prefijo_salida;
			String cadena = "";
			cadena = String.valueOf(numero);
			cadena= Integer.toString(numero);
			siguiente_archivo += "_";
			siguiente_archivo += cadena;
			siguiente_archivo += ".txt";
			numero++;
			this.cambiar_archivo(siguiente_archivo);
			String parametros_string = String.valueOf(this.cantidad_pisos);
			this.salida_a_archivo.write(parametros_string);
			parametros_string = String.valueOf(this.longitud_pasillo);
			this.salida_a_archivo.printf(" ");
			this.salida_a_archivo.write(parametros_string);
			Iterator<DobleTupla> it = ya_utilizados.iterator();
			this.salida_a_archivo.printf("\n");
			for(int j=0;j<=i;j++){
				DobleTupla a_escribir = it.next();
				this.salida_a_archivo.write(" ");
				String numero_string;
				numero_string = String.valueOf(a_escribir.a);
				this.salida_a_archivo.write(numero_string);
				this.salida_a_archivo.write(" ");
				numero_string = String.valueOf(a_escribir.b);
				this.salida_a_archivo.write(numero_string);
				this.salida_a_archivo.write(" ");
				numero_string = String.valueOf(a_escribir.c);
				this.salida_a_archivo.write(numero_string);
				this.salida_a_archivo.write(" ");
				numero_string = String.valueOf(a_escribir.d);
				this.salida_a_archivo.write(numero_string);
				if(j!=i){
					this.salida_a_archivo.write(";");
				}
			}
			this.salida_a_archivo.close();
		}
	}
	public static void main(String [] entrada) throws IOException{
		GeneradorDeEntradasExtensible gen = new GeneradorDeEntradasExtensible(Integer.parseInt(entrada[0]),Integer.parseInt(entrada[1]),Integer.parseInt(entrada[2]),entrada[3]);
		gen.imprimir_entrada();
	}
}
