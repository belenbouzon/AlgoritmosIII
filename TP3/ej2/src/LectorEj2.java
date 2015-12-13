import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;


public class LectorEj2 {
	private ArrayList<Nodo_Coloreable_ej2> nodos_del_grafo;
	private ArrayList<Nodo_Coloreable> grafo_2colores;
	private BufferedReader is;
	private int cantidad_nodos;
	private int cantidad_aristas;
	private int cantidad_colores;
	private ArrayDeque<Integer> cola;
	private boolean podas;

	public int cantNodos(){
		return cantidad_nodos;
	}

	public int cantAristas(){
		return cantidad_aristas;
	}

	public int cantColores(){
		return cantidad_colores;
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

	public LectorEj2(String archivo, boolean p) throws Exception
	{
		try {
			this.is = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(archivo)));
			this.cola = new ArrayDeque<Integer>();
			this.podas = p;
		}
		catch (RuntimeException e) {throw new Exception ("No pudo hallarse el archivo especificado." + archivo);}
	}

	public boolean inicializar_lector() throws IOException{
		//Devuelve false si al preprocesar el grafo no tiene solucion.
		String parametros = this.leer_palabra();
		String [] parametros_procesados = parametros.split(" ");
		this.cantidad_nodos = Integer.parseInt(parametros_procesados[0]);
		this.cantidad_aristas = Integer.parseInt(parametros_procesados[1]);
		this.cantidad_colores = Integer.parseInt(parametros_procesados[2]);
		
		this.nodos_del_grafo = new ArrayList<Nodo_Coloreable_ej2>(cantidad_nodos);
		this.grafo_2colores = new ArrayList<Nodo_Coloreable>(cantidad_nodos);

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
			nodo2color.colores.agregar_color(nuevo.colores.get(0));
			if (cantidad_colores_nodo == 1){ 
				nodo2color.cantidad_colores = 1;
				if(this.podas){ this.cola.add(i); }
			}else{
				//si hay mas de un color agrego el segundo.
				nodo2color.cantidad_colores = 2;
				nodo2color.colores.agregar_color(nuevo.colores.get(1));
			}
			this.grafo_2colores.add(nodo2color);
		}
		for(int i = 0; i < this.cantidad_aristas; i++){
			String arista_string = this.leer_palabra();
			String [] arista_string_procesada = arista_string.split(" ");
			int nodo_1 = Integer.parseInt(arista_string_procesada[0]);
			int nodo_2 = Integer.parseInt(arista_string_procesada[1]);
			Nodo_Coloreable n_1 = this.grafo_2colores.get(nodo_1);
			Nodo_Coloreable n_2 = this.grafo_2colores.get(nodo_2);
			n_1.adyacentes.add(n_2);
			n_2.adyacentes.add(n_1);
		}
		
		if(podas){
			return this.preprocesarNodos1Color();
		}else{
			return true;
		}
	}
	
	private boolean preprocesarNodos1Color(){
		Nodo_Coloreable_ej2 nodo;
		Nodo_Coloreable_ej2 vecino;
		Nodo_Coloreable nodo2color;
		Integer color;
		int indice;
		while(!this.cola.isEmpty()){
			indice = this.cola.remove();
			nodo = this.nodos_del_grafo.get(indice);
			nodo2color = this.grafo_2colores.get(indice);
			color = nodo.colores.get(0);
			Iterator<Nodo_Coloreable> it = nodo2color.adyacentes.iterator();
			while(it.hasNext()){
				//recorro todos los vecinos
				nodo2color = (Nodo_Coloreable) it.next();
				vecino = this.nodos_del_grafo.get(nodo2color.identidad);
				if(vecino.colores.remove(color)){
					vecino.cantidad_colores--;				
					if(vecino.cantidad_colores == 1){
						nodo2color.cantidad_colores = 1;
						this.cola.add(vecino.identidad);
					}
				}
				if(vecino.cantidad_colores == 1 && vecino.colores.get(0) == color || vecino.cantidad_colores == 0){
					//hay dos nodos vecinos que tienen un solo color y es el mismo. Si tiene 0 es porque borro el unico color.
					return false;
				}
			}
		}
		return true;
	}
}
