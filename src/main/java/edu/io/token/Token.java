package edu.io.token;

public abstract class Token {
    public final String label;
    
    public Token(String symbol) {
        this.label = symbol;
    }
    
    public String getSymbol() {
        return label;
    }
    
    public String display() {
        return label;
    }
    
    public String label() {
        return label;
    }
    
    public static Token createEmpty() {
        return new EmptyToken();
    }
    
    public static Token createGold() {
        return new GoldToken();
    }
    
    public static Token createPlayer() {
        return new PlayerToken();
    }
    
    public boolean isEmpty() {
        return "・".equals(label);
    }
    
    public boolean isGold() {
        return "💰".equals(label);
    }
    
    public boolean isPlayer() {
        return "웃".equals(label);
    }
}