/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago;

import g49803.atl.pentago.controller.Controller;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.view.View;

/**
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
