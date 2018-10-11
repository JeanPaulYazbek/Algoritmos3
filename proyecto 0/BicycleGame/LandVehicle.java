
public interface LandVehicle {
	/**Fija el n&uacute;mero de revoluciones por minuto
	 * 
	 * @param newValue Nuevo rpm
	 */
    public void changeRpm(int newValue);
    
	/**Fija la velocidad (primera, segunda, etc)
	 * 
	 * @param newValue Ordinal de la nueva velocidad
	 */
    public void changeGear(int newValue);

	/**Aumenta la rapidez
	 * 
	 * @param newValue Cantidad de millas por hora de aumento
	 */
    public void speedUp(int increment);

	/**Disminuye la rapidez
	 * 
	 * @param newValue Cantidad de millas por hora de disminuci&oacute;n
	 */
    public void applyBrakes(int decrement);
}
