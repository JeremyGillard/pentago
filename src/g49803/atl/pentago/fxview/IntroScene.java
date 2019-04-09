/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.fxview;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

/**
 *
 * @author Jeremy
 */
public class IntroScene extends Scene {
    
    public IntroScene(Parent root) {
        super(root, Screen.getPrimary().getVisualBounds().getWidth(), 
                Screen.getPrimary().getVisualBounds().getHeight());
        
    }
    
}
