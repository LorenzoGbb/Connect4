package gabban.controllo;

import gabban.vista.Frame;
import gabban.modello.GestoreGioco;

import java.awt.Color;

import javax.swing.*;
import gabban.modello.Giocatore;
import gabban.modello.Giocatore.Simbolo;
import gabban.modello.Tabellone;
import gabban.modello.ControlloVittoria;
import gabban.eccezioni.*;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */

public class MainController {
	private GestoreGioco gestoreGioco = new GestoreGioco();
	private Tabellone tabellone  = new Tabellone();
	private ControlloVittoria controlloVittoria;
	
	private Color coloreGiocatoreX;
    private Color coloreGiocatoreO;
	public String nRighe;
	public String nColonne;
	
    private Giocatore giocatoreCorrente;
    private Simbolo simboloCorrente;
    
    /***
     * Richiede l'inserimento del nome e del colore di un giocatore tramite un'interfaccia grafica.
     * Se il nome è valido e confermato, aggiunge il giocatore al gioco.
     * 
     * @param simbolo Il simbolo del giocatore (X o O).
     * 
     * @throws NomeGiocatoreException se il nome inserito è vuoto o non valido.
     */
    public void inserisciParametriGiocatore(Simbolo simbolo) throws NomeGiocatoreException{
    	boolean inputValido = false;
    	
    	while(!inputValido) {
    		JTextField nomeGiocatoreTextField = new JTextField();
        	
        	Giocatore.Colore[] opzioni = Giocatore.Colore.values();	
        	JComboBox<Giocatore.Colore> comboBox = new JComboBox<>(opzioni);
        	
        	Object[] message = {
        			"Nome giocatore: ", nomeGiocatoreTextField,
                    "Seleziona il colore: ", comboBox,
            };
        	
        	 int conferma = JOptionPane.showConfirmDialog(null, message, "Scegli il tuo colore: ", JOptionPane.OK_CANCEL_OPTION);
        	 
        	 
        	 if (conferma == JOptionPane.OK_OPTION) {
        		 
        		 String nomeGiocatore = nomeGiocatoreTextField.getText();
        		 Giocatore.Colore coloreScelto = (Giocatore.Colore) comboBox.getSelectedItem();
        		     		 
        		 if (nomeGiocatore.isEmpty()) {
        			 JOptionPane.showMessageDialog(null, "Il nome non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
        			 continue;
        		 }
        		 
        		 gestoreGioco.aggiungiGiocatore(nomeGiocatore, coloreScelto, simbolo);
        		 
        		 if (simbolo == Simbolo.X) {
                     coloreGiocatoreX = convertiColore(coloreScelto);
                 } else if (simbolo == Simbolo.O) {
                     coloreGiocatoreO = convertiColore(coloreScelto);
                 }
        		 
        		 inputValido = true;
        	 }
    	}
    	
    }
    
    /***
     * Converte un colore dal tipo enum Giocatore.Colore al tipo java.awt.Color.
     * 
     * @param colore Il colore scelto dal giocatore.
     * 
     * @return Il colore corrispondente in formato java.awt.Color.
     */
    private Color convertiColore(Giocatore.Colore colore) {
        switch (colore) {
            case ROSSO: return Color.RED;
            case BLU: return Color.BLUE;
            case VERDE: return Color.GREEN;
            case GIALLO: return Color.YELLOW;
            
            default: return Color.BLACK;
        }
    }
    
    /***
     * Restituisce il colore assiociato al giocatore il cui simbolo e' X.
     * 
     * @return coloreGiocatoreX Colore del giocatore dal simbolo X.
     */
    public Color getColoreGiocatoreX() {
        return coloreGiocatoreX;
    }
    
    /***
     * Restituisce il colore assiociato al giocatore il cui simbolo e' O.
     * 
     * @return coloreGiocatoreX Colore del giocatore dal simbolo O.
     */
    public Color getColoreGiocatoreO() {
        return coloreGiocatoreO;
    }
    
    /***
     * Visualizza un'interfaccia per richiedere all'utente le dimensioni personalizzate del tabellone.
     * Se le dimensioni sono valide e confermate, imposta le dimensioni personalizzate per il gioco.
     * 
     * @throws DimensioneCustomException se le dimensioni inserite non sono valide (tra 4 e 10).
     */
    public void inserisciDimensioneCustom() throws DimensioneCustomException{
    	JTextField nRigheTextField = new JTextField();
    	JTextField nColonneTextField = new JTextField();
    	
    	Object[] message = {
    			"Numero di righe: ", nRigheTextField,
                "Numero di colonne: ", nColonneTextField,
        };
    	
    	int conferma = JOptionPane.showConfirmDialog(null, message, "Indica la dimensione del tabellone: ", JOptionPane.OK_CANCEL_OPTION);

    	if (conferma == JOptionPane.OK_OPTION) {
    		nRighe = nRigheTextField.getText();
    		nColonne = nColonneTextField.getText();
    		
	    	if (nRighe.isEmpty()) {
	            throw new DimensioneCustomException("Compilare tutti i campi prima di proseguire.");
	        }
	    	if((Integer.parseInt(nRighe)<4 ||Integer.parseInt(nRighe)> 10)){
	    		throw new DimensioneCustomException("Dimensione non valita, inserire valori compresti tra 4 e 10");
	    	}
	    		
	   		if (nColonne.isEmpty()) {
	            throw new DimensioneCustomException("Compilare tutti i campi prima di proseguire.");
	        }
	   		if((Integer.parseInt(nColonne)<4 ||Integer.parseInt(nColonne)> 10)){
	    		throw new DimensioneCustomException("Dimensione non valita, inserire valori compresti tra 4 e 10");
	   		}
	   		
    		gestoreGioco.aggiungiDimensioneCustom(Integer.parseInt(nRighe), Integer.parseInt(nColonne));
    	}	
    }
    
    /***
     * Restituisce l'istanza del gestore del gioco corrente.
     * 
     * @return gestoreGioco L'istanza di GestoreGioco associata al gioco.
     */
    public GestoreGioco getGestoreGioco() {
        return this.gestoreGioco;
    }
    
    /***
     * Inserisce un gettone nella colonna specificata del tabellone di gioco per il giocatore corrente.
     * Lancia eccezioni in caso di vittoria, tabellone pieno, colonna piena, o indice di colonna non valido.
     * 
     * @param colonna La colonna del tabellone in cui inserire il gettone.
     * 
     * @throws ColonnaPienaException 		se la colonna è già piena.
     * @throws IndexOutOfBoundsException 	se la colonna è fuori dai limiti del tabellone.
     * @throws VittoriaException 			se il giocatore corrente ha vinto.
     * @throws TabellonePienoException 		se il tabellone è pieno e non è possibile inserire altri gettoni.
     */
    public void inserisciGettone(int colonna) throws ColonnaPienaException, IndexOutOfBoundsException, VittoriaException, TabellonePienoException {
    	this.controlloVittoria = new ControlloVittoria(gestoreGioco.getTabellone().getTabellone());
    	if (colonna < 0 || colonna >= gestoreGioco.getTabellone().getColonne()) {
            throw new IndexOutOfBoundsException("Colonna fuori dai limiti!");
        }
    	
    	
    	for (int riga = gestoreGioco.getTabellone().getRighe() - 1; riga >= 0; riga--) {
            if (gestoreGioco.getTabellone().getCella(riga, colonna) == ' ') { // Se la cella è vuota
            	giocatoreCorrente = gestoreGioco.getGiocatoreCorrente();
            	simboloCorrente = giocatoreCorrente.getSimbolo();
            	//gestoreGioco.cambiaTurno();
            	
                // Inserisci il simbolo del giocatore corrente
            	gestoreGioco.getTabellone().setCella(riga, colonna, simboloCorrente);
            	
            	 if (controlloVittoria.controllaVittoria(simboloCorrente.toString().charAt(0))) {
            		 giocatoreCorrente = gestoreGioco.getGiocatoreCorrente();
                 	 simboloCorrente = giocatoreCorrente.getSimbolo();
                     throw new VittoriaException("Il giocatore " + giocatoreCorrente.getNome() + " ha vinto!");
                 }
            	if(!gestoreGioco.cambiaTurno()) {
            		throw new TabellonePienoException("Tabellone pieno - fine partita");
            	}
            	
            	return;	//necessario, in quanto la throw sottostante si deve lanciare solo se alla fine del ciclo for non si sono trovate celle libere
            }
        }
    	// Se nessuna cella è disponibile, la colonna è piena
    	throw new ColonnaPienaException("la colonna è piena!");
    }
    
    /***
     * Ottiene il giocatore attualmente di turno.
     * 
     * @return Il giocatore che deve compiere la mossa corrente.
     */
    public Giocatore getGiocatoreCorrente() {
    	return gestoreGioco.getGiocatoreCorrente();
    }
    
    /***
     * Restituisce l'istanza corrente del tabellone di gioco.
     * 
     * @return L'istanza del tabellone di gioco.
     */
    public Tabellone getTabellone() {
        return gestoreGioco.getTabellone();
    }
}