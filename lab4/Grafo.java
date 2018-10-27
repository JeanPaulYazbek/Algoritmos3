
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
public class Grafo {
	
	//ToDo: Debe colocar aqu&iacute; estructuras de java.util.collections apropiadas
	protected int[][] grafo;
	protected boolean dfsfinished = false;
	/**Crea un grafo minimal*/
	Grafo(){
		
	}
	
	/**Agrega un v&eacute;rtice al grafo. Si el v&eacute;rtice ya existe, el m&eacute;todo no hace
	 * nada.
	 * 
	 * @param id El n&uacute;mero del v&eacute;rtice que se desea agregar
	 */
	public void agregarVertice(int id){
		//caso de grafo vacio
		if (this.grafo == null){
			int nuevoGrafo[][] = new int[1][1];
			this.grafo = nuevoGrafo;
			
		}
		//caso de posible insercion
		if (id >= this.grafo.length){
			
			do{
				
				int n = this.grafo.length + 1;
				int nuevoGrafo[][] = new int[n][n];
				for(int i=0; i<n-1; i++){
					for(int j=0; j<n-1; j++){
						nuevoGrafo[i][j] = this.grafo[i][j];
					}
				}
				this.grafo = nuevoGrafo;
			
			}while(id+ 1> this.grafo.length);
			
			return;
			
		}
		
		//ya esta insertado
		
		return;

	}
	
	/** Metodoque toma un vertice inicial y final del tipo int, y modifica la estructura 
	del grafo acordemente para agregar el arco**/
	public void agregarArco(int verticeInicial, int verticeFinal){
		//si el verticefinal es mas grande que el grafo hay que redimensionar
		if (verticeFinal >= this.grafo.length){
			
			this.agregarVertice( verticeFinal);
		}

		if (verticeInicial >= this.grafo.length){
			
			this.agregarVertice( verticeInicial);
		}
		


		//representamos la arista en la matriz
		this.grafo[verticeInicial][verticeFinal] = 1;
		
		return;
	}
	
	
		
	
}