/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.fxview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy
 */
public class EndPane extends StackPane {
    
    private ImageView imageView;
    
    private VBox endRoot;
    
    private Label winnerLabel;
    
    private Button quitButton;
    
    public EndPane(String winnerName) {
        winnerSounds();
        initWinnerLabel(winnerName);
        initVisual();
        initQuitButton();
        behavior();
        arrangement();
    }

    private void behavior() {
        quitButton.setOnMouseEntered((event) -> {
            this.getScene().setCursor(Cursor.HAND);
        });

        quitButton.setOnMouseExited((event) -> {
            this.getScene().setCursor(Cursor.DEFAULT);
        });

        quitButton.setOnMouseClicked((event) -> {
            ((Stage) this.getScene().getWindow()).close();
        });
    }

    private void initQuitButton() {
        quitButton = new Button("Quit");
        quitButton.setMinSize(100, 30);
        quitButton.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7), new CornerRadii(16), Insets.EMPTY)));
        quitButton.setFont(Font.font("sans-serif", FontWeight.BOLD, 40));
        quitButton.setTextFill(Color.rgb(138, 95, 57));
    }

    private void initVisual() {
        imageView = new ImageView("file:media/img/wood.jpg");
        endRoot = new VBox();
        endRoot.setAlignment(Pos.CENTER);
        endRoot.setPadding(new Insets(40, 40, 40, 40));
        endRoot.setSpacing(40);
        endRoot.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 50, 0.4), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void initWinnerLabel(String winnerName) {
        winnerLabel = new Label("The winner is : " + winnerName +"\n      Congratulation !!");
        winnerLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 40));
        winnerLabel.setTextFill(Color.rgb(68, 25, 7));
    }
    
    private void arrangement() {
        endRoot.getChildren().addAll(winnerLabel, quitButton);
        this.getChildren().addAll(imageView, endRoot);
    }
    
    private void winnerSounds() {
        AudioClip sound1 = new AudioClip("file:media/sound/endGame.wav");
        sound1.setVolume(0.2);
        sound1.play();
        AudioClip sound2 = new AudioClip("file:media/sound/winnerVoice.wav");
        sound2.setVolume(0.2);
        sound2.play();
    }
    
}
