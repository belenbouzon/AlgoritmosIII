import java.util.LinkedHashSet;
import java.util.Set;


public class Nodo_Grafo_Dirigido implements Comparable<Nodo_Grafo_Dirigido>{
	public int identidad;
	public int color;
	public int color_alternativo;
	public Nodo_Grafo_Dirigido hermano;
	public int id_componente_conexa;
	
	public boolean valor_fijado;
	public boolean formula_afirmativa;
	public Set<Nodo_Grafo_Dirigido> adyacentes;
	public Set<Nodo_Grafo_Dirigido> adyacentes_inverso;
	
	
	public void agregar_adyacentes(Nodo_Grafo_Dirigido otro){
		this.adyacentes.add(otro);
		otro.adyacentes_inverso.add(this);
	}
	
	
	public void print(){
		char signo = ' ';
		if(!this.formula_afirmativa){
			signo = '¬';
		}
		System.out.printf("nodo: %c%d color: %d comp.conexa:%d\n", signo,this.identidad,this.color,this.id_componente_conexa);
	}
	
	public Nodo_Grafo_Dirigido(int id,int color,boolean formula){
		this.identidad = id;
		this.adyacentes = new LinkedHashSet<Nodo_Grafo_Dirigido>();
		this.adyacentes_inverso = new LinkedHashSet<Nodo_Grafo_Dirigido>();
		this.valor_fijado = false;
		this.color = color;
		this.formula_afirmativa = formula;
		this.id_componente_conexa = -2;
	}
	
	
	@Override
	public int compareTo(Nodo_Grafo_Dirigido otro){
		if(this.identidad < otro.identidad){
			return -1;
		}else if(this.identidad > otro.identidad){
			return 1;
		}else if(this.color<otro.color){
			return -1;
		}else if(this.color > otro.color){
			return 1;
		}else if(!this.formula_afirmativa && otro.formula_afirmativa){
			return -1;
		}else if(this.formula_afirmativa && !otro.formula_afirmativa){
			return 1;
		}else{
			return 0;
		}
	}
	public boolean equals(Nodo_Grafo_Dirigido otro){
		Nodo_Grafo_Dirigido otro_casteado = (Nodo_Grafo_Dirigido) otro;
		if(this.identidad==otro_casteado.identidad && this.color == otro_casteado.color && this.formula_afirmativa==otro_casteado.formula_afirmativa){
			return true;
		}else{
			return false;
		}
	}
	public int hashCode(){
		return (this.identidad*17 + this.color*37);
	}
	
	
}
