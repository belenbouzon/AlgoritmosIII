
public class GeneradorResultadosVariandoCantPortales {
	GeneradorResultadosVariandoCantPortales(int cantidadDePisos, int longitudDePasillos, int escala) throws Exception
	{
		GenerarCasosDeTests gen = new GenerarCasosDeTests(cantidadDePisos, longitudDePasillos, escala);

		for (int i = 4; i < 28; i++) //el intervalo que corresponda a los archivos creados
		{
			String[] parametros = new String[]{String.valueOf(i)+"Portales"};

			Main.main(parametros);
			
		}
	}	
}
