import java.util.Hashtable;
import java.util.ArrayList;
import java.io.IOException;

public class PrimAlgoritmo{

	public static void prim(GrafoNoDirigido<Integer, Integer> grafoConexo){

		grafoConexo.resetVertices();

		ArrayList<Vertice<Integer>> vertices = grafoConexo.vertices();

		if(vertices.size() > 0){
			vertices.get(0).costo = 0.0;
		}

		while(vertices.size() != 0){
			Vertice<Integer> ver = extraerMinimo(vertices);
			ArrayList<Arista<Integer>> incidentes = grafoConexo.incidentes(ver.getId());
			for(Arista<Integer> arista : incidentes){

				Vertice<Integer> w;
				if(arista.getExtremo1().getId().equals(ver.getId())){
					w = arista.getExtremo2();
				}else{
					w = arista.getExtremo1();
				}
				if( !(w.getSalio()) && (w.getCosto() > arista.getPeso())){
					w.costo = arista.getPeso();
					w.predecesor = ver;
					w.aristaPredecesora = arista;
				}
			}

		}


	}

	public static Vertice<Integer> extraerMinimo(ArrayList<Vertice<Integer>> listaVertices){

		int n = listaVertices.size();
		double minimo = listaVertices.get(0).getCosto();
		int indiceMinimo = 0;
		for(int i = 0; i < n; i++){
			if(listaVertices.get(i).getCosto() < minimo){
				minimo = listaVertices.get(i).getCosto();
				indiceMinimo = i;
			}
		}

		Vertice<Integer> verticeMinimo = listaVertices.get(indiceMinimo);
		listaVertices.remove(indiceMinimo);
		verticeMinimo.salio = true;
		return verticeMinimo;
	}

	public static void main(String[] args){

		GrafoNoDirigido<Integer,Integer> nuevog = new GrafoNoDirigido<Integer, Integer>();
		nuevog.agregarVertice("a", 0, 0.0);
		nuevog.agregarVertice("b", 0, 0.0);
		nuevog.agregarVertice("c", 0, 0.0);
		nuevog.agregarVertice("d", 0, 0.0);
		nuevog.agregarVertice("e", 0, 0.0);
		nuevog.agregarArista("ac", 0, 2.00, "a", "c");
		nuevog.agregarArista("ab", 0, 1.00, "a", "b");
		nuevog.agregarArista("bc", 0, 0.00, "a", "c");
		nuevog.agregarArista("bd", 0, 3.00, "b", "d");
		nuevog.agregarArista("dc", 0, 1.00, "d", "c");
		nuevog.agregarArista("ce", 0, 2.00, "e", "c");
		nuevog.agregarArista("de", 0, 2.00, "d", "e");
		prim(nuevog);
		ArrayList<Vertice<Integer>> vertices = nuevog.vertices();
		for(Vertice<Integer> ver : vertices){
			if(ver.getAristaPredecesora() != null){

			System.out.println( ver.getAristaPredecesora().getExtremo1().getId() + "-" + ver.getAristaPredecesora().getExtremo2().getId() + ":" + String.valueOf(ver.getAristaPredecesora().getPeso()));
			}
		}



	}
}