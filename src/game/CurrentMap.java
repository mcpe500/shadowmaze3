package src.game;

import java.util.ArrayList;

public class CurrentMap {
    private String[][] strMap;
    private int[][] maps;

    public CurrentMap(String[][] strMap) {
        this.strMap = strMap;
        maps = convertMapToInt(this.strMap);
    }

    public CurrentMap(int[][] strMap) {
        this.maps = strMap;
        this.strMap = convertMapToStr(this.maps);
    }

    public int[][] convertMapToInt(String[][] s) {
        int[][] map1 = new int[s.length][s[0].length];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[0].length; j++) {
                map1[i][j] = Integer.parseInt(s[i][j]);
            }
        }
        return map1;
    }

    public String[][] convertMapToStr(int[][] s) {
        String[][] map1 = new String[s.length][s[0].length];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[0].length; j++) {
                map1[i][j] = String.valueOf(s[i][j]);
            }
        }
        return map1;
    }

    public void updateMap(ArrayList<Karakter> karakters, int mapX, int mapY, int size, String[][] s) {
        int[][] newMap = convertMapToInt(s);
        // for (int i = 0; i < newMap.length; i++) {
        // for (int j = 0; j < newMap[i].length; j++) {
        // newMap[i][j] = maps[i][j];
        // }
        // }

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++) {
                maps[i][j] = newMap[i][j];
                for (int j2 = 0; j2 < karakters.size(); j2++) {
                    int[][] mapPos = new int[4][2];
                    mapPos[0][0] = (int) ((karakters.get(j2).getX() - mapX) * 1.0 / size);
                    mapPos[0][1] = (int) ((karakters.get(j2).getY() - mapY) * 1.0 / size);
                    mapPos[1][0] = (int) ((karakters.get(j2).getX() + karakters.get(j2).getWidth() - mapX - 1) * 1.0
                            / size);
                    mapPos[1][1] = (int) ((karakters.get(j2).getY() - mapY) * 1.0 / size);
                    mapPos[2][0] = (int) ((karakters.get(j2).getX() - mapX) * 1.0 / size);
                    mapPos[2][1] = (int) ((karakters.get(j2).getY() + karakters.get(j2).getHeight() - mapY - 1) * 1.0
                            / size);
                    mapPos[3][0] = (int) ((karakters.get(j2).getX() + karakters.get(j2).getWidth() - mapX - 1) * 1.0
                            / size);
                    mapPos[3][1] = (int) ((karakters.get(j2).getY() + karakters.get(j2).getHeight() - mapY - 1) * 1.0
                            / size);
                    karakters.get(j2).setMapPos(mapPos);

                    if (karakters.get(j2).getX() + karakters.get(j2).getWidth() / 2 >= j * size + mapX
                            && karakters.get(j2).getX() + karakters.get(j2).getWidth() / 2 < (j + 1) * size + mapX &&
                            karakters.get(j2).getY() + karakters.get(j2).getHeight() / 2 >= i * size + mapY
                            && karakters.get(j2).getY() + karakters.get(j2).getHeight() / 2 < (i + 1) * size + mapY) {
                        maps[i][j] = karakters.get(j2).getId();
                        karakters.get(j2).setMapPosX(j);
                        karakters.get(j2).setMapPosY(i);
                        // System.out.println(
                        // "X: " + karakters.get(j2).getX() + " Y: " + karakters.get(j2).getY() + "
                        // MapX: "
                        // + karakters.get(j2).getMapPosX() + " MapY: " + karakters.get(j2).getMapPosY()
                        // +
                        // " MapTile: " +
                        // maps[karakters.get(j2).mapPosY-1][karakters.get(j2).mapPosX-1]);
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
