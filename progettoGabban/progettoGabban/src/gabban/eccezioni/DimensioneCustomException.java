package gabban.eccezioni;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */

@SuppressWarnings("serial")			
public class DimensioneCustomException extends Exception{
	
	/***
	 * Crea un'eccezione nel caso la dimensione indicata supera i limiti consentiti
	 *
	 * @param message 	Messaggio di errore
	 */
	public DimensioneCustomException(String message) {
	     super(message);
	 }
}
