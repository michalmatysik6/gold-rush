package edu.io;

import edu.io.token.EmptyToken;

public class SimplePlacement implements PlacementStrategy {
    public Board.Coords findEmptySpot(Board board) {
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.size(); col++) {
                if (board.peekToken(col, row) instanceof EmptyToken) {
                    return new Board.Coords(col, row);
                }
            }
        }
        throw new IllegalStateException("Plansza jest pełna");
    }
}