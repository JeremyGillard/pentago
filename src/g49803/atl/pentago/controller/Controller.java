package g49803.atl.pentago.controller;

import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.view.View;

/**
 *
 * @author g49803
 */
public class Controller {

    private final Pentago pentago;

    private final View view;

    public Controller(Pentago pentago, View view) {
        this.pentago = pentago;
        this.view = view;
    }

    public void startGame() {

        while (!pentago.isThereEnoughPlayer()) {
            try {
                pentago.addNewPlayer(view.askForNewPlayer());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        
        pentago.start();
        
        while (!pentago.isOver()) {
            
            String[] placeMarbleCmd = view.placeMarbleCmd();
            pentago.placeMarble(Integer.parseInt(placeMarbleCmd[0]), 
                                Integer.parseInt(placeMarbleCmd[1]));
            view.displayBoard();
            
            String[] turnQuadrantCmd = view.turnQuadrantCmd();
            pentago.rotateQuadrant(Integer.parseInt(turnQuadrantCmd[0]),
                                   Boolean.parseBoolean(turnQuadrantCmd[1]));
            view.displayBoard();
        }
        
        view.displayWinner();

    }

}
