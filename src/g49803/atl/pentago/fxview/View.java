package g49803.atl.pentago.fxview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy Gillard
 */
public class View extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        //effet light spot et lightning
        Label label = new Label("Current Player : ");
        Board board = new Board();
        
        VBox root = new VBox();
        root.getChildren().addAll(label, board);
        
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
