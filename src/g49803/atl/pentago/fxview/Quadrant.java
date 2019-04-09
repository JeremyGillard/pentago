package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.model.State;
import g49803.atl.pentago.util.Observer;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

class Quadrant extends StackPane implements Observer {
    
    private final Pentago pentago;

    private final int quadrantNumber;
    
    private Rectangle representation;

    private RotateTransition transition;

    public Quadrant(int quadrantNumber, Pentago pentago, Lighting lightingEffet) {
        this.quadrantNumber = quadrantNumber;
        this.pentago = pentago;
        visualInitialization(lightingEffet);
        arrangement(quadrantNumber, lightingEffet);
        behavior();
    }

    private void visualInitialization(Lighting lightingEffet) {
        representation = new Rectangle(300, 300);
        representation.setFill(new LinearGradient(0f, 0f, 0f, 1f, true,
                CycleMethod.NO_CYCLE,
                new Stop[]{new Stop(0, Color.rgb(89, 34, 2)),
                    new Stop(1, Color.rgb(65, 28, 1))}));
        representation.setArcWidth(100);
        representation.setArcHeight(100);
        representation.setEffect(lightingEffet);
    }

    private void arrangement(int quadrantNumber, Lighting lightingEffet) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(25);
        grid.setVgap(25);
        grid.setGridLinesVisible(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid.add(new Piece(i, j, quadrantNumber, this.pentago, lightingEffet), i, j);
            }
        }
        
        this.getChildren().addAll(representation, grid);
    }

    private void behavior() {
        pentago.addObserver(this);
        
        transition = new RotateTransition(Duration.millis(3000), this);
        transition.setDuration(Duration.millis(800));
    }

    @Override
    public void update() {
        if (pentago.getCurrentGameState() == State.ROTATION) {
            if (pentago.getLastQuadrantRotated() == quadrantNumber) {
                if (pentago.isLastRotationClockwise()) {
                    transition.setByAngle(90);
                } else {
                    transition.setByAngle(-90);
                }
                transition.play();
            }
        }
    }

}
