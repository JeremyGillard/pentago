package g49803.atl.pentago.controller;

import g49803.atl.pentago.model.Marble;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.model.Player;
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
    
    public void startGame() throws Exception {
        
        while (pentago.isThereEnoughtPlayer()) {
            try {
                pentago.addPlayer(view.askPlayerName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        do {
            pentago.start();
            
            while (!pentago.isEnded()) {

                for (Player player : pentago.getPlayers()) {
                    String[] marblePositionCmd = view.placeMarbleCmd();
                    pentago.placeMarble(Integer.parseInt(marblePositionCmd[0]), 
                                        Integer.parseInt(marblePositionCmd[1]),
                                        player);
                    view.displayBoard();

                    String[] rotationQuadrantDirectionCmd = view.turnQuadrantCmd();
                    System.out.println("Tourner Quadrant");
                    pentago.turnQuadrant(Integer.parseInt(rotationQuadrantDirectionCmd[0]), 
                                         Boolean.parseBoolean(rotationQuadrantDirectionCmd[1]));
                    view.displayBoard();
                }

            }
        } while (view.takeRevenge());

    }

}
