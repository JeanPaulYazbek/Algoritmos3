import java.util.ArrayList;
import java.util.Arrays;

public class backTracking
{
	//estados por los que se paso durante el recorrido mas corto.	
	protected int[][] camino;
	protected boolean noCamino=true;

	/**
	* @param puertas matriz de adyacencias que representa las aristas pasillos
	* @param luces matriz de adyacencias que representa los arcos interruptores
	* @param estado arreglo de n+1 elementos donde el ultimo representa la 
	* posicion actual y el resto representan si la luz de dicha habitacion
	* esta encendida (1) o apagada (0)
	* @param estados estados por los que se ha pasado durante el recorrido
	*/
	public void recorridoADormitorio(int[][]luces,int[][]puertas,int[] estado,
		ArrayList<int[]>estados,int profundidad,int[]estadoDeExito)
	{
		//almacenamos todas posibles acciones
		int n=luces.length;
		int posicionActual=estado[n];
		int[] siApago = new int[n+1];
		int[] siEnciendo = new int[n+1];
		int[] siMeMuevo = new int[n+1];

		//estadoPosible = estado;
		for (int k = 0; k < n+1; k++)
		{
			siApago[k]=estado[k];
		}

		//estadoPosible = estado;
		for (int k = 0; k < n+1; k++)
		{
			siEnciendo[k]=estado[k];
		}

		//estadoPosible = estado;
		for (int k = 0; k < n+1; k++)
		{
			siMeMuevo[k]=estado[k];
		}

		estados.add(estado);
		exito(estado,estadoDeExito,estados);

		for (int k = 0; k < n; k++)
		{
			if (luces[posicionActual][k]==1)
			{
				if (estado[k]==0)
				{
					siEnciendo[k]=1;
					if (!estadoRepetido(estados,siEnciendo))
					{
						recorridoADormitorio(luces,puertas,siEnciendo,estadosPosibles,
							profundidad+1,estadoDeExito);
					}
				}
				if ((estado[k]==1)&&((k!=posicionActual)))
				{
					siApago[k]=0;	
					if (!estadoRepetido(estados,siApago))
					{
						recorridoADormitorio(luces,puertas,estadoPosible,estadosPosibles,
							profundidad+1,estadoDeExito);
					}
				}
			}
			if (puertas[posicionActual][k]==1)
			{
				if ((estado[k]==1)&&((k!=posicionActual)))
				{
					siMeMuevo[n]=k;	
					if (!estadoRepetido(estados,estadoPosible))
					{
						recorridoADormitorio(luces,puertas,siMeMuevo,estadosPosibles,
							profundidad+1,estadoDeExito);
					}
				}
			}
		}
	}

	public void mostrarEstados(ArrayList<int[]> estados)
	{
		for (int k = 0; k < estados.size(); k++)
		{
			System.out.print(Arrays.toString(estados.get(k)));
		}
		System.out.println("");
	}

	public void exito(int[]estado,int[]estadoDeExito,
		ArrayList<int[]> estados)
	{
		if (Arrays.equals(estado,estadoDeExito))
		{
			int k;
			if (noCamino) //Primera vez
			{
				mostrarEstados(estados);
				camino=calcularCamino(estados);
				noCamino=false;
			}
			if(camino.length>=estados.size()) //Demas veces
			{
				mostrarEstados(estados);
				camino=calcularCamino(estados);
			}
		}
	}

	public boolean estadoRepetido(ArrayList<int[]> estados,int[] estadoPosible)
	{
		//vemos si el estado posible ya se encuentra explorado
		int n = estados.size();
		for (int k = 0; k < n; k++)
		{
			if (Arrays.equals(estados.get(k),estadoPosible))
			{
				return true;
			}
		}
		return false;
	}

	/**
	* funcion que llama a recorridoPosible, de ser posible inicializa los
	* valores y llama a recorridoADormitorio, para buscar el camino.
	* @param puertas matriz de adyacencias que representa las aristas pasillos
	* @param luces matriz de adyacencias que representa los arcos interruptores
	*/
	public void pathMaker(int[][] luces,int[][] puertas)
	{
		if (!recorridoPosible(luces,puertas))
		{
			return;
		}

		int[] estadoDeExito = new int[luces.length+1];//condicion de parada
		// todas las apagadas salvo la n y posicion igual n.
		// estado de exito
		ArrayList<int[]> estados = new ArrayList<int[]>(); //estados por los que
		// se ha pasado durante el recorrido
		int[] estado = new int[luces.length+1]; //arreglo de n+1 elementos donde
		// el ultimo representa la posicion actual
		//y el resto representan si la luz de dicha habitacion esta encendida
		// (1) o apagada (0).

		estado[0]=1;//al principio solo esta encendida la luz del cuarto 0 y se
		// encuentra en el cuarto 0
		estadoDeExito[luces.length]=luces.length-1;//al final se encuentra en el
		// cuarto n
		estadoDeExito[luces.length-1]=1;//solo se encuentra encendida la luz del
		// cuarto n
		estados.add(estado);
		recorridoADormitorio(luces,puertas,estado,estados,0,estadoDeExito);
		mostrarCamino();
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
  		int n = luces.length;
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
		System.out.println("Puertas, NoDirigido");
		System.out.println(Arrays.deepToString(camino));
		if (noCamino)
		{
			System.out.println("El problema no puede ser resuelto.");
			return;
		}
		
		int numeroDeAcciones=camino.length;
		System.out.println("El problema puede ser resuelto en "+numeroDeAcciones+" pasos");
		for(int k = 0; k<numeroDeAcciones; k++)
		{
			if (camino[k][0]==0)
			{
				System.out.println("- Apaga la luz en la habitacion "+(camino[k][1]+1)+".");
			}
			else if (camino[k][0]==1)
			{
				System.out.println("- Enciende la luz en la habitacion "+(camino[k][1]+1)+".");
			}
			else if (camino[k][0]==2)
			{
				System.out.println("- Muevete a la habitacion "+(camino[k][1]+1)+".");
			}
		}
	}

	public int[][] calcularCamino(ArrayList<int[]>estados)
	{
		int n = estados.size();
		int[][] camino = new int[n-1][2];
		int[] accion = new int[2];
		int m = estados.get(0).length;
		int [] estadoA;
		int [] estadoB;
		for(int k = 0; k<n-1; k++)
		{
			estadoA=estados.get(k);
			estadoB=estados.get(k+1);

			if(!(estadoA[m-1]==estadoB[m-1]))
			{	
				camino[k][0]=2;
				camino[k][1]=estadoB[m-1];
			}
			else
			{
				int w=0;
				while (w<m-1)
				{
					if(!(estadoA[w]==estadoB[w]))
					{
						estadoA[w]=1;
						if(estadoA[w]==estadoB[w])
						{
							camino[k][0]=1;
							camino[k][1]=w;
							w=n;
						}
						else
						{
							camino[k][0]=0;
							camino[k][1]=w;
							w=n;
						}
					}
					w+=1;
				}
			}
		}
		return camino;
	}
}