import java.util.ArrayList;
import java.text.DecimalFormat;

public class PathFinder{

	public void bellman(GrafoNoDirigido<Integer,Integer> grafoCaso, int verticeInicial)
	{
		int nrovertices = grafoCaso.numeroDeVertices();//guardamos la cantidad
		// de mesas
		Boolean cambio = true;//para saber si el algoritmo sigue encontrando
		// caminos mas cortos
		int i = 1;//para comenzar la iteracion
		grafoCaso.obtenerVertice(verticeInicial).costo = 0.0;//ponemos el costo
		// de llegar a la cocina en 0
		ArrayList<Arista<Integer>> caminos = grafoCaso.aristas();//lista con todos
		// los caminos dentro del restaurante

		//PROCEDIMIENTO Bellman
		while(i <= nrovertices && cambio)
		{// ponemos la cota de clausura y si no hay cambios en una iteracion salimos

			cambio = false;

			for(Arista<Integer> camino: caminos)
			{//recorremos todos los caminos en el campus
				
				if( camino.getExtremo2().getCosto() > (camino.getExtremo1().getCosto()
				 + camino.distancia()))
				{//sea (v,w) si el costo de llegar a w es mayor que el de llegar
					//a v sumado con el costo de un camino entre v y w 
					camino.getExtremo2().costo = camino.getExtremo1().costo
					 + camino.distancia();//modificamos los costos de los vertices
					camino.getExtremo2().predecesor = camino.getExtremo1().getId();
					//modificamos por quien se llega al vertice
					camino.getExtremo2().aristaPredecesora = camino;
					//modificamos por que arista se llega al vertice w
					cambio = true;//marcamos que hubo cambios
				}

				if( camino.getExtremo1().getCosto() > (camino.getExtremo2().getCosto()
				 + camino.distancia()))
				 {//sea (w,v) si el costo de llegar a v es mayor que el de llegar
					//a w sumado con el costo de un camino entre v y w 
					camino.getExtremo1().costo = camino.getExtremo2().costo 
					+ camino.distancia();//modificamos los costos de los vertices
					camino.getExtremo1().predecesor = camino.getExtremo2().getId();
					//modificamos por quien se llega al vertice
					camino.getExtremo1().aristaPredecesora = camino;
					cambio = true;//marcamos que hubo cambios
				}
			}
			i = i + 1;
		}

	}

	/**
	 * funcion que muestra los caminos mas cortos a cada mesa desde la cocina
	 */
	public void showPath(GrafoNoDirigido<Integer,Integer> grafoCaso,int verticeInicial)
	{
		int n =grafoCaso.numeroDeVertices();
		String line;
		Vertice<Integer> v;
		int j;
		DecimalFormat formateador = new DecimalFormat("0.0#");
		String toPrint;
		for(int k = 0; k< n; k++)
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
			System.out.println(line);
		}
	}
}