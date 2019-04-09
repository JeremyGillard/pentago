package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy
 */
public class IntroPane extends StackPane {

    private final Pentago pentago;

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

    private GamePane gamePane;

    private final Lighting lightingEffect;

    public IntroPane(Pentago pentago, Lighting lightingEffect) {
        this.pentago = pentago;
        this.lightingEffect = lightingEffect;
        initTitle();
        initFirstPlayerField();
        initSecondPlayerField();
        initStartButton();
        initQuitButton();
        visualInitialization();
        arrangement();
        behavior();
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

    private void behavior() {
        quitButton.setOnMouseEntered((event) -> {
            this.getScene().setCursor(Cursor.HAND);
        });

        quitButton.setOnMouseExited((event) -> {
            this.getScene().setCursor(Cursor.DEFAULT);
        });

        quitButton.setOnMouseClicked((event) -> {
            ((Stage) this.getScene().getWindow()).close();
        });

        startButton.setOnMouseEntered((event) -> {
            this.getScene().setCursor(Cursor.HAND);
        });

        startButton.setOnMouseExited((event) -> {
            this.getScene().setCursor(Cursor.DEFAULT);
        });

        startButton.setOnMouseClicked((event) -> {
            if (player1TF.getText().isEmpty() || player2TF.getText().isEmpty()) {
                if (player1TF.getText().isEmpty()) {
                    player1TF.setPromptText("Please enter a name");
                }
                if (player2TF.getText().isEmpty()) {
                    player2TF.setPromptText("Please enter a name");
                }
            } else {
                pentago.addNewPlayer(player1TF.getText());
                pentago.addNewPlayer(player2TF.getText());
                pentago.start();
                nextScene();
            }
        });

        this.setOnKeyPressed(key -> {
            if (key.getCode().equals(KeyCode.ENTER)) {
                if (player1TF.getText().isEmpty() || player2TF.getText().isEmpty()) {
                    if (player1TF.getText().isEmpty()) {
                        player1TF.setPromptText("Please enter a name");
                    }
                    if (player2TF.getText().isEmpty()) {
                        player2TF.setPromptText("Please enter a name");
                    }
                } else {
                    pentago.addNewPlayer(player1TF.getText());
                    pentago.addNewPlayer(player2TF.getText());
                    pentago.start();
                    nextScene();
                }
            }
        });
    }

    private void nextScene() {
        gamePane = new GamePane(pentago, lightingEffect);
        Scene gameScene = new Scene(gamePane,
                Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight());
        ((Stage) this.getScene().getWindow()).setScene(gameScene);
        ((Stage) this.getScene().getWindow()).setFullScreen(true);
    }
}
