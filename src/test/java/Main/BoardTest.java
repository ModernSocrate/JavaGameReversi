package Main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static Main.Color.*;

class BoardTest {

    @Test
    void determinePossibleMoves() {
        Player playerWhite = new PlayerHuman(White);
        Player playerBlack = new PlayerHuman(Black);
        Board board = new Board(playerWhite, playerBlack);

        board.determinePossibleMoves();
        assertEquals(4, board.getPieceAmount(CanPlace));

        Piece[][] boardMap = board.getBoardMap();
        assertEquals(CanPlace, boardMap[2][3].color);
        assertEquals(CanPlace, boardMap[3][2].color);
        assertEquals(CanPlace, boardMap[4][5].color);
        assertEquals(CanPlace, boardMap[5][4].color);
    }

    @Test
    void executeNextMove() {
        Player playerWhite = new PlayerHuman(White);
        Player playerBlack = new PlayerHuman(Black);
        Board board = new Board(playerWhite, playerBlack);

        board.determinePossibleMoves();
        board.executeNextMove(2, 3);

        Piece[][] boardMap = board.getBoardMap();
        assertEquals(Black, boardMap[2][3].color);
        assertEquals(Black, boardMap[3][3].color);
        assertEquals(Black, boardMap[4][3].color);
        assertEquals(playerWhite, board.whoseMove);
    }

    @Test
    void readInput() {
        Player playerWhite = new PlayerAI(White);
        Player playerBlack = new PlayerAI(Black);
        Board board = new Board(playerWhite, playerBlack);

        board.determinePossibleMoves();
        board.readInput(playerBlack);

        Piece[][] boardMap = board.getBoardMap();
        assertEquals(Black, boardMap[2][3].color);
        assertEquals(Black, boardMap[3][3].color);
    }
}
