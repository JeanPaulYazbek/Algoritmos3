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
    boolean esHamiltoniano = true;
    int[] profundidad = new int[grafo[0].length];

    //iteracion
    while (camino.size() != 0) 
    {
      i = camino.poll(); // Decolamos el nodo
      caminoHamiltoniano.add(i); //Agregamos el nodo al camino Hamiltoniano
      String espacio = "";
      for(int k = 0; k<profundidad[i]+1; k++)
      {
        espacio +="  "; // Incrementamos el espacio por la profundidad
      }

      for(int j = 0; j<grafo[0].length; j++)
      {
        //criterio de parada
        if(caminoHamiltoniano.size() == grafo[0].length)
        {
          System.out.print("Arbol encontrado: ");
          int n = caminoHamiltoniano.size();
          for(int k = 0; k<n-1; k++)
          {
            System.out.print(caminoHamiltoniano.get(k)+"-");
            if (0==grafo[caminoHamiltoniano.get(k)][caminoHamiltoniano.get(k+1)])
            {
              esHamiltoniano = false;
            }
          }
          System.out.println(caminoHamiltoniano.get(n-1));
          if (!esHamiltoniano)
          {
            System.out.print("No se identifico un camino Hamiltoniano en el Arbol");
            return;
          }
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
          profundidad[j]=profundidad[i]+1;
          System.out.println(espacio + i + "-" + j);
        }
      }
    }
  }
}