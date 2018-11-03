import java.util.ArrayList;
import java.util.Arrays;

public class backTracking
{
	protected int[] estadoDeExito; //condicion de parada todas las apagadas salvo la n y posicion igual n.
	protected ArrayList<int[]> accionesDeExito; //acciones que se tomaron durante el recorrido mas corto.

	/**
	* @param puertas matriz de adyacencias que representa las aristas pasillos
	* @param luces matriz de adyacencias que representa los arcos interruptores
	* @param estado arreglo de n+1 elementos donde el ultimo representa la posicion actual
	* y el resto representan si la luz de dicha habitacion esta encendida (1) o apagada (0)
	* @param estados estados por los que se ha pasado durante el recorrido
	* @param acciones acciones que se han realizado durante el recorrido
	*/
	public void recorridoADormitorio(int[][] luces,int[][] puertas,int [] estado,ArrayList<int[]> estados,ArrayList<int[]> acciones)
	{
		//almacenamos todas posibles acciones
		n=luces.length;
		posicionActual=estado[n+1];
		int[] estadoPosible;

		for (int k = 0; k < n; k++)
		{
			estadoPosible = estado;
			if (luces[posicionActual][k]==1)
			{
				if (estado[k]==0)
				{
					estadoPosible[k]=1;
					if (!estadoRepetido(estados,estadoPosible))
					{
						acciones.add([1,k]);//puedes encender la habitacion k
						if (exito(acciones,estadoPosible))
						{
							return;
						}
						estados.add(estadoPosible);
						recorridoADormitorio(luces,puertas,estadoPosible,estados,acciones);
					}
				}
				else if (estado[k]==1)&&(!k==posicionActual)
				{
					estadoPosible[k]=0;
					if (!estadoRepetido(estados,estadoPosible))
					{
						acciones.add([0,k]);//puedes apagar la habitacion k
						if (exito(acciones,estadoPosible))
						{
							return;
						}
						estados.add(estadoPosible);
						recorridoADormitorio(luces,puertas,estadoPosible,estados,acciones);
					}
				}
			}
			estadoPosible = estado;
			if (puertas[posicionActual][k]==1)
			{
				if (estado[k]==1)&&(!k==posicionActual)
				{
					estadoPosible[n]=k;
					if (!estadoRepetido(estados,estadoPosible))
					{
						acciones.add([2,k]);//puedes moverte a la habitacion k
						if (exito(acciones,estadoPosible))
						{
							return;
						}
						estados.add(estadoPosible);
						recorridoADormitorio(luces,puertas,estadoPosible,estados,acciones);
					}
				}
			}
		}
	}

	public boolean Exito(ArrayList<int[]> acciones,int[] estado)
	{
		//condicion de exito
		if (estado==estadoDeExito)
		{
			if (accionesDeExito.size()==0)
			{
				accionesDeExito=acciones;
				return true;
			}
			else if(acciones.size()>=accionesDeExito.size())
			{
				return false;
			}
			else if(accionesDeExito.size()>acciones.size())
			{
				accionesDeExito=acciones;
				return true;
			}
		}
	}

	public boolean estadoRepetido(ArrayList<int[]> estados,int[] estadoPosible)
	{
		//vemos si el estado posible ya se encuentra explorado
		int n = estados.size();
		for (int k = 0; k < n; k++)
		{
			if (estados.get(k)==estadoPosible)
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
	public void empezarCamino(int[][] luces,int[][] puertas)
	{
		if (!recorridoPosible(luces,puertas))
		{
			return
		}
		ArrayList<int[]> estados; //estados por los que se ha pasado durante el recorrido
		ArrayList<int[]> acciones; //acciones que se tomaron durante el recorrido
		int[] accion; //tupla en la que el primer elemento representa la accion (0 apagar,
		//1 encender, 2 mover) y el segundo el objeto de dicha acción
		int[] estado; //arreglo de n+1 elementos donde el ultimo representa la posicion actual
		//y el resto representan si la luz de dicha habitacion esta encendida (1) o apagada (0).

		estado = new int[luces.length+1];
		estadoDeExito = estado;
		estado[0]=1;//al principio solo esta encendida la luz del cuarto 0 y se encuentra en el cuarto 0
		estados.add(estado);
		estadoDeExito[luces.length]=luces.length-1;//al final se encuentra en el cuarto n
		estadoDeExito[luces.length-1]=1;//solo se encuentra encendida la luz del cuarto n
		recorridoADormitorio(luces,puertas,estado,accion,estados,acciones);
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
		numeroDeAcciones=accionesDeExito.size();
		int[] _accion;
		int[] temp; 
		System.out.println("El problema puede ser resuelto en "+numeroDeAcciones+" pasos");
		for(int k = 0; k<numeroDeAcciones; k++)
		{
			_accion=accionesDeExito.get(k);
			temp=_accion[1]+1;
			if (_accion[0]==0)
			{
				System.out.println("- Apaga la luz en la habitacion "+temp+".");
			}
			if (_accion[0]==1)
			{
				System.out.println("- Enciende la luz en la habitacion "+temp+".");
			}
			if (_accion[0]==2)
			{
				System.out.println("- Muevete a la habitacion "+temp+".");
			}
		}
	}
}