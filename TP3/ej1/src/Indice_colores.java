import java.util.Iterator;


public abstract class Indice_colores {
	abstract Indice_colores colores_en_comun(Indice_colores otro);
	abstract Indice_colores descartar_colores_en_comun(Indice_colores otro);
	abstract Indice_colores descartar_colores_en_comun(int color);
	abstract void agregar_color(int color);
	abstract boolean color_pertenece(int color);
	abstract int cantidad_colores();
	abstract void calcular_coliciones(Indice_colores otro);
	abstract Iterator<Integer> iterador_de_colores();
}
