package edu.io.token;

import edu.io.player.Repairable;
import edu.io.player.Tool;

public class PickaxeToken extends Token implements Tool, Repairable {
    private final double gainFactor;
    private final int maxDurability;
    private int durability;
    private boolean shouldWork = false;
    private boolean shouldBreak = false;
    private boolean shouldIdle = false;

    public PickaxeToken() {
        this(1.5, 3);
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }

    public PickaxeToken(double gainFactor, int durability) {
        if (gainFactor <= 0 || durability <= 0) throw new IllegalArgumentException();
        this.gainFactor = gainFactor;
        this.maxDurability = durability;
        this.durability = durability;
    }

    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }

    public void use() {
        if (!isBroken()) durability--;
    }

    @Override
    public boolean isBroken() {
        return durability == 0;
    }

    @Override
    public Tool useWith(edu.io.token.Token token) {
        shouldWork = false;
        shouldBreak = false;
        shouldIdle = false;
        
        if (isBroken()) {
            shouldBreak = true;
        } else if (token instanceof GoldToken) {
            use();
            shouldWork = true;
        } else {
            shouldIdle = true;
        }
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) {
        if (shouldWork) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action) {
        if (shouldBreak) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) {
        if (shouldIdle) {
            action.run();
        }
        return this;
    }

    @Override
    public void repair() {
        durability = maxDurability;
    }

    @Override
    public Label label() {
        return Label.PICKAXE_TOKEN_LABEL;
    }
}