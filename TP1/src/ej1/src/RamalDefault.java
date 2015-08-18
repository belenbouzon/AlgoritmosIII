
public class RamalDefault extends Ramal {

	 public RamalDefault()
	    {
	    	Ciudad ciudades [] = new Ciudad [5];
	    	
	        ciudades[0] = new Ciudad(0);
	        for (int j = 1; j < 5; j++)
	        {
	      	  ciudades[j] = new Ciudad(j*2+1);
	        }
	        
	        this.ciudades = ciudades;
	    }
}
