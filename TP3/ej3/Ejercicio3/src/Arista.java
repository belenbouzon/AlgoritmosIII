

public class Arista 
{
	public int desde;
	public int hasta;
	
	Arista(int desde, int hasta)
	{
		this.desde = desde;
		this.hasta = hasta;
	}
	
	Arista(){};
	
	@Override
	public boolean equals(Object otherArista){
	    if (otherArista == null) return false;
	    if (otherArista == this) return true;
	    if (!(otherArista instanceof Arista))return false;
	    Arista other = (Arista)otherArista;
		return (this.desde == other.desde && 
		this.hasta == other.hasta)
		||
		(this.desde == other.hasta &&
		this.hasta == other.desde);
	}
}
