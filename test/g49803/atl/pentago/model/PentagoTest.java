package g49803.atl.pentago.model;

import g49803.atl.pentago.util.Observer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

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
    
}
