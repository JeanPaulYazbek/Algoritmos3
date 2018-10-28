import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

public class BFS{
 
  protected int[] predecesores; //lista de vertices con su predecesor, el indice de la lista representa el vertice
  protected int[] orientacion; //lista de orden, el indice de la lista representa el vertice
  protected int t;  //variable global que usaremos para contar orden

  /**
   * funcion que hace un recorrido bfs desde un vertice
   * @param i vertice del que se comienza el bfs
   * @param visited lista para saber quien ha sido alcanzado
   * @param cola cola que nos ayuda recorrer el grafo por capas
   * @param camino camino formado en orden de descubrimiento en caso de necesitarlo
   * @param profundidad arreglo que nos indica la profundidad de cada vertice en el recorrido
   * @param grafo matriz de adyacensias que representa el grafo
   * @param arbol true si se quiere imprimir la forma de arbol del recorrido, false si no
   */
  public void bfs(int i, boolean[] visited, LinkedList<Integer> cola, ArrayList<Integer> camino, int[] profundidad, int[][] grafo, boolean arbol)
  {
    orientacion[i] = t; //marcamos la orientacion del vertice i
    t = t + 1;
    //iteracion
    while (cola.size() != 0)
    {
      i = cola.poll();
      camino.add(i);
      String espacio = "";
      
      for(int k = 0; k<profundidad[i]+1; k++)
      {
        espacio +="  "; // Incrementamos el espacio por la profundidad
      }
      for(int j = 0; j<grafo.length; j++)
      {
        if (grafo[i][j]==1 && visited[j] && arbol)
        {
          System.out.println(espacio + i + "-" + j + " (arco de subida)");
        }
        if (grafo[i][j]==1 && !visited[j])
        {
          visited[j] = true;
          cola.add(j);
          predecesores[j] = i; //marcomos el predecesor del vertice j
          profundidad[j]=profundidad[i]+1;
          orientacion[j] = t; //marcamos la orientacion del vertice i
          t = t + 1;
          if(arbol)
          {
            System.out.println(espacio + i + "-" + j + " (arco de camino)");
          }
        }
      }
    }
  }

  /**
   * funcion que llama al bfs adecuado
   * @param i vertice desde el que se quiere empezar el recorrido
   * @param grafo matriz de adyacencias grafo
   * @param arbol true si se quiere imprimir arbol false si no
   * @param cota -1 si no se quiere truncar, otro numero mayor o igual a 0 si si
   */
  public void bfsCliente(int i, int[][] grafo, boolean arbol, int cota)
  {
    t = 0;//el orden inicia en 0

    boolean[] visited = new boolean[grafo.length];//creamos la lista de visitados del tamano del grafo

    predecesores = new int[grafo.length];//creamos la lista de predecesores del tamano adecuado
    Arrays.fill(predecesores, -1);//llenala de -1
    predecesores[i] = i;//el primer vertice es su propio predecesor

    orientacion = new int[grafo.length];//creamos la lista de orientacion del tamano adecuado
    Arrays.fill(orientacion, -1);//llenamos de -1

    //parametros que usaremos para BFS
    LinkedList<Integer> cola = new LinkedList<Integer>();
    ArrayList<Integer> camino = new ArrayList<Integer>();
    cola.add(i); //Encolamos el nodo al camino
    int[] profundidad = new int[grafo.length];
    visited[i] = true; // Marcamos el nodo como visitado

    if(arbol)
    {
      System.out.println("Arbol:");
    }
    if (cota == -1)
    {//si no truncamos llamamos a bfs
      this.bfs(i, visited, cola, camino, profundidad, grafo, arbol);
    }
    else
    {//si truncamos llamamos a bfsTrunacado
      this.bfsTruncado(i, visited, cola, camino, profundidad, grafo, arbol, cota);
    }
    System.out.println("");
  }

  /**
   * funcion que imprime los predecesores en el bfs
   */
  public void predecesores()
  {
    System.out.println("Predecesores:");
    for(int j = 0; j< predecesores.length; j++)
    {
      System.out.println(j + ": " + predecesores[j]);
    }
    System.out.println("");
  }

  /**
   * funcion que imprime los no conexos del inidice inicial
   */
  public void noConexos()
  {
    ArrayList<Integer> nocon = new ArrayList<Integer>();
    for(int j = 0; j< predecesores.length ; j++)
    {
      if (predecesores[j] == -1)
      {
        nocon.add(j);
      }
    }

    for(int i = 0; i<nocon.size() -1; i++)
    {
      System.out.print(nocon.get(i) + ",");
    }
    if(nocon.size() != 0)
    {
      System.out.print(nocon.get(nocon.size() -1));
      System.out.println("");
    }
    else if(nocon.size() == 0)
    {
      System.out.println("Todas las paginas son parte de la internet visible.");
    }
    System.out.println("");
  }

  /**
   * funcion que imprime la orientacion del recorrido
   */
  public void orientacion()
  {
    System.out.println("Ordinales:");
    for(int j = 0; j< orientacion.length; j++)
    {
      System.out.println( j + ": " + orientacion[j]);
    }
    System.out.println(" ");
  }

  /**
   * funcion que hace un recorrido bfs desde un vertice
   * @param i vertice del que se comienza el bfs
   * @param visited lista para saber quien ha sido alcanzado
   * @param cola cola que nos ayuda recorrer el grafo por capas
   * @param camino camino formado en orden de descubrimiento en caso de necesitarlo
   * @param profundidad arreglo que nos indica la profundidad de cada vertice en el recorrido
   * @param grafo matriz de adyacensias que representa el grafo
   * @param arbol true si se quiere imprimir la forma de arbol del recorrido, false si no
   * @param cota en cual capa se quiere truncar
   */
  public void bfsTruncado(int i, boolean[] visited, LinkedList<Integer> cola, ArrayList<Integer> camino, int[] profundidad, int[][] grafo, boolean arbol, int cota)
  {
    orientacion[i] = t; //marcamos la orientacion del vertice i
    t = t + 1;
    //iteracion
    while (cola.size() != 0) 
    {
      i = cola.poll();
      camino.add(i);
      String espacio = "";
      for(int k = 0; k<profundidad[i]+1; k++)
      {
        espacio +="  "; // Incrementamos el espacio por la profundidad
      }
      for(int j = 0; j<grafo.length; j++)
      {
        if (grafo[i][j]==1 && visited[j] && arbol)
        {
          if (profundidad[i]+1 > cota)
          {
            return;
          }
          System.out.println(espacio + i + "-" + j + " (arco de subida)");
        }
        if (grafo[i][j]==1 && !visited[j])
        {
          visited[j] = true;
          cola.add(j);
          profundidad[j]=profundidad[i]+1;
          if (profundidad[j] > cota)
          {
            return;
          }
          predecesores[j] = i; //marcomos el predecesor del vertice j
          orientacion[j] = t; //marcamos la orientacion del vertice i
          t = t + 1;
          if(arbol)
          {
            System.out.println(espacio + i + "-" + j + " (arco de camino)");
          }
        }
      }
    }
  }
}