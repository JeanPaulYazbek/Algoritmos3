import java.util.ArrayList;
public interface Grafo<V, L>{

	public Boolean cargarGrafo(Grafo<V, L> g , String archivo);
	public int numeroDeVertices(Grafo<V, L> g);
	public int numeroDeLados(Grafo<V, L> g);
	public Boolean agregarVertice(Grafo<V, L> g, Vertice<V> v);
	public Boolean agregarVertice(Grafo<V, L> g, String id, V dato, double p );
	public Vertice obtenerVertice(Grafo<V, L> g, String id);
	public Boolean estaVertice(Grafo<V, L> g, String id);
	public Boolean estaLado(Grafo<V, L> g, String u, String v);
	public Boolean eliminarVertice(Grafo<V, L> g, String id);
	public ArrayList<Vertice<V>> vertices(Grafo<V, L> g);
	public ArrayList<Lado<L>> lados(Grafo<V, L> g);
	public int grado(Grafo<V, L> g, String id);
	public ArrayList<Vertice<V>> adyacentes(Grafo<V, L> g, String id);
	public ArrayList<Lado<L>> incidentes(Grafo<V, L> g, String id);
	public Grafo<V, L> clone(Grafo<V, L> g);
	public String toString(Grafo<V, L> g);

}
