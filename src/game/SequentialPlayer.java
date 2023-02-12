package game;

public class SequentialPlayer implements Player {
    @Override
    public Move makeMove(Position position) {
        final MNKBoard board = (MNKBoard) position;
        final int m = position.getM(board);
        final int n = position.getN(board);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                final Move move = new Move(r, c, position.getTurn());
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError("No valid moves");
    }
}
