public abstract class Lado<E>{


	/**
	 * metodo abstracto que todo lado debe tener, permite obtener el peso del lado
	 * @return peso del lado
	 */
	public abstract double getPeso();

	/**
	 * metodo abstracto que todo lado debe tener, permite obtener el id del lado
	 * @return id del lado
	 */
	public abstract String getId();

	/**
	 * metodo abstracto que todo lado debe tener, permite obtener el Dato del lado
	 * @return dato del lado
	 */
	public abstract E getDato();

	/**
	 * metodo abstracto que todo lado debe tener, permite obtener todos los atributos del lado en forma de cadena String
	 * @return string que representa el lado
	 */
	public abstract String toString();


}