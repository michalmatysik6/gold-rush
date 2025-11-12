package edu.io.token;

import edu.io.Board;
import edu.io.player.Player;

public final class PlayerToken extends Token {
    private Board.Coords pos;
    private final Board board;
    private final Player player;
    private boolean initialized = false;

    public PlayerToken(Player player, Board board) {
        this(player, board, board.getAvailableSquare());
    }

    public PlayerToken(Player player, Board board, Board.Coords pos) {
        if (player == null || board == null || pos == null) {
            throw new NullPointerException("Gracz i plansza są wymagane do utworzenia żetonu");
        }
        this.player = player;
        this.board = board;
        this.pos = pos;
        initializeOnBoard();
    }

    public void initializeOnBoard() {
        if (!initialized) {
            board.placeToken(pos.col(), pos.row(), this);
            initialized = true;
        }
    }

    public Board.Coords pos() {
        return pos;
    }

    public enum Move { LEFT, RIGHT, UP, DOWN, NONE }

    public void move(Move dir) {
        if (dir == null) {
            throw new NullPointerException("Wybierz kierunek ruchu");
        }
        
        int col = pos.col();
        int row = pos.row();
        
        switch (dir) {
            case LEFT -> col--;
            case RIGHT -> col++;
            case UP -> row--;
            case DOWN -> row++;
            case NONE -> {}
        }
        
        if (!board.isValidPos(col, row)) {
            throw new IllegalArgumentException("Nie można wyjść poza planszę");
        }
        
        Token encounteredToken = board.peekToken(col, row);
        player.interactWithToken(encounteredToken);
        
        board.placeToken(pos.col(), pos.row(), new EmptyToken());
        pos = new Board.Coords(col, row);
        board.placeToken(col, row, this);
    }

    @Override
    public Label label() {
        return Label.PLAYER_TOKEN_LABEL;
    }
}