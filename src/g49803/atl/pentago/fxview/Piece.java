package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.GameStateException;
import g49803.atl.pentago.model.Marble;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.model.State;
import g49803.atl.pentago.util.Observer;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 * This class allows to create empty circle on the quadrants of the pentago
 * game, and the player's white and black marbles during the game.
 *
 * @author Jeremy Gillard
 */
public class Piece extends Circle implements Observer {

    private static final int RADIUS = 35;

    private final Pentago pentago;

    private final int Xposition;

    private final int Yposition;

    private final int quadrantNumber;

    private final Lighting lighting;

    /**
     * Every Piece will be instantiated with a certain X and Y position in
     * relation to the board pentago game, a number of quadrant, a pentago game
     * model and a light effect.
     *
     * @param quadrantNumber the quadrant number in which the piece is located.
     * @param Xposition the x position of the piece in relation to the board
     * pentago game.
     * @param Yposition the y position of the piece in relation to the board
     * pentago game.
     * @param pentago the pentago model
     * @param lightingVisualEffect the visual effect.
     */
    public Piece(int quadrantNumber, int Xposition, int Yposition, Pentago pentago, Lighting lightingVisualEffect) {
        super(RADIUS);
        this.pentago = pentago;
        this.quadrantNumber = quadrantNumber;
        this.Xposition = xPositionAccordingQuadrant(Xposition);
        this.Yposition = yPositionAccordingQuadrant(Yposition);
        this.lighting = lightingVisualEffect;
        visualInitialization();
        behavior();
    }

    private int xPositionAccordingQuadrant(int x) {
        if (quadrantNumber == 2 || quadrantNumber == 4) {
            return x + 3;
        } else {
            return x;
        }
    }

    private int yPositionAccordingQuadrant(int y) {
        if (quadrantNumber == 3 || quadrantNumber == 4) {
            return y + 3;
        } else {
            return y;
        }
    }

    private void visualInitialization() {
        setFill(new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                new Stop[]{new Stop(0, Color.rgb(168, 125, 87)),
                    new Stop(1, Color.rgb(134, 112, 80))}));
    }

    private void behavior() {
        pentago.addObserver(this);

        this.setOnMouseEntered((event) -> {
            this.getScene().setCursor(Cursor.HAND);
        });

        this.setOnMouseExited((event) -> {
            this.getScene().setCursor(Cursor.DEFAULT);
        });

        this.setOnMouseClicked((event) -> {
            System.out.println("ClickLoc (" + xPositionAccordingQuadrant(GridPane.getColumnIndex(this)) + ", " + yPositionAccordingQuadrant(GridPane.getRowIndex(this)) + ")");
            try {
                pentago.placeMarble(yPositionAccordingQuadrant(GridPane.getRowIndex(this)), xPositionAccordingQuadrant(GridPane.getColumnIndex(this)));
                placeMarbleSound();
            } catch (IllegalArgumentException | GameStateException e) {
                Parent parent = this.getParent();
                while (!(parent instanceof GamePane)) {
                    parent = parent.getParent();
                }
                ((GamePane) parent).setGameCommunication(e.getMessage());
            }
        });
    }

    public void placeMarbleSound() {
        AudioClip sound = new AudioClip("file:media/sound/placingPiece.wav");
        sound.setVolume(1);
        sound.play();
    }

    @Override
    public void update() {
        if (pentago.getCurrentGameState() == State.PLACEMENT) {
            if (pentago.getLastXPlacement() == Xposition && pentago.getLastYPlacement() == Yposition) {
                if (pentago.getCurrentPlayer().getColor() == Marble.BLACK) {
                    setFill(Color.rgb(0, 0, 0));
                    setEffect(lighting);
                } else {
                    setFill(Color.rgb(255, 230, 158));
                    setEffect(lighting);
                }
            }
        }
    }
}
