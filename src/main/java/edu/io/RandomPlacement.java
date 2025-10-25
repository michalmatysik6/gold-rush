package edu.io;

import edu.io.token.EmptyToken;
import java.util.ArrayList;
import java.util.Random;

public class RandomPlacement implements PlacementStrategy {
    private Random random = new Random();
    
    public Board.Coords findEmptySpot(Board board) {
        ArrayList<Board.Coords> emptySpots = new ArrayList<>();
        
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.size(); col++) {
                if (board.peekToken(col, row) instanceof EmptyToken) {
                    emptySpots.add(new Board.Coords(col, row));
                }
            }
        }
        
        if (emptySpots.isEmpty()) {
            throw new IllegalStateException("Brak wolnych pól na planszy");
        }
        
        return emptySpots.get(random.nextInt(emptySpots.size()));
    }
}