package g49803.atl.pentago;

import g49803.atl.pentago.controller.Controller;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.view.View;

/**
 * This class is the focus of our application. It's in charge of launching the
 * game. Creating the model, the view and the controller by binding them.
 *
 * @author g49803
 */
public class Main {
    
    public static void main(String[] args) {
        
        Pentago pentago = new Pentago();
        View view = new View(pentago);
        Controller controller = new Controller(pentago, view);
        
        controller.startGame();
        
    }
    
}
