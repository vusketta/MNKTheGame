package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move makeMove(Position position) {
        final MNKBoard board = (MNKBoard) position;
        final int m = position.getM(board);
        final int n = position.getN(board);

        while (true) {
            final Move move = new Move(
                    random.nextInt(m),
                    random.nextInt(n),
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
