package edu.io.player;

public class Gold {
    private double amount;

    public Gold() {
        this(0.0);
    }

    public Gold(double amount) {
        if (amount < 0) throw new IllegalArgumentException();
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public void gain(double value) {
        if (value < 0) throw new IllegalArgumentException();
        amount += value;
    }

    public void lose(double value) {
        if (value < 0 || value > amount) throw new IllegalArgumentException();
        amount -= value;
    }
}