package edu.io.token;

import edu.io.Label;

public class EmptyToken extends Token {
    @Override
    public Label label() {
        return Label.EMPTY_TOKEN_LABEL;
    }
}