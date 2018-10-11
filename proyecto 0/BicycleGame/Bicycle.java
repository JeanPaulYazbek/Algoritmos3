/**La clase <code>Bicycle</code> representa una bicicleta del mundo real.
 * Mantiene su {@link #rapidez}, {@link #velocidad} y {@link #cadencia}
 */
public class Bicycle {
	
	/**Almacena la cadencia de los pedales en revoluciones por minuto.
	 * Se puede obtener con {@link #getCadence()} y fijar con
	 * {@link #changeCadence(int)}.
	 */
    int cadencia = 0;
    
    /**Almacena la rapidez traslacional en millas por hora.
     * Se puede obtener con {@link #getSpeed()} y fijar con
     * {@link #speedUp(int)}.
     */
    float rapidez = 0;
    
    /**Almacena el engranaje actual del cassette posterior de la
     * bicicleta (primera, segunda, tercera, etc).
     */
    int velocidad = 1;
    

    /**Almacena el número mayor de velocidades
     */
    protected int gears = 3;

    /**Define el factor de conversión entre cadencia y
    * rapidez. Las subclases pueden cambiarlo según corresponda
    * a su tipo de bicicleta.
    */
    protected int factor = 1; 

    /**Fija la cadencia al n&uacute;mero de revoluciones indicado
     * 
     * @param nuevaCadencia
     * 		N&uacute;mero de revoluciones por minuto
     *      al cual se fijar&aacute; la cadencia.
     */
    public void changeCadence(int nuevaCadencia) {
         this.cadencia = nuevaCadencia;
         //considerar inercia
         System.out.println("entro");
         this.rapidez = ( this.rapidez + this.convertirCadenciaARapidez() )/2;
    }

    /**Fija el engranaje actual al dado por la
     * <code>nuevaVelocidad</code>-&eacute;sima velocidad
     * @param nuevaVelocidad
     * 		Ordinal de la velocidad que se fijar&aacute;.
     */
    public void changeGear(int nuevaVelocidad) throws IllegalArgumentException {
        if (nuevaVelocidad > this.gears || nuevaVelocidad < 0) {
            throw new IllegalArgumentException(
                "No se puede poner" + nuevaVelocidad + "a." +
                " La bici solo tiene velocidades del 1" +
                " al " + this.gears
            );
        }
        this.velocidad = nuevaVelocidad;
    }

    /**Suma a {@link rapidez} el aumento indicado en millas por hora.
     * Note que este m&eacute;todo no afecta la cadencia.
     * 
     * @param aumento
     * 		Cantidad de millas en las que se 
     *      aumentar&aacute; la rapidez.
     */
    public void speedUp(int aumento) {
        this.rapidez = this.rapidez + aumento;   
    }

    /**Resta a {@link rapidez} la disminuci&oacute;n indicada
     * en millas por hora.
     * Note que este m&eacute;todo no afecta la cadencia.
     * 
     * @param disminucion
     * 		Cantidad de millas en las que 
     * 		disminuir&aacute; la rapidez.
     */
    public void applyBrakes(int disminucion) {
         this.rapidez = this.rapidez - disminucion;
    }

    /**Imprime los valores actuales de {@link #cadencia},
     * {@link #rapidez} y {@link #velocidad} por c&oacute;nsola.
     */
    public void printStates() {
        System.out.println(
        		" cadencia:"  + this.cadencia +
        		" rapidez:"   + this.rapidez  +
        		" velocidad:" + this.velocidad
        		);
   }
   
    /**Obtiene la cadencia actual.
     * 
     * @return el valor actual de {@link #cadencia}.
     */
   public int getCadence(){
	   return this.cadencia;
   }
   
   /**Obtiene la rapidez actual.
    * 
    * @return el valor actual de {@link #rapidez}.
    */
   public float getSpeed(){
	   return this.rapidez;
   }
   
   /**Obtiene la velocidad actual.
    * 
    * @return el valor actual de {@link #velocidad}.
    */
   public int getGear(){
	   return this.velocidad;
   }
   
   /**M&eacute;todo que envuelve a {@link #changeCadence(int)}
    * 
    * @param nuevaCadencia
    * 		N&uacute;mero de revoluciones por minuto
    *      al cual se fijar&aacute; la cadencia.
    */
   public void changeRpm(int newValue){
       changeCadence(newValue);
   }

   /**Convierte la cadencia a la rapidez equivalente
   * sin considerar la gravedad o el momento de inercia.
   *
   * @return la rapidez producida por la cadencia
   * actual en millas por hora.
   */
   protected float convertirCadenciaARapidez(){
       float div = (float)this.cadencia*this.velocidad/this.factor;
       return div;
   }
}
