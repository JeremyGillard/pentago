/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.fxview;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author Jeremy
 */
public class Board extends GridPane {

    private Quadrant quadrantI;
    private Quadrant quadrantII;
    private Quadrant quadrantIII;
    private Quadrant quadrantIV;

    private Button quadILeft;
    private Button quadIRight;

    private Button quadIILeft;
    private Button quadIIRight;

    private Button quadIIILeft;
    private Button quadIIIRight;

    private Button quadIVLeft;
    private Button quadIVRight;

    public Board() {
        initContent();
        initBoard();
    }

    private void initContent() {
        quadrantI = new Quadrant();
        quadrantII = new Quadrant();
        quadrantIII = new Quadrant();
        quadrantIV = new Quadrant();

        quadILeft = new Button("⟲");
        quadILeft.setShape(new Circle(20));
        quadIRight = new Button("⟳");
        quadIRight.setShape(new Circle(20));

        quadIILeft = new Button("⟲");
        quadIILeft.setShape(new Circle(20));
        quadIIRight = new Button("⟳");
        quadIIRight.setShape(new Circle(20));

        quadIIILeft = new Button("⟲");
        quadIIILeft.setShape(new Circle(20));
        quadIIIRight = new Button("⟳");
        quadIIIRight.setShape(new Circle(20));

        quadIVLeft = new Button("⟲");
        quadIVLeft.setShape(new Circle(20));
        quadIVRight = new Button("⟳");
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
