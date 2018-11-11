public class Arista<E>
{
	protected int id; 		//id de la Arista
	protected Vertice v;	// vertice inicial de la Arista
	protected Vertice u;	// vertice final de la Arista
	protected double distancia;	//distancia entre los vertices

	/**
	 * crea arista por constructor
	 * @param id id de la arista a crear
	 * @param distancia distancia de la arista a crear
	 */
	Arista(int id, Vertice v, Vertice u, double distancia)
	{
		this.id = id;
		this.v = v;
		this.u = u;
		this.distancia = distancia;
	}
	/**
	 * metodo que obtiene el id de una arista
	 * @return id de la arista
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * metodo para obtener la distancia de los vertices que conecta
	 * @return distancia
	 */
	public double distancia()
	{
		return distancia;
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
}