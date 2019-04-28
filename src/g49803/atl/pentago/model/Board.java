package g49803.atl.pentago.model;

/**
 * Represents the board of the pentago game.
 *
 * @author Jeremy Gillard
 */
public class Board {

    private final Marble[][] board;

    /**
     * Allows to create a board with a side length precised.
     *
     * @param side the side length precised.
     */
    public Board(int side) {
        board = new Marble[side][side];
    }

    /**
     * Checks if the cell of the board at the col and row position is empty or
     * not.
     *
     * @param col the colomn concerned.
     * @param row the row concerned.
     * @return true if the cell concerned is empty.
     */
    boolean isEmptyCell(int col, int row) {
        return board[col][row] == null;
    }

    /**
     * Fill the selected cell at a certain column and a certain row with a
     * marble color.
     *
     * @param col the colomn concerned.
     * @param row the row concerned.
     * @param marbleColor the marble color to place in the cell.
     */
    void fillCell(int col, int row, Marble marbleColor) {
        board[col][row] = marbleColor;
    }

    /**
     * Allows to turn a quadrant I, II, III, IV in the clockwise direction or
     * the opposite of clockwise direction.
     *
     * @param quadrantPosition the quadrant to be turned.
     * @param direction the direction in which to turn the quadrant.
     */
    void turnQuadrant(int quadrantPosition, boolean direction) {
        Marble[][] quadrant;
        quadrant = quandrantFromBoard(quadrantPosition);
        rotate(quadrant, direction);
        quadrantToBoard(quadrantPosition, quadrant);
    }

    private void swapRows(Marble[][] matrix) {
        for (int i = 0, j = matrix.length - 1; i < j; i++, j--) {
            Marble[] x = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = x;
        }
    }

