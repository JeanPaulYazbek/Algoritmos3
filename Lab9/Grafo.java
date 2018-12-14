import java.util.ArrayList;
import java.io.IOException;
public interface Grafo<V, L>
{	
	/**
	 * funcion que carga un grafo de un archivo de texto en el grafo
	 * @param archivo nombre del archivo desde el que queremos cargar el grafo
	 * @param transformer objeto de la clase Transformer que nos ayuda a convertir de String a tipo generico del vertice
	 * @param transformerarco objeto de la clase Transformer que nos ayuda a convertir de String a tipo generico de arco
	 * @return un booleano, true si se creo exitosamente el grafo, false si no
	 * @throws IOException
	 */
	public Boolean cargarGrafo(String archivo)throws IOException;

	/**
	 * funcion que devuelve el numero de vertices de un grafo
	 * @return numero de vertices del grafo
	 */
	public int numeroDeVertices();
	/** 
	 * funcion que devuelve el numero de lados de un grafo
	 * @return numero de lados de un grafo
	 */
	public int numeroDeLados();
	/**
	 * funcion que agrega un objeto tipo vertice a un grafo
	 * @param v objeto vertice a agregar
	 * @return true si se agrega , false en otro caso
	 */
	public Boolean agregarVertice(Vertice<V> v);
	/**
	 * funcion que agrega un vertice segun sus atributos a un grafo
	 * @param id id del vertice a agregar
	 * @param dato dato del vertice a agregar
	 * @param p peso del vertice a agregar
	 * @return true si se agrega, false en otro caso
	 */
	public Boolean agregarVertice( String id, V dato, double p );
	/**
	 * funcion que obtiene vertice de un grafo
	 * @param id id del vertice que se busca
	 * @return objeto vertice obtenido
	 */
	public Vertice obtenerVertice( String id);
	/**
	 * funcion que revisa si existe un vertice en el grafo
	 * @param id id del vertice cuya existencia se quiere verificar
	 * @return true si esta, false en otro caso
	 */
	public Boolean estaVertice( String id);
	/**
	 * funcion que revisa existe un lado en el grafo
	 * @param u	id del primer vertice del lado que se desea verificar
	 * @param v id del segundo vertice del lado que se desea verificar
	 * @return true si existe, false en otro caso
	 */
	public Boolean estaLado(String u, String v);
	/**
	 * funcion que elimina un vertice de un grafo segun su id
	 * @param id id del vertice a eliminar
	 * @return true si se elimina, false en otro caso
	 */
	public Boolean eliminarVertice(String id);
	/**
	 * funcion que da una lista de vertices de un grafo
	 * @return lista de objetos vertices del grafo
	 */
	public ArrayList<Vertice<V>> vertices();
	/**
	 * funcion que da una lista de lados de un grafo
	 * @return lista de objetos lado de un grafo
	 */
	public ArrayList<Lado<L>> lados();
	/**
	 * funcion que calcula el grado de un vertice dado en un grafo
	 * @param id id del vertice que se quiere calcular
	 * @return grado del vertice
	 */
	public int grado(String id);
	/**
	 * funcion que calcula los adyacentes de un vertice
	 * @param id id del vertice cuyas adyacencias se quieren calcular
	 * @return lista de objetos vertice adyacentes
	 */
	public ArrayList<Vertice<V>> adyacentes(String id);
	/**
	 * funcion que calcula lados adyacentes de un vertice en un grafo
	 * @param id id del vertice cuyos lados incidentes se quieren calcular
	 * @return lista de lados incidentes del vertice 
	 */
	//public ArrayList<Lado<L>> incidentes(String id);
	/**
	 * funcion que clona un objeto grafo en otro
	 * @return objeto tipo grafo 
	 */
	public Grafo<V, L> clone();
	/**
	 * funcion que convierte un grafo en un string para imprimirlo
	 * @return regresa el string que representa al grafo
	 */
	public String toString();

}