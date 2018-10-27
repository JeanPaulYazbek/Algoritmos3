public class TransformarBoolean implements Transformer<String,Boolean>{
	public 	Boolean transform(String input){
		/**
		* funcion que toma un string y lo convierte a boolean
		* @param input string que se desea convertir a boolean
	 	* @return	string convertido
		 */
		Boolean dato = Boolean.valueOf(input);
		return dato;
	}

}