public class Arco<E> extends Lado{
	protected String id; //id del arco
	protected E dato;	// datos del arco
	protected double p;	// peso del arco
	protected Vertice vi;	// vertice inicial del arco
	protected Vertice vf;	// vertice final del arco 

	//crea arco por constructor
	Arco(String id, E dato, double p, Vertice vi, Vertice vf){
		this.dato = dato;
		this.id = id;
		this.p = p;
		this.vi = vi;
		this.vf = vf;
	}

	//metodo que devuelve el extremo inicial de un arco
	public Vertice getExtremoInicial(){
		return vi;
	}

	//metodo que devuelve extremo final de un arco
	public Vertice getExtremoFinal(){
		return vf;
	}

	//metodo que obtiene el peso de un arco
	public double getPeso(){
		return p;
	}
    
    //metodo que obtiene el id de un arco
	public String getId(){
		return id;
	}

	//metodo que obtiene el Dato de un arco
	public E getDato(){
		return dato;
	}

	//metodo que presenta todos los atributos de un arco en forma de cadena de String
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