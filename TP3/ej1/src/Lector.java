import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Lector {
	public ArrayList<Nodo_Coloreable> nodos_del_grafo;
	private BufferedReader is;
	//private static String path;
	public int cantidad_colores;
	public int cantidad_nodos;
	public int cantidad_aristas;

	//para testeos de tiempo

	String [] nodos_sin_procesar;
	String [] aristas_sin_procesar;

	public Lector(int cant_nodos,int cant_aristas,int cant_colores,String[] nodos,String[] aristas){
		this.cantidad_colores = cant_colores;
		this.cantidad_aristas = cant_aristas;
		this.cantidad_nodos = cant_nodos;
		this.nodos_sin_procesar = nodos;
		this.aristas_sin_procesar = aristas;
		this.nodos_del_grafo = new ArrayList<Nodo_Coloreable>(this.cantidad_nodos);
	}

	//.....................
	
	public String leer_palabra() throws IOException{
        try{
            return this.is.readLine();
        } catch(IOException e){
            return null;
        }
	}
	public Lector(String archivo) throws IOException
	{
		try { this.is = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(archivo)));}
		catch (RuntimeException e) {throw new IOException ("No pudo hallarse el archivo especificado." + archivo);}
		//this.path = getClass().getResource( "" ).getPath();
	}
	public void inicializar_lector() throws IOException{
		String parametros = this.leer_palabra();
		String [] parametros_procesados = parametros.split(" ");
		this.cantidad_nodos = Integer.parseInt(parametros_procesados[0]);
		this.cantidad_aristas = Integer.parseInt(parametros_procesados[1]);
		this.cantidad_colores = Integer.parseInt(parametros_procesados[2]);
		this.nodos_del_grafo = new ArrayList<Nodo_Coloreable>(cantidad_nodos);
		for(int i = 0;i<this.cantidad_nodos;i++){
			String nodo_string = this.leer_palabra();
			String [] nodo_string_procesado = nodo_string.split(" ");
			Nodo_Coloreable nuevo = new Nodo_Coloreable(i);
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
			Nodo_Coloreable n_1 = this.nodos_del_grafo.get(nodo_1);
			Nodo_Coloreable n_2 = this.nodos_del_grafo.get(nodo_2);
			n_1.adyacentes.add(n_2);
			n_2.adyacentes.add(n_1);
		}
	}


	public void cargar_archivo() throws IOException{
		String parametros = this.leer_palabra();
		String [] parametros_procesados = parametros.split(" ");
		this.cantidad_nodos = Integer.parseInt(parametros_procesados[0]);
		this.cantidad_aristas = Integer.parseInt(parametros_procesados[1]);
		this.cantidad_colores = Integer.parseInt(parametros_procesados[2]);
		this.nodos_del_grafo = new ArrayList<Nodo_Coloreable>(cantidad_nodos);

		this.nodos_sin_procesar = new String [this.cantidad_nodos];
		this.aristas_sin_procesar = new String [this.cantidad_aristas];

		for(int i = 0;i<this.cantidad_nodos;i++){
			this.nodos_sin_procesar[i] = this.leer_palabra();
		}
		for(int i = 0;i<this.cantidad_aristas;i++){
			this.aristas_sin_procesar[i] = this.leer_palabra();
		}
	}

	public void procesar_datos(){
		for(int i = 0;i<this.cantidad_nodos;i++){
			String nodo_string = this.nodos_sin_procesar[i];
			String [] nodo_string_procesado = nodo_string.split(" ");
			Nodo_Coloreable nuevo = new Nodo_Coloreable(i);
			nuevo.cantidad_colores = Integer.parseInt(nodo_string_procesado[0]);
			for(int j=1;j<=nuevo.cantidad_colores;j++){
				nuevo.colores.agregar_color(Integer.parseInt(nodo_string_procesado[j]));
			}
			this.nodos_del_grafo.add(nuevo);
		}
		for(int i = 0;i<this.cantidad_aristas;i++){
			String arista_string = this.aristas_sin_procesar[i];
			String [] arista_string_procesada = arista_string.split(" ");
			int nodo_1 = Integer.parseInt(arista_string_procesada[0]);
			int nodo_2 = Integer.parseInt(arista_string_procesada[1]);
			Nodo_Coloreable n_1 = this.nodos_del_grafo.get(nodo_1);
			Nodo_Coloreable n_2 = this.nodos_del_grafo.get(nodo_2);
			n_1.adyacentes.add(n_2);
			n_2.adyacentes.add(n_1);
		}
	}
}
