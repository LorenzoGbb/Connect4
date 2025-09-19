package gabban.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gabban.modello.Giocatore;
import gabban.modello.Giocatore.Colore;
import gabban.modello.Giocatore.Simbolo;

public class GiocatoreTest {

    private Giocatore giocatore;

    @BeforeEach
    public void setUp() {
        // Inizializza un'istanza di Giocatore prima di ogni test
        giocatore = new Giocatore("Lorenzo", Colore.ROSSO, Simbolo.X);
    }
    
    @Test
    public void testCostruttore() {
        Giocatore nuovoGiocatore = new Giocatore("Mario", Colore.GIALLO, Simbolo.O);
        
        // Verifica i valori assegnati dal costruttore
        assertEquals("Mario", nuovoGiocatore.getNome(), "Il nome del giocatore non è corretto");
        assertEquals(Colore.GIALLO, nuovoGiocatore.getColore(), "Il colore del giocatore non è corretto");
        assertEquals(Simbolo.O, nuovoGiocatore.getSimbolo(), "Il simbolo del giocatore non è corretto");
    }

    @Test
    public void testGetNome() {
        // Verifica che il nome sia corretto
        assertEquals("Lorenzo", giocatore.getNome(), "Il nome del giocatore non è corretto");
    }

    @Test
    public void testGetColore() {
        // Verifica che il colore sia corretto
        assertEquals(Colore.ROSSO, giocatore.getColore(), "Il colore del giocatore non è corretto");
    }

    @Test
    public void testGetSimbolo() {
        // Verifica che il simbolo sia corretto
        assertEquals(Simbolo.X, giocatore.getSimbolo(), "Il simbolo del giocatore non è corretto");
    }
   
}
