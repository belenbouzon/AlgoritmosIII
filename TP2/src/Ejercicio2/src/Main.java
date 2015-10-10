import java.io.File;
import java.io.FileWriter;

public class Main{
	public static void main(String [] entrada) throws Exception{
		Lector recuperar_nodos = new Lector(entrada[0]);
		long inicio = System.nanoTime();
		recuperar_nodos.procesar_entrada();
		Solucion sol = new Solucion(recuperar_nodos.primer_nodo(),recuperar_nodos.ultimo_nodo());
		int res = sol.calcular_segundos();
		long fin = System.nanoTime();
		System.out.printf("%d\n",res);
		if(entrada.length>1 /*&& entrada[1]=="--enable-debbuger"*/)
		{
			System.out.printf("%d\n", fin-inicio);
		}
			
		
		String path = Lector.Path();
		File archivo = new File( path + "RES"+entrada[0]);
	    
	    if (archivo.exists())
	    {
		    FileWriter fw = new FileWriter(archivo, true);
		    fw.append(Long.toString(fin-inicio)+"\n");
		    fw.close();
	    }
	    
	    else
	    {
			Escritor escritor = new Escritor("RES"+entrada[0]);
			escritor.Append(Long.toString(fin-inicio)+ "\n");
			escritor.Fin();
		}

	
	}
}