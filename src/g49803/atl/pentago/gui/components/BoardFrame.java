package g49803.atl.pentago.gui.components;

import g49803.atl.pentago.model.Pentago;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;

/**
 * Allows to create the board of the pentago.
 *
 * @author Jeremy Gillard
 */
public class BoardFrame extends GridPane {

    private  QuadrantFrame[] board;

    /**
     * Allows to create the board frame of the pentago game.
     * 
     * @param pentago the pentago concerned.
     */
    public BoardFrame(Pentago pentago) {
        arrangement(pentago);
    }

    private void arrangement(Pentago pentago) {
        initQuadrantFrames(pentago);
        RotationButtonComponent[] rotationButtons = initRotationsButtons(pentago);
        arrangement(rotationButtons);
    }

    private RotationButtonComponent[] initRotationsButtons(Pentago pentago) {
        RotationButtonComponent[] rotationButtons = new RotationButtonComponent[8];
        rotationButtons[0] = new RotationButtonComponent(0, true, pentago, lightingEffect());
        rotationButtons[1] = new RotationButtonComponent(0, false, pentago, lightingEffect());
        rotationButtons[2] = new RotationButtonComponent(1, true, pentago, lightingEffect());
        rotationButtons[3] = new RotationButtonComponent(1, false, pentago, lightingEffect());
        rotationButtons[4] = new RotationButtonComponent(2, true, pentago, lightingEffect());
        rotationButtons[5] = new RotationButtonComponent(2, false, pentago, lightingEffect());
        rotationButtons[6] = new RotationButtonComponent(3, true, pentago, lightingEffect());
        rotationButtons[7] = new RotationButtonComponent(3, false, pentago, lightingEffect());
        return rotationButtons;
    }

    private void initQuadrantFrames(Pentago pentago) {
        board = new QuadrantFrame[4];
        for (int i = 0; i < board.length; i++) {
            board[i] = new QuadrantFrame(i, pentago, lightingEffect());
        }
    }

    private void arrangement(RotationButtonComponent[] rotationButtons) {
        this.setHgap(8);
        this.setVgap(8);
        
        this.add(rotationButtons[0], 0, 1);
        this.add(rotationButtons[1], 1, 0);
        this.add(board[0], 1, 1);

        this.add(rotationButtons[2], 2, 0);
        this.add(rotationButtons[3], 3, 1);
        this.add(board[1], 2, 1);

        this.add(rotationButtons[4], 1, 3);
        this.add(rotationButtons[5], 0, 2);
        this.add(board[3], 2, 2);

        this.add(rotationButtons[6], 3, 2);
        this.add(rotationButtons[7], 2, 3);
        this.add(board[2], 1, 2);
    }

    private Lighting lightingEffect() {
        // EFFET DE LUMIERE
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-165.0f);
        Lighting l = new Lighting();
        l.setLight(light);
        l.setSurfaceScale(3.0f);
        return l;
    }

}
