import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Escritor {
	
	public Escritor(String archiveIn) throws Exception 
	{
	     try{ this.os = new BufferedWriter( new FileWriter( getClass().getResource( "" ).getPath() + archiveIn + ".out" ) );}
		catch (RuntimeException e) {throw new Exception ("Error al escribir el archivo");}
	}
	
	BufferedWriter os;
	
	public void EscribirInt(long l) throws Exception
	{
		try {this.os.write(Long.toString(l) + " ");}
		catch (IOException e) {throw new Exception ("No se pudo escribir el valor");}
	}
	
	public void NuevaLinea() throws Exception
	{
		try {this.os.newLine();}
		catch (IOException e) {throw new Exception ("No se pudo escribir una nueva linea");}
	}
	
	public void Fin() throws IOException
	{
		this.os.close();
	}
	
}
