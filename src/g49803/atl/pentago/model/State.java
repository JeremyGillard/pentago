package g49803.atl.pentago.model;

/**
 * this class represents the states in which the pentago game can be found.
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
    
    @Override
    public String toString() {
        return communicationSate;
    }
}
