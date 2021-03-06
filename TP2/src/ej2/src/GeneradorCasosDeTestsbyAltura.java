import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GeneradorCasosDeTestsbyAltura {
	
	private int longitudDePasillos;
	private int cantPortales;

	GeneradorCasosDeTestsbyAltura(int cantidadDePisos, int cantidadMaxDePisos, int escala, int longitudDePasillos, int cantPortales) throws Exception
	{
		this.longitudDePasillos = longitudDePasillos;
		this.cantPortales       = cantPortales;
		
		while (cantidadDePisos < cantidadMaxDePisos)	
		{
			GenerarCasoValidoConCantDePisos(cantidadDePisos);
			cantidadDePisos += escala;
		}
	}

	private void GenerarCasoValidoConCantDePisos(int cantidadDePisos) throws IOException, Exception 
	{
		CrearArchivoDeGrafo(cantidadDePisos);

		while (!TieneSolucionElGrafo(String.valueOf(cantidadDePisos) + "Pisos.out"))
		{
			CrearArchivoDeGrafo(cantidadDePisos);
		}
	}

	private void CrearArchivoDeGrafo(int cantidadDePisos) throws Exception 
	{
		
		Escritor escritor = new Escritor(String.valueOf(cantidadDePisos) + "Pisos"+".out");
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
