import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

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
	
	public void PrintNewNode(int cantTotalColores) throws Exception
	{
		Random numero = new Random();
		int cantColores = 1 + numero.nextInt(cantTotalColores);
		StringBuilder colores = new StringBuilder();
		Set<Integer> conjColores = new HashSet<Integer>();
		while(conjColores.size() < cantColores)
			conjColores.add(1 + numero.nextInt(cantColores));
		Iterator<Integer> iterador = conjColores.iterator();
		
		while(iterador.hasNext())
			colores.append(String.valueOf(iterador.next()) + " ");
		
		this.EscribirLinea(cantColores + " " + colores + "\n");
	}

    public void EscribirString(String s) throws Exception
	{
		try {this.os.write(s);}
		catch (IOException e) {throw new Exception ("No se pudo escribir el valor");}
	}

	public void Fin() throws IOException
	{
		this.os.close();
	}
	
	public void escribirArista(Arista arista) throws Exception 
	{
		try 
		{
			this.os.append(String.valueOf(arista.desde));
			this.os.write(" ");
			this.os.append(String.valueOf(arista.hasta));
			this.os.write("\n");
		} 
		catch (IOException e) { throw new Exception ("No se pudo escribir arista");}
	}
	
	public void GenerateAndPrintAristas(int cantAristas, int cantNodos) throws Exception 
	{
		HashSet<Arista> aristas = this.GenerarAristas(cantAristas, cantNodos);
		this.ImprimirAristas(aristas.iterator());
	}

	private void ImprimirAristas(Iterator<Arista> iterator) throws Exception 
	{
		while(iterator.hasNext())
			this.escribirArista(iterator.next());
	}

	private HashSet<Arista> GenerarAristas(int cantAristas, int cantNodos) 
	{
		HashSet<Arista> aristas = new HashSet<Arista>();
		Random random = new Random();
		while (aristas.size() < cantAristas)
		{
			int inicio = random.nextInt(cantNodos);
			int destino = random.nextInt(cantNodos);
			if (inicio != destino)
				aristas.add(new Arista(inicio,destino));
		}
		return aristas;
	}
}
