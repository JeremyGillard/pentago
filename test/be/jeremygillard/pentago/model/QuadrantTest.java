package be.jeremygillard.pentago.model;

import be.jeremygillard.pentago.model.Quadrant;
import be.jeremygillard.pentago.model.Marble;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuadrantTest {
    
    @Test
    public void placeAndGetMarbleTest() {
        Quadrant quadrant = new Quadrant(3);
        quadrant.placeMarble(0, 0, Marble.BLACK);
        assertTrue(quadrant.getMarble(0, 0) == Marble.BLACK);
    }
    
    @Test
    public void clockwiseRotateQuadrant() {
        Quadrant quadrant = new Quadrant(3);
        quadrant.placeMarble(0, 0, Marble.BLACK);
        quadrant.rotate(true);
        assertTrue(quadrant.getMarble(2, 0) == Marble.BLACK);
    }
    
    @Test
    public void nonClockwiseRotateQuadrant() {
        Quadrant quadrant = new Quadrant(3);
        quadrant.placeMarble(0, 0, Marble.BLACK);
        quadrant.rotate(false);
        assertTrue(quadrant.getMarble(0, 2) == Marble.BLACK);
    }
    
}
