import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Lector {
	
	public Lector(String archive) throws Exception 
	{
		try { this.is = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(archive)));}
		catch (RuntimeException e) {throw new Exception ("No pudo hallarse el archivo especificado.");}
	}
	
	private BufferedReader is;
	private boolean hasEnded = false;
	private int intersecciones = 0;

	public boolean archivo_termino(){
		return hasEnded;
	}
	
	public int CantIntersecciones(){
		//esta funcion solo se puede usar despues de haber llamado a LeerLista.
		//sino devuelve basura.
		return intersecciones;
	}
	
	public List<Pasillo> LeerLista () throws IOException{
		/* Lee una sola linea del archivo.
		   Si el archivo no tiene mas lineas, devuelve null.	
		*/
		List<Pasillo> lista;
		Pasillo p;
		String line;
		String[] pasillos;
		String[] pasillo;
		int tam;
		intersecciones = 0;

		line =  is.readLine();
		if (line == null || line.isEmpty()){
			hasEnded = true;
			return null;
		}
		pasillos = line.split("; ");
		tam = pasillos.length;
		lista = new ArrayList<Pasillo>(tam);
		for(int i = 0; i < tam; i++){
			pasillo = pasillos[i].split(" ");
			p = new Pasillo(Integer.parseInt(pasillo[0]), Integer.parseInt(pasillo[1]), Integer.parseInt(pasillo[2]));
			lista.add(p);
			//calculo las intersecciones
			if(p.getExtremo1() > intersecciones){
				intersecciones = p.getExtremo1();
			}
			if(p.getExtremo2() > intersecciones){
				intersecciones = p.getExtremo2();
			}
		}
		//sumo 1 porque las intersecciones empiezan desde cero.
		intersecciones++;
		return lista;
	}
}
