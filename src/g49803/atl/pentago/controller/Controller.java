package g49803.atl.pentago.controller;

import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.model.GameStateException;
import g49803.atl.pentago.view.View;

/**
 * It's in this class that the dynamics of the game operate.
 *
 * @author Jeremy Gillard
 */
public class Controller {

    private final Pentago pentago;

    private final View view;

    /**
     * Link a game and a view to form a game in the playable sense.
     *
     * @param pentago the game concerned.
     * @param view the view concerned.
     */
    public Controller(Pentago pentago, View view) {
        this.pentago = pentago;
        this.view = view;
        this.pentago.addObserver(this.view);
    }

    /**
     * Allows to start a game part.
     */
    public void startGame() {

        while (!pentago.isThereEnoughPlayer()) {
            try {
                pentago.addNewPlayer(view.askForNewPlayer());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        pentago.start();

        this.mainGameFlow();

        view.displayWinner();

    }
    
    private void mainGameFlow() {
        while (!pentago.isOver()) {

            try {
                String[] placeMarbleCmd = view.placeMarbleCmd();
                pentago.placeMarble(Integer.parseInt(placeMarbleCmd[0]),
                        Integer.parseInt(placeMarbleCmd[1]));

                String[] turnQuadrantCmd = view.turnQuadrantCmd();
                pentago.rotateQuadrant(Integer.parseInt(turnQuadrantCmd[0])-1,
                        Integer.parseInt(turnQuadrantCmd[1]) == 1);
            } catch (GameStateException e) {
                break;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                mainGameFlow();
            }

        }
    }

}
