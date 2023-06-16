package src.game.Tile;

import processing.core.PApplet;
import src.util.AssetLoader;

public class Stonefloor extends Tile {

    public Stonefloor(PApplet parent, float width, float height, int x, int y) {
        super(parent, width, height, new AssetLoader(parent).getStonefloor(), x, y);
    }
}
