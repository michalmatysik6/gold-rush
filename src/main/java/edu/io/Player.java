package edu.io;

import edu.io.token.PlayerToken;

public class Player {
    private PlayerToken token;
    private double gold;
    
    public Player() {
        this.gold = 0.0;
    }
    
    public void assignToken(PlayerToken token) {
        this.token = token;
    }
    
    public PlayerToken token() {
        return token;
    }
    
    public double gold() {
        return gold;
    }
    
    public void interactWithToken(edu.io.token.GoldToken goldToken) {
        gainGold(goldToken.amount());
    }
    
    public void gainGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Ilość złota nie może być ujemna");
        }
        this.gold += amount;
    }
    
    public void loseGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Ilość złota nie może być ujemna");
        }
        if (this.gold - amount < 0) {
            throw new IllegalArgumentException("Złoto nie może spaść poniżej zera");
        }
        this.gold -= amount;
    }
}