public class Vertice<E>
{
	private int id; //atributo id de los vertices
	private int posicionX; //atributo posicionX de los vertices
	private int posicionY; //atributo posicionY de los vertices
	protected double costo; //atributo costo de los vertices
	protected Vertice<E> predecesor; // predecesor de los vertices
	protected Arista<E> aristaPredecesora;// arista predecesora de los vertices

	/**
	 * contructor para crear un vertice con todos los atributos 
	 * @param id id del vertice a
	 * @param posicionX coordenada X del vertice
	 * @param posicionY coordenada Y del vertice
	 */
	Vertice(int id, int x, int y)
	{
		this.id = 9999999;
		this.posicionX = 9999999;
		this.posicionY = 9999999;
		this.costo = 9999999.00;
		this.predecesor = null;
	}
	/**
	 * metodo para obterner el id de un vertice
	 * @return id del evrtice
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * metodo para obtener la coordenada X de un vertice
	 * @return coordenada X
	 */
	public int posicionX()
	{
		return posicionX;
	}
	/**
	 * metodo para obtener la coordenada Y de un vertice
	 * @return coordenada Y
	 */
	public int posicionY()
	{
		return posicionY;
	}
	/**
	 * metodo para obtener el costo de un vertice
	 * @return costo del vertice
	 */
	public double getCosto()
	{
		return costo;
	}
	/**
	 * metodo para obtener el predecesor de un vertice
	 * @return predecesor del vertice
	 */
	public Vertice<E> getPredecesor()
	{
		return predecesor;
	}
}