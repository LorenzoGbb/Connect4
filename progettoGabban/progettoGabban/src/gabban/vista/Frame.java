package gabban.vista;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */
 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gabban.modello.Tabellone;
import gabban.modello.GestoreGioco;
import gabban.modello.Giocatore;
import gabban.modello.Giocatore.Simbolo;
import gabban.controllo.MainController;
import gabban.eccezioni.ColonnaPienaException;
import gabban.eccezioni.DimensioneCustomException;
import gabban.eccezioni.NomeGiocatoreException;
import gabban.eccezioni.TabellonePienoException;
import gabban.eccezioni.VittoriaException;

public class Frame extends JFrame{
    
	private JPanel buttonPanelDIM;
    private JButton dimensione_default;
    private JButton dimensione_custom;
    private JLabel titleLabel;
    private JLabel turnoLabel;  // Etichetta per visualizzare il turno
    private JPanel tabellonePanel;
    private JLabel testoSelezionePulsanti;
    private Tabellone tabellone;  			// Istanza del Tabellone
    private MainController controller;    	//Istanza di MainController
    private GestoreGioco gestoreGioco;
    
    /**
     * Costruttore di Frame.
     * 
     * Inizializza il titolo, le dimensioni, i componenti grafici, e gestisce i listener per le dimensioni del tabellone.
     */
    public Frame() {    	
    	setTitle("Forza 4");
    	setSize(700, 800);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        titleLabel = new JLabel("Buon divertimento!!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);
        
        turnoLabel = new JLabel("Turno di: ");
        turnoLabel.setHorizontalAlignment(JLabel.CENTER);
        turnoLabel.setVisible(false);		//la rendo visibile solo dopo aver selezionato la dimensione del tabellone
        add(turnoLabel, BorderLayout.SOUTH);
        
        // Inizializza controller e GestoreGioco
        controller = new MainController();
        this.gestoreGioco = controller.getGestoreGioco();
        
        //Aggiunta dei 2 giocatori necessari alla partita
        try {
        	controller.inserisciParametriGiocatore(Simbolo.X);
        } catch(NomeGiocatoreException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
        	controller.inserisciParametriGiocatore(Simbolo.O);
        } catch(NomeGiocatoreException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
       
        
        //Pannello per i pulsanti di selezione della dimensione
        buttonPanelDIM = new JPanel();
        testoSelezionePulsanti = new JLabel("Seleziona la dimensione del tabellone:");
        dimensione_default = new JButton("Default 6x7");
        dimensione_custom = new JButton("Custom");
        
        buttonPanelDIM.add(testoSelezionePulsanti);
        buttonPanelDIM.add(dimensione_default);
        buttonPanelDIM.add(dimensione_custom);
        add(buttonPanelDIM, BorderLayout.CENTER);
        
     // Aggiungi listener per il pulsante "Default 6x7"
        dimensione_default.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	tabellone = controller.getTabellone();
                remove(buttonPanelDIM);		// Rimuovi i pulsanti e crea la tabella 6x7
                mostraTabellone(tabellone);
                turnoLabel.setVisible(true);  // Mostra l'etichetta del turno
                aggiornaTurno();
            }
        });
        