    private void transpose(Marble[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                Marble x = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = x;
            }
        }
    }

    private void rotate(Marble[][] matrix, boolean clockwise) {
        if (clockwise) {
            swapRows(matrix);
            transpose(matrix);
        } else {
            transpose(matrix);
            swapRows(matrix);
        }
    }

    private Marble[][] quandrantFromBoard(int nbQuadrant) {
        Marble[][] quadrant = new Marble[3][3];
        switch (nbQuadrant) {
            case 1:
                for (int i = 0; i < board.length - 3; i++) {
                    for (int j = 0; j < board.length - 3; j++) {
                        quadrant[i][j] = board[i][j];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < board.length - 3; i++) {
                    for (int j = 3; j < board.length; j++) {
                        quadrant[i][j - 3] = board[i][j];
                    }
                }
                break;
            case 3:
                for (int i = 3; i < board.length; i++) {
                    for (int j = 0; j < board.length - 3; j++) {
                        quadrant[i - 3][j] = board[i][j];
                    }
                }
                break;
            case 4:
                for (int i = 3; i < board.length; i++) {
                    for (int j = 3; j < board.length; j++) {
                        quadrant[i - 3][j - 3] = board[i][j];
                    }
                }
                break;
        }
        return quadrant;
    }

    private void quadrantToBoard(int nbQuadrant, Marble[][] quadrant) {
        switch (nbQuadrant) {
            case 1:
                for (int i = 0; i < board.length - 3; i++) {
                    for (int j = 0; j < board.length - 3; j++) {
                        board[i][j] = quadrant[i][j];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < board.length - 3; i++) {
                    for (int j = 3; j < board.length; j++) {
                        board[i][j] = quadrant[i][j - 3];
                    }
                }
                break;
            case 3:
                for (int i = 3; i < board.length; i++) {
                    for (int j = 0; j < board.length - 3; j++) {
                        board[i][j] = quadrant[i - 3][j];
                    }
                }
                break;
            case 4:
                for (int i = 3; i < board.length; i++) {
                    for (int j = 3; j < board.length; j++) {
                        board[i][j] = quadrant[i - 3][j - 3];
                    }
                }
                break;
        }
    }

    /**
     * Returns the marble at a certain position on the board.
     *
     * @param x the x position of the marble.
     * @param y the y position of the marble.
     * @return the marble at a certain position on the board.
     */
    Marble getMarbleAtPosition(int x, int y) {
        return board[x][y];
    }

    /**
     * Returns the length side of the board.
     *
     * @return the length side of the board.
     */
    int getSideBoard() {
        return board.length;
    }

    /**
     * Returns true if a player  has managed to line up 5 balls on the board.
     * 
     * @return true if a player  has managed to line up 5 balls on the board.
     */
    boolean checkAlignmentWinner() {
        return (topLeftBottomRightDiagonalCheckWinner() || 
                topRightBottomLeftDiagonalCheckWinner() || 
                horizontalCheckWinner() || 
                verticalCheckWinner());
    }
    
    /**
     * Returns true if the board of the game is full.
     * 
     * @return true if the board of the game is full.
     */
    boolean isFull() {
        for (Marble[] marbles : board) {
            for (Marble marble : marbles) {
                if (marble == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean topLeftBottomRightDiagonalCheckWinner() {
        int index = 0;
        Marble previousValue = board[1][0];
        for (int i = 1; i < board.length; i++) {
            if (previousValue == board[i][i - 1] && previousValue != null) {
                index++;
            } else {
                if (index == 5) {
                    return true;
                }
                index = 1;
                previousValue = board[i][i - 1];
            }
        }
        if (index == 5) {
            return true;
        }
        index = 0;
        previousValue = board[0][0];
        for (int i = 0; i < board.length; i++) {
            if (previousValue == board[i][i] && previousValue != null) {
                index++;
            } else {
                if (index == 5) {
                    return true;
                }
                index = 1;
                previousValue = board[i][i];
            }
        }
        if (index == 5) {
            return true;
        }
        index = 0;
        previousValue = board[0][1];
        for (int i = 1; i < board.length; i++) {
            if (previousValue == board[i - 1][i] && previousValue != null) {
                index++;
            } else {
                if (index == 5) {
                    return true;
                }
                index = 1;
                previousValue = board[i - 1][i];
            }
        }
        return index == 5;
    }

    private boolean topRightBottomLeftDiagonalCheckWinner() {
        int index = 0;
        Marble previousValue = board[0][4];
        for (int i = 0, j = 4; i < board.length - 1; i++, j--) {
            if (previousValue == board[i][j] && previousValue != null) {
                index++;
            } else {
                if (index == 5) {
                    return true;
                }
                index = 1;
                previousValue = board[i][j];
            }
        }
        if (index == 5) {
            return true;
        }

        index = 0;
        previousValue = board[0][5];
        for (int i = 0, j = 5; i < board.length; i++, j--) {
            if (previousValue == board[i][j] && previousValue != null) {
                index++;
            } else {
                if (index == 5) {
                    return true;
                }
                index = 1;
                previousValue = board[i][j];
            }
        }
        if (index == 5) {
            return true;
        }

        index = 0;
        previousValue = board[1][5];
        for (int i = 1, j = 5; i < board.length; i++, j--) {
            if (previousValue == board[i][j] && previousValue != null) {
                index++;
            } else {
                if (index == 5) {
                    return true;
                }
                index = 1;
                previousValue = board[i][j];
            }
        }
        return index == 5;
    }

    private boolean horizontalCheckWinner() {
        int index = 0;
        Marble previousValue;
        for (int i = 0; i < board.length; i++) {
            previousValue = board[i][0];
            for (int j = 0; j < board.length; j++) {
                if (previousValue == board[i][j]  && previousValue != null) {
                    index++;
                } else {
                    if (index == 5) {
                        return true;
                    }
                    index = 1;
                    previousValue = board[i][j];
                }
            }
            if (index == 5) {
                return true;
            }
            index = 0;
        }
        return false;
    }

    private boolean verticalCheckWinner() {
        int index = 0;
        Marble previousValue;
        for (int i = 0; i < board.length; i++) {
            previousValue = board[0][i];
            for (int j = 0; j < board.length; j++) {
                if (previousValue == board[j][i] && previousValue != null) {
                    index++;
                } else {
                    if (index == 5) {
                        return true;
                    }
                    index = 1;
                    previousValue = board[j][i];
                }
            }
            if (index == 5) {
                return true;
            }
            index = 0;
        }
        return false;
    }

}
