package edu.io;

public class Board {
    private int size;
    private Token[][] grid;
    
    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }
    
    public void clean() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = null;
            }
        }
    }
    
    public void placeToken(int col, int row, Token token) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            grid[col][row] = token;
        }
    }
    
    public Token square(int col, int row) {
        if (col >= 0 && col < size && row >= 0 && row < size) {
            return grid[col][row];
        }
        return null;
    }
    
    public void display() {
    }
}
