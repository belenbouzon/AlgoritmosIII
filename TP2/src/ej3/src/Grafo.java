import java.util.List;
import java.util.ListIterator;
import java.util.Collections;

public class Grafo {

   private List<Pasillo> _pasillos;
   private UnionFind _sets;

   public Grafo( int n, List<Pasillo> ps ) {
	   OrdenarDesc(ps);
      _pasillos = ps;
      _sets = new UnionFind(n);
   }
   
   public void OrdenarDesc(List<Pasillo> ps){
		//ps.Sort(new PasilloComparator()); //Con java 7 no compila.
		Collections.sort(ps, new PasilloComparator());
   }

   public final int kruskal() {
	   int suma = 0;
	   Pasillo p;
	   ListIterator<Pasillo> it = _pasillos.listIterator();
	   while(it.hasNext()){
		   p = it.next();
		   if(_sets.isSameSet(p.getExtremo1(), p.getExtremo2())){
			   //si estan en el mismo set
			   //tengo que sumar la long del pasillo a clausurar.
			   suma += p.getLongitud();
		   }else{
			   _sets.unionSet(p.getExtremo1(), p.getExtremo2());
		   }
	   }
	   return suma;
   }
}
