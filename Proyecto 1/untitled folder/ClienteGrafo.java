import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class ClienteGrafo{

	Boolean usando = true;//VARIABLE: ayuda a determinar cuando se desea salir del programa
	/**
	 * funcion que que ayuda a terminar el programa
	 */
	public void terminar(){
		usando = false;
	}

	/**
	 * funcion que muestra el menu en caso de trabajar con un di
	 * y realiza procesos logicos de a cuerdo a la operacion seleccionada
	 * @param scan lee input del usuario
	 * @param grafo grafo sobre el que se realizan las operaciones
	 * @param transformer cambia datos de un tipo a otro
	 * @param transformerarco cambia datos de un tipo a otro
	 */	
	public <V, L> void menuDirigido(Scanner scan, GrafoDirigido<V,L> grafo, Transformer<String, V> transformer,  Transformer<String, L> transformerarco){
		//Imprimimos el menu
		System.out.println("Que operacion desea realizar sobre su nuevo grafo dirigido? presione el numero correspondiente a la operacion");
		System.out.println("1. numero de vertices");
		System.out.println("2. numero de lados");
		System.out.println("3. agregar vertice");
		System.out.println("4. obtener vertice");
		System.out.println("5. esta vertice");
		System.out.println("6. agregar arco");
		System.out.println("7. obtener arco");
		System.out.println("8. esta lado");
		System.out.println("9. eliminar arco");
		System.out.println("10. eliminar vertice");
		System.out.println("11. vertices ");
		System.out.println("12. lados");
		System.out.println("13. grado");
		System.out.println("14. adyacentes");
		System.out.println("15. incidentes");
		System.out.println("16. grado interior");
		System.out.println("17. grado exterior");
		System.out.println("18. sucesores ");
		System.out.println("19. predecesores");
		System.out.println("20. imprimir grafo");
		System.out.println("21. salir");


		int n = scan.nextInt();//leemos la opcion
		int k;				   // variable que usaremos para opciones que involucren numeros, como grado.
		String id;			   // variable que usaremos para las operaciones que requieran id de vertices
		V dato;				   // variable que almacena el dato de un vertice en la operacion necesaria
		Double peso;		   // variable que almacena el peso de un vertice en la operacion necesaria
		String idarco;		   // "					id			"de un arco"							"	
		L datoarco;			   //"					dato		"de un arco"                            "
		Double pesoarco;       //"					peso		"de un arco"						    "
		String vi;			   //"					vertice inicial" de un arco"						"
		String vf;			   //"					vertice final" de un arco"							"
		ArrayList<Vertice<V>> listavers;	//"					"almacena lista de vertices"		    "
		ArrayList<Lado<L>> listalados;		//"					"almacena lista de lados"				"


		//OPCIONES DE OPERACION: todas usan la funcion necesaria de GrafoDirigido
		if(n==1){
			k = grafo.numeroDeVertices();
			System.out.println("El numero de vertices es: " + k);

		}else if(n==2){
			k = grafo.numeroDeLados();
			System.out.println("El numero de lados es: " + k);

		}else if(n==3){
			System.out.println("Dime el id:");
			id = scan.next();
			System.out.println("Dime el dato");
			dato = transformer.transform(scan.next());
			System.out.println("Dime el peso");
			peso = Double.parseDouble(scan.next());
			grafo.agregarVertice(id, dato, peso);
		}else if(n==4){
			System.out.println("Dime el id");
			id = scan.next();
			try{
				System.out.println("El vertice es: " + grafo.obtenerVertice(id).toString());
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}
		}else if(n==5){
			System.out.println("Dime el id:");
			id = scan.next();
			if(grafo.estaVertice(id)){
				System.out.println("Si esta");
			}else{
				System.out.println("No esta");
			}

		}else if(n==6){
			System.out.println("Dime el id:");
			idarco = scan.next();
			System.out.println("Dime el dato");
			datoarco = transformerarco.transform(scan.next());
			System.out.println("Dime el peso");
			pesoarco = Double.parseDouble(scan.next());
			System.out.println("Dime el id inicial:");
			vi = scan.next();
			System.out.println("Dime el id final:");
			vf = scan.next();
			grafo.agregarArco(idarco, datoarco, pesoarco, vi, vf);

		}else if(n==7){
			System.out.println("Dime el id:");
			idarco = scan.next();
			try{
				System.out.println(grafo.obtenerArco(idarco).toString());
			}catch(Exception e){
				System.out.println("No hay tal arco");
			}

		}else if(n==8){
			System.out.println("Dime el id inicial:");
			vi = scan.next();
			System.out.println("Dime el id final:");
			vf = scan.next();
			if(grafo.estaLado(vi, vf)){
				System.out.println("Si esta");
			}else{
				System.out.println("No esta");
			}

		}else if(n==9){
			System.out.println("Dime el id:");
			idarco = scan.next();
			grafo.eliminarArco(idarco);

		}else if(n==10){
			System.out.println("Dime el id:");
			id = scan.next();
			grafo.eliminarVertice(id);

		}else if(n==11){
			listavers = grafo.vertices();
			System.out.println("Los vertices son:");
			k = listavers.size();
			for(int i = 0; i<k; i++){
				System.out.println(listavers.get(i).toString());
			}


		}else if(n==12){
			listalados = grafo.lados();
			System.out.println("Los lados son:");
			k = listalados.size();
			for(int i = 0; i<k; i++){
				System.out.println(listalados.get(i).toString());
			}

		}else if(n==13){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				k = grafo.grado(id);
				System.out.println("El grado es" + k);
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}

		}else if(n==14){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				listavers = grafo.adyacentes(id);
				System.out.println("Los vertices adyacentes son:");
				k = listavers.size();
				for(int i = 0; i<k; i++){
					System.out.println(listavers.get(i).toString());
				}
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}


		}else if(n==15){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				listalados = grafo.incidentes(id);
				System.out.println("Los lados incidentes son:");
				n = listalados.size();
				for(int i = 0; i<n; i++){
					System.out.println(listalados.get(i).toString());
				}
			}catch(Exception e){
				System.out.println("No hay tal vertice");			
			}

		}else if(n==16){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				k = grafo.gradoInterior(id);
				System.out.println("El grado interno es" + k);
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}


		}else if(n==17){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				k = grafo.gradoExterior(id);
				System.out.println("El grado externo es" + k);
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}

		}else if(n==18){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				listavers = grafo.sucesores(id);
				System.out.println("Los vertices sucesores son:");
				k = listavers.size();
				for(int i = 0; i<k; i++){
					System.out.println(listavers.get(i).toString());
				}
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}
		}else if(n==19){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				listavers = grafo.predecesores(id);
				System.out.println("Los vertices predecesores son:");
				k = listavers.size();
				for(int i = 0; i<k; i++){
					System.out.println(listavers.get(i).toString());
				}
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}

		}else if(n ==20){
			System.out.println(grafo.toString());
		
		}else if(n==21){
			System.out.println("Saliendo....");
			terminar();
		}


	}

	/**
	 * funcion que muestra el menu en caso de trabajar con un grafo no dirigido
	 * y realiza procesos logicos de a cuerdo a la operacion seleccionada
	 * @param scan lee input del usuario
	 * @param grafo grafo sobre el que se realizan las operaciones
	 * @param transformer cambia datos de un tipo a otro
	 * @param transformerarista cambia datos de un tipo a otro
	 */	
	public <V, L> void menuNoDirigido(Scanner scan, GrafoNoDirigido<V,L> grafo, Transformer<String, V> transformer,  Transformer<String, L> transformerarista){
		//Imprimimos el menu
		System.out.println("Que operacion desea realizar sobre su nuevo grafo dirigido? presione el numero correspondiente a la operacion");
		System.out.println("1. numero de vertices");
		System.out.println("2. numero de lados");
		System.out.println("3. agregar vertice");
		System.out.println("4. obtener vertice");
		System.out.println("5. esta vertice");
		System.out.println("6. agregar arista");
		System.out.println("7. obtener arista");
		System.out.println("8. esta lado");
		System.out.println("9. eliminar arista");
		System.out.println("10. eliminar vertice");
		System.out.println("11. vertices ");
		System.out.println("12. lados");
		System.out.println("13. grado");
		System.out.println("14. adyacentes");
		System.out.println("15. incidentes");
		System.out.println("16. imprimir grafo");
		System.out.println("17. salir");


		int n = scan.nextInt();//leemos la opcion
		int k;				   // variable que usaremos para opciones que involucren numeros, como grado.
		String id;			   // variable que usaremos para las operaciones que requieran id de vertices
		V dato;				   // variable que almacena el dato de un vertice en la operacion necesaria
		Double peso;		   // variable que almacena el peso de un vertice en la operacion necesaria
		String idarista;		   // "					id			"de una arista"							"	
		L datoarista;			   //"					dato		"de una arista"                            "
		Double pesoarista;       //"					peso		"de una arista"						    "
		String vi;			   //"					vertice inicial" de una arista"						"
		String vf;			   //"					vertice final" de una arista"							"
		ArrayList<Vertice<V>> listavers;	//"					"almacena lista de vertices"		    "
		ArrayList<Lado<L>> listalados;		//"					"almacena lista de lados"				"


		//OPCIONES DE OPERACION: todas usan la funcion necesaria de GrafoNoDirigido
		if(n==1){
			k = grafo.numeroDeVertices();
			System.out.println("El numero de vertices es: " + k);

		}else if(n==2){
			k = grafo.numeroDeLados();
			System.out.println("El numero de lados es: " + k);

		}else if(n==3){
			System.out.println("Dime el id:");
			id = scan.next();
			System.out.println("Dime el dato");
			dato = transformer.transform(scan.next());
			System.out.println("Dime el peso");
			peso = Double.parseDouble(scan.next());
			grafo.agregarVertice(id, dato, peso);
		}else if(n==4){
			System.out.println("Dime el id");
			id = scan.next();
			try{
				System.out.println("El vertice es: " + grafo.obtenerVertice(id).toString());
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}
		}else if(n==5){
			System.out.println("Dime el id:");
			id = scan.next();
			if(grafo.estaVertice(id)){
				System.out.println("Si esta");
			}else{
				System.out.println("No esta");
			}

		}else if(n==6){
			System.out.println("Dime el id:");
			idarista = scan.next();
			System.out.println("Dime el dato");
			datoarista = transformerarista.transform(scan.next());
			System.out.println("Dime el peso");
			pesoarista = Double.parseDouble(scan.next());
			System.out.println("Dime el id inicial:");
			vi = scan.next();
			System.out.println("Dime el id final:");
			vf = scan.next();
			grafo.agregarArista(idarista, datoarista, pesoarista, vi, vf);

		}else if(n==7){
			System.out.println("Dime el id:");
			idarista = scan.next();
			try{
				System.out.println(grafo.obtenerArista(idarista).toString());
			}catch(Exception e){
				System.out.println("No hay tal arista");
			}

		}else if(n==8){
			System.out.println("Dime el id inicial:");
			vi = scan.next();
			System.out.println("Dime el id final:");
			vf = scan.next();
			if(grafo.estaLado(vi, vf)){
				System.out.println("Si esta");
			}else{
				System.out.println("No esta");
			}

		}else if(n==9){
			System.out.println("Dime el id:");
			idarista = scan.next();
			grafo.eliminarArista(idarista);

		}else if(n==10){
			System.out.println("Dime el id:");
			id = scan.next();
			grafo.eliminarVertice(id);

		}else if(n==11){
			listavers = grafo.vertices();
			System.out.println("Los vertices son:");
			k = listavers.size();
			for(int i = 0; i<k; i++){
				System.out.println(listavers.get(i).toString());
			}


		}else if(n==12){
			listalados = grafo.lados();
			System.out.println("Los lados son:");
			k = listalados.size();
			for(int i = 0; i<k; i++){
				System.out.println(listalados.get(i).toString());
			}

		}else if(n==13){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				k = grafo.grado(id);
				System.out.println("El grado es" + k);
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}

		}else if(n==14){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				listavers = grafo.adyacentes(id);
				System.out.println("Los vertices adyacentes son:");
				k = listavers.size();
				for(int i = 0; i<k; i++){
					System.out.println(listavers.get(i).toString());
				}
			}catch(Exception e){
				System.out.println("No hay tal vertice");
			}


		}else if(n==15){
			System.out.println("Dime el id:");
			id = scan.next();
			try{
				listalados = grafo.incidentes(id);
				System.out.println("Los lados incidentes son:");
				n = listalados.size();
				for(int i = 0; i<n; i++){
					System.out.println(listalados.get(i).toString());
				}
			}catch(Exception e){
				System.out.println("No hay tal vertice");			
			}

		}else if(n ==16){
			System.out.println(grafo.toString());
		
		}else if(n==17){
			System.out.println("Saliendo....");
			terminar();
		}
	}
