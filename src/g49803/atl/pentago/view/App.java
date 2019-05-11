package g49803.atl.pentago.view;

import g49803.atl.pentago.view.layout.IntroductionLayout;
import g49803.atl.pentago.model.Pentago;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Pentago pentago = new Pentago();
        ConsolView consolView = new ConsolView(pentago); // A RETIRER
        pentago.addObserver(consolView); // A RETIRER
        
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
    
}
