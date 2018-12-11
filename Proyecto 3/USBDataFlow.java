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
		if (nuevoGrafoDirigido.cargarGrafo(args[0]))
		{
			OrdenTopologicoDfs orden = new OrdenTopologicoDfs();
			if (orden.DfsVisita(nuevoGrafoDirigido))
			{
				InfixToPostfix evaluador = new InfixToPostfix();
				int n = orden.ordenes.length;
				int m;
		        for (int i = 0; i<n; ++i)
				{
					m = orden.ordenes[i].predecesores.size();
					if (m==0)
					{
						orden.ordenes[i].eval = orden.ordenes[i].expr;
					}
					else
					{
				        for (int j = 0; j<m; ++j)
						{
							orden.ordenes[i].expr = orden.ordenes[i].expr.replace(orden.ordenes[i].predecesores.get(j).getId(),orden.ordenes[i].predecesores.get(j).eval);
						}
					}
					orden.ordenes[i].eval = evaluador.driver(orden.ordenes[i].expr);
				}
				System.out.print(nuevoGrafoDirigido.imprimirMatriz());
			}
		}
	}
}