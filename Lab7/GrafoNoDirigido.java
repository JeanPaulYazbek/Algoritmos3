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

			String[] coordenadas = linea.split(" ");

			if(coordenadas.length != 2){//en caso de formato erroneo por cantidad de elementos
				return false;
			}
			double posicionX;
			double posicionY;
			//BUSCAMOS ERRORES DE FORMATO
			try{
				posicionX = Double.parseDouble(coordenadas[0]);
				posicionY = Double.parseDouble(coordenadas[1]);
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

			int extremo1;
			int extremo2;
			//BUSCAMOS ERRORES DE FORMATO
			try{
				extremo1= Integer.parseInt(vertice[0]);
				extremo2= Integer.parseInt(vertice[1]);
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
	public Boolean agregarVertice(int id,double posicionX,double posicionY)
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
	/** 
	 * funcion que convierte  un grafo en String para impresion
	 * @return un string que representa el grafo en su estado actual
	*/
	public String toString()
	{	
		String cadenaGrafo = "";
		ArrayList<Arista<L>> listadelados;
		int k;
		int idv;

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
					if(listadelados.get(j).getExtremo1().getId()==(idv)) // es un bucle o un original
					{
						cadenaGrafo = cadenaGrafo+listadelados.get(j).getExtremo2().getId()+"("+listadelados.get(j).distancia()+"),";
					}
					else // es un duplicado
					{
						cadenaGrafo = cadenaGrafo+listadelados.get(j).getExtremo1().getId()+"("+listadelados.get(j).distancia()+"),";
					}
				}
				if(listadelados.get(k-1).getExtremo1().getId()==(idv)) // es un bucle o un original
				{
					cadenaGrafo = cadenaGrafo+listadelados.get(k-1).getExtremo2().getId()+"("+listadelados.get(k-1).distancia()+")"+"\n";
				}
				else // es un duplicado
				{
					cadenaGrafo = cadenaGrafo+listadelados.get(k-1).getExtremo1().getId()+"("+listadelados.get(k-1).distancia()+")"+"\n";
				}
			}else{
				cadenaGrafo = cadenaGrafo+"\n";
			}
		}
		return cadenaGrafo;
	}

		public ArrayList<Vertice<V>> adyacentes(int id)
	{	
		ArrayList<Vertice<V>> adyacentes = new ArrayList<Vertice<V>>();
		//procedemos solo si el vertice esta 
		if(estaVertice(id)){
			int idadyacente;
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
	 * funcion que revisa si un lado esta por el id de los vertices
	 * @param u id del vertice inicial del arco que se busca
	 * @param v	id del vertice final que se busca
	 * @return	true si esta en el grafo, false en otro caso
	 */
	public Boolean estaLado(int u, int v)
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
				if (listadelados.get(k).getExtremo2().getId() == v)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * funcion que toma una clave vertice y devuelve una lista de aritas incidentes del vertice en caso de que el vertice este
	 * @param id id del vertice cuyos aristas incidentes se desea solicitar
	 * @return regresa una lista de aristas incidentes al vertice , si el vertice no esta lanza una excepcion
	 */
	public ArrayList<Arista<L>> incidentes(int id)
	{
		if(estaVertice(id))
		{
			ArrayList<Arista<L>> lados = new ArrayList<Arista<L>>();
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
	 * funcion que resetea los costos y predecesores de los vertices de un grafo
	 */
	public void resetVertices(){

		ArrayList<Vertice<V>> vertices = this.vertices();
		for(Vertice<V> vert: vertices){

			vert.f = 9999999.00;
			vert.costo = 9999999.00;
			vert.predecesor = -1;
			vert.aristaPredecesora = null;
		}
	}

	/**
	 * funcion que setea la euristica h de un grafo de acuerdo a un vertice final
	 */
	public void setEuristica(int verticeFin){

		Vertice<V> verticeFinal = obtenerVertice(verticeFin);
		ArrayList<Vertice<V>> vertices = this.vertices();
		Mathematics calcularDistancia = new Mathematics();

		for(Vertice<V> vert: vertices){

			vert.h = calcularDistancia.distanciaEuclidiana(vert.posicionX(), vert.posicionY(), verticeFinal.posicionX(), verticeFinal.posicionY());
			
		}
	}
}