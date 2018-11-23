import java.util.ArrayList;
import java.io.IOException;
public interface Grafo<V, L>
{	
	/**
	 * funcion que carga un grafo de un archivo de texto en el grafo
	 * @param archivo nombre del archivo desde el que queremos cargar el grafo
	 * @return un booleano, true si se creo exitosamente el grafo, false si no
	 * @throws IOException
	 */
	public Boolean cargarGrafo(String archivo)
	throws IOException;
	/**
	 * funcion que devuelve el numero de vertices de un grafo
	 * @return numero de vertices del grafo
	 */
	public int numeroDeVertices();
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
	public Boolean agregarVertice(int id,double posicionX,double posicionY);
	/**
	 * funcion que obtiene vertice de un grafo
	 * @param id id del vertice que se busca
	 * @return objeto vertice obtenido
	 */
	public Vertice obtenerVertice(int id);
	/**
	 * funcion que revisa si existe un vertice en el grafo
	 * @param id id del vertice cuya existencia se quiere verificar
	 * @return true si esta, false en otro caso
	 */
	public Boolean estaVertice(int id);
	/**
	 * funcion que revisa existe un lado en el grafo
	 * @param u	id del primer vertice del lado que se desea verificar
	 * @param v id del segundo vertice del lado que se desea verificar
	 * @return true si existe, false en otro caso
	 */
}