import java.util.ArrayList;
import java.util.Arrays;

public class DFS{
  protected boolean finished = false;
  protected ArrayList<Integer> caminoHamiltoniano;
  protected int[] predecesores;
  protected int[] orientacion;
  protected int t;


  public void dfs(int i,  boolean[] visited, String espacio, int contador, ArrayList<Integer> camino, int[][] grafo, boolean arbol) {

	
	//recursion
  	if(!visited[i]){        
      
      visited[i] = true; // Mark node as "visited"	 
      orientacion[i] = t;
      t = t + 1;

 		 for (int j = 0; j < grafo[i].length; j++) {

 		 	if (grafo[i][j]==1 && visited[j]){
 		 		if(arbol){
 		 			System.out.println(espacio + i + "-" + j + " (arco de subida)");
 		 		}
 		 	}
     		if (grafo[i][j]==1 && !visited[j]) {
     			predecesores[j] = i;   
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

  public void dfsCliente(int i, int[][] grafo, boolean arbol, int cota){

  		t = 0;

    	boolean[] visited = new boolean[grafo.length];

  		predecesores = new int[grafo.length];
  		Arrays.fill(predecesores, -1);
  		predecesores[i] = i;

  		orientacion = new int[grafo.length];
  		Arrays.fill(orientacion, -1);


  		ArrayList<Integer> camino = new ArrayList<Integer>();
  		camino.add(i);

  		if(arbol){
  			System.out.println("Arbol:");
  		}
  		if (cota == -1){
  			this.dfs(i, visited, "  ", 0, camino, grafo, arbol);
  		}else{
  			this.dfsTruncado(i, visited, " ", 0, camino, grafo, arbol, cota);
  		}

  		System.out.println("");

  }

  public void predecesores(){

  	System.out.println("Predecesores:");
  	for(int j = 0; j< predecesores.length; j++){
  		System.out.println(j + ": " + predecesores[j]);
  	}
  	System.out.println("");
  }

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

  public void orientacion(){

  	System.out.println("Ordinales:");
  	for(int j = 0; j< orientacion.length; j++){
  		System.out.println( j + ": " + orientacion[j]);
  	}
  	System.out.println(" ");
  }

   public void dfsTruncado(int i,  boolean[] visited, String espacio, int contador, ArrayList<Integer> camino, int[][] grafo, boolean arbol, int cota) {

	
	//recursion
  	if(!visited[i]){        
      
      visited[i] = true; // Mark node as "visited"	 
      orientacion[i] = t;
      t = t + 1;

 		 for (int j = 0; j < grafo[i].length; j++) {

 		 	if (grafo[i][j]==1 && visited[j] ){
 		 		if(arbol){
 		 			System.out.println(espacio + i + "-" + j + " (arco de subida)");
 		 		}
 		 	}
     		if (grafo[i][j]==1 && !visited[j] && contador != cota) {
     			predecesores[j] = i;   
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