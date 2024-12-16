package Main;

import java.util.ArrayList;

public class Piece {
    public Color color;
    public int x, y;
    private ArrayList<Integer[]> prevCords = new ArrayList<>();

    public Piece(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void addPrevCords(Integer[] dir) {
        prevCords.add(dir);
    }

    public void clearPrevCords() {
        prevCords.clear();
    }

    public ArrayList<Integer[]> getPrevCords() {
        return prevCords;
    }
}
