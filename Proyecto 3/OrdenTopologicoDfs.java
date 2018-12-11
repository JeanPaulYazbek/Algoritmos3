import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays; 
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;

public class OrdenTopologicoDfs
{	
	static Boolean ciclo;
	static int contador;
	static Vertice<Boolean> verticeCiclo;
	static Vertice<Boolean>[] ordenes;
////////////////////////////////////////////////////////////////////////////////
	public static boolean DfsVisita(GrafoDirigido<Boolean,Integer> grafoPrecedencia)
	{
		ciclo = false;

		contador = grafoPrecedencia.numeroDeVertices();//creamos el contador para el orden topologico
		ArrayList<Vertice<Boolean>> vertices = grafoPrecedencia.vertices();//buscamos los vertices del grafo
		ordenes = new Vertice[contador];//arreglo para guardar vertices donde el indice representa el orden

		for(Vertice<Boolean> vert : vertices)
		{
			vert.dato = false;
		}

		for(Vertice<Boolean> vert : vertices)
		{//por cada vertice
			if (vert.getDato() == false)
			{//si el vertice no ha sido visitado
				DfsRecursivo(vert, grafoPrecedencia);
				if (ciclo)
				{
					System.out.println("Su configuración contiene un ciclo:");
					String caminoCiclo = verticeCiclo.getId();
					Vertice<Boolean> verticeCiclopredecesor = verticeCiclo.predecesor;
//					System.out.println(caminoCiclo);
//					System.out.println(verticeCiclo.predecesor.getId());
					while(!(verticeCiclopredecesor.getId().equals(verticeCiclo.getId())))
					{
						caminoCiclo=verticeCiclopredecesor.getId()+"->"+caminoCiclo;
						verticeCiclopredecesor = verticeCiclopredecesor.predecesor;
					}
					System.out.println(caminoCiclo);
					return false;
				}
			}
		}
		return true;
	}
////////////////////////////////////////////////////////////////////////////////
	public static void DfsRecursivo(Vertice<Boolean> vertice,
		GrafoDirigido<Boolean,Integer> grafoPrecedencia)
	{
		vertice.dato = true;//lo marcamos como visitado
		vertice.color = "Gray";//cambiamos su color a gris
		ArrayList<Vertice<Boolean>> sucesores = grafoPrecedencia.sucesores(vertice.getId());//extraemos los sucesores del vertice actual

		for(Vertice<Boolean> sucesor : sucesores)
		{//por cada sucesor
			sucesor.predecesor = vertice;
			
			if (sucesor.getColor().equals("Gray"))
			{//si nos regresamos a un vertice que esta en gris actualmente es que hay un circuito
				verticeCiclo = sucesor;
				ciclo = true;
			}

			if (sucesor.getDato() ==false)
			{//si no ha sido visitado
				DfsRecursivo(sucesor, grafoPrecedencia);//llamamos la recursion
			}
		}
		contador = contador -1; //bajamos el contador
		vertice.color = "Black";//marcamos de negro el vertice con orden
		ordenes[contador] = vertice;//marcamos en el arreglo quien tiene ese orden
	}
}