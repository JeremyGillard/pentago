package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.GameStateException;
import g49803.atl.pentago.model.Pentago;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * This class allows to create a rotation button with a certain graphic charter 
 * to turn the pentago quadrants.
 * 
 * @author Jeremy
 */
public class RotationButton extends StackPane {
    
    private final Pentago pentago;
    
    private Circle circle;
    
    private Label label;
    
    /**
     * Allows to create a rotation button linked with a pentago game, a quandrant 
     * and assign with a rotation sense. The lightingEffect is only here for an
     * esthetic reason.
     * 
     * @param quadrantNumber the number of the quadrant to which it is linked.
     * @param clockwise the sense. Clockwise or not.
     * @param pentago the pentago game to which it is linked.
     * @param lightingEffect the esthetic effect.
     */
    public RotationButton(int quadrantNumber, boolean clockwise, Pentago pentago, Lighting lightingEffect) {
        this.pentago = pentago;
        visualInitialization(lightingEffect, clockwise);
        behavior(quadrantNumber, clockwise);
        arrangement();
    }

    private void behavior(int quadrantNumber, boolean clockwise) {
        label.setOnMouseEntered((event) -> {
            this.getScene().setCursor(Cursor.HAND);
        });

        label.setOnMouseExited((event) -> {
            this.getScene().setCursor(Cursor.DEFAULT);
        });
        
        label.setOnMouseClicked((event) -> {
            circle.setFill(Color.rgb(238, 195, 157));
            try {
                this.pentago.rotateQuadrant(quadrantNumber, clockwise);
                slideClickSound();
            } catch (IllegalArgumentException | GameStateException e) {
                Parent parent = this.getParent();
                while (!(parent instanceof GamePane)) {
                    parent = parent.getParent();
                }
                ((GamePane) parent).setGameCommunication(e.getMessage());
            }
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
    
    private void slideClickSound() {
        AudioClip sound = new AudioClip("file:media/sound/slideBoard.wav");
        sound.setVolume(0.5);
        sound.play();
    }
}
