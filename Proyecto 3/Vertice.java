public class Vertice<E> {

	private String id; //atributo id de los vertices
	private E posicionUltimoCaracter;    //atributo dato de los vertices
	private int posicionPrimerCaracter;	//atributo peso de los vertices
	protected double costo; //atributo costo de los vertices
	protected Vertice<E> predecesor; // predecesor de los vertices
	private boolean operador;	//atributo peso de los vertices
	private int contenido;	//atributo peso de los vertices
//	protected Arista<E> aristaPredecesora;// arista predecesora de los vertices

	/**
	 * contructor para crear un vertice con todos los atributos 
	 * @param id id del vertice a crear
	 * @param dato dato del vertice a crear
	 * @param p peso del vertice
	 */
	Vertice(String strPosicionUltimoCaracter,E intPosicionUltimoCaracter,int posicionPrimerCaracter,int contenido,boolean operador)
	{
		this.id = strPosicionUltimoCaracter;
		this.posicionUltimoCaracter = intPosicionUltimoCaracter;
		this.posicionPrimerCaracter = posicionPrimerCaracter;
		this.operador=operador;
		this.contenido=contenido;
		this.costo = 9999999.00;
		this.predecesor = null;
	}

	/**
	 * contructor para crear un vertice con todos los atributos 
	 * @param original vertice original al parit del cual se crea la copia
	 */
	public Vertice(Vertice<E> original){
		this.id = original.id;
		this.posicionUltimoCaracter = original.posicionUltimoCaracter;
		this.posicionPrimerCaracter = original.posicionPrimerCaracter;
		this.contenido = original.contenido;
		this.operador = original.operador;
		this.costo = original.costo;
		this.predecesor = this.predecesor;
	}

	/**
	 * metodo para obtener el peso de un vertice
	 * @return peso del vertice
	 */
	public E getDato(){
		return posicionUltimoCaracter;
	}

	/**
	 * metodo para obtener el peso de un vertice
	 * @return peso del vertice
	 */
	public int getPosicionPrimerCaracter(){
		return posicionPrimerCaracter;
	}

	/**
	 * metodo para obtener el peso de un vertice
	 * @return peso del vertice
	 */
	public int getContenido(){
		return posicionPrimerCaracter;
	}

	/**
	 * metodo para obterner el id de un vertice
	 * @return id del evrtice
	 */
	public String getId(){
		return id;
	}

	/**
	 * metodo para obtener el costo de un vertice
	 * @return costo del vertice
	 */
	public double getCosto(){
		return costo;
	}

	/**
	 * metodo para obtener el predecesor de un vertice
	 * @return predecesor del vertice
	 */
	public Vertice<E> getPredecesor(){
		return predecesor;
	}

	/**
	 * metodo para obtener la arista predecesora de un vertice
	 * @return aristaPredecesora del vertice
	 */
//	public Arista<E> getAristaPredecesora(){
//		return aristaPredecesora;
//	}

	public void changeDato(E nuevodato){

		this.posicionUltimoCaracter = nuevodato;
	}
	/**
	 * metodo para presentar todos los atributos del vertice en una sola cadena String
	 * @return string que representa el vertice
	 */
	public String toString()
	{
		String x = id;
		String y = String.valueOf(posicionUltimoCaracter);
		String informacionVertice = x + " " + y;
		return informacionVertice;
	}
}