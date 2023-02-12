package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + position.getTurn());

        int row, col;

        while (true) {
            row = in.nextInt() - 1;
            col = in.nextInt() - 1;
            if (position.isValid(new Move(row, col, position.getTurn()))) break;
            System.out.println("Input move is incorrect");
            System.out.println("Try again");
        }

        return new Move(row, col, position.getTurn());
    }
}
