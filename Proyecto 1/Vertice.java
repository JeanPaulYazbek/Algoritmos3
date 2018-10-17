public class Vertice<E> {

	private String id; //atributo id de los vertices
	private E dato;    //atributo dato de los vertices
	private double p;	//atributo peso de los vertices

	//contructor para crear un vertice con todos los atributos 
	Vertice(String id, E dato, double p){
		this.id = id;
		this.dato = dato;
		this.p = p;
	}

	//metodo para obtener el peso de un vertice
	public double getPeso(){
		return p;
	}

	//metodo para obterner el id de un vertice
	public String getId(){
		return id;
	}

	//metodo para obtener el Dato de un vertice
	public E getDato(){
		return dato;
	}

	//metodo para presentar todos los atributos del vertice en una sola cadena String
	public String toString(){
		String x = id;
		String y = String.valueOf(dato);
		String z = String.valueOf(p);

		String informacionVertice = x + " " + y + " " + z;
		return informacionVertice;
	}
}