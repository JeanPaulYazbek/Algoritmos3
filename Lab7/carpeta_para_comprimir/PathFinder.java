import java.util.ArrayList;
import java.text.DecimalFormat;

public class PathFinder{


	//funcion que calcula un camino de costo minimo de un vertice inicial a uno final
	public void Astar(GrafoNoDirigido<Integer,Integer> grafoCaso, int verticeInicial, int verticeFinal)
	{
		grafoCaso.resetVertices();//reseteamos los vertices(costos, predecesores, etc)
		grafoCaso.setEuristica(verticeFinal);//calculamos las "h" para todos los vertices
		grafoCaso.obtenerVertice(verticeInicial).costo = 0.0;//el origen debe tener costo 0
		grafoCaso.obtenerVertice(verticeInicial).f = 0.0;//el origen debe tener funcion "f" 0
		ArrayList<Vertice<Integer>> cola = grafoCaso.vertices();//creamos la cola con todos los vertices en el grafo
		int idMinimo = verticeInicial;//marcamos para comenzar el id del verticeInicial
		int cerrados = 1;//contador para saber cuantos caminos cerramos 

		while(idMinimo != verticeFinal){//salimos si en algun momento se alcanza al vertice Final

			idMinimo = extraerMinimoF(cola);//extraemos el vertice de f minimo
			cerrados++;//un vertice se cierra

			Vertice<Integer> verticeMinimo = grafoCaso.obtenerVertice(idMinimo);

			ArrayList<Arista<Integer>> incidentes = grafoCaso.incidentes(idMinimo);//buscamos las aristas incidentes al vertice minimo
			double costoDeVertMin = verticeMinimo.getCosto();
			

			for(Arista<Integer> incidente: incidentes){//nos paseamos por todas las incidentes

				Vertice<Integer> v;

				if(incidente.getExtremo1().getId() == verticeMinimo.getId()){//buscamos el vertice incidente que no es el vertice minimo
					v = incidente.getExtremo2();
				}else{
					v = incidente.getExtremo1();
				}

				double costoArista = incidente.distancia();//guardamos el costo de la arista

				if( v.getCosto() > costoDeVertMin + costoArista){//si el costo de llegar a v es mayor que el de llegar a VertMin Sumado con (VertMin, v)
					//MODIFICAMOS PREDECESOR Y COSTO
					v.costo = costoDeVertMin + costoArista;
					v.predecesor = idMinimo;
					v.f = v.getH() + v.getCosto();//modificamos f acordemente
				}
			}
		}

		showPathA(grafoCaso, verticeInicial, verticeFinal, cerrados);
	}

	//funcion que calcula un camino de costo minimo a cada alcanzable de un vertice inicial
	public void dijkstra(GrafoNoDirigido<Integer,Integer> grafoCaso, int verticeInicial)
	{
		grafoCaso.resetVertices();//reseteamos los vertices(costos, predecesores, etc)
		grafoCaso.obtenerVertice(verticeInicial).costo = 0.0;//el origen debe tener costo 0
		ArrayList<Vertice<Integer>> cola = grafoCaso.vertices();//creamos la cola con todos los vertices en el grafo

		while(cola.size() != 0){

			int idMinimo = extraerMinimo(cola);//extraemos el vertice de costo minimo
			Vertice<Integer> verticeMinimo = grafoCaso.obtenerVertice(idMinimo);

			ArrayList<Arista<Integer>> incidentes = grafoCaso.incidentes(idMinimo);//buscamos las aristas incidentes al vertice minimo
			double costoDeVertMin = verticeMinimo.getCosto();

			for(Arista<Integer> incidente: incidentes){//nos paseamos por todas las incidentes

				Vertice<Integer> v;

				if(incidente.getExtremo1().getId() == verticeMinimo.getId()){//buscamos el vertice incidente que no es el vertice minimo
					v = incidente.getExtremo2();
				}else{
					v = incidente.getExtremo1();
				}

				double costoArista = incidente.distancia();//guardamos el costo de la arista

				if( v.getCosto() > costoDeVertMin + costoArista){//si el costo de llegar a v es mayor que el de llegar a VertMin Sumado con (VertMin, v)
					//MODIFICAMOS PREDECESOR Y COSTO
					v.costo = costoDeVertMin + costoArista;
					v.predecesor = idMinimo;
					
				}
			}
		}
	}

	//funcion que extrae el vertice de costo minimo de una lista y retorna el id de ese vertice
	public int extraerMinimo(ArrayList<Vertice<Integer>> cola){

		int n = cola.size();//para iterar sobre la lista 
		double minimo = cola.get(0).getCosto();//minimo por defecto
		int indiceminimo = 0;//indice del vertice con menor costo en la lista
		double costoactual;

		for(int i = 0; i<n; i++){//iteramos sobre la cola

			costoactual = cola.get(i).getCosto();//guardamos el costo del vertice actual
			if(costoactual < minimo){//si el costo del vertice actual es menor al del minimo actual
				minimo = costoactual;//hacemos el minimo el actual
				indiceminimo = i;//marcamos el indice
			}
		}

		Vertice<Integer> verticeMinimo = cola.get(indiceminimo);//extraemos el vertice minimo
		cola.remove(indiceminimo);//lo removemos de la cola
		return verticeMinimo.getId();//regresamos el vertice minimo

	}


