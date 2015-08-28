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
	private boolean hasEnded = false;

	
	public boolean archivo_termino(){
		return hasEnded;
	}
	public String leer_palabra() throws IOException{
		String st = this.is.readLine();
		return st;
	}
}
