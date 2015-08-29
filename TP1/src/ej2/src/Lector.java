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
	
	public int[] LeerConjunto () throws IOException{
		/* Lee una sola linea del archivo.
		   Si el archivo no tiene mas lineas, devuelve null.	
		*/
		int[] conjunto;
		String line;
		String[] nums;
		int tam;

		line =  is.readLine();
		if (line == null || line.isEmpty()){
			hasEnded = true;
			return null;
		}
		nums = line.split(" ");
		tam = nums.length;
		conjunto = new int[nums.length];
		for(int i = 0; i < tam; i++){
			conjunto[i] = Integer.parseInt(nums[i]);			
		}
		return conjunto;
	}
}
