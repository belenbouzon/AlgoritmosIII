import java.util.Random;


public class Arista 
{
	public int pisoDesde;
	public int distanciaDesde;
	public int pisoHasta;
	public int distanciaHasta;
	
	Arista(int cantPisos, int longitudPasillos)
	{
		Random random = new Random();
		//Arista arista = new Arista();
		this.pisoHasta = random.nextInt(cantPisos+1);
		this.pisoDesde = random.nextInt(cantPisos+1);
		this.distanciaDesde = random.nextInt(longitudPasillos+1);
		this.distanciaHasta = random.nextInt(longitudPasillos+1);
		while(this.pisoHasta == this.pisoDesde && this.distanciaDesde == this.distanciaHasta)
		{
			this.distanciaHasta = random.nextInt(longitudPasillos+1);
		}
	}
	
	Arista(){};
	
	@Override
	public boolean equals(Object otherArista){
	    if (otherArista == null) return false;
	    if (otherArista == this) return true;
	    if (!(otherArista instanceof Arista))return false;
	    Arista other = (Arista)otherArista;
		return (this.pisoDesde == other.pisoDesde && 
		this.pisoHasta == other.pisoHasta  &&
		this.distanciaDesde == other.distanciaDesde &&
		this.distanciaHasta == other.distanciaHasta) 
		||
		(this.pisoDesde == other.pisoHasta &&
		this.distanciaDesde == other.distanciaHasta &&
		this.pisoHasta == other.pisoDesde &&
		this.distanciaHasta == other.distanciaDesde);
	}
}
