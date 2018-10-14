
/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
public class TraductorDesdeLista extends TraductorGrafo{
	
	//ToDo: Debe colocar aqu&iacute; estructuras de java.util.collections apropiadas
	
	/**Crea un grafo minimal*/
	TraductorDesdeLista(){
		
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
		//representamos el arco
		this.grafo[verticeInicial][verticeFinal] = 1;
		return;
	}
	
	/**Metodo que tome la representacion de un grafo en matriz de adyacencias logica e imprime la traduccion
	adecuada **/
	public String imprimirGrafoTraducido(){
		int n = this.grafo.length;

		//primera fila
		String matriz = "  ";
		for(int i=0; i<n; i++){
			String vertice = Integer.toString(i);
			matriz = matriz + " " +vertice;
		}
		matriz = matriz + "\n";

		//fila de guiones 
		String guiones = "---";
		for(int i=0; i<n; i++){
			
			guiones = guiones + "--";
		}
		guiones = guiones + "\n";
		matriz = matriz + guiones;

		//resto de las filas 
		for(int i=0; i<n; i++){
			String vertice = Integer.toString(i);
			String fila = vertice + "|";
			for(int j=0; j<n; j++){
				if (this.grafo[i][j] == 1){
					fila = fila + " 1";
				}else{
					fila = fila + " 0";
				}
			
			}
			fila = fila + "\n";
			matriz = matriz + fila;
		}

		return matriz;

	}

	/**Metodo que tome la representacion de un grafo en matriz de adyacencias
	y verifica si para todo lado de la forma (a,b) existe (b,a) **/
	public void esDigrafo(){
		int n = this.grafo.length;
		for(int i = 0; i<n; i++){
			for(int j=0; j<n; j++)
				if(this.grafo[i][j] != this.grafo[j][i]){
					System.out.println("Es un grafo dirigido");
					return;
				}
		}
		System.out.println("Es un grafo no dirigido");
	}
}