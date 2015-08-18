
public class Main {
  public static void main(String[] args) throws Exception 
  {
      
      Ramal ramal = new RamalDefault ();

      final int longitudVias = 5;
      int indiceCiudadMasLejanaAlcanzada = 0;
      int ciudadesUnidas;
      int cantMaximaCiudadesUnidas = 1;
      int sumaKMQueSeguroSePuedenRecorrer; 
      /*Guarda los km que ya se incluyeron en otro computo
       * Ej: si se vio que de la ciudad 0 a la 4 alcanzaba,
       * en particular alcanzara desde la 1 hasta la 4,
       * almacenandose la distancia de 1 a 4 en esta variable*/

      if (ramal.HayCiudades()) 
      {  
		      for (int indiceCiudadAPartirDeLaCualMido = 0; indiceCiudadAPartirDeLaCualMido < ramal.CantidadDeCiudades(); indiceCiudadAPartirDeLaCualMido++)
		      {
		    		int ciudadAnterior = indiceCiudadAPartirDeLaCualMido - 1;
		    	    int i = indiceCiudadAPartirDeLaCualMido;

		    		if (indiceCiudadAPartirDeLaCualMido == 0)
		    			sumaKMQueSeguroSePuedenRecorrer = 0;
		    		else
		    			sumaKMQueSeguroSePuedenRecorrer = ramal.DistanciaEntreCiudades(ciudadAnterior, indiceCiudadMasLejanaAlcanzada) - ramal.DistanciaEntreCiudades(ciudadAnterior, indiceCiudadAPartirDeLaCualMido);
		    	    
		    		while (longitudVias > sumaKMQueSeguroSePuedenRecorrer && i < ramal.CantidadDeCiudades())
		    	      {
		    				if (ramal.EsLaUltimaCiudad(indiceCiudadMasLejanaAlcanzada) || !ramal.EsLaUltimaCiudad(indiceCiudadMasLejanaAlcanzada) && !hayCuerdaSuficienteParaUnaCiudadMas(ramal, longitudVias, sumaKMQueSeguroSePuedenRecorrer, i))
		    	    		  break;
		    				
		    	    	  sumaKMQueSeguroSePuedenRecorrer += ramal.DistanciaEntreCiudades(indiceCiudadMasLejanaAlcanzada, indiceCiudadMasLejanaAlcanzada + 1);
		    	    	  indiceCiudadMasLejanaAlcanzada ++;
		    	    	  i ++;  	  
		    	      }
		    		
	  	    	  ciudadesUnidas = indiceCiudadMasLejanaAlcanzada - indiceCiudadAPartirDeLaCualMido + 1;
		    	     	  
			      if (ciudadesUnidas > cantMaximaCiudadesUnidas)
			    	  cantMaximaCiudadesUnidas = ciudadesUnidas;
			      /*
		    	  System.out.printf("Partiendo de ciudad Nro: %d \n", indiceCiudadAPartirDeLaCualMido);
		    	  System.out.printf("Se unieron: %d \n", ciudadesUnidas);
		    	  System.out.printf("Siendo el maximo hasta ahora: %d \n", cantMaximaCiudadesUnidas);
		    	  System.out.printf("Siendo la mas lejana aquella del indice: %d \n", indiceCiudadMasLejanaAlcanzada);
		    	  */
		      }
      }
      else
      {
    	  ciudadesUnidas = 1;
    	  sumaKMQueSeguroSePuedenRecorrer = 0;
      }
  }

	   private static boolean hayCuerdaSuficienteParaUnaCiudadMas(Ramal ramal, int longitudCuerda, int sumaDistancias, int ciudadMasLejanaAlcanzada) throws Exception 
	   {
		   return sumaDistancias + ramal.DistanciaEntreCiudades(ciudadMasLejanaAlcanzada, ciudadMasLejanaAlcanzada+1) <= longitudCuerda;
	   }
	   
}