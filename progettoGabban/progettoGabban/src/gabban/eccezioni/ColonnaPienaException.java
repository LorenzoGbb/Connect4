package gabban.eccezioni;

/***
 * Crea un'eccezione nel caso la cononna sia piena
 *
 * @param message 	Messaggio di errore
 */

@SuppressWarnings("serial")			
public class ColonnaPienaException extends Exception{
	
	/***
	 * Crea un'eccezione nel caso il campo nome sia vuoto
	 *
	 * @param message 	Messaggio di errore
	 */
	public ColonnaPienaException(String message) {
	     super(message);
	 }
}