package edu.io.player;

import edu.io.token.AnvilToken;
import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.PyriteToken;
import edu.io.token.Token;
import edu.io.token.WaterToken;

public class Player {
    private PlayerToken token;
    public final Gold gold = new Gold();
    public final Vitals vitals = new Vitals();
    private final Shed shed = new Shed();

    public void assignToken(PlayerToken token) {
        if (token == null) {
            throw new NullPointerException("Token nie może być pusty");
        }
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public Shed shed() {
        return shed;
    }

    public void interactWithToken(Token token) {
        if (token == null) {
            throw new NullPointerException("Nie można wykonać akcji z pustym żetonem");
        }
        if (!vitals.isAlive()) {
            throw new IllegalStateException("Gracz nie żyje");
        }

        if (token instanceof PickaxeToken pickaxe) {
            shed.add(pickaxe);
            // Podnoszenie kilofa NIE zużywa wody
        } else if (token instanceof PyriteToken pyrite) {
            handlePyriteInteraction(pyrite);
            vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
        } else if (token instanceof GoldToken goldToken) {
            handleGoldInteraction(goldToken);
            vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
        } else if (token instanceof AnvilToken anvil) {
            anvil.interact(this);
            vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
        } else if (token instanceof WaterToken waterToken) {
            vitals.hydrate(waterToken.amount());
        } else {
            vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
        }
    }

    private void handleGoldInteraction(GoldToken goldToken) {
        Tool tool = shed.getTool();
        tool.useWith(goldToken)
            .ifWorking(() -> {
                if (tool instanceof PickaxeToken pickaxe) {
                    gold.gain(goldToken.amount() * pickaxe.gainFactor());
                }
            })
            .ifBroken(() -> gold.gain(goldToken.amount()))
            .ifIdle(() -> gold.gain(goldToken.amount()));
    }

    private void handlePyriteInteraction(PyriteToken pyrite) {
        gold.gain(pyrite.amount());
    }
}