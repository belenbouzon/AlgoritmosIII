package algo3.tp3.ej3;


public class Arista 
{
	public int desde;
	public int hasta;
	
	Arista(int desde, int hasta)
	{
		this.desde = desde;
		this.hasta = hasta;
	}
	
	Arista(){}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if(this.desde > this.hasta){
			result = prime * result + desde;
			result = prime * result + hasta;
			return result;
		}else{
			result = prime * result + hasta;
			result = prime * result + desde;
			return result;
		}
	}

	
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
