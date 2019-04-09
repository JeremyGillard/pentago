package g49803.atl.pentago.fxview;

import g49803.atl.pentago.model.Marble;
import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.model.State;
import g49803.atl.pentago.util.Observer;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 *
 * @author Jeremy
 */
public class Piece extends Circle implements Observer {
    
    private static final int RADIUS = 35;
    
    private final Pentago pentago;
    
    private final int Xposition;
    
    private final int Yposition;
    
    private final int quadrantNumber;
    
    private final Lighting lighting;
    
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
        
        this.setOnMouseClicked(e -> {
            System.out.println("CurrentPlayer :" + pentago.getCurrentPlayer().getName());
            System.out.println("Xposition :" + Xposition);
            System.out.println("Yposition :" + Yposition);
            System.out.println("quadrantNb:" + quadrantNumber);
            pentago.placeMarble(Xposition, Yposition);
        });
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
