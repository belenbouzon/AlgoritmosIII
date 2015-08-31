import java.util.ArrayList;

public class Main 
{
  public static void main(String[] args) throws Exception 
  {
        MostrarIndicaciones(args);
        
        int ejecuciones = 1;
        Boolean mideTiempos = false;
        
        if (args.length > 1 && args[1].equalsIgnoreCase("--tiempos"))
        {
            mideTiempos = true;
            ejecuciones = Integer.parseInt(args[2]);
        }
        
        Lector lect = new Lector(args[0]);
        Escritor esc = new Escritor(args[0], ".out");
        if (mideTiempos){
            esc.EscribirString("n,tiempo");
            esc.NuevaLinea();
        }
        int caso = 1;
        
        while (true)
        {   
            int longitudCable = lect.LeerLongitud();
            ArrayList<Ciudad> ciudades = lect.LeerCiudades();
            
            if (lect.hasEnded){break;}
            
            int res;
            int ciclos = ejecuciones;

            while (ciclos > 0)
            {
                long time0= System.nanoTime();
            
                if (longitudCable != 0 && ciudades.size() > 1)
                {
                    Ramal ramal = new Ramal(ciudades);
                    int indiceCiudadMasLejanaAlcanzada    = 0;  
                    int cantMaximaCiudadesUnidas          = 1;   
                    int ciudadesUnidas;

                    while(ramal.HayCiudadesMasLejanas() && !ramal.EsLaUltimaCiudad(indiceCiudadMasLejanaAlcanzada)) 
                    {
                        if (indiceCiudadMasLejanaAlcanzada > ramal.indiceCiudadActual)
                            ciudadesUnidas =  indiceCiudadMasLejanaAlcanzada - ramal.indiceCiudadActual + 1;
                        else
                            ciudadesUnidas = 1;                 

                        while(ramal.AlcanzaParaUnirUnaCiudadMas(ciudadesUnidas, longitudCable)) 
                        {
                            ciudadesUnidas ++;
                            indiceCiudadMasLejanaAlcanzada ++;
                        }
                                                
                        ramal.AvanzarCiudadBase();
                        
                        if (ciudadesUnidas > cantMaximaCiudadesUnidas)
                            cantMaximaCiudadesUnidas = ciudadesUnidas;
                        
                    }
                      
                    res = cantMaximaCiudadesUnidas == 1? 0: cantMaximaCiudadesUnidas;

                }
                else
                {
                    res = 0;
                }

                if (!mideTiempos){
                    esc.EscribirInt(res);
                    System.out.printf("La máxima cantidad de estaciones unidas fue %d \n", res);
                }
                else
                {
                    long time1= System.nanoTime();
                    esc.EscribirInt(ciudades.size());
                    esc.EscribirString(",");
                    esc.EscribirInt(time1-time0);
                    esc.NuevaLinea();
                }
                ciclos--;
                esc.NuevaLinea();
            }
            caso++;
        }
        esc.Fin();
  }

  private static void MostrarIndicaciones(String[] args) 
  {
	if (args.length < 1)
	{
	    System.out.printf("Debe pasar el nombre del archivo de input como parametro. Ademas puede pasar el flag --tiempos despues del nombre de archivo, seguido de un numero entero, para correr las mediciones de tiempos esa cantidad de veces\n");
	    System.out.printf("USO: java Main INPUT [--tiempos N]\n");
	}
  }
  
}
