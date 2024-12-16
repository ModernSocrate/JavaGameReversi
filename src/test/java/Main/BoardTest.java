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
        assertEquals(4, board.getPieceAmount(CanPlace), "Expected 4 possible moves for the Black player.");

        Piece[][] boardMap = board.getBoardMap();
        assertEquals(CanPlace, boardMap[2][3].color, "Expected CanPlace at (2,3).");
        assertEquals(CanPlace, boardMap[3][2].color, "Expected CanPlace at (3,2).");
        assertEquals(CanPlace, boardMap[4][5].color, "Expected CanPlace at (4,5).");
        assertEquals(CanPlace, boardMap[5][4].color, "Expected CanPlace at (5,4).");
    }

    @Test
    void executeNextMove() {
        Player playerWhite = new PlayerHuman(White);
        Player playerBlack = new PlayerHuman(Black);
        Board board = new Board(playerWhite, playerBlack);

        board.determinePossibleMoves();
        board.executeNextMove(2, 3);

        Piece[][] boardMap = board.getBoardMap();
        assertEquals(Black, boardMap[2][3].color, "Expected Black piece at (2,3).");
        assertEquals(Black, boardMap[3][3].color, "Expected Black piece at (3,3) (flipped).");
        assertEquals(Black, boardMap[4][3].color, "Expected Black piece at (4,3) (unchanged).");
        assertEquals(playerWhite, board.whoseMove, "Expected White's turn after Black's move.");
    }

    @Test
    void readInput() {
        Player playerWhite = new PlayerAI(White);
        Player playerBlack = new PlayerAI(Black);
        Board board = new Board(playerWhite, playerBlack);

        board.determinePossibleMoves();
        board.readInput(playerBlack);

        Piece[][] boardMap = board.getBoardMap();
        assertEquals(Black, boardMap[2][3].color, "Expected Black piece at (2,3).");
        assertEquals(Black, boardMap[3][3].color, "Expected Black piece at (3,3) (flipped).");
    }
}
