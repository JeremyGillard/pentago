package g49803.atl.pentago.model;

/**
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
