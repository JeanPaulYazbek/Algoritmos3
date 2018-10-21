import java.util.Hashtable;
import java.util.ArrayList;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;


public class GrafoDirigido<V, L> implements Grafo<V, L>{

	//digrafo: representamos el grafo con un diccionario donde las claves son objetos tipo vertices , y los valores listas de objetos tipo lados
	private Hashtable<Vertice<V>, ArrayList<Arco<L>>> digrafo = new Hashtable<Vertice<V>, ArrayList<Arco<L>>>();

	//>>>constructor por defecto
	GrafoDirigido(){
	}
	//>>>0 funcion que carga un grafo de un archivo de texto en el grafo
	public Boolean cargarGrafo(String archivo, Transformer<String, V> transformer,  Transformer<String, L> transformerarco)
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
		if(cuantosVertices.length != 1){//en caso de formato erroneo
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
		if(cuantosLados.length != 1){//en caso de formato erroneo
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
		for(int k = 0; k<numeroVertices; k++){

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

		String idarco;//guarda id del arco leido
		L datoarco;	  //guarda dato del arco leido
		double pesoarco;//guarda peso del arco leido
		String vi;		//guarda id del vertice inicial del arco que leamos
		String vf;		//guarda id "			" final "  				"
		//AGREGAR LOS ARCOS
		for(int k = 0; k<numeroLados; k++){

			linea = Lector.readLine();//a partir de aqui son vertices
			String[] vertice = linea.split(" ");

			if(vertice.length != 5){//en caso de formato erroneo por cantidad de elementos
				return false;
			}

			//BUSCAMOS ERRORES DE FORMATO
			try{
				idarco  = vertice[0];
				peso = Double.valueOf(vertice[2]);
			 	datoarco = transformerarco.transform(vertice[1]);
				vi = vertice[3];
				vf = vertice[4];
				
			}catch(Exception e){
				return false;//si algunos de los datos es erroneo
			}

			agregarArco(idarco, datoarco, peso, vi, vf);

		}
		//se cargo el grafo
		return true;
	}

	//>>>1 funcion que calcula el numero de vertices de un digrafo
	public int numeroDeVertices(){

		int i = 0; //contador de evrtices
		for (Vertice<V> ids: digrafo.keySet()){
			i = i + 1;//sumamos uno por cada vertice en el dicionario
		}
		return i;
	}

	//>>>2 funcion que calcula el numero de lados
	public int numeroDeLados(){

		int i = 0;//contador de lados
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values()){
			 i = i + arcosDeEsteVertice.size();
		}
		return i;
	}

	//>>>3 funcion que agrega un vertice a partir de un objeto, si no se puede retorna false y si se puede retorna true
	public Boolean agregarVertice(Vertice<V> v){

		String clave = v.getId();//clave del vertice
		String id;				//id para comparar
		//revisamos si ya hay un vertice con ese id
		for (Vertice<V> vertices: digrafo.keySet()){
			 id = vertices.getId();
			 if (id.equals(clave)){
			 	return false;
			 }
		}
		//si no lo hay creamos una lista de lados nueva y vacia para el vertice , y agregamos el vertice y la lista al diccionario
		ArrayList<Arco<L>> listadelados = new ArrayList<Arco<L>>();
		digrafo.put(v, listadelados);
		return true;
	}

	//>>>4 funcion que agrega un vertice a partir de su informacion
	public Boolean agregarVertice( String id, V dato, double p ){
		//creamos un objeto vertice e intetamos agregarlo
		Vertice<V> nuevoVertice = new Vertice<V>(id, dato, p);
		return agregarVertice(nuevoVertice);
	}

	//>>>5 funcion para obtener el objeto vertice con ese id en caso de que este
	public Vertice obtenerVertice( String id){

		//chequeamos si algun vertice tiene ese id
		for (Vertice<V> vertices: digrafo.keySet()){
			
			 if (vertices.getId().equals(id)){
			 	return vertices;
			 }
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	//>>>6 funcion que revisa si el vertice con ese id esta
	public Boolean estaVertice(String id){

		//revisamos si existe algun vertice con ese id en el grafo
		for (Vertice<V> vertices: digrafo.keySet()){
			
			 if (vertices.getId().equals(id)){
			 	return true;
			 }
		}
		return false;
	}

	//>>>7 funcion que agrega un objeto tipo arco al grafo
	public Boolean agregarArco(Arco<L> a){


		//vertices no estan
		String verticeA = a.getExtremoInicial().getId();
		String verticeB = a.getExtremoFinal().getId();
		if (!(estaVertice(verticeA)) || !(estaVertice(verticeB))){
			return false;
		}

		//vertices estan pero arco con ese id ya esta
		String id = a.getId();
		
		int i;
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values()){
			 i = arcosDeEsteVertice.size();
			 for(int k = 0; k< i; k++){
			 	if (arcosDeEsteVertice.get(k).getId().equals(id)){
			 		return false;
			 	}
			 }
		}
			
		

		//vertices con el mismo id pero diferentes datos lo cual no es valido ya que hay un solo vertice en el grafo 
		Vertice<V> verticeInicial = obtenerVertice(verticeA);
		Vertice<V> verticeFinal = obtenerVertice(verticeB);
		Vertice<V> vertice1 = a.getExtremoInicial();
		Vertice<V> vertice2 = a.getExtremoFinal();
		if ((verticeInicial.getPeso() != vertice1.getPeso())||(verticeFinal.getPeso() != vertice2.getPeso())){
			return false;
		}
		if ((verticeInicial.getDato() != vertice1.getDato())||(verticeFinal.getDato() != vertice2.getDato())){
			return false;
		}

		//insertar arco
		ArrayList<Arco<L>> lados = digrafo.get(verticeInicial);
		lados.add(a);
		digrafo.put(verticeInicial, lados);
		return true;

	}

	//>>>8 funcion que agrega arco por informacion
	public Boolean agregarArco(String id, L dato, double p, String vInicial, String vFinal){

		Vertice<V> verticeInicial;
		Vertice<V> verticeFinal;
		//si esta el vertice lo marcamos
		if (estaVertice(vInicial)){
			verticeInicial = obtenerVertice(vInicial);	
		//si no salimos
		}else{
			return false;
		}
		//si esta el vertice lo marcamos
		if (estaVertice(vFinal)){
			verticeFinal = obtenerVertice(vFinal);
		//si no salimos
		}else{
			return false;
		}

		//creamos el arco a introducir
		Arco<L> nuevoArco = new Arco<L>(id, dato, p, verticeInicial, verticeFinal);

		//lo insertamos
		return agregarArco(nuevoArco);
	}

	//>>>9 funcion que busca un arco por su id
	public Arco<L> obtenerArco(String id){

		int i;
		//buscamos en todo el grafo si existe un arco con ese id
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values()){
			 i = arcosDeEsteVertice.size();
			 for(int k = 0; k< i; k++){
			 	if (arcosDeEsteVertice.get(k).getId().equals(id)){
			 		return arcosDeEsteVertice.get(k);
			 	}
			 }
		}

		throw new NoSuchElementException("No se encontro el arco");
	}

	//>>>10 funcion que elimina arco por is
	public Boolean eliminarArco(String id){

		int i;
		//buscamos si existe ese arco en el garfo
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values()){
			 i = arcosDeEsteVertice.size();
			 for(int k = 0; k< i; k++){
			 	if (arcosDeEsteVertice.get(k).getId().equals(id)){
			 		arcosDeEsteVertice.remove(k);
			 		return true;
			 	}
			 }
		}
		return false;
	}

