package Main;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        VisualBoard visualBoard = new VisualBoard(board);
        while(!board.isEndgame()) {
            board.determinePossibleMoves();
            visualBoard.gamePanel.updateBoard(board);
            board.readInput();
        }
        System.out.println("Game ended. Result: Black - " + board.getPieceAmount(Color.Black) + ", White - " + board.getPieceAmount(Color.White) + ".");
    }
}