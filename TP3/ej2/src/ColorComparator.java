import java.util.Comparator;

public class ColorComparator implements Comparator<Nodo_Coloreable_ej2>
{
    public int compare( Nodo_Coloreable_ej2 n1, Nodo_Coloreable_ej2 n2 )
    {
    	int cantColores1 = n1.cantidad_colores;
    	int cantColores2 = n2.cantidad_colores;
    	int res = cantColores1 - cantColores2;

    	if (cantColores1 == cantColores2) {
    		// si tienen la misma cant de colores pongo primero el de id menor.
    		res = n1.identidad - n2.identidad;
    	}

        return res;
    }
}