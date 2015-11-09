import java.util.Arrays;
import java.util.LinkedList;

public class Nodo 
{
	private int id;
	private boolean visitado;
	private int color;
	private boolean[] seguimientoColoresTotales; //Almacena todos los colores seteando un bool para los que tiene.
	private LinkedList<Integer> coloresDescartados;
	private LinkedList<Integer> coloresRestantes;
	
	public Nodo(int j, int cantidadDeColoresDelGrafo, int[] colores)  {
		this.setColores(colores, cantidadDeColoresDelGrafo);
		this.setId(j);
		this.setColor(-1);
		this.setVisitado(false);
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public int getColor() 
	{
		return color;
	}
	public void setColor(int color) 
	{
		this.color = color;
	}
	public void TacharColor(int color)
	{
		coloresRestantes.removeFirstOccurrence(color); //O(c)
		coloresDescartados.add(color); //O(1)
		seguimientoColoresTotales[color] = false; //O(1)
	}
	
	/*El nodo lleva seguimiento de 4 estructuras de colores: una de una lista con los colores habilitados para �l, para iterar facilmente. La otra,
	 con todos los colores que tenga el grafo, seteada en true en aquellas posiciones que respondan a colores propios, permite preguntar en O(1) si el nodo
	 permite ser pintado de determinado color. Por ultimo, coloresDescartados almacena los que se decidio no utilizar de sus colores posible y coloresRestantes, aquellos
	 que todavia no fueron descartados*/
	public void setColores(int[] coloresPosibles, int cantidadDeColoresDelGrafo) 
	{
		this.coloresDescartados = new LinkedList<Integer>();
		this.coloresRestantes = new LinkedList<Integer>();
		this.seguimientoColoresTotales = new boolean[cantidadDeColoresDelGrafo+1]; //O(n)
		Arrays.fill(this.seguimientoColoresTotales, false);//O(n)

		for (int i = 0; i < coloresPosibles.length; i++)
		{
			this.coloresRestantes.add(coloresPosibles[i]);
			this.seguimientoColoresTotales[i] = true;
		}
	}
	public boolean isVisitado() 
	{
		return visitado;
	}
	public void setVisitado(boolean visitado) 
	{
		this.visitado = visitado;
	}
	public boolean[] getSeguimientoColoresTotales()
	{
		return seguimientoColoresTotales;
	}
	public LinkedList<Integer> getColoresDescartados()
	{
		return coloresDescartados;
	}
	public void setColoresDescartados(LinkedList<Integer> coloresDescartados) 
	{
		this.coloresDescartados = coloresDescartados;
	}
	public LinkedList<Integer> getColoresRestantes() 
	{
		return coloresRestantes;
	}
}
