import java.util.LinkedList;
import java.util.ArrayList;

public class BFS{
  protected boolean finished = false;

  public void bfs(int i,ArrayList<Integer> caminoHamiltoniano, int[][] grafo) {
    //parametros que usaremos para BFS
    LinkedList<Integer> camino = new LinkedList<Integer>();
    boolean [] visited = new boolean[grafo[0].length];
    camino.add(i); //Encolamos el nodo al camino
    visited[i] = true; // Marcamos el nodo como visitado
    String espacio = "";

    //iteracion
    while (camino.size() != 0) 
    {
      i = camino.poll(); // Decolamos el nodo
      espacio +="  "; // Incrementamos el espacio
      caminoHamiltoniano.add(i); //Agregamos el nodo al camino Hamiltoniano

      for(int j = 0; j<grafo[0].length; j++)
      {
        //criterio de parada
        if(caminoHamiltoniano.size() == grafo[0].length)
        {
          System.out.print("Camino encontrado: ");
          int n = caminoHamiltoniano.size();
          for(int k = 0; k<n-1; k++)
          {
            System.out.print(caminoHamiltoniano.get(k)+"-");
          }
          System.out.println(caminoHamiltoniano.get(n-1));
          System.out.print("El camino Hamiltoniano tiene " + caminoHamiltoniano.size() +" vertices");
          finished = true;
          return;
        }
        if (grafo[i][j]==1 && visited[j])
        {
          System.out.println(espacio + i + "-" + j + " ya visitado");
        }
        if (grafo[i][j]==1 && !visited[j])
        {
          visited[j] = true;
          camino.add(j);
          System.out.println(espacio + i + "-" + j);
        }
      }
    }
  }
}