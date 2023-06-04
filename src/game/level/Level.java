package src.game.level;

import processing.core.PApplet;

public abstract class Level extends PApplet {
    protected PApplet parent;

    public Level(PApplet parent) {
        this.parent = parent;
    }

    public abstract void setup();

    public abstract void draw();

    @Override
    public void settings() {
        parent.size(1280, 720);
    }
}