package g49803.atl.pentago.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g49803
 */
public class Pentago {
    
    private List<Player> playerList;
    
    private Player currentPlayer;
    
    private final Board board;
    
    public Pentago() {
        this.playerList = new ArrayList<>(2);
        this.currentPlayer = null;
        this.board = new Board();
    }
    
    public void addPlayer(String name) throws Exception {
        if (this.playerList.size() < 2) {
            this.playerList.add(new Player(name));
        } else {
            throw new Exception("To many player");
        }
    }
    
    public void start() {
        
    }
    
    public void placeMarble(int col, int row) {
        
    }
}
