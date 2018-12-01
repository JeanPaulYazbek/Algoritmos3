public class Vertice<E> {

	private String id; //atributo id de los vertices
	private E dato;    //atributo dato de los vertices
	private double p;	//atributo peso de los vertices

	/**
	 * contructor para crear un vertice con todos los atributos 
	 * @param id id del vertice a crear
	 * @param dato dato del vertice a crear
	 * @param p peso del vertice
	 */
	Vertice(String id, E dato, double p){
		this.id = id;
		this.dato = dato;
		this.p = p;
	}

	/**
	 * metodo para obtener el peso de un vertice
	 * @return peso del vertice
	 */
	public double getPeso(){
		return p;
	}

	/**
	 * metodo para obterner el id de un vertice
	 * @return id del evrtice
	 */
	public String getId(){
		return id;
	}

	/**
	 * metodo para obtener el Dato de un vertice
	 * @return dato del vertice
	 */
	public E getDato(){
		return dato;
	}

	/**
	 * metodo para presentar todos los atributos del vertice en una sola cadena String
	 * @return string que representa el vertice
	 */
	public String toString(){
		String x = id;
		String y = String.valueOf(dato);
		String z = String.valueOf(p);

		String informacionVertice = x + " " + y + " " + z;
		return informacionVertice;
	}
}