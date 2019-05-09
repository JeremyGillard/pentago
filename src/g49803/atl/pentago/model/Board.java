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
     * @param size
     */
    Board(int size) {
        board = new Quadrant[NUMBER_OF_QUADRANTS];
        boardSize = size;
        for (int i = 0; i < board.length; i++) {
            board[i] = new Quadrant(boardSize / (NUMBER_OF_QUADRANTS/2));
        }
    }

    /**
     * Checks if the cell of the board at the col and row position is empty or
     * not.
     *
     * @param col the colomn concerned.
     * @param row the row concerned.
     * @return true if the cell concerned is empty.
     */
    boolean isEmptyCell(int x, int y) {
        return board[getQuadrantNumber(x, y)].getMarble(
                fromBoardToQuadrantCoordinates(x), 
                fromBoardToQuadrantCoordinates(y)) == null;
    }

    /**
     * Fill the selected cell at a certain column and a certain row with a
     * marble color.
     *
     * @param col the colomn concerned.
     * @param row the row concerned.
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
     * Returns true if a player  has managed to line up 5 balls on the board.
     * 
     * @return true if a player  has managed to line up 5 balls on the board.
     */
    Marble checkAlignmentWinner(int chainLengthToWin, Marble color) {
        for (int i = 0; i < boardSize; i++) { 
            if(checkAlignment(i, 0, 0, 1, chainLengthToWin) == color) return color;
            if(checkAlignment(0, i, 1, 0, chainLengthToWin) == color) return color;
            if(checkAlignment(0, i, 1, 1, chainLengthToWin) == color) return color;
            if(checkAlignment(i, 0, 1, 1, chainLengthToWin) == color) return color;
            if(checkAlignment(5, i, -1, 1, chainLengthToWin) == color) return color;
            if(checkAlignment(i, 0, -1, 1, chainLengthToWin) == color) return color;
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
    
    public Marble checkAlignment(int x, int y, int dx, int dy, int size) {
        Marble value = getMarbleAtPosition(x, y);
        int index = 1;
        
        
        for (int i = (dx < 0 && x != 0) ? ((boardSize-1) % x) : x, j = y; 
            (dx == 1) ? (dy == 1) ? (i < boardSize-1 && j < boardSize-1) : i < boardSize-1 : (dx < 0) ? j < x : j < boardSize-1; 
             i++, j++) {
            if (value == getMarbleAtPosition(x + dx, y + dy)) {
                index++;
            } else {
                index = 1;
                value = getMarbleAtPosition(x, y);
            }
            if (index == size) {
                return value;
            }
        }
        return null;
    }

}
