import java.util.Scanner; 
/**Clase principal del juego de la bicicleta tandem. El juego es de dos jugadores.
 */
public class Main{
	/**Fija la cadencia que est&aacute; intentando lograr el ciclista delantero
	 * de la bicicleta t&aacute;ndem <code>t</code> en <code>p</code> rpm.
	 * 
	 * @param t Bicicleta t&aacute;ndem donde est&aacute; el ciclista
	 * @param p cadencia de pedal que intenta lograr el ciclista en rpm
	 */
	static void pedalearDelante(Tandem t, int p){
		int actual = t.getCadence();
		t.changeCadence(p, actual);
		t.changeCadence(t.cadencia);
	}
	
	/**Fija la cadencia que est&aacute; intentando lograr el ciclista
	 * de atr&aacute;s de la bicicleta t&aacute;ndem <code>t</code>
	 * en <code>p</code> rpm.
	 * 
	 * @param t Bicicleta t&aacute;ndem donde est&aacute; el ciclista
	 * @param p cadencia de pedal que intenta lograr el ciclista en rpm
	 */
	static void pedalearDetras(Tandem t, int p){
		int actual = t.getCadence();
		t.changeCadence(actual, p);
		t.changeCadence(t.cadencia);
	}
	
	/**Contiene el juego como tal.*/
	public static void main(String[] args) throws java.io.IOException{
		Tandem t = new Tandem();
		int ganaDelante = 0;
		int ganaDetras  = 0;
		String jugadorA;
		String jugadorL;
		Scanner ss = new Scanner(System.in);


		System.out.println("Introduce tu nombre jugadorA (usas la 'a') :");
		jugadorA = ss.nextLine();
		jugadorA = jugadorA.trim();
		System.out.println("Introduce tu nombre jugadorL (usas la 'l') :");
		jugadorL = ss.nextLine();
		jugadorL = jugadorL.trim();

		System.out.println(
				"Presione 'a' (seguido de \"Enter\") para pedalear en los pedales delanteros\n"+
				"Presione 'l' (seguido de \"Enter\") para pedalear en los pedales de atras\n"+
				"Presione 'n' (seguido de \"Enter\") para salir\n"+
				"Presione 'v' (seguido de \"Enter\") para aumentar velocidad\n"+
				"Presione 'b' (seguido de \"Enter\") para disminuir velocidad\n");


		
		while(true){

			char c = (char)System.in.read();
			
			if(c=='a'){
				pedalearDelante(t, t.getCadence()+2);
				t.printStates();
				if(t.esfuerzoDelante < t.esfuerzoDetras){
					ganaDetras++;
				}else{
					ganaDelante++;
				}
			}
			
			if(c=='l'){
				pedalearDetras(t, t.getCadence()+2);
				t.printStates();
				if(t.esfuerzoDelante < t.esfuerzoDetras){
					ganaDetras++;
				}else{
					ganaDelante++;
				}
			}
			
			if(c=='n'){
				if(ganaDelante < ganaDetras){
					System.out.println("El ciclista de atras hizo "+
							"la mayor parte del esfuerzo en "+
							ganaDetras+" de "+(ganaDelante+ganaDetras)+
							" pedaleadas. Felicidades, " + (jugadorL) + "!");
				}else if (ganaDelante > ganaDetras){
					System.out.println("El ciclista delantero hizo "+
							"la mayor parte del esfuerzo en "+
							ganaDelante+" de "+(ganaDelante+ganaDetras)+
							" pedaleadas. Felicidades, " + (jugadorA) + "!");
				}else{
					System.out.println("Han empatado con " + ganaDetras + "pedaleadas cada uno");
				}
				return;
			}
			
			//subir velocidad(gear)
			if(c == 'v'){
				try{
					t.changeGear(t.getGear()+1);
					t.changeCadence(t.cadencia);
				}
				catch(IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
			}
			
			//bajar velocidad(gear)
			if(c == 'b'){
				try{
					t.changeGear(t.getGear()-1);
				}
				catch(IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
			}
			//quien va ganando
			
			if(ganaDelante > ganaDetras){
					System.out.println("El ciclista de adelante va ganando con  "+ganaDelante +" pedaleadas, mientras que el de atras va perdiendo con "+ ganaDetras);

			}else if(ganaDelante < ganaDetras){
					System.out.println("El ciclista de atras va ganando con  "+ganaDetras +" pedaleadas, mientras que el de adelante va perdiendo con "+ ganaDelante);

			}else{
					System.out.println("Van empatados con  "+ganaDelante + " pedaleadas cada uno");

			}
			System.in.read();
			
	    }
    }
}
