
public class Nodo 
{
	private int id;
	private boolean visitado;
	private int padre;
	private int color;
	private int[] coloresPosibles;
	private int cantidadDeColoresPosibles;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPadre() {
		return padre;
	}
	public void setPadre(int padre) {
		this.padre = padre;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int[] getColoresPosibles() {
		return coloresPosibles;
	}
	public void setColoresPosibles(int[] coloresPosibles) {
		this.coloresPosibles = coloresPosibles;
	}
	public int getCantidadDeColoresPosibles() {
		return cantidadDeColoresPosibles;
	}
	public void setCantidadDeColoresPosibles(int cantidadDeColoresPosibles) {
		this.cantidadDeColoresPosibles = cantidadDeColoresPosibles;
	}
	public boolean isVisitado() {
		return visitado;
	}
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	
}
