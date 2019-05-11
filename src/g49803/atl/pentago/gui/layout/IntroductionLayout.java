package g49803.atl.pentago.gui.layout;

import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.gui.components.ButtonComponent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy
 */
public class IntroductionLayout extends StackPane {
    
    private final Pentago pentago;
    
    private TextField textFieldFirstPlayer;
    
    private TextField textFieldSecondPlayer;
    
    public IntroductionLayout(Pentago pentago) {
        this.pentago = pentago;
        
        Label labelTitle = new Label("Pentago");
        labelTitleArrangement(labelTitle);
        
        Label labelFirstPlayer = new Label("First player's name : ");
        firstPlayerFieldArrangement(labelFirstPlayer);
        
        Label labelSecondPlayer = new Label("Second player's name : ");
        secondPlayerFieldArrangement(labelSecondPlayer);
        
        ButtonComponent buttonStart = new ButtonComponent("Start");
        buttonStartBehavior(buttonStart);
        
        ButtonComponent buttonQuit = new ButtonComponent("Quit");
        buttonQuitBehavior(buttonQuit);
        
        GridPane gridPaneTextFields = new GridPane();
        gridPaneTextFieldsArrangement(gridPaneTextFields, labelFirstPlayer, labelSecondPlayer);
        
        VBox introLayout = new VBox();
        introLayoutArrangement(introLayout);
        introLayout.getChildren().addAll(labelTitle, gridPaneTextFields, buttonStart, buttonQuit);
        
        ImageView imageView = new ImageView("file:media/img/wood.jpg");
        this.getChildren().addAll(imageView, introLayout);
    }

    private void labelTitleArrangement(Label labelTitle) {
        labelTitle.setFont(Font.font("sans-serif", FontWeight.BOLD, 70));
        labelTitle.setTextFill(Color.rgb(68, 25, 7));
    }

    private void firstPlayerFieldArrangement(Label labelFirstPlayer) {
        labelFirstPlayer.setFont(Font.font("sans-serif", FontWeight.BOLD, 25));
        labelFirstPlayer.setTextFill(Color.rgb(68, 25, 7));
        
        textFieldFirstPlayer = new TextField();
        textFieldFirstPlayer.setFont(Font.font("sans-serif", FontWeight.BOLD, 20));
        textFieldFirstPlayer.setBackground(new Background(new BackgroundFill(Color.rgb(170, 170, 170, 0.9), new CornerRadii(5), Insets.EMPTY)));
    }

    private void secondPlayerFieldArrangement(Label labelSecondPlayer) {
        labelSecondPlayer.setFont(Font.font("sans-serif", FontWeight.BOLD, 25));
        labelSecondPlayer.setTextFill(Color.rgb(68, 25, 7));
        
        textFieldSecondPlayer = new TextField();
        textFieldSecondPlayer.setFont(Font.font("sans-serif", FontWeight.BOLD, 20));
        textFieldSecondPlayer.setBackground(new Background(new BackgroundFill(Color.rgb(170, 170, 170, 0.9), new CornerRadii(5), Insets.EMPTY)));
    }

    private void gridPaneTextFieldsArrangement(GridPane gridPaneTextFields, Label labelFirstPlayer, Label labelSecondPlayer) {
        gridPaneTextFields.add(labelFirstPlayer, 0, 0);
        gridPaneTextFields.add(textFieldFirstPlayer, 1, 0);
        gridPaneTextFields.add(labelSecondPlayer, 0, 1);
        gridPaneTextFields.add(textFieldSecondPlayer, 1, 1);
        gridPaneTextFields.setAlignment(Pos.CENTER);
        gridPaneTextFields.setHgap(20);
        gridPaneTextFields.setVgap(20);
    }

    private void introLayoutArrangement(VBox introLayout) {
        introLayout.setAlignment(Pos.CENTER);
        introLayout.setPadding(new Insets(20, 20, 20, 20));
        introLayout.setSpacing(40);
        introLayout.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 50, 0.1), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void buttonQuitBehavior(Button buttonQuit) {
        buttonQuit.setOnMouseClicked((event) -> {
            ((Stage) this.getScene().getWindow()).close();
        });
    }

    private void buttonStartBehavior(Button buttonStart) {
        buttonStart.setOnMouseClicked((event) -> {
            if (textFieldFirstPlayer.getText().isEmpty() || textFieldSecondPlayer.getText().isEmpty()) {
                if (textFieldFirstPlayer.getText().isEmpty()) {
                    textFieldFirstPlayer.setPromptText("Please enter a name");
                }
                if (textFieldSecondPlayer.getText().isEmpty()) {
                    textFieldSecondPlayer.setPromptText("Please enter a name");
                }
            } else {
                pentago.addNewPlayer(textFieldFirstPlayer.getText());
                pentago.addNewPlayer(textFieldSecondPlayer.getText());
                pentago.start();
                sceneChange(textFieldFirstPlayer.getText(), textFieldSecondPlayer.getText());
            }
        });
    }

    private void sceneChange(String firstPlayerName, String secondPlayerName) {
        GameLayout gameLayout = new GameLayout(pentago, firstPlayerName, secondPlayerName);
        this.getScene().setRoot(gameLayout);
    }
    
}
