package gabban.eccezioni;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */

@SuppressWarnings("serial")			
public class NomeGiocatoreException extends Exception{
	
	/***
	 * Crea un'eccezione nel caso il campo nome sia vuoto
	 *
	 * @param message 	Messaggio di errore
	 */
	public NomeGiocatoreException(String message) {
	     super(message);
	 }
}
