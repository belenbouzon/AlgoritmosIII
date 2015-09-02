import java.util.Comparator;


public class ComparadorExploradoras implements Comparator<Exploradora> {
	public int compare(Exploradora ex1,Exploradora ex2){
		return (ex1.letra-ex2.letra);
	}
}
