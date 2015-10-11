import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GeneradorCasosDeTestsbyLongitud {
	
	private int cantidadDePisos;
	private int cantPortales;

	GeneradorCasosDeTestsbyLongitud(int longitudDePasillos, int longitudMaxDePasillos, int escala, int cantPortales, int cantPisos) throws Exception
	{
		this.cantPortales       = cantPortales;
		this.cantidadDePisos = cantPisos;
		
		while (longitudDePasillos < longitudMaxDePasillos)	
		{
			GenerarCasoValidoConLongDePasillos(longitudDePasillos);
			longitudDePasillos += escala;
		}
	}

	private void GenerarCasoValidoConLongDePasillos(int longitudDePasillos) throws IOException, Exception 
	{
		CrearArchivoDeGrafo(longitudDePasillos);

		while (!TieneSolucionElGrafo(String.valueOf(longitudDePasillos) + "Pasillos.out"))
		{
			CrearArchivoDeGrafo(longitudDePasillos);
		}
	}

	private void CrearArchivoDeGrafo(int longitudDePasillos) throws Exception 
	{
		
		Escritor escritor = new Escritor(String.valueOf(longitudDePasillos) + "Pasillos"+".out");
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
