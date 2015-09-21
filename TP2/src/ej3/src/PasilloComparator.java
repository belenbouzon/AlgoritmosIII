import java.util.Comparator;

public class PasilloComparator implements Comparator<Pasillo>{
	
	public int compare( Pasillo x, Pasillo y )
    {
        return y.compareTo(x);
    }
}
