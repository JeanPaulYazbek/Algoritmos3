import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class USBDataFlow
{
	public static void main(String[] args)
	throws IOException
	{
		//caso en el que no se indico archivo
    	if(args.length < 1)
    	{
			System.err.println("Uso: java USBDataFlow <hojadecalculo>");
			return;
		}

		BufferedReader Lector;
		try
		{
			Lector = new BufferedReader(new FileReader(args[0]));
		}
		catch(Exception e)
		{
			System.out.println("Archivo No Encontrado");
			return;
		}

		GrafoDirigido<Boolean,Integer> nuevoGrafoDirigido = new GrafoDirigido<Boolean,Integer>();
		Vertice<Boolean> verticeActual;

		//si no hubo error al cargar el grafo
		if (nuevoGrafoDirigido.cargarGrafo(args[0]))
		{
			OrdenTopologicoDfs orden = new OrdenTopologicoDfs();
			//calculamos el orden topologico sobre el grafo de las celdas
			if (orden.DfsVisita(nuevoGrafoDirigido))
			{
				InfixToPostfix evaluador = new InfixToPostfix();
				int n = orden.ordenes.length;
				int m;
		        for (int i = 0; i<n; ++i)
				{
					verticeActual = orden.ordenes[i];
					m = verticeActual.predecesores.size();

					//si su expr no depende de ningun vertice
					if (m==0)
					{
						verticeActual.eval = verticeActual.expr;
					}
					else
					{
				        for (int j = 0; j<m; ++j)
						{
							//reemplazamos los vertices en la expr por su eval
							try
							{
								verticeActual.expr = verticeActual.expr.replace(
									verticeActual.predecesores.get(j).getId(),
									verticeActual.predecesores.get(j).eval);							}
							catch(Exception e)
							{
								System.out.println("Error en Expresion");
								return;
							}
						}
					}
					//evaluamos la expresion del vertice y lo guardamos en su eval
					try
					{
						verticeActual.eval = evaluador.driver(verticeActual.expr);
					}
					catch(Exception e)
					{
						System.out.println("Error en Expresion");
						System.out.println(verticeActual.expr);
						return;
					}
				}
				//mostramos en pantalla el grafo del excel
				System.out.print(nuevoGrafoDirigido.imprimirMatriz());
			}
		}
	}
}