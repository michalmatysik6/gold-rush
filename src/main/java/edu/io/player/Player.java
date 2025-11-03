package edu.io.player;

import edu.io.token.AnvilToken;
import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.PyriteToken;
import edu.io.token.Token;

public class Player {
    private PlayerToken token;
    public final Gold gold = new Gold();
    private final Shed shed = new Shed();

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public Shed shed() {
        return shed;
    }

    public void interactWithToken(Token token) {
        switch (token) {
            case PickaxeToken pickaxe -> shed.add(pickaxe);
            case PyriteToken pyrite -> handlePyriteInteraction(pyrite);
            case GoldToken goldToken -> handleGoldInteraction(goldToken);
            case AnvilToken anvil -> anvil.interact(this);
            default -> {} // Ignoruj inne tokeny
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