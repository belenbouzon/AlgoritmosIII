import java.util.LinkedHashSet;
import java.util.Set;

public class Nodo{
	public int identificacion;
	public Set<Nodo> aristas_caminado;
	public Set<Nodo> aristas_teletranspoorte;
	public int posicion;
	public boolean ya_calculado;
	public int camino_minimo;
	public int fantasma;
	
	public void agregar_punto_teletransporte(Nodo nod)
	{
		this.aristas_teletranspoorte.add(nod);
	}
	
	public static Nodo nodo_fantasma(int cantidad_ciclos,Nodo nodo_real,int camino)
	{
		Nodo res = new Nodo(-1,-1);
		res.fantasma = cantidad_ciclos-1;
		res.aristas_teletranspoorte.add(nodo_real);
		res.camino_minimo = camino;
		return res;
	}
	
	public Nodo(int n,int pos)
	{
		this.identificacion = n;
		this.posicion = pos;
		this.aristas_teletranspoorte = new LinkedHashSet<Nodo>();
		this.ya_calculado = false;
		this.camino_minimo = -1;
		this.fantasma = 0;
	}
}