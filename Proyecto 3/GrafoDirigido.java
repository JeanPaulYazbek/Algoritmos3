import java.util.Hashtable;
import java.util.ArrayList;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class GrafoDirigido<V, L> implements Grafo<V, L>{

	//digrafo: representamos el grafo con un diccionario donde las claves son objetos tipo vertices , y los valores listas de objetos tipo lados
	private Hashtable<Vertice<V>, ArrayList<Arco<L>>> digrafo = new Hashtable<Vertice<V>, ArrayList<Arco<L>>>();
	private String[][] matrizDeVertices;

	/**
	 * constructor por defecto
	 */
	GrafoDirigido()
	{
	}
////////////////////////////////////////////////////////////////////////////////
	/**
	 * funcion que carga un grafo de un archivo de texto en el grafo
	 * @param archivo nombre del archivo desde el que queremos cargar el grafo
	 * @param transformer objeto de la clase Transformer que nos ayuda a convertir de String a tipo generico del vertice
	 * @param transformerarco objeto de la clase Transformer que nos ayuda a convertir de String a tipo generico de arco
	 * @return un booleano, true si se creo exitosamente el grafo, false si no
	 * @throws IOException
	 */
	public Boolean cargarGrafo(String archivo)
	throws IOException
	{
		BufferedReader Lector = new BufferedReader(new FileReader(archivo));

		String linea = Lector.readLine();
		
		String[] cuantasCeldas = linea.split(" ");
		
		//Buscamos errores en la linea
		int numeroLineas;
		int numeroExpresiones;
		if(cuantasCeldas.length != 2)
		{//en caso de formato erroneo
			return false;
		}
		try
		{
			numeroLineas = Integer.parseInt(cuantasCeldas[0]);
			numeroExpresiones = Integer.parseInt(cuantasCeldas[1]);
		}
		catch(Exception e)
		{
			return false;//si no es un entero formato erroneo
		}
		//AGREGAR LOS VERTICES
		String idVertice;
		String columna;
		V dato =  null;
		matrizDeVertices= new String[numeroLineas][numeroExpresiones];
		for(int i = 0; i<numeroLineas; i++)
		{
			for(int j = 0; j<numeroExpresiones; j++)
			{
				idVertice = translateCol(j+1) + String.valueOf(i+1);
				matrizDeVertices[i][j]= idVertice;
				agregarVertice(idVertice, dato, 0.0);
			}
		}

		//AGREGAR LOS ARCOS
		String idarco;//guarda id del arco leido
		L datoarco = null;	  //guarda dato del arco leido
		double pesoarco;//guarda peso del arco leido
		String expresionActual;
		char newCharacter;
		ArrayList<String> verticesExpresion = new ArrayList<String>();
		//AGREGAR LOS ARCOS
		for(int i = 0; i<numeroLineas; i++)
		{
			linea = Lector.readLine();//a partir de aqui son vertices
			String[] expresiones;
			try
			{
				expresiones = linea.split(" ");
			}
			catch(Exception e)
			{	
				System.out.println("Numero De Lineas erroneo");
				return false;//si no es un entero formato erroneo
			}
			for(int j = 0; j<numeroExpresiones; j++)
			{
				idVertice = matrizDeVertices[i][j];
				try
				{
					expresionActual = expresiones[j]+" ";
				}
				catch(Exception e)
				{	
					System.out.println("Numero De Expresiones erroneo");
					return false;//si no es un entero formato erroneo
				}
				if (expresionActual.charAt(0)=='=')
				{
					expresionActual = expresionActual.substring(1,expresionActual.length()-1);
				}
		        //Convertimos cualquier letra a Mayusculas
				StringBuilder sb = new StringBuilder(expresionActual);
				for (int index = 0; index < sb.length(); index++)
				{
					char c = sb.charAt(index);
					if (Character.isLowerCase(c))
					{
						sb.setCharAt(index, Character.toUpperCase(c));
					}
				}
				expresionActual = sb.toString();

				Pattern pattern = Pattern.compile("[a-zA-Z]+\\d+");
				Matcher matcher = pattern.matcher(expresionActual);
				verticesExpresion = new ArrayList<String>();
				while (matcher.find())
				{
					verticesExpresion.add(matcher.group(0));
				}

				obtenerVertice(idVertice).modifyExpresion(expresionActual);

		        for (int k = 0; k<verticesExpresion.size(); ++k)
		        {
		            if (estaVertice(verticesExpresion.get(k)))
		            {
						idarco = String.valueOf(verticesExpresion.get(k))
						+String.valueOf(idVertice);
						if (verticesExpresion.get(k).equals(idVertice))
						{
							System.out.println("Su configuración contiene un ciclo:");
							System.out.println(idVertice+"->"+idVertice);
							return false;
						}
						agregarArco(idarco,datoarco,0.0,verticesExpresion.get(k),idVertice);
						obtenerVertice(idVertice).predecesores.add(obtenerVertice(verticesExpresion.get(k)));
		            }
		            else
		            {
		            	System.out.println("Se ha detectado vertice invalido");
		            	System.out.println(verticesExpresion.get(k));
		            	return false;
		            }
		        }
			}
		}
		//se cargo el grafo
		return true;
	}
////////////////////////////////////////////////////////////////////////////////
	public String translateCol(int n)
	{
	    char[] buf = new char[(int) floor(log(25 * (n + 1)) / log(26))];
	    for (int i = buf.length - 1; i >= 0; i--)
	    {
	        n--;
	        buf[i] = (char) ('A' + n % 26);
	        n /= 26;
	    }
		return new String(buf);
	}
////////////////////////////////////////////////////////////////////////////////
    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;  
    }
