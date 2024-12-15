package Main;

import javax.swing.*;

import static Main.GamePanel.*;

public class VisualBoard {
    private final JFrame gameFrame;
    public GamePanel gamePanel;
    public VisualBoard(Board board) {
        this.gameFrame = new JFrame("Reversi");
        this.gameFrame.setSize(squareSize*squareAmount+squareGap*(squareAmount+4)+1,squareSize*squareAmount+squareGap*(squareAmount+4)+24);
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameFrame.setLocationRelativeTo(null);
        this.gamePanel = new GamePanel(board);
        this.gameFrame.add(gamePanel);
        this.gameFrame.setVisible(true);
    }
}
