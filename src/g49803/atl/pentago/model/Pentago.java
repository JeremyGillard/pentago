package g49803.atl.pentago.model;

import g49803.atl.pentago.util.Observable;
import g49803.atl.pentago.util.Observer;
import java.util.ArrayList;
import java.util.List;

/**
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

        state = State.MARBLEPLACEMENT;
    }

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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean didAnyoneWin() {
        return board.getArrangement()[0][5] != null;
    }

    public boolean isOver() {
        return didAnyoneWin();
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
        observerList.forEach((observer) -> {
            observer.update();
        });
    }
    
    public State getState() {
        return state;
    }
}
