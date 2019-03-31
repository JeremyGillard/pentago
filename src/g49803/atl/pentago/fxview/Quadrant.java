/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.fxview;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jeremy
 */
public class Quadrant extends Group {
    
    private StackPane core;
    
    private Rectangle shape;
    
    private GridPane content;
    
    public Quadrant() {
        initShape();
        initContent();
        initCore();
        fixContent();
    }

    private void initCore() {
        core = new StackPane();
        core.setPrefWidth(300);
        core.setPrefHeight(300);
        core.getChildren().addAll(shape, content);
    }

    private void initContent() {
        content = new GridPane();
        content.setGridLinesVisible(false);
        List<Circle> list = new ArrayList();
        for (int i = 0; i < 9; i++) {
            list.add(new Circle(35));
            list.get(i).setFill(Color.GAINSBORO);
        }
        int o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                content.add(list.get(o), i, j);
                o++;
            }
        }
        content.setAlignment(Pos.CENTER);
        content.setHgap(25);
        content.setVgap(25);
    }

    private void initShape() {
        shape = new Rectangle();
        shape.setWidth(300);
        shape.setHeight(300);
        shape.setFill(Color.ANTIQUEWHITE);
        shape.setArcHeight(70);
        shape.setArcWidth(70);
    }
    
    private void fixContent() {
        this.getChildren().add(core);
    }
    
    public StackPane getCore() {
        return core;
    }

}
