/**Una T&aacute;ndem es una bicicleta con dos asientos y dos
 * pares de pedales. Se distingue de una bicicleta definida
 * por {@link Bicycle} en la manera de calcular la cadencia.
 * La clase almacena el esfuerzo realizado por cada ciclista
 * en {@link #esfuerzoDelante} y {@link #esfuerzoDetras}.
 */
public class Tandem extends Bicycle {
	/**Proporci&oacute;n del esfuerzo de pedaleo realizado
	 * por el ciclista en el asiendo delantero. 
	 * Generalmente entre cero y uno.
	 */
    double esfuerzoDelante = 0.5;
    
	/**Proporci&oacute;n del esfuerzo de pedaleo realizado
	 * por el ciclista en el asiendo de atr&aacute;s. 
	 * Generalmente entre cero y uno.
	 */
    double esfuerzoDetras = 0.5;
    
    /**Fija la cadencia al promedop ponderado de las cadencias
     * que est&aacute;n intentando ambos ciclistas. La
     * ponderaci&oacute;n viene dada por el actual
     * {@link #esfuerzoDelante} y {@link #esfuerzoDetras}.
     * Este m&eacute;todo ajusta los esfuerzos acorde con
     * los n&uacute;meros dados.
     * 
     * @param pedalDelante
     * 		Cadencia de pedal a la que est&aacute;
     *      intentando pedalear el ciclista en el
     *      asiento delantero.
     * @param pedalDetras
     *      Cadencia de pedal a la que est&aacute;
     *      intentando pedalear el ciclista en el
     *      asiento de atr&aacute;s.
     */
   public void changeCadence(int pedalDelante, int pedalDetras){
		this.cadencia = (int)(pedalDelante*this.esfuerzoDelante+
				pedalDetras * this.esfuerzoDetras);

		this.esfuerzoDelante =
				(double)pedalDelante / (double)this.cadencia / 2.0;
		this.esfuerzoDetras =
				 (double)pedalDetras / (double)this.cadencia / 2.0;
        
	}
   
    /**Imprime, adem&aacute;s de los valores impresos por
     * {@link Bicycle#printStates()}, el {@link #esfuerzoDelante}
     * actual y el {@link #esfuerzoDetras} actual.
     */
    public void printStates() {
        super.printStates();
        System.out.println("esfuerzoDelante:" 
            +this.esfuerzoDelante+" esfuerzoDetras:" 
            +this.esfuerzoDetras);
    }
	
    /**Obtiene el esfuerzo actual del ciclista en el asiendo
     * delantero
     * 
     * @return el valor actual de {@link #esfuerzoDelante}.
     */
   public double getFront(){
	   return this.esfuerzoDelante;
   }
   
   /**Obtiene el esfuerzo actual del ciclista en el asiendo
    * de atr&aacute;s
    * 
    * @return el valor actual de {@link #esfuerzoDetras}.
    */
  public double getBack(){
	   return this.esfuerzoDetras;
  }

}
