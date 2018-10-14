import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Arrays;

public class Prueba
{
	public static void main(String[] args)
		throws IOException, IllegalArgumentException
	 {
	 	//Leemos el archivo que contiene la representacion matricial del grafo
	 	String fileName = args[0];
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) 
		{
		//Almacenamos temporael contenido de la linea cero
		String firstLine = reader.readLine();
		
		// Obtenemos los vertices de la primera linea
		String[] firstLineSplit = firstLine.split(" ");
		int numberOfVertices = firstLineSplit.length;
		numberOfVertices -=3;				// no contamos los 3 espacios en blanco

		//Creamos la matriz y almacenamos los vertices en la primera fila
		// dejando la primera casilla en 0.
		int[][] grafo = new int [numberOfVertices+1][numberOfVertices+1];
		for (int i = 3; i < numberOfVertices+3; ++i)
		{
			grafo[0][i-2]=Integer.parseInt(firstLineSplit[i]);
		}

		//Almacenamos el contenido de las lineas 2 en adelante
		String myCurrentLine;
		int k=0;
		while ((myCurrentLine = reader.readLine()) != null)
		{
			if (k>0)
			{
				String[] a = myCurrentLine.split("|");
				int w=0;
				for (int i = 0; i < a.length; ++i)
				{
					if ((!a[i].equals(" ")))
					{
						if (!a[i].equals("|"))
						{
							grafo[k][w]=Integer.parseInt(a[i]);
							w+=1;
						}
					}
				}
			}
			k+=1;
		}
		//System.out.println(Arrays.deepToString(grafo));
		
		// Imprimimos la matriz en forma de lista de adyacencias
		int n = grafo.length;
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
		System.out.println(fila);
		}

		//Verificamos si es digrafo
		int m = grafo.length;
		for (int i = 1; i < m; ++i)
		{
			for (int j = 1; j < m; ++j)
			{
				if (grafo[i][j] == 1)
				{
					for (int c = 1; c < m; ++c)
					{
						if (grafo[0][c] == grafo[i][0])
						{
							for (int f = 1; f < m; ++f)
							{
								if (grafo[f][0] == grafo[0][j])
								{
									if (grafo[f][c] == 0)
									{
										System.out.println("No es digrafo");
										//return false;
									}
								}
							}
						}
					}
				}
			}
		}				
		System.out.println("Es digrafo");
		//return true

		//Agregar Arco {a,b}
		int a=2;
		int b=1;
		int o = grafo.length;
		for (int i = 1; i < o; ++i)
		{
			if (grafo[0][i] == a)
			{
				for (int j = 1; j < o; ++j)
				{
					if (grafo[j][0] == b)
					{
						if (a==b){
							grafo[i][j]=2;
							//return
						}
						else if (grafo[i][j]==1)
						{
							//return
						}
						for (int c = 1; c < o; ++c)
						{
							if (grafo[0][c] == b)
							{
								for (int f = 1; f < o; ++f)
								{
									if (grafo[f][0] == a)
									{
										{
											grafo[i][j]=1;
											grafo[c][f]=1;
											//return
										}
									}
								}
							}
						}
					}
				}
			}
		}
		//System.out.println(Arrays.deepToString(grafo));

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}