package edu.io;

import java.util.Random;

import edu.io.token.EmptyToken;

public class RandomPlacement implements PlacementStrategy {
    private final Random random = new Random();

    @Override
    public Board.Coords place(Board board) {
        int size = board.size();
        for (int i = 0; i < size * size; i++) {
            int col = random.nextInt(size);
            int row = random.nextInt(size);
            if (board.peekToken(col, row) instanceof EmptyToken) {
                return new Board.Coords(col, row);
            }
        }
        throw new IllegalStateException("Plansza jest pełna");
    }
}