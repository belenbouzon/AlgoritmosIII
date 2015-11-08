import java.util.LinkedHashSet;
import java.util.Set;


public class Nodo_Grafo_Dirigido_2{
	public int identidad;
	public Set<Nodo_Grafo_Dirigido_2> hermanos;
	public Set<Nodo_Grafo_Dirigido> componente_conexa;
	public Set<Nodo_Grafo_Dirigido_2> adyacentes;
	public Set<Nodo_Grafo_Dirigido_2> adyacentes_inverso;
	public int id_componente_conexa;
	private boolean valor_fijado;
	private boolean valor_de_verdad;
	
	public boolean valor_fijado_en(boolean valor){
		return (this.valor_fijado && this.valor_de_verdad==valor);
	}
	
	public boolean valor_esta_fijado(){
		return this.valor_fijado;
	}
	
	public void agregar_hermano(Nodo_Grafo_Dirigido_2 otro){
		this.hermanos.add(otro);
		otro.hermanos.add(this);
	}
	
	public boolean fijar_valores_de_verdad(boolean valor){
		if(this.valor_fijado && this.valor_de_verdad!=valor){
			return false;
		}
		this.valor_fijado = true;
		this.valor_de_verdad = valor;
		return true;
	}
	

	public void agregar_adyacentes(Nodo_Grafo_Dirigido_2 otro){
		this.adyacentes.add(otro);
		otro.adyacentes_inverso.add(this);
	}
	
	public Nodo_Grafo_Dirigido_2(int id,int id_componente_conexa,Set<Nodo_Grafo_Dirigido> componente_conexa){
		this.identidad = id;
		this.adyacentes = new LinkedHashSet<Nodo_Grafo_Dirigido_2>();
		this.adyacentes_inverso = new LinkedHashSet<Nodo_Grafo_Dirigido_2>();
		this.hermanos = new LinkedHashSet<Nodo_Grafo_Dirigido_2>();
		//this.valor_fijado = false;
		this.id_componente_conexa = id_componente_conexa;
		this.componente_conexa = componente_conexa;
		
		this.valor_fijado = false;
		this.valor_de_verdad = false;
		
		//this.valores_de_verdad = new Valores_de_Verdad();
		
	}
	
}
