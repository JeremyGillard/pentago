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
    
    /*
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
*/

}
