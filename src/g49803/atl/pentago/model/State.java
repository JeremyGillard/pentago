package g49803.atl.pentago.model;

/**
 * This class represents the states in which the pentago game can be found.
 * 
 * @author Jeremy Gillard
 */
public enum State {
    
    PLACEMENT("place a marble"),
    ROTATION("turn a quadrant"),
    OVER("finish the game");
    
    private final String communicationSate;
    
    private State(String communication) {
        communicationSate = communication;
    }
    
    /**
     * Returns a string representation of a state enum.
     * 
     * @return a string representation of a state enum.
     */
    @Override
    public String toString() {
        return communicationSate;
    }
}
