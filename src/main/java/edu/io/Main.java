package edu.io;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gold Rush");
        
        Board board = new Board(8);
        
        Token gold = new Token("G");
        Token player = new Token("P");
        
        board.placeToken(2, 3, gold);
        board.placeToken(5, 5, player);
    }
}
