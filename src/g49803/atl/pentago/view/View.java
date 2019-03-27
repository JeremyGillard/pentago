package g49803.atl.pentago.view;

import g49803.atl.pentago.model.Marble;
import g49803.atl.pentago.model.Pentago;
import java.util.Scanner;

/**
 *
 * @author g49803
 */
public class View {
    
    private final Scanner in;
    
    private final Pentago pentago;
    
    public View(Pentago pentago) {
        this.in = new Scanner(System.in);
        this.pentago = pentago;
    }
    
    public String askPlayerName() {
        System.out.print("Nom du joueur " + (pentago.getPlayers().size() + 1) 
                + " : ");
        return in.nextLine();
    }
    
    public boolean takeRevenge() {
        System.out.print("Do you want to take your revenge ? (y/n) : ");
        String response = in.nextLine().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.print("This answear is not correct. Please try again "
                    + "(Y/y or N/n) : ");
            response = in.nextLine().toLowerCase();
        }
        return response.equals("y");
    }
    
    public String[] placeMarbleCmd() {
        System.out.print("Marble placement of " 
                + pentago.getCurrentPlayer().getName()
                + " patern(int int)>> ");
        return this.in.nextLine().toLowerCase().split(" ");
    }
    
    public String[] turnQuadrantCmd() {
        System.out.print("Quadrant Rotation of " 
                + pentago.getCurrentPlayer().getName()
                + " patern(int bool)>> ");
        return this.in.nextLine().toLowerCase().split(" ");
    }
    
    public String[] arrayCmd(String cmdType) {
        System.out.print("(" + cmdType + ")>> ");
        return this.in.nextLine().toLowerCase().split(" ");
    }
    
    public void displayBoard() {
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
