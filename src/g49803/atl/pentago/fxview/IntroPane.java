package g49803.atl.pentago.fxview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

/**
 *
 * @author Jeremy
 */
public class IntroPane extends StackPane {

    private Label title;

    private ImageView imageView;

    private VBox introRoot;

    private Label player1Label;

    private TextField player1TF;

    private Label player2Label;

    private TextField player2TF;

    private Button startButton;

    private Button quitButton;

    private GridPane tfPane;

    public IntroPane() {
        initTitle();
        initFirstPlayerField();
        initSecondPlayerField();
        initStartButton();
        initQuitButton();
        visualInitialization();
        arrangement();
//        behavior();
    }

    private void initQuitButton() {
        quitButton = new Button("Quit");
        quitButton.setMinSize(100, 30);
        quitButton.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7), new CornerRadii(16), Insets.EMPTY)));
        quitButton.setFont(Font.font("sans-serif", FontWeight.BOLD, 40));
        quitButton.setTextFill(Color.rgb(138, 95, 57));
    }

    private void initStartButton() {
        startButton = new Button("Start");
        startButton.setMinSize(100, 30);
        startButton.setBackground(new Background(new BackgroundFill(Color.rgb(68, 25, 7), new CornerRadii(16), Insets.EMPTY)));
        startButton.setFont(Font.font("sans-serif", FontWeight.BOLD, 40));
        startButton.setTextFill(Color.rgb(138, 95, 57));
    }

    private void initSecondPlayerField() {
        player2Label = new Label("Second player's name :");
        player2Label.setFont(Font.font("sans-serif", FontWeight.BOLD, 40));
        player2Label.setTextFill(Color.rgb(138, 95, 57));

        player2TF = new TextField();
        player2TF.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
        player2TF.setBackground(new Background(new BackgroundFill(Color.rgb(170, 170, 170, 0.9), new CornerRadii(5), Insets.EMPTY)));
    }

    private void initFirstPlayerField() {
        player1Label = new Label("First player's name :");
        player1Label.setFont(Font.font("sans-serif", FontWeight.BOLD, 40));
        player1Label.setTextFill(Color.rgb(138, 95, 57));

        player1TF = new TextField();
        player1TF.setFont(Font.font("sans-serif", FontWeight.BOLD, 30));
        player1TF.setBackground(new Background(new BackgroundFill(Color.rgb(170, 170, 170, 0.9), new CornerRadii(5), Insets.EMPTY)));
    }

    private void initTitle() {
        title = new Label("Pentago");
        title.setFont(Font.font("sans-serif", FontWeight.BOLD, 120));
        title.setTextFill(Color.rgb(68, 25, 7));
    }

    private void visualInitialization() {
        imageView = new ImageView("file:img/wood.jpg");
        introRoot = new VBox();
        introRoot.setAlignment(Pos.CENTER);
        introRoot.setPadding(new Insets(40, 40, 40, 40));
        introRoot.setSpacing(40);
        introRoot.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 50, 0.7), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void arrangement() {
        tfPane = new GridPane();
        tfPane.add(player1Label, 0, 0);
        tfPane.add(player1TF, 1, 0);
        tfPane.add(player2Label, 0, 1);
        tfPane.add(player2TF, 1, 1);
        tfPane.setAlignment(Pos.CENTER);
        tfPane.setHgap(20);
        tfPane.setVgap(20);

        introRoot.getChildren().addAll(title, tfPane, startButton, quitButton);
        this.getChildren().addAll(imageView, introRoot);
    }

    // Défaut, le jeu se lance avec "Please enter a name comme noms"
//    private void behavior() {
//        startButton.setOnMouseReleased((event) -> {
//            if (player1TF.getText().isEmpty()) {
//                player1TF.setText("Please enter a name here");
//            }
//            if (player2TF.getText().isEmpty()) {
//                player2TF.setText("Please enter a name here");
//            }
//        });
//    }
    
    public Button getStartButton() {
        return startButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public String getFirstPlayerName() {
        return player1TF.getText();
    }

    public String getSecondPlayerName() {
        return player2TF.getText();
    }
}
