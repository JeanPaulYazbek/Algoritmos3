import java.util.ArrayList;

public class DFS{
  protected boolean finished = false;
  protected ArrayList<Integer> caminoHamiltoniano;
	public void dfs(int i,  boolean[] visited, String espacio, int contador, ArrayList<Integer> camino, int[][] grafo) {

		//criterio de parada
		if(contador == grafo[i].length ){
			System.out.print("Camino encontrado: ");
			int n = camino.size();
			for(int k = 0; k<n-1; k++){
				System.out.print(camino.get(k)+"-");
			}
			System.out.println(camino.get(n-1));
			System.out.print("El camino Hamiltoniano tiene " + contador +" vertices");
			finished = true;
			caminoHamiltoniano = camino;
			return;
		}

		//recursion
  	if(!visited[i]){        
      
      visited[i] = true; // Mark node as "visited"	 

 		 for (int j = 0; j < grafo[i].length; j++) {
 		 	if (finished){
 		 		return;
 		 	}
 		 	if (grafo[i][j]==1 && visited[j]){
 		 		System.out.println(espacio + i + "-" + j + " ya visitado");
 		 	}
     	if (grafo[i][j]==1 && !visited[j]) {   
     		System.out.println(espacio + i + "-" + j);
     		camino.add(j);
     		ArrayList<Integer> caminoExpandido = camino;
      	    dfs(j, visited, espacio + "  ", contador + 1, caminoExpandido, grafo); // Visit node
      }
      }
    	}
    }
}