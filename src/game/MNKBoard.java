package game;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );

    private Cell[][] field;
    private int m;
    private int n;
    private int k;
    private Cell turn;

    public MNKBoard() {
        Scanner in = new Scanner(System.in);
        System.out.println("MNK-game");
        while (true) {
            try {
                String num = in.next();
                m = Integer.parseInt(num);
                num = in.next();
                n = Integer.parseInt(num);
                num = in.next();
                k = Integer.parseInt(num);
            } catch (NumberFormatException | NoSuchElementException e) {
                System.err.println("Try to print number");
            }

            if (m <= 0 || n <= 0 || k <= 0 || (k > n && k > m)) {
                System.out.println("Input MNK is incorrect");
            } else {
                break;
            }
        }
        field = new Cell[n][m];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public int getM(Board board) {
        return m;
    }

    @Override
    public int getN(Board board) {
        return n;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public void clear() {
        field = new Cell[n][m];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOOSE;
        }

        int emptyCells = 0;
        field[move.getRow()][move.getCol()] = move.getValue();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (field[r][c] == Cell.E) {
                    emptyCells++;
                }

                int upL = 0;
                int upM = 0;
                int upR = 0;
                int midL = 0;
                int midR = 0;
                int downL = 0;
                int downM = 0;
                int downR = 0;

                for (int line = 0; line < k; line++) {
                    if (r - line >= 0 && c + line < m && field[r - line][c + line] == turn) {
                        upL++;
                    }
                    if (c + line < m && field[r][c + line] == turn) {
                        upM++;
                    }
                    if (r + line < n && c + line < m && field[r + line][c + line] == turn) {
                        upR++;
                    }
                    if (r - line >= 0 && field[r - line][c] == turn) {
                        midL++;
                    }
                    if (r + line < n && field[r + line][c] == turn) {
                        midR++;
                    }
                    if (r - line >= 0 && c - line >= 0 && field[r - line][c - line] == turn) {
                        downL++;
                    }
                    if (c - line >= 0 && field[r][c - line] == turn) {
                        downM++;
                    }
                    if (r + line < n && c - line >= 0 && field[r + line][c - line] == turn) {
                        downR++;
                    }
                }

                if (upL == k | upM == k | upR == k
                        | midL == k | midR == k
                        | downL == k | downM == k | downR == k) {
                    return GameResult.WIN;
                }
            }
        }

        if (emptyCells == 0) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" ");
        for (int c = 1; c <= m; c++) {
            sb.append(c);
        }
        sb.append(System.lineSeparator());

        for (int r = 1; r <= n; r++) {
            sb.append(r);
            for (Cell cell : field[r - 1]) {
                sb.append(CELL_TO_STRING.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
