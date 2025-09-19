package gabban.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import gabban.modello.ControlloVittoria;

public class ControlloVittoriaTest {

    @Test
    public void testVittoriaOrizzontale() {
        char[][] griglia = {
            {'-', '-', '-', '-'},
            {'-', '-', '-', '-'},
            {'X', 'X', 'X', 'X'},  // Quattro X in orizzontale
            {'-', '-', '-', '-'}
        };
        ControlloVittoria controllo = new ControlloVittoria(griglia);
        assertTrue(controllo.controllaVittoria('X'), "La vittoria orizzontale non è stata rilevata correttamente.");
    }

    @Test
    public void testVittoriaVerticale() {
        char[][] griglia = {
            {'-', 'X', '-', '-'},
            {'-', 'X', '-', '-'},
            {'-', 'X', '-', '-'},
            {'-', 'X', '-', '-'}  // Quattro X in verticale
        };
        ControlloVittoria controllo = new ControlloVittoria(griglia);
        assertTrue(controllo.controllaVittoria('X'), "La vittoria verticale non è stata rilevata correttamente.");
    }

    @Test
    public void testVittoriaDiagonaleAscendente() {
        char[][] griglia = {
            {'-', '-', '-', 'X'},
            {'-', '-', 'X', '-'},
            {'-', 'X', '-', '-'},
            {'X', '-', '-', '-'}  // Quattro X in diagonale ascendente
        };
        ControlloVittoria controllo = new ControlloVittoria(griglia);
        assertTrue(controllo.controllaVittoria('X'), "La vittoria diagonale ascendente non è stata rilevata correttamente.");
    }

    @Test
    public void testVittoriaDiagonaleDiscendente() {
        char[][] griglia = {
            {'X', '-', '-', '-'},
            {'-', 'X', '-', '-'},
            {'-', '-', 'X', '-'},
            {'-', '-', '-', 'X'}  // Quattro X in diagonale discendente
        };
        ControlloVittoria controllo = new ControlloVittoria(griglia);
        assertTrue(controllo.controllaVittoria('X'), "La vittoria diagonale discendente non è stata rilevata correttamente.");
    }

    @Test
    public void testNessunaVittoria() {
        char[][] griglia = {
            {'X', '-', 'O', 'X'},
            {'-', 'O', '-', '-'},
            {'O', '-', 'X', '-'},
            {'-', '-', '-', 'O'}
        };
        ControlloVittoria controllo = new ControlloVittoria(griglia);
        assertFalse(controllo.controllaVittoria('X'), "La vittoria dovrebbe essere assente.");
        assertFalse(controllo.controllaVittoria('O'), "La vittoria dovrebbe essere assente.");
    }
}