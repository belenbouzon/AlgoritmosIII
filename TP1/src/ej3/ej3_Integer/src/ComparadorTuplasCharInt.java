import java.util.Comparator;


public class ComparadorTuplasCharInt implements Comparator<Tuple<Character,Integer> > {
	public int compare(Tuple<Character,Integer> t1,Tuple<Character,Integer> t2){
		//if(t1.x==t2.x){
			//	return (t1.y -t2.y);
		//}else{
			return (t1.x-t2.x);
		//}
	}
}
