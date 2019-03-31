package g49803.atl.pentago.view;

import g49803.atl.pentago.model.Marble;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.util.Observer;
import java.util.Scanner;

/**
 * This class allows to display the Pentago game in the terminal.
 * 
 * @author Jeremy Gillard
 */
public class View implements Observer {
    
    private final Scanner in;
    
    private final Pentago pentago;
    
    /**
     * Allows to create a terminal view of a pentago game.
     * 
     * @param pentago the game concerned.
     */
    public View(Pentago pentago) {
        this.in = new Scanner(System.in);
        this.pentago = pentago;
    }
    
    /**
     * asks for the name of the player who will be added and returns it.
     * 
     * @return the player's name.
     */
    public String askForNewPlayer() {
        System.out.print("Please enter a name : ");
        return in.nextLine();
    }
    
    /**
     * Returns the command captured to place a marble 
     * in the terminal as a string array.
     * 
     * @return the command captured to place a marble 
     * in the terminal as a string array.
     */
    public String[] placeMarbleCmd() {
        System.out.print("(Marble placement by " + 
                pentago.getCurrentPlayer().getName() +")>> ");
        return this.in.nextLine().toLowerCase().split(" ");
    }

    /**
     * Returns the command captured to turn a quadrant in the terminal 
     * as a string array.
     * 
     * @return the command captured to turn a quadrant in the terminal 
     * as a string array.
     */
    public String[] turnQuadrantCmd() {
        System.out.print("(Quadrant rotation by " 
                + pentago.getCurrentPlayer().getName() +")>> ");
        return this.in.nextLine().toLowerCase().split(" ");
    }
    
    /**
     * Display the winner of the game.
     */
    public void displayWinner() {
        System.out.println("The winner is "
                + pentago.getCurrentPlayer().getName());
    }

    /**
     * Update the board terminal view.
     */
    @Override
    public void update() {
        displayBoard();
    }
    
    private void displayBoard() {
        String description = "";
        for (Marble[] marbles : pentago.getBoard().getArrangement()) {
            for (Marble marble : marbles) {
                if (marble == null) {
                    description += " ";
                } else {
                    if (marble == Marble.BLACK) {
                        description += "#";
                    } else {
                        description += "O";
                    }
                }
            }
            description += "\n";
        }
        System.out.println(description);
    }

}
