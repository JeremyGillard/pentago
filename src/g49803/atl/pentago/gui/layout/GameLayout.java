package g49803.atl.pentago.gui.layout;

import g49803.atl.pentago.gui.components.BoardFrame;
import g49803.atl.pentago.model.Marble;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.model.State;
import g49803.atl.pentago.util.Observer;
import g49803.atl.pentago.gui.components.ButtonComponent;
import g49803.atl.pentago.gui.components.IdentityPlayerFrame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * This class allows to display the pentago game.
 * 
 * @author Jeremy Gillard
 */
public class GameLayout extends VBox implements Observer {
    
    private final Pentago pentago;
    
    private Label labelPentagoMessage;
    
    private boolean isAlreadyGameCommunication;
    
    private IdentityPlayerFrame blackPlayerFrame;
    
    private IdentityPlayerFrame whitePlayerFrame;
    
    /**
     * Allows to create the game layout of the pentago game.
     * 
     * @param pentago the pentago game concerned.
     * @param firstPlayerName the first player's name.
     * @param secondPlayerName the second player's name.
     */
    public GameLayout(Pentago pentago, String firstPlayerName, String secondPlayerName) {
        this.pentago = pentago;
        this.pentago.addObserver(this);
        this.isAlreadyGameCommunication = false;
        initPentagoMessage();
        HBox hBoxPlayersBoard = initPlayableFrame(pentago, secondPlayerName, firstPlayerName);
        ButtonComponent buttonQuit = new ButtonComponent("Quit");
        initBackground();
        arrangement(hBoxPlayersBoard, buttonQuit);
        behavior(buttonQuit);
    }

    private void arrangement(HBox hBoxPlayersBoard, ButtonComponent buttonQuit) {
        this.getChildren().addAll( labelPentagoMessage, hBoxPlayersBoard, buttonQuit);
        this.setAlignment(Pos.CENTER);
    }

    private void initBackground() {
        Image image = new Image("file:media/img/wood.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
    }

    private void initPentagoMessage() {
        labelPentagoMessage = new Label("Message : ");
        labelPentagoMessage.setMinSize(600, 45);
        labelPentagoMessage.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7, 0.5), new CornerRadii(10), new Insets(0, -10, 0, -10))));
        labelPentagoMessage.setFont(Font.font("sans-serif", FontWeight.BOLD, 25));
        labelPentagoMessage.setTextFill(Color.rgb(196, 168, 121));
    }

    private void behavior(ButtonComponent buttonQuit) {
        buttonQuit.setOnMouseClicked((event) -> {
            ((Stage) this.getScene().getWindow()).close();
        });
    }

    private HBox initPlayableFrame(Pentago pentago1, String secondPlayerName, String firstPlayerName) {
        HBox hBoxPlayersBoard = new HBox();
        hBoxPlayersBoard.setAlignment(Pos.CENTER);
        hBoxPlayersBoard.setSpacing(30);
        BoardFrame boardFrame = new BoardFrame(pentago1);
        blackPlayerFrame = new IdentityPlayerFrame(secondPlayerName, Marble.BLACK);
        whitePlayerFrame = new IdentityPlayerFrame(firstPlayerName, Marble.WHITE);
        hBoxPlayersBoard.getChildren().addAll(whitePlayerFrame, boardFrame, blackPlayerFrame);
        return hBoxPlayersBoard;
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
        } else if (pentago.getCurrentGameState() == State.OVERWINNER) {
            sceneChange("The winner is : " + pentago.getCurrentPlayer().getName() +"\n      Congratulation !!");
            winnerSounds();
        } else if (pentago.getCurrentGameState() == State.OVERNOWINNER) {
            sceneChange("There is no winner for this round...\n\t\t Take revenge?!");
            noWinnerSounds();
        }
    }
    
    private void sceneChange(String endMessage) {
        EndLayout endLayout = new EndLayout(endMessage);
        this.getScene().setRoot(endLayout);
    }
    
    private void winnerSounds() {
        AudioClip sound1 = new AudioClip("file:media/sound/endGame.wav");
        sound1.setVolume(0.2);
        sound1.play();
        AudioClip sound2 = new AudioClip("file:media/sound/winnerVoice.wav");
        sound2.setVolume(0.2);
        sound2.play();
    }
    
    private void noWinnerSounds() {
        AudioClip sound1 = new AudioClip("file:media/sound/revenge.wav");
        sound1.setVolume(0.1);
        sound1.play();
    }
    
}
