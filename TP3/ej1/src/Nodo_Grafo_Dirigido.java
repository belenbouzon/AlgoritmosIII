import java.util.HashSet;
import java.util.Set;


public class Nodo_Grafo_Dirigido extends Comparable {
	public int identidad;
	public int color;
	public boolean valor_fijado;
	public boolean valor_de_verdad;
	public Nodo_Grafo_Dirigido hermano;
	public Set<Nodo_Grafo_Dirigido> adyacentes;
	public Nodo_Grafo_Dirigido(int id){
		this.identidad = id;
		this.adyacentes = new HashSet<Nodo>();
		this.valor_fijado = false;
	}
	public int compareTo(Nodo_Grafo_Dirigido otro){
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
		return (this.identidad*21 + this.color*37);
	}
}
