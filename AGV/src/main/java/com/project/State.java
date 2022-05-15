package com.project;

public enum State {
    STATE_ONE("1"),
    STATE_TWO("2"),
    STATE_THREE("3")
    ;

    private final String text;

    State(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
