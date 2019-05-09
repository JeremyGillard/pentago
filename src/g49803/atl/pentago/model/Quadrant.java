/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.model;

/**
 *
 * @author g49803
 */
public class Quadrant {
    
    private final Marble[][] quadrant;
    
    Quadrant(int size) {
        quadrant = new Marble[size][size];
    }
    
    void placeMarble(int x, int y, Marble marbleColor) {
        quadrant[y][x] = marbleColor;
    }
    
    Marble getMarble(int x, int y) {
        return quadrant[y][x];
    }
    
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
