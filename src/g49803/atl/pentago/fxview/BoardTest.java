/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jeremy
 */
public class BoardTest extends GridPane {
    
    private Pentago pentago;
    
    public BoardTest(Pentago pentago) {
        this.pentago = pentago;
        
        Quadrant[] board = new Quadrant[4];
        
        RotationButton[] boardControlButtons = new RotationButton[8];
    }

}
