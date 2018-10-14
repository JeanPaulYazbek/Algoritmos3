import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**<p>Programa para convertir archivos Lista de Adyacencia a archivos Matriz de
 * Adyacencia.</p>
 * 
 * <p>Un archivo Lista de Adyacencia representa un grafo, donde cada
 * l&iacute;nea tiene el formato:</p>
 * <blockquote>
 * <code>v<sub>i</sub>: v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>m</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>i</sub></code> es un n&uacute;mero de un v&eacute;rtice, y 
 * <code>v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>m</sub></code> son los n&uacute;meros
 * de los v&eacute;rtices adyacentes a <code>v<sub>i</sub></code></p>
 * <p>mientras que un archivo Matriz de adyacencia representa un grafo
 * en el formato</p>
 * <blockquote>
 * <code> &nbsp; v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>n</sub><br>
 * v<sub>1</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub><br>
 * v<sub>2</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub><br>
 * &vellip;<br>
 * v<sub>n</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>n</sub></code> son
 * los n&uacute;meros que identifican a los v&eacute;rtices, y <code>a<sub>i</sub></code>
 * indica si existe un arco desde el v&eacute;rtice al inicio de esa fila, y el
 * v&eacute;rtice al que corresponde esa columna.</p>
 * <p>El programa {@link #main} lee un archivo, detecta (a trav&eacute;s de
 * {@link #esLista(String)}) el formato del archivo, lo carga (a trav&eacute;s de
 * {@link #cargarGrafo(String)}) en un {@link TraductorGrafo}, y lo imprime
 * en el format contrario. Las funciones se ofrecen a nivel de package.</p>
 */
public class Cliente{
	/**Detecta el tipo de archivo basado en una muestra de una l&iacute;nea tomada del
	 * archivo.
	 * 
	 * @param  linea              La l&iacute;nea de muestra tomada del archivo
	 * @return <code>true</code>  si est&aacute; en el formato de un archivo lista de
	 *                            adyacencias;<br></br>
	 *         <code>false</code> si est&aacute; en el formato de un archivo Matriz de
	 *                            adyacencias.
	 * 
	 * @throws IllegalArgumentException si la l&iacute;nea no tiene ninguno de los dos
	 *                                  formatos
	 */
	static boolean esLista(String linea)
			throws IllegalArgumentException
	{	//separamos la primera linea por :
		String[] primerafila = linea.split(":");
		//guardamos el len de esa lista
		int valor = primerafila.length;
		//si el len es 2 entonces no era una matriz de adyacencias
		if (valor == 2){
			return true;
		}else{
			return false;
		}
	}
	
	/**Carga la <code>linea</code> de un archivo Lista de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La l&iacute;nea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual ser&aacute; cargada la l&iacute;nea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static void cargarLista(String linea, TraductorDesdeLista grafo)
			throws IllegalArgumentException
	{	
		//creamos una lista con todos los items separados por espacios
		String[] verticeinfo = linea.split(" ");
		//guardamos el largo de esa lista para saber cuantos arcos anadir
		int n = verticeinfo.length;

		//creamos una lista de items separados por ":" para poder tomar el vertice principL
		String[] verticefijo = linea.split(":");

		//convertimos el vertice en int
		int vertice = Integer.parseInt(verticefijo[0]);
		//agregamos el vertice a la estructur
		grafo.agregarVertice(vertice);

		// agregamos todos los arcos relacionados al vertice principal
		for(int i=1; i<n; i++){
			
			int verticearco = Integer.parseInt(verticeinfo[i]);
			grafo.agregarArco(vertice, verticearco);
		}
	}
	
	/**Carga la <code>linea</code> de un archivo Matriz de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La l&iacute;nea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual ser&aacute; cargada la l&iacute;nea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static void cargarMatriz(String linea, TraductorDesdeMatriz grafo)
			throws IllegalArgumentException
	{
		try (BufferedReader readr = new BufferedReader(new FileReader(linea))) 
			{
				//Almacenamos temporael contenido de la linea cero
				String firstLine = readr.readLine();
				
				// Obtenemos los vertices de la primera linea
				String[] firstLineSplit = firstLine.split(" ");
				int numberOfVertices = firstLineSplit.length;
				numberOfVertices -=3;				// no contamos los 3 espacios en blanco

				//Creamos la matriz y almacenamos los vertices en la primera fila
				// dejando la primera casilla en 0.
				int[][] graf = new int [numberOfVertices+1][numberOfVertices+1];
				for (int i = 3; i < numberOfVertices+3; ++i)
				{
					graf[0][i-2]=Integer.parseInt(firstLineSplit[i]);
				}

				//Almacenamos el contenido de las lineas 2 en adelante
				String myCurrentLine;
				int k=0;
				while ((myCurrentLine = readr.readLine()) != null)
				{
					if (k>0)
					{
						String[] a = myCurrentLine.split("|");
						int w=0;
						for (int i = 0; i < a.length; ++i)
						{
							if ((!a[i].equals(" ")))
							{
								if (!a[i].equals("|"))
								{
									graf[k][w]=Integer.parseInt(a[i]);
									w+=1;
								}
							}
						}
					}
					k+=1;
				}
				grafo.grafo = graf;
			/*Hemos creado una matriz logica igual a la representacion grafica
			en la que no importa quienes sean los vertices mientras que sean int
			o como esten ordenados.
			*/


			} catch (IOException e)
			{
			e.printStackTrace();
			}
	}
	
	/**Detecta el n&uacute;mero de v&eacute;rtices en un archivo Matriz de Adyacencias 
	 * basado en una muestra de una l&iacute;nea tomada del archivo.
	 * 
	 * @param  linea  La l&iacute;nea del archivo que se desea cargar.
	 * @return        el n&uacute;mero de v&eacute;rtices detectado
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
/**	private static int detectarVertices(String linea)
			throws IllegalArgumentException
	{
		throw new UnsupportedOperationException("Este metodo aun no ha sido "
				+"implementado");
	}
	
	/**Carga un grafo desde un archivo y lo almacena en un
	 * {@link TraductorGrafo}.
	 * 
	 * @param  nombreArchivo nombre o ruta del archivo que se desea cargar.
	 * @return               Un <code>TraductorGrafo</code> que contiene el 
	 *                       grafo representado en el archivo dado.
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	static TraductorGrafo cargarGrafo(String nombreArchivo)
			throws IOException
	{
		TraductorGrafo salida;
		
		BufferedReader Lector = new BufferedReader(new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		
		if(esLista(linea)){
			
			salida = new TraductorDesdeLista();
			do{
				cargarLista(linea, (TraductorDesdeLista)salida);
			//>>>modifique esta linea ya que la linea permanecia como la primera siempre<<<	
			}while((linea = Lector.readLine()) != null);
		} else{
			String[] lineaSplit = linea.split(" ");
			int numberOfVertices = lineaSplit.length;
			numberOfVertices -=3;				// no contamos los 3 espacios en blanco
			//Creamos la matriz y almacenamos los vertices en la primera fila
			// dejando la primera casilla en 0.
			salida = new TraductorDesdeMatriz(numberOfVertices);
			cargarMatriz(nombreArchivo,(TraductorDesdeMatriz)salida);
			return salida;
		}
		
		return salida;
	}
	
	/**Carga el grafo representado en el archivo dado y lo muestra en su
	 * representaci&oacute;n alternativa.
	 * 
	 * @param args Arreglo que contiene el nombre el archivo como &uacute;nico elemento
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	public static void main(String[] args)
		throws IOException, IllegalArgumentException
	{
		if(args.length < 1){
			System.err.println("Uso: java Cliente <nombreArchivo>");
			return;
		}
		
		TraductorGrafo g = cargarGrafo(args[0]);
		
		System.out.println(g.imprimirGrafoTraducido());

		g.esDigrafo();
	}
}