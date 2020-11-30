package be.jeremygillard.pentago.gui;

import be.jeremygillard.pentago.gui.layout.IntroductionLayout;
import be.jeremygillard.pentago.model.Pentago;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //Atmosphere work for more immersion in the game.
        // Commented because the wav file was too large for a remote. 
        //cf: remote: error: GH001: Large files detected. You may want to try Git Large File Storage - https://git-lfs.github.com    
        //initSurroundingMusic();
        
        Pentago pentago = new Pentago();
        
        IntroductionLayout introLayout = new IntroductionLayout(pentago);
        
        Scene scene = new Scene(introLayout, 1300, 800);
        
        primaryStage.setTitle("Pentago");
        primaryStage.getIcons().add(new Image("file:media/img/icon.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void initSurroundingMusic() {
        AudioClip sound = new AudioClip("file:media/sound/Jazz.wav");
        sound.setVolume(0.1);
        sound.play();
    }
    
}
