package src.game;

import processing.core.PApplet;

public abstract class Level {
    private PApplet parent;

    public Level(PApplet parent) {
        this.parent = parent;
    }

    public PApplet getParent() {
        return parent;
    }

    public abstract void setup();

    public abstract void draw();
}