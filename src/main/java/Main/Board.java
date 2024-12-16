package Main;

import java.util.ArrayList;

import static Main.Color.*;
import static Main.Util.isWithinBounds;

public class Board {
    private Piece[][] boardMap = new Piece[8][8];
    EventManager eventManager;
    Player whoseMove;
    Player whoseNotMove;
    boolean endgame = false;

    public boolean isEndgame() {
        return endgame;
    }

    public Board(Player playerWhite, Player playerBlack) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                boardMap[i][j] = new Piece(Empty, i, j);
            }
        boardMap[3][3] = new Piece(White, 3, 3);
        boardMap[4][4] = new Piece(White, 4, 4);
        boardMap[4][3] = new Piece(Black, 4, 3);
        boardMap[3][4] = new Piece(Black, 3, 4);
        whoseMove = playerBlack;
        whoseNotMove = playerWhite;
        eventManager = new EventManager();
    }

    public void boardLoop() {
        determinePossibleMoves();
        eventManager.notifyObservers(this);
        if (!endgame) readInput(whoseMove);
    }

    public void determinePossibleMoves() {
        ArrayList<Piece> pieceList = createPieceList(whoseMove.color);
        for (Piece piece : pieceList)
            determinePieceNextMove(piece);
        if (createPieceList(CanPlace).isEmpty()) {
            updateRound();
            pieceList = createPieceList(whoseMove.color);
            if (createPieceList(CanPlace).isEmpty()) {
                endgame = true;
            }
        }
    }

    public void executeNextMove(int x, int y) {
        System.out.println("Player " + whoseMove.color.toString() + " moved to " + x + " " + y + ".");
        boardMap[x][y].color = whoseMove.color;
        changePieceColor(boardMap[x][y]);
        updateRound();
    }

    private void updateRound() {
        cleanPossibleMoves();
        Player temp = whoseMove;
        whoseMove = whoseNotMove;
        whoseNotMove = temp;
    }

    private void changePieceColor(Piece piece) {
        for (Integer[] item : piece.getPrevCords()) {
            int x = piece.x - item[0];
            int y = piece.y - item[1];
            if (isWithinBounds(x, y)) {
                while (isWithinBounds(x, y) && boardMap[x][y].color == whoseNotMove.color) {
                    boardMap[x][y].color = whoseMove.color;
                    x = x - item[0];
                    y = y - item[1];
                }
            }
        }
    }

    private void cleanPossibleMoves() {
        ArrayList<Piece> pieceList = createPieceList(CanPlace);
        for (Piece piece : pieceList) {
            piece.color = Empty;
        }
    }

    private void determinePieceNextMove(Piece piece) {
        Integer[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (Integer[] dir : directions) {
            int x = piece.x + dir[0];
            int y = piece.y + dir[1];
            if (isWithinBounds(x, y) && boardMap[x][y].color == whoseNotMove.color) {
                while (isWithinBounds(x, y) && boardMap[x][y].color != Empty && boardMap[x][y].color != CanPlace) {
                    x = x + dir[0];
                    y = y + dir[1];
                }
                if (isWithinBounds(x, y) && boardMap[x][y].color == Empty) {
                    boardMap[x][y].color = CanPlace;
                    boardMap[x][y].addPrevCords(dir);
                }
            }
        }
    }

    public Piece[][] getBoardMap() {
        return boardMap;
    }

    private ArrayList<Piece> createPieceList(Color color) {
        ArrayList<Piece> pieceList = new ArrayList<Piece>();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (boardMap[i][j].color == color)
                    pieceList.add(boardMap[i][j]);
            }
        return pieceList;
    }

    public int getPieceAmount(Color color) {
        return createPieceList(color).size();
    }

    public void readInput(Player player) {
        int[] cords = {0, 0}; //x,y
        boolean isValidMove = false;
        while (!isValidMove) {
            try {
                cords = player.makeMove();
                if (!isWithinBounds(cords[0], cords[1]) || getBoardMap()[cords[0]][cords[1]].color != CanPlace) {
                    throw new Exception("Invalid move");
                }
                isValidMove = true;
            } catch (Exception e) {
                if (player.isHuman)
                    System.out.println("Invalid input. Please try again.");
            }
        }
        executeNextMove(cords[0], cords[1]);
    }

}
