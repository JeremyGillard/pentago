package be.jeremygillard.pentago.console;

import be.jeremygillard.pentago.console.controller.Controller;
import be.jeremygillard.pentago.model.Pentago;
import be.jeremygillard.pentago.console.view.View;

/**
 * This class is the focus of our application. It's in charge of launching the
 * game. Creating the model, the view and the controller by binding them.
 *
 * @author Jeremy Gillard
 */
public class Main {
    
    public static void main(String[] args) {
        
        Pentago pentago = new Pentago();
        View view = new View(pentago);
        Controller controller = new Controller(pentago, view);
        
        controller.startGame();
        
    }
    
}
