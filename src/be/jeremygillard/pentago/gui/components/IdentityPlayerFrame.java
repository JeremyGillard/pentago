package be.jeremygillard.pentago.gui.components;

import be.jeremygillard.pentago.model.Marble;
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
 * This class allows to display player's names with their colors and to
 * highlight the current player who need to play.
 *
 * @author Jeremy
 */
public class IdentityPlayerFrame extends VBox {

    private boolean isHighLighted;

    /**
     * Allows to create an identity player frame with his name and his
     * attributed color.
     *
     * @param playerName the player's name.
     * @param playerColor the player's color.
     */
    public IdentityPlayerFrame(String playerName, Marble playerColor) {
        Label labelPlayer = new Label(playerName);
        Arrangement(labelPlayer, playerColor);
    }

    private void Arrangement(Label labelPlayer, Marble playerColor) {
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
