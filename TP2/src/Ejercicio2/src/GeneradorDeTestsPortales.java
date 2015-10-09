import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GeneradorDeTestsPortales {

	static final int CANTPISOS = 4;//10000;
	static final int LONGITUDPASILLO = 2;//30;
	static final int CANTPORTALESNOINFLUYENTES = 5; //10000

	GeneradorDeTestsPortales() throws Exception
	{
		
		int cantPortales = CANTPISOS * LONGITUDPASILLO - CANTPORTALESNOINFLUYENTES;
		int cantMaximaPortales = CANTPISOS * LONGITUDPASILLO / 2 * ((CANTPISOS * LONGITUDPASILLO) -1);
		
		while (cantPortales < cantMaximaPortales)	
		{
			GenerarCasoValidoConCantPortales(cantPortales);
			cantPortales += 1;
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
		escritor.EscribirString(String.valueOf(CANTPISOS) + " " + String.valueOf(LONGITUDPASILLO) +"\n");
		Set<Arista> aristas = new HashSet<Arista>();
		while (aristas.size() < cantPortales)
		{
			Arista arista = new Arista(CANTPISOS, LONGITUDPASILLO);
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
