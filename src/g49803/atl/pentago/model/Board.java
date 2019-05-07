package g49803.atl.pentago.model;

/**
 * Represents the board of the pentago game.
 *
 * @author Jeremy Gillard
 */
public class Board {
    
    private final Quadrant[] board;

    /**
     * Allows to create a board with a side length precised.
     */
    public Board() {
        board = new Quadrant[4];
        for (int i = 0; i < board.length; i++) {
            board[i] = new Quadrant();
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
     * @param direction the direction in which to turn the quadrant.
     */
    void turnQuadrant(int quadrantPosition, boolean direction) {
        board[quadrantPosition].rotate(direction);
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

//    /**
//     * Returns true if a player  has managed to line up 5 balls on the board.
//     * 
//     * @return true if a player  has managed to line up 5 balls on the board.
//     */
//    boolean checkAlignmentWinner() {
//        return (topLeftBottomRightDiagonalCheckWinner() || 
//                topRightBottomLeftDiagonalCheckWinner() || 
//                horizontalCheckWinner() || 
//                verticalCheckWinner());
//    }
//    
//    /**
//     * Returns true if the board of the game is full.
//     * 
//     * @return true if the board of the game is full.
//     */
//    boolean isFull() {
//        for (Marble[] marbles : board) {
//            for (Marble marble : marbles) {
//                if (marble == null) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    
    private int getQuadrantNumber(int x, int y) {
        return x / 3 + 2 * (y / 3);
    }
    
    private int fromBoardToQuadrantCoordinates(int coord) {
        return coord % 3; //utiliser une constante à la place de trois!!!!
    }
    
    
    
//    // Méthode à fort retravailler pour moins de ligne: possibilité d'en avoir
//    // une seule avec paramètre.
//
//    private boolean topLeftBottomRightDiagonalCheckWinner() {
//        int index = 0;
//        Marble previousValue = board[1][0];
//        for (int i = 1; i < board.length; i++) {
//            if (previousValue == board[i][i - 1] && previousValue != null) {
//                index++;
//            } else {
//                if (index == 5) {
//                    return true;
//                }
//                index = 1;
//                previousValue = board[i][i - 1];
//            }
//        }
//        if (index == 5) {
//            return true;
//        }
//        index = 0;
//        previousValue = board[0][0];
//        for (int i = 0; i < board.length; i++) {
//            if (previousValue == board[i][i] && previousValue != null) {
//                index++;
//            } else {
//                if (index == 5) {
//                    return true;
//                }
//                index = 1;
//                previousValue = board[i][i];
//            }
//        }
//        if (index == 5) {
//            return true;
//        }
//        index = 0;
//        previousValue = board[0][1];
//        for (int i = 1; i < board.length; i++) {
//            if (previousValue == board[i - 1][i] && previousValue != null) {
//                index++;
//            } else {
//                if (index == 5) {
//                    return true;
//                }
//                index = 1;
//                previousValue = board[i - 1][i];
//            }
//        }
//        return index == 5;
//    }
//
//    private boolean topRightBottomLeftDiagonalCheckWinner() {
//        int index = 0;
//        Marble previousValue = board[0][4];
//        for (int i = 0, j = 4; i < board.length - 1; i++, j--) {
//            if (previousValue == board[i][j] && previousValue != null) {
//                index++;
//            } else {
//                if (index == 5) {
//                    return true;
//                }
//                index = 1;
//                previousValue = board[i][j];
//            }
//        }
//        if (index == 5) {
//            return true;
//        }
//
//        index = 0;
//        previousValue = board[0][5];
//        for (int i = 0, j = 5; i < board.length; i++, j--) {
//            if (previousValue == board[i][j] && previousValue != null) {
//                index++;
//            } else {
//                if (index == 5) {
//                    return true;
//                }
//                index = 1;
//                previousValue = board[i][j];
//            }
//        }
//        if (index == 5) {
//            return true;
//        }
//
//        index = 0;
//        previousValue = board[1][5];
//        for (int i = 1, j = 5; i < board.length; i++, j--) {
//            if (previousValue == board[i][j] && previousValue != null) {
//                index++;
//            } else {
//                if (index == 5) {
//                    return true;
//                }
//                index = 1;
//                previousValue = board[i][j];
//            }
//        }
//        return index == 5;
//    }
//
//    private boolean horizontalCheckWinner() {
//        int index = 0;
//        Marble previousValue;
//        for (int i = 0; i < board.length; i++) {
//            previousValue = board[i][0];
//            for (int j = 0; j < board.length; j++) {
//                if (previousValue == board[i][j]  && previousValue != null) {
//                    index++;
//                } else {
//                    if (index == 5) {
//                        return true;
//                    }
//                    index = 1;
//                    previousValue = board[i][j];
//                }
//            }
//            if (index == 5) {
//                return true;
//            }
//            index = 0;
//        }
//        return false;
//    }
//
//    private boolean verticalCheckWinner() {
//        int index = 0;
//        Marble previousValue;
//        for (int i = 0; i < board.length; i++) {
//            previousValue = board[0][i];
//            for (int j = 0; j < board.length; j++) {
//                if (previousValue == board[j][i] && previousValue != null) {
//                    index++;
//                } else {
//                    if (index == 5) {
//                        return true;
//                    }
//                    index = 1;
//                    previousValue = board[j][i];
//                }
//            }
//            if (index == 5) {
//                return true;
//            }
//            index = 0;
//        }
//        return false;
//    }

}
