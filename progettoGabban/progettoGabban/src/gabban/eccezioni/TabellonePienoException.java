package gabban.eccezioni;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */

@SuppressWarnings("serial")			
public class TabellonePienoException  extends Exception{
	
	/***
	 * Crea un'eccezione nel il tabellone sia pieno
	 *
	 * @param message 	Messaggio di errore
	 */
	public TabellonePienoException (String message) {
	     super(message);
	 }
}
