/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
public class TraductorDesdeMatriz extends TraductorGrafo{
	
	//ToDo: Debe colocar aqu&iacute; estructuras de java.util.collections apropiadas
	
	/**Crea un grafo con el n&uacute;mero de v&eacute;rtices dado
	 * 
	 * @param vertices El n&uacute;mero de v&eacute;rtices del grafo +1
	 */
	TraductorDesdeMatriz(int vertices){
		int[][] graf = new int [vertices+1][vertices+1];
		this.grafo = graf;
	}
	/**{@inheritDoc}**/
	/**Metodo que tome la representacion de un grafo en matriz de adyacencias
	e y coloca un 1 o un 2 dependiendo del caso en la matriz, o no coloca nada
	si ya se encontraba insertado. **/	
	public void agregarArco(int verticeInicial, int verticeFinal)
	{
		int o = this.grafo.length;
		for (int i = 1; i < o; ++i)
		{
			if (this.grafo[0][i] == verticeInicial)
			{
				for (int j = 1; j < o; ++j)
				{
					if (this.grafo[j][0] == verticeFinal)
					{
						if (verticeInicial==verticeFinal){
							grafo[i][j]=2;
							return;
						}
						else if (this.grafo[i][j]==1)
						{
							//return
						}
						for (int c = 1; c < o; ++c)
						{
							if (this.grafo[0][c] == verticeFinal)
							{
								for (int f = 1; f < o; ++f)
								{
									if (this.grafo[f][0] == verticeInicial)
									{
										{
											this.grafo[i][j]=1;
											this.grafo[c][f]=1;
											return;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	return;
	}
	
	/**{@inheritDoc}**/
	/**Metodo que tome la representacion de un grafo en matriz de adyacencias
	y lee por fila con quien se relaciona cada vertice, y lo imprime en formato
	de lista de adyacencias **/
	public String imprimirGrafoTraducido(){
		int n = this.grafo.length;
		String matriz = "";
		for (int i = 1; i < n; ++i)
		{
			String vertice = Integer.toString(grafo[i][0]);
			String fila = vertice + ":";
			for (int j = 1; j < n; ++j)
			{
				if (grafo[i][j] == 1){
					fila = fila + " " + Integer.toString(grafo[0][j]);
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
		int m = this.grafo.length;
		for (int i = 1; i < m; ++i)
		{
			for (int j = 1; j < m; ++j)
			{
				if (this.grafo[i][j] == 1)
				{
					for (int c = 1; c < m; ++c)
					{
						if (this.grafo[0][c] == this.grafo[i][0])
						{
							for (int f = 1; f < m; ++f)
							{
								if (this.grafo[f][0] == this.grafo[0][j])
								{
									if (this.grafo[f][c] == 0)
									{
										System.out.println("No es digrafo");
										return;
									}
								}
							}
						}
					}
				}
			}
		}				
		System.out.println("Es digrafo");
	}
}