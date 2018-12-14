import java.util.Hashtable;
import java.util.ArrayList;
import java.io.IOException;

public class PrimAlgoritmo{

	/**
	*funcion que calcula el arbol minimo cobertor de un grafoConexo 
	* @param le pasamos un grafo conexo
	**/
	public static void prim(GrafoNoDirigido<Integer, Integer> grafoConexo){

		grafoConexo.resetVertices();//reseteamos los atributos de los vertices

		ArrayList<Vertice<Integer>> vertices = grafoConexo.vertices();//buscamos los vertices del grafo dado

		if(vertices.size() > 0){
			vertices.get(0).costo = 0.0;//utilizamos como nodo inicial del algoritmo el primer vertice en caso de que exista
										//y marcamos su costo como 0
		}

		while(vertices.size() != 0){	//mientras queden vertices
			Vertice<Integer> ver = extraerMinimo(vertices); //extraemos el vertice con minimo costo
			ArrayList<Arista<Integer>> incidentes = grafoConexo.incidentes(ver.getId());//buscamos la aristas incidentes sobre el vertice
			for(Arista<Integer> arista : incidentes){//por cada arista incidente

				Vertice<Integer> w;
				//Buscamos el extremo que no es el vertice que acabamos de extraer
				if(arista.getExtremo1().getId().equals(ver.getId())){
					w = arista.getExtremo2();
				}else{
					w = arista.getExtremo1();
				}
				if( !(w.getSalio()) && (w.getCosto() > arista.getPeso())){//si ese otro vertice no esta en los ya extraidos y
																		  //y su costo es mayor que el de la arista incidente
					w.costo = arista.getPeso();//modificamos su costo
					w.predecesor = ver;//marcamos el vertice predecesor
					w.aristaPredecesora = arista;//y marcamos una arista que conecta a w en el grafo conexo
				}
			}

		}


	}

	/**
	* funcion que toma una lista de objetos vertices y devuelve el que tenga menor costo
	* @param una lista de vertices
	**/
	public static Vertice<Integer> extraerMinimo(ArrayList<Vertice<Integer>> listaVertices){

		int n = listaVertices.size();//cargamos el largo de la lista
		double minimo = listaVertices.get(0).getCosto();// escogemos el primer vertice como minimo por defecto
		int indiceMinimo = 0;//marcamos el indice del primero
		for(int i = 0; i < n; i++){//por cada vertice
			if(listaVertices.get(i).getCosto() < minimo){//si es menor
				minimo = listaVertices.get(i).getCosto();//marcamos el nuevo minimo
				indiceMinimo = i;//marcamos el indice del nuevo minimo
			}
		}

		Vertice<Integer> verticeMinimo = listaVertices.get(indiceMinimo);// extraemos el minimo vertice
		listaVertices.remove(indiceMinimo);//lo removemos de la lista
		verticeMinimo.salio = true;//lo marcamos como fuera de la lista para el uso de prim
		return verticeMinimo;//retornamos el vertice de minimo costo
	}

	
}