public class TransformarDouble implements Transformer<String,Double>{
	/**
	 * funcion que toma un string y lo convierte a double
	 * @param input string que se desea convertir a double
	 * @return	string convertido
	 */
	public Double transform(String input){
		Double dato = Double.parseDouble(input);
		return dato;
	}

}