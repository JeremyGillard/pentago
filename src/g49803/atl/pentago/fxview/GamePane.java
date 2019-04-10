/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Marble;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.model.State;
import g49803.atl.pentago.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This class is the main graphical representation of the pentago game.
 *
 * @author Jeremy Gillard
 */
public class GamePane extends VBox implements Observer {

    private final Pentago pentago;

    private Label currentPlayerLabel;

    private final Board board;

    private Label gameCommunicationLabel;

    private Button quitButton;

    private boolean gameCommunicationYet;

    /**
     * Allows to create a gamePane with a pentago model and a light effect.
     *
     * @param pentago the pentago game model.
     * @param lightingEffect the visual effect.
     */
    public GamePane(Pentago pentago, Lighting lightingEffect) {
        this.pentago = pentago;
        this.setSpacing(15);
        this.setAlignment(Pos.CENTER);
        this.board = new Board(pentago, lightingEffect);
        initCurrentPlayerLabel();
        initGameCommunicationLabel();
        initQuitButton();
        initBackground();
        arrangement();
        behavior();
    }

    private void initBackground() {
        Image image = new Image("file:media/img/wood.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
    }

    private void initQuitButton() {
        quitButton = new Button("Quit");
        quitButton.setMinSize(70, 25);
        quitButton.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7), new CornerRadii(10), Insets.EMPTY)));
        quitButton.setFont(Font.font("sans-serif", FontWeight.BOLD, 25));
        quitButton.setTextFill(Color.rgb(176, 148, 101));
    }

    private void initGameCommunicationLabel() {
        gameCommunicationLabel = new Label("Message : ");
        gameCommunicationLabel.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7), new CornerRadii(10), new Insets(-5, -10, -5, -10))));
        gameCommunicationLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
        gameCommunicationLabel.setTextFill(Color.rgb(176, 148, 101));
        gameCommunicationYet = false;
    }

    private void changeGameCommunicationLabelColor() {
        if (gameCommunicationYet) {
            gameCommunicationLabel.setTextFill(Color.rgb(138, 95, 57));
            gameCommunicationYet = false;
        } else {
            gameCommunicationLabel.setTextFill(Color.rgb(176, 148, 101));
            gameCommunicationYet = true;
        }
    }

    private void initCurrentPlayerLabel() {
        String firstName = pentago.getCurrentPlayer().getName();
        currentPlayerLabel = new Label("Current Player : " + firstName);
        currentPlayerLabel.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7), new CornerRadii(10), new Insets(-5, -10, -5, -10))));
        currentPlayerLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
        currentPlayerLabel.setTextFill(Color.rgb(255, 230, 158));
    }

    private void arrangement() {
        this.getChildren().addAll(currentPlayerLabel, board, gameCommunicationLabel, quitButton);
    }

    private void behavior() {
        pentago.addObserver(this);

        quitButton.setOnMouseEntered((event) -> {
            this.getScene().setCursor(Cursor.HAND);
        });

        quitButton.setOnMouseExited((event) -> {
            this.getScene().setCursor(Cursor.DEFAULT);
        });

        quitButton.setOnMouseClicked((MouseEvent event) -> {
            onClickSound();
            ((Stage) this.getScene().getWindow()).close();
        });
    }

    void setGameCommunication(String communication) {
        gameCommunicationLabel.setText("Message : " + communication);
        changeGameCommunicationLabelColor();
    }

    @Override
    public void update() {
        if (pentago.getCurrentGameState() != State.OVER) {
            currentPlayerLabel.setText("Current Player : " + pentago.getCurrentPlayer().getName());
            currentPlayerLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
            if (pentago.getCurrentPlayer().getColor() == Marble.WHITE) {
                currentPlayerLabel.setTextFill(Color.rgb(185, 160, 88));
            } else {
                currentPlayerLabel.setTextFill(Color.rgb(0, 0, 0));
            }
        } else {
            nextScene();
        }
    }
    
    private void nextScene() {
        EndPane endPane = new EndPane(pentago.getCurrentPlayer().getName());
        Scene gameScene = new Scene(endPane,
                Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight());
        Stage stage = ((Stage) this.getScene().getWindow());
        stage.setScene(gameScene);
        stage.setFullScreen(true);
    }
    
    private void onClickSound() {
        AudioClip sound = new AudioClip("file:media/sound/clickButton.wav");
        sound.setVolume(0.2);
        sound.play();
    }

}
