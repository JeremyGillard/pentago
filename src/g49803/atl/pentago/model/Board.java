package g49803.atl.pentago.model;

/**
 *
 * @author g49803
 */
public class Board {
    
    private Marble[][] board;
    
    public Board() {
        this.board = new Marble[6][6];
    }
    
    public boolean isEmptyCell(int col, int row) {
        return this.board[col][row] == null;
    }
    
    public void fillCell(int col, int row, Marble marbleColor){
        this.board[col][row] = marbleColor;
    }
    
    
}
