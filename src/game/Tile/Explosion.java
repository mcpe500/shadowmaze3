package src.game.Tile;

import processing.core.PApplet;
import src.util.AssetLoader;

public class Explosion extends Tile {

    private int explodeTime;

    public Explosion(PApplet parent, float width, float height, int x, int y) {
        super(parent, width, height, new AssetLoader(parent).getExplosion(), x, y);
        this.explodeTime = 20;
    }

    public int getExplodeTime() {
        return this.explodeTime;
    }

    public void decExplodeTime() {
        this.explodeTime--;
    }
    
}
