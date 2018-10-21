import java.util.ArrayList;
public interface Grafo<V, L>{

	
	public int numeroDeVertices();
	public int numeroDeLados();
	public Boolean agregarVertice(Vertice<V> v);
	public Boolean agregarVertice( String id, V dato, double p );
	public Vertice obtenerVertice( String id);
	public Boolean estaVertice( String id);
	public Boolean estaLado(String u, String v);
	public Boolean eliminarVertice(String id);
	public ArrayList<Vertice<V>> vertices();
	public ArrayList<Lado<L>> lados();
	public int grado(String id);
	public ArrayList<Vertice<V>> adyacentes(String id);
	public ArrayList<Lado<L>> incidentes(String id);
	public Grafo<V, L> clone();
	public String toString();

}
