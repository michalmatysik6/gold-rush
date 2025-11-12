package edu.io.token;

public class Label {
    public static final Label EMPTY_TOKEN_LABEL = new Label("・");
    public static final Label GOLD_TOKEN_LABEL = new Label("💰");
    public static final Label PLAYER_TOKEN_LABEL = new Label("웃");
    public static final Label PICKAXE_TOKEN_LABEL = new Label("⛏️");
    public static final Label ANVIL_TOKEN_LABEL = new Label("π");
    public static final Label WATER_TOKEN_LABEL = new Label("💧");

    private final String value;

    public Label(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Label)) return false;
        Label label = (Label) o;
        return value.equals(label.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}