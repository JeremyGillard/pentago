package g49803.atl.pentago.model;

/**
 * Represents a quadrant of the pentago game board
 *
 * @author Jeremy Gillard
 */
public class Quadrant {
    
    private final Marble[][] quadrant;
    
    /**
     * Allows to create a quadrant with a certain size.
     * 
     * @param size the size of the quadrant sides.
     */
    Quadrant(int size) {
        quadrant = new Marble[size][size];
    }
    
    /**
     * Allows to place a marble at (x, y) position of the quadrant.
     * 
     * @param x the x position.
     * @param y the y position.
     * @param marble the marble to place.
     */
    void placeMarble(int x, int y, Marble marble) {
        quadrant[y][x] = marble;
    }
    
    /**
     * Returns the marble at (x, y) position.
     * 
     * @param x the x position.
     * @param y the y position.
     * @return the marble at (x, y) position.
     */
    Marble getMarble(int x, int y) {
        return quadrant[y][x];
    }
    
    /**
     * Allows the quadrant to be rotated clockwise or non clockwise.
     * 
     * @param clockwise if the rotate will be clockwise or not.
     */
    void rotate(boolean clockwise) {
        if (clockwise) {
            swapRows();
            transpose();
        } else {
            transpose();
            swapRows();
        }
    }
    
    private void swapRows() {
        for (int i = 0, j = quadrant.length - 1; i < j; i++, j--) {
            Marble[] x = quadrant[i];
            quadrant[i] = quadrant[j];
            quadrant[j] = x;
        }
    }

    private void transpose() {
        for (int i = 0; i < quadrant.length; i++) {
            for (int j = i; j < quadrant[0].length; j++) {
                Marble x = quadrant[i][j];
                quadrant[i][j] = quadrant[j][i];
                quadrant[j][i] = x;
            }
        }
    }
}
