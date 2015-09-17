import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import java.util.Iterator;

public class Lector{
	private Nodo prim_nodo;
	private Nodo ult_nodo;
	private BufferedReader is;
	public Nodo primer_nodo(){
		return this.prim_nodo;
	}
	public Nodo ultimo_nodo(){
		return this.ult_nodo;
	}
	private static int absoluto(int numero){
		if(numero<0){
			numero = -numero;
		}
		return numero;
	}
	private void procesar_entrada() throws IOException{
		String formato = this.leer_palabra();
		String parametros [] = formato.split(" ");
		int n = Integer.parseInt(parametros[0]);
		int L = Integer.parseInt(parametros[1]);
		String entrada = this.leer_palabra();
		String portales [] = entrada.split(";");
		TreeMap<Integer,TreeMap<Integer,Nodo>> mapas_de_piso = new TreeMap<Integer,TreeMap<Integer,Nodo>>();
		this.prim_nodo = new Nodo(0);
		this.ult_nodo = new Nodo(1);
		TreeMap<Integer,Nodo> pos_0 = new TreeMap<Integer,Nodo>();
		pos_0.put(0, this.prim_nodo);
		TreeMap<Integer,Nodo> pos_l = new TreeMap<Integer,Nodo>();
		pos_l.put(L, this.ult_nodo);
		mapas_de_piso.put(0, pos_0);
		mapas_de_piso.put(n, pos_l);
		
		
		int nodo_iesimo = 2;
		for(int i = 0;i<portales.length;i++){
			String coordenadas [] = portales[i].split(" ");
			int k = 0;
			if(coordenadas[0].length()==0){
				k = 1;
			}
			int piso_1 = Integer.parseInt(coordenadas[0+k]);
			int distancia_1 = Integer.parseInt(coordenadas[1+k]);
			int piso_2 = Integer.parseInt(coordenadas[2+k]);
			int distancia_2 = Integer.parseInt(coordenadas[3+k]);
			Nodo nodo;
			if(!mapas_de_piso.containsKey(piso_1)){
				TreeMap<Integer,Nodo> nuevo = new TreeMap<Integer,Nodo>();
				nodo = new Nodo(nodo_iesimo);
				nodo_iesimo++;
				nuevo.put(distancia_1,nodo);
				mapas_de_piso.put(piso_1,nuevo);
			}else{
				TreeMap<Integer,Nodo> piso = mapas_de_piso.get(piso_1);
				if(!piso.containsKey(distancia_1)){
					nodo = new Nodo(nodo_iesimo);
					nodo_iesimo++;
					Set<Entry<Integer,Nodo>> otros = piso.entrySet();
					Iterator<Entry<Integer,Nodo>> it = otros.iterator();
					while(it.hasNext()){
						Entry<Integer,Nodo> otro_nodo = it.next();
						otro_nodo.getValue().agregar_arista(nodo, absoluto(otro_nodo.getKey()-distancia_1));
						nodo.agregar_arista(otro_nodo.getValue(), absoluto(otro_nodo.getKey()-distancia_1));
					}
					piso.put(distancia_1,nodo);
				}else{
					nodo = piso.get(distancia_1);
				}
			}
			Nodo nodo2;
			if(!mapas_de_piso.containsKey(piso_2)){
				TreeMap<Integer,Nodo> nuevo = new TreeMap<Integer,Nodo>();
				nodo2 = new Nodo(nodo_iesimo);
				nodo_iesimo++;
				nuevo.put(distancia_2,nodo2);
				mapas_de_piso.put(piso_2,nuevo);
			}else{
				TreeMap<Integer,Nodo> piso = mapas_de_piso.get(piso_2);
				if(!piso.containsKey(distancia_2)){
					Set<Entry<Integer,Nodo>> otros = piso.entrySet();
					Iterator<Entry<Integer,Nodo>> it = otros.iterator();
					nodo2 = new Nodo(nodo_iesimo);
					while(it.hasNext()){
						Entry<Integer,Nodo> otro_nodo = it.next();
						otro_nodo.getValue().agregar_arista(nodo2, absoluto(otro_nodo.getKey()-distancia_2));
						nodo2.agregar_arista(otro_nodo.getValue(), absoluto(otro_nodo.getKey()-distancia_2));
					}
					nodo_iesimo++;
					piso.put(distancia_2,nodo2);
				}else{
					nodo2 = piso.get(distancia_2);
				}
			}
			nodo.agregar_arista(nodo2, 2);
			nodo2.agregar_arista(nodo, 2);
		}
		//----------Test------------
		for(int i=0;i<=L;i++){
			if(mapas_de_piso.containsKey(i)){
				Set<Entry<Integer,Nodo>> otros = mapas_de_piso.get(i).entrySet();
				Iterator<Entry<Integer,Nodo>> it = otros.iterator();
				while(it.hasNext()){
					System.out.printf("piso: %d,nodo: %d\n", i,it.next().getValue().piso);
				}
			}
		}
		//--------------------------
	}
	public String leer_palabra() throws IOException{

        try{
            return this.is.readLine();
        } catch(IOException e){
            return null;
        }
	}
	public Lector(String archivo) throws Exception{
		try { this.is = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream(archivo)));}
		catch (RuntimeException e) {throw new Exception ("No pudo hallarse el archivo especificado.");}
		this.procesar_entrada();
	}
}