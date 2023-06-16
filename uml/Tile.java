package src.game.Tile;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Tile {
    protected PApplet parent;
    protected float width;
    protected float height;
    protected PImage image;
    protected int x, y;

    public Tile(PApplet parent, float width, float height, PImage image, int x, int y) {
        this.parent = parent;
        this.width = width;
        this.height = height;
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void draw(PApplet parent) {
        parent.image(image, x, y, width, height);
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public float getSize() {
        return this.width;
    }
}
