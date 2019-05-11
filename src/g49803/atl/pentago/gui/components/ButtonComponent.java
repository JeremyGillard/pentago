package g49803.atl.pentago.gui.components;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Jeremy
 */
public class ButtonComponent extends Button {
    
    public ButtonComponent(String label) {
        super(label);
        arrangement();
        defaultBehavior();
    }

    private void arrangement() {
        this.setMinSize(150, 30);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7), new CornerRadii(10), Insets.EMPTY)));
        this.setFont(Font.font("sans-serif", FontWeight.BOLD, 25));
        this.setTextFill(Color.rgb(196, 168, 121));
    }
    
    private void defaultBehavior() {
        this.setOnMouseEntered((event) -> {
            this.getScene().setCursor(Cursor.HAND);
        });

        this.setOnMouseExited((event) -> {
            try {
                this.getScene().setCursor(Cursor.DEFAULT);
            } catch (NullPointerException e) {}
        });
        
        this.setOnMousePressed((event) -> {
            onClickSound();
        });
    }
    
    private void onClickSound() {
        AudioClip sound = new AudioClip("file:media/sound/clickButton.wav");
        sound.setVolume(0.2);
        sound.play();
    }
    
}
