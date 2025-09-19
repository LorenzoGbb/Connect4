package gabban.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gabban.modello.Tabellone;
import gabban.modello.Giocatore.Simbolo;

public class TabelloneTest {

    private Tabellone tabellone;

    @BeforeEach
    public void setUp() {
        // Inizializza il tabellone con righe e colonne predefinite (6x7)
        tabellone = new Tabellone();
    }

    @Test
    public void testCostruttoreSenzaParametri() {
        // Testa il costruttore senza parametri
        assertEquals(6, tabellone.getRighe(), "Il numero di righe dovrebbe essere 6");
        assertEquals(7, tabellone.getColonne(), "Il numero di colonne dovrebbe essere 7");

        for (int i = 0; i < tabellone.getRighe(); i++) {
            for (int j = 0; j < tabellone.getColonne(); j++) {
                assertEquals(' ', tabellone.getCella(i, j), "La cella dovrebbe essere vuota");
            }
        }
    }

    @Test
    public void testCostruttoreConParametri() {
        // Testa il costruttore con parametri
        Tabellone tabellonePersonalizzato = new Tabellone(5, 8);
        assertEquals(5, tabellonePersonalizzato.getRighe(), "Il numero di righe dovrebbe essere 5");
        assertEquals(8, tabellonePersonalizzato.getColonne(), "Il numero di colonne dovrebbe essere 8");
    }

    @Test
    public void testSetGetCella() {
        // Imposta un simbolo in una cella e verifica che sia stato impostato correttamente
        tabellone.setCella(0, 0, Simbolo.X);
        assertEquals('X', tabellone.getCella(0, 0), "La cella (0,0) dovrebbe contenere il simbolo 'X'");
        
        tabellone.setCella(2, 3, Simbolo.O);
        assertEquals('O', tabellone.getCella(2, 3), "La cella (2,3) dovrebbe contenere il simbolo 'O'");
    }

    @Test
    public void testGetCellaFuoriLimiti() {
        // Verifica che accedere a una cella fuori dai limiti generi un'eccezione
        assertThrows(IndexOutOfBoundsException.class, () -> tabellone.getCella(-1, 0), "Indice fuori dai limiti");
        assertThrows(IndexOutOfBoundsException.class, () -> tabellone.getCella(0, -1), "Indice fuori dai limiti");
        assertThrows(IndexOutOfBoundsException.class, () -> tabellone.getCella(6, 0), "Indice fuori dai limiti");
        assertThrows(IndexOutOfBoundsException.class, () -> tabellone.getCella(0, 7), "Indice fuori dai limiti");
    }

    @Test
    public void testSetCellaFuoriLimiti() {
        // Verifica che impostare una cella fuori dai limiti generi un'eccezione
        assertThrows(IndexOutOfBoundsException.class, () -> tabellone.setCella(-1, 0, Simbolo.X), "Indice fuori dai limiti");
        assertThrows(IndexOutOfBoundsException.class, () -> tabellone.setCella(0, -1, Simbolo.O), "Indice fuori dai limiti");
        assertThrows(IndexOutOfBoundsException.class, () -> tabellone.setCella(6, 0, Simbolo.X), "Indice fuori dai limiti");
        assertThrows(IndexOutOfBoundsException.class, () -> tabellone.setCella(0, 7, Simbolo.O), "Indice fuori dai limiti");
    }
    
    @Test
    public void testGetTabellone() {
        // Verifica che il tabellone restituito sia non nullo e abbia le dimensioni corrette
        char[][] matrice = tabellone.getTabellone();
        assertNotNull(matrice, "La matrice del tabellone non dovrebbe essere nulla");
        assertEquals(6, matrice.length, "Il numero di righe della matrice dovrebbe essere 6");
        assertEquals(7, matrice[0].length, "Il numero di colonne della matrice dovrebbe essere 7");
    }
}