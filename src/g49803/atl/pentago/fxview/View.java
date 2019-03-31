package g49803.atl.pentago.fxview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author g49803
 */
public class View extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Board root = new Board();
        
        Scene scene = new Scene(root, 800, 800);
        
        primaryStage.setTitle("PENTAGO");
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
