package gabban.modello;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */

import gabban.modello.Giocatore.Colore;
import gabban.modello.Giocatore.Simbolo;

public class Tabellone {
	private char[][] tabella;
	private int righe = 6;
	private int colonne = 7;
	
	/***
     * Costruttore del tabellone con parametri
     * 
     * @param tabella 		matrice di gioco
     * @param righe  num. righe della tabella
     * @param colonne	num. colonne della tabella
     */
	public Tabellone(int righe, int colonne) {
		tabella = new char[righe][colonne];
		this.righe = righe;
		this.colonne = colonne;
		
		for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                tabella[i][j] = ' ';  
            }
        }
	}
	
	/***
     * Costruttore del tabellone senza parametri, invoca l'altro costruttore della classe (Tabellone(int righe, int colonne)) passando i paramentri (6,7)
     * 
     * @param tabella matrice di gioco
     */
	public Tabellone(){
		this(6, 7);	
		
		for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                tabella[i][j] = ' ';  
            }
        }
    }
	
	/***
     * Restituisce il numero di righe nel tabellone
     * 
     * @return righe 	Numero di righe che compongono il tabellone
     */
	public int getRighe() {
	    return righe;
	}
	
	/***
     * Restituisce il numero di colonne nel tabellone
     * 
     * @return colonne 	Numero di colonne che compongono il tabellone
     */
	public int getColonne() {
	    return colonne;
	}
	
	/***
     * Imposta il numero di righe del tabellone.
     *
     * @param righe Numero di righe da impostare nel tabellone.
     */
	public int setRighe() {
	    return righe;
	}
	
	/***
     * Imposta il numero di colonne del tabellone.
     *
     * @param colonne Numero di colonne da impostare nel tabellone.
     */
	public int setColonne() {
	    return colonne;
	}
	
	/***
     * Restituisce il tabellone di gioco
     * 
     * @return tabella 	Tabella completa di gioco allo stato attuale
     */
	public char[][] getTabellone() {
	    return tabella; // Restituisce la matrice di gioco
	}
	
	/***
     * Restituisce una singola cella del tabellone
     * 
     * @return tabella[i][j] 	Valore della cella nella tabella alle coordinate [i][j] specificate
     */
    public char getCella(int i, int j) {
        if (i >= 0 && i < righe && j >= 0 && j < colonne) {
            return tabella[i][j];
        } else {
            throw new IndexOutOfBoundsException("Indice fuori dai limiti della tabella");
        }
    }
    
    /***
     * Imposta il valore di una cella in tabellone, in posizione specificata [i][j]
     *
     * @param i			riga sulla quale si trova la cella ricercata
     * @param j			colonna sulla quale si trova la cella ricercata
     * @param simbolo	simbolo da inserire in cella
     * 
     * @throws IndexOutOfBoundsException se gli indici i o j sono fuori dai limiti della tabella.
     */
    public void setCella(int i, int j, Simbolo simbolo) {
        if (i >= 0 && i < righe && j >= 0 && j < colonne) {
            tabella[i][j] = simbolo.name().charAt(0);;  // Imposta il colore nella cella
        } else {
            throw new IndexOutOfBoundsException("Indice fuori dai limiti della tabella");
        }
    }
}
