import java.util.ArrayList;

public class Prueba
{
	private static ArrayList<Vertice<String>>  listaVertices = new ArrayList<Vertice<String>>();
	private static ArrayList<Arco<Double>>  listaArcos = new ArrayList<Arco<Double>>();
	private static ArrayList<Arista<Boolean>>  listaAristas = new ArrayList<Arista<Boolean>>();
	public static void main(String[] args)
	{
		
		///VERTICES
		Vertice<String> ver = new Vertice<String>("Venezuela", "nhjrbf", 9999.99);

		System.out.println(ver.getId());
		System.out.println(ver.getDato());
		System.out.println(ver.getPeso());
		System.out.println(ver.toString());

		Vertice<Double> ver2 = new Vertice<Double>("Colombia", 69.69, 9999.99);

		System.out.println(ver2.getId());
		System.out.println(ver2.getDato());
		System.out.println(ver2.getPeso());
		System.out.println(ver2.toString());

		Vertice<Boolean> ver3 = new Vertice<Boolean>("Venezuela", true || false, 9999.99);

		System.out.println(ver3.getId());
		System.out.println(ver3.getDato());
		System.out.println(ver3.getPeso());
		System.out.println(ver3.toString());

		for(double i = 0.00; i<10.00; i++){
			Vertice<String> x = new Vertice<String>("Venezuela", String.valueOf(i), i);
			listaVertices.add(x);
		}

		for(int i = 0; i<10; i++){
			System.out.println(listaVertices.get(i).toString());
		}

////////////////////////////////////////////////////////////////////////////////

		////ARCOS
		System.out.println("Arcos");
		Arco<String> arc = new Arco<String>("por Tierra", "se llega por tierra ", 20.00, ver, ver2);

		System.out.println(arc.getId());
		System.out.println(arc.getDato());
		System.out.println(arc.getPeso());
		System.out.println(arc.getExtremoInicial().getId());
		System.out.println(arc.getExtremoFinal());
		System.out.println(arc.toString());

		for(int i = 0; i<10; i++){
			Arco<Double> x = new Arco<Double>("Venezuela a Venezuela", 6.66, 20.00, listaVertices.get(i), listaVertices.get(i));
			listaArcos.add(x);
		}

		for(int i = 0; i<10; i++){
			System.out.println(listaArcos.get(i).toString());
			System.out.println(listaArcos.get(i).getExtremoInicial());
			System.out.println(listaArcos.get(i).getExtremoFinal());
		}

		///ARISATS
		System.out.println("Aristas");
		Arista<String> aris = new Arista<String>("por Mar", "se va y viene por mar ", 10.00, ver, ver2);

		System.out.println(aris.getId());
		System.out.println(aris.getDato());
		System.out.println(aris.getPeso());
		System.out.println(aris.getExtremo1().getId());
		System.out.println(aris.getExtremo2());
		System.out.println(aris.toString());

		Boolean z = true;
		Boolean y = false;
		Boolean w;
		for(int i = 0; i<10; i++){

			Arista<Boolean> x = new Arista<Boolean>("Venezuela a Venezuela", z, 20.00, listaVertices.get(i), listaVertices.get(i));
			listaAristas.add(x);
			w = z;
			z = y;
			y = w;
		}

		for(int i = 0; i<10; i++){
			System.out.println(listaAristas.get(i).toString());
			System.out.println(listaAristas.get(i).getExtremo1());
			System.out.println(listaAristas.get(i).getExtremo2());
		}

////////////////////////////////////////////////////////////////////////////////

		///Grafo dirigido
		System.out.println("PRUEBAS GRAFO DIRIGIDO");

		GrafoDirigido<String, String> primerdigrafo = new GrafoDirigido<String, String>();
		//numero de lados y vertices
		System.out.println(primerdigrafo.numeroDeVertices());
		System.out.println(primerdigrafo.numeroDeLados());
		//agregar vertice
		primerdigrafo.agregarVertice(ver);
		primerdigrafo.agregarVertice("Guatemala", "esta lejos", 7777.77);
		primerdigrafo.agregarVertice("Alemania", "esta lejos", 7777.77);
		primerdigrafo.agregarVertice("Alemania", "esta lejos", 7777.77);
		primerdigrafo.agregarVertice(ver);
		primerdigrafo.agregarVertice("Narnia", "esta en verdad muy lejos", 7777.77);
		//obterner vertice
		Vertice<String> verticeobtenido;
		verticeobtenido = primerdigrafo.obtenerVertice("Narnia");
		System.out.println(verticeobtenido.getId());
		
		try{
			Vertice<String> nuevoobtenido;
			nuevoobtenido = primerdigrafo.obtenerVertice("Hades");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		//esta vertice

		if (primerdigrafo.estaVertice("Narnia")){
			System.out.println("Narnia esta!!!");
		}
		if (!(primerdigrafo.estaVertice("Hades"))){
			System.out.println("No esta Hades");
		}

		//agregararco
		primerdigrafo.agregarArco("Alemania a Narnia", "se puede ir por tierra", 5.5555, "Alemania", "Narnia");
		primerdigrafo.agregarArco("Alemania a Narnia", "se puede ir por tierra", 5.5555, "Alemania", "Narnia");
		primerdigrafo.agregarArco("Narnia a Alemania", "se puede ir por tierra", 5.5555, "Narnia", "Alemania");
		primerdigrafo.agregarArco("Alemania a Alemania", "se puede ir por tierra", 5.5555, "Alemania", "Alemania");
		primerdigrafo.agregarArco("Alemania a Alemania", "se puede ir por tierra", 5.5555, "Alemania", "Alemania");
		primerdigrafo.agregarArco("Alemania a Alemania", "se puede ir por tierra", 5.5555, "Guatemala", "Alemania");
		primerdigrafo.agregarArco("Narnia a Narnia", "se puede ir por tierra", 5.5555, "Narnia", "Narnia");
		primerdigrafo.agregarArco("Guate a Guate", "se puede ir por tierra", 5.5555, "Guatemala", "Guatemala");
		//obtener arco
		try{
			Arco<String> nuevoarcoobtenido;
			nuevoarcoobtenido = primerdigrafo.obtenerArco("Alemania a Guatemala");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//eliminar arco
		primerdigrafo.eliminarArco("Alemania a Alemania");
		//estalado
		if (primerdigrafo.estaLado("Alemania","Narnia")){
			System.out.println("esta");
		}

		if (!primerdigrafo.estaLado("Guatemala","Narnia")){
			System.out.println("no esta");
		}

		//eliminarvertice
		if (primerdigrafo.eliminarVertice("Alemania")){
			System.out.println("eliminado");
		}
		//lista de vertices
		ArrayList<Vertice<String>> listavers = new ArrayList<Vertice<String>> ();
		listavers = primerdigrafo.vertices();
		int n = listavers.size();
		for (int i = 0; i<n; i++){
			System.out.println(listavers.get(i).getId());
		}

		//lista de lados
		ArrayList<Lado<String>> listalads = new ArrayList<Lado<String>> ();
		listalads = primerdigrafo.lados();
		int m = listalads.size();
		for (int i = 0; i<m; i++){
			System.out.println(listalads.get(i).getId());
		}

		//grados
		primerdigrafo.agregarArco("Narnia a Guatemala", "se puede ir por tierra", 5.5555, "Narnia", "Guatemala");
		primerdigrafo.agregarArco("Narnia a Narnia 2", "se puede ir por tierra", 5.5555, "Narnia", "Narnia");
		primerdigrafo.agregarArco("Guatemala a Narnia", "se puede ir por tierra", 5.5555, "Guatemala", "Narnia");
		primerdigrafo.agregarArco("Venezuela a Narnia", "se puede ir por tierra", 5.5555, "Venezuela", "Narnia");
		int grado = primerdigrafo.grado("Narnia");
		int gradointerno = primerdigrafo.gradoInterior("Narnia");
		int gradoexterno = primerdigrafo.gradoExterior("Narnia");
		System.out.println("grado:" + grado);
		System.out.println("grado interno:" + gradointerno);
		System.out.println("grado externo:" + gradoexterno);

		//adyacentes
		ArrayList<Vertice<String>> listadyacencias = new ArrayList<Vertice<String>> ();
		listadyacencias = primerdigrafo.adyacentes("Narnia");
		n = listadyacencias.size();
		System.out.println("Adyacentes:");
		for (int i = 0; i<n; i++){
			System.out.println(listadyacencias.get(i).getId());
		}

		//sucesores
		ArrayList<Vertice<String>> listasucesores = new ArrayList<Vertice<String>> ();
		listasucesores = primerdigrafo.sucesores("Narnia");
		n = listasucesores.size();
		System.out.println("Sucesores:");
		for (int i = 0; i<n; i++){
			System.out.println(listasucesores.get(i).getId());
		}

		//predecesores
		ArrayList<Vertice<String>> listapredecesores = new ArrayList<Vertice<String>> ();
		listapredecesores = primerdigrafo.predecesores("Narnia");
		n = listapredecesores.size();
		System.out.println("Predecesores:");
		for (int i = 0; i<n; i++){
			System.out.println(listapredecesores.get(i).getId());
		}


		//incidentes
		ArrayList<Lado<String>> listaincidentes = new ArrayList<Lado<String>> ();
		listaincidentes = primerdigrafo.incidentes("Narnia");
		n = listaincidentes.size();
		for (int i = 0; i<n; i ++){
			System.out.println(listaincidentes.get(i).getId());
		}

		//Clon
		Grafo<String, String> segundodigrafo;
		segundodigrafo = primerdigrafo.clone();

		//tostring del grafo
		primerdigrafo.agregarVertice("Narnia", "est en verdad muy lejos", 777.77);
		//obterner vertice
		System.out.println("numero y forma");
		System.out.println(primerdigrafo.toString());
		System.out.println(primerdigrafo.numeroDeVertices());
		System.out.println(primerdigrafo.numeroDeLados());

		//cargar grafo
		GrafoDirigido<Double, Double> tercerdigrafo = new GrafoDirigido<Double, Double>();
		Transformer<String, Double> transformadorarco= new TransformarDouble();
		Transformer<String, Double> transformador= new TransformarDouble();
		try{
			tercerdigrafo.cargarGrafo("ejemplo.txt", transformador, transformadorarco );
		}catch(Exception e){

		}
		System.out.println(tercerdigrafo.toString());
		ArrayList<Lado<Double>> listala = new ArrayList<Lado<Double>> ();
		listala = tercerdigrafo.lados();
		m = listala.size();
		for (int i = 0; i<m; i++){
			System.out.println(listala.get(i).toString());
		}

////////////////////////////////////////////////////////////////////////////////

		///Grafo No Dirigido
		System.out.println("PRUEBAS GRAFO NO DIRIGIDO");

		GrafoNoDirigido<String, String> primergrafonodirigido = new GrafoNoDirigido<String, String>();
		//numero de lados y vertices
		System.out.println(primergrafonodirigido.numeroDeVertices());
		System.out.println(primergrafonodirigido.numeroDeLados());
		//agregar vertice
		primergrafonodirigido.agregarVertice(ver);
		primergrafonodirigido.agregarVertice("Guatemala", "esta lejos", 7777.77);
		primergrafonodirigido.agregarVertice("Alemania", "esta lejos", 7777.77);
		primergrafonodirigido.agregarVertice("Alemania", "esta lejos", 7777.77);
		primergrafonodirigido.agregarVertice(ver);
		primergrafonodirigido.agregarVertice("Narnia", "esta en verdad muy lejos", 7777.77);
		//obterner vertice
		verticeobtenido = primergrafonodirigido.obtenerVertice("Narnia");
		System.out.println(verticeobtenido.getId());
		
		try{
			Vertice<String> nuevoobtenido;
			nuevoobtenido = primergrafonodirigido.obtenerVertice("Hades");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		//esta vertice

		if (primergrafonodirigido.estaVertice("Narnia")){
			System.out.println("Narnia esta!!!");
		}
		if (!(primergrafonodirigido.estaVertice("Hades"))){
			System.out.println("No esta Hades");
		}

		//agregar arista
		primergrafonodirigido.agregarArista("Alemania a Narnia", "se puede ir por tierra", 5.5555, "Alemania", "Narnia");
		primergrafonodirigido.agregarArista("Alemania a Narnia", "se puede ir por tierra", 5.5555, "Alemania", "Narnia");
		primergrafonodirigido.agregarArista("Narnia a Alemania", "se puede ir por tierra", 5.5555, "Narnia", "Alemania");
		primergrafonodirigido.agregarArista("Alemania a Alemania", "se puede ir por tierra", 5.5555, "Alemania", "Alemania");
		primergrafonodirigido.agregarArista("Alemania a Alemania", "se puede ir por tierra", 5.5555, "Alemania", "Alemania");
		primergrafonodirigido.agregarArista("Alemania a Alemania", "se puede ir por tierra", 5.5555, "Guatemala", "Alemania");
		primergrafonodirigido.agregarArista("Narnia a Narnia", "se puede ir por tierra", 5.5555, "Narnia", "Narnia");
		primergrafonodirigido.agregarArista("Guate a Guate", "se puede ir por tierra", 5.5555, "Guatemala", "Guatemala");
		//obtener arista
		try{
			Arista<String> nuevoaristaobtenido;
			nuevoaristaobtenido = primergrafonodirigido.obtenerArista("Alemania a Guatemala");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//eliminar arista
		primergrafonodirigido.eliminarArista("Alemania a Alemania");
		//estalado
		if (primergrafonodirigido.estaLado("Alemania","Narnia")){
			System.out.println("esta");
		}

		if (!primergrafonodirigido.estaLado("Guatemala","Narnia")){
			System.out.println("no esta");
		}

		//eliminarvertice
		if (primergrafonodirigido.eliminarVertice("Alemania")){
			System.out.println("eliminado");
		}
		//lista de vertices
		listavers = new ArrayList<Vertice<String>> ();
		listavers = primergrafonodirigido.vertices();
		n = listavers.size();
		for (int i = 0; i<n; i++){
			System.out.println(listavers.get(i).getId());
		}

		//lista de lados
		listalads = new ArrayList<Lado<String>> ();
		listalads = primergrafonodirigido.lados();
		m = listalads.size();
		for (int i = 0; i<m; i++){
			System.out.println(listalads.get(i).getId());
		}

		//grados
		primergrafonodirigido.agregarArista("Narnia a Guatemala", "se puede ir por tierra", 5.5555, "Narnia", "Guatemala");
		primergrafonodirigido.agregarArista("Narnia a Narnia 2", "se puede ir por tierra", 5.5555, "Narnia", "Narnia");
		primergrafonodirigido.agregarArista("Guatemala a Narnia", "se puede ir por tierra", 5.5555, "Guatemala", "Narnia");
		primergrafonodirigido.agregarArista("Venezuela a Narnia", "se puede ir por tierra", 5.5555, "Venezuela", "Narnia");
		grado = primergrafonodirigido.grado("Narnia");
		System.out.println("grado:" + grado);

		//adyacentes
		listadyacencias = new ArrayList<Vertice<String>> ();
		listadyacencias = primergrafonodirigido.adyacentes("Narnia");
		n = listadyacencias.size();
		System.out.println("Adyacentes:");
		for (int i = 0; i<n; i++){
			System.out.println(listadyacencias.get(i).getId());
		}

		//incidentes
		listaincidentes = new ArrayList<Lado<String>> ();
		listaincidentes = primergrafonodirigido.incidentes("Narnia");
		n = listaincidentes.size();
		for (int i = 0; i<n; i ++){
			System.out.println(listaincidentes.get(i).getId());
		}

		//Clon
		Grafo<String, String> segundografonodirigido;
		segundografonodirigido = primergrafonodirigido.clone();

		//tostring del grafo
		primergrafonodirigido.agregarVertice("Narnia", "est en verdad muy lejos", 777.77);
		//obterner vertice
		System.out.println("numero y forma");
		System.out.println(primergrafonodirigido.toString());
		System.out.println(primergrafonodirigido.numeroDeVertices());
		System.out.println(primergrafonodirigido.numeroDeLados());

		//cargar grafo
		GrafoNoDirigido<Double, Double> tercergrafonodirigido = new GrafoNoDirigido<Double, Double>();
		Transformer<String, Double> transformadorArista= new TransformarDouble();
		transformador= new TransformarDouble();
		try{
			tercergrafonodirigido.cargarGrafo("ejemplo.txt", transformador, transformadorArista );
		}catch(Exception e){

		}
		System.out.println(tercergrafonodirigido.toString());
		listala = new ArrayList<Lado<Double>> ();
		listala = tercergrafonodirigido.lados();
		m = listala.size();
		for (int i = 0; i<m; i++){
			System.out.println(listala.get(i).toString());
		}
	}
}