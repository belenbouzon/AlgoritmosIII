import java.util.Iterator;
import java.util.List;


public abstract class ColoresPosibles {
	abstract ColoresPosibles colores_en_comun(ColoresPosibles otro);
	abstract ColoresPosibles descartar_colores_en_comun(ColoresPosibles otro);
	abstract ColoresPosibles descartar_colores_en_comun(int color);
	abstract void agregar_color(int color);
	abstract boolean color_pertenece(int color);
	abstract int cantidad_colores();
	abstract void calcular_coliciones(ColoresPosibles otro);
	abstract Iterator<Integer> iterador_de_colores();
	abstract List<Integer> lista_de_colores();
}
