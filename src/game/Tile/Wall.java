package src.game.Tile;

import processing.core.PApplet;
import src.util.AssetLoader;

public class Wall extends Tile {
    public Wall(PApplet parent, float width, float height, int x, int y) {
        super(parent, width, height, new AssetLoader(parent).getWall(), x, y);
    }

}
