package g49803.atl.pentago.view;

import g49803.atl.pentago.model.Marble;
import g49803.atl.pentago.model.Pentago;
import java.util.Scanner;

/**
 *
 * @author g49803
 */
public class View {
    
    private Scanner in;
    
    private Pentago pentago;
    
    public View(Pentago pentago) {
        this.in = new Scanner(System.in);
        this.pentago = pentago;
    }
    
    public String askPlayerName() {
        System.out.print("Votre nom svp : ");
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
    
    public String[] arrayCmd() {
        System.out.print(">> ");
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
