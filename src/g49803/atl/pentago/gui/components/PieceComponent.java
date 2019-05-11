package g49803.atl.pentago.gui.components;

import g49803.atl.pentago.gui.layout.GameLayout;
import g49803.atl.pentago.model.GameStateException;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.util.Observer;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.effect.Lighting;
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
public class PieceComponent extends Circle implements Observer {

    private static final int RADIUS = 30;

    private final Pentago pentago;

    private final int xPosition;

    private final int yPosition;

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
    public PieceComponent(int quadrantNumber, int Xposition, int Yposition, Pentago pentago, Lighting lightingVisualEffect) {
        super(RADIUS);
        this.pentago = pentago;
        this.pentago.addObserver(this);
        this.xPosition = Xposition;
        this.yPosition = Yposition;
        this.quadrantNumber = quadrantNumber;
        this.lighting = lightingVisualEffect;
        visualInitialization();
        behavior();
    }

    private void visualInitialization() {
        setFill(new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                new Stop[]{new Stop(0, Color.rgb(168, 125, 87)),
                    new Stop(1, Color.rgb(134, 112, 80))}));
    }

    private void behavior() {
        this.setOnMouseEntered((event) -> {
            this.getScene().setCursor(Cursor.HAND);
        });

        this.setOnMouseExited((event) -> {
            try {
                this.getScene().setCursor(Cursor.DEFAULT);
            } catch (NullPointerException e) {}
        });

        this.setOnMouseClicked((event) -> {
            try {
                pentago.placeMarble(xPosition, yPosition);
                placeMarbleSound();
            } catch (IllegalArgumentException | GameStateException e) {
                Parent parent = this.getParent();
                while (!(parent instanceof GameLayout)) {
                    parent = parent.getParent();
                }
                ((GameLayout) parent).setGameCommunication(e.getMessage());
            }
        });
    }

    @Override
    public void update() {
        if (null == pentago.getMarbleAt(xPosition, yPosition)) {
            this.setFill(new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                    new Stop[]{new Stop(0, Color.rgb(168, 125, 87)),
                        new Stop(1, Color.rgb(134, 112, 80))}));
            this.setEffect(null);
        } else switch (pentago.getMarbleAt(xPosition, yPosition)) {
            case BLACK:
                this.setFill(Color.rgb(0, 0, 0));
                this.setEffect(lighting);
                break;
            case WHITE:
                this.setFill(Color.rgb(255, 230, 158));
                this.setEffect(lighting);
                break;
            default:
                this.setFill(new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                        new Stop[]{new Stop(0, Color.rgb(168, 125, 87)),
                            new Stop(1, Color.rgb(134, 112, 80))}));
                this.setEffect(null);
                break;
        }
    }

    private void placeMarbleSound() {
        AudioClip sound = new AudioClip("file:media/sound/placingPiece.wav");
        sound.setVolume(1);
        sound.play();
    }

}
