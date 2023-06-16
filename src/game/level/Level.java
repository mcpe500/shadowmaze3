package src.game.level;

import java.util.Random;

import processing.core.PApplet;
import src.game.CurrentMap;
import src.game.Tile.Portal;

public abstract class Level extends PApplet {
    protected PApplet parent;
    protected CurrentMap currentMap;
    protected Random random;
    protected Portal[] portals;

    public Level(PApplet parent) {
        this.parent = parent;
        random = new Random();
        portals = new Portal[2];
    }

    public abstract void setup();

    public abstract void draw();

    @Override
    public void settings() {
        parent.size(1280, 720);
    }

}