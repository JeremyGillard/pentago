package g49803.atl.pentago.model;

/**
 * Represents the board of the pentago game.
 *
 * @author Jeremy Gillard
 */
public class Board {

    private final Marble[][] board;

    /**
     * Allows to create a 6x6 board.
     */
    public Board() {
        board = new Marble[6][6];
    }

    /**
     * Checks if the cell of the board at the col and row position is empty or
     * not.
     *
     * @param col the colomn concerned.
     * @param row the row concerned.
     * @return true if the cell concerned is empty.
     */
    public boolean isEmptyCell(int col, int row) {
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
    public void fillCell(int col, int row, Marble marbleColor) {
        board[col][row] = marbleColor;
    }

    /**
     * Allows to turn a quadrant I, II, III, IV in the clockwise direction or
     * the opposite of clockwise direction.
     *
     * @param quadrantPosition the quadrant to be turned.
     * @param direction the direction in which to turn the quadrant.
     */
    public void turnQuadrant(int quadrantPosition, boolean direction) {
        //only for the 1st delivery
        String description = "Quadrant ";
        switch (quadrantPosition) {
            case 1:
                description += "I rotation to the ";
                break;
            case 2:
                description += "II rotation to the ";
                break;
            case 3:
                description += "III rotation to the ";
                break;
            case 4:
                description += "IV rotation to the ";
                break;
            default:
                description += "NOT DEFINED ";
        }
        if (direction) {
            description += "right";
        } else {
            description += "left";
        }
        System.out.println(description);
    }

    /**
     * Returns the tab[][] representation of the board.
     *
     * @return the tab[][] representation of the board.
     */
    public Marble[][] getArrangement() {
        return board;
    }

    @Override
    public String toString() {
        String description = "";
        for (Marble[] marbles : board) {
            for (Marble marble : marbles) {
                if (marble == null) {
                    description += " ";
                } else {
                    description += "O";
                }
            }
            description += "\n";
        }
        return description;
    }

}
