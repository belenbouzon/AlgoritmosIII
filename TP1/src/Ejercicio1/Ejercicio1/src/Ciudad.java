import java.util.Comparator;

public class Ciudad implements Comparator<Ciudad>{
	public Ciudad(int km) { this.km = km;}
	public Ciudad() {}
	private int km;
	public int GetKilometro()
	{
		return this.km;
	}
	
	@Override
	public int compare(Ciudad arg0, Ciudad arg1) 
	{
		return arg0.GetKilometro() - arg1.GetKilometro();
	}
}
