package src.game.level;

import processing.core.PApplet;
import src.game.CurrentMap;

public abstract class Level extends PApplet {
    protected PApplet parent;
    protected CurrentMap currentMap;

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