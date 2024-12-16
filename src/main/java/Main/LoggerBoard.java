package Main;

public class LoggerBoard implements GameListener{
    Board ourBoard;
    @Override
    public void updateBoard(Board board) {
        System.out.println("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                Piece cell = board.getBoardMap()[j][i];
                if (cell.color == Color.White) {
                    System.out.print("W ");
                } else if (cell.color == Color.Black) {
                    System.out.print("B ");
                } else if (cell.color == Color.CanPlace) {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
