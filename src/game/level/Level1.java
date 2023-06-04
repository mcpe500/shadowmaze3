package src.game.level;

import src.game.Tile.Tile;
import src.util.MapLoader;
import processing.core.PApplet;

public class Level1 extends Level {
    private Tile[][] map;

    public Level1(PApplet parent) {
        super(parent);
    }

    public void setup() {
        String[][] strMap = MapLoader.loadMap(parent, "../assets/maps/map1.txt");
        map = MapLoader.tileMap(parent, strMap, 32, 10, 10);
    }

    public void draw() {
        text("Level 1 ", 100, 100, 100, 100);
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                if (map[i][j] != null) {
                    map[i][j].display();
                }
            }
        }
    }

    @Override
    public void settings() {
        parent.size(1280, 720);
    }
}
