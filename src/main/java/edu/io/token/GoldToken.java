package edu.io.token;

public class GoldToken extends Token {
    private final double amount;
    
    public GoldToken() {
        this(1.0);
    }
    
    public GoldToken(double amount) {
        super("💰");
        if (amount < 0) {
            throw new IllegalArgumentException("Ilość złota nie może być ujemna");
        }
        this.amount = amount;
    }
    
    public double amount() {
        return amount;
    }
}