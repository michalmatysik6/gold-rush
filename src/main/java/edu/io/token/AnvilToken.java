package edu.io.token;

import edu.io.Label;

public class AnvilToken extends Token {
    public AnvilToken() {
        super();
    }

    @Override
    public Label label() {
        return Label.ANVIL_TOKEN_LABEL;
    }

    public void interact(edu.io.player.Player player) {
        edu.io.player.Tool tool = player.shed().getTool();
        if (tool instanceof edu.io.player.Repairable repairableTool) {
            repairableTool.repair();
        }
    }
}