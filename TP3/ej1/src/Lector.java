import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Lector {
	public ArrayList<Nodo> nodos_del_grafo;
	private BufferedReader is;
	//private static String path;
	public int cantidad_colores;
	public int cantidad_nodos;
	public int cantidad_aristas;
	
	public String leer_palabra() throws IOException{
        try{
            return this.is.readLine();
        } catch(IOException e){
            return null;
        }
	}
	public Lector(String archivo) throws Exception
	{
		try { this.is = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(archivo)));}
		catch (RuntimeException e) {throw new Exception ("No pudo hallarse el archivo especificado." + archivo);}
		//this.path = getClass().getResource( "" ).getPath();
	}
	public void inicializar_lector() throws IOException{
		String parametros = this.leer_palabra();
		String [] parametros_procesados = parametros.split(" ");
		this.cantidad_nodos = Integer.parseInt(parametros_procesados[0]);
		this.cantidad_aristas = Integer.parseInt(parametros_procesados[1]);
		this.cantidad_colores = Integer.parseInt(parametros_procesados[2]);
		for(int i = 0;i<this.cantidad_nodos;i++){
			String nodo_string = this.leer_palabra();
			String [] nodo_string_procesado = nodo_string.split(" ");
			Nodo nuevo = new Nodo(i);
			nuevo.cantidad_colores = Integer.parseInt(nodo_string_procesado[0]);
			for(int j=1;j<=nuevo.cantidad_colores;j++){
				nuevo.colores.agregar_color(Integer.parseInt(nodo_string_procesado[j]));
			}
			this.nodos_del_grafo.add(nuevo);
		}
		for(int i = 0;i<this.cantidad_aristas;i++){
			String arista_string = this.leer_palabra();
			String [] arista_string_procesada = arista_string.split(" ");
			int nodo_1 = Integer.parseInt(arista_string_procesada[0]);
			int nodo_2 = Integer.parseInt(arista_string_procesada[1]);
			Nodo n_1 = this.nodos_del_grafo.get(nodo_1);
			Nodo n_2 = this.nodos_del_grafo.get(nodo_2);
			n_1.adyacentes.add(n_2);
			n_2.adyacentes.add(n_1);
		}
	}
}
