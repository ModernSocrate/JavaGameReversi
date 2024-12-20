package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Main.Color.*;

public class GamePanel extends JPanel implements GameListener {
    BufferedImage blackImg, whiteImg, possibleImg;
    Board ourBoard;
    static final int squareAmount = 8;
    static final int squareSize = 80;
    static final int squareGap = 5;

    public GamePanel() {
        super();
        ourBoard = new Board(new PlayerAI(White), new PlayerAI(Black));
        try {
            blackImg = ImageIO.read(new File("src/main/resources/black.png"));
            whiteImg = ImageIO.read(new File("src/main/resources/white.png"));
            possibleImg = ImageIO.read(new File("src/main/resources/possible.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBoard(Board board) {
        this.ourBoard = board;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        drawBoard((Graphics2D) g);
        drawPieces((Graphics2D) g);
    }

    public void drawBoard(Graphics2D g) {
        g.setColor(Color.GREEN);
        for (int i = 0; i < squareAmount; i++) {
            for (int j = 0; j < squareAmount; j++) {
                int x = i * (squareSize + squareGap) + squareGap;
                int y = j * (squareSize + squareGap) + squareGap;
                g.fillRect(x, y, squareSize, squareSize);
            }
        }
    }

    public void drawPieces(Graphics2D g) {
        Piece[][] cords = ourBoard.getBoardMap();
        for (int i = 0; i < squareAmount; i++) {
            for (int j = 0; j < squareAmount; j++) {
                int x = i * (squareSize + squareGap) + squareGap;
                int y = j * (squareSize + squareGap) + squareGap;
                if (cords[i][j].color == White) {
                    g.drawImage(whiteImg, x, y, squareSize, squareSize, this);
                } else if (cords[i][j].color == Black) {
                    g.drawImage(blackImg, x, y, squareSize, squareSize, this);
                } else if (cords[i][j].color == CanPlace) {
                    g.drawImage(possibleImg, x, y, squareSize, squareSize, this);
                }
            }
        }
    }
}
