package g49803.atl.pentago.model;

import g49803.atl.pentago.util.Observable;
import g49803.atl.pentago.util.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the model representation of the pentago game.
 * It is in itself a representation of the rules of the game.
 * 
 * @author Jeremy Gillard
 */
public class Pentago implements Observable {

    private final int NB_MAX_PLAYER = 2;

    private final Board board;

    private final List<Player> players;

    private Player currentPlayer;

    private State state;

    private final List<Observer> observerList;

    /**
     * Allows to create a pentago game.
     */
    public Pentago() {
        board = new Board();
        players = new ArrayList();
        observerList = new ArrayList();
    }

    /**
     * Add a player with a certain name to the game.
     * 
     * @param name of the player.
     */
    public void addNewPlayer(String name) {
        if (players.size() < NB_MAX_PLAYER) {
            players.add(new Player(name));
        } else {
            throw new RuntimeException("There is enough player for this game");
        }
    }

    /**
     * Returns true if there is enought player.
     * 
     * @return true if there is enought player.
     */
    public boolean isThereEnoughPlayer() {
        return players.size() == NB_MAX_PLAYER;
    }

    /**
     * Allows to start the game.
     */
    public void start() {
        if (!isThereEnoughPlayer()) {
            throw new RuntimeException("There is not enough player"
                    + " to play this game");
        }
        players.get(0).setColor(Marble.WHITE);
        currentPlayer = players.get(0);
        players.get(1).setColor(Marble.BLACK);

        state = State.MARBLEPLACEMENT;
    }

    /**
     * Allows to place a marble on the board at coordinates (col, row).
     * 
     * @param col the column of the coordonitates
     * @param row the row of the coordinates
     */
    public void placeMarble(int col, int row) {
        checkState(State.MARBLEPLACEMENT);
        if (!board.isEmptyCell(col, row)) {
            throw new RuntimeException("There is already a marble in this cell");
        }
        board.fillCell(col, row, currentPlayer.getColor());
        state = State.QUADRANTROTATION;
        if (didAnyoneWin()) {
            state = State.ENDED;
        }
        this.notifyObservers();
    }

    /**
     * Allows to turn a quadrant 1, 2, 3 or 4 in a certain direction;
     * true -> clockwiseDirection
     * false -> inverse of clockwiseDirection
     * 
     * @param quadrantNumber the quadrant to turn.
     * @param clockwiseDirection the direction of the rotation.
     */
    public void rotateQuadrant(int quadrantNumber, boolean clockwiseDirection) {
        checkState(State.QUADRANTROTATION);
        board.turnQuadrant(quadrantNumber, clockwiseDirection);
        state = State.NEXTPLAYER;
        if (didAnyoneWin()) {
            state = State.ENDED;
        }
        this.notifyObservers();
        nextPlayer();
    }
    
    private void nextPlayer() {
        checkState(State.NEXTPLAYER);
        if (players.get(0) == currentPlayer) {
            currentPlayer = players.get(1);
        } else {
            currentPlayer = players.get(0);
        }
        state = State.MARBLEPLACEMENT;
    }
    
    private void checkState(State stateTest) {
        if (state != stateTest) {
            throw new StateGameException("inconsistent game status to "
                    + stateTest);
        }
    }

    /**
     * Returns the current player who have to play.
     * 
     * @return the current player who have to play.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns true if a player has won.
     * 
     * @return true if a player has won.
     */
    public boolean didAnyoneWin() {
        return board.getArrangement()[0][5] != null;
    }

    /**
     * Returns true if the game is over.
     * 
     * @return true if the game is over.
     */
    public boolean isOver() {
        return didAnyoneWin();
    }

    /**
     * Returns the board of the game.
     * 
     * @return the board of the game.
     */
    public Board getBoard() {
        return board;
    }

    @Override
    public void addObserver(Observer obs) {
        observerList.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observerList.remove(obs);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach((observer) -> {
            observer.update();
        });
    }
}
