import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Mesero
{
	public static void main(String[] args)
	throws IOException
	{
		//caso en el que no se escribio archivo ni metodo
    	if(args.length < 2)
    	{
			System.err.println("Uso: java Mesero <instancia> <origen>");
			return;
		}
		//creamos el grafo del restaurante base
		GrafoNoDirigido<Integer,Integer> restaurante = new GrafoNoDirigido<Integer,Integer>();
		//cargamos las mesas y los caminos
		restaurante.cargarGrafo(args[0]);
		//guardamos el vertice inicial
		int cocina = Integer.parseInt(args[1]);
		//aplicamos el metodo de busqueda de caminos mas cortos
		PathFinder bellMan = new PathFinder();
		System.out.println(restaurante.toString());
		bellMan.bellman(restaurante,cocina);
		bellMan.showPath(restaurante,cocina);
	}
}