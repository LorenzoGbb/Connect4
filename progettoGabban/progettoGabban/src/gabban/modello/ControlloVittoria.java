package gabban.modello;

/***
 * Classe  invocata per controllare, dopo ogmi mossa, se il giocatore corrente ha vinto
 * @author Lorenzo Gabban 20044584
 *
 */

 
import gabban.modello.Tabellone;


public class ControlloVittoria {
	private Tabellone tabellone;
	private char[][] griglia;  // La matrice del gioco nxm
	private int numRighe;
    private int numColonne;

    public ControlloVittoria(char[][] griglia) {
        this.griglia = griglia;  // Passa la griglia come parametro
        this.numRighe = griglia.length;
        this.numColonne = griglia[0].length;
    }
    
    
    /***
     * Costruttore della classe ControlloVittoria
     * 
     * @param griglia[][]	Matrice tabellone da controllare
     */
    public boolean controllaVittoria(char simbolo) {
        return controllaOrizzontale(simbolo) || controllaVerticale(simbolo) || 
               controllaDiagonaleAscendente(simbolo) || controllaDiagonaleDiscendente(simbolo);
    }

    /***
     * Controllo di vittoria esclusivamente orizzontale
     * 
     * @param 	simbolo	Simbolo su cui controllare la potenziale vittoria in orizzontale
     * @return 	true se il giocatore ha vinto con la mossa attuale, false altrimenti
     */
    private boolean controllaOrizzontale(char simbolo) {
        for (int riga = 0; riga < numRighe; riga++) {
            for (int colonna = 0; colonna < numColonne-3; colonna++) {  
                if (griglia[riga][colonna] == simbolo && 
                	griglia[riga][colonna + 1] == simbolo &&
                    griglia[riga][colonna + 2] == simbolo && 
                    griglia[riga][colonna + 3] == simbolo) {
                    return true;  // Quattro simboli uguali in fila
                }
            }
        }
        return false;
    }

    /***
     * Controllo di vittoria esclusivamente verticale
     * 
     * @param 	simbolo	Simbolo su cui controllare la potenziale vittoria in verticale
     * @return 	true se il giocatore ha vinto con la mossa attuale, false altrimenti
     */
    private boolean controllaVerticale(char simbolo) {
        for (int riga = 0; riga < numRighe-3; riga++) {  
            for (int colonna = 0; colonna < numColonne; colonna++) {
                if (griglia[riga][colonna] == simbolo &&
                	griglia[riga + 1][colonna] == simbolo &&
                    griglia[riga + 2][colonna] == simbolo && 
                    griglia[riga + 3][colonna] == simbolo) {
                    return true;  // Quattro simboli uguali in colonna
                }
            }
        }
        return false;
    }

    /***
     * Controllo di vittoria esclusivamente diagonale ascendente
     * 
     * @param 	simbolo	Simbolo su cui controllare la potenziale vittoria in diagonale ascendente
     * @return 	true se il giocatore ha vinto con la mossa attuale, false altrimenti
     */
    private boolean controllaDiagonaleAscendente(char simbolo) {
        for (int riga = 3; riga < numRighe; riga++) {  
            for (int colonna = 0; colonna < numColonne-3; colonna++) {
                if (griglia[riga][colonna] == simbolo && 
                	griglia[riga - 1][colonna + 1] == simbolo && 
                	griglia[riga - 2][colonna + 2] == simbolo && 
                	griglia[riga - 3][colonna + 3] == simbolo) {
                    return true;  // Quattro simboli uguali in diagonale ascendente
                }
            }
        }
        return false;
    }

    /***
     * Controllo di vittoria esclusivamente diagonale discendente
     * 
     * @param 	simbolo	Simbolo su cui controllare la potenziale vittoria in diagonale discendente
     * @return 	true se il giocatore ha vinto con la mossa attuale, false altrimenti
     */
    private boolean controllaDiagonaleDiscendente(char simbolo) {
        for (int riga = 0; riga < numRighe-3; riga++) {  // Solo fino alla riga 2
            for (int colonna = 0; colonna < numColonne-3; colonna++) {
                if (griglia[riga][colonna] == simbolo && 
                	griglia[riga + 1][colonna + 1] == simbolo &&
                    griglia[riga + 2][colonna + 2] == simbolo &&
                    griglia[riga + 3][colonna + 3] == simbolo) {
                    return true;  // Quattro simboli uguali in diagonale discendente
                }
            }
        }
        return false;
    }
}
