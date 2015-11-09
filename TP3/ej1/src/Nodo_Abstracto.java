import java.util.Set;

public abstract class Nodo_Abstracto {

	public int identidad;
	public Set<Nodo_Coloreable> adyacentes;
	abstract public void agregar_adyacentes(Nodo_Abstracto otro);

}
