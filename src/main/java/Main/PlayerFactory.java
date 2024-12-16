package Main;

import java.util.Scanner;

public class PlayerFactory {
    public Player createPlayer(Color color) {
        Player player = null;
        System.out.println("Select type of player for " + color.toString() + ".");
        System.out.println("1. PLAYER");
        System.out.println("2. AI");
        int answer = new Scanner(System.in).nextInt();
        switch (answer) {
            case 1:
                player = new PlayerHuman(color);
                break;
            case 2:
                player = new PlayerAI(color);
                break;
            default:
                player = new PlayerHuman(color);
                break;
        }
        return player;
    }
}