////////////////////////////////////////////////////////////////////////////////
	/**
	 * funcion que controla recibe la entrada del usuario acerca del tipo de
	 * grafo (dirigido o no), vertice (String,Double,Boolean) y lado
	 * (String,Double,Boolean) con los que se de desea a trabajar y llama a
	 * a menuDirigido o menuNoDirigido dependiendo de las elecciones.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args)
	throws IOException
	{

		ClienteGrafo profe = new ClienteGrafo();	//objeto cliente para llamar a los metodos
		Scanner scan = new Scanner(System.in).useDelimiter("\n");
		//Transformadores del tipo generico respectivo:
		Transformer<String, Double> transformadorarcoDouble= new TransformarDouble();
		Transformer<String, Double> transformadorDouble= new TransformarDouble();
		Transformer<String, Boolean> transformadorarcoBoolean= new TransformarBoolean();
		Transformer<String, Boolean> transformadorBoolean= new TransformarBoolean();
		Transformer<String, String> transformadorarcoString= new TransformarString();
		Transformer<String, String> transformadorString= new TransformarString();

		if(args.length == 0){//CASO CREAR GRAFO

			System.out.println("Que tipo de dato de vertice desea almacenar? B(Boolean), D(Double) o S(String)?");
			String tipovertice = scan.next();
			System.out.println("Que tipo de dato de lado desea almacenar? B(Boolean), D(Double) o S(String)?");
			String tipolado = scan.next();
			System.out.println("Que tipo de grafo desea almacenar? D(Dirigido) o N(No Dirigido)?");
			String tipografo = scan.next();
			

			if(tipografo.equals("D")){//DIRIGIDO ----> dividimos por casos

				if(tipovertice.equals("S") && tipolado.equals("S")){
					GrafoDirigido<String, String> nuevoDigrafo = new GrafoDirigido<String, String>();
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorString, transformadorarcoString);
						
					}
				}else if(tipovertice.equals("S") && tipolado.equals("D")){
					GrafoDirigido<String, Double> nuevoDigrafo = new GrafoDirigido<String, Double>();
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorString, transformadorarcoDouble);
						
					}


				}else if(tipovertice.equals("S") && tipolado.equals("B")){
					GrafoDirigido<String, Boolean> nuevoDigrafo = new GrafoDirigido<String, Boolean>();
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorString, transformadorarcoBoolean);
						
					}
				}else if(tipovertice.equals("D") && tipolado.equals("S")){
					GrafoDirigido<Double, String> nuevoDigrafo = new GrafoDirigido<Double, String>();
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorDouble, transformadorarcoString);
						
					}
				}else if(tipovertice.equals("D") && tipolado.equals("D")){
					System.out.println("entro");
					GrafoDirigido<Double, Double> nuevoDigrafo = new GrafoDirigido<Double, Double>();
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorDouble, transformadorarcoDouble);
						
					}
				}else if(tipovertice.equals("D") && tipolado.equals("B")){
					GrafoDirigido<Double, Boolean> nuevoDigrafo = new GrafoDirigido<Double, Boolean>();
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorDouble, transformadorarcoBoolean);
						
					}
				}else if(tipovertice.equals("B") && tipolado.equals("S")){
					GrafoDirigido<Boolean, String> nuevoDigrafo = new GrafoDirigido<Boolean, String>();
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorBoolean, transformadorarcoString);
						
					}
				}else if(tipovertice.equals("B") && tipolado.equals("D")){
					GrafoDirigido<Boolean, Double> nuevoDigrafo = new GrafoDirigido<Boolean, Double>();
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorBoolean, transformadorarcoDouble);
						
					}
				}else if(tipovertice.equals("B") && tipolado.equals("B")){
					GrafoDirigido<Boolean, Boolean> nuevoDigrafo = new GrafoDirigido<Boolean, Boolean>();
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorBoolean, transformadorarcoBoolean);
						
					}
				}else{
					System.out.println("Formato erroneo, cerrando...");
					return;
				}
			}
////////////////////////////////////////////////////////////////////////////////
			else if(tipografo.equals("N"))
			{
				if(tipovertice.equals("S") && tipolado.equals("S"))
				{
					GrafoNoDirigido<String, String> nuevoGrafoNoDirigido = new GrafoNoDirigido<String, String>();
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorString, transformadorarcoString);	
					}
				}
				else if(tipovertice.equals("S") && tipolado.equals("D"))
				{
					GrafoNoDirigido<String, Double> nuevoGrafoNoDirigido = new GrafoNoDirigido<String, Double>();
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorString, transformadorarcoDouble);	
					}
				}
				else if(tipovertice.equals("S") && tipolado.equals("B"))
				{
					GrafoNoDirigido<String, Boolean> nuevoGrafoNoDirigido = new GrafoNoDirigido<String, Boolean>();
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorString, transformadorarcoBoolean);
					}
				}
				else if(tipovertice.equals("D") && tipolado.equals("S"))
				{
					GrafoNoDirigido<Double, String> nuevoGrafoNoDirigido = new GrafoNoDirigido<Double, String>();
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorDouble, transformadorarcoString);
					}
				}
				else if(tipovertice.equals("D") && tipolado.equals("D"))
				{
					System.out.println("entro");
					GrafoNoDirigido<Double, Double> nuevoGrafoNoDirigido = new GrafoNoDirigido<Double, Double>();
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorDouble, transformadorarcoDouble);	
					}
				}
				else if(tipovertice.equals("D") && tipolado.equals("B"))
				{
					GrafoNoDirigido<Double, Boolean> nuevoGrafoNoDirigido = new GrafoNoDirigido<Double, Boolean>();
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorDouble, transformadorarcoBoolean);
					}
				}
				else if(tipovertice.equals("B") && tipolado.equals("S"))
				{
					GrafoNoDirigido<Boolean, String> nuevoGrafoNoDirigido = new GrafoNoDirigido<Boolean, String>();
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorBoolean, transformadorarcoString);
					}
				}
				else if(tipovertice.equals("B") && tipolado.equals("D"))
				{
					GrafoNoDirigido<Boolean, Double> nuevoGrafoNoDirigido = new GrafoNoDirigido<Boolean, Double>();
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorBoolean, transformadorarcoDouble);	
					}
				}
				else if(tipovertice.equals("B") && tipolado.equals("B"))
				{
					GrafoNoDirigido<Boolean, Boolean> nuevoGrafoNoDirigido = new GrafoNoDirigido<Boolean, Boolean>();
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorBoolean, transformadorarcoBoolean);
					}
				}
				else
				{
					System.out.println("Formato erroneo, cerrando...");
					return;
				}
			}
			else
			{
				System.out.println("Formato erroneo, cerrando...");
				return;
			}

		}else if(args.length ==1){//Caso en que se carga archivo
			
			BufferedReader Lector;
			//Buscamos errores de tipo de archivo
			try{
				Lector = new BufferedReader(new FileReader(args[0]));
			}catch(Exception e){
				System.out.println("No hay tal archivo");
				return;
			}

			String tipovertice = Lector.readLine();//leimos V
			String tipolado = Lector.readLine();// leimos L
			String tipografo = Lector.readLine();// leimos O
			
			if(tipografo.equals("D")){//DIRIGIDO ----> dividimos por casos

				if(tipovertice.equals("S") && tipolado.equals("S")){
					GrafoDirigido<String, String> nuevoDigrafo = new GrafoDirigido<String, String>();
					nuevoDigrafo.cargarGrafo(args[0], transformadorString, transformadorarcoString);
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorString, transformadorarcoString);
						
					}
				}else if(tipovertice.equals("S") && tipolado.equals("D")){
					GrafoDirigido<String, Double> nuevoDigrafo = new GrafoDirigido<String, Double>();
					nuevoDigrafo.cargarGrafo(args[0], transformadorString, transformadorarcoDouble);
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorString, transformadorarcoDouble);
						
					}


				}else if(tipovertice.equals("S") && tipolado.equals("B")){
					GrafoDirigido<String, Boolean> nuevoDigrafo = new GrafoDirigido<String, Boolean>();
					try{
						nuevoDigrafo.cargarGrafo(args[0], transformadorString, transformadorarcoBoolean);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorString, transformadorarcoBoolean);
						
					}
				}else if(tipovertice.equals("D") && tipolado.equals("S")){
					GrafoDirigido<Double, String> nuevoDigrafo = new GrafoDirigido<Double, String>();
					try{
						nuevoDigrafo.cargarGrafo(args[0], transformadorDouble, transformadorarcoString);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorDouble, transformadorarcoString);
						
					}
				}else if(tipovertice.equals("D") && tipolado.equals("D")){
					System.out.println("entro");
					GrafoDirigido<Double, Double> nuevoDigrafo = new GrafoDirigido<Double, Double>();
					try{
						nuevoDigrafo.cargarGrafo(args[0], transformadorDouble, transformadorarcoDouble);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}

					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorDouble, transformadorarcoDouble);
						
					}
				}else if(tipovertice.equals("D") && tipolado.equals("B")){
					GrafoDirigido<Double, Boolean> nuevoDigrafo = new GrafoDirigido<Double, Boolean>();
					try{
						nuevoDigrafo.cargarGrafo(args[0], transformadorDouble, transformadorarcoBoolean);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}	
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorDouble, transformadorarcoBoolean);
						
					}
				}else if(tipovertice.equals("B") && tipolado.equals("S")){
					GrafoDirigido<Boolean, String> nuevoDigrafo = new GrafoDirigido<Boolean, String>();
					try{
						nuevoDigrafo.cargarGrafo(args[0], transformadorBoolean, transformadorarcoString);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorBoolean, transformadorarcoString);
						
					}
				}else if(tipovertice.equals("B") && tipolado.equals("D")){
					GrafoDirigido<Boolean, Double> nuevoDigrafo = new GrafoDirigido<Boolean, Double>();
					try{
						nuevoDigrafo.cargarGrafo(args[0], transformadorBoolean, transformadorarcoDouble);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorBoolean, transformadorarcoDouble);
						
					}
				}else if(tipovertice.equals("B") && tipolado.equals("B")){
					GrafoDirigido<Boolean, Boolean> nuevoDigrafo = new GrafoDirigido<Boolean, Boolean>();
					try{
						nuevoDigrafo.cargarGrafo(args[0], transformadorBoolean, transformadorarcoBoolean);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando){
						profe.menuDirigido(scan, nuevoDigrafo, transformadorBoolean, transformadorarcoBoolean);
						
					}
				}else{
					System.out.println("Formato erroneo, cerrando...");
					return;
				}
////////////////////////////////////////////////////////////////////////////////
			// Caso No Dirigido
			}else if(tipografo.equals("N"))
			{
				if(tipovertice.equals("S") && tipolado.equals("S"))
				{
					GrafoNoDirigido<String, String> nuevoGrafoNoDirigido = new GrafoNoDirigido<String, String>();
					nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorString, transformadorarcoString);
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorString, transformadorarcoString);
					}
				}
				else if(tipovertice.equals("S") && tipolado.equals("D"))
				{
					GrafoNoDirigido<String, Double> nuevoGrafoNoDirigido = new GrafoNoDirigido<String, Double>();
					nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorString, transformadorarcoDouble);
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorString, transformadorarcoDouble);
					}
				}
				else if(tipovertice.equals("S") && tipolado.equals("B"))
				{
					GrafoNoDirigido<String, Boolean> nuevoGrafoNoDirigido = new GrafoNoDirigido<String, Boolean>();
					try{
						nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorString, transformadorarcoBoolean);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorString, transformadorarcoBoolean);
					}
				}
				else if(tipovertice.equals("D") && tipolado.equals("S"))
				{
					GrafoNoDirigido<Double, String> nuevoGrafoNoDirigido = new GrafoNoDirigido<Double, String>();
					try{
						nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorDouble, transformadorarcoString);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorDouble, transformadorarcoString);
					}
				}
				else if(tipovertice.equals("D") && tipolado.equals("D"))
				{
					System.out.println("entro");
					GrafoNoDirigido<Double, Double> nuevoGrafoNoDirigido = new GrafoNoDirigido<Double, Double>();
					try{
						nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorDouble, transformadorarcoDouble);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorDouble, transformadorarcoDouble);
					}
				}
				else if(tipovertice.equals("D") && tipolado.equals("B"))
				{
					GrafoNoDirigido<Double, Boolean> nuevoGrafoNoDirigido = new GrafoNoDirigido<Double, Boolean>();
					try{
						nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorDouble, transformadorarcoBoolean);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}	
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorDouble, transformadorarcoBoolean);
					}
				}
				else if(tipovertice.equals("B") && tipolado.equals("S"))
				{
					GrafoNoDirigido<Boolean, String> nuevoGrafoNoDirigido = new GrafoNoDirigido<Boolean, String>();
					try{
						nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorBoolean, transformadorarcoString);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorBoolean, transformadorarcoString);
					}
				}
				else if(tipovertice.equals("B") && tipolado.equals("D"))
				{
					GrafoNoDirigido<Boolean, Double> nuevoGrafoNoDirigido = new GrafoNoDirigido<Boolean, Double>();
					try{
						nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorBoolean, transformadorarcoDouble);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorBoolean, transformadorarcoDouble);
					}
				}
				else if(tipovertice.equals("B") && tipolado.equals("B"))
				{
					GrafoNoDirigido<Boolean, Boolean> nuevoGrafoNoDirigido = new GrafoNoDirigido<Boolean, Boolean>();
					try{
						nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorBoolean, transformadorarcoBoolean);
					}catch(Exception e){
						System.out.println("Error de formato, cerrando...");
						return;
					}
					while(profe.usando)
					{
						profe.menuNoDirigido(scan, nuevoGrafoNoDirigido, transformadorBoolean, transformadorarcoBoolean);
					}
				}
				else
				{
					System.out.println("Formato erroneo, cerrando...");
					return;
				}
			}else{
				System.out.println("Formato erroneo, cerrando...");
				return;
			}
		}else{//Formato erroneo
			System.err.println("Uso: java Cliente <nombreArchivo> (para cargarGrafo grafo) ");
			System.err.println("Uso: java Cliente (para crear grafo) ");
				return;
		}

	}

}