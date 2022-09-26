package com.regicide.board;

public class BoardSettings {
    public final int WIDTH;
    public final int HEIGHT;

    public BoardSettings(String path) {
        // TODO: load from file
        WIDTH = 12;
        HEIGHT = 12;
    }
}
