package g49803.atl.pentago.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g49803
 */
public class Pentago {
    
    private final List<Player> playerList;
    
    private Player currentPlayer;
    
    private final Board board;
    
    
    public Pentago() {
        playerList = new ArrayList<>(2);
        currentPlayer = null;
        board = new Board();
    }
    
    public List<Player> getPlayers() {
        return playerList;
    }
    
    public void addPlayer(String name) throws Exception {
        if (playerList.size() < 2) {
            playerList.add(new Player(name));
        } else {
            throw new Exception("To many player");
        }
    }
    
    public boolean isThereEnoughtPlayer() {
        return playerList.size() < 2;
    }
    
    public void start() {
        playerList.get(0).fillHand(Marble.WHITE);
        playerList.get(1).fillHand(Marble.BLACK);
        currentPlayer = playerList.get(0);
    }
    
    public boolean isThereAWinner() {
        return true;
    }
    
    public boolean isEnded() {
        if (!board.isEmptyCell(0, 0)) {
            return true;
        }
        for (Marble[] marbles : board.getArrangement()) {
            for (Marble marble : marbles) {
                if (marble == null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void placeMarble(int col, int row, Player player) throws Exception {
        if (board.getArrangement()[col][row] != null) {
            throw new Exception("Il y a déjà un marble à cet endroit");
        }
        board.fillCell(col, row, player.getMarble());
    }
    
    public void turnQuadrant(int quadrantPosition, boolean clockwiseRotationDirection) {
        board.turnQuadrant(quadrantPosition, clockwiseRotationDirection);
    }
    
    public Board getBoard() {
        return board;
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
