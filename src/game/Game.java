package game;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Choose first player");
        Player firstPlayer = getPlayer(in);

        System.out.println("Choose second player");
        Player secondPlayer = getPlayer(in);

        int result = new TwoPlayerGame(
                new MNKBoard(),
                firstPlayer,
                secondPlayer
        ).play(false);

        switch (result) {
            case 1 -> System.out.println("\nFirst player won");
            case 2 -> System.out.println("\nSecond player won");
            case 0 -> System.out.println("\nDraw");
            default -> throw new AssertionError("\nUnknown result " + result);
        }
    }

    public static Player getPlayer(Scanner in) {
        System.out.println("   h - human");
        System.out.println("   s - sequential");
        System.out.println("   r - random");

        String player = in.next();
        return switch (player) {
            case "h" -> new HumanPlayer(new Scanner(System.in));
            case "s" -> new SequentialPlayer();
            case "r" -> new RandomPlayer();
            default -> throw new IllegalArgumentException("No such player name");
        };
    }
}
