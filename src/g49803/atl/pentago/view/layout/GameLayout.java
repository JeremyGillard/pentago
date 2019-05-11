/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.view.layout;

import g49803.atl.pentago.model.Marble;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.model.State;
import g49803.atl.pentago.util.Observer;
import g49803.atl.pentago.view.components.ButtonComponent;
import g49803.atl.pentago.view.components.IdentityPlayerFrame;
import g49803.atl.pentago.view.components.QuadrantFrame;
import g49803.atl.pentago.view.components.RotationButtonComponent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy
 */
public class GameLayout extends VBox implements Observer {
    
    private final Pentago pentago;
    
    private final Label labelPentagoMessage;
    
    private boolean isAlreadyGameCommunication;
    
    private final QuadrantFrame[] board;
    
    private final IdentityPlayerFrame blackPlayerFrame;
    
    private final IdentityPlayerFrame whitePlayerFrame;
    
    public GameLayout(Pentago pentago, String firstPlayerName, String secondPlayerName) {
        this.pentago = pentago;
        this.pentago.addObserver(this);
        this.isAlreadyGameCommunication = false;
        
        labelPentagoMessage = new Label("Message : ");
        labelPentagoMessage.setMinSize(600, 45);
        labelPentagoMessage.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7, 0.5), new CornerRadii(10), new Insets(0, -10, 0, -10))));
        labelPentagoMessage.setFont(Font.font("sans-serif", FontWeight.BOLD, 25));
        labelPentagoMessage.setTextFill(Color.rgb(196, 168, 121));
        
        HBox hBoxPlayersBoard = new HBox();
        hBoxPlayersBoard.setAlignment(Pos.CENTER);
        hBoxPlayersBoard.setSpacing(30);
        
        // BOARD
        GridPane boardFrame = new GridPane();
        boardFrame.setHgap(8);
        boardFrame.setVgap(8);
        
        board = new QuadrantFrame[4];
        for (int i = 0; i < board.length; i++) {
            board[i] = new QuadrantFrame(i, pentago, lightingEffect());
        }
        
        RotationButtonComponent[] rotationButtons = new RotationButtonComponent[8];
        rotationButtons[0] = new RotationButtonComponent(0, true, pentago, lightingEffect());
        rotationButtons[1] = new RotationButtonComponent(0, false, pentago, lightingEffect());
        rotationButtons[2] = new RotationButtonComponent(1, true, pentago, lightingEffect());
        rotationButtons[3] = new RotationButtonComponent(1, false, pentago, lightingEffect());
        rotationButtons[4] = new RotationButtonComponent(2, true, pentago, lightingEffect());
        rotationButtons[5] = new RotationButtonComponent(2, false, pentago, lightingEffect());
        rotationButtons[6] = new RotationButtonComponent(3, true, pentago, lightingEffect());
        rotationButtons[7] = new RotationButtonComponent(3, false, pentago, lightingEffect());
        
        boardFrame.add(rotationButtons[0], 0, 1);
        boardFrame.add(rotationButtons[1], 1, 0);
        boardFrame.add(board[0], 1, 1);
        
        boardFrame.add(rotationButtons[2], 2, 0);
        boardFrame.add(rotationButtons[3], 3, 1);
        boardFrame.add(board[1], 2, 1);
        
        boardFrame.add(rotationButtons[4], 1, 3);
        boardFrame.add(rotationButtons[5], 0, 2);
        boardFrame.add(board[3], 2, 2);
        
        boardFrame.add(rotationButtons[6], 3, 2);
        boardFrame.add(rotationButtons[7], 2, 3);
        boardFrame.add(board[2], 1, 2);        
        
        // WHITE PLAYER
        blackPlayerFrame = new IdentityPlayerFrame(secondPlayerName, Marble.BLACK);
        // BLACK PLAYER
        whitePlayerFrame = new IdentityPlayerFrame(firstPlayerName, Marble.WHITE);
        
        hBoxPlayersBoard.getChildren().addAll(whitePlayerFrame, boardFrame, blackPlayerFrame);
        
        ButtonComponent buttonQuit = new ButtonComponent("Quit");
        
        Image image = new Image("file:media/img/wood.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
        
        this.getChildren().addAll( labelPentagoMessage, hBoxPlayersBoard, buttonQuit);
        this.setAlignment(Pos.CENTER);
        
        // BEHAV

        buttonQuit.setOnMouseClicked((event) -> {
            ((Stage) this.getScene().getWindow()).close();
        });
        
    }
    
    public void setGameCommunication(String message) {
        labelPentagoMessage.setText("Message : " + message);
        changeGameCommunicationLabelColor();
    }
    
    private void changeGameCommunicationLabelColor() {
        if (isAlreadyGameCommunication) {
            labelPentagoMessage.setTextFill(Color.rgb(158, 115, 77));
            isAlreadyGameCommunication = false;
        } else {
            labelPentagoMessage.setTextFill(Color.rgb(176, 148, 101));
            isAlreadyGameCommunication = true;
        }
    }
    
    @Override
    public void update() {
        if (pentago.getCurrentGameState() == State.ROTATION) {
            blackPlayerFrame.highlight();
            whitePlayerFrame.highlight();
        } else if (pentago.getCurrentGameState() == State.OVER) {
            sceneChange();
        }
    }
    
    private void sceneChange() {
        EndLayout endLayout = new EndLayout(pentago.getCurrentPlayer().getName());
        this.getScene().setRoot(endLayout);
    }
    
    private Lighting lightingEffect() {
        // EFFET DE LUMIERE
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-165.0f);
        Lighting l = new Lighting();
        l.setLight(light);
        l.setSurfaceScale(3.0f);
        return l;
    }
}
