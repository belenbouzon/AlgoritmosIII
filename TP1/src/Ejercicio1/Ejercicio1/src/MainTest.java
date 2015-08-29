import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class MainTest {

	@Test
	public void testMain() throws Exception {
		
		String parametro [] = new String[]{"PruebaCorrectitud"};
		Lector lectorResultados = new Lector("PruebaCorrectitud.out");
		Lector lectorResultadosEsperados =  new Lector("PruebaCorrectitudExpected");
		
		Main.main(parametro);
		
		while (!lectorResultados.hasEnded )
		{
		String resultado = lectorResultados.GetLine();
		String resultadoEsperado = lectorResultadosEsperados.GetLine();
		if (resultado != null && resultadoEsperado != null)
				assertTrue (resultado.trim().equals(resultadoEsperado.trim()));
	
		}
	}

}
