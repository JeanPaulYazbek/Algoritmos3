import java.lang.Math;
import java.util.Arrays;
import static java.lang.Math.*;

public class Test
{
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
	    char[] buf = new char[(int) floor(log(25 * (n + 1)) / log(26))];
	    for (int i = buf.length - 1; i >= 0; i--) {
	        n--;
	        buf[i] = (char) ('A' + n % 26);
	        n /= 26;
	    }
	    System.out.println(new String(buf));
	}
}
/*		String idVertice;
		String alphabetUp  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int mod26;
		int xTemp = 0;
		double logs;
		int repetecion;
		int numeroExpresiones = Integer.parseInt(args[0]);
		String[] celdas = new String[numeroExpresiones];

		logs = Math.round(Math.log(26)/Math.log(26));
		System.out.println(logs);

		if (numeroExpresiones<alphabetUp.length())
		{
			for(int j = 0; j<numeroExpresiones; j++)
			{
				celdas[j]=String.valueOf(alphabetUp.charAt(j));
			}
		}
		else
		{
			for(int j = 0; j<alphabetUp.length(); j++)
			{
				celdas[j]=String.valueOf(alphabetUp.charAt(j));
			}
			for(int j = 0; j<numeroExpresiones; j++)
			{
				celdas[j]=String.valueOf(alphabetUp.charAt(j));
			}

		}
		System.out.println(Arrays.toString(celdas));


			for(int j = 0; j<numeroExpresiones; j++)
			{
				idVertice="";
				if (logs>0)
				{
					repetecion = (Math.round(j/Math.pow(26,logs))).intValue();
					idVertice+=alphabetUp[repetecion-1];
					logs-=1;
				}
				mod26 = j%26;
				idVertice+=alphabetUp[mod26];
				System.out.println(idVertice);
			}
*/