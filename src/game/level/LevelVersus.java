package src.game.level;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import src.game.Enemy;
import src.game.Player;
import src.game.Tile.Tile;
import src.util.Button;
import src.util.MapLoader;

public class LevelVersus extends Level {

    private String[][] strMap;
    private Player player;
    private ArrayList<Enemy> enemies;
    private boolean run;
    private Button mainButton;
    private Button restartButton;
    private PImage gameOver;
    private boolean over;
    private int time;
    private int currentSecond;
    private Random random;

    private int[][] map;
    private Tile[][] tileMap;
    private int width, height;

    public LevelVersus(PApplet parent) {
        super(parent);
        width = 1280;
        height = 720;
        player = new Player(170, 170, 5, 100, 10, 32, 32);
        run = true;
        mainButton = new Button(width / 2 - 200, height / 2, 100, 50, "main");
        restartButton = new Button(width / 2 + 100, height / 2, 100, 50, "restart");
        enemies = new ArrayList<>();
        over = false;
        time = 0;
        currentSecond = second();
        random = new Random();
    }

    @Override
    public void settings() {
        size(width, height);
    }

    @Override
    public void setup() {
        int sizeX = 30;
        int sizeY = 30;
        map = genEmptyMap(sizeX, sizeY);
        randomMap(1, 1, map);
        newMap(map);
        tileMap = MapLoader.tileMap(parent, map, 32, 100, 100);
    }

    public int[][] genEmptyMap(int width, int height) {
        int[][] nMap = new int[height][width];
        for (int i = 0; i < nMap.length; i++) {
            for (int j = 0; j < nMap[i].length; j++) {
                nMap[i][j] = 1;
            }
        }
        return nMap;
    }

    public void newMap(int[][] intMap){
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                intMap[i][j] = 0;
            }
        }
    }

    public void randomMap(int startX, int startY, int[][] intMap) {
        int sizeX = intMap[0].length;
        int sizeY = intMap.length;
        intMap[startY][startX] = 0;
        int[][] possibleMove = { { 0, 2 }, { 2, 0 }, { 0, -2 }, { -2, 0 } };
        shuffle(possibleMove);
        for (int i = 0; i < possibleMove.length; i++) {
            int x1 = possibleMove[i][0];
            int y1 = possibleMove[i][1];
            int lx1 = startX + x1;
            int ly1 = startY + y1;
            int mx1 = startX + x1 / 2;
            int my1 = startY + y1 / 2;
            if (lx1 >= 0 && lx1 < sizeX && ly1 >= 0 && ly1 < sizeY) {
                if (intMap[ly1][lx1] == 1 && intMap[my1][mx1] == 1) {
                    intMap[my1][mx1] = 0;
                    randomMap(lx1, ly1, intMap);
                }
            }
        }
    }
    

    public void shuffle(int[][] shf) {
        for (int i = shf.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] temp = shf[i];
            shf[i] = shf[j];
            shf[j] = temp;
        }
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
