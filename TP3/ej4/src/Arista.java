
public class Arista {
	private NodoConVecinos n1;
	private NodoConVecinos n2;
	
	
	public NodoConVecinos getN1() {
		return n1;
	}
	public NodoConVecinos getN2() {
		return n2;
	}
	Arista(NodoConVecinos n1, NodoConVecinos n2){
		assert n1 != n2;
		if (n1.getId() < n2.getId()){
			this.n1 = n1;
			this.n2 = n2;
		}else{
			this.n1 = n2;
			this.n2 = n1;
		}
	}
	
	//Arista(){};
	
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
