package algo3.tp3.ej4;

public class Swap {
	NodoConVecinos n;
	int mejora;
	public Swap() {
		this.n = null;
		this.mejora = 0;
	}
	public Swap(NodoConVecinos n, int mejora){
		this.n = n;
		this.mejora = mejora;
	}
	public NodoConVecinos getN() {
		return n;
	}
	public void setN(NodoConVecinos n) {
		this.n = n;
	}
	public int getMejora() {
		return mejora;
	}
	public void setMejora(int mejora) {
		this.mejora = mejora;
	}
	
}
