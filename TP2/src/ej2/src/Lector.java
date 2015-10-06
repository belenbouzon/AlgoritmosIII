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
		//String formato = this.leer_palabra();
		String parametros [] = formato.split(" "); //O(1)
		this.n = Integer.parseInt(parametros[0]);
		this.L = Integer.parseInt(parametros[1]);
		//String entrada = this.leer_palabra();
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
			{
				BoundedIntegerMap<Nodo> piso = mapas_de_piso.get(piso_1);
				if(!piso.containsKey(distancia_1)){
					nodo = new Nodo(nodo_iesimo,distancia_1);
					nodo_iesimo++;
					/*Set<Entry<Integer,Nodo>> otros = piso.entrySet();
					Iterator<Entry<Integer,Nodo>> it = otros.iterator();
					while(it.hasNext()){
						Entry<Integer,Nodo> otro_nodo = it.next();
						otro_nodo.getValue().agregar_arista(nodo, absoluto(otro_nodo.getKey()-distancia_1));
						nodo.agregar_arista(otro_nodo.getValue(), absoluto(otro_nodo.getKey()-distancia_1));
					}*/
					piso.put(distancia_1,nodo);
				}else{
					nodo = piso.get(distancia_1);
				}
			}
			Nodo nodo2;
			//Nodo nodo2 = incluir_nodo(piso_2,distancia_2,nodo_iesimo,mapas_de_piso);
			{
				BoundedIntegerMap<Nodo> piso = mapas_de_piso.get(piso_2);
				if(!piso.containsKey(distancia_2)){ //O(1)
					nodo2 = new Nodo(nodo_iesimo,distancia_2);
					nodo_iesimo++;
					piso.put(distancia_2,nodo2);
				}else{
					nodo2 = piso.get(distancia_2);
				}
			}
			nodo.agregar_punto_teletransporte(nodo2);
			nodo2.agregar_punto_teletransporte(nodo);
		}
		//----------Test------------
		/*for(int i=0;i<=L;i++){
			if(mapas_de_piso.containsKey(i)){
				Set<Entry<Integer,Nodo>> otros = mapas_de_piso.get(i).entrySet();
				Iterator<Entry<Integer,Nodo>> it = otros.iterator();
				while(it.hasNext()){
					Nodo nn = it.next().getValue();
					System.out.printf("piso: %d,nodo: %d\n", i,nn.identificacion);
					System.out.print(nn.aristas_teletranspoorte);
					System.out.printf("\n");
				}
			}
		}*/
		
		//--------------------------

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
		//----------Test------------
		//System.out.printf("\n id buscada: %d\n",mapas_de_piso.get(0).get(5).identificacion);
		/*for(int i=0;i<=L;i++){
			if(mapas_de_piso.containsKey(i)){
				Collection<Nodo> otros = mapas_de_piso.get(i).values();
				Iterator<Nodo> it = otros.iterator();
				while(it.hasNext()){
					Nodo nn = it.next();
					System.out.printf("Nodo: %d ", nn.identificacion);
					System.out.print(nn.aristas_caminado);
					System.out.printf("\n");
				}
			}
		}*/
		
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
		this.formato = this.leer_palabra();
		this.entrada = this.leer_palabra();
	}
	public Lector(String form, String entr){
		this.formato = form;
		this.entrada = entr;
	}
}