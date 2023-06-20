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
    private PImage exit;
    private PImage portal1;
    private PImage portal2;
    private PImage trapdoor;
    private PImage holyGrenade;
    private PImage explosion;

    public AssetLoader(PApplet pApplet) {
        parent = pApplet;
        wall = parent.loadImage("../assets/sprites/Wall.png");
        stonefloor = parent.loadImage("../assets/sprites/stonefloor.png");
        beartrap = parent.loadImage("../assets/sprites/beartrap.png");
        beartrapBlood = parent.loadImage("../assets/sprites/beartrap_blood.png");
        lava = parent.loadImage("../assets/sprites/lava.png");
        exit = parent.loadImage("../assets/sprites/hole_exit.png");
        portal1 = parent.loadImage("../assets/sprites/portal1.png");
        portal2 = parent.loadImage("../assets/sprites/portal2.png");
        trapdoor = parent.loadImage("../assets/sprites/trapdoor.png");
        holyGrenade = parent.loadImage("../assets/sprites/Holy_grenade.png");
        explosion = parent.loadImage("../assets/sprites/explosion.png");
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

    public PImage getExit() {
        return exit;
    }

    public PImage getPortal1() {
        return portal1;
    }

    public PImage getPortal2() {
        return portal2;
    }

    public PImage getTrapdoor() {
        return trapdoor;
    }

    public PImage getHolyGrenade() {
        return holyGrenade;
    }

    public PImage getExplosion() {
        return explosion;
    }
}
