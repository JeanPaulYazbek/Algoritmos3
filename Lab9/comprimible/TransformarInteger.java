public class TransformarInteger implements Transformer<String,Integer>{
	/**
	 * funcion que toma un string y lo convierte a integer
	 * @param input string que se desea convertir a integer
	 * @return	string convertido
	 */
	public Integer transform(String input){
		Integer dato = Integer.parseInt(input);
		return dato;
	}

}
