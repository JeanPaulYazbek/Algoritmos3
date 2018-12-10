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
		GrafoDirigido<String,String> nuevoGrafoDirigido = new GrafoDirigido<String,String>();
		nuevoGrafoDirigido.cargarGrafo(args[0]);
		System.out.println(nuevoGrafoDirigido.toString());
//		nuevoGrafoDirigido.OrdenTopologicoDfs();
//		nuevoGrafoDirigido.resolverMatriz();
//		nuevoGrafoDirigido.imprimirMatriz();
	}
}