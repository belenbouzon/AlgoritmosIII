import java.util.Comparator;

public class ComparadorNodo implements Comparator<Nodo>{
	public int compare(Nodo n1, Nodo n2){
		return n1.piso-n2.piso;
	}
}