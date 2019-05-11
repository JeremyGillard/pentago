package g49803.atl.pentago.view.components;

import g49803.atl.pentago.model.Marble;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Jeremy
 */
public class IdentityPlayerFrame extends VBox {
    
    private boolean isHighLighted;
    
    public IdentityPlayerFrame(String playerName, Marble playerColor) {
        Label labelPlayer = new Label(playerName);
        labelPlayer.setFont(Font.font("sans-serif", FontWeight.BOLD, 25));
        Circle circlePlayerColor = new Circle(26);
        
        this.setMinSize(150, 180);
        this.setMaxSize(150, 180);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7, 0.5), new CornerRadii(10), Insets.EMPTY)));
        
        if (playerColor == Marble.BLACK) {
            labelPlayer.setTextFill(Color.rgb(0, 0, 0, 0.9));
            circlePlayerColor.setFill(Color.rgb(0, 0, 0, 0.9));
        } else {
            labelPlayer.setTextFill(Color.rgb(255, 230, 158, 0.8));
            circlePlayerColor.setFill(Color.rgb(255, 230, 158, 0.8));
            highlight();
        }
        
//        labelPlayer.setTextFill(Color.rgb(176, 148, 101));
//        
//        circlePlayerColor.setFill(new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
//                new Stop[]{new Stop(0, Color.rgb(168, 125, 87)),
//                    new Stop(1, Color.rgb(134, 112, 80))}));
        
        this.getChildren().addAll(labelPlayer, circlePlayerColor);
    }
    
    public void highlight() {
        if (isHighLighted) {
            this.setBorder(Border.EMPTY);
            isHighLighted = false;
        } else {
            this.setBorder(new Border(new BorderStroke(Color.rgb(225, 190, 118, 0.9), 
            BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
            isHighLighted = true;
        }
    }
    
}
