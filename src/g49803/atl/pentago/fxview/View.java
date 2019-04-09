package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.util.Observer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jeremy Gillard
 */
public class View extends Application implements Observer {
    
    private Label currentPlayerLabel;
    
    private Pentago pentago;
    
    /**
     * The main entry point for all JavaFX applications. 
     * The start method is called after the init method has returned, and after 
     * the system is ready for the application to begin running. 
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        
        pentago = new Pentago();
        pentago.addObserver(this);
        
        IntroPane introPane = new IntroPane();
        Scene introScene = new Scene(introPane, visualBounds.getWidth(), visualBounds.getHeight());
        primaryStage.setScene(introScene);

        introPane.getQuitButton().setOnMouseEntered((event) -> {
            introScene.setCursor(Cursor.HAND);
        });

        introPane.getQuitButton().setOnMouseExited((event) -> {
            introScene.setCursor(Cursor.DEFAULT);
        });

        introPane.getQuitButton().setOnMouseClicked((event) -> {
            primaryStage.close();
        });

        introPane.getStartButton().setOnMouseEntered((event) -> {
            introScene.setCursor(Cursor.HAND);
        });

        introPane.getStartButton().setOnMouseExited((event) -> {
            introScene.setCursor(Cursor.DEFAULT);
        });
        
        
        Board board = new Board(pentago, lightingEffect());
        
        Label messageLabel = new Label("Message : ");
        messageLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
        messageLabel.setTextFill(Color.rgb(68, 25, 7));
        
        
        Button quitButton = new Button("Quit");
        quitButton.setMinSize(70, 25);
        quitButton.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7), new CornerRadii(10), Insets.EMPTY)));
        quitButton.setFont(Font.font("sans-serif", FontWeight.BOLD, 25));
        quitButton.setTextFill(Color.rgb(138, 95, 57));
        
        quitButton.setOnMouseClicked((event) -> {
            primaryStage.close();
        });
        
        VBox root = new VBox();
        root.setSpacing(15);
        
        //Pour l'image de fond du la scene
        Image image = new Image("file:img/wood.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        //Pour la taille de la fenêtre
        Scene gameScene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());

        Text currentPlayerTxt = new Text("Current Player : ");
        introPane.getStartButton().setOnMouseClicked((event) -> {
            if (!introPane.getFirstPlayerName().isEmpty() && !introPane.getSecondPlayerName().isEmpty()) {
                pentago.addNewPlayer(introPane.getFirstPlayerName());
                pentago.addNewPlayer(introPane.getSecondPlayerName());
                pentago.start();
                primaryStage.setScene(gameScene);
                primaryStage.setFullScreen(true);
                currentPlayerTxt.setText("CurrentPlayer : " + introPane.getFirstPlayerName());
            }
        });
        
        currentPlayerLabel = new Label(currentPlayerTxt.getText());
        currentPlayerLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
        currentPlayerLabel.setTextFill(Color.rgb(68, 25, 7));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(currentPlayerLabel, board, messageLabel, quitButton);
        
        
        primaryStage.setFullScreen(true);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private Lighting lightingEffect() {
        // EFFET DE LUMIERE
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0f);
        Lighting l = new Lighting();
        l.setLight(light);
        l.setSurfaceScale(5.0f);
        return l;
    }

    @Override
    public void update() {
        String description = "Current Player : " + pentago.getCurrentPlayer().getName();
        currentPlayerLabel.setText(description);
    }
    
}
