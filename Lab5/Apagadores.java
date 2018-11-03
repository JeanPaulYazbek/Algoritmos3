import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Apagadores{
	//Carga el grafo desde un archivo en forma de matriz de adyacencias y lo retorna como objeto de la clase grafo
	static Grafo cargarGrafoLuces(String nombreArchivo)
			throws IOException
	{
		Grafo matrizLuces = new Grafo();
		
		BufferedReader Lector = new BufferedReader(new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		String[] primeralinea = linea.split(" ");
		int numeroVertices = Integer.parseInt(primeralinea[0]);
		int numeroPuertas = Integer.parseInt(primeralinea[1]);
		int numeroSwitches = Integer.parseInt(primeralinea[2]);

		matrizLuces.agregarVertice(numeroVertices-1);

		for(int i = 0; i < numeroPuertas + 1; i++)
		{
			linea = Lector.readLine();
		}
		
		do
		{
			crearGrafo(linea, (Grafo)matrizLuces, 1);
		
		}
		while((linea = Lector.readLine()) != null);
		
		return matrizLuces;
	}

	static Grafo cargarGrafoPuertas(String nombreArchivo)
			throws IOException
	{
		Grafo matrizPuertas = new Grafo();
		
		BufferedReader Lector = new BufferedReader(new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		String[] primeralinea = linea.split(" ");
		int numeroVertices = Integer.parseInt(primeralinea[0]);
		int numeroPuertas = Integer.parseInt(primeralinea[1]);
		int numeroSwitches = Integer.parseInt(primeralinea[2]);

		matrizPuertas.agregarVertice(numeroVertices-1);
		int contador = numeroPuertas;
		linea = Lector.readLine(); //primera linea de puertas

		do
		{
			crearGrafo(linea, (Grafo)matrizPuertas, 0);
			contador = contador - 1;
			linea = Lector.readLine();
		}
		while(contador != 0);
		
		return matrizPuertas;
	}

	//ayuda a cargar el grafo linea por linea
	//tipo 1 si es arcos y tipo 0 si es aristas
	private static void crearGrafo(String linea, Grafo grafo, int tipo)
		throws IllegalArgumentException
	{	
		//creamos una lista con todos los items separados por espacios
		String[] verticeinfo = linea.split(" ");
		
		
		//convertimos los vertices en int
		int verticeinicial = Integer.parseInt(verticeinfo[0]);
		int verticefinal = Integer.parseInt(verticeinfo[1]);
		//agregamos el Arco a la estructura
		try
		{
			if(tipo == 1)
			{
				grafo.agregarArco(verticeinicial, verticefinal);
			}
			else
			{
				grafo.agregarArista(verticeinicial, verticefinal);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}	
	}

	public static void main(String[] args)
    	throws IOException, IllegalArgumentException
	{
    	//caso en el que no se escribio archivo ni metodo
    	if(args.length < 1){
				System.err.println("Uso: java Opaco <archivo>");
				return;
		}

		Grafo Luces = cargarGrafoLuces(args[0]);
		Grafo Puertas = cargarGrafoPuertas(args[0]);

		backTracking donOpaco = new backTracking();
		donOpaco.empezarCamino(Luces.grafo,Puertas.grafo);
		donOpaco.mostrarCamino();
	}
}