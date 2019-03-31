package g49803.atl.pentago.model;

/**
 *
 * @author Jeremy Gillard
 */
public enum State {
    MARBLEPLACEMENT("place a marble"),
    QUADRANTROTATION("turn a quadrant"),
    NEXTPLAYER("move on the next player"), 
    ENDED("finish the game");
    
    private final String communicationSate;
    
    private State(String communication) {
        communicationSate = communication;
    }
    
    @Override
    public String toString() {
        return communicationSate;
    }
}