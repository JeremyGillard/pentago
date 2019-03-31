package g49803.atl.pentago.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a player who will take part in the pentago game.
 *
 * @author g49803
 */
public class Player {

    private final String name;

    private Marble color;

//    private List<Marble> marbles;

    /**
     * Create a player with a certain name.
     *
     * @param name the name of the player.
     */
    public Player(String name) {
        this.name = name;
//        marbles = new ArrayList();
    }

//    public void fillHand(Marble color) {
//        if (color == Marble.BLACK) {
//            for (int i = 0; i < 18; i++) {
//                marbles.add(Marble.BLACK);
//            }
//        } else {
//            for (int i = 0; i < 18; i++) {
//                marbles.add(Marble.WHITE);
//            }
//        }
//    }
//    
//    public Marble getAMarble() {
//        return marbles.remove(0);
//    }

    /**
     * Sets the marble color of the player.
     *
     * @param color the player's color.
     */
    public void setColor(Marble color) {
        this.color = color;
    }

    /**
     * Returns the player's color.
     *
     * @return the player's color.
     */
    public Marble getColor() {
        return color;
    }

    /**
     * Returns the player's name.
     *
     * @return the player's name.
     */
    public String getName() {
        return name;
    }
}
