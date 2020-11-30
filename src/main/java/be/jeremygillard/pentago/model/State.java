package be.jeremygillard.pentago.model;

/**
 * This class represents the states in which the pentago game can be found.
 * 
 * @author Jeremy Gillard
 */
public enum State {
    
    PLACEMENT("place a marble"),
    ROTATION("turn a quadrant"),
    OVERWINNER("finish the game (with winner)"),
    OVERNOWINNER("finish the game (no winner)");
    
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
