import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DeepWeb{
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

		linea = Lector.readLine();//no necesitamos el numero de lados
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
		try
		{
			grafo.agregarArco(verticeinicial, verticefinal);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
	}

	public static void main(String[] args)
    	throws IOException, IllegalArgumentException
	{
    	//caso en el que no se escribio archivo ni metodo
    	if(args.length < 1){
				System.err.println("Uso: java DeepWeb <nombreArchivo> <origen> <dfs/bfs> [--trunc #] [--arb] [--ord] [--pred]");
				System.err.println("Omita el parametro opcional que considere");
				return;
		}

		int vertice = Integer.parseInt(args[1]);

		if(args[2].equals("dfs")){

			Grafo g = cargarGrafo(args[0]);
			DFS procedimientoDFS = new DFS();

			if(args[3].equals("--trunc")){

				int cota = Integer.parseInt(args[4]);
				if(args.length == 8){
					procedimientoDFS.dfsCliente(vertice, g.grafo, false, cota);
					procedimientoDFS.noConexos();
					procedimientoDFS.dfsCliente(vertice, g.grafo, true, cota);
					procedimientoDFS.orientacion();
					procedimientoDFS.predecesores();

				}else if(args.length == 7){
					if(args[5].equals("--arb")){
						if(args[6].equals("--ord")){
							procedimientoDFS.dfsCliente(vertice, g.grafo, false, cota);
							procedimientoDFS.noConexos();
							procedimientoDFS.dfsCliente(vertice, g.grafo, true, cota);
							procedimientoDFS.orientacion();
							
						}else if(args[6].equals("--pred")){
							procedimientoDFS.dfsCliente(vertice, g.grafo, false, cota);
							procedimientoDFS.noConexos();
							procedimientoDFS.dfsCliente(vertice, g.grafo, true, cota);
							procedimientoDFS.predecesores();
						}
					}else if(args[5].equals("--ord")){
						procedimientoDFS.dfsCliente(vertice, g.grafo, false, cota);
						procedimientoDFS.noConexos();
						procedimientoDFS.predecesores();
					}

				}else if(args.length == 6){
					if(args[5].equals("--arb")){
						procedimientoDFS.dfsCliente(vertice, g.grafo, false, cota);
						procedimientoDFS.noConexos();
						procedimientoDFS.dfsCliente(vertice, g.grafo, true, cota);
						

					}else if(args[5].equals("--ord")){
						procedimientoDFS.dfsCliente(vertice, g.grafo, false, cota);
						procedimientoDFS.noConexos();
						procedimientoDFS.orientacion();

					}else if(args[5].equals("--pred")){
						procedimientoDFS.dfsCliente(vertice, g.grafo, false, cota);
						procedimientoDFS.noConexos();
						procedimientoDFS.predecesores();
					}

				}else if(args.length == 5){
					
					procedimientoDFS.dfsCliente(vertice, g.grafo, false, cota);
					procedimientoDFS.noConexos();
				}
			}else if(args[3].equals("--arb")){
				if(args.length == 6){
					procedimientoDFS.dfsCliente(vertice, g.grafo, false, -1);
					procedimientoDFS.noConexos();
					procedimientoDFS.dfsCliente(vertice, g.grafo, true, -1);
					procedimientoDFS.orientacion();
					procedimientoDFS.predecesores();

				}else if(args.length == 5){
					if(args[4].equals("--ord")){
						procedimientoDFS.dfsCliente(vertice, g.grafo, false, -1);
						procedimientoDFS.noConexos();
						procedimientoDFS.dfsCliente(vertice, g.grafo, true, -1);
						procedimientoDFS.orientacion();
					}else if(args[4].equals("--pred")){
						procedimientoDFS.dfsCliente(vertice, g.grafo, false, -1);
						procedimientoDFS.noConexos();
						procedimientoDFS.dfsCliente(vertice, g.grafo, true, -1);
						procedimientoDFS.predecesores();
					}
				}else if(args.length ==4){
					procedimientoDFS.dfsCliente(vertice, g.grafo, false, -1);
					procedimientoDFS.noConexos();
					procedimientoDFS.dfsCliente(vertice, g.grafo, true, -1);

				}

			}else if(args[3].equals("--ord")){
				if (args.length == 5){
					procedimientoDFS.dfsCliente(vertice, g.grafo, false, -1);
					procedimientoDFS.noConexos();
					procedimientoDFS.orientacion();
					procedimientoDFS.predecesores();
				}else if(args.length == 4){
					procedimientoDFS.dfsCliente(vertice, g.grafo, false, -1);
					procedimientoDFS.noConexos();
					procedimientoDFS.orientacion();
					
				}
			}else if(args[3].equals("--pred")){
				procedimientoDFS.dfsCliente(vertice, g.grafo, false, -1);
					procedimientoDFS.noConexos();
					procedimientoDFS.predecesores();
			}	


			
		}else if(args[2].equals("bfs")){

		}else{
			System.out.println("Formato no valido");
			return;
		}



	}
}