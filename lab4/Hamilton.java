import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Hamilton{

	//Carga el grafo desde un archivo en forma de matriz de adyacencias y lo retorna como objeto de la clase grafo
	static Grafo cargarGrafo(String nombreArchivo)
			throws IOException
	{
		Grafo salida = new Grafo();
		
		BufferedReader Lector = new BufferedReader(new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		String[] cuantosVertices = linea.split(" ");
		int numeroVertices = Integer.parseInt(cuantosVertices[0]);
		salida.agregarVertice(numeroVertices-1);

		linea = Lector.readLine();
		linea = Lector.readLine();
		
			
		
		do{
			crearGrafo(linea, (Grafo)salida);
		
		}while((linea = Lector.readLine()) != null);
		
		return salida;
	}


	//ayuda a cargar el grafo linea por linea
	private static void crearGrafo(String linea, Grafo grafo)
			throws IllegalArgumentException
	{	
		//creamos una lista con todos los items separados por espacios
		String[] verticeinfo = linea.split(" ");
		
		
		//convertimos los vertices en int
		int verticeinicial = Integer.parseInt(verticeinfo[0]);
		int verticefinal = Integer.parseInt(verticeinfo[1]);
		//agregamos el Arco a la estructura
		try{
			grafo.agregarArco(verticeinicial, verticefinal);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
	}



	public static void main(String[] args)
    throws IOException, IllegalArgumentException
	{
    	//caso en el que no se escribio archivo ni metodo
    	if(args.length < 1){
				System.err.println("Uso: java Hamilton <nombreArchivo> DFS/BFS");
				return;
		}

		
		//en caso de haberse pedido DFS se entra en este if
		if(args[1].equals("DFS")){
			
			//creamos el grafo logico a partir del archivo
			Grafo g = cargarGrafo(args[0]);
		
		
			//parametros que usaremos para DFS
			boolean [] visited = new boolean[g.grafo[0].length];
			ArrayList<Integer> camino;
			DFS procedimientoDFS = new DFS();
			//iniciamos DFS con cada vertice del grafo
			for(int i = 0; i<g.grafo[0].length; i++){

				//si DFS no ha terminado volvemos a comenzar
				if(!procedimientoDFS.dfsfinished){
					camino = new ArrayList<Integer>(); 
					camino.add(i);
					System.out.println("Recorrido desde " + i +":");
					procedimientoDFS.dfs(i, visited, "  ", 1, camino, g.grafo);
					visited = new boolean[g.grafo[i].length];
					System.out.println("");
				}

				//si llegamos al ultimo vertice y DFS no termino es que no hay camino
				if(i == g.grafo[0].length -1 && !procedimientoDFS.dfsfinished){
					System.out.println("No existe camino");
				}
			}
		}

		
	
  
}
}