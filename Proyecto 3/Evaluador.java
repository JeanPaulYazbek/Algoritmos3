import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Evaluador
{
	public static void main(String[] args)
	throws IOException
	{
		//caso en el que no se indico archivo
    	if(args.length < 1)
    	{
			System.err.println("Uso: java Evaluador <archivodeexpresiones>");
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
		String linea = Lector.readLine();
		InfixToPostfix evaluator = new InfixToPostfix();
		while(linea != null)
		{
//			System.out.println(linea);
			System.out.println(evaluator.driver(linea));
			linea = Lector.readLine();
		}
	}
}