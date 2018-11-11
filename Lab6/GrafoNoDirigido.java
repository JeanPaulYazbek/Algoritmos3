import java.util.Hashtable;
import java.util.ArrayList;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;

public class GrafoNoDirigido<V,L> implements Grafo<V,L>
{
	//grafo: representamos el grafo con un diccionario donde las claves son objetos
	// tipo vertices, y los valores listas de objetos tipo lados
	private Hashtable<Vertice<V>, ArrayList<Arista<L>>> grafo = new Hashtable<Vertice<V>,
	 ArrayList<Arista<L>>>();

	/**
	 * constructor por defecto
	 */
	GrafoNoDirigido()
	{
	}

	/**
	 * funcion que carga un grafo de un archivo de texto en el grafo
	 * @param archivo nombre del archivo desde el que queremos cargar el grafo
	 * @return un booleano, true si se creo exitosamente el grafo, false si no
	 * @throws IOException
	 */
	public Boolean cargarGrafo(String archivo)
		throws IOException
	{	
		BufferedReader Lector = new BufferedReader(new FileReader(archivo));

		String linea = Lector.readLine();//nos interesa el numero de vertices 

		String[] cuantosVertices = linea.split(" ");
		
		//Buscamos errores en la linea
		int numeroVertices;
		if(cuantosVertices.length != 1)//en caso de formato erroneo
		{
			return false;
		}
		try{
			numeroVertices = Integer.parseInt(cuantosVertices[0]);
		}catch(Exception e){
			return false;//si no es un entero formato erroneo
		}

		//AGREGAR LOS VERTICES
		for(int k = 0; k<numeroVertices; k++)
		{
			linea = Lector.readLine();//a partir de aqui son vertices
			String[] vertice = linea.split(" ");

			if(vertice.length != 2){//en caso de formato erroneo por cantidad de elementos
				return false;
			}

			//BUSCAMOS ERRORES DE FORMATO
			try{
				int posicionX = Integer.parseInt(vertice[0]);
				int posicionY = Integer.parseInt(vertice[1]);
			}catch(Exception e){
				return false;//si algunos de los datos es erroneo
			}
			agregarVertice(k,posicionX,posicionY);
		}

		linea = Lector.readLine();//nos interesa m(numero de lados)
		String[] cuantosLados = linea.split(" ");

		//Buscamos errores en la linea
		int numeroLados;
		if(cuantosLados.length != 1) //en caso de formato erroneo
		{
			return false;
		}
		try{
			numeroLados = Integer.parseInt(cuantosLados[0]);
		}catch(Exception e){
			return false;//si no es un entero formato erroneo
		}

		//AGREGAR LAS ARISTAS
		for(int k = 0; k<numeroLados; k++)
		{

			linea = Lector.readLine();//a partir de aqui son lados
			String[] vertice = linea.split(" ");

			if(vertice.length != 2)
			{//en caso de formato erroneo por cantidad de elementos
				return false;
			}

			//BUSCAMOS ERRORES DE FORMATO
			try{
				int extremo1= Integer.parseInt(vertice[0]);
				int extremo2= Integer.parseInt(vertice[1]);
			}catch(Exception e){
				return false;//si algunos de los datos es erroneo
			}
			agregarArista(k,extremo1,extremo2);
		}
		//se cargo el grafo
		return true;
	}

	/**
	 * funcion que agrega un vertice a partir de un objeto, si no se puede retorna false y si se puede retorna true
	 * @param v objeto de la clase vertice que se quiere agregar al grafo
	 * @return regresa true si se agrego exitosamente
	 */
	public Boolean agregarVertice(Vertice<V> v)
	{
		int clave = v.getId();//clave del vertice
		int id;				//id para comparar
		//revisamos si ya hay un vertice con ese id
		for (Vertice<V> vertices: grafo.keySet())
		{
			id = vertices.getId();
			if (id==clave)
			{
				return false;
			}
		}
		//si no lo hay creamos una lista de lados nueva y vacia para el vertice,
		// y agregamos el vertice y la lista al diccionario
		ArrayList<Arista<L>> listadelados = new ArrayList<Arista<L>>();
		grafo.put(v,listadelados);
		return true;
	}

	/**
	 * funcion que agrega un vertice a partir de su informacion
	 * @param id es el id del vertice que queremos agregar
	 * @param dato es el dato de tipo generico que queremos tener en el vertice
	 * @param p es el peso del vertice
	 * @return true si se agrega en vertice, false en otro caso
	 */
	public Boolean agregarVertice(int id,int posicionX,int posicionY)
	{
		//creamos un objeto vertice e intetamos agregarlo
		Vertice<V> nuevoVertice = new Vertice<V>(id,posicionX,posicionY);
		return agregarVertice(nuevoVertice);
	}

