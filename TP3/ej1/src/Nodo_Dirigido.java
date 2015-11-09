import java.util.Set;


public abstract class Nodo_Dirigido extends Nodo_Abstracto {
	public int identidad;
	public boolean valor_fijado;
	public boolean formula_afirmativa;
	public Set<Nodo_Dirigido> adyacentes;
	public Set<Nodo_Dirigido> adyacentes_inverso;
	@Override
	public void agregar_adyacentes(Nodo_Abstracto otro) {
		Nodo_Dirigido casteado = (Nodo_Dirigido)otro;
		this.adyacentes.add(casteado);
		casteado.adyacentes.add(this);
	}
	
}
