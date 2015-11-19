import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ColoresPosiblesEj1 extends ColoresPosibles{

	private ArrayList<Integer> colores;
	
	public ColoresPosiblesEj1 (){
		this.colores = new ArrayList<Integer>(2);
	}
	
	public ColoresPosiblesEj1(List<Integer> colores){
		this.colores = new ArrayList<Integer>(colores);
	}
	
	@Override
	ColoresPosibles colores_en_comun(ColoresPosibles otro) {
		ColoresPosiblesEj1 res = new ColoresPosiblesEj1();
		Iterator<Integer> it = otro.iterador_de_colores();
		while(it.hasNext()){
			int nuevo = it.next();
			if(this.colores.contains(nuevo)){
				res.agregar_color(nuevo);
			}
		}
		return res;
	}

	@Override
	ColoresPosibles descartar_colores_en_comun(ColoresPosibles otro) {
		ColoresPosiblesEj1 res = new ColoresPosiblesEj1();
		Iterator<Integer> it = otro.iterador_de_colores();
		while(it.hasNext()){
			Integer nuevo = it.next();
			if(!this.colores.contains(nuevo)){
				res.agregar_color(nuevo);
			}
		}
		it = this.colores.iterator();
		while(it.hasNext()){
			Integer nuevo = it.next();
			if(!otro.color_pertenece(nuevo)){
				res.agregar_color(nuevo);
			}
		}
		return res;
	}
	

	@Override
	ColoresPosibles descartar_colores_en_comun(int color) {
		ColoresPosiblesEj1 res = new ColoresPosiblesEj1(this.colores);
		if(res.colores.contains(color)){
			res.colores.remove(color);
		}
		return res;
	}

	@Override
	void agregar_color(int color) {
		this.colores.add(color);
	}

	@Override
	boolean color_pertenece(int color) {
		return this.colores.contains(color);
	}

	@Override
	int cantidad_colores() {
		return this.colores.size();
	}

	@Override
	Iterator<Integer> iterador_de_colores() {
		return this.colores.iterator();
	}
	
	@Override
	List<Integer> lista_de_colores(){
		return this.colores;
	}
	
	@Override
	void set_color(int index, int color){
		this.colores.set(index, color);
	}
	

}
