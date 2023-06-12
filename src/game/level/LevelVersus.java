package src.game.level;

import processing.core.PApplet;
import src.game.Tile.Tile;
import src.util.MapLoader;

public class LevelVersus extends Level {
    private int[][] map;
    private Tile[][] tileMap;
    private int width, height;

    public LevelVersus(PApplet parent) {
        super(parent);
        width = 1280;
        height = 720;
    }

    @Override
    public void settings() {
        size(width, height);
    }

    @Override
    public void setup() {
        int sizeX = 30;
        int sizeY = 30;
        map = new int[sizeY][sizeX];
        map = randomMap(map);
        tileMap = new Tile[sizeY][sizeX];
        tileMap = MapLoader.tileMap(parent, map, 32, 100, 100);
    }

    public int[][] randomMap(int[][] intMap) {
        int sizeX = intMap[0].length;
        int sizeY = intMap.length;
        int[][] newMap = new int[sizeY][sizeX];
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                newMap[i][j] = 0;
            }
        }
        return newMap;
    }

    @Override
    public void draw() {
        background(0);
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[0].length; j++) {
                tileMap[i][j].draw(this);
            }
        }
    }

}
