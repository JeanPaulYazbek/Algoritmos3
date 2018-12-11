public class Arco<E> extends Lado{
	protected String id; //id del arco
	protected E dato;	// datos del arco
	protected double p;	// peso del arco
	protected Vertice vi;	// vertice inicial del arco
	protected Vertice vf;	// vertice final del arco 
//	HACKED
	/**
	 * crea arco por constructor
	 * @param id id del arco a crear
	 * @param dato dato del arco a crear 
	 * @param p peso del arco a crear
	 * @param vi objeto vertice inicial del arco
	 * @param vf objeto vertice final del arco
	 */
	Arco(String id, E dato, double p, Vertice vi, Vertice vf){
		this.dato = dato;
		this.id = id;
		this.p = p;
		this.vi = vi;
		this.vf = vf;
	}

	/**
	 * metodo que devuelve el extremo inicial de un arco
	 * @return objeto vertice extremo inicial del arco
	 */
	public Vertice getExtremoInicial(){
		return vi;
	}

	/**
	 * metodo que devuelve extremo final de un arco
	 * @return objeto vertice extremo final del arco
	 */
	public Vertice getExtremoFinal(){
		return vf;
	}

	/**
	 * metodo que obtiene el peso de un arco
	 * @return peso del arco
	 */
	public double getPeso(){
		return p;
	}
    
	/**
	 * metodo que obtiene el id de un arco
	 * @return id del arco
	 */
	public String getId(){
		return id;
	}

	/**
	 * metodo que obtiene el Dato de un arco
	 * @return dato del arco
	 */
	public E getDato(){
		return dato;
	}


	/**
	 * metodo que presenta todos los atributos de un arco en forma de cadena de String
	 * @return string que representa el arco
	 */
	public String toString(){
		String x = id;
		String y = String.valueOf(dato);
		String z = String.valueOf(p);
		String a = vi.getId();
		String b = vf.getId();

		String informacionArco = x + " " + y + " " + z + " " + a + " " + b;
		return informacionArco;
	}

} 
