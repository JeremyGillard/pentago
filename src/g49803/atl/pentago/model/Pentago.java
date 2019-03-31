package g49803.atl.pentago.model;

import g49803.atl.pentago.util.Observable;
import g49803.atl.pentago.util.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g49803
 */
public class Pentago implements Observable {

    private final int NB_MAX_PLAYER = 2;

    private final Board board;

    private final List<Player> players;

    private Player currentPlayer;

    private boolean placeMarble;

    private boolean turnQuadrant;

    private boolean ended;
    
    private final List<Observer> observerList;

    public Pentago() {
        board = new Board();
        players = new ArrayList();
        observerList = new ArrayList();
    }

    public void addNewPlayer(String name) {
        if (players.size() < NB_MAX_PLAYER) {
            players.add(new Player(name));
        } else {
            throw new RuntimeException("There is enough player for this game");
        }
    }
    
    public boolean isThereEnoughPlayer() {
        return players.size() == NB_MAX_PLAYER;
    }

    public void start() {
        players.get(0).setColor(Marble.WHITE);
        currentPlayer = players.get(0);
        players.get(1).setColor(Marble.BLACK);
        placeMarble = false;
        turnQuadrant = false;
        ended = false;
    }

    public void placeMarble(int col, int row) {
        if (ended) {
            throw new RuntimeException("It's not possible to place a marble if "
                    + "the game is ended.");
        }
        if (placeMarble) {
            throw new RuntimeException("There is already a marble placed for "
                    + "this player");
        }
        if (!board.isEmptyCell(col, row)) {
            throw new RuntimeException("There is already a marble in this cell");
        }
        board.fillCell(col, row, currentPlayer.getColor());
        placeMarble = true;
        if (didAnyoneWin()) {
            ended = true;
        }
        this.notifyObservers();
    }

    public void rotateQuadrant(int quadrantNumber, boolean clockwiseDirection) {
        if (ended) {
            throw new RuntimeException("It's not possible to turn a quadrant if "
                    + "the game is ended.");
        }
        if (turnQuadrant) {
            throw new RuntimeException("A quandrant has already been turned at "
                    + "this state of the game");
        }
        board.turnQuadrant(quadrantNumber, clockwiseDirection);
        turnQuadrant = true;
        if (didAnyoneWin()) {
            ended = true;
        }
        this.notifyObservers();
        nextPlayer();
    }

    private void nextPlayer() {
        if (placeMarble && turnQuadrant) {
            if (players.get(0) == currentPlayer) {
                currentPlayer = players.get(1);
            } else {
                currentPlayer = players.get(0);
            }
            placeMarble = false;
            turnQuadrant = false;
        }
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean didAnyoneWin() {
        return false;
    }

    public boolean isOver() {
        return false;
    }
    
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
        for (Observer observer : observerList) {
            observer.update();
        }
    }
}
