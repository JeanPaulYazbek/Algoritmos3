import java.util.Hashtable;
import java.util.ArrayList;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;

public class GrafoNoDirigido<V, L> implements Grafo<V, L>
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
	 * @param transformer objeto de la clase Transformer que nos ayuda a convertir de String a tipo generico del vertice
	 * @param transformerarco objeto de la clase Transformer que nos ayuda a convertir de String a tipo generico de arco
	 * @return un booleano, true si se creo exitosamente el grafo, false si no
	 * @throws IOException
	 */
	public Boolean cargarGrafo(String archivo, Transformer<String, V> transformer,
	  Transformer<String, L> transformerarista)
	throws IOException
	{	
		BufferedReader Lector = new BufferedReader(new FileReader(archivo));

		String linea = Lector.readLine();//ya leimos V
		linea = Lector.readLine();//ya leimos L
		linea = Lector.readLine();//ya leimos O
		linea = Lector.readLine();//nos interesa n(numero de vertices)
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

		String id;//guarda ids que leamos
		V dato;//guarda datos que leamos
		double peso;//guarda peso que leamos
		//AGREGAR LOS VERTICES
		for(int k = 0; k<numeroVertices; k++)
		{
			linea = Lector.readLine();//a partir de aqui son vertices
			String[] vertice = linea.split(" ");

			if(vertice.length != 3){//en caso de formato erroneo por cantidad de elementos
				return false;
			}

			//BUSCAMOS ERRORES DE FORMATO
			try{
				id  = vertice[0];
				peso = Double.valueOf(vertice[2]);
			 	dato = transformer.transform(vertice[1]);//debemos transformar al tipo adecuado
			}catch(Exception e){
				return false;//si algunos de los datos es erroneo
			}
			agregarVertice(id, dato, peso);
		}

		String idarista;//guarda id del arista leido
		L datoarista;	  //guarda dato de la arista leida
		double pesoarista;//guarda peso de la arista leida
		String extremo1;		//guarda id del extremo 1 de la arista que leamos
		String extremo2;		//guarda id "			" 2 "  				"
		//AGREGAR LAS ARISTAS
		for(int k = 0; k<numeroLados; k++)
		{

			linea = Lector.readLine();//a partir de aqui son vertices
			String[] vertice = linea.split(" ");

			if(vertice.length != 5){//en caso de formato erroneo por cantidad de elementos
				return false;
			}

			//BUSCAMOS ERRORES DE FORMATO
			try{
				idarista  = vertice[0];
				peso = Double.valueOf(vertice[2]);
			 	datoarista = transformerarista.transform(vertice[1]);
				extremo1= vertice[3];
				extremo2= vertice[4];
				
			}catch(Exception e){
				return false;//si algunos de los datos es erroneo
			}
			agregarArista(idarista, datoarista, peso, extremo1, extremo2);
		}
		//se cargo el grafo
		return true;
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
	 * funcion que calcula el numero de lados
	 * @return entero que representa el numero de lados
	 */
	public int numeroDeLados()
	{

		int i = 0;//contador de lados
		int temp;//tamaño de la lista de lados del vertice
		String id;
		for (ArrayList<Arista<L>> aristasDeEsteVertice: grafo.values())
		{
			temp = aristasDeEsteVertice.size();
			i = i + temp;
			for(int k = 0; k< temp; k++)
			{
				id = aristasDeEsteVertice.get(k).getExtremo1().getId();
				if(aristasDeEsteVertice.get(k).getExtremo2().getId().equals(id))
				{
					i = i +1;//si la arista es un bucle sumamos 1 mas
				}
			}
		}
		return i/2; //contamos cada lado 2 veces y los bucles valen por 2
	}

	/**
	 * funcion que agrega un vertice a partir de un objeto, si no se puede retorna false y si se puede retorna true
	 * @param v objeto de la clase vertice que se quiere agregar al grafo
	 * @return regresa true si se agrego exitosamente
	 */
	public Boolean agregarVertice(Vertice<V> v)
	{
		String clave = v.getId();//clave del vertice
		String id;				//id para comparar
		//revisamos si ya hay un vertice con ese id
		for (Vertice<V> vertices: grafo.keySet())
		{
			id = vertices.getId();
			if (id.equals(clave))
			{
				return false;
			}
		}
		//si no lo hay creamos una lista de lados nueva y vacia para el vertice,
		// y agregamos el vertice y la lista al diccionario
		ArrayList<Arista<L>> listadelados = new ArrayList<Arista<L>>();
		grafo.put(v, listadelados);
		return true;
	}

	/**
	 * funcion que agrega un vertice a partir de su informacion
	 * @param id es el id del vertice que queremos agregar
	 * @param dato es el dato de tipo generico que queremos tener en el vertice
	 * @param p es el peso del vertice
	 * @return true si se agrega en vertice, false en otro caso
	 */
	public Boolean agregarVertice( String id, V dato, double p )
	{
		//creamos un objeto vertice e intetamos agregarlo
		Vertice<V> nuevoVertice = new Vertice<V>(id, dato, p);
		return agregarVertice(nuevoVertice);
	}

	/**
	 * funcion que obtiene un vertice segun su id
	 * @param id id del vertice que buscamos
	 * @return regresa el vertice que se busca o una excepcion en caso contrario
	 */
	public Vertice obtenerVertice(String id)
	{
		//chequeamos si algun vertice tiene ese id
		for (Vertice<V> vertices: grafo.keySet())
		{
			if (vertices.getId().equals(id))
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
	public Boolean estaVertice(String id)
	{

		//revisamos si existe algun vertice con ese id en el grafo
		for (Vertice<V> vertices: grafo.keySet())
		{
			
			if (vertices.getId().equals(id))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * funcion que agrega un objeto arista al grafo
	 * @param a objeto arista que sera agregado
	 * @return true si es agregado, false en otro caso
	 */
	public Boolean agregarArista(Arista<L> a)
	{
		//vertices no estan
		String verticeA = a.getExtremo1().getId();
		String verticeB = a.getExtremo2().getId();
		if (!(estaVertice(verticeA)) || !(estaVertice(verticeB)))
		{
			return false;
		}

		//vertices estan pero arista con ese id ya esta
		String id = a.getId();
		
		int i;
		for (ArrayList<Arista<L>> aristasDeEsteVertice: grafo.values())
		{
			i = aristasDeEsteVertice.size();
			for(int k = 0; k< i; k++)
			{
			 	if (aristasDeEsteVertice.get(k).getId().equals(id))
			 	{
			 		return false;
			 	}
			}
		}
		//vertices con el mismo id pero diferentes datos lo cual no es valido
		// ya que hay un solo vertice en el grafo 
		Vertice<V> verticeInicial = obtenerVertice(verticeA);
		Vertice<V> verticeFinal = obtenerVertice(verticeB);
		Vertice<V> vertice1 = a.getExtremo1();
		Vertice<V> vertice2 = a.getExtremo2();
		if ((verticeInicial.getPeso() != vertice1.getPeso())
			||(verticeFinal.getPeso() != vertice2.getPeso()))
		{
			return false;
		}
		if ((verticeInicial.getDato() != vertice1.getDato())||(verticeFinal.getDato()
		 != vertice2.getDato()))
		{
			return false;
		}

		//insertar arista
		ArrayList<Arista<L>> lados = grafo.get(verticeInicial);
		lados.add(a);
		grafo.put(verticeInicial,lados); // agregamos el original
		if(!verticeA.equals(verticeB)) // si es un bucle solo se agrega una vez
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
	public Boolean agregarArista(String id, L dato, double p, String vInicial, String vFinal)
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

		//creamos la nueva arista a introducir
		Arista<L> nuevoArista = new Arista<L>(id, dato, p, verticeInicial, verticeFinal);

		//lo insertamos
		return agregarArista(nuevoArista);
	}

	/**
	 * funcion que busca un arista por su id
	 * @param id id del arista que se quiere obtener
	 * @return objeto del tipo arista con el id solicitado, lanza una excepcion si no se encuentra
	 */
	public Arista<L> obtenerArista(String id)
	{
		int i;
		//buscamos en todo el grafo si existe un arista con ese id
		for (ArrayList<Arista<L>> aristasDeEsteVertice: grafo.values())
		{
			i = aristasDeEsteVertice.size();
			for(int k = 0; k< i; k++)
			{
			 	if (aristasDeEsteVertice.get(k).getId().equals(id))
			 	{
			 		return aristasDeEsteVertice.get(k);
			 	}
			}
		}
		throw new NoSuchElementException("No se encontro el arista");
	}

	/**
	 * funcion que elimina arista por id 
	 * @param id id del arista que se quiere eliminar
	 * @return	true si se elimina exitosamente, false en otro caso
	 */
	public Boolean eliminarArista(String id)
	{
		int i;
		int j = 0;
		String id1;
		//buscamos si existe ese arista en el garfo
		for (ArrayList<Arista<L>> aristasDeEsteVertice: grafo.values())
		{
			i = aristasDeEsteVertice.size();
			for (int k = 0; k< i; k++)
			{
			 	if (aristasDeEsteVertice.get(k).getId().equals(id))
			 	{
			 		j +=1;
			 		id1 = aristasDeEsteVertice.get(k).getExtremo1().getId();
					if (!aristasDeEsteVertice.get(k).getExtremo2().getId().equals(id1)
						&& j==1)
					{
						aristasDeEsteVertice.remove(k);
					}
					else if (!aristasDeEsteVertice.get(k).getExtremo2().getId().equals(id1)
						&& j==2)
					{
						aristasDeEsteVertice.remove(k);
			 			return true;
					}
					else // si era un bucle entonces solo hay 1 arista que eliminar
					{
						aristasDeEsteVertice.remove(k);
			 			return true;
					}
			 	}
			}
		}
		return false;
	}

	/**
	 * funcion que revisa si un lado esta por el id de los vertices
	 * @param u id del vertice inicial del arco que se busca
	 * @param v	id del vertice final que se busca
	 * @return	true si esta en el grafo, false en otro caso
	 */
	public Boolean estaLado(String u, String v)
	{
		//si ambos vertices estan lo buscamos
		if(estaVertice(u) && estaVertice(v))
		{
			//si el lado esta, esta en la lista de lados del vertice u
			Vertice<V> ver = obtenerVertice(u);
			ArrayList<Arista<L>> listadelados = grafo.get(ver);
			int n = listadelados.size();
			//buscamos en la lista de lados de u
			for(int k =0; k<n; k++)
			{
				if (listadelados.get(k).getExtremo2().getId().equals(v))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * funcion que elimina vertice por id
	 * @param id id del vertice que se desea elimina
	 * @return	true si se elimina , false en otro caso
	 */
	public Boolean eliminarVertice(String id)
	{
		//solo procedemos si el vertice esta en el grafo
		if (estaVertice(id))
		{
			Vertice<V> eliminar = obtenerVertice(id);//buscamos el objeto tipo vertice
			grafo.remove(eliminar);//lo sacamos del grafo
			int i;
			//sacamos todo los aristas relacionados a ese vertice que sobrevivieron
			for (ArrayList<Arista<L>> aristasDeEsteVertice: grafo.values())
			{
				i = aristasDeEsteVertice.size();
				for(int k = i-1; k>=0; k--)
				{
			 		if (aristasDeEsteVertice.get(k).getExtremo2().getId().equals(id)
			 			|| aristasDeEsteVertice.get(k).getExtremo1().getId().equals(id))
			 		{
			 			aristasDeEsteVertice.remove(k);
				 	}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * funcion que devuelve una lista de objetos tipo vertice en el grafo
	 * @return una lista con objetos vertices en el grafo, si no hay regresa una lista vacia 
	 */
	public ArrayList<Vertice<V>> vertices()
	{
		ArrayList<Vertice<V>> listaVertices = new ArrayList<Vertice<V>>();	
		for (Vertice<V> vertice: grafo.keySet())
		{
			listaVertices.add(vertice);
		}
		return listaVertices;
	}

	/**
	 * funcion que devuelve una lista de lados en el grafo
	 * @return una lista de aristas representados en el vertice, si no hay regresa una lista vacia
	 */
	public ArrayList<Lado<L>> lados()
	{
		ArrayList<Lado<L>> listaLados = new ArrayList<Lado<L>>();
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
					if (aristasDeEsteVertice.get(k).getId().equals(listaLados.get(l).getId()))
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

	/**
	 * funcion que devuelve el grado de un vertice existente en el grafo
	 * @param id id del vertice que cuyo grado se quiere revisar
	 * @return	entero que representa el grado del vertice, si no esta da una excepcion 
	 */
	public int grado(String id)
	{
		//procedemos solo si el vertice esta en el grafo
		if (estaVertice(id))
		{
			ArrayList<Arista<L>> lados = grafo.get(obtenerVertice(id));
			int grado = lados.size(); //almacenamos el numero de aristas	
			for (int k = 0; k< grado; k++) //buscamos si hay un bucle
			{
				if(lados.get(k).getExtremo1().getId().equals(id) 
					&& lados.get(k).getExtremo2().getId().equals(id) ) //hallamos un bucle
				{
					grado +=1;
				}
			}
			return grado;
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	/**
	 * 
	 * @param id id del vertices cuyos adyacente se buscan
	 * @return	lista de vertices adyacentes al vertice solicitado, en caso de que no este lanza una excepcion
	 */
	public ArrayList<Vertice<V>> adyacentes(String id)
	{	
		ArrayList<Vertice<V>> adyacentes = new ArrayList<Vertice<V>>();
		//procedemos solo si el vertice esta 
		if(estaVertice(id)){
			String idadyacente;
			for (Vertice<V> vertice: grafo.keySet()){//buscamos todas las
			// combinaciones posibles de aristas con el vertice
				idadyacente = vertice.getId();
				if (estaLado(id, idadyacente) || estaLado(idadyacente,id))
				{//vemos si el arista esta
					adyacentes.add(vertice);//agregamos el vertice adyacente
				}
			}
			return adyacentes;
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	/**
	 * funcion que toma una clave vertice y devuelve una lista de aritas incidentes del vertice en caso de que el vertice este
	 * @param id id del vertice cuyos aristas incidentes se desea solicitar
	 * @return regresa una lista de aristas incidentes al vertice , si el vertice no esta lanza una excepcion
	 */
	public ArrayList<Lado<L>> incidentes(String id)
	{
		if(estaVertice(id))
		{
			ArrayList<Lado<L>> lados = new ArrayList<Lado<L>>();
			ArrayList<Arista<L>> aristas = grafo.get(obtenerVertice(id));
			int i = aristas.size();
			for(int k = 0; k< i; k++)
			{
				lados.add(aristas.get(k));
			}
			return lados;
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	/** 
	 * funcion que convierte  un grafo en String para impresion
	 * @return un string que representa el grafo en su estado actual
	*/
	public String toString()
	{	
		String cadenaGrafo = "";
		ArrayList<Arista<L>> listadelados;
		int k;
		String idv;

		for (Vertice<V> vertices: grafo.keySet())
		{
			idv = vertices.getId();
			cadenaGrafo = cadenaGrafo + idv + ": ";
			listadelados = grafo.get(vertices);
			k = listadelados.size();
			if (k != 0)
			{
				for(int j = 0; j<k-1; j++)
				{
					if(listadelados.get(j).getExtremo1().getId().equals(idv)) // es un bucle o un original
					{
						cadenaGrafo = cadenaGrafo+listadelados.get(j).getExtremo2().getId()+", ";
					}
					else // es un duplicado
					{
						cadenaGrafo = cadenaGrafo+listadelados.get(j).getExtremo1().getId()+", ";
					}
				}
				if(listadelados.get(k-1).getExtremo1().getId().equals(idv)) // es un bucle o un original
				{
					cadenaGrafo = cadenaGrafo+listadelados.get(k-1).getExtremo2().getId()+"\n";
				}
				else // es un duplicado
				{
					cadenaGrafo = cadenaGrafo+listadelados.get(k-1).getExtremo1().getId()+"\n";
				}
			}else{
				cadenaGrafo = cadenaGrafo+"\n";
			}
		}
		return cadenaGrafo;
	}

	/**
	 * funcion que clona un grafo en un nuevo objeto grafo
	 * @return regresa un objeto tipo grafo con la forma del grafo actual
	 */
	public Grafo<V, L> clone()
	{
		GrafoNoDirigido<V, L> clon = new GrafoNoDirigido<V, L>();
		clon.grafo = grafo;
		return clon;
	}
}