import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Evaluador
{
	public static void main(String[] args)
	throws IOException
	{
		GrafoDirigido<Integer,Integer> expresionActual = new GrafoDirigido<Integer,Integer>();

//		System.out.println(args[0]);
		String entrada = args[0]+" ";
		int n = entrada.length()-1;
		int k = 0;
		String actual="";
		String nuevoCaracter="";
		//Primero detectamos los enteros, que seran las hojas
		while(k<n)
		{
			nuevoCaracter = String.valueOf(entrada.charAt(k));
			if ((nuevoCaracter.equals("0"))||(nuevoCaracter.equals("1"))||(nuevoCaracter.equals("2"))
				||(nuevoCaracter.equals("3"))||(nuevoCaracter.equals("4"))||(nuevoCaracter.equals("5"))
				||(nuevoCaracter.equals("6"))||(nuevoCaracter.equals("7"))||(nuevoCaracter.equals("8"))
				||(nuevoCaracter.equals("9")))
			{
				actual+=nuevoCaracter;
//				System.out.println(actual);
				if (k==n-1)
				{
					if (!(actual.equals("")))
					{
						expresionActual.agregarVertice(actual,k,(k-(actual.length())+1));
					}
					actual="";
				}
			}
			else if (nuevoCaracter.equals("-"))
			{
				if (actual.equals(""))
				{
					actual+=nuevoCaracter;
				}
				else
				{
					if (!(actual.equals("")))
					{
						expresionActual.agregarVertice(actual,k,-1);
					}
					actual="-";
				}

			}
			else
			{
				if (!(actual.equals("")))
				{
					expresionActual.agregarVertice(actual,k,-1);
				}
//				System.out.println(actual+"agregado");
				actual="";
			}
//			System.out.println(actual);
			k+=1;
		}
		System.out.println(expresionActual.toString());
	}
}