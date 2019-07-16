package be.jeremygillard.pentago.model;

/**
 * This class is a special exception for the pentago game.
 * 
 * @author Jeremy Gillard
 */
public class GameStateException extends IllegalStateException {
    
    /**
     * Creates a new instance of <code>StateGameException</code> without detail
     * message.
     */
    public GameStateException() {
        super();
    }

    /**
     * Constructs an instance of <code>GameException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GameStateException(String msg) {
        super(msg);
    }

}
