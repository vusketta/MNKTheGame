package game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Choose first player");
        Player firstplayer = getPlayer(in);

        System.out.println("Choose second player");
        Player secondplayer = getPlayer(in);

        int bestOf = 0;
        do {
            try {
                System.out.println("Input number of games to win");
                String num = in.next();
                bestOf = Integer.parseInt(num);
            } catch (NumberFormatException e) {
                System.err.println("Try to print number");
            }
        } while (bestOf <= 0);

        int matchResult = new Match(
                new TwoPlayerGame(
                        new MNKBoard(), firstplayer, secondplayer
                ), bestOf
        ).playMatch(false);

        switch (matchResult) {
            case 1 -> System.out.println("\nFirst player is a winner of BO" + bestOf);
            case 2 -> System.out.println("\nSecond player is a winner of BO" + bestOf);
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
