package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 *
 * @author Jeremy
 */
public class RotationButton extends StackPane {
    
    private final Pentago pentago;
    
    private Circle circle;
    
    private Label label;
    
    public RotationButton(int quadrantNumber, boolean clockwise, Pentago pentago, Lighting lightingEffect) {
        this.pentago = pentago;
        visualInitialization(lightingEffect, clockwise);
        behavior(quadrantNumber, clockwise);
        arrangement();
    }

    private void behavior(int quadrantNumber, boolean clockwise) {
        label.setOnMouseClicked((e) -> {
            circle.setFill(Color.rgb(238, 195, 157)); // A voir si l'effet reste ou pas
            this.pentago.rotateQuadrant(quadrantNumber, clockwise);
        });
    }

    private void visualInitialization(Lighting lightingEffect, boolean clockwise) {
        circle = new Circle(25);
        circle.setFill(Color.rgb(208, 165, 127));
        circle.setEffect(lightingEffect);
        String representationChar;
        if (clockwise) {
            representationChar = "⟳";
        } else {
            representationChar = "⟲";
        }
        label = new Label(representationChar);
        label.setFont(new Font(40));
    }

    private void arrangement() {
        this.getChildren().addAll(circle, label);
        GridPane.setMargin(this, new Insets(20, 20, 20, 20));
    }
    
}
