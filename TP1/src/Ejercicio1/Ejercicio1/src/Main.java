import java.util.ArrayList;

public class Main 
{
  public static void main(String[] args) throws Exception 
  {
        if (args.length < 1){
            System.out.printf("Debe pasar el nombre del archivo de input como parámetro. Además puede pasar el flag --tiempos después del nombre de archivo, seguido de un número entero, para correr las mediciones de tiempos esa cantidad de veces\n");
            System.out.printf("USO: java Main INPUT [--tiempos N]\n");
        }

	    
        Lector lect = new Lector(args[0]);

        while (!lect.hasEnded)
        {
            int ejecuciones = 1;
            Boolean tiempos = false;
            if ((args.length > 1) && (args[1] == "--tiempos")){
                tiempos = true;
                ejecuciones = Integer.parseInt(args[2]);
            }
                        
            int longitudCable = lect.LeerLongitud();
            ArrayList<Ciudad> ciudades = lect.LeerCiudades();
            int n = ciudades.size();
            int res;
            while (ejecuciones > 0){
                long time0= System.nanoTime();
            
                if (longitudCable != 0 && ciudades.size() > 1)
                {
                    Ramal ramal = new Ramal(ciudades);
                    int indiceCiudadMasLejanaAlcanzada    = 0;  
                    int cantMaximaCiudadesUnidas 	      = 1;   
                    int ciudadesUnidas;
                    
                    //System.out.printf("NUEVO EJERCICIO. Long del cable: %d \n", longitudCable);

                    while(ramal.HayCiudadesMasLejanas() && !ramal.EsLaUltimaCiudad(indiceCiudadMasLejanaAlcanzada)) 
                    {
                        //System.out.printf("COMENZANDO EN LA ESTACION %d \n", ramal.indiceCiudadActual);

                        if (indiceCiudadMasLejanaAlcanzada > ramal.indiceCiudadActual)
                            ciudadesUnidas =  indiceCiudadMasLejanaAlcanzada - ramal.indiceCiudadActual + 1;
                        else
                            ciudadesUnidas = 1;					

                        while(ramal.AlcanzaParaUnirUnaCiudadMas(ciudadesUnidas, longitudCable)) 
                        {
                            ciudadesUnidas ++;
                            indiceCiudadMasLejanaAlcanzada ++;
                        }
                        
                        //System.out.printf("Yo llego a lo sumo hasta la %d \n", indiceCiudadMasLejanaAlcanzada);
                        
                        ramal.AvanzarCiudadBase();
                        
                        if (ciudadesUnidas > cantMaximaCiudadesUnidas)
                            cantMaximaCiudadesUnidas = ciudadesUnidas;
                        
                        //System.out.printf("La max cant de ciudades unidas hasta el momento %d \n", cantMaximaCiudadesUnidas);

                    }
                      
                    res = cantMaximaCiudadesUnidas == 1? 0: cantMaximaCiudadesUnidas;
                    
                    if (!tiempos){
                        System.out.printf("La max cant de ciudades q se pudo unir fue %d \n", res);
                    }

                }
                else
                {
                    res = 0;
                }
                long time1= System.nanoTime();
                if (tiempos){
                    System.out.printf(n + "," + Long.toString(time1-time0));
                }
                ejecuciones--;
            }
        }
  }

}

