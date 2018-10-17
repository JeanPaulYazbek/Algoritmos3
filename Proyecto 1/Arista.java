public class Arista<E> extends Lado{
	protected String id; //id de la Arista
	protected E dato;	// datos de la Arista
	protected double p;	// peso de la Arista
	protected Vertice v;	// vertice inicial de la Arista
	protected Vertice u;	// vertice final de la Arista

	//crea una arsita por constructor
	Arista(String id, E dato, double p, Vertice v, Vertice u){
		this.dato = dato;
		this.id = id;
		this.p = p;
		this.v = v;
		this.u = u;
	}

	//metodo que devuelve el primer extremo de una arista
	public Vertice getExtremo1(){
		return v;
	}

	//metodo que devuelve el segundo extremo de una arista
	public Vertice getExtremo2(){
		return u;
	}

	//metodo que obtiene el peso de una arista
	public double getPeso(){
		return p;
	}
    
    //metodo que obtiene el id de una arista
	public String getId(){
		return id;
	}

	//metodo que obtiene el Dato de una arista
	public E getDato(){
		return dato;
	}

	//metodo que presenta todos los atributos de una arista en forma de cadena de String
	public String toString(){
		String x = id;
		String y = String.valueOf(dato);
		String z = String.valueOf(p);
		String a = v.getId();
		String b = u.getId();

		String informacionArco = x + " " + y + " " + z + " " + a + " " + b;
		return informacionArco;
	}

} 