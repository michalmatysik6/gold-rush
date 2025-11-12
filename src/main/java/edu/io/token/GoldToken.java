package edu.io.token;

public class GoldToken extends Token {
    private final double amount;

    public GoldToken() {
        this(1.0);
    }

    public GoldToken(double amount) {
        if (amount < 0) throw new IllegalArgumentException();
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    @Override
    public Label label() {
        return Label.GOLD_TOKEN_LABEL;
    }
}