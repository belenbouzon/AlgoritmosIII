import org.junit.Test;


public class GeneradorResultadosVariandoCantPortales {
	GeneradorResultadosVariandoCantPortales() throws Exception
	{
		GeneradorDeTestsPortales gen = new GeneradorDeTestsPortales();

		for (int i = 4; i < 28; i++) //el intervalo que corresponda a los archivos creados
		{
			String[] parametros = new String[]{String.valueOf(i)+"Portales"};

			Main.main(parametros);
			
		}


	}
	
	
	/*
	 * 		GeneradorDeTestsPortales gen = new GeneradorDeTestsPortales();
	 * 		crea los archivos que coinciden en n y l y varian en p.-
	 *		Se llaman p.ToString() + "Portales"
	 */
	
	
	
}
