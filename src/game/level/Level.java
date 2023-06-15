package src.game.level;

import java.util.Random;

import processing.core.PApplet;
import src.game.CurrentMap;

// import processing.sound.*;

public abstract class Level extends PApplet {
    protected PApplet parent;
    protected CurrentMap currentMap;
    protected Random random;
    // protected SoundFile song;
    // protected Amplitude amplitudo;

    public Level(PApplet parent) {
        this.parent = parent;
        random = new Random();
    }

    public abstract void setup();

    public abstract void draw();

    @Override
    public void settings() {
        parent.size(1280, 720);
    }
}