import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Mesero
{
	/**
	 * funcion que dado dada la entrada correcta del cliente muestra
	 * hace los llamados correspondientes para mostrar en pantalla
	 * los caminos mas cortos desde la cocina a las mesas siguiendo
	 * el formato Nodo <i>: n1->n2->…->nL <L> lados (costo <C>)
	 * @param args argumentos con los que se llama a Mesero.java
	 * @throws IOException
	 */
	public static void main(String[] args)
	throws IOException
	{
		//caso en el que no se escribio archivo ni metodo
    	if(args.length < 2)
    	{
			System.err.println("Uso: java Mesero <instancia> <origen>");
			return;
		}
		//creamos el grafo del restaurante base
		GrafoNoDirigido<Integer,Integer> restaurante = new GrafoNoDirigido<Integer,Integer>();
		//cargamos las mesas y los caminos
		restaurante.cargarGrafo(args[0]);
		//guardamos el vertice inicial
		int cocina = Integer.parseInt(args[1]);
		//aplicamos el metodo de busqueda de caminos mas cortos
		PathFinder repertorio = new PathFinder();


		//DIJKSTRA--------------------------------------------------------------------------------------------
		long tiempoinicialDijkstra = System.currentTimeMillis();//marcamos el comienzo de Dijkstra
		repertorio.dijkstra(restaurante,cocina);
		//mostramos los caminos con el formato solicitado
		repertorio.showPath(restaurante,cocina);
		long tiempofinalDijkstra = System.currentTimeMillis() - tiempoinicialDijkstra;//marcamos tiempo final

		System.out.println("TIEMPO DIJKSTRA: " + (tiempofinalDijkstra/1000.00000000) + " segs");
		System.out.println("");

		//A*---------------------------------------------------------------------------------------------------
		System.out.println("*** A* ***");
		System.out.println("");

		int n = restaurante.numeroDeVertices();
		long tiempofinalfinalA = 0;//contador parar el tiempo final
		for(int i = 0; i<n; i++){//Aplicamos A* a cada alcanzable

			long tiempoinicialA = System.currentTimeMillis();//marcamos el comienzo de esta iteracion de A*
			repertorio.Astar(restaurante,cocina, i);
			long tiempofinalA = System.currentTimeMillis() - tiempoinicialA;//marcamos el comienzo de esta iteracion de A*
			System.out.println("TIEMPO A*: " + (tiempofinalA/1000.00000000) + " segs");
			System.out.println("");

			tiempofinalfinalA = tiempofinalfinalA + tiempofinalA;
		}
		System.out.println("TIEMPO FINAL A*: " + (tiempofinalfinalA/1000.00000000) + " segs");
		System.out.println("");
	}
}