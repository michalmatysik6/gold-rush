package edu.io.token;

import edu.io.Board;
import edu.io.Player;

public class PlayerToken extends Token {
    private Board board;
    private int col;
    private int row;
    
    public enum Move {
        NONE, UP, DOWN, LEFT, RIGHT
    }
    
    public PlayerToken() {
        super("웃");
    }
    
    public PlayerToken(Player player, Board board) {
        super("웃");
        this.board = board;
        this.col = 0;
        this.row = 0;
        board.placeToken(col, row, this);
    }
    
    public PlayerToken(Board board) {
        super("웃");
        this.board = board;
        this.col = 0;
        this.row = 0;
        board.placeToken(col, row, this);
    }
    
    public PlayerToken(Board board, int startCol, int startRow) {
        super("웃");
        this.board = board;
        this.col = startCol;
        this.row = startRow;
        board.placeToken(col, row, this);
    }
    
    public void move(Move direction) {
        int newCol = col;
        int newRow = row;
        
        switch (direction) {
            case UP: newRow--; break;
            case DOWN: newRow++; break;
            case LEFT: newCol--; break;
            case RIGHT: newCol++; break;
            case NONE: return;
        }
        
        if (!board.isValidPos(newCol, newRow)) {
            throw new IllegalArgumentException("Nie można wyjść poza planszę");
        }
        
        board.placeToken(col, row, new EmptyToken());
        col = newCol;
        row = newRow;
        board.placeToken(col, row, this);
    }
    
    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }
}