import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class Hidrocapital{


	public static void CargarGrafoCaso(String nombreArchivo, GrafoNoDirigido<Double, Double> grafoOriginal)
	throws IOException
	{	
		BufferedReader Lector = new BufferedReader(new FileReader(nombreArchivo));	//buffer para leer el archivo
		String linea = Lector.readLine();//id del caso

		while(linea != null){
			GrafoNoDirigido<Double, Double> grafoCaso = grafoOriginal.clone();//nuevo grafo clonado para el caso

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


			//Agregamos el vertice representante del baño para cada edificio con agua
			while(contadorEdificios > 0){

				linea = Lector.readLine();
				String[] informacionEdificio = linea.split(" ");

				if(informacionEdificio.length == 1){

					String nombreBaño = "baño" + informacionEdificio[0] ;//creamos el nombre del bano
					grafoCaso.agregarVertice(nombreBaño, -1.00, -1.00);//agregamos el vertice bano

					String nombreEscalera = "escalera" + informacionEdificio[0];//creamos el nombre de la escalera
					Vertice<Double> edificio = grafoCaso.obtenerVertice(informacionEdificio[0]);//buscamos el vertice del edificio
					double piso = edificio.getPeso();//sacamos cuanto pisos hay que subir para llegar a un bano
					double capacidad = edificio.getDato();//sacamos cuanta gente puede ir al bano en el edificio
					grafoCaso.agregarArista(nombreEscalera, capacidad, (piso*25.00)  , informacionEdificio[0], nombreBaño);


				}else if(informacionEdificio.length == 2){

					String[] modificacionPisos = informacionEdificio[1].split("");
					Double modificacion;
					if(modificacionPisos[0].equals("+")){
						modificacion = Double.parseDouble(modificacionPisos[1]);
					}else if(modificacionPisos[0].equals("-")){
						modificacion = -1.00 * Double.parseDouble(modificacionPisos[1]);
					}else{
						System.out.println("Formato erroneo +/-");
						return;
					}			

					String nombreBaño = "baño" + informacionEdificio[0] ;//creamos el nombre del bano
					grafoCaso.agregarVertice(nombreBaño, -1.00, -1.00);//agregamos el vertice bano

					String nombreEscalera = "escalera" + informacionEdificio[0];//creamos el nombre de la escalera
					Vertice<Double> edificio = grafoCaso.obtenerVertice(informacionEdificio[0]);//buscamos el vertice del edificio
					double piso = edificio.getPeso();//sacamos cuantos pisos hay que subir para llegar a un bano
					double capacidad = edificio.getDato();//sacamos cuanta gente puede ir al bano en el edificio
					grafoCaso.agregarArista(nombreEscalera, capacidad, (piso + modificacion)*25.00  , informacionEdificio[0], nombreBaño);

				}

				contadorEdificios = contadorEdificios - 1;
			}

			//Proceso para eliminar los caminos afectados del grafo del caso
			while(contadorCaminos > 0){

				linea = Lector.readLine();
				String[] idDeCamino = linea.split(" ");//linea con id del camino a eliminar

	
				grafoCaso.eliminarArista(idDeCamino[0]);//eliminamos el camino 
				contadorCaminos = contadorCaminos - 1;

			}

			System.out.println(grafoCaso.toString());
			linea = Lector.readLine();
			linea = Lector.readLine();
		}


		System.out.println(grafoOriginal.toString());

	}
	public static void main(String[] args)
	throws IOException
	{

			Transformer<String, Double> transformadorarcoDouble= new TransformarDouble();
			Transformer<String, Double> transformadorDouble= new TransformarDouble();
			GrafoNoDirigido<Double, Double> nuevoGrafoNoDirigido = new GrafoNoDirigido<Double, Double>();
			nuevoGrafoNoDirigido.cargarGrafo(args[0], transformadorDouble, transformadorarcoDouble);
			CargarGrafoCaso(args[1], nuevoGrafoNoDirigido);
/**			System.out.println(nuevoGrafoNoDirigido.toString());
			System.out.println(nuevoGrafoNoDirigido.eliminarArista("13"));
			System.out.println(nuevoGrafoNoDirigido.numeroDeLados());

			GrafoNoDirigido<Double, Double> nuevoclon = nuevoGrafoNoDirigido.clone();
			nuevoclon.eliminarVertice("MYS");
			System.out.println(nuevoGrafoNoDirigido.numeroDeVertices());
			System.out.println(nuevoclon.numeroDeVertices());
			System.out.println(nuevoclon.eliminarArista("13"));
			System.out.println(nuevoclon.numeroDeLados());
			System.out.println(nuevoclon.toString());
**/
	}
}