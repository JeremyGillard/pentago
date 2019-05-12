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

    private static final int NB_MAX_PLAYER = 2;
    
    private static final int BOARD_SIDE = 6;

    private final Board board;

    private final List<Player> players;

    private Player currentPlayer;

    private State state;

    private final List<Observer> observerList;
    
    private int lastQuadrantRotated;
    
    private boolean isLastRotationClockwise;

    /**
     * Allows to create a pentago game.
     */
    public Pentago() {
        board = new Board(BOARD_SIDE);
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
            throw new IllegalStateException("There is not enough player"
                    + " to play this game");
        }
        players.get(0).setColor(Marble.WHITE);
        currentPlayer = players.get(0);
        players.get(1).setColor(Marble.BLACK);

        state = State.PLACEMENT;
    }

    /**
     * Allows to place a marble on the board at coordinates (col, row).
     * 
     * @param x the column of the coordonitates
     * @param y the row of the coordinates
     */
    public void placeMarble(int x, int y) {
        checkState(State.PLACEMENT);
        if (!board.isEmptyCell(x, y)) {
            throw new IllegalArgumentException("There is already a marble "
                    + "in this cell");
        }
        if (x >= BOARD_SIDE || y >= BOARD_SIDE) {
            throw new IllegalArgumentException("The coordinates entered "
                    + "are outside the area covered by the board");
        }
        board.fillCell(x, y, currentPlayer.getColor());
        isOver();
        this.notifyObservers();
        state = State.ROTATION;
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
        checkState(State.ROTATION);
        if (quadrantNumber < 0 || quadrantNumber > 4) {
            throw new IllegalArgumentException("The number of quadrant does not exist, it will be between 1 and 4");
        }
        board.turnQuadrant(quadrantNumber, clockwiseDirection);
        lastQuadrantRotated = quadrantNumber;
        isLastRotationClockwise = clockwiseDirection;
        isOver();
        this.notifyObservers();
        nextPlayer();
    }
    
    private void nextPlayer() {
        if (players.get(0) == currentPlayer) {
            currentPlayer = players.get(1);
        } else {
            currentPlayer = players.get(0);
        }
        state = State.PLACEMENT;
    }
    
    private void checkState(State stateTest) {
        if (state != stateTest) {
            throw new GameStateException("inconsistent game status to "
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
    boolean didAnyoneWin() {
        if (board.checkWinAlignmentFor(currentPlayer.getColor(), BOARD_SIDE) != null) {
            state = State.OVERWINNER;
            return true;
        }
        return false;
    }

    /**
     * Returns true if the game is over.
     * 
     * @return true if the game is over.
     */
    public boolean isOver() {
        if (board.isFull()) {
            state = State.OVERNOWINNER;
            return true;
        }
        return didAnyoneWin();
    }
    
    /**
     * Returns the marble at a certain position on the board.
     * 
     * @param x the x position of the marble.
     * @param y the y position of the marble.
     * @return the marble at a certain position on the board.
     */
    public Marble getMarbleAt(int x, int y) {
        return board.getMarbleAtPosition(x, y);
    }
    
    /**
     * Represents the number that will be assigned to the future added player. 
     * This method exists mainly to be able to address more correctly 
     * in the view before having a name.
     * 
     * @return the number that will be assigned to the future player who will 
     * be added to the game.
     */
    public int getNbPlayer() {
        return players.size();
    }
    
    /**
     * Returns the current state of the game.
     * 
     * @return the current state of the game.
     */
    public State getCurrentGameState() {
        return state;
    }
    
    /**
     * Returns the number of the last quadrant rotated.
     * 
     * @return the number of the last quadrant rotated.
     */
    public int getLastQuadrantRotated() {
        return lastQuadrantRotated;
    }
    
    /**
     * Returns true if the last rotation was clockwise.
     * 
     * @return true if the last rotation was clockwise.
     */
    public boolean isLastRotationClockwise() {
        return isLastRotationClockwise;
    }

    @Override
    public void addObserver(Observer obs) {
        observerList.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observerList.remove(obs);
    }

    private void notifyObservers() {
        observerList.forEach((observer) -> {
            observer.update();
        });
    }
}
