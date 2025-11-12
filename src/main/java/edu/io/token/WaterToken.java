package edu.io.token;

public class WaterToken extends Token {
    private final int amount;

    public WaterToken() {
        this(10);
    }

    public WaterToken(int amount) {
        if (amount < 0 || amount > 100) {
            throw new IllegalArgumentException("Ilość wody musi być w zakresie od 0 do 100");
        }
        this.amount = amount;
    }

    public int amount() {
        return amount;
    }

    @Override
    public Label label() {
        return Label.WATER_TOKEN_LABEL;
    }
}