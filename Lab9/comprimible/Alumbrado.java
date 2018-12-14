import java.util.Hashtable;
import java.util.ArrayList;
import java.io.IOException;

public class Alumbrado{
	
	/**
	* 
	* funcion que toma el costo de todas las aristas del grafo sumadas, y un grafo al que se le aplico prim
	* y calcula la suma de las aristas del minimo cobertor calculado por  prim y devuelve la resta del peso
	* de todas menos las del minimo cobertor.
	* @param es el costo sumado de todas las aristas del grafo
	* @param el grafo despues de calcular el minimo cobertor
	**/
	public static double ahorrado(double costoOriginal, GrafoNoDirigido<Integer,Integer> nuevoGrafo){

		double costoNuevo = 0.0;//inicializamos el costo de la suma de las aristas del arbol m c
		ArrayList<Vertice<Integer>> vertices = nuevoGrafo.vertices();//buscamos los vertices del grafo
		for(Vertice<Integer> ver : vertices){//por cada vertice
			Arista<Integer> arista = ver.getAristaPredecesora();//buscamos la arista que lo involucra en el arbol minimo cobertor
			if(arista != null){//si la tiene
				costoNuevo = costoNuevo + arista.getPeso();//agregamos su costo
			}
		}

		return costoOriginal - costoNuevo;//finalmente calculamos el ahorro y lo retornamos 
	}

	/**
	* main a ejecutar
	**/
	public static void main(String[] args)
	throws IOException
	{

		//Caso de formato erroneo
		if(args.length != 1){
			System.out.println("Formato invalido");
			System.out.println("Formato valido: >java Alumbrado <instancia>");
			return;
		}

		//inicializamos un nuevo grafo
		GrafoNoDirigido<Integer,Integer> nuevoGrafo = new GrafoNoDirigido<Integer, Integer>();

		//intentamos cargarlo
		if (!nuevoGrafo.cargarGrafo(args[0])){
			System.out.println("No se cargo... puede deberse a errores de formato");
			return;
		}

		ArrayList<Arista<Integer>> aristas = nuevoGrafo.aristas();//cargamos las aristas del grafo
		double costoOriginal = 0.0;//inicializamos la variable que contendra el largo de las calles
		for(Arista<Integer> arista: aristas){
			costoOriginal = costoOriginal + arista.getPeso();//sumamos el largo de cada calle
		}

		PrimAlgoritmo repertorio = new PrimAlgoritmo();//cargamos un reportio con el algoritmo prim

		repertorio.prim(nuevoGrafo);//calculamos un arbol minimo cobertor del grafo dado
		String ahorro = String.valueOf(ahorrado(costoOriginal, nuevoGrafo));//calculamos el ahorro y lo volvemos un String para imprimir
		String[] decimales = ahorro.split("\\.");//hacemos un split por "." para detectar si el numero resultante puede ser convertido a entero

		if(decimales[1].equals("0")){//si puede ser entero imprimimos solo  + la parte entera
			System.out.println(decimales[0] + " UT ahorrados");//imprimimos la parte entera junto al mensaje
		}else{
			System.out.println(ahorro + " UT ahorrados");//imprimimos el double junto al mensaje
		}

	}
}