import java.util.LinkedHashSet;
import java.util.Set;


public class Nodo_Dirigido_Compacto extends Nodo_Dirigido{
	public int identidad;
	public Set<Nodo_Dirigido_Compacto> hermanos;
	public Set<Nodo_Dirigido_SAT> componente_conexa;
	public Set<Nodo_Dirigido_Compacto> adyacentes;
	public Set<Nodo_Dirigido_Compacto> adyacentes_inverso;
	public int id_componente_conexa;
	private boolean valor_fijado;
	private boolean valor_de_verdad;
	
	public boolean valor_fijado_en(boolean valor){
		return (this.valor_fijado && this.valor_de_verdad==valor);
	}
	
	public boolean valor_esta_fijado(){
		return this.valor_fijado;
	}
	
	public void agregar_hermano(Nodo_Dirigido_Compacto otro){
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
	

	public void agregar_adyacentes(Nodo_Dirigido_Compacto otro){
		this.adyacentes.add(otro);
		otro.adyacentes_inverso.add(this);
	}
	
	public Nodo_Dirigido_Compacto(int id,int id_componente_conexa,Set<Nodo_Dirigido_SAT> componente_conexa){
		this.identidad = id;
		this.adyacentes = new LinkedHashSet<Nodo_Dirigido_Compacto>();
		this.adyacentes_inverso = new LinkedHashSet<Nodo_Dirigido_Compacto>();
		this.hermanos = new LinkedHashSet<Nodo_Dirigido_Compacto>();
		//this.valor_fijado = false;
		this.id_componente_conexa = id_componente_conexa;
		this.componente_conexa = componente_conexa;
		
		this.valor_fijado = false;
		this.valor_de_verdad = false;
		
		//this.valores_de_verdad = new Valores_de_Verdad();
		
	}
	
}
