import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Lector {
	private ArrayList<Nodo_Coloreable_ej2> nodos_del_grafo;
	private ArrayList<Nodo_Coloreable> grafo_2colores;
	private BufferedReader is;
	private int cantidad_colores;
	private int cantidad_nodos;
	private int cantidad_aristas;

	public int cantNodos(){
		return cantidad_nodos;
	}

	public ArrayList<Nodo_Coloreable_ej2> getGrafo(){
		return nodos_del_grafo;
	}

	public ArrayList<Nodo_Coloreable> getGrafo2colores(){
		return grafo_2colores;
	}

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
	}

	public void inicializar_lector() throws IOException{
		String parametros = this.leer_palabra();
		String [] parametros_procesados = parametros.split(" ");
		this.cantidad_nodos = Integer.parseInt(parametros_procesados[0]);
		this.cantidad_aristas = Integer.parseInt(parametros_procesados[1]);
		this.cantidad_colores = Integer.parseInt(parametros_procesados[2]);

		for(int i = 0; i < this.cantidad_nodos; i++){
			String nodo_string = this.leer_palabra();
			String [] nodo_string_procesado = nodo_string.split(" ");

			//creo nodo con todos los colores
			int cantidad_colores_nodo = Integer.parseInt(nodo_string_procesado[0]);
			Nodo_Coloreable_ej2 nuevo = new Nodo_Coloreable_ej2(i, cantidad_colores_nodo);

			for(int j = 1; j <= nuevo.cantidad_colores; j++){
				nuevo.colores.add(Integer.parseInt(nodo_string_procesado[j]));
			}
			this.nodos_del_grafo.add(nuevo);

			//creo nodo de 2 colores
			Nodo_Coloreable nodo2color = new Nodo_Coloreable(i);
			nodo2color.cantidad_colores = 2;
			if (cantidad_colores_nodo < 2) {
				nodo2color.cantidad_colores = 1;
			}
			this.grafo_2colores.add(nodo2color);
		}
		for(i = 0; i < this.cantidad_aristas; i++){
			String arista_string = this.leer_palabra();
			String [] arista_string_procesada = arista_string.split(" ");
			int nodo_1 = Integer.parseInt(arista_string_procesada[0]);
			int nodo_2 = Integer.parseInt(arista_string_procesada[1]);
			Nodo_Coloreable n_1 = this.grafo_2colores.get(nodo_1);
			Nodo_Coloreable n_2 = this.grafo_2colores.get(nodo_2);
			n_1.adyacentes.add(n_2);
			n_2.adyacentes.add(n_1);
		}
	}
}
