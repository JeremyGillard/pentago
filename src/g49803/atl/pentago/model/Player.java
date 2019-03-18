package g49803.atl.pentago.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g49803
 */
public class Player {
    
    private final String name;
    
    private List<Marble> marbles;
    
    private boolean marblePlacement;
    
    private boolean quadrantTruned;
    
    public Player(String name) {
        this.name = name;
        this.marbles = new ArrayList<>(18);
        //Quand le joueur commence il n'est pas obligé de tourner le quadrant du coup true au lieu de commencer à false.
        this.marblePlacement = true;
        this.quadrantTruned = true;
    }
    
    public void fillHand(Marble marbleColor) {
        for (Marble marble : this.marbles) {
            this.marbles.add(marbleColor);
        }
    }
    
    public Marble getMarble() {
        return this.marbles.remove(0);
    }
    
    public boolean isPlayerPlaceAMarble() {
        return this.marblePlacement;
    }
    
    public boolean isPlayerTurnAQuadrant() {
        return this.quadrantTruned;
    }
}
