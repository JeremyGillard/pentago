package g49803.atl.pentago.view;

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
        System.out.println("Votre nom svp : ");
        return in.nextLine();
    }
    
}
