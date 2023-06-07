package src.util;

import src.game.Tile.*;
import processing.core.PApplet;

public class MapLoader {

    public static String[][] loadMap(PApplet parent, String filename) {
        String[] map1d = parent.loadStrings(filename);
        int row = map1d.length;
        int col = map1d[0].length();
        String[][] map = new String[row][col];
        for (int i = 0; i < map1d.length; i++) {
            String[] map1d2 = map1d[i].split(" ");
            map[i] = map1d2;
        }
        return map;
    }

    public static Tile[][] tileMap(PApplet parent, String[][] arrMap, int bSize, int bX, int bY) {
        Tile[][] tileMap = new Tile[arrMap.length][arrMap[0].length];
        int savebX = bX;
        for (int i = 0; i < arrMap.length; i++) {
            for (int j = 0; j < arrMap[0].length; j++) {
                switch (arrMap[i][j]) {
                    case "0":
                        tileMap[i][j] = new Stonefloor(parent, bSize, bSize, bX, bY);
                        break;
                    case "1":
                        tileMap[i][j] = new Wall(parent, bSize, bSize, bX, bY);
                        break;
                    default:
                        break;
                }
                bX += bSize;
            }
            bX = savebX;
            bY += bSize;
        }
        return tileMap;
    }
}
