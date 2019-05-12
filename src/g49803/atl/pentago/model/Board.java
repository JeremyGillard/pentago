package g49803.atl.pentago.model;

/**
 * Represents the board of the pentago game.
 *
 * @author Jeremy Gillard
 */
public class Board {
    
    private final int boardSize;
    
    private final static int NUMBER_OF_QUADRANTS = 4;
    
    private final Quadrant[] board;

    /**
     * Allows to create a board with a side length precised.
     * 
     * @param size the board size.
     */
    Board(int size) {
        board = new Quadrant[NUMBER_OF_QUADRANTS];
        boardSize = size;
        for (int i = 0; i < board.length; i++) {
            board[i] = new Quadrant(boardSize / (NUMBER_OF_QUADRANTS/2));
        }
    }

    /**
     * Checks if the cell of the board at x and y position is empty or
     * not.
     *
     * @param x the x concerned.
     * @param y the y concerned.
     * @return true if the cell concerned is empty.
     */
    boolean isEmptyCell(int x, int y) {
        return getMarbleAtPosition(x, y) == null;
    }

    /**
     * Fill the selected cell at a certain x and a certain y with a
     * marble color.
     *
     * @param x the x concerned.
     * @param y the y concerned.
     * @param marbleColor the marble color to place in the cell.
     */
    void fillCell(int x, int y, Marble marbleColor) {
        board[getQuadrantNumber(x, y)].placeMarble(
                fromBoardToQuadrantCoordinates(x), 
                fromBoardToQuadrantCoordinates(y), marbleColor);
    }

    /**
     * Allows to turn a quadrant I(0), II(1), III(2), IV(3) in the clockwise direction or
     * the opposite of clockwise direction.
     *
     * @param quadrantPosition the quadrant to be turned.
     * @param clockWise the direction in which to turn the quadrant.
     */
    void turnQuadrant(int quadrantPosition, boolean clockWise) {
        board[quadrantPosition].rotate(clockWise);
    }

    /**
     * Returns the marble at a certain position on the board.
     *
     * @param x the x position of the marble.
     * @param y the y position of the marble.
     * @return the marble at a certain position on the board.
     */
    Marble getMarbleAtPosition(int x, int y) {
        return board[getQuadrantNumber(x, y)].getMarble(fromBoardToQuadrantCoordinates(x), fromBoardToQuadrantCoordinates(y));
    }
    
    /**
     * This method allows to check if there is a certain repetition (size) 
     * of a value between (x, y) and the edge of the board 
     * horizontaly (dx = 1, dy = 0)
     * verticaly (dx = 0, dy = 1) 
     * or diagonaly (dx = -1, dy = 1).
     * 
     * @param x the x start point.
     * @param y the y start point.
     * @param dx the step of the iteration along x axis.
     * @param dy the step of the iteration along y axis.
     * @param size the size of the repetition chain.
     * @return 
     */
    Marble checkAlignment(int x, int y, int dx, int dy, int size) {
        Marble value = getMarbleAtPosition(x, y);
        int index = 0;
        while (x < boardSize && x >= 0 && y < boardSize && y >= 0) {
            if (value == getMarbleAtPosition(x, y)) {
                index++;
            } else {
                index = 1;
                value = getMarbleAtPosition(x, y);
            }
            if (index == size) {
                return value;
            }
            y += dy;
            x += dx;
        }
        return null;
    }
    
    /**
     * Returns the marbleColor if there is an alignment of chainLengthToWin on
     * the board.
     * 
     * @param marbleColor the color alignment to check.
     * @param chainLengthToWin to size of the alignment.
     * @return the marbleColor if there is an alignment of chainLengthToWin on
     * the board.
     */
    Marble checkWinAlignmentFor(Marble marbleColor, int chainLengthToWin) {
        for (int i = 0; i < boardSize; i++) {
            if (checkAlignment(0, i, 1, 0, 5) == marbleColor) return marbleColor;
            if (checkAlignment(i, 0, 0, 1, 5) == marbleColor) return marbleColor;
            
            if (checkAlignment(0, i, 1, 1, 5) == marbleColor) return marbleColor;
            if (checkAlignment(i, 0, 1, 1, 5) == marbleColor) return marbleColor;
            
            if (checkAlignment(5, i, -1, 1, 5) == marbleColor) return marbleColor;
            if (checkAlignment(i, 0, -1, 1, 5) == marbleColor) return marbleColor;
        }
        return null;
    }
    
    /**
     * Returns true if the board of the game is full.
     * 
     * @return true if the board of the game is full.
     */
    boolean isFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (getMarbleAtPosition(i, j) == null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private int getQuadrantNumber(int x, int y) {
        return x / 3 + 2 * (y / 3);
    }
    
    private int fromBoardToQuadrantCoordinates(int coord) {
        return coord % 3; //utiliser une constante à la place de trois!!!!
    }

}
