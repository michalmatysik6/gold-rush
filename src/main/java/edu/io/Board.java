package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {
    private static final int SIZE = 8;
    private final Token[][] grid = new Token[SIZE][SIZE];
    private PlacementStrategy placementStrategy = new SimplePlacement();

    public Board() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = new EmptyToken();
            }
        }
    }

    public void setPlacementStrategy(PlacementStrategy strategy) {
        this.placementStrategy = strategy;
    }

    public boolean isValidPos(int col, int row) {
        return col >= 0 && col < SIZE && row >= 0 && row < SIZE;
    }

    public void clean() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = new EmptyToken();
            }
        }
    }

    public void placeToken(int col, int row, Token token) {
        if (!isValidPos(col, row)) {
            throw new IllegalArgumentException("Nieprawidłowa pozycja: " + col + ", " + row);
        }
        if (token == null) {
            throw new NullPointerException("Żeton nie może być pusty");
        }
        grid[row][col] = token;
    }

    public Token peekToken(int col, int row) {
        if (!isValidPos(col, row)) {
            throw new IllegalArgumentException("Nieprawidłowa pozycja: " + col + ", " + row);
        }
        return grid[row][col];
    }

    public int size() {
        return SIZE;
    }

    public Coords getAvailableSquare() {
        return placementStrategy.place(this);
    }

    public void display() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(grid[row][col].label() + " ");
            }
            System.out.println();
        }
    }

    public record Coords(int col, int row) {}
}