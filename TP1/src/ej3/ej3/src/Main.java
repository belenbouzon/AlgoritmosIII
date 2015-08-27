
public class Main {
	public static void main(String[] args) throws Exception{
		Solucion sol = new Solucion();
		//String s = "a bc;b ac;c ab";
		String s = "a c;b de;c a;d b;e b;";
		//String s = "a c;b ;c a;d ;e ;f ;g ;h ;i ;j ;";
		sol.generar_solucion(s);
	}
}
