import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays; 
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;

public class OrdenTopologicoDfs{
	
	static Boolean ciclo;
	static int contador;
	static Vertice<Boolean> verticeCiclo;
	static Vertice<Boolean>[] ordenes;

	public static boolean DfsVisita(GrafoDirigido<Boolean, Integer> grafoPrecedencia){

		ciclo = false;

		contador = grafoPrecedencia.numeroDeVertices();//creamos el contador para el orden topologico
		ArrayList<Vertice<Boolean>> vertices = grafoPrecedencia.vertices();//buscamos los vertices del grafo
		ordenes = new Vertice[contador];//arreglo para guardar vertices donde el indice representa el orden

		for(Vertice<Boolean> vert : vertices){
			vert.dato = false;
		}

		for(Vertice<Boolean> vert : vertices){//por cada vertice
			if (vert.getDato() == false){//si el vertice no ha sido visitado
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

//		for(int i = ordenes.length -  1 ; i > -1 ; i--){
//			System.out.println(ordenes[i].getId());//imprimimos los vertices en orden topologico
//		}
		return true;
	}

	public static void DfsRecursivo(Vertice<Boolean> vertice, GrafoDirigido<Boolean, Integer> grafoPrecedencia){

		vertice.dato = true;//lo marcamos como visitado
		vertice.color = "Gray";//cambiamos su color a gris
		ArrayList<Vertice<Boolean>> sucesores = grafoPrecedencia.sucesores(vertice.getId());//extraemos los sucesores del vertice actual

		for(Vertice<Boolean> sucesor : sucesores){//por cada sucesor
			sucesor.predecesor = vertice;
			
			if (sucesor.getColor().equals("Gray")){//si nos regresamos a un vertice que esta en gris actualmente es que hay un circuito
				verticeCiclo = sucesor;
//				System.out.println(sucesor.predecesor.getId());
				ciclo = true;
			}

			if (sucesor.getDato() ==false){//si no ha sido visitado
//				sucesor.predecesor = vertice;
//				System.out.println(sucesor.predecesor.getId());
				DfsRecursivo(sucesor, grafoPrecedencia);//llamamos la recursion
			}
		}

		contador = contador -1; //bajamos el contador
		vertice.color = "Black";//marcamos de negro el vertice con orden
		ordenes[contador] = vertice;//marcamos en el arreglo quien tiene ese orden

	}


	public static void main(String[] args)
	throws IOException
	{

		String archivo = args[0];
		BufferedReader lector = new BufferedReader(new FileReader(archivo));

		String line = lector.readLine(); 

		int numeroLine = 1;

		//Transformadores para la conversion de archivo a grafo
		Transformer<String, Integer> transformadorarcoInt= new TransformarInteger();
		Transformer<String, Boolean> transformadorInt= new TransformarBoolean();

		while(true){
			
			String[] informacioncaso = line.split(" ");
			int nroLados = Integer.parseInt(informacioncaso[1]);
			int nroVertices = Integer.parseInt(informacioncaso[0]);

			if( nroLados == 0  && nroVertices == 0){

				break;

			}else{
				//creamos el grafo de la universidad base
				GrafoDirigido<Boolean, Integer> nuevoGrafoDirigido = new GrafoDirigido<Boolean, Integer>();
				//cargamos el grafo de la universidad base
//				nuevoGrafoDirigido.cargarGrafo(args[0], numeroLine, transformadorInt, transformadorarcoInt);
				DfsVisita(nuevoGrafoDirigido);//le asignamos un orden topologico

			}

			for(int x = numeroLine; x < numeroLine + nroLados + 1; x++){//saltamos lineas hasta llegar al caso
   				line = lector.readLine();
			}
			numeroLine = numeroLine + nroLados + 1;
		
		}

	}

}