     // Aggiungi listener per il pulsante "Custom"
        dimensione_custom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                // Azione per il pulsante custom 
            	try {
            		controller.inserisciDimensioneCustom();
            		tabellone = controller.getTabellone();
            		 
            		 if (tabellone != null) {  // Verifica se il tabellone è stato inizializzato correttamente
                         remove(buttonPanelDIM);   // Rimuovi il pannello dei bottoni
                         mostraTabellone(tabellone); // Mostra il nuovo tabellone
                         turnoLabel.setVisible(true);  // Mostra l'etichetta del turno
                         aggiornaTurno();
                     } else {
                         throw new NullPointerException("Il tabellone non è stato inizializzato correttamente.");
                     }
            	}catch(DimensioneCustomException ex) {
            		JOptionPane.showMessageDialog(Frame.this, "Errore nella dimensione custom: " + ex.getMessage());
            	}
            }
        }); 
    } 
    
    /**
     * Aggiorna l'etichetta del turno per mostrare il nome del giocatore e il simbolo attualmente di turno.
     */
    private void aggiornaTurno() {
        Giocatore giocatoreCorrente = controller.getGiocatoreCorrente();
        turnoLabel.setText("Turno di: " + giocatoreCorrente.getNome() + " (" + giocatoreCorrente.getSimbolo() + ")");
    }
    
    /**
     * Mostra il tabellone di gioco con una griglia di celle in base alla dimensione specificata
     * e crea una fila di pulsanti per l'inserimento dei gettoni nella parte superiore.
     * 
     * @param tabellone Il tabellone da visualizzare.
     */
    public void mostraTabellone(Tabellone tabellone){
    	if (tabellonePanel != null) {
            remove(tabellonePanel); // Rimuovi il vecchio pannello se esistente
        }
        
    	//Pannello per il tabellone
        tabellonePanel = new JPanel();
        tabellonePanel.setLayout(new GridLayout(tabellone.getRighe(), tabellone.getColonne()));
        
        
        // Pannello per i bottoni sopra il tabellone
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, tabellone.getColonne()));  // Un'unica riga, una colonna per ogni bottone

        
        //Aggiungi una riga di bottoni sopra la tabella
        for (int i = 0; i < tabellone.getColonne(); i++) {
            JButton bottone = new JButton("↓");
            final int colonna = i; //dichiarata in questo modo cosi' da poter essere utilizzata dentro al addActionListener
            
            // Assegna un'azione al bottone (puoi personalizzarla in base alla logica del tuo gioco)
            bottone.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    	controller.inserisciGettone(colonna);
                    	
                    	// Aggiorna la visualizzazione del tabellone e cambia turno, inseriti all'interno del try cosi' da non cambiare turno se si seleziona una colonna piena                    	
                    	aggiornaTabellone();
                    	aggiornaTurno();
                    	
                    	revalidate(); // Aggiorna il layout
                        repaint(); // Ridisegna il contenuto
                        
                    }catch(ColonnaPienaException ex){
                		JOptionPane.showMessageDialog(Frame.this, "Errore: " + ex.getMessage());
                	}catch (VittoriaException ex) {           
                        JOptionPane.showMessageDialog(Frame.this, ex.getMessage(), "Vittoria!", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IndexOutOfBoundsException e1) {
                    	JOptionPane.showMessageDialog(Frame.this,  e1.getMessage());
					} catch (TabellonePienoException ex) {
						JOptionPane.showMessageDialog(Frame.this, ex.getMessage());
					}
                }
            });
            // Aggiungi il bottone al pannello
            buttonPanel.add(bottone);
        }
        
        for (int i = 0; i < tabellone.getRighe(); i++) {
            for (int j = 0; j < tabellone.getColonne(); j++) {
                // Crea un'etichetta con il carattere corrispondente alla cella
                JLabel cella = new JLabel(String.valueOf(tabellone.getCella(i, j)), SwingConstants.CENTER);
                cella.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Bordo per le celle
                tabellonePanel.add(cella);
            }
        }
        
        //Aggiungi i pannelli al frame principale
        add(buttonPanel, BorderLayout.NORTH);
        add(tabellonePanel, BorderLayout.CENTER);
        
    }
    
    /**
     * Aggiorna lo stato delle celle del tabellone senza ricreare l'intero pannello.
     * Questo metodo consente di aggiornare solo il contenuto visivo delle celle.
     */
    public void aggiornaTabellone() {
    	SwingUtilities.invokeLater(() -> {
	        for (int i = 0; i < tabellone.getRighe(); i++) {
	            for (int j = 0; j < tabellone.getColonne(); j++) {
	                JLabel cella = (JLabel) tabellonePanel.getComponent(i * tabellone.getColonne() + j);
	                aggiornaCella(cella, tabellone.getCella(i, j));
	            }
	        }
	        revalidate(); // Aggiorna il layout
	        repaint(); // Ridisegna il contenuto
    	});
    }
    
    /**
     * Aggiorna una singola cella con il simbolo del giocatore e imposta il colore corrispondente.
     * 
     * @param cella La JLabel della cella da aggiornare.
     * @param simbolo Il simbolo da impostare nella cella (X o O).
     */
    private void aggiornaCella(JLabel cella, char simbolo) {
        cella.setText(String.valueOf(simbolo));
        
        // Imposta il colore in base al simbolo
        if (simbolo == 'X') {
            cella.setForeground(controller.getColoreGiocatoreX());
        } else if (simbolo == 'O') {
            cella.setForeground(controller.getColoreGiocatoreO());;
        }
    }
}