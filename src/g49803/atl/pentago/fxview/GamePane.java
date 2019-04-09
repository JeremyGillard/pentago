/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy
 */
public class GamePane extends VBox implements Observer {
    
    private final Pentago pentago;
    
    private Label currentPlayerLabel;
    
    private final Board board;
    
    private Label gameCommunicationLabel;
    
    private Button quitButton;
    
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
        Image image = new Image("file:img/wood.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
    }

    private void initQuitButton() {
        quitButton = new Button("Quit");
        quitButton.setMinSize(70, 25);
        quitButton.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7), new CornerRadii(10), Insets.EMPTY)));
        quitButton.setFont(Font.font("sans-serif", FontWeight.BOLD, 25));
        quitButton.setTextFill(Color.rgb(138, 95, 57));
    }

    private void initGameCommunicationLabel() {
        gameCommunicationLabel = new Label("Message : ");
        gameCommunicationLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
        gameCommunicationLabel.setTextFill(Color.rgb(68, 25, 7));
    }

    private void initCurrentPlayerLabel() {
        String firstName = pentago.getCurrentPlayer().getName();
        currentPlayerLabel = new Label("Current Player : " + firstName);
        currentPlayerLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
        currentPlayerLabel.setTextFill(Color.rgb(68, 25, 7));
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
            ((Stage) this.getScene().getWindow()).close();
        });
    }
    
    void setGameCommunication(String communication) {
        gameCommunicationLabel.setText("Message : " + communication);
    }

    @Override
    public void update() {
        currentPlayerLabel.setText("Current Player : " + pentago.getCurrentPlayer().getName());
        currentPlayerLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
        currentPlayerLabel.setTextFill(Color.rgb(68, 25, 7));
    }
    
}