	//funcion que extrae el vertice de "f" minimo de una lista y retorna el id de ese vertice
	public int extraerMinimoF(ArrayList<Vertice<Integer>> cola){

		int n = cola.size();//para iterar sobre la lista 
		double minimo = cola.get(0).getF();//minimo por defecto
		int indiceminimo = 0;//indice del vertice con menor costo en la lista
		double costoactual;

		for(int i = 0; i<n; i++){//iteramos sobre la cola

			costoactual = cola.get(i).getF();//guardamos el costo del vertice actual
			if(costoactual < minimo){//si el costo del vertice actual es menor al del minimo actual
				minimo = costoactual;//hacemos el minimo el actual
				indiceminimo = i;//marcamos el indice
			}
		}

		Vertice<Integer> verticeMinimo = cola.get(indiceminimo);//extraemos el vertice minimo
		cola.remove(indiceminimo);//lo removemos de la cola
		return verticeMinimo.getId();//regresamos el vertice minimo

	}

	/**
	 * funcion que muestra los caminos mas cortos a cada mesa desde la cocina para dijkstra
	 */
	public void showPath(GrafoNoDirigido<Integer,Integer> grafoCaso,int verticeInicial)
	{
		System.out.println("");
		System.out.println("*** DIJKSTRA ***");
		System.out.println("");

		int n =grafoCaso.numeroDeVertices();
		String line;
		Vertice<Integer> v;
		int j;
		DecimalFormat formateador = new DecimalFormat("0.0#");
		String toPrint;
		for(int k = 0; k< n; k++)
		{
			if (grafoCaso.obtenerVertice(k).getCosto()==9999999.00)
			{
				line="Nodo "+k+": No es alcanzable";
			}
			else
			{
			v = grafoCaso.obtenerVertice(k);
			line="";
			j=0;
			while(v.getPredecesor() != -1)
			{
				line="->"+Integer.toString(v.getId())+line;
				v=grafoCaso.obtenerVertice(v.getPredecesor());
				j+=1;
			}
			line="Nodo "+k+": "+verticeInicial+line+"\t"+j+" lados (costo "
				+formateador.format(grafoCaso.obtenerVertice(k).getCosto())+")";
			}
			System.out.println(line);
		}

		ArrayList<Vertice<Integer>> verticesFinales = grafoCaso.vertices();

		//BUSCANDO LOS CERRADOS Y ABIERTOS
		int abiertos_cerrados = 0;
		for(Vertice<Integer> vertice: verticesFinales){

			if(vertice.getCosto() != 9999999.00){//si no fue visitado
				abiertos_cerrados = abiertos_cerrados + 1;
			}

		}
		//IMPRIMIMOS LOS DATOS
		System.out.println("");
		System.out.println("DATOS:");
		System.out.println("Nodos ABIERTOS: " + abiertos_cerrados);
		System.out.println("Nodos CERRADOS: " + abiertos_cerrados);
		System.out.println("Nodos NO VISITADOS: " + (verticesFinales.size() - abiertos_cerrados));
		
	}


	//funcion que imprime el camino mas corto de un nodo a otro usando A*
	public void showPathA(GrafoNoDirigido<Integer,Integer> grafoCaso,int verticeInicial, int verticeFinal, int cerrados)
	{
		System.out.println("");
		

		int n =grafoCaso.numeroDeVertices();
		String line;
		Vertice<Integer> v;
		int j;
		DecimalFormat formateador = new DecimalFormat("0.0#");
		String toPrint;
		int k = verticeFinal;

		if (grafoCaso.obtenerVertice(k).getCosto()==9999999.00)
		{
			line="Nodo "+k+": No es alcanzable";
			System.out.println(line);
			return;
		}
		else
		{
		v = grafoCaso.obtenerVertice(k);
		line="";
		j=0;
		while(v.getPredecesor() != -1)
		{
			line="->"+Integer.toString(v.getId())+line;
			v=grafoCaso.obtenerVertice(v.getPredecesor());
			j+=1;
		}
		line="Nodo "+k+": "+verticeInicial+line+"\t"+j+" lados (costo "
			+formateador.format(grafoCaso.obtenerVertice(k).getCosto())+")";
		}
		System.out.println(line);
		

		ArrayList<Vertice<Integer>> verticesFinales = grafoCaso.vertices();

		//BUSCANDO LOS CERRADOS Y ABIERTOS
		int abiertos = 0;
		for(Vertice<Integer> vertice: verticesFinales){

			if(vertice.getCosto() != 9999999.00){//si no fue visitado
				abiertos = abiertos + 1;
			}

		}
		//IMPRIMIMOS LOS DATOS
		System.out.println("");
		System.out.println("DATOS CAMINO:");
		System.out.println("Nodos ABIERTOS: " + abiertos);
		System.out.println("Nodos CERRADOS: " + cerrados);
		System.out.println("Nodos NO VISITADOS: " + (verticesFinales.size()-abiertos));
		
	}
}