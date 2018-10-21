public class TransformarDouble implements Transformer<String,Double>{
	public Double transform(String input){
		Double dato = Double.parseDouble(input);
		return dato;
	}

}