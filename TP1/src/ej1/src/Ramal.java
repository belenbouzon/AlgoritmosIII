

public abstract class Ramal {
	
    public Ramal(){}

	public Ciudad ciudades [];

	public Ramal (Ciudad [] ciudades)
	{
		this.ciudades = ciudades;
	}
	
	public int DistanciaEntreCiudades(int indice, int indiceSiguiente)
	{
		return this.ciudades[indiceSiguiente].GetKilometro() - this.ciudades[indice].GetKilometro();
	}

	public boolean EsLaUltimaCiudad(int i) {
		return i == this.ciudades.length - 1;
	}
	
	public Ciudad FirstCiudad()
	{
		return this.ciudades[0];
	}
	
	public boolean HayCiudades() {
		return this.ciudades != null && this.ciudades.length > 1; /*si ciudades.length es 1, esta solo el origen: el km 0*/
	}
	
	public int CantidadDeCiudades()
	{
		return ciudades != null? ciudades.length : 0;
	}
}
