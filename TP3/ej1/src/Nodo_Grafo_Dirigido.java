import java.util.HashSet;
import java.util.Set;


public class Nodo_Grafo_Dirigido implements Comparable {
	public int identidad;
	public int color;
	public boolean valor_fijado;
	public boolean valor_de_verdad;
	public Nodo_Grafo_Dirigido hermano;
	public Set<Nodo_Grafo_Dirigido> adyacentes;
	public Set<Nodo_Grafo_Dirigido> adyacentes_inverso;
	public void agregar_adyacentes(Nodo_Grafo_Dirigido otro){
		this.adyacentes.add(otro);
		otro.adyacentes_inverso.add(this);
	}
	public Nodo_Grafo_Dirigido(int id){
		this.identidad = id;
		this.adyacentes = new HashSet<Nodo_Grafo_Dirigido>();
		this.adyacentes_inverso = new HashSet<Nodo_Grafo_Dirigido>();
		this.valor_fijado = false;
	}
	public Nodo_Grafo_Dirigido(int id,int color){
		this.identidad = id;
		this.adyacentes = new HashSet<Nodo_Grafo_Dirigido>();
		this.adyacentes_inverso = new HashSet<Nodo_Grafo_Dirigido>();
		this.valor_fijado = false;
		this.color = color;
	}
	@Override
	public int compareTo(Object arg0){
		Nodo_Grafo_Dirigido otro = (Nodo_Grafo_Dirigido) arg0;
		if(this.identidad < otro.identidad){
			return -1;
		}else if(this.identidad > otro.identidad){
			return 1;
		}else if(this.color<otro.color){
			return -1;
		}else if(this.color > otro.color){
			return 1;
		}else{
			return 0;
		}
	}
	public boolean equals(Object otro){
		if(otro.getClass()!=this.getClass()){
			return false;
		}
		Nodo_Grafo_Dirigido otro_casteado = (Nodo_Grafo_Dirigido) otro;
		if(this.identidad==otro_casteado.identidad && this.color == otro_casteado.color){
			return true;
		}else{
			return false;
		}
	}
	public int hashCode(){
		return (this.identidad*17 + this.color*37);
	}
	
}
