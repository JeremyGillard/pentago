/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49803.atl.pentago;

import g49803.atl.pentago.model.Board;
import g49803.atl.pentago.model.Marble;

/**
 *
 * @author g49803
 */
public class Main {
    
    public static void main(String[] args) {
        
        Board board = new Board();
        
        System.out.println(board);
        
        System.out.println(board.isEmptyCell(0, 0));
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board.fillCell(0, 0, Marble.BLACK);
            }
        }
        
        System.out.println(board.isEmptyCell(0, 0));
        
        System.out.println(board);
        
    }
    
}
