
public class Arista {
	public NodoConVecinos n1;
	public NodoConVecinos n2;
	
	Arista(NodoConVecinos n1, NodoConVecinos n2)
	{
		this.n1 = n1;
		this.n2 = n2;
	}
	
	Arista(){};
	
	@Override
	public boolean equals(Object otherArista){
	    //if (otherArista == null) return false;
	    if (otherArista == this) return true;
	    if (!(otherArista instanceof Arista)) return false;
	    Arista other = (Arista)otherArista;
		return (this.n1 == other.n1 && 
		this.n2 == other.n2)
		||
		(this.n1 == other.n2 &&
		this.n2 == other.n1);
	}
}