	/**
	 * funcion que obtiene un vertice segun su id
	 * @param id id del vertice que buscamos
	 * @return regresa el vertice que se busca o una excepcion en caso contrario
	 */
	public Vertice obtenerVertice(int id)
	{
		//chequeamos si algun vertice tiene ese id
		for (Vertice<V> vertices: grafo.keySet())
		{
			if (vertices.getId()==id)
			{
			 	return vertices;
			}
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	/**
	 * funcion que revisa si el vertice con ese id esta
	 * @param id id del vertice que se desea verificar
	 * @return true si esta false en otro caso
	 */
	public Boolean estaVertice(int id)
	{
		//revisamos si existe algun vertice con ese id en el grafo
		for (Vertice<V> vertices: grafo.keySet())
		{
			if (vertices.getId()==id)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * funcion que calcula el numero de vertices de un digrafo
	 * @return regresa un entero que representa el numero de vertices
	 */
	public int numeroDeVertices()
	{
		int i = 0; //contador de vertices
		for (Vertice<V> ids: grafo.keySet())
		{
			i = i + 1;//sumamos uno por cada vertice en el dicionario
		}
		return i;
	}

	/**
	 * funcion que agrega un objeto arista al grafo
	 * @param a objeto arista que sera agregado
	 * @return true si es agregado, false en otro caso
	 */
	public Boolean agregarArista(Arista<L> a)
	{
		//vertices no estan
		int verticeA = a.getExtremo1().getId();
		int verticeB = a.getExtremo2().getId();
		if (!(estaVertice(verticeA)) || !(estaVertice(verticeB)))
		{
			return false;
		}

		//vertices estan pero arista con ese id ya esta
		int id = a.getId();
		
		int i;
		for (ArrayList<Arista<L>> aristasDeEsteVertice: grafo.values())
		{
			i = aristasDeEsteVertice.size();
			for(int k = 0; k< i; k++)
			{
			 	if (aristasDeEsteVertice.get(k).getId()==id)
			 	{
			 		return false;
			 	}
			}
		}

		Vertice<V> verticeInicial = obtenerVertice(verticeA);
		Vertice<V> verticeFinal = obtenerVertice(verticeB);
		//insertar arista
		ArrayList<Arista<L>> lados = grafo.get(verticeInicial);
		lados.add(a);
		grafo.put(verticeInicial,lados); // agregamos el original
		if(verticeA!=verticeB) // si es un bucle solo se agrega una vez
		{
			lados = grafo.get(verticeFinal);
			lados.add(a);
			grafo.put(verticeFinal,lados); // agregamos el duplicado
		}
		return true;
	}

	/**
	 * funcion que agrega arista por informacion
	 * @param id id del arista que se quiere agregar
	 * @param dato dato del tipo generico de arista que se quiere agregar
	 * @param p	peso del arista que se quiere agregar
	 * @param vInicial	id del vertice inicial del arista que se quiere agregar
	 * @param vFinal id del vertice final del arista que se quiere agregar
	 * @return	true si se agrego el arista, false en otro caso
	 */
	public Boolean agregarArista(int id,int vInicial,int vFinal)
	{
		Vertice<V> verticeInicial;
		Vertice<V> verticeFinal;
		//si esta el vertice lo marcamos
		if (estaVertice(vInicial))
		{
			verticeInicial = obtenerVertice(vInicial);	
		//si no salimos
		}else{
			return false;
		}
		//si esta el vertice lo marcamos
		if (estaVertice(vFinal))
		{
			verticeFinal = obtenerVertice(vFinal);
		//si no salimos
		}else{
			return false;
		}

		Mathematics calcularDistancia = new Mathematics();

		double distancia = calcularDistancia.distanciaEuclidiana(verticeInicial.posicionX(),verticeInicial.posicionY(),
			verticeFinal.posicionX(),verticeFinal.posicionY());

		//creamos la nueva arista a introducir
		Arista<L> nuevoArista = new Arista<L>(id,verticeInicial,verticeFinal,distancia);

		//lo insertamos
		return agregarArista(nuevoArista);
	}
	/**
	 * funcion que devuelve una lista de aristas en el grafo
	 * @return una lista de aristas representados en el vertice, si no hay regresa una lista vacia
	 */
	public ArrayList<Arista<L>> aristas()
	{
		ArrayList<Arista<L>> listaLados = new ArrayList<Arista<L>>();
		int i;
		int j;
		boolean duplicado;
		for (ArrayList<Arista<L>> aristasDeEsteVertice: grafo.values())
		{
			i = aristasDeEsteVertice.size();
			for(int k = 0; k< i; k++)
			{
				j = listaLados.size();
				duplicado = false;
				for (int l = 0; l< j && !duplicado; l++) //buscamos si ya la habiamos agregado
				{
					if (aristasDeEsteVertice.get(k).getId()==(listaLados.get(l).getId()))
					{
						duplicado = true;
					}
				}
				if (!duplicado) // si no se encontraba ya en el arreglo
		 		{
		 			listaLados.add(aristasDeEsteVertice.get(k));//aqui agregamos cada arista
		 		}		 			
			}
		}
		return listaLados;
	}
}