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
    
    private boolean quadrantTurned;
    
    //les états marblePlacement et quadrantTurner peuvent éventuellement être placé dans la façade comme état du jeu.
    
    public Player(String name) {
        this.name = name;
        this.marbles = new ArrayList<>(18);
        //Quand le joueur commence il n'est pas obligé de tourner le quadrant du coup true au lieu de commencer à false.
        this.marblePlacement = true;
        this.quadrantTurned = true;
    }
    
    public void fillHand(Marble marbleColor) {
        for (Marble marble : marbles) {
            marbles.add(marbleColor);
        }
    }
    
    public Marble getMarble() {
        return marbles.remove(0);
    }
    
    public boolean didPlayerPlaceAMarble() {
        return marblePlacement;
    }
    
    public boolean didPlayerTurnAQuadrant() {
        return quadrantTurned;
    }
}
