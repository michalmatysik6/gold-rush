package edu.io;

import edu.io.token.Token;

public class Board {
    public record Coords(int col, int row) {}
    
    private final int size = 8;
    private final Token[][] grid;
    private PlacementStrategy placementStrategy;
    
    public Board() {
        this.grid = new Token[size][size];
        this.placementStrategy = new SimplePlacement();
        clear();
    }
    
    public void setPlacementStrategy(PlacementStrategy strategy) {
        this.placementStrategy = strategy;
    }
    
    public void clear() {
        Token empty = Token.createEmpty();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[col][row] = empty;
            }
        }
    }
    
    public void clean() {
        clear();
    }
    
    public void placeToken(int col, int row, Token token) {
        if (!isValidPos(col, row)) {
            throw new IllegalArgumentException("Niedozwolona pozycja: " + col + ", " + row);
        }
        grid[col][row] = token;
    }
    
    public Token peekToken(int col, int row) {
        if (!isValidPos(col, row)) {
            throw new IllegalArgumentException("Niedozwolona pozycja: " + col + ", " + row);
        }
        return grid[col][row];
    }
    
    public Token getSquare(int col, int row) {
        return peekToken(col, row);
    }
    
    public Token square(int col, int row) {
        return peekToken(col, row);
    }
    
    public boolean isValidPos(int col, int row) {
        return col >= 0 && col < size && row >= 0 && row < size;
    }
    
    public Board.Coords getAvailableSquare() {
        return placementStrategy.findEmptySpot(this);
    }
    
    public void display() {
        System.out.println("Plansza 'Gold Rush' 8x8:");
        System.out.println("____________________________");
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(grid[col][row].display() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public int size() {
        return size;
    }
    
    public int getSize() {
        return size;
    }
    
    public int[] findPlayer() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[col][row].isPlayer()) {
                    return new int[]{col, row};
                }
            }
        }
        return new int[]{-1, -1};
    }
}