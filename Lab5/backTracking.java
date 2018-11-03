import java.util.ArrayList;
import java.util.Arrays;

public class backTracking
{
	protected ArrayList<int[]> estados; //estados por los que se ha pasado durante el recorrido
	protected ArrayList<int[]> acciones; //acciones que se tomaron durante el recorrido
	protected int[] accion; //tupla en la que el primer elemento representa la accion (0 apagar,
	//1 encender, 2 mover) y el segundo el objeto de dicha acción
	protected int[] estado; //arreglo de n+1 elementos donde el ultimo representa la posicion actual
	//y el resto representan si la luz de dicha habitacion esta encendida (1) o apagada (0).

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
	public void recorridoADormitorio(int[][] luces,int[][] puertas)
	{
		//recursión
		
	}

	/**
	* funcion que llama a recorridoPosible, de ser posible inicializa los
	* valores y llama a recorridoADormitorio, para buscar el camino.
	* @param puertas matriz de adyacencias que representa las aristas pasillos
	* @param luces matriz de adyacencias que representa los arcos interruptores
	*/
	public void empezarCamino(int[][] luces,int[][] puertas)
	{
		if (!recorridoPosible(luces,puertas))
		{
			return
		}
		estado = new int[luces.length+1];
		estado[0]=1;
		estado[luces.length]=1;
		estados.add(estado);
		recorridoADormitorio(luces,puertas);

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
	* funcion que verifica si hay: un pasillo hacia el cuarto, un interruptor
	* que controle la luz del cuarto y un interruptor en el cuarto. Dado que
	* el incumplimiento de cualquiera de estas condiciones hace que no sea
	* posible encontrar un recorrido
	* @param puertas matriz de adyacencias que representa las aristas pasillos
	* @param luces matriz de adyacencias que representa los arcos interruptores
	*/
	public boolean recorridoPosible(int[][] luces,int[][] puertas)
	{
  		int n = grafo.length;
  		boolean hayPasillo=false;
  		boolean hayInterruptorHacia=false;
  		boolean hayInterruptorDesde=false;
		for (int k = 0; k < n; k++)
		{
			if (luces[n-1][k]==1)
			{
				hayInterruptorHacia=true;
			}
			if (luces[k][n-1]==1)
			{
				hayInterruptorDesde=true;
			}
			if (puertas[k][n-1]==1)
			{
				hayPasillo=true;
			}
		}
		return hayPasillo&&(hayInterruptorDesde&&hayInterruptorHacia);
	}

	/**
	* funcion que muestra el recorrido por accion
	*/
	public void mostrarCamino()
	{
		if (accion.size()==0)
		{
			System.out.println("El problema no puede ser resuelto.");
			return
		}
		numeroDeAcciones=acciones.size();
		int[] accion;
		int[] temp; 
		System.out.println("El problema puede ser resuelto en "+numeroDeAcciones+" pasos");
		for(int k = 0; k<numeroDeAcciones; k++)
		{
			accion=acciones.get(k);
			temp=accion[1]+1;
			if (accion[0]==0)
			{
				System.out.println("- Apaga la luz en la habitacion "+temp+".");
			}
			if (accion[0]==1)
			{
				System.out.println("- Enciende la luz en la habitacion "+temp+".");
			}
			if (accion[0]==2)
			{
				System.out.println("- Muevete a la habitacion "+temp+".");
			}
		}
	}
}