import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Escritor {
	
	public Escritor(String archiveIn) throws Exception 
	{
	     try{ this.os = new BufferedWriter( new FileWriter( getClass().getResource( "" ).getPath() + archiveIn) );}
	     catch (RuntimeException e) {throw new Exception ("Error al escribir el archivo");}
	}
	
	public void EscribirLinea (String linea) throws Exception
	{
		try {this.os.write(linea);}
		catch (IOException e) {throw new Exception ("No se pudo escribir linea");}

	}
	
	BufferedWriter os;

	
    public void EscribirString(String s) throws Exception
	{
		try {this.os.write(s);}
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
	/*
	public void Append(int num) throws Exception
	{
		try {this.os.append(Integer.toString(num) + " ");}
		catch (IOException e) {throw new Exception ("No se pudo escribir el numero");}
	}
    */
	public void Fin() throws IOException
	{
		this.os.close();
	}
	
	public void escribirArista(Arista arista) throws Exception 
	{
		try 
		{
			this.os.append(String.valueOf(arista.pisoDesde));
			this.os.write(" ");
			this.os.append(String.valueOf(arista.distanciaDesde));
			this.os.write(" ");
			this.os.append(String.valueOf(arista.pisoHasta));
			this.os.write(" ");
			this.os.append(String.valueOf(arista.distanciaHasta));
			this.os.write(";");
		} 
		catch (IOException e) { throw new Exception ("No se pudo escribir arista");}
	}
	
	
}
