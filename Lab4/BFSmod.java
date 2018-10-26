import java.util.LinkedList;
import java.util.ArrayList;

public class BFS{
  protected boolean finished = false;

  public void bfs(int i, int[][] grafo, boolean trun, int altura, boolean arb, boolean ord, boolean pred) {
    //parametros que usaremos para BFS
    LinkedList<Integer> cola = new LinkedList<Integer>();
    ArrayList<Integer> camino = new ArrayList<Integer>();
    ArrayList<Integer> inalcanzables = new ArrayList<Integer>();
    boolean [] visited = new boolean[grafo[0].length];
    cola.add(i); //Encolamos el nodo
    visited[i] = true; // Marcamos el nodo como visitado
    boolean encontrado = true;
    int[] profundidad = new int[grafo[0].length];

    //iteracion
    while (cola.size() != 0) 
    {
      i = cola.poll(); // Decolamos el nodo
      camino.add(i); //Agregamos el nodo al cola Hamiltoniano
      String espacio = "";
      for(int k = 0; k<profundidad[i]+1; k++)
      {
        espacio +="  "; // Incrementamos el espacio por la profundidad
      }

      for(int j = 0; j<grafo[0].length; j++)
      {
        //criterio de parada
        if(camino.size() == grafo[0].length)
        {
          System.out.print("Arbol encontrado: ");
          int n = camino.size();
          for(int k = 0; k<n-1; k++)
          {
            System.out.print(camino.get(k)+"-");
            if (0==grafo[camino.get(k)][camino.get(k+1)])
            {
              esHamiltoniano = false;
            }
          }
          System.out.println(camino.get(n-1));
          if (!esHamiltoniano)
          {
            System.out.print("No se identifico un cola Hamiltoniano en el Arbol");
            return;
          }
          System.out.print("El cola Hamiltoniano tiene " + camino.size() +" vertices");
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
          cola.add(j);
          profundidad[j]=profundidad[i]+1;
          System.out.println(espacio + i + "-" + j);
        }
      }
    }
    for(int j = 0; j<grafo[0].length; j++)
    {
      if(!checkifexist(j,camino))
      {
        inalcanzables.add(j);
      }
    }
    for(int j = 0; j<inalcanzables.size()-2; j++)
    {
      System.out.print(inalcanzables.get(j)+",");
    }
    System.out.print(inalcanzables.get(inalcanzables.size()-1));
  }

  public boolean checkifexist(int i, ArrayList<Integer> camino)
  {
    for(int j = 0; j<camino.size(); j++)
    {
      if (i==camino.get(j))
        return true;
    }
    return false;
  }
}