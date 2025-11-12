package edu.io.token;

public class EmptyToken extends Token {
    @Override
    public Label label() {
        return Label.EMPTY_TOKEN_LABEL;
    }
}