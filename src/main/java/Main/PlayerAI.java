package Main;

import java.util.Random;

public class PlayerAI extends Player{
    public PlayerAI(Color color) {
        super(color);
        isHuman=false;
    }

    public int[] makeMove() {
        Random random = new Random();
        return new int[] {random.nextInt(8),random.nextInt(8)};
    }
}
