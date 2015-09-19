import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class BoundedIntegerMap<E extends Object> implements Map<Integer,E>  {
	private ArrayList<E> array_datos;
	private boolean [] contiene_dato;
	private int tamanio_maximo;
	private int cantidad_claves;
	
	@Override
	public void clear() {
		for(int i = 0;i<tamanio_maximo;i++){
			array_datos.set(i, null);
			this.contiene_dato[i] = false;
		}
		this.cantidad_claves = 0;
	}
	public BoundedIntegerMap(int n){
		this.array_datos = new ArrayList<E>(n+1);
		this.contiene_dato = new boolean [n+1];
		this.tamanio_maximo = n+1;
		for(int i = 0;i<tamanio_maximo;i++){
			array_datos.add(i,null);
			this.contiene_dato[i] = false;
		}
		this.cantidad_claves = 0;
	}

	@Override
	public boolean containsKey(Object arg0) {
		return this.contiene_dato[(Integer)arg0];
	}

	@Override
	public boolean containsValue(Object arg0) {
		int i = 0;
		int cant = 0;
		while(i<this.tamanio_maximo && cant<this.cantidad_claves){
			if(this.contiene_dato[i]){
				cant++;
				if(this.array_datos.get(i)==arg0){
					return true;
				}
			}
			i++;
		}
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<Integer, E>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E get(Object arg0) {
		return this.array_datos.get((Integer)arg0);
	}

	@Override
	public boolean isEmpty() {
		return (this.cantidad_claves==0);
	}

	@Override
	public Set<Integer> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E put(Integer arg0, E arg1) {
		E res = this.array_datos.get(arg0);
		this.array_datos.set(arg0, arg1);
		this.contiene_dato[arg0] = true;
		return res;
	}
	@Override
	public E remove(Object arg0) {
		E res = this.array_datos.get((Integer)arg0);
		this.array_datos.set((Integer)arg0, null);
		this.contiene_dato[(Integer)arg0] = false;
		return res;
	}

	@Override
	public int size() {
		return this.cantidad_claves;
	}

	@Override
	public Collection<E> values() {
		int cant = 0;
		Collection<E> res = new LinkedHashSet<E>();
		int i = 0;
		while(i<this.tamanio_maximo){
			if(this.contiene_dato[i]){
				cant++;
				res.add(this.get(i));
			}
			i++;
		}
		return res;
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends E> m) {
		// TODO Auto-generated method stub	
	}

}
