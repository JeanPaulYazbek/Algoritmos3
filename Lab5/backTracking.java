import java.util.ArrayList;
import java.util.Arrays;

public class backTracking
{
	protected int[][] camino; //camino mas corto encontrado hasta ahora
	protected boolean noCamino=true; //aun no se ha encontrado un camino
	protected int[] estadoIndeseable; //estado [0,...,0]
	protected int [] estadoDeExito; //condicion de parada

	/**
	* @param puertas matriz de adyacencias que representa las aristas pasillos
	* @param luces matriz de adyacencias que representa los arcos interruptores
	* @param estado arreglo de n+1 elementos donde el ultimo representa la 
	* posicion actual y el resto representan si la luz de dicha habitacion
	* esta encendida (1) o apagada (0)
	* @param estados estados por los que se ha pasado durante el recorrido
	*/
	public void recorridoADormitorio(int[][]luces,int[][]puertas,int[] estado,
		int[][] estados)
	{
		estados[estados.length-1]=estado.clone();
		exito(estado,estados);
		int[][] accionesPosibles = posiblesAcciones(estados,estado,luces,puertas);
		int i;
		int j;

		for (i = 0; i < accionesPosibles.length; i++)
		{
			int[][]nuevosEstados= new int[estados.length+1][estados[0].length+1];

			for (j = 0; j < estados.length; j++)
			{
				nuevosEstados[j]=estados[j].clone();
			}			
			recorridoADormitorio(luces,puertas,accionesPosibles[i],nuevosEstados);
		}
		return;
	}

	/**
	* funcion que calcula las acciones validas posibles desde el estado actual
	* @param estados matriz de estados del recorrido
	* @param estado estado actual
	* @param puertas matriz de adyacencias que representa las aristas pasillos
	* @param luces matriz de adyacencias que representa los arcos interruptores
	*/
	public int[][] posiblesAcciones(int[][] estados,int[] estado,int[][]luces,int[][]puertas)
	{
		int n=luces.length;
		int[][] estadosPosibles = new int[3*n][n+1];
		int[] siApago;
		int[] siEnciendo;
		int[] siMeMuevo;

		//almacenamos todas posibles acciones
		int posicionActual=estado[n];
		int k;
		int j=0;

		for (k = 0; k < n; k++)
		{

			siApago = estado.clone();
			siEnciendo = estado.clone();
			siMeMuevo = estado.clone();

			if (luces[posicionActual][k]==1)
			{
				if (estado[k]==0)
				{
					siEnciendo[k]=1;
					if (!estadoRepetido(estados,siEnciendo))
					{
						estadosPosibles[j]=siEnciendo.clone();
						j+=1;						
					}
				}
				if ((estado[k]==1)&&((k!=posicionActual)))
				{
					siApago[k]=0;
					if (!estadoRepetido(estados,siApago))
					{
						estadosPosibles[j]=siApago.clone();
						j+=1;
					}
				}
			}
			if (puertas[posicionActual][k]==1)
			{
				if ((estado[k]==1)&&((k!=posicionActual)))
				{
					siMeMuevo[n]=k;
					if (!estadoRepetido(estados,siMeMuevo))
					{
						estadosPosibles[j]=siMeMuevo.clone();
						j+=1;
					}
				}
			}
		}
		int[][] estadosPosiblesReales = new int[j][n+1];
		for (k = 0; k < j; k++)
		{
			estadosPosiblesReales[k] = estadosPosibles[k].clone();
		}
		return estadosPosiblesReales;
	}

	/**
	* funcion que verifica si el estado actual es el estado de exito
	* y almacena el dicho camino (sucesion de estados) si es el mas corto
	* @param estados matriz de estados del recorrido
	* @param estadoPosible estado candidato que queremos saber si ya existe en el recorrido
	*/
	public void exito(int[]estado,int[][] estados)
	{
		if (Arrays.equals(estado,estadoDeExito))
		{
			if (noCamino) //Primera vez
			{
				camino=calcularCamino(estados);
				noCamino=false;
			}
			if(camino.length>=estados.length) //Demas veces
			{
				camino=calcularCamino(estados);
			}
		}
	}

	/**
	* funcion que verifica si un estado se encuentra en la matriz de estados
	* @param estados matriz de estados del recorrido
	* @param estadoPosible estado candidato que queremos saber si ya existe en el recorrido
	* @return un booleano, true si estaba repetido, false si no
	*/
	public boolean estadoRepetido(int[][] estados,int[] estadoPosible)
	{
		if (Arrays.equals(estadoPosible,estadoIndeseable))
		{
			return false;
		}
		else
		{
			//vemos si el estado posible ya se encuentra explorado
			int n = estados.length;

			for (int k = 0; k < n; k++)
			{
				if (Arrays.equals(estados[k],estadoPosible))
				{
					return true;
				}
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
			System.out.println("El problema no puede ser resuelto.");
			return;
		}

		estadoDeExito = new int[luces.length+1];//condicion de parada
		// todas las apagadas salvo la n y posicion igual n.
		// estado de exito
		int[][] estados = new int[1][1]; //estados por los que
		// se ha pasado durante el recorrido
		int[] estado = new int[luces.length+1]; //arreglo de n+1 elementos donde
		// el ultimo representa la posicion actual
		//y el resto representan si la luz de dicha habitacion esta encendida
		// (1) o apagada (0).
		estadoIndeseable = new int[luces.length+1];
		estado[0]=1;//al principio solo esta encendida la luz del cuarto 0 y se
		// encuentra en el cuarto 0
		estadoDeExito[luces.length]=luces.length-1;//al final se encuentra en el
		// cuarto n
		estadoDeExito[luces.length-1]=1;//solo se encuentra encendida la luz del
		// cuarto n
		recorridoADormitorio(luces,puertas,estado,estados);
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

	/**
	* funcion que tomando los estados del recorrido calcula las acciones ejecutadas
	* y las devuelve en una matriz
	* @param estados matriz de estados
	*/
	public int[][] calcularCamino(int[][]estados)
	{
		int n = estados.length;
		int[][] camino = new int[n-1][2];
		int[] accion = new int[2];
		int m = estados[0].length;
		int [] estadoA;
		int [] estadoB;
		for(int k = 0; k<n-1; k++)
		{
			estadoA=estados[k];
			estadoB=estados[k+1];

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