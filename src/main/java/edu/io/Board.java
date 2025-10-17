package edu.io;

public class Board {
    private final int size;
    private final Token[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        initializeBoard();
    }
    
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Token("\u30FB");
            }
        }
    }

    public void clean() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Token("\u30FB");
            }
        }
    }

    public void placeToken(int col, int row, Token token) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            grid[row][col] = token;
        }
    }
    
    public Token square(int col, int row) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            return grid[row][col];
        }
        return null;
    }
    
    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].getLabel() + " ");
            }
            System.out.println();
        }
    }
}
