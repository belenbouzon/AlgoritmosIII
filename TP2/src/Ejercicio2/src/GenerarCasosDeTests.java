import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GenerarCasosDeTests {
	
	private int cantidadDePisos;
	private int longitudDePasillos;

	GenerarCasosDeTests(int cantidadDePisos, int longitudDePasillos, int escala) throws Exception
	{
		this.cantidadDePisos = cantidadDePisos;
		this.longitudDePasillos = longitudDePasillos;
		
		int cantPortales = cantidadDePisos * longitudDePasillos;
		int cantMaximaPortales = cantidadDePisos * longitudDePasillos / 2 * ((cantidadDePisos * longitudDePasillos) -1);
		
		while (cantPortales < cantMaximaPortales)	
		{
			GenerarCasoValidoConCantPortales(cantPortales);
			cantPortales += escala;
		}
	}

	private void GenerarCasoValidoConCantPortales(int cantPortales) throws IOException, Exception 
	{
		CrearArchivoDeGrafo(cantPortales);

		while (!TieneSolucionElGrafo(String.valueOf(cantPortales) + "Portales.out"))
		{
			CrearArchivoDeGrafo(cantPortales);
		}
	}

	private void CrearArchivoDeGrafo(int cantPortales) throws Exception 
	{
		
		Escritor escritor = new Escritor(String.valueOf(cantPortales) + "Portales"+".out");
		escritor.EscribirString(String.valueOf(cantidadDePisos) + " " + String.valueOf(longitudDePasillos) +"\n");
		Set<Arista> aristas = new HashSet<Arista>();
		while (aristas.size() < cantPortales)
		{
			Arista arista = new Arista(cantidadDePisos, longitudDePasillos);
			aristas.add(arista);
		}
		
        for (Arista a: aristas) 
		{
			escritor.escribirArista(a);
		}

		escritor.Fin();	
	}

	private boolean TieneSolucionElGrafo(String archivoGrafo) throws Exception 
	{
		
		String path = getClass().getResource( "" ).getPath();
		File archivo = new File( path + archivoGrafo);
	    if (!archivo.exists())
	    {
			System.out.print("No existe el archivo que se quiere verificar");
	    	return false;
	    }
	    
		Lector recuperar_nodos = new Lector(archivoGrafo);
		recuperar_nodos.procesar_entrada();
		Solucion sol = new Solucion(recuperar_nodos.primer_nodo(),recuperar_nodos.ultimo_nodo());
		int solucion = sol.calcular_segundos();
		if (solucion != 0)
			return true;
		else
			return false;
	}
	    	
}