////////////////////////////////////////////////////////////////////////////////


	/**
	 * funcion que calcula el numero de vertices de un digrafo
	 * @return regresa un entero que representa el numero de vertices
	 */
	public int numeroDeVertices()
	{
		int i = 0; //contador de vertices
		for (Vertice<V> ids: digrafo.keySet())
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
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values())
		{
			 i = i + arcosDeEsteVertice.size();
		}
		return i;
	}

	/**
	 * funcion que agrega un vertice a partir de un objeto, si no se puede
	 * retorna false y si se puede retorna true
	 * @param v objeto de la clase vertice que se quiere agregar al grafo
	 * @return regresa true si se agrego exitosamente
	 */
	public Boolean agregarVertice(Vertice<V> v){

		String clave = v.getId();//clave del vertice
		String id;				//id para comparar
		//revisamos si ya hay un vertice con ese id
		for (Vertice<V> vertices: digrafo.keySet())
		{
			 id = vertices.getId();
			 if (id.equals(clave))
			 {
			 	return false;
			 }
		}
		//si no lo hay creamos una lista de lados nueva y vacia para el vertice,
		// y agregamos el vertice y la lista al diccionario
		ArrayList<Arco<L>> listadelados = new ArrayList<Arco<L>>();
		digrafo.put(v, listadelados);
		return true;
	}

	/**
	 * funcion que agrega un vertice a partir de su informacion
	 * @param id es el id del vertice que queremos agregar
	 * @param dato es el dato de tipo generico que queremos tener en el vertice
	 * @param p es el peso del vertice
	 * @return true si se agrega en vertice, false en otro caso
	 */
	public Boolean agregarVertice( String id, V dato, double p ){
		//creamos un objeto vertice e intetamos agregarlo
		Vertice<V> nuevoVertice = new Vertice<V>(id, dato, p);
		return agregarVertice(nuevoVertice);
	}
	
	/**
	 * funcion que obtiene un vertice segun su id
	 * @param id id del vertice que buscamos
	 * @return regresa el vertice que se busca o una excepcion en caso contrario
	 */
	public Vertice obtenerVertice( String id){
		for (Vertice<V> vertices: digrafo.keySet())
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
	public Boolean estaVertice(String id){

		//revisamos si existe algun vertice con ese id en el grafo
		for (Vertice<V> vertices: digrafo.keySet())
		{
			if (vertices.getId().equals(id))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * funcion que agrega un objeto arco al grafo
	 * @param a objeto arco que sera agregado
	 * @return true si es agregado, false en otro caso
	 */
	public Boolean agregarArco(Arco<L> a){
		//vertices no estan
		String verticeA = a.getExtremoInicial().getId();
		String verticeB = a.getExtremoFinal().getId();
		if (!(estaVertice(verticeA)) || !(estaVertice(verticeB)))
		{
			return false;
		}

		//arcos con el mismo id
		String id = a.getId();
		
		int i;
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values())
		{
			i = arcosDeEsteVertice.size();
			for(int k = 0; k< i; k++)
			{
				if (arcosDeEsteVertice.get(k).getId().equals(id))
				{
					return false;
				}
			}
		}

		//vertices con el mismo id pero diferentes datos lo cual no es valido ya que hay un solo vertice en el grafo 
		Vertice<V> verticeInicial = obtenerVertice(verticeA);
		Vertice<V> verticeFinal = obtenerVertice(verticeB);
		Vertice<V> vertice1 = a.getExtremoInicial();
		Vertice<V> vertice2 = a.getExtremoFinal();
		if ((verticeInicial.getPeso() != vertice1.getPeso())
			||(verticeFinal.getPeso() != vertice2.getPeso()))
		{
			return false;
		}
		if ((verticeInicial.getDato() != vertice1.getDato())
			||(verticeFinal.getDato() != vertice2.getDato()))
		{
			return false;
		}

		//insertar arco
		ArrayList<Arco<L>> lados = digrafo.get(verticeInicial);
		lados.add(a);
		digrafo.put(verticeInicial, lados);
		return true;

	}


	/**
	 * funcion que agrega arco por informacion
	 * @param id id del arco que se quiere agregar
	 * @param dato dato del tipo generico de arco que se quiere agregar
	 * @param p	peso del arco que se quiere agregar
	 * @param vInicial	id del vertice inicial del arco que se quiere agregar
	 * @param vFinal id del vertice final del arco que se quiere agregar
	 * @return	true si se agrego el arco, false en otro caso
	 */
	public Boolean agregarArco(String id, L dato, double p, String vInicial, String vFinal){

		Vertice<V> verticeInicial;
		Vertice<V> verticeFinal;
		//si esta el vertice lo marcamos
		if (estaVertice(vInicial))
		{
			verticeInicial = obtenerVertice(vInicial);	
		//si no salimos
		}else
		{
			return false;
		}
		//si esta el vertice lo marcamos
		if (estaVertice(vFinal))
		{
			verticeFinal = obtenerVertice(vFinal);
		//si no salimos
		}else
		{
			return false;
		}

		//creamos el arco a introducir
		Arco<L> nuevoArco = new Arco<L>(id, dato, p, verticeInicial, verticeFinal);

		//lo insertamos
		return agregarArco(nuevoArco);
	}

	/**
	 * funcion que busca un arco por su id
	 * @param id id del arco que se quiere obtener
	 * @return objeto del tipo arco con el id solicitado, lanza una excepcion si no se encuentra
	 */
	public Arco<L> obtenerArco(String id){

		int i;//variable que nos ayudara a contar el largo de cada lista de lados en el grafo
		//buscamos en todo el grafo si existe un arco con ese id
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values())
		{
			i = arcosDeEsteVertice.size();
			for(int k = 0; k< i; k++)
			{
				if (arcosDeEsteVertice.get(k).getId().equals(id))
				{
					return arcosDeEsteVertice.get(k);
				}
			}
		}

		throw new NoSuchElementException("No se encontro el arco");
	}

	/**
	 * funcion que elimina arco por id 
	 * @param id id del arco que se quiere eliminar
	 * @return	true si se elimina exitosamente, false en otro caso
	 */
	public Boolean eliminarArco(String id){

		int i;
		//buscamos si existe ese arco en el garfo
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values())
		{
			i = arcosDeEsteVertice.size();
			for(int k = 0; k< i; k++)
			{
				if (arcosDeEsteVertice.get(k).getId().equals(id))
				{
					arcosDeEsteVertice.remove(k);
					return true;
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
	public Boolean estaLado(String u, String v){

		//si ambos vertices estan lo buscamos
		if(estaVertice(u) && estaVertice(v))
		{
			//si el lado esta , esta en la lista de lados del vertice u
			Vertice<V> ver = obtenerVertice(u);
			ArrayList<Arco<L>> listadelados = digrafo.get(ver);
			int n = listadelados.size();
			//buscamos en la lista de lados de u
			for(int k =0; k<n; k++)
			{
				if (listadelados.get(k).getExtremoFinal().getId().equals(v))
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
	public Boolean eliminarVertice(String id){

		//solo procedemos si el vertice esta en el grafo
		if (estaVertice(id))
		{

			Vertice<V> eliminar = obtenerVertice(id);//buscamos el objeto tipo vertice
			digrafo.remove(eliminar);//lo sacamos del grafo
			int i;
			//sacamos todo los arcos relacionados a ese vertice que sobrevivieron
			for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values())
			{
				 i = arcosDeEsteVertice.size();
				 for(int k = i-1; k>=0; k--)
				 {
			 		if (arcosDeEsteVertice.get(k).getExtremoFinal().getId().equals(id))
			 		{
			 			arcosDeEsteVertice.remove(k);
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
		
		for (Vertice<V> vertice: digrafo.keySet())
		{
			listaVertices.add(vertice);
		}
		return listaVertices;
	}

	/**
	 * funcion que devuelve una lista de lados en el grafo
	 * @return una lista de arcos representados en el vertice, si no hay regresa una lista vacia
	 */
	public ArrayList<Lado<L>> lados(){

		ArrayList<Lado<L>> listaLados = new ArrayList<Lado<L>>();
		int i;
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values())
		{
			i = arcosDeEsteVertice.size();
			for(int k = 0; k< i; k++)
			{
				listaLados.add(arcosDeEsteVertice.get(k));//aqui agregamos cada arco
			}
		}
		return listaLados;
	}

	/**
	 * funcion que devuelve el grado de un vertice existente en el grafo
	 * @param id id del vertice que cuyo grado se quiere revisar
	 * @return	entero que representa el grado del vertice, si no esta da una excepcion 
	 */
	public int grado(String id){

		//procedemos solo si el vertice esta en el grafo
		if (estaVertice(id))
		{
			ArrayList<Lado<L>> listaLados = new ArrayList<Lado<L>>();
			int i;
			int grado = 0;//grado comienza en 0
			for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values())
			{//revisamos cada arco
				 i = arcosDeEsteVertice.size();
				 for(int k = 0; k< i; k++)
				 {
			 		if(arcosDeEsteVertice.get(k).getExtremoFinal().getId().equals(id)
			 		 || arcosDeEsteVertice.get(k).getExtremoInicial().getId().equals(id))
			 		{
			 			grado = grado + 1;//si el arco tiene el vertice sumamos 1
			 			if(arcosDeEsteVertice.get(k).getExtremoFinal().getId().equals(id)
			 			 && arcosDeEsteVertice.get(k).getExtremoInicial().getId().equals(id))
			 			{
			 				grado = grado +1;//si el arco es un bucle y tiene el vertice sumamos 1 mas 
			 			}
			 		}
			 			
				 }
			}
			return grado;
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	//>>>16 funcion que regresa una lista de vertices adyacentes a un vertice dada su clave
	/**
	 * 
	 * @param id id del vertices cuyos adyacente se buscan
	 * @return	lista de vertices adyacentes al vertice solicitado, en caso de que no este lanza una excepcion
	 */
	public ArrayList<Vertice<V>> adyacentes(String id){
		
		ArrayList<Vertice<V>> adyacentes = new ArrayList<Vertice<V>>();
		//procedemos solo si el vertice esta 
		if(estaVertice(id)){
			String idadyacente;
			for (Vertice<V> vertice: digrafo.keySet()){//buscamos todas las combinaciones posibles de arcos con el vertice
				idadyacente = vertice.getId();
				if (estaLado(id, idadyacente) || estaLado(idadyacente,id)){//vemos si el arco esta
					adyacentes.add(vertice);//agregamos el vertice adyacente
				}

			}
			return adyacentes;
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	/**
	 * funcion que toma una clave vertice y devuelve una lista de arcos incidentes del vertice en caso de que el vertice este
	 * @param id id del vertice cuyos arcos incidentes se desea solicitar
	 * @return regresa una lista de arcos incidentes al vertice , si el vertice no esta lanza una excepcion
	 */
	public ArrayList<Lado<L>> incidentes(String id){

		ArrayList<Lado<L>> lados = new ArrayList<Lado<L>>();
		if(estaVertice(id)){
			int i;
			for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values()){//buscamos en todos los arco
				 i = arcosDeEsteVertice.size();
				 for(int k = 0; k< i; k++){//vemos si el vertice incide
			 		if(arcosDeEsteVertice.get(k).getExtremoInicial().getId().equals(id) || arcosDeEsteVertice.get(k).getExtremoFinal().getId().equals(id)){
			 			lados.add(arcosDeEsteVertice.get(k));//si incide lo agregamos
			 		}
			 			
				 }
			}
			return lados;

		}
			
		
		throw new NoSuchElementException("No se encontro el vertice");

	}

	/**
	 * funcion que toma una clave de vertice y devuelve el grado exterior en caso de que este
	 * @param id id del vertice cuyo grado exterior se desea chequear 
	 * @return	el grado exterior del vertice, en caso de que no este se lanza una excepcion
	 */
	public int gradoExterior(String id){

		//procedemos si el vertice esta en el grafo
		if (estaVertice(id)){
			Vertice<V> vertice = obtenerVertice(id);
			ArrayList<Arco<L>> listadelados = digrafo.get(vertice);//lista de arcos del vertice
			int grado = 0;
			int n = listadelados.size();
			for(int i = 0; i< n; i++){
				grado = grado + 1;//sumamos uno por cada arco  que comienza en el vertice
				
			}
			return grado;

		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	/**
	 * funcion que toma la clave de un vertice y calcula el grado interno del vertice si esta en el grafo
	 * @param id id del vertice cuyo grado interior se desea solicitar
	 * @return	entero que representa el gardo interior del vertice , lanza una excepcion si no esta en el grafo.
	 */
	public int gradoInterior(String id){

		//procedemos si el vertice esta
		if (estaVertice(id)) {
			int grado = grado(id) - gradoExterior(id);//segun la teoria el grado interno es igual al grado menos el grado exterior
			return grado;
			
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	/**
	 * funcion que toma la clave de un vertice y devuelve una lista de vertices sucesores si el vertice esta en el grafo
	 * @param id id del vertice cuyos sucesores se quieren conocer
	 * @return lista de vertices sucesores del vertice solicitado, lanza una excepcion si no esta
	 */
	public ArrayList<Vertice<V>> sucesores(String id){
		
		ArrayList<Vertice<V>> sucesores = new ArrayList<Vertice<V>>();

		//procedemos solo si el vertice esta
		if(estaVertice(id)){
			String idadyacente;
			for (Vertice<V> vertice: digrafo.keySet()){//revisamos todo lado que comienze en el vertice anterior
				idadyacente = vertice.getId();
				if (estaLado(id, idadyacente) ){//si el lado esta
					sucesores.add(vertice);//agregamos el vertice final
				}

			}
			return sucesores;
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	public String toString(){
		
		String cadenaGrafo = "";
		ArrayList<Arco<L>> listadelados ;
		int k;

		for (Vertice<V> vertices: digrafo.keySet()){
			cadenaGrafo = cadenaGrafo + vertices.getId() + ": ";
			listadelados = digrafo.get(vertices);
			k = listadelados.size();
			if (k != 0){
				for(int j = 0; j<k-1; j++){
					cadenaGrafo = cadenaGrafo + listadelados.get(j).getExtremoFinal().getId() + ", ";
				}
				cadenaGrafo = cadenaGrafo + listadelados.get(k-1).getExtremoFinal().getId() + "\n";
			}else{
				cadenaGrafo = cadenaGrafo + "\n";
			}	
		}
		return cadenaGrafo;
	}
////////////////////////////////////////////////////////////////////////////////
	public String imprimirMatriz()
	{	
		String cadenaGrafo = "";
		int n = matrizDeVertices.length;
		int m = matrizDeVertices[0].length;
		for (int i = 0; i<n; ++i)
		{
			for (int j = 0; j<m; ++j)
			{
				cadenaGrafo += obtenerVertice(matrizDeVertices[i][j]).eval.replace(" ","")+" ";
			}
				cadenaGrafo += "\n";
		}
		return cadenaGrafo;
	}
////////////////////////////////////////////////////////////////////////////////
	/**
	 * funcion que clona un grafo en un nuevo objeto grafo
	 * @return regresa un objeto tipo grafo con la forma del grafo actual
	 */
	public GrafoDirigido<V, L> clone(){

		GrafoDirigido<V, L> clon = new GrafoDirigido<V, L>();
		clon.digrafo = (Hashtable)digrafo.clone();
		return clon;
	}
}