/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeremy
 */
public class BoardTest {

    @Test
    public void isEmptyCellTest() {
        Board board = new Board(6);
        assertTrue(board.isEmptyCell(0, 0));
    }

    @Test
    public void fillCellIsEmptyCellFirstQuadrantTest() {
        Board board = new Board(4);
        board.fillCell(0, 0, Marble.BLACK);
        assertFalse(board.isEmptyCell(0, 0));
    }

    @Test
    public void fillCellIsEmptyCellLastQuadrantTest() {
        Board board = new Board(4);
        board.fillCell(3, 3, Marble.BLACK);
        assertFalse(board.isEmptyCell(3, 3));
    }

    @Test
    public void fillCellIsEmptyCellSecondQuadrantTest() {
        Board board = new Board(6);
        board.fillCell(4, 1, Marble.BLACK);
        assertFalse(board.isEmptyCell(4, 1));
    }

    @Test
    public void turnFirstQuadrantTest() {
        Board board = new Board(6);
        board.fillCell(0, 0, Marble.BLACK);
        board.turnQuadrant(0, true);
        assertFalse(board.isEmptyCell(2, 0));
    }

    @Test
    public void turnSecondQuadrantTest() {
        Board board = new Board(6);
        board.fillCell(5, 0, Marble.BLACK);
        board.turnQuadrant(1, true);
        assertFalse(board.isEmptyCell(5, 2));
    }

    @Test
    public void getMarbleAtPositionBasicTest1() {
        Board board = new Board(6);
        assertTrue(board.getMarbleAtPosition(0, 0) == null);
    }

    @Test
    public void getMarbleAtPositionBasicTest2() {
        Board board = new Board(6);
        board.fillCell(0, 0, Marble.BLACK);
        assertTrue(board.getMarbleAtPosition(0, 0) == Marble.BLACK);
    }

    @Test
    public void getMarbleAtPositionGeneralTest() {
        Board board = new Board(6);
        board.fillCell(4, 2, Marble.BLACK);
        assertTrue(board.getMarbleAtPosition(4, 2) == Marble.BLACK);
    }

    @Test
    public void getMarbleAtPositionAfterSecondQuadrantRotationClockwise() {
        Board board = new Board(6);
        board.fillCell(4, 2, Marble.BLACK);
        board.turnQuadrant(1, true);
        assertTrue(board.getMarbleAtPosition(3, 1) == Marble.BLACK);
    }

    @Test
    public void getMarbleAtPositionAfterThirdQuadrantRotationNonClockwise() {
        Board board = new Board(6);
        board.fillCell(2, 4, Marble.BLACK);
        board.turnQuadrant(2, false);
        assertTrue(board.getMarbleAtPosition(1, 3) == Marble.BLACK);
    }

    @Test
    public void checkHorizontalAlignmentFirstLineNotOkTest() {
        Board board = new Board(6);
        assertTrue(board.checkAlignment(0, 0, 1, 0, 5) == null);
    }

    @Test
    public void checkHorizontalAlignmentFirstLineOkTest() {
        Board board = new Board(6);
        for (int i = 1; i < 6; i++) {
            board.fillCell(i, 0, Marble.BLACK);
        }
        assertTrue(board.checkAlignment(0, 0, 1, 0, 5) == Marble.BLACK);
    }

