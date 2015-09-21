import java.util.List;
import java.util.ListIterator;

public class Grafo {

   private List<Pasillo> _pasillos;
   private UnionFind _sets;

   // O(n)
   public Grafo( int n, List<Pasillo> ps ) {
	   OrdenarDesc(ps);
      _pasillos = ps;
      _sets = new UnionFind(n);
   }
   
   public void OrdenarDesc(List<Pasillo> ps){
	   ps.sort(new PasilloComparator());
   }

   // O(m.log(m))
   public final int kruskal() {
	   int suma = 0;
	   Pasillo p;
	   ListIterator<Pasillo> it = _pasillos.listIterator();
	   while(it.hasNext()){
		   p = it.next();
		   if(_sets.isSameSet(p.getExtremo1(), p.getExtremo2())){
			   suma += p.getLongitud();
		   }else{
			   _sets.unionSet(p.getExtremo1(), p.getExtremo2());
		   }
	   }
	   return suma;
   }
}
