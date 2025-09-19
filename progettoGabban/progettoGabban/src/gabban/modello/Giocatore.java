package gabban.modello;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */

public class Giocatore {
    /***
    * 
    * Enumerazione dei colori giocatore
    *
    */
	public enum Colore {
	    ROSSO, GIALLO, VERDE, BLU
	}

	/***
	* 
	* Enumerazione dei simboli giocatore
	*
	*/
	public enum Simbolo {
		X, O
	}
	
	private String nome;
	private Colore Colore;
	private Simbolo Simbolo;
	
	/***
    * Costruttore di giocatore
    * 
    * @param nome 		
    * @param simbolo  [...]
    * @param colore	
    */
	public Giocatore(String nome, Colore colore, Simbolo simbolo) {
		this.nome = nome;
		this.Colore = colore;		
		this.Simbolo = simbolo;		
	}
	
	/***
     * Restituisce il nome di un giocatore
     * 
     * @return nome 	Una copia del nome del giocatore specifico
     */
	public String getNome() {
	    return nome;
	}
	
	/***
     * Restituisce il simbolo di un giocatore
     * 
     * @return simbolo 	Una copia del simbolo del giocatore specifico
     */
	public Simbolo getSimbolo() {
	    return Simbolo;						
	}
	
	/***
     * Restituisce il colore di un giocatore
     * 
     * @return colore 	Una copia del colore del giocatore specifico
     */
	public Colore getColore() {
	    return Colore;						
	}
}
