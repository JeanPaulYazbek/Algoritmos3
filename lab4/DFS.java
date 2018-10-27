import java.util.ArrayList;
import java.util.Arrays;

public class DFS{
 
  protected int[] predecesores; //lista de vertices con su predecesor, el indice de la lista representa el vertice
  protected int[] orientacion; //lista de orden, el indice de la lista representa el vertice
  protected int t;	//variable global que usaremos para contar orden

  /**
   * funcion que hace un recorrido dfs desde un vertice
   * @param i vertice del que se comienza el dfs
   * @param visited	lista para saber quien ha sido alcanzado
   * @param espacio	espacion que aumenta con cada recursion para implementar el print
   * @param contador contador que nos indica la profundidad de cada rama de la recursion
   * @param camino camino formado en orden de descubrimiento en caso de necesitarlo
   * @param grafo matriz de adyacensias que representa el grafo
   * @param arbol true si se quiere imprimir la forma de arbol del recorrido, false si no
   */
  public void dfs(int i,  boolean[] visited, String espacio, int contador, ArrayList<Integer> camino, int[][] grafo, boolean arbol) {

	
	//recursion
  	if(!visited[i]){        
      
      visited[i] = true; // Mark node as "visited"	 
      orientacion[i] = t; //en este momento sabemos el orden de descubrimiento
      t = t + 1; //aumentamos el orden en 1 para el proximo en ser descubierto

 		 for (int j = 0; j < grafo[i].length; j++) {

 		 	if (grafo[i][j]==1 && visited[j]){
 		 		if(arbol){
 		 			System.out.println(espacio + i + "-" + j + " (arco de subida)");
 		 		}
 		 	}
     		if (grafo[i][j]==1 && !visited[j]) {
     			predecesores[j] = i;   //marcomo el predecesor en este momento
     			if(arbol){
     				System.out.println(espacio + i + "-" + j + " (arco de camino)");
     			}
     			camino.add(j);
     			ArrayList<Integer> caminoExpandido = camino;
      	  	    dfs(j, visited, espacio + "  ", contador + 1, caminoExpandido, grafo, arbol); // Visit node
     	   }
        }
    }
  }

  /**
   * funcion que llama al dfs adecuado
   * @param i vertice desde el que se quiere empezar el recorrido
   * @param grafo matriz de adyacencias grafo
   * @param arbol true si se quiere imprimir arbol false si no
   * @param cota -1 si no se quiere truncar, otro numero mayor o igual a 0 si si
   */
  public void dfsCliente(int i, int[][] grafo, boolean arbol, int cota){

  		t = 0;//el orden inicia en 0

    	boolean[] visited = new boolean[grafo.length];//creamos la lista de visitados del tamano del grafo

  		predecesores = new int[grafo.length];//creamos la lista de predecesores del tamano adecuado
  		Arrays.fill(predecesores, -1);//llenala de -1
  		predecesores[i] = i;//el primer vertice es su propio predecesor

  		orientacion = new int[grafo.length];//creamos la lista de orientacion del tamano adecuado
  		Arrays.fill(orientacion, -1);//llenamos de -1


  		ArrayList<Integer> camino = new ArrayList<Integer>();
  		camino.add(i);

  		if(arbol){
  			System.out.println("Arbol:");
  		}
  		if (cota == -1){//si no truncamos llamamos a dfs
  			this.dfs(i, visited, "  ", 0, camino, grafo, arbol);
  		}else{//si truncamos llamamos a dfsTrunacado
  			this.dfsTruncado(i, visited, " ", 0, camino, grafo, arbol, cota);
  		}

  		System.out.println("");

  }

  /**
   * funcion que imprime los predecesores en el dfs
   */
  public void predecesores(){

  	System.out.println("Predecesores:");
  	for(int j = 0; j< predecesores.length; j++){
  		System.out.println(j + ": " + predecesores[j]);
  	}
  	System.out.println("");
  }

  /**
   * funcion que imprime los no conexos del inidice inicial
   */
  public void noConexos(){

  	ArrayList<Integer> nocon = new ArrayList<Integer>();
  	for(int j = 0; j< predecesores.length ; j++){
  		if (predecesores[j] == -1){
  			nocon.add(j);
  		}
  	}

  	for(int i = 0; i<nocon.size() -1; i++){
  		System.out.print(nocon.get(i) + ",");
  	}
  	if(nocon.size() != 0){
  		System.out.print(nocon.get(nocon.size() -1));
  		System.out.println("");
  	}else if(nocon.size() == 0){
  		System.out.println("Todas las paginas son parte de la internet visible.");
  	}

  	System.out.println("");

  }

  /**
   * funcion que imprime la orientacion del recorrido
   */
  public void orientacion(){

  	System.out.println("Ordinales:");
  	for(int j = 0; j< orientacion.length; j++){
  		System.out.println( j + ": " + orientacion[j]);
  	}
  	System.out.println(" ");
  }

  	/**
	   * dfs que trunca los recorridos
	   * @param i vertice inicial
	   * @param visited lista de visitados
	   * @param espacio espacio para los prints
	   * @param contador contador para saber que tan profundo ha sido la recursion
	   * @param camino	camino recorrido en caso de ser necesario
	   * @param grafo matriz de adyacencias
	   * @param arbol true si se quiere imprimir el arbol
	   * @param cota cuanto se quiere truncar
	   */
   public void dfsTruncado(int i,  boolean[] visited, String espacio, int contador, ArrayList<Integer> camino, int[][] grafo, boolean arbol, int cota) {

	
	//recursion
  	if(!visited[i]){        
      
      visited[i] = true; // Mark node as "visited"	 
      orientacion[i] = t; //marcamos la orientacion del vertice i
      t = t + 1;

 		 for (int j = 0; j < grafo[i].length; j++) {

 		 	if (grafo[i][j]==1 && visited[j] ){
 		 		if(arbol){
 		 			System.out.println(espacio + i + "-" + j + " (arco de subida)");
 		 		}
 		 	}
     		if (grafo[i][j]==1 && !visited[j] && contador != cota) {
     			predecesores[j] = i; //marcomos el predecesor del vertice j
     			if(arbol){
     				System.out.println(espacio + i + "-" + j + " (arco de camino)");
     			}
     			camino.add(j);
     			ArrayList<Integer> caminoExpandido = camino;
      	  	    dfsTruncado(j, visited, espacio + "  ", contador + 1, caminoExpandido, grafo, arbol, cota); // Visit node
     	   }
        }
    }
  }
}