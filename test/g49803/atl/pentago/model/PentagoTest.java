package g49803.atl.pentago.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeremy
 */
public class PentagoTest {

    @Test(expected = RuntimeException.class)
    public void addNewPlayerToManyPlayer() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.addNewPlayer("Magi");
    }

    @Test(expected = RuntimeException.class)
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

    @Test(expected = RuntimeException.class)
    public void placeMarbleFullCell() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.start();
        pentago.placeMarble(0, 0);
        pentago.rotateQuadrant(1, true);
        pentago.placeMarble(0, 0);
    }

    @Test(expected = GameStateException.class)
    public void placeMarbleWrongState() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.placeMarble(0, 0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void placeMarbleWrongCoord() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.start();
        pentago.placeMarble(10, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void rotateInexistantQuadrant() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.start();
        pentago.placeMarble(0, 0);
        pentago.rotateQuadrant(7, true);
    }
    
    @Test(expected = GameStateException.class)
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
    public void nextPlayerTest() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        pentago.start();
        pentago.placeMarble(0, 0);
        pentago.rotateQuadrant(0, true);
        pentago.placeMarble(1, 0);
        pentago.rotateQuadrant(0, true);
        assertEquals(pentago.getCurrentPlayer().getName(), "Homer");
    }
    
    @Test
    public void getNbPlayerTest() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Homer");
        pentago.addNewPlayer("Bart");
        assertEquals(pentago.getNbPlayer(), 2);
    }
    
    @Test
    public void getCurrentGameStateTest() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Guillaume");
        pentago.addNewPlayer("Amandine");
        pentago.start();
        assertEquals(pentago.getCurrentGameState(), State.PLACEMENT);
    }
    
    @Test
    public void getLastQuadrantRotatedTest() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Guillaume");
        pentago.addNewPlayer("Amandine");
        pentago.start();
        pentago.placeMarble(0, 0);
        pentago.rotateQuadrant(0, true);
        assertEquals(pentago.getLastQuadrantRotated(), 0);
    }
    
    @Test
    public void isLastRotationClockwiseTest() {
        Pentago pentago = new Pentago();
        pentago.addNewPlayer("Guillaume");
        pentago.addNewPlayer("Amandine");
        pentago.start();
        pentago.placeMarble(0, 0);
        pentago.rotateQuadrant(0, true);
        assertTrue(pentago.isLastRotationClockwise());
    }
    
}
