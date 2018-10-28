public class Arista<E> extends Lado
{
	protected String id; 	//id de la Arista
	protected E dato;		// datos de la Arista
	protected double p;		// peso de la Arista
	protected Vertice v;	// vertice inicial de la Arista
	protected Vertice u;	// vertice final de la Arista

	/**
	 * crea arista por constructor
	 * @param id id de la arista a crear
	 * @param dato dato de la arista a crear 
	 * @param p peso de la arista a crear
	 * @param v objeto vertice uno o inicial de la arista
	 * @param u objeto vertice dos o final de la arista
	 */
	Arista(String id, E dato, double p, Vertice v, Vertice u)
	{
		this.dato = dato;
		this.id = id;
		this.p = p;
		this.v = v;
		this.u = u;
	}

	/**
	 * metodo que devuelve el extremo uno o inicial de una arista
	 * @return objeto vertice extremo uno o inicial de la arista
	 */
	public Vertice getExtremo1()
	{
		return v;
	}

	/**
	 * metodo que devuelve el extremo dos o final de una arista
	 * @return objeto vertice extremo dos o final de la arista
	 */
	public Vertice getExtremo2()
	{
		return u;
	}

	/**
	 * metodo que obtiene el peso de una arista
	 * @return peso de la arista
	 */
	public double getPeso()
	{
		return p;
	}
    
	/**
	 * metodo que obtiene el id de una arista
	 * @return id de la arista
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * metodo que obtiene el Dato de una arista
	 * @return dato de la arista
	 */
	public E getDato()
	{
		return dato;
	}

	/**
	 * metodo que presenta todos los atributos de una arista en forma de cadena de String
	 * @return string que representa la arista
	 */
	public String toString()
	{
		String x = id;
		String y = String.valueOf(dato);
		String z = String.valueOf(p);
		String a = v.getId();
		String b = u.getId();

		String informacionArco = x + " " + y + " " + z + " " + a + " " + b;
		return informacionArco;
	}
}