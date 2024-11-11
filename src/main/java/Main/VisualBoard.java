package Main;

import javax.swing.*;

public class VisualBoard {
    private final JFrame gameFrame;
    public GamePanel gamePanel;
    public VisualBoard(Board board) {
        this.gameFrame = new JFrame("Reversi");
        this.gameFrame.setSize(660,685);
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameFrame.setLocationRelativeTo(null);
        this.gamePanel = new GamePanel(board);
        this.gameFrame.add(gamePanel);
        this.gameFrame.setVisible(true);
    }
}
