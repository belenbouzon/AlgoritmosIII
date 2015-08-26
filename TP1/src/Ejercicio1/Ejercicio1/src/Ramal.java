import java.util.ArrayList;


public class Ramal {
	
    public Ramal(){}

	public ArrayList<Ciudad> ciudades;

	public Ramal (ArrayList<Ciudad> ciudades)
	{
		this.ciudades = ciudades;
		this.indiceCiudadActual = 0;
	}
	
	public int indiceCiudadActual;
	
	public boolean AlcanzaParaUnirUnaCiudadMas(int ciudadesUnidas, int longitudDeCable) //devuelve false tmb si estoy mirando la ultima
	{
		return !EsLaUltimaCiudad(indiceCiudadActual + ciudadesUnidas - 1) &&
				DistanciaEntreCiudades(indiceCiudadActual, indiceCiudadActual + ciudadesUnidas) <= longitudDeCable; //checkeado. Me aseguro de q se fije la dist sabiendo que hay otra ciudad
	}
	
	public void AvanzarCiudadBase()
	{
		indiceCiudadActual++;
	}
	
	public boolean HayCiudadesMasLejanas()
	{
		return (indiceCiudadActual < ciudades.size() - 1); //checkeado
	}
	
	
	public int DistanciaEntreCiudades(int indiceCiudadA, int indiceCiudadB)
	{
		return this.ciudades.get(indiceCiudadB).GetKilometro() - this.ciudades.get(indiceCiudadA).GetKilometro();
	}

	public boolean EsLaUltimaCiudad(int indiceCiudad) {
		return indiceCiudad == this.ciudades.size() - 1;
	}
	
}
