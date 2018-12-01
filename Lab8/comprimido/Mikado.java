import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Mikado{

	public static Boolean capas(GrafoDirigido<Integer, Integer> grafoCaso){

		ArrayList<ArrayList<Vertice<Integer>>> particionesCapas = new ArrayList<ArrayList<Vertice<Integer>>>();
		ArrayList<Vertice<Integer>> primeraCapa = new ArrayList<Vertice<Integer>>();

		//inicializamos la primera capa 
		boolean ciclo = true;
		ArrayList<Vertice<Integer>> vertices = grafoCaso.vertices();
		for (Vertice<Integer> vert : vertices){

			if ( grafoCaso.gradoInterior(vert.getId()) == 0){
				primeraCapa.add(vert);
				ciclo = false;
			}
		}

		if(ciclo){
			System.out.println("IMPOSIBLE");
			return false;
		}

		ArrayList<Vertice<Integer>> capai = primeraCapa;

		while( grafoCaso.numeroDeVertices() != 0){

			particionesCapas.add(capai);

			for (Vertice<Integer> vert: capai){//eliminamos del grafo los vertices con grado 0
				grafoCaso.eliminarVertice(vert.getId());
			}

			capai = new ArrayList<Vertice<Integer>>();
			ciclo = true;
			vertices = grafoCaso.vertices();
			for (Vertice<Integer> vert : vertices){

				if ( grafoCaso.gradoInterior(vert.getId()) == 0){
					capai.add(vert);
					ciclo = false;
				}
			}

			if(ciclo && grafoCaso.numeroDeVertices() != 0){
				System.out.println("IMPOSIBLE");
				return false;
			}
		}

		Collections.reverse(particionesCapas);
		for (ArrayList<Vertice<Integer>> capa: particionesCapas){
			for ( Vertice<Integer> vert: capa){
				System.out.println(vert.getId());
			}
		}
		return true;


	}

	public static void main(String[] args)
	throws IOException
	{

		String archivo = args[0];
		BufferedReader lector = new BufferedReader(new FileReader(archivo));

		String line = lector.readLine(); 

		int numeroLine = 1;

		//Transformadores para la conversion de archivo a grafo
		Transformer<String, Integer> transformadorarcoInt= new TransformarInteger();
		Transformer<String, Integer> transformadorInt= new TransformarInteger();

		while(true){
			
			String[] informacioncaso = line.split(" ");
			int nroLados = Integer.parseInt(informacioncaso[1]);
			int nroVertices = Integer.parseInt(informacioncaso[0]);

			if( nroLados == 0  && nroVertices == 0){

				break;

			}else{
				//creamos el grafo de la universidad base
				GrafoDirigido<Integer, Integer> nuevoGrafoDirigido = new GrafoDirigido<Integer, Integer>();
				//cargamos el grafo de la universidad base
				nuevoGrafoDirigido.cargarGrafo(args[0], numeroLine, transformadorInt, transformadorarcoInt);
				capas(nuevoGrafoDirigido);

			}

			for(int x = numeroLine; x < numeroLine + nroLados + 1; x++){//saltamos lineas hasta llegar al caso
   				line = lector.readLine();
			}
			numeroLine = numeroLine + nroLados + 1;
		
		}

	}
}