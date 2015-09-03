import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Lector {
	
	public Lector(String archive) throws Exception 
	{
		try { this.is = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(archive)));}
		catch (RuntimeException e) {throw new Exception ("No pudo hallarse el archivo especificado.");}
	}
	
	private BufferedReader is;

	
	public String leer_palabra() throws IOException{

        try{
            return this.is.readLine();
        } catch(IOException e){
            return null;
        }
	}
}
