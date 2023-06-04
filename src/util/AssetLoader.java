package src.util;

import processing.core.PImage;
import processing.core.PApplet;

public class AssetLoader {
    private PApplet parent;
    private PImage wall;
    private PImage stonefloor;

    public AssetLoader(PApplet pApplet) {
        parent = pApplet;
        wall = parent.loadImage("../assets/sprites/Wall.png");
        stonefloor = parent.loadImage("../assets/sprites/stonefloor.png");
    }

    public PImage getStonefloor() {
        return stonefloor;
    }

    public PImage getWall() {
        return wall;
    }
}
