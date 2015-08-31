import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Escritor {
	
	public Escritor(String archiveIn) throws Exception 
	{
	     try{ this.os = new BufferedWriter( new FileWriter( getClass().getResource( "" ).getPath() + archiveIn) );}
		catch (RuntimeException e) {throw new Exception ("Error al escribir el archivo");}
	}
	
	public Escritor(String archiveIn, String extension) throws Exception 
	{
	     try{ this.os = new BufferedWriter( new FileWriter( getClass().getResource( "" ).getPath() + archiveIn + extension ) );}
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
	
	public void Append(String stuff) throws Exception
	{
		try {this.os.append(stuff);}
		catch (IOException e) {throw new Exception ("No se pudo escribir");}
	}
	
	public void Append(int num) throws Exception
	{
		try {this.os.append(Integer.toString(num) + " ");}
		catch (IOException e) {throw new Exception ("No se pudo escribir el numero");}
	}
	
	
	public void EscribirLinea (String linea) throws Exception
	{
		try {this.os.write(linea);}
		catch (IOException e) {throw new Exception ("No se pudo escribir linea");}

	}
	
	public void Fin() throws IOException
	{
		this.os.close();
	}
	
}
