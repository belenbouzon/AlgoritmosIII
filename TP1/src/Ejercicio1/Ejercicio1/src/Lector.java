import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Lector {
	
	public Lector(String archive) throws Exception 
	{
		try { this.is = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(archive)));}
		catch (RuntimeException e) {throw new Exception ("No pudo hallarse el archivo especificado.");}
	}
	
	private BufferedReader is;
	public boolean hasEnded = false;
	
	public ArrayList<Ciudad> LeerCiudades () throws IOException
	{
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		ciudades.add(new Ciudad(0));
		
		String linea= is.readLine();
		
		if (linea == null || linea.isEmpty())
		{
			hasEnded = true;
			return null;
		}

		String [] kms = linea.split(" ");
		
		for (int i = 0; i< kms.length ; i++)
		{
			ciudades.add(new Ciudad(Integer.parseInt(kms[i])));
		}

		return ciudades;
	}
	
	public int LeerLongitud () throws IOException{
		
		String longitud = is.readLine();
		
		if (longitud == null || longitud.isEmpty())
		{
			hasEnded = true;
			return 0;
		}	
		
		return Integer.parseInt(longitud.split(" ")[0]);
	}
}
