package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

/**
 * This class represents the board of the pentago game; with 4 quadrant and its 
 * rotation buttons.
 * 
 * @author Jeremy Gillard
 */
public class Board extends GridPane {
    
    private final Pentago pentago;

    private Quadrant quadrantI;
    private Quadrant quadrantII;
    private Quadrant quadrantIII;
    private Quadrant quadrantIV;

    private RotationButton quadILeft;
    private RotationButton quadIRight;

    private RotationButton quadIILeft;
    private RotationButton quadIIRight;

    private RotationButton quadIIILeft;
    private RotationButton quadIIIRight;

    private RotationButton quadIVLeft;
    private RotationButton quadIVRight;
    
    private final Lighting lightEffect;

    /**
     * Allows to create a board with a pentago model and a light effect.
     * 
     * @param pentago the pentago game model.
     * @param lightingEffect the visual effect.
     */
    Board(Pentago pentago, Lighting lightEffect) {
        this.pentago = pentago;
        this.lightEffect = lightEffect;
        initContent();
        initBoard();
    }

    private void initContent() {
        quadrantI = new Quadrant(1, pentago, lightEffect);
        quadrantII = new Quadrant(2, pentago, lightEffect);
        quadrantIII = new Quadrant(3, pentago, lightEffect);
        quadrantIV = new Quadrant(4, pentago, lightEffect);

        quadILeft = new RotationButton(1, false, pentago, lightEffect);
        quadILeft.setShape(new Circle(20));
        quadIRight = new RotationButton(1, true, pentago, lightEffect);
        quadIRight.setShape(new Circle(20));

        quadIILeft = new RotationButton(2, false, pentago, lightEffect);
        quadIILeft.setShape(new Circle(20));
        quadIIRight = new RotationButton(2, true, pentago, lightEffect);
        quadIIRight.setShape(new Circle(20));

        quadIIILeft = new RotationButton(3, false, pentago, lightEffect);
        quadIIILeft.setShape(new Circle(20));
        quadIIIRight = new RotationButton(3, true, pentago, lightEffect);
        quadIIIRight.setShape(new Circle(20));

        quadIVLeft = new RotationButton(4, false, pentago, lightEffect);
        quadIVLeft.setShape(new Circle(20));
        quadIVRight = new RotationButton(4, true, pentago, lightEffect);
        quadIVRight.setShape(new Circle(20));
    }

    private void initBoard() {
        this.add(quadILeft, 0, 1);
        this.add(quadIRight, 1, 0);
        setHalignment(quadIRight, HPos.CENTER);
        this.add(quadrantI, 1, 1);

        this.add(quadIILeft, 2, 0);
        setHalignment(quadIILeft, HPos.CENTER);
        this.add(quadIIRight, 3, 1);
        this.add(quadrantII, 2, 1);

        this.add(quadIIILeft, 1, 3);
        setHalignment(quadIIILeft, HPos.CENTER);
        this.add(quadIIIRight, 0, 2);
        this.add(quadrantIII, 1, 2);

        this.add(quadIVLeft, 3, 2);
        this.add(quadIVRight, 2, 3);
        setHalignment(quadIVRight, HPos.CENTER);
        this.add(quadrantIV, 2, 2);

        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
    }

}
