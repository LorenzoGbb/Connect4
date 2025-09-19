package gabban.test;

import gabban.modello.GestoreGioco;
import gabban.modello.Giocatore;
import gabban.modello.Tabellone;
import gabban.modello.Giocatore.Simbolo;
import gabban.modello.Giocatore.Colore;
import gabban.eccezioni.DimensioneCustomException;
import gabban.eccezioni.NomeGiocatoreException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.Test;

class GestoreGiocoTest {
	
	private GestoreGioco gestoreGioco;
	
	@BeforeEach
	public void setUp() throws NomeGiocatoreException {
	    gestoreGioco = new GestoreGioco();
	    
	    // Aggiungi due giocatori diversi per il test del cambio turno
	    gestoreGioco.aggiungiGiocatore("MARIO", Colore.ROSSO, Simbolo.X);
	    gestoreGioco.aggiungiGiocatore("LUIGI", Colore.VERDE, Simbolo.O);
	}
	
	@Test
	public void testAggiungiGiocatore_Corretto() {
	    // Verifica che il primo turno sia del giocatore "MARIO"
	    assertEquals("MARIO", gestoreGioco.getGiocatoreCorrente().getNome());
	    assertEquals(Colore.ROSSO, gestoreGioco.getGiocatoreCorrente().getColore());
	    assertEquals(Simbolo.X, gestoreGioco.getGiocatoreCorrente().getSimbolo());
	    
	    // Cambia turno e verifica che il turno passi a "LUIGI"
	    gestoreGioco.cambiaTurno();
	    assertEquals("LUIGI", gestoreGioco.getGiocatoreCorrente().getNome());
	    assertEquals(Colore.VERDE, gestoreGioco.getGiocatoreCorrente().getColore());
	    assertEquals(Simbolo.O, gestoreGioco.getGiocatoreCorrente().getSimbolo());
	}
    
	@Test
    public void testAggiungiGiocatore_EccezioneNomeVuoto() {
        assertThrows(NomeGiocatoreException.class, () -> {
            gestoreGioco.aggiungiGiocatore("", Colore.GIALLO, Simbolo.O);
        });
    }

	@Test
    public void testAggiungiDimensioneCustom_Corretto() throws DimensioneCustomException {
        gestoreGioco.aggiungiDimensioneCustom(5, 6);
        Tabellone tabellone = gestoreGioco.getTabellone();
        assertEquals(5, tabellone.getRighe());
        assertEquals(6, tabellone.getColonne());
    }

	@Test
    public void testAggiungiDimensioneCustom_EccezioneDimensioneInvalida() {
        assertThrows(DimensioneCustomException.class, () -> {
            gestoreGioco.aggiungiDimensioneCustom(2, 2); // Supponendo che 2x2 sia una dimensione non valida
        });
    }

	@Test
	public void testCambiaTurno() throws NomeGiocatoreException, DimensioneCustomException {
	    // Turno iniziale
	    assertEquals("MARIO", gestoreGioco.getGiocatoreCorrente().getNome());
	    
	    // Passa al turno successivo e verifica
	    gestoreGioco.cambiaTurno();
	    assertEquals("LUIGI", gestoreGioco.getGiocatoreCorrente().getNome());
	    
	    // Torna a MARIO al secondo turno
	    gestoreGioco.cambiaTurno();
	    assertEquals("MARIO", gestoreGioco.getGiocatoreCorrente().getNome());
	}


	@Test
    public void testCambiaTurno_TermineGioco() throws DimensioneCustomException, NomeGiocatoreException {
        // Aggiunge il tabellone con dimensione 3x3 per avere 9 turni massimi
        gestoreGioco.aggiungiDimensioneCustom(5, 5);
        
        // Aggiunge due giocatori necessari per alternare i turni
        gestoreGioco.aggiungiGiocatore("MARIO", Colore.ROSSO, Simbolo.X);
        gestoreGioco.aggiungiGiocatore("LUIGI", Colore.GIALLO, Simbolo.O);
        
        int maxTurni = 9;
        boolean turniDisponibili = true;
        for (int i = 0; i < maxTurni; i++) {
            turniDisponibili = gestoreGioco.cambiaTurno();
            assertTrue(turniDisponibili, "Dovrebbero esserci turni disponibili finché il tabellone non è pieno.");
        }
    }
}