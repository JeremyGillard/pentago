package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jeremy Gillard
 */
public class View extends Application {
    
    /**
     * The main entry point for all JavaFX applications. 
     * The start method is called after the init method has returned, and after 
     * the system is ready for the application to begin running. 
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        
        Pentago pentago = new Pentago();
        
        //effet light spot et lightning
        Label label = new Label("Current Player : ");
        Board board = new Board(pentago, lightingEffect());
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, board);
        
        //Pour l'image de fond du la scene
        Image image = new Image("file:img/wood.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        //Pour la taille de la fenêtre
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
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
    
}
