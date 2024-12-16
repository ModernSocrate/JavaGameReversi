package Main;

public abstract class Player {
    public Color color;
    public boolean isHuman;

    abstract int[] makeMove();
    public Player(Color color) {
        this.color = color;
    }
}