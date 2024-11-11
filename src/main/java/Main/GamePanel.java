package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Main.Color.*;

public class GamePanel extends JPanel {
    BufferedImage blackImg, whiteImg, possibleImg;
    Board ourBoard;
    int size = 80;
    int gap = 5;
    public GamePanel(Board board) {
        super();
        ourBoard = board;
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.fillRect(i * size + gap, j * size + gap, size - gap, size - gap);
            }
        }
    }

    public void drawPieces(Graphics2D g) {
        Piece[][] cords = ourBoard.getBoardMap();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int x = i * size + gap;
                int y = j * size + gap;
                if (cords[i][j].color == White) {
                    g.drawImage(whiteImg, x, y, size - gap, size - gap, this);
                } else if (cords[i][j].color == Black) {
                    g.drawImage(blackImg, x, y, size - gap, size - gap, this);
                } else if (cords[i][j].color == CanPlace) {
                    g.drawImage(possibleImg, x, y, size - gap, size - gap, this);
                }
            }
        }
    }
}
