
public class Matrix {
	
	public Matrix(int filas, int columnas)
	{
		this.columnas = columnas;
		this.filas = filas;
	}

	private int filas;
	private int columnas;
	
	public int ReturnFila()
	{
		return this.filas;
	}
	
	public int ReturnColumna()
	{
		return this.columnas;
	}
}