    @Test
    public void checkHorizontalAlignmentLastLineOkTest() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(i, 5, Marble.BLACK);
        }
        assertTrue(board.checkAlignment(0, 5, 1, 0, 5) == Marble.BLACK);
    }

    @Test
    public void checkVerticalAlignmentColumnNotOkTest() {
        Board board = new Board(6);
        assertTrue(board.checkAlignment(4, 0, 0, 1, 5) == null);
    }

    @Test
    public void checkVerticalAlignmentColumnOkTest() {
        Board board = new Board(6);
        for (int i = 0; i < 6; i++) {
            board.fillCell(3, i, Marble.WHITE);
        }
        assertTrue(board.checkAlignment(3, 0, 0, 1, 5) == Marble.WHITE);
    }

    @Test
    public void checkVerticalAlignmentLastColumnOkTest() {
        Board board = new Board(6);
        for (int i = 0; i < 6; i++) {
            board.fillCell(5, i, Marble.WHITE);
        }
        assertTrue(board.checkAlignment(5, 0, 0, 1, 5) == Marble.WHITE);
    }

    @Test
    public void checkTopLeftBottomRightAlignmentNotOkTest() {
        Board board = new Board(6);
        assertTrue(board.checkAlignment(0, 0, 1, 1, 5) == null);
    }

    @Test
    public void checkFirstTopLeftBottomRightAlignmentOkTest() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(i, i + 1, Marble.WHITE);
        }
        assertTrue(board.checkAlignment(0, 1, 1, 1, 5) == Marble.WHITE);
    }

    @Test
    public void checkSecondTopLeftBottomRightAlignmentOkTest() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(i, i, Marble.WHITE);
        }
        assertTrue(board.checkAlignment(0, 0, 1, 1, 5) == Marble.WHITE);
    }

    @Test
    public void checkThirdTopLeftBottomRightAlignmentOkTest() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(i + 1, i, Marble.WHITE);
        }
        assertTrue(board.checkAlignment(1, 0, 1, 1, 5) == Marble.WHITE);
    }

    @Test
    public void checkTopRightBottomLeftAlignmentNotOkTest() {
        Board board = new Board(6);
        assertTrue(board.checkAlignment(5, 0, -1, 1, 5) == null);
    }

    @Test
    public void checkFirstTopRightBottomLeftAlignmentOkTest() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(4 - i, i, Marble.BLACK);
        }
        assertTrue(board.checkAlignment(4, 0, -1, 1, 5) == Marble.BLACK);
    }

    @Test
    public void checkSecondTopRightBottomLeftAlignmentOkTest() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(5 - i, i, Marble.BLACK);
        }
        assertTrue(board.checkAlignment(5, 0, -1, 1, 5) == Marble.BLACK);
    }

    @Test
    public void checkThirdTopRightBottomLeftAlignmentOkTest() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(5 - i, i + 1, Marble.BLACK);
        }
        assertTrue(board.checkAlignment(5, 1, -1, 1, 5) == Marble.BLACK);
    }

    @Test
    public void checkWinAlignementNoWinnerTest() {
        Board board = new Board(6);
        assertTrue(board.checkWinAlignmentFor(Marble.BLACK, 5) == null);
    }

    @Test
    public void checkWinAlignementHorizontalWinnerTest() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(i, 3, Marble.BLACK);
        }
        assertTrue(board.checkWinAlignmentFor(Marble.BLACK, 5) == Marble.BLACK);
    }

    @Test
    public void checkWinAlignementVerticalWinnerTest() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(5, i, Marble.BLACK);
        }
        assertTrue(board.checkWinAlignmentFor(Marble.BLACK, 5) == Marble.BLACK);
    }

    @Test
    public void checkWinAlignementDiagonalWinnerTest1() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(i, i, Marble.BLACK);
        }
        assertTrue(board.checkWinAlignmentFor(Marble.BLACK, 5) == Marble.BLACK);
    }

    @Test
    public void checkWinAlignementDiagonalWinnerTest2() {
        Board board = new Board(6);
        for (int i = 0; i < 5; i++) {
            board.fillCell(i + 1, i, Marble.BLACK);
        }
        assertTrue(board.checkWinAlignmentFor(Marble.BLACK, 5) == Marble.BLACK);
    }

    @Test
    public void checkWinAlignementDiagonalWinnerTest3() {
        Board board = new Board(6);
        for (int i = 5, j = 0; i > 0 && j < 5; i--, j++) {
            board.fillCell(i, j, Marble.BLACK);
        }
        assertTrue(board.checkWinAlignmentFor(Marble.BLACK, 5) == Marble.BLACK);
    }
    
    @Test
    public void checkWinAlignementDiagonalWinnerTest4() {
        Board board = new Board(6);
        for (int i = 4, j = 0; i >= 0 && j < 5; i--, j++) {
            board.fillCell(i, j, Marble.BLACK);
        }
        assertTrue(board.checkWinAlignmentFor(Marble.BLACK, 5) == Marble.BLACK);
    }

    @Test
    public void isFullEmptyBoardTest() {
        Board board = new Board(4);
        assertFalse(board.isFull());
    }

    @Test
    public void isFullSomeMarbleBoardTest() {
        Board board = new Board(6);
        board.fillCell(0, 0, Marble.BLACK);
        board.fillCell(3, 2, Marble.BLACK);
        board.fillCell(4, 5, Marble.BLACK);
        assertFalse(board.isFull());
    }

    @Test
    public void isFullFullBoardTest() {
        Board board = new Board(6);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board.fillCell(i, j, Marble.BLACK);
            }
        }
        assertTrue(board.isFull());
    }

}
