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
                    int[][] mapPos = new int[4][2];
                    mapPos[0][0] = (int)((karakters.get(j2).getX()-mapX)*1.0/size);
                    mapPos[0][1] = (int)((karakters.get(j2).getY()-mapY)*1.0/size);
                    mapPos[1][0] = (int)((karakters.get(j2).getX()+karakters.get(j2).getWidth()-mapX-1)*1.0/size);
                    mapPos[1][1] = (int)((karakters.get(j2).getY()-mapY)*1.0/size);
                    mapPos[2][0] = (int)((karakters.get(j2).getX()-mapX)*1.0/size);
                    mapPos[2][1] = (int)((karakters.get(j2).getY()+karakters.get(j2).getHeight()-mapY-1)*1.0/size);
                    mapPos[3][0] = (int)((karakters.get(j2).getX()+karakters.get(j2).getWidth()-mapX-1)*1.0/size);
                    mapPos[3][1] = (int)((karakters.get(j2).getY()+karakters.get(j2).getHeight()-mapY-1)*1.0/size);
                    karakters.get(j2).setMapPos(mapPos);

                    // int[][][] nextMove = new int[4][2][2];
                    // // up
                    // nextMove[0][0][0] = (karakters.get(j2).getX()-mapX)/size;
                    // nextMove[0][0][1] = (karakters.get(j2).getY()-mapY-karakters.get(j2).getMoveSpeed())/size;
                    // nextMove[0][1][0] = (karakters.get(j2).getX()+karakters.get(j2).getWidth()-mapX-1)/size;
                    // nextMove[0][1][1] = (karakters.get(j2).getY()-mapY-karakters.get(j2).getMoveSpeed())/size;
                    // // down
                    // nextMove[1][0][0] = (karakters.get(j2).getX()-mapX)/size;
                    // nextMove[1][0][1] = (karakters.get(j2).getY()+karakters.get(j2).getHeight()-1-mapY+karakters.get(j2).getMoveSpeed())/size;
                    // nextMove[1][1][0] = (karakters.get(j2).getX()+karakters.get(j2).getWidth()-mapX-1)/size;
                    // nextMove[1][1][1] = (karakters.get(j2).getY()+karakters.get(j2).getHeight()-1-mapY+karakters.get(j2).getMoveSpeed())/size;
                    // // left
                    // nextMove[2][0][0] = (karakters.get(j2).getX()-mapX-karakters.get(j2).getMoveSpeed())/size;
                    // nextMove[2][0][1] = (karakters.get(j2).getY()-mapY)/size;
                    // nextMove[2][1][0] = (karakters.get(j2).getX()-mapX-karakters.get(j2).getMoveSpeed())/size;
                    // nextMove[2][1][1] = (karakters.get(j2).getY()+karakters.get(j2).getHeight()-mapY-1)/size;
                    // // right
                    // nextMove[3][0][0] = (karakters.get(j2).getX()+karakters.get(j2).getWidth()-mapX+karakters.get(j2).getMoveSpeed()-1)/size;
                    // nextMove[3][0][1] = (karakters.get(j2).getY()-mapY)/size;
                    // nextMove[3][1][0] = (karakters.get(j2).getX()+karakters.get(j2).getWidth()-mapX+karakters.get(j2).getMoveSpeed()-1)/size;
                    // nextMove[3][1][1] = (karakters.get(j2).getY()+karakters.get(j2).getHeight()-mapY-1)/size;
                    // karakters.get(j2).setNextMove(nextMove);


                    if (karakters.get(j2).getX()+karakters.get(j2).getWidth()/2 >= j * size + mapX && karakters.get(j2).getX()+karakters.get(j2).getWidth()/2 < (j + 1) * size + mapX &&
                            karakters.get(j2).getY()+karakters.get(j2).getHeight()/2 >= i * size + mapY && karakters.get(j2).getY()+karakters.get(j2).getHeight()/2 < (i + 1) * size + mapY) {
                        maps[i][j] = karakters.get(j2).getId();
                        karakters.get(j2).setMapPosX(j);
                        karakters.get(j2).setMapPosY(i);
                        // System.out.println(
                        //         "X: " + karakters.get(j2).getX() + " Y: " + karakters.get(j2).getY() + " MapX: "
                        //                 + karakters.get(j2).getMapPosX() + " MapY: " + karakters.get(j2).getMapPosY() + 
                        //                 " MapTile: " + maps[karakters.get(j2).mapPosY-1][karakters.get(j2).mapPosX-1]);
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
