package g49803.atl.pentago.model;

/**
 * This class represents a player who will take part in the pentago game.
 *
 * @author Jeremy Gillard
 */
public class Player {

    private final String name;

    private Marble color;

    /**
     * Create a player with a certain name.
     *
     * @param name the name of the player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Sets the marble color of the player.
     *
     * @param color the player's color.
     */
    void setColor(Marble color) {
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
