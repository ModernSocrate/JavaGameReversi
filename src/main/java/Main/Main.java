package Main;

import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlayerFactory factory = new PlayerFactory();
        Board board = new Board(factory.createPlayer(Color.White), factory.createPlayer(Color.Black));
        VisualBoard visualBoard = new VisualBoard();
        LoggerBoard loggerBoard = new LoggerBoard();
        board.eventManager.addObserver(visualBoard);
        board.eventManager.addObserver(loggerBoard);
        while(!board.isEndgame()) {
            board.boardLoop();
        }
        System.out.println("Game ended. Result: Black - " + board.getPieceAmount(Color.Black) + ", White - " + board.getPieceAmount(Color.White) + ".");
    }
}