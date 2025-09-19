package gabban.eccezioni;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */

@SuppressWarnings("serial")			
public class VittoriaException extends Exception{
	
	/***
	 * Crea un'eccezione nel caso un giocatore abbia vinto
	 *
	 * @param message 	Messaggio di errore
	 */
	public VittoriaException(String message) {
	     super(message);
	 }
}
