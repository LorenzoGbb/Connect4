package gabban.modello;

import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import gabban.modello.Tabellone;
import gabban.modello.Giocatore;
import gabban.controllo.MainController;
import gabban.eccezioni.DimensioneCustomException;
import gabban.eccezioni.NomeGiocatoreException;
import gabban.modello.Giocatore.Colore;
import gabban.modello.Giocatore.Simbolo;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */

public class GestoreGioco {
	private List<Giocatore> listaGiocatori = new ArrayList<>();
	private MainController controller;
	private Giocatore giocatore;
	private Tabellone tabelloneCustom;
	private int totaleTurni = 0;
	private int turnoCorrente = 0;	//Variabile tra i valori 0 e 1, ciascun valore indica un giocatore
	
	public GestoreGioco(){
		 this.tabelloneCustom = new Tabellone();
	}
	
	/***
     * Aggiunge un giocatore alla lista dei giocatori
     * 
     * @param nome							Il nome del giocatore
     * @param colore						Il colore scelto dal giocatore
	 * @return 
     * @throws NomeGiocatoreException 		Lanciata se il campo e' vuoto				
     */
	// Metodo per aggiungere un giocatoreb
	public void aggiungiGiocatore(String nome, Giocatore.Colore colore, Simbolo simbolo) throws NomeGiocatoreException {
	    if (nome == null || nome.trim().isEmpty()) {
	        throw new NomeGiocatoreException("Il nome del giocatore non pu� essere vuoto.");
	    }
	    giocatore = new Giocatore(nome, colore, simbolo);
	    listaGiocatori.add(giocatore);	  
	}
    
    /***
     * Aggiunge il tabellone con la dimensione indicata dall'utente
     * 
     * @param nRighe							Numero di righe indicate
     * @param nColonne						    Numero di colonne indicate
	 * @return 
     * @throws DimensioneCustomException		Lanciata se la dim inserita non e' valida 						
     */
	public void aggiungiDimensioneCustom(int nRighe, int nColonne) throws DimensioneCustomException {
	    if (nRighe < 4 || nColonne < 4) {  // Supponiamo che la dimensione minima valida sia 3x3
	        throw new DimensioneCustomException("La dimensione minima consentita � 4x4.");
	    }
	    this.tabelloneCustom = new Tabellone(nRighe,nColonne);
	}
    
	/***
     * Restituisce il tabellone di dimensione specificata dall'utente
     * 
     * @return tabelloneCustom 	Tabella creata con la dimensione specificata dall'utente 
     */
    public Tabellone getTabellone() {
    	if (this.tabelloneCustom == null) {
            this.tabelloneCustom = new Tabellone(); 
        }
        return this.tabelloneCustom;
    }
    
    /***
     * Metodo per passare al turno successivo, contando i turni calcola di quale giocatore e' il turno attuale
     * 
     * @return true se non si ha superato il numero di mosse massime disponibili, false altrimenti
     */
    public boolean cambiaTurno() {
        totaleTurni++;
        int maxTurni = tabelloneCustom.getRighe() * tabelloneCustom.getColonne();
        
        if(totaleTurni >= maxTurni) {
        	return false;
        }
        turnoCorrente = totaleTurni % 2;  
        return true;
    }
    
    /***
     * Restituisce il giocatore il cui turno e' quello attuale - rispettivamente posizione 0 oppure 1 della lista
     * 
     * @return listaGiocatori.get(turnoCorrente) 	giocatore della lista il cui turno e' quello attuale 
     */
    public Giocatore getGiocatoreCorrente() {
        return listaGiocatori.get(turnoCorrente);
    }
}
