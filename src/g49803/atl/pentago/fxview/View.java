package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jeremy Gillard
 */
public class View extends Application {
    
    private Pentago pentago;
    
    private Rectangle2D visualBounds;
    
    /**
     * The main entry point for all JavaFX applications. 
     * The start method is called after the init method has returned, and after 
     * the system is ready for the application to begin running. 
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        visualBounds = Screen.getPrimary().getVisualBounds();
        pentago = new Pentago();
        
//        pentago.addNewPlayer("Jeremy");
//        pentago.addNewPlayer("Thomas");
//        pentago.start();
        
        IntroPane introPane = new IntroPane(pentago, lightingEffect());
        Scene introScene = new Scene(introPane, visualBounds.getWidth(), visualBounds.getHeight());
        primaryStage.setScene(introScene);
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
    
}
