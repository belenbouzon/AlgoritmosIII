import java.util.HashSet;
import java.util.Random;


public class Tester {
	public static void generarGrafoBipartitoCompleto(String archivo,String [] resultados,int cantidadNodos,int cantidadColores,boolean limitarCantidad) throws Exception{
		int posicionDeResultado = 0;
		
		Escritor es;
		if(archivo!=null){
			es = new Escritor(archivo);
			es.EscribirLinea(Integer.toString(cantidadNodos) + " " + Integer.toString(cantidadNodos*cantidadNodos/4) + " " + Integer.toString(cantidadColores));
			es.EscribirLinea("\n");
		}else{
			es = null;
		}
		Random numeros = new Random();
		int colorComun1 = numeros.nextInt(cantidadColores);
		int colorComun2 = numeros.nextInt(cantidadColores);
		while(colorComun1==colorComun2){
			colorComun2 = numeros.nextInt(cantidadColores);
		}
		for(int i=0;i<cantidadNodos;i++){
			int cantidadColoresNodo;
			if(limitarCantidad){
				cantidadColoresNodo = cantidadColores;
			}else{
				cantidadColoresNodo = 1 + numeros.nextInt(cantidadColores);
			}
			StringBuilder expresionDeNodo = new StringBuilder();
			expresionDeNodo.append(cantidadColoresNodo);
			expresionDeNodo.append(" ");
			int posicionColor1 = numeros.nextInt(cantidadColoresNodo);
			int posicionColor2 = numeros.nextInt(cantidadColoresNodo);
			HashSet<Integer> coloresUsados = new HashSet<Integer>(); 
			coloresUsados.add(posicionColor1);
			coloresUsados.add(posicionColor2);
			for(int j=0;j<cantidadColoresNodo;j++){
				if(j==posicionColor1){
					expresionDeNodo.append(colorComun1);
				}else if(j==posicionColor2){
					expresionDeNodo.append(colorComun2);
				}else{
					int nuevoNumero = numeros.nextInt(cantidadColores);
					while(coloresUsados.contains(nuevoNumero)){
						nuevoNumero = numeros.nextInt(cantidadColores);
					}
					coloresUsados.add(nuevoNumero);
					expresionDeNodo.append(nuevoNumero);
				}
				if(j!=cantidadColoresNodo-1){
					expresionDeNodo.append(" ");
				}
			}
			if(archivo!=null){
				es.EscribirLinea(expresionDeNodo.toString());
				es.EscribirLinea("\n");
			}
			if(resultados!=null){
				resultados[posicionDeResultado] = expresionDeNodo.toString();
				posicionDeResultado++;
			}
		}
		
		for(int i= 0;i<cantidadNodos;i++){
			for(int j = i+1;j<cantidadNodos;j+=2){
				StringBuilder expresionDeArista = new StringBuilder();
				expresionDeArista.append(i);
				expresionDeArista.append(" ");
				expresionDeArista.append(j);
				
				if(archivo!=null){
					es.EscribirLinea(expresionDeArista.toString());
					es.EscribirLinea("\n");
				}
				if(resultados!=null){
					resultados[posicionDeResultado] = expresionDeArista.toString();
					posicionDeResultado++;
				}
			}
		}
		if(es!=null){
			es.Fin();
		}
	}
	
	private static void imprimirHelp(){
		System.out.print("0: nombre archivo,cantidad nodos, cantidad colores,limitar color (sino ingresar VARIABLE)\n ");
	}
	
	public static void main(String[] entrada) throws NumberFormatException, Exception{
		if(entrada.length==0){
			imprimirHelp();
			return;
		}
		int primerParametro = Integer.parseInt(entrada[0]);
		if(primerParametro==0){
			boolean limitarCantidad;
			if(entrada[3].equals("VARIABLE")){
				limitarCantidad = false;
			}else{
				limitarCantidad = true;
			}
			generarGrafoBipartitoCompleto(entrada[1],null,Integer.parseInt(entrada[2]),Integer.parseInt(entrada[3]),limitarCantidad);
		}else{
			imprimirHelp();
		}
	}
}
