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

    public void updateMap(ArrayList<Karakter> karakters, int mapX, int mapY, int size) {
        int[][] newMap = new int[maps.length][maps[0].length];
        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[i].length; j++) {
                newMap[i][j] = maps[i][j];
            }
        }
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++) {
                maps[i][j] = newMap[i][j];
                for (int j2 = 0; j2 < karakters.size(); j2++) {
                    if (karakters.get(j2).getX() >= j * size + mapX && karakters.get(j2).getX() < (j + 1) * size + mapX
                            &&
                            karakters.get(j2).getY() >= i * size + mapY
                            && karakters.get(j2).getY() < (i + 1) * size + mapY) {
                        maps[i][j] = karakters.get(j2).getId();
                        karakters.get(i).setMapPosX(j);
                        karakters.get(i).setMapPosY(i);
                        System.out.println("X: " + karakters.get(i).getX() + " Y: " + karakters.get(i).getY() + " MapX: " + karakters.get(i).getMapPosX() + " MapY: " + karakters.get(i).getMapPosY());
                    }

                }
            }
        }
    }

    public int[][] getMaps() {
        return maps;
    }

}
