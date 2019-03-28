package g49803.atl.pentago.model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author g49803
 */
public class Pentago {

    private final Board board;
    
    private final List<Player> players;
    
    private ListIterator currentPlayer;
    
    public Pentago() {
        board = new Board();
        players = new LinkedList();
        currentPlayer = null;
    }
    
    public void addNewPlayer(String name) throws Exception {
        if (players.size() < 2) {
            players.add(new Player(name));
        } else {
            throw new Exception("There is enough player for this game");
        }
    }
    
    public void start() {
        
    }
    
    public void placeMarble(int col, int row) {
        
    }
    
    public void rotateQuadrant(int quadrantNumber, boolean clockwiseDirection) {
        
    }
    
    public void didAnyoneWin() {
        
    }
    
    public void isOver() {
        
    }
    
    
    
}
