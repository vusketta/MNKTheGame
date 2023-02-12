package game;

public interface Position {
    Cell getCell(int row, int column);
    Cell getTurn();
    int getM(Board board);
    int getN(Board board);
    boolean isValid(Move move);
}
