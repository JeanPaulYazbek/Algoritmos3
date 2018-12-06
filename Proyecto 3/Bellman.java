import java.util.ArrayList;

public class Bellman{

	/**
	 * funcion que toma un grafo y un vertice inicial y una cantidad de gente , luego calcula el camino mas corto a cada alcanzable y deduce
	 * en base a las capacidades de las aristas cuanta gente puede llegar al bano mas cercano y modifica la capacidad de las aristas y vertices
	 * en base a la cantidad de gente que paso.
	 * @param grafoCaso es el grafo del caso particular por ejemplo el del "jueves", cabe destacar que este grafo incluye vertices bano y aristas escaleras
	 * @param verticeInicial id del vertice desde el que se desean encontrar los banos
	 * @param genteCaso cuanta gente se desea trasladar en ese instante
	 * @return	si existe al menos un bano alcanzable devuelve el numero de gente desplazada al bano mas cercano, sino existe devuelve -1 
	 */
	public int bellman(GrafoNoDirigido<Integer, Integer> grafoCaso, String verticeInicial, int genteCaso){

		grafoCaso.resetVertices();//reseteamos los costos y predecesores de todos los vertices para aplicar bellman
		int nrovertices = grafoCaso.vertices().size();//guardamos la cantidad de edificios y banos en el grafo del caso
		Boolean cambio = true;//para saber si el algoritmo sigue encontrando caminos mas cortos
		int i = 1;//para comenzar la iteracion
		grafoCaso.obtenerVertice(verticeInicial).costo = 0.0;//ponemos el costo de llegar al edificio de partida en 0
		ArrayList<Arista<Integer>> caminos = grafoCaso.aristas();//lista con todos los caminos y escaleras en el campus que estan disponibles para su uso
	

		//PROCEDIMIENTO Bellman
		while(i <= nrovertices && cambio){// ponemos la cota de clausura y ademas si no hay cambios en una iteracion salimos para ahorrar iteraciones 

			cambio = false;

			for(Arista<Integer> camino: caminos){//recorremos todos los caminos en el campus
				
				if( camino.getExtremo2().getCosto() > (camino.getExtremo1().getCosto() + camino.getPeso())){//sea (v,w) si el costo de llegar a w es mayor que el de llegar
																											//a v sumado con el costo de un camino entre v y w 
					camino.getExtremo2().costo = camino.getExtremo1().costo + camino.getPeso();//modificamos los costos de los vertices
					camino.getExtremo2().predecesor = camino.getExtremo1();//modificamos por quien se llega al vertice
					camino.getExtremo2().aristaPredecesora = camino;//modificamos por que arista se llega al vertice w
					cambio = true;//marcamos que hubo cambios

				}

				if( camino.getExtremo1().getCosto() > (camino.getExtremo2().getCosto() + camino.getPeso())){//sea (w,v) si el costo de llegar a v es mayor que el de llegar
																											//a w sumado con el costo de un camino entre v y w 
					camino.getExtremo1().costo = camino.getExtremo2().costo + camino.getPeso();//modificamos los costos de los vertices
					camino.getExtremo1().predecesor = camino.getExtremo2();//modificamos por quien se llega al vertice
					camino.getExtremo1().aristaPredecesora = camino;
					cambio = true;//marcamos que hubo cambios

				}
			}
			i = i + 1;


		}

		Boolean hayAgua = false;//usaremos esta variable para ver si hubieron banos con agua accesibles

		ArrayList<Vertice<Integer>> vertices = grafoCaso.vertices();//aqui guardamos todos los lugares del campus
		ArrayList<Vertice<Integer>> sanitariosAlcanzables = new ArrayList<Vertice<Integer>>();//aqui guardaremos los banos alcanzables
		double minimo = 9999999.00;//aqui guardaremos el costo del bano minimo

		//BUSCAMOS el bano con costo minimo para llegar y lo guardamos
		for(Vertice<Integer> lugar: vertices){
		
			if(lugar.getDato() == -1){//si el lugar es un bano 
				if(lugar.getCosto() < minimo){//si su costo es menor o igual al menor actual

					sanitariosAlcanzables.add(lugar);//lo agregamos a los sanitarios alcanzables
					minimo = lugar.getCosto();//cambiamos el minimo 
					hayAgua = true;//marcamos que si existen banos con agua

				}
			}
		}

		if(hayAgua){//si encontramos al menos un bano alcanzable

			int n = sanitariosAlcanzables.size();//posicion del bano alcanzable mas cercano
			Vertice<Integer> sanitarioMasCercano = sanitariosAlcanzables.get(n -1);//bano mas cercano
			int capacidadMinima = sanitarioMasCercano.getAristaPredecesora().getDato();//capacidad de la arista por la que se llega al bano

			if(capacidadMinima > genteCaso){//en caso de que nos sobre espacio
				capacidadMinima = genteCaso;//volvemos la capacidad minima la gente que falta ya que no requerimos mas que eso
			}

			Vertice<Integer> anterior = sanitarioMasCercano.getPredecesor();//vertice por el cual llegamos al bano
			String destino = anterior.getId();

			//aqui buscamos cual arista tenia la capacidad minima en el camino para saber cuanta gente puede pasar
			while (!(anterior.getId().equals(verticeInicial))){//paramos si encontramos el vertice inicial
				if(anterior.getAristaPredecesora().getDato() < capacidadMinima){//si una arista tiene menos capacidad que la anterior minima modificamos
					capacidadMinima = anterior.getAristaPredecesora().getDato();
				}
				anterior = anterior.getPredecesor();//pasamos al siguiente predecesor 
			}

			//modificamos la capacidad del edificio del bano 
			Vertice<Integer> edificioBano = sanitarioMasCercano.getPredecesor();//buscamos el edificio con bano es decir el predecesor del bano
			int nuevaCapacidad = edificioBano.getDato() - capacidadMinima;//guardamos la nueva capacidad
			edificioBano.changeDato(nuevaCapacidad);//cambiamos la capacidad

			String personasTrasladadas = "     " + String.valueOf(capacidadMinima) + " personas a " + destino;//imprimimos cuanta gente fue trasladada y a donde
			System.out.println(personasTrasladadas);

			anterior = sanitarioMasCercano;//volvemos de nuevo al predecesor del bano
			String rutaSanitario = "          Ruta: " ;//inicio de la impresion de la ruta
			ArrayList<String> edificios = new ArrayList<String>(); 

			//aqui modificaremos la capacidad de cada arista segun la cantidad de gente que paso y ademas hacemos los prints necesarios
			while (!(anterior.getId().equals(verticeInicial))){

				anterior.getAristaPredecesora().changeDato( anterior.getAristaPredecesora().getDato() - capacidadMinima); //modificamos la capacidad acordemente
				if( anterior.getAristaPredecesora().getDato() == 0){
					grafoCaso.eliminarArista(anterior.getAristaPredecesora().getId());//eliminamos la arista cuya capacidad se haya sobrepasado
				}
				
				anterior = anterior.predecesor;//pasamos al vertice anterior
				edificios.add(anterior.getId());//agregamos su id a la lista para luego imprimir

			}

			//hacemos un for en orden inverso para construir el string de la ruta 
			int m = edificios.size();
			for(int k = m-1; k!= 0; k--){
				rutaSanitario = rutaSanitario + edificios.get(k) + " - ";
			}

			rutaSanitario = rutaSanitario + edificios.get(0);//agregamos el edificio final
			rutaSanitario = rutaSanitario + "(" + String.valueOf(sanitarioMasCercano.getCosto()) + " m)";//agregamos el costo de llegada al bano
			System.out.println(rutaSanitario);//finalmente imprimimos la ruta al bano

			return capacidadMinima;//retornamos la cantidad de gente que consiguio un  un bano

		
		}else{//si no hay agua

			return -1;//regresamos menos uno para saber que ya no quedan rutas para mas gente
		}		

	}

}