import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Indice_colores_ej1 extends Indice_colores{

	private ArrayList<Integer> colores;
	@Override
	Indice_colores colores_en_comun(Indice_colores otro) {
		Indice_colores_ej1 res = new Indice_colores_ej1();
		Iterator<Integer> it = otro.iterador_de_colores();
		//System.out.printf("holaaaaaaaaaa\n");
		while(it.hasNext()){
			int nuevo = it.next();
			if(this.colores.contains(nuevo)){
				//System.out.printf("holaaaaaaaaaa\n");
				res.agregar_color(nuevo);
			}
		}
		return res;
	}

	@Override
	Indice_colores descartar_colores_en_comun(Indice_colores otro) {
		Indice_colores_ej1 res = new Indice_colores_ej1();
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
	protected Indice_colores_ej1 clone(){
		Indice_colores_ej1 res = new Indice_colores_ej1();
		res.colores = (ArrayList<Integer>) this.colores.clone();
		return res;
	}

	@Override
	Indice_colores descartar_colores_en_comun(int color) {
		Indice_colores_ej1 res = new Indice_colores_ej1().clone();
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
	void calcular_coliciones(Indice_colores otro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	Iterator<Integer> iterador_de_colores() {
		return this.colores.iterator();
	}
	@Override
	List<Integer> conjunto_de_colores(){
		return this.colores;
	}
	public Indice_colores_ej1 (){
		this.colores = new ArrayList<Integer>(2);
	}

}
