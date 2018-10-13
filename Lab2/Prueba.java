import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class Prueba{
	public static void main(String[] args)
		throws IOException, IllegalArgumentException
	{
		String nombreArchivo = args[0];
		
		
		BufferedReader Lector = new BufferedReader(new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();

		String[] primerafila = linea.split(" ");

		System.out.println(primerafila[0]);
		System.out.println(primerafila[0].substring(0,1));
	}
}
