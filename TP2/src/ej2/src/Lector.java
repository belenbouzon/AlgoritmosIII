import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Set;
import java.util.LinkedHashSet;

import java.util.Iterator;

public class Lector{
	private Nodo prim_nodo;
	private Nodo ult_nodo;
	private BufferedReader is;
	private int n;
	private int L;
	private String formato;
	private String entrada;
	public int cantidad_pisos(){
		return n;
	}
	public int largo_pasillos(){
		return L;
	}
	public Nodo primer_nodo(){
		return this.prim_nodo;
	}
	public Nodo ultimo_nodo(){
		return this.ult_nodo;
	}
	private static void iniciar_diccionario(BoundedIntegerMap<BoundedIntegerMap<Nodo>> map,int n, int l){
		for(int i = 0;i<=n;i++){
			BoundedIntegerMap<Nodo> piso = new BoundedIntegerMap<Nodo>(l);
			map.put(i, piso);
		}
	}
	/*private Nodo incluir_nodo(int n_piso,int n_distancia,Integer nodo_iesimo,BoundedIntegerMap<BoundedIntegerMap<Nodo>> mapas_de_piso){
		BoundedIntegerMap<Nodo> piso = mapas_de_piso.get(n_piso);
		Nodo n;
		if(!piso.containsKey(n_distancia)){
			n = new Nodo(nodo_iesimo,n_distancia);
			nodo_iesimo = nodo_iesimo + 1;
			piso.put(n_distancia,n);
			System.out.printf("nodo: %d\n",nodo_iesimo);
		}else{
			n = piso.get(n_distancia);
		}
		return n;
	}*/
	public void procesar_entrada(){
		String parametros [] = formato.split(" "); //O(1)
		this.n = Integer.parseInt(parametros[0]);
		this.L = Integer.parseInt(parametros[1]);
		String portales [] = entrada.split(";"); //O(1)
		BoundedIntegerMap<BoundedIntegerMap<Nodo>> mapas_de_piso = new BoundedIntegerMap<BoundedIntegerMap<Nodo>>(n);
		iniciar_diccionario(mapas_de_piso,n,L);
		this.prim_nodo = new Nodo(0,0);
		this.ult_nodo = new Nodo(1,L);
		mapas_de_piso.get(0).put(0, prim_nodo);
		mapas_de_piso.get(n).put(L, ult_nodo);
		
		
		Integer nodo_iesimo = 2;
		for(int i = 0;i<portales.length;i++){
			String coordenadas [] = portales[i].split(" "); //O(1)
			int k = 0;
			if(coordenadas[0].length()==0){
				k = 1;
			}
			int piso_1 = Integer.parseInt(coordenadas[0+k]);
			int distancia_1 = Integer.parseInt(coordenadas[1+k]);
			int piso_2 = Integer.parseInt(coordenadas[2+k]);
			int distancia_2 = Integer.parseInt(coordenadas[3+k]);
			Nodo nodo;
			BoundedIntegerMap<Nodo> dicc_piso_1 = mapas_de_piso.get(piso_1);
			if(!dicc_piso_1.containsKey(distancia_1)){
				nodo = new Nodo(nodo_iesimo,distancia_1);
				nodo_iesimo++;
				dicc_piso_1.put(distancia_1,nodo);
			}else{
				nodo = dicc_piso_1.get(distancia_1);
			}
			Nodo nodo2;
			BoundedIntegerMap<Nodo> dicc_piso_2 = mapas_de_piso.get(piso_2);
			if(!dicc_piso_2.containsKey(distancia_2)){ //O(1)
				nodo2 = new Nodo(nodo_iesimo,distancia_2);
				nodo_iesimo++;
				dicc_piso_2.put(distancia_2,nodo2);
			}else{
				nodo2 = dicc_piso_2.get(distancia_2);
			}
			
			nodo.agregar_punto_teletransporte(nodo2);
			nodo2.agregar_punto_teletransporte(nodo);
		}

		Collection<BoundedIntegerMap<Nodo>> conjunto = mapas_de_piso.values();
		Iterator<BoundedIntegerMap<Nodo>> it_principal = conjunto.iterator();
		while(it_principal.hasNext()){ //O(nL) para todos los pisos se recorren todos los puntos del piso 
			BoundedIntegerMap<Nodo> sub_dicc = it_principal.next();
			Collection<Nodo> subconjunto = sub_dicc.values();
			Iterator<Nodo> it_secundario = subconjunto.iterator();
			Set<Nodo> conjunto_aristas_caminando = null;
			Nodo anterior = null;
			while(it_secundario.hasNext()){ //O(L) no existen más de L puntos de portal
				Nodo actual = it_secundario.next();
				if(conjunto_aristas_caminando!=null){
					conjunto_aristas_caminando.add(actual);
				}
				conjunto_aristas_caminando = new LinkedHashSet<Nodo>();
				actual.aristas_caminado = conjunto_aristas_caminando;
				if(anterior!=null){
					conjunto_aristas_caminando.add(anterior);
				}
				anterior = actual;
			}
		}
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
		this.formato = this.leer_palabra();
		this.entrada = this.leer_palabra();
	}
	public Lector(String form, String entr){
		this.formato = form;
		this.entrada = entr;
	}
}