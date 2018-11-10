import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class Hidrocapital{


	public static void CargarGrafoCaso(String nombreArchivo, GrafoNoDirigido<Integer, Integer> grafoOriginal, String edificioInicial, int numeroGente)
	throws IOException
	{	
		BufferedReader Lector = new BufferedReader(new FileReader(nombreArchivo));	//buffer para leer el archivo
		String linea = Lector.readLine();//id del caso
		Bellman minibell = new Bellman();//objeto bellman para llamar a la funcion bellman

		//BUSCAMOS TODOS LOS CASOS 
		while(linea != null){
			GrafoNoDirigido<Integer, Integer> grafoCaso = grafoOriginal.clone();//nuevo grafo clonado para el caso

			String[] lineaidCaso = linea.split(" ");
			String idCaso = lineaidCaso[0];//guardamos el id del caso
			System.out.println(idCaso);

			linea = Lector.readLine(); //Numero de edificios con agua
			String[] lineaNumeroEdificios = linea.split(" ");
			int numeroEdificiosAgua = Integer.parseInt(lineaNumeroEdificios[0]);//guardamos el numero de edificios con agua del caso

			linea = Lector.readLine(); //Numero de caminos no utilizables
			String[] lineaNumeroCaminos = linea.split(" ");
			int numeroCaminoEliminados = Integer.parseInt(lineaNumeroCaminos[0]);//guardamos el numero de caminos no utilizables

			int contadorEdificios = numeroEdificiosAgua;
			int contadorCaminos = numeroCaminoEliminados;


			//AGREGAMOS VERTICES BANO PARA CADA EDIFICIO CON AGUA
			while(contadorEdificios > 0){

				linea = Lector.readLine();
				String[] informacionEdificio = linea.split(" ");

				if(informacionEdificio.length == 1){//si no hay modificacion de pisos en los edificios

					String nombreBaño = "baño" + informacionEdificio[0] ;//creamos el nombre del bano
					grafoCaso.agregarVertice(nombreBaño, -1, -1.00);//agregamos el vertice bano

					String nombreEscalera = "escalera" + informacionEdificio[0];//creamos el nombre de la escalera
					Vertice<Integer> edificio = grafoCaso.obtenerVertice(informacionEdificio[0]);//buscamos el vertice del edificio
					double piso = edificio.getPeso();//sacamos cuanto pisos hay que subir para llegar a un bano
					int capacidad = edificio.getDato();//sacamos cuanta gente puede ir al bano en el edificio

					if(piso < 0.00){//si el piso es negativo debemos hacerlos positivo para als cuentas
						piso = piso * -1.00;
					}

					grafoCaso.agregarArista(nombreEscalera, capacidad, (piso*25.00)  , informacionEdificio[0], nombreBaño);
					//System.out.println(grafoCaso.obtenerArista(nombreEscalera).toString());


				}else if(informacionEdificio.length == 2){// si hay modificacion de pisos

					String[] modificacionPisos = informacionEdificio[1].split("");//cantidad de pisos que cambian para el bano
					Double modificacion;
					if(modificacionPisos[0].equals("+")){
						modificacion = Double.parseDouble(modificacionPisos[1]);//si es positivo simplemente lo volvemos double
					}else if(modificacionPisos[0].equals("-")){
						modificacion = -1.00 * Double.parseDouble(modificacionPisos[1]);//si es negativo multiplicamos por -1
					}else{
						System.out.println("Formato erroneo +/-");
						return;
					}			

					String nombreBaño = "baño" + informacionEdificio[0] ;//creamos el nombre del bano
					grafoCaso.agregarVertice(nombreBaño, -1, -1.00);//agregamos el vertice bano

					String nombreEscalera = "escalera" + informacionEdificio[0];//creamos el nombre de la escalera
					Vertice<Integer> edificio = grafoCaso.obtenerVertice(informacionEdificio[0]);//buscamos el vertice del edificio
					double piso = edificio.getPeso();//sacamos cuantos pisos hay que subir para llegar a un bano
					int capacidad = edificio.getDato();//sacamos cuanta gente puede ir al bano en el edificio
					Double pisofinal = piso + modificacion;

					if(pisofinal < 0.00){//si el piso es negativo debemos hacerlos positivo para als cuentas
						pisofinal = pisofinal * -1.00;
					}

					grafoCaso.agregarArista(nombreEscalera, capacidad, (pisofinal)*25.00  , informacionEdificio[0], nombreBaño);
					//System.out.println(grafoCaso.obtenerArista(nombreEscalera).toString());
				}

				contadorEdificios = contadorEdificios - 1;
			}

			//ELIMINAMOS LOS CAMINOS AFECTADOS POR LAS LLUVIAS
			while(contadorCaminos > 0){

				linea = Lector.readLine();
				String[] idDeCamino = linea.split(" ");//linea con id del camino a eliminar

	
				grafoCaso.eliminarArista(idDeCamino[0]);//eliminamos el camino 
				contadorCaminos = contadorCaminos - 1;

			}

			int genteCaso = numeroGente;//guardamos el numero de gente del caso
			int genteSale = 0; //gente que consigue bano segun bellman

			//EMPEZAMOS EL TRASLADO DE LA GENTA
			while(genteCaso>0 && genteSale != -1){//mientras no todas las personas no tengan bano y queden banos
				genteSale = minibell.bellman(grafoCaso, edificioInicial, genteCaso);
				if(genteSale != -1){//si alguien consiguio bano
					genteCaso = genteCaso - genteSale;
				}else{//si no quedan banos
					String genteSinBano = String.valueOf(genteCaso);//String de la gente que quedo sin bano
					System.out.println("     " + genteSinBano + " personas sin asignar");
				}
			}
			
			linea = Lector.readLine();//saltamos la linea de espacio vacio
			linea = Lector.readLine();//linea con el dia por ejemplo "jueves"
		}


		System.out.println(grafoOriginal.toString());

	}
	public static void main(String[] args)
	throws IOException
	{

			//Transformadores para la conversion
			Transformer<String, Integer> transformadorarcoDouble= new TransformarInteger();
			Transformer<String, Integer> transformadorDouble= new TransformarInteger();
			//creamos el grafo de la universidad base
			GrafoNoDirigido<Integer, Integer> nuevoGrafoNoDirigido = new GrafoNoDirigido<Integer, Integer>();
			//cargamos el grafo de la universidad base
			nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorDouble, transformadorarcoDouble);
			//guardamos el vertice inicial
			String edificioInicial = args[2];
			//guardamos el numero de gente que busca sanitarios
			int numeroGente = Integer.parseInt(args[3]);
			//aplicamos el metodo
			CargarGrafoCaso(args[1], nuevoGrafoNoDirigido, edificioInicial, numeroGente);

	}
}