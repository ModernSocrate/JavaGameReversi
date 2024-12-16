package Main;

import java.util.Scanner;

public class PlayerHuman extends Player{
    public PlayerHuman(Color color) {
        super(color);
        isHuman=true;
    }

    public int[] makeMove() {
        Scanner input = new Scanner(System.in);
        int x,y;
        System.out.println("Enter value x:");
        x = input.nextInt()-1;
        System.out.println("Enter value x:");
        y = input.nextInt()-1;
        return new int[] {x,y};
    }
}
