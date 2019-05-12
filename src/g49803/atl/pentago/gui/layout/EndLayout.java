package g49803.atl.pentago.gui.layout;

import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.gui.components.ButtonComponent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
 * This class allows to display the end layout of the pentago game.
 * 
 * @author Jeremy Gillard
 */
public class EndLayout extends StackPane {
    
    private ImageView imageView;
    
    private VBox endRoot;
    
    private Label winnerLabel;
    
    /**
     * Allows to create the end layout of the pentago game.
     * 
     * @param message the message to display.
     */
    public EndLayout(String message) {
        initWinnerLabel(message);
        initVisual();
        ButtonComponent buttonQuit = new ButtonComponent("Quit");
        ButtonComponent buttonRestart = new ButtonComponent("Restart");
        buttonRestartBehavior(buttonRestart);
        buttonQuitBehavior(buttonQuit);
        arrangement(buttonRestart, buttonQuit);
    }

    private void arrangement(ButtonComponent buttonRestart, ButtonComponent buttonQuit) {
        endRoot.getChildren().addAll(winnerLabel, buttonRestart, buttonQuit);
        this.getChildren().addAll(imageView, endRoot);
    }
    
    private void buttonRestartBehavior(Button buttonRestart) {
        buttonRestart.setOnMouseClicked((event) -> {
            IntroductionLayout introductionLayout = new IntroductionLayout(new Pentago());
            this.getScene().setRoot(introductionLayout);
        });
    }

    private void buttonQuitBehavior(Button buttonQuit) {
        buttonQuit.setOnMouseClicked((event) -> {
            ((Stage) this.getScene().getWindow()).close();
        });
    }

    private void initVisual() {
        imageView = new ImageView("file:media/img/wood.jpg");
        endRoot = new VBox();
        endRoot.setAlignment(Pos.CENTER);
        endRoot.setPadding(new Insets(40, 40, 40, 40));
        endRoot.setSpacing(40);
        endRoot.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 50, 0.4), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void initWinnerLabel(String message) {
        winnerLabel = new Label(message);
        winnerLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 40));
        winnerLabel.setTextFill(Color.rgb(68, 25, 7));
    }
    
}
