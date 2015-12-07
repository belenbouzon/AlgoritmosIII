package algo3.tp3.ej4;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AristaEj4 {
	private NodoConVecinos n1;
	private NodoConVecinos n2;
	
	
	public NodoConVecinos getN1() {
		return n1;
	}
	public NodoConVecinos getN2() {
		return n2;
	}
	AristaEj4(NodoConVecinos n1, NodoConVecinos n2) throws Exception{
		if ((n1 == n2) || (n1.getId() == n2.getId()))
				throw new Exception ("No puede haber aristas que unen al nodo consigo mismo");
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
	    if (!(otherArista instanceof AristaEj4)) return false;
	    AristaEj4 other = (AristaEj4)otherArista;
		return (this.n1 == other.n1 && 
		this.n2 == other.n2)
		||
		(this.n1 == other.n2 &&
		this.n2 == other.n1);
	}
	
	// http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(n1).
            append(n2).
            toHashCode();
    }
    
}
