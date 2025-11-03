package edu.io;

public class Label {
    public static final Label EMPTY_TOKEN_LABEL = new Label("・");
    public static final Label GOLD_TOKEN_LABEL = new Label("💰");
    public static final Label PLAYER_TOKEN_LABEL = new Label("웃");
    public static final Label PICKAXE_TOKEN_LABEL = new Label("T"); // Kilof
    public static final Label ANVIL_TOKEN_LABEL = new Label("K"); // Kowadło

    private final String value;

    public Label(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Label l && l.value.equals(this.value);
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