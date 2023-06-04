package src.util;

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
}
