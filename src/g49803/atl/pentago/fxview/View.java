package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.media.AudioClip;
import static javafx.scene.media.MediaPlayer.INDEFINITE;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class is the instance of the graphical pentago game representation.
 *
 * @author Jeremy Gillard
 */
public class View extends Application {

    private Pentago pentago;

    private Rectangle2D visualBounds;

    /**
     * The main entry point for all JavaFX applications. The start method is
     * called after the init method has returned, and after the system is ready
     * for the application to begin running.
     *
     * Here, the introScene will be instantiated to take player's names and then
     * instantiated the gameScene with a pentago game started.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        //initSurroundingMusic();

        visualBounds = Screen.getPrimary().getVisualBounds();
        pentago = new Pentago();

        IntroPane introPane = new IntroPane(pentago, lightingEffect());
        Scene introScene = new Scene(introPane, visualBounds.getWidth(), visualBounds.getHeight());
        
        primaryStage.setScene(introScene);
        primaryStage.setFullScreen(true);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    public void initSurroundingMusic() {
        AudioClip sound = new AudioClip("file:media/sound/Jazz.wav");
        sound.setVolume(0.1);
        sound.play();
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
