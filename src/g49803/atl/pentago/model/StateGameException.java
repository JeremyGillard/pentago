package g49803.atl.pentago.model;

/**
 *
 * @author Jeremy Gillard
 */
public class StateGameException extends RuntimeException {
    
    /**
     * Creates a new instance of <code>StateGameException</code> without detail
     * message.
     */
    public StateGameException() {
        super();
    }

    /**
     * Constructs an instance of <code>GameException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public StateGameException(String msg) {
        super(msg);
    }

}