	//>>>11 funcion que revisa si un lado esta por el id de los vertices
	public Boolean estaLado(String u, String v){

		//si ambos vertices estan lo buscamos
		if(estaVertice(u) && estaVertice(v)){
			//si el lado esta , esta en la lista de lados del vertice u
			Vertice<V> ver = obtenerVertice(u);
			ArrayList<Arco<L>> listadelados = digrafo.get(ver);
			int n = listadelados.size();
			//buscamos en la lista de lados de u
			for(int k =0; k<n; k++){
				if (listadelados.get(k).getExtremoFinal().getId().equals(v)){
					return true;
				}
			}
		}
		return false;

	}

	//>>>12 funcion que elimina vertice por id
	public Boolean eliminarVertice(String id){

		//solo procedemos si el vertice esta en el grafo
		if (estaVertice(id)){

			Vertice<V> eliminar = obtenerVertice(id);//buscamos el objeto tipo vertice
			digrafo.remove(eliminar);//lo sacamos del grafo
			int i;
			//sacamos todo los arcos relacionados a ese vertice que sobrevivieron
			for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values()){
				 i = arcosDeEsteVertice.size();
				 for(int k = i-1; k>=0; k--){
				 	
				 	
			 		if (arcosDeEsteVertice.get(k).getExtremoFinal().getId().equals(id)){
			 			arcosDeEsteVertice.remove(k);
			 			
				 	}
				 }
			}
			
			return true;
		
		}
		return false;
	}

	//>>>13 funcion que devuelve una lista de objetos tipo vertice en el grafo
	public ArrayList<Vertice<V>> vertices(){

		ArrayList<Vertice<V>> listaVertices = new ArrayList<Vertice<V>>();
		
		for (Vertice<V> vertice: digrafo.keySet()){
			listaVertices.add(vertice);
		}
		return listaVertices;
	}

	//>>>14 funcion que devuelve una lista de lados en el grafo
	public ArrayList<Lado<L>> lados(){

		ArrayList<Lado<L>> listaLados = new ArrayList<Lado<L>>();
		int i;
		for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values()){
				 i = arcosDeEsteVertice.size();
				 for(int k = 0; k< i; k++){
			 		listaLados.add(arcosDeEsteVertice.get(k));//aqui agregamos cada arco
			 			
				 }
		}
			

		return listaLados;

	}

	//>>>15 funcion que devuelve el grado de un vertice existente en el grafo
	public int grado(String id){

		//procedemos solo si el vertice esta en el grafo
		if (estaVertice(id)){
			ArrayList<Lado<L>> listaLados = new ArrayList<Lado<L>>();
			int i;
			int grado = 0;//grado comienza en 0
			for (ArrayList<Arco<L>> arcosDeEsteVertice: digrafo.values()){//revisamos cada arco
				 i = arcosDeEsteVertice.size();
				 for(int k = 0; k< i; k++){
			 		if(arcosDeEsteVertice.get(k).getExtremoFinal().getId().equals(id) || arcosDeEsteVertice.get(k).getExtremoInicial().getId().equals(id)){
			 			grado = grado + 1;//si el arco tiene el vertice sumamos 1
			 			if(arcosDeEsteVertice.get(k).getExtremoFinal().getId().equals(id) && arcosDeEsteVertice.get(k).getExtremoInicial().getId().equals(id)){
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

	//>>>17 funcion que toma una clave vertice y devuelve una lista de arcos incidentes del vertice en caso de que el vertice este
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

	//>>>18 funcion que toma una clave de vertice y devuelve el grado exterior en caso de que este
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

	//>>>19 funcion que toma la clave de un vertice y calcula el grado interno del vertice si esta en el grafo
	public int gradoInterior(String id){

		//procedemos si el vertice esta
		if (estaVertice(id)) {
			int grado = grado(id) - gradoExterior(id);//segun la teoria el grado interno es igual al grado menos el grado exterior
			return grado;
			
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	//>>>20 funcion que toma la clave de un vertice y devuelve una lista de vertices sucesores si el vertice esta en el grafo
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

	//>>>21 funcion analoga a "sucesores" pero calcula predecesores
	public ArrayList<Vertice<V>> predecesores(String id){
		
		ArrayList<Vertice<V>> predecesores = new ArrayList<Vertice<V>>();

		if(estaVertice(id)){
			String idadyacente;
			for (Vertice<V> vertice: digrafo.keySet()){
				idadyacente = vertice.getId();
				if (estaLado(idadyacente, id) ){
					predecesores.add(vertice);
				}

			}
			return predecesores;
		}
		throw new NoSuchElementException("No se encontro el vertice");
	}

	//>>>22 funcion que convierte  un grafo en String para impresion
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

	//>>>23 funcion que clona un grafo en un nuevo objeto grafo
	public Grafo<V, L> clone(){

		GrafoDirigido<V, L> clon = new GrafoDirigido<V, L>();
		clon.digrafo = digrafo;
		return clon;

	}
}