import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Lector {
	private ArrayList<Nodo_Coloreable> _nodos_del_grafo;
	private BufferedReader is;
	private int _cantidad_colores;
	private int _cantidad_nodos;
	private int _cantidad_aristas;
	private boolean cargado;

	//para testeos de tiempo

	String [] nodos_sin_procesar;
	String [] aristas_sin_procesar;

	public Lector(int cant_nodos,int cant_aristas,int cant_colores,String[] nodos,String[] aristas){
		this._cantidad_colores = cant_colores;
		this._cantidad_aristas = cant_aristas;
		this._cantidad_nodos = cant_nodos;
		this.nodos_sin_procesar = nodos;
		this.aristas_sin_procesar = aristas;
		this._nodos_del_grafo = new ArrayList<Nodo_Coloreable>(this._cantidad_nodos);
		this.is = null;
		this.cargado = true;
	}

	//.....................
	
	public int cantidad_nodos(){
		return this._cantidad_nodos;
	}
	
	public int cantidad_colores(){
		return this._cantidad_aristas;
	}
	
	public int cantidad_aristas(){
		return this._cantidad_colores;
	}
	
	public ArrayList<Nodo_Coloreable> nodos_del_grafo(){
		return this._nodos_del_grafo;
	}
	
	public String leer_palabra() throws IOException{
         return this.is.readLine();
	}
	
	public Lector(String archivo) throws IOException
	{
		try { 
			this.is = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(archivo)));
		} catch (RuntimeException e){
			throw new IOException ("No pudo hallarse el archivo especificado." + archivo, e);
		}
		this.cargado = false;
	}
	
	
	public static Lector crear_lector_cargado (String entrada) throws IOException{
		Lector res = new Lector(entrada);
		res.cargar_archivo();
		res.cargado = true;
		return res;
	}


	private void cargar_archivo() throws IOException{
		if(this.is==null){
			throw new IOException("error, lector no vinculado a archivo");
		}
		
		String parametros = this.leer_palabra();
		String [] parametros_procesados = parametros.split(" ");
		this._cantidad_nodos = Integer.parseInt(parametros_procesados[0]);
		this._cantidad_aristas = Integer.parseInt(parametros_procesados[1]);
		this._cantidad_colores = Integer.parseInt(parametros_procesados[2]);
		this._nodos_del_grafo = new ArrayList<Nodo_Coloreable>(_cantidad_nodos);

		this.nodos_sin_procesar = new String [this._cantidad_nodos];
		this.aristas_sin_procesar = new String [this._cantidad_aristas];

		for(int i = 0;i<this._cantidad_nodos;i++){
			this.nodos_sin_procesar[i] = this.leer_palabra();
		}
		for(int i = 0;i<this._cantidad_aristas;i++){
			this.aristas_sin_procesar[i] = this.leer_palabra();
		}
		this.is.close();
	}

	public void inicializar_lector() throws IOException{
		if(!this.cargado){
			this.cargar_y_procesar();
		}else{
			this.procesar_informacion_cargada();
		}
		
	}
	
	private void cargar_y_procesar() throws IOException{
		if(this.is==null){
			throw new IOException("error, lector no vinculado a archivo");
		}
		
		String parametros = this.leer_palabra();
		String [] parametros_procesados = parametros.split(" ");
		this._cantidad_nodos = Integer.parseInt(parametros_procesados[0]);
		this._cantidad_aristas = Integer.parseInt(parametros_procesados[1]);
		this._cantidad_colores = Integer.parseInt(parametros_procesados[2]);
		this._nodos_del_grafo = new ArrayList<Nodo_Coloreable>(_cantidad_nodos);
		for(int i = 0;i<this._cantidad_nodos;i++){
			String nodo_string = this.leer_palabra();
			String [] nodo_string_procesado = nodo_string.split(" ");
			Nodo_Coloreable nuevo = new Nodo_Coloreable(i);
			nuevo.cantidad_colores = Integer.parseInt(nodo_string_procesado[0]);
			for(int j=1;j<=nuevo.cantidad_colores;j++){
				nuevo.colores.agregar_color(Integer.parseInt(nodo_string_procesado[j]));
			}
			this._nodos_del_grafo.add(nuevo);
		}
		for(int i = 0;i<this._cantidad_aristas;i++){
			String arista_string = this.leer_palabra();
			String [] arista_string_procesada = arista_string.split(" ");
			int nodo_1 = Integer.parseInt(arista_string_procesada[0]);
			int nodo_2 = Integer.parseInt(arista_string_procesada[1]);
			Nodo_Coloreable n_1 = this._nodos_del_grafo.get(nodo_1);
			Nodo_Coloreable n_2 = this._nodos_del_grafo.get(nodo_2);
			n_1.adyacentes.add(n_2);
			n_2.adyacentes.add(n_1);
		}
		this.is.close();
	}
	
	private void procesar_informacion_cargada(){
		for(int i = 0;i<this._cantidad_nodos;i++){
			String nodo_string = this.nodos_sin_procesar[i];
			String [] nodo_string_procesado = nodo_string.split(" ");
			Nodo_Coloreable nuevo = new Nodo_Coloreable(i);
			nuevo.cantidad_colores = Integer.parseInt(nodo_string_procesado[0]);
			for(int j=1;j<=nuevo.cantidad_colores;j++){
				nuevo.colores.agregar_color(Integer.parseInt(nodo_string_procesado[j]));
			}
			this._nodos_del_grafo.add(nuevo);
		}
		for(int i = 0;i<this._cantidad_aristas;i++){
			String arista_string = this.aristas_sin_procesar[i];
			String [] arista_string_procesada = arista_string.split(" ");
			int nodo_1 = Integer.parseInt(arista_string_procesada[0]);
			int nodo_2 = Integer.parseInt(arista_string_procesada[1]);
			Nodo_Coloreable n_1 = this._nodos_del_grafo.get(nodo_1);
			Nodo_Coloreable n_2 = this._nodos_del_grafo.get(nodo_2);
			n_1.adyacentes.add(n_2);
			n_2.adyacentes.add(n_1);
		}
	}
}
