package edu.io;

import edu.io.token.PlayerToken;

public class Game {
    private Board board;
    
    public Game() {
        this.board = new Board();
    }
    
    public void join(Player player) {
        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);
    }
    
    public void start() {
        System.out.println("Gra rozpoczęta!");
    }
}