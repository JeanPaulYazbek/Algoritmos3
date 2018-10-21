public class TransformarBoolean implements Transformer<String,Boolean>{
	public 	Boolean transform(String input){
		Boolean dato = Boolean.valueOf(input);
		return dato;
	}

}