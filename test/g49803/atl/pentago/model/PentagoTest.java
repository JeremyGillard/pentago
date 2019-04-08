package g49803.atl.pentago.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeremy
 */
public class PentagoTest {
    
    @Test (expected = RuntimeException.class)
    public void addNewPlayerToManyPlayer() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.addNewPlayer("Magi");
    }
    
    @Test (expected = RuntimeException.class)
    public void startGameNotEnoughtPlayer() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Marge");
        pentago.start();
    }
    
    @Test
    public void placeMarbleNormalCase() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.start();
        pentago.placeMarble(0, 0);
        assertEquals(pentago.getMarbleAt(0, 0), Marble.WHITE);
    }
    
    @Test (expected = RuntimeException.class)
    public void placeMarbleFullCell() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.start();
        pentago.placeMarble(0, 0);
        pentago.rotateQuadrant(1, true);
        pentago.placeMarble(0, 0);
    }
    
    @Test (expected = StateGameException.class)
    public void placeMarbleWrongState() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.placeMarble(0, 0);
    }
    
    @Test (expected = StateGameException.class)
    public void rotateQuadrantWrongState() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.rotateQuadrant(0, true);
    }
    
    @Test
    public void rotateQuadrantNextPlayer() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.start();
        pentago.placeMarble(0, 0);
        pentago.rotateQuadrant(0, true);
        assertEquals(pentago.getCurrentPlayer().getName(), "Bart");
    }
    
    @Test
    public void didAnyoneWin() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.start();
        pentago.placeMarble(0, 5);
        assertTrue(pentago.didAnyoneWin());
    }
    
}
