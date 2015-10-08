import java.util.List;
import java.util.ListIterator;
import java.util.Collections;

public class Grafo {

   private List<Pasillo> _pasillos;
   private UnionFind _sets;
   private int _nodos;

   public Grafo( int n, List<Pasillo> ps ) {
	   OrdenarDesc(ps);
      _pasillos = ps;
      _nodos = n;
      _sets = new UnionFind(n);
   }
   
   public void OrdenarDesc(List<Pasillo> ps){
		//ps.Sort(new PasilloComparator()); //Con java 7 no compila.
		Collections.sort(ps, new PasilloComparator());
   }

   public final int kruskal() {
	   int suma = 0;
	   int aristasAG = 0;
	   Pasillo p;
	   ListIterator<Pasillo> it = _pasillos.listIterator();
	   while(it.hasNext()){
		   p = it.next();
		   if(aristasAG < _nodos - 1){
			   if(_sets.isSameSet(p.getExtremo1(), p.getExtremo2())){
				   //si estan en el mismo set
				   //tengo que sumar la long del pasillo a clausurar.
				   suma += p.getLongitud();
			   }else{
				   _sets.unionSet(p.getExtremo1(), p.getExtremo2());
				   //sumo una arista al AG.
				   aristasAG++;
			   }
			}else{
				// si ya puse n-1 aristas solo sumo las distancias.
				suma += p.getLongitud();
			}
	   }
	   return suma;
   }
}
