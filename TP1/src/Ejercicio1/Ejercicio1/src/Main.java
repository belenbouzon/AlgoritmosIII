import java.util.ArrayList;

public class Main 
{
  public static void main(String[] args) throws Exception 
  {
	  	
	    Lector lect = new Lector("Ejercicio1");
	    
	    while (!lect.hasEnded)
	    {
	    			    
		    int longitudCable = lect.LeerLongitud();
		    ArrayList<Ciudad> ciudades = lect.LeerCiudades();
		    int res;
		    
		    if (longitudCable != 0 && ciudades.size() > 1)
		    {
				Ramal ramal = new Ramal(ciudades);
				int indiceCiudadMasLejanaAlcanzada    = 0;  
				int cantMaximaCiudadesUnidas 	      = 1;   
				int ciudadesUnidas;
				
			  	//System.out.printf("NUEVO EJERCICIO. Long del cable: %d \n", longitudCable);

				while(ramal.HayCiudadesMasLejanas() && !ramal.EsLaUltimaCiudad(indiceCiudadMasLejanaAlcanzada)) 
				{
				  	//System.out.printf("COMENZANDO EN LA ESTACION %d \n", ramal.indiceCiudadActual);

					if (indiceCiudadMasLejanaAlcanzada > ramal.indiceCiudadActual)
						ciudadesUnidas =  indiceCiudadMasLejanaAlcanzada - ramal.indiceCiudadActual + 1;
					else
						ciudadesUnidas = 1;					

					while(ramal.AlcanzaParaUnirUnaCiudadMas(ciudadesUnidas, longitudCable)) 
					{
						ciudadesUnidas ++;
						indiceCiudadMasLejanaAlcanzada ++;
					}
					
				  	//System.out.printf("Yo llego a lo sumo hasta la %d \n", indiceCiudadMasLejanaAlcanzada);
					
					ramal.AvanzarCiudadBase();
					
					if (ciudadesUnidas > cantMaximaCiudadesUnidas)
						cantMaximaCiudadesUnidas = ciudadesUnidas;
					
				  	//System.out.printf("La max cant de ciudades unidas hasta el momento %d \n", cantMaximaCiudadesUnidas);

				}
				  
				res = cantMaximaCiudadesUnidas == 1? 0: cantMaximaCiudadesUnidas;
				
			  	System.out.printf("La max cant de ciudades q se pudo unir fue %d \n", res);

		    }
		    else
		    {
		    	res = 0;
		    }
		    
	    }
  }

}

