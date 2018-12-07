
import java.util.ArrayList;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Planificacion{

	static int contador;

	public static Vertice<Boolean> raiz(GrafoDirigido<Boolean, Integer> grafoPrecedencia){

		ArrayList<Vertice<Boolean>> vertices = grafoPrecedencia.vertices();//buscamos los vertices del grafo
		Boolean laRaiz = false;

		for(Vertice<Boolean> candidato: vertices){
			for(Vertice<Boolean> vert : vertices){
				vert.dato = false;
			}
			DfsRecursivo(candidato, grafoPrecedencia);

			laRaiz = true;
			for(Vertice<Boolean> vert : vertices){
				if(vert.getDato() == false){
					laRaiz = false;//no es raiz
				}
				
			}

			if(laRaiz){
				return candidato;
			}
		}
		
		return null;
	}

	public static void DfsRecursivo(Vertice<Boolean> vertice, GrafoDirigido<Boolean, Integer> grafoPrecedencia){

		vertice.dato = true;//lo marcamos como visitado
		ArrayList<Vertice<Boolean>> sucesores = grafoPrecedencia.sucesores(vertice.getId());//extraemos los sucesores del vertice actual

		for(Vertice<Boolean> sucesor : sucesores){//por cada sucesor

			if (sucesor.getDato() ==false){//si no ha sido visitado
				DfsRecursivo(sucesor, grafoPrecedencia);//llamamos la recursion
			}
		}

	}

/**	public static ArrayList<int[]> DfsVisista(GrafoDirigido<Boolean, Integer> grafoPrecedencia){

		ArrayList<Vertice<Boolean>> vertices = grafoPrecedencia.vertices();//buscamos los vertices del grafo
		ArrayList<int[]> largos = new ArrayList<int[]>();

		for(Vertice<Boolean> candidato: vertices){
			for(Vertice<Boolean> vert : vertices){
				vert.dato = false;
			}
			contador = 0;
			int[] camino = new int[2]();

			DfsRecursivo2(candidato, grafoPrecedencia);

			camino = [Integer.parseInt(candidato.getId()); contador];

			largos.add(camino);
			
		}
		 
		return largos;
	}
**/
/**	public static void DfsRecursivo2(Vertice<Boolean> vertice, GrafoDirigido<Boolean, Integer> grafoPrecedencia){

		vertice.dato = true;//lo marcamos como visitado
		ArrayList<Arco<Integer>> incidentes = grafoPrecedencia.incidentes(vertice.getId());//extraemos los sucesores del vertice actual
		contador = contador + 1;

		for(Arco<Integer> sucesor : incidentes){//por cada sucesor


			if (sucesor.getDato() != -1){//si no ha sido visitado
				if(sucesor.getExtremoInicial().getId() == vertice.getId()){

					DfsRecursivo2(sucesor.getExtremoFinal, grafoPrecedencia);//llamamos la recursion
				}
					
			}
		}

	}
**/
	public static void main(String[] args)
	throws IOException
	{

		String archivo = args[0];
		BufferedReader Lector = new BufferedReader(new FileReader(archivo));
		String linea = Lector.readLine(); 

		while(!linea.equals("0")){
			//Cargamos el grafo
			GrafoDirigido<Boolean, Integer> grafoCaso = new GrafoDirigido<Boolean, Integer>();
			while(!linea.equals("0")){
				
				
				String[] informacion = linea.split(" ");
				int cuantos = informacion.length;
				grafoCaso.agregarVertice(informacion[0], false, 0.0);
				for (int i = 1; i< cuantos -1; i++){
					String conexion = informacion[i];
					String[] adyacente = informacion[i].split("");
					String[] adyacenteD = informacion[i].split("d");
					String[] adyacenteDcuantos = adyacenteD[0].split("");
					String[] adyacenteU = informacion[i].split("u");
					String[] adyacenteUcuantos = adyacenteU[0].split("");
					
					if( adyacenteDcuantos.length != adyacente.length ){
						grafoCaso.agregarVertice(adyacenteD[0], false, 0.0);
						grafoCaso.agregarArco(informacion[0] + adyacenteD[0], 0, 0.0, informacion[0], adyacenteD[0]);
					}else if(adyacenteUcuantos.length != adyacente.length){
						grafoCaso.agregarVertice(adyacenteU[0], false, 0.0);
						grafoCaso.agregarArco(adyacenteU[0] + informacion[0] , 0, 0.0, adyacenteU[0], informacion[0]);
					}else{
						grafoCaso.agregarVertice(informacion[i], false, 0.0);
						grafoCaso.agregarArco(informacion[i] + informacion[0] , -1, 0.0, informacion[i], informacion[0]);
						grafoCaso.agregarArco(informacion[0] + informacion[i] , -1, 0.0, informacion[0], informacion[i]);

					}
				}

				
				linea = Lector.readLine();

			}
			System.out.println("Grafo:");
			System.out.println(grafoCaso.toString());

			Vertice<Boolean> raiz = raiz(grafoCaso);
			ArrayList<Arco<Integer>> conflictos= grafoCaso.incidentes(raiz.getId());
			ArrayList<Vertice<Boolean>> raices = new ArrayList<Vertice<Boolean>>();
			

			for( Arco<Integer> aritsa : conflictos){
				if(aritsa.getDato() == -1){
					raices.add(aritsa.getExtremoInicial());
					
				}
			}
			
			linea = Lector.readLine();
		}

	}
	//no pude terminar de plantearlo

}