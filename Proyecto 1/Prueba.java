import java.util.ArrayList;

public class Prueba{
	private static ArrayList<Vertice<String>>  listaVertices = new ArrayList<Vertice<String>>();
	private static ArrayList<Arco<Double>>  listaArcos = new ArrayList<Arco<Double>>();
	private static ArrayList<Arista<Boolean>>  listaAristas = new ArrayList<Arista<Boolean>>();
	public static void main(String[] args){
		
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

	}
}