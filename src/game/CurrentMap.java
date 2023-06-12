package src.game;

import java.util.ArrayList;

public class CurrentMap {
    private String[][] strMap;
    private int[][] maps;

    public CurrentMap(String[][] strMap) {
        this.strMap = strMap;
        maps = convertMap(this.strMap);
    }

    public int[][] convertMap(String[][] strMap) {
        int[][] map1 = new int[strMap.length][strMap[0].length];
        for (int i = 0; i < strMap.length; i++) {
            for (int j = 0; j < strMap[0].length; j++) {
                map1[i][j] = Integer.parseInt(strMap[i][j]);
            }
        }
        return map1;
    }

    public void updateMap(ArrayList<Karakter> karakters, int mapX, int mapY, int size, String[][] strMaps) {
        int[][] newMap = convertMap(strMaps);
        // for (int i = 0; i < newMap.length; i++) {
        // for (int j = 0; j < newMap[i].length; j++) {
        // newMap[i][j] = maps[i][j];
        // }
        // }

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++) {
                maps[i][j] = newMap[i][j];
                for (int j2 = 0; j2 < karakters.size(); j2++) {
                    if (karakters.get(j2).getX() >= j * size + mapX && karakters.get(j2).getX() < (j + 1) * size + mapX
                            &&
                            karakters.get(j2).getY() >= i * size + mapY
                            && karakters.get(j2).getY() < (i + 1) * size + mapY) {
                        maps[i][j] = karakters.get(j2).getId();
                        karakters.get(j2).setMapPosX(j);
                        karakters.get(j2).setMapPosY(i);
                        System.out.println(
                                "X: " + karakters.get(j2).getX() + " Y: " + karakters.get(j2).getY() + " MapX: "
                                        + karakters.get(j2).getMapPosX() + " MapY: " + karakters.get(j2).getMapPosY());
                    }
                }
            }
        }
    }

    public int[][] getMaps() {
        // for (int i = 0; i < maps.length; i++) {
        // for (int j = 0; j < maps[i].length; j++) {
        // System.out.print(maps[i][j] + " ");
        // }
        // System.out.println();
        // }
        return maps;
    }

}
