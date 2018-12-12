import java.util.ArrayList;

public class Vertice<E> {
	private String id;		//atributo id del vertice
	protected E dato;		//atributo dato del vertice
	protected int orden;	//atributo orden topoligo
	protected String eval;	//atr. del valor que evalua el atr. expr
	protected String color;	//atributo color para dfs visita
	private double p;		//atributo peso
	protected String expr;	//expresion cuya evalucion representa el valor
	protected double costo;					//atributo costo de los vertices
	protected Vertice<E> predecesor;		// predecesor del vertice
	protected ArrayList<Vertice<E>> predecesores;// predecesores del vertice
	protected Arista<E> aristaPredecesora;	// arista predecesora de los vertices

	/**
	 * contructor para crear un vertice con todos los atributos 
	 * @param id id del vertice a crear
	 * @param dato dato del vertice a crear
	 * @param p peso del vertice
	 */
	Vertice(String id, E dato, double p)
	{
		this.id = id;
		this.dato = dato;
		this.expr = new String("");
		this.eval = new String("");
		this.p = p;
		this.costo = 9999999.00;
		this.predecesor = null;
		this.color = "White";
		this.predecesores = new ArrayList<Vertice<E>>();
	}

	/**
	 * contructor para crear un vertice con todos los atributos de uno
	 * @param original vertice original al parit del cual se crea la copia
	 */
	public Vertice(Vertice<E> original){
		this.id = original.id;
		this.dato = original.dato;
		this.p = original.p;
		this.costo = original.costo;
		this.predecesor = this.predecesor;
	}
	/**
	 * metodo para modificar el atributo expresion de un vertice
	 */
	public void modifyExpresion(String newExpression)
	{
		this.expr = newExpression;
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
	 * @return id del vertice
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
	public Arista<E> getAristaPredecesora(){
		return aristaPredecesora;
	}

	/**
	 * metodo para obterner el color de un vertice
	 * @return color del vertice
	 */
	public String getColor(){
		return color;
	}

	/**
	 * metodo para modificar el atributo dato de un vertice
	 */
	public void changeDato(E nuevodato){

		this.dato = nuevodato;
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