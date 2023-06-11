package src.util;

import processing.core.PImage;
import processing.core.PApplet;

public class AssetLoader {
    private PApplet parent;
    private PImage wall;
    private PImage stonefloor;
    private PImage beartrap;
    private PImage beartrapBlood;
    private PImage lava;

    public AssetLoader(PApplet pApplet) {
        parent = pApplet;
        wall = parent.loadImage("../assets/sprites/Wall.png");
        stonefloor = parent.loadImage("../assets/sprites/stonefloor.png");
        beartrap = parent.loadImage("../assets/sprites/beartrap.png");
        beartrapBlood = parent.loadImage("../assets/sprites/beartrap_blood.png");
        lava = parent.loadImage("../assets/sprites/lava.png");
    }

    public PImage getStonefloor() {
        return stonefloor;
    }

    public PImage getWall() {
        return wall;
    }

    public PImage getBeartrap() {
        return beartrap;
    }

    public PImage getBeartrapBlood() {
        return beartrapBlood;
    }

    public PImage getLava() {
        return lava;
    }
}
