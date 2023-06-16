package src.game.level;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import src.game.*;
import src.game.Tile.*;
import src.util.Button;
import src.util.MapLoader;
import src.util.MazeGenerator;

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
    private MazeGenerator mazeGenerator;

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
        mazeGenerator = new MazeGenerator(width / 32 - 2, height / 32 - 2);
    }

    @Override
    public void settings() {
        size(width, height);
    }

    @Override
    public void setup() {
        int sizeX = 40;
        int sizeY = 40;
        map = mazeGenerator.generateMaze();
        // map = genEmptyMap(sizeX, sizeY);
        // randomMap(1, 1, map);
        // map = newMap(map);
        tileMap = MapLoader.tileMap(parent, map, 32, 100, 100);
        player = new Player(170, 170, 10, 100, 10, 32, 32);
        player.setImage(loadImage("../assets/sprites/player.png"));
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

    public int[][] newMap(int[][] intMap) {
        int[][] nMap = new int[intMap.length][intMap[0].length];
        for (int i = 0; i < nMap.length; i++) {
            for (int j = 0; j < nMap.length; j++) {
                nMap[i][j] = 1;
            }
        }
        for (int i = 1; i < nMap.length - 1; i++) {
            for (int j = 1; j < nMap[i].length - 1; j++) {
                nMap[i][j] = intMap[i - 1][j - 1];
            }
        }
        return nMap;
    }

    // public void randomMap(int startX, int startY, int[][] intMap) {
    //     int sizeX = intMap[0].length;
    //     int sizeY = intMap.length;
    //     intMap[startY][startX] = 0;
    //     int[][] possibleMove = { { 0, 2 }, { 2, 0 }, { 0, -2 }, { -2, 0 } };
    //     shuffle(possibleMove);
    //     for (int i = 0; i < possibleMove.length; i++) {
    //         int x1 = possibleMove[i][0];
    //         int y1 = possibleMove[i][1];
    //         int lx1 = startX + x1;
    //         int ly1 = startY + y1;
    //         int mx1 = startX + x1 / 2;
    //         int my1 = startY + y1 / 2;
    //         if (lx1 >= 0 && lx1 < sizeX && ly1 >= 0 && ly1 < sizeY) {
    //             if (intMap[ly1][lx1] == 1 && intMap[my1][mx1] == 1) {
    //                 intMap[ly1][lx1] = 0;
    //                 intMap[my1][mx1] = 0;
    //                 randomMap(lx1, ly1, intMap);
    //             }
    //         }
    //     }
    // }

    // public void shuffle(int[][] shf) {
    //     Random random = new Random();
    //     for (int i = shf.length - 1; i > 0; i--) {
    //         int j = random.nextInt(i + 1);
    //         int[] temp = shf[i];
    //         shf[i] = shf[j];
    //         shf[j] = temp;
    //     }
    // }

    @Override
    public void draw() {
        if (run) {
            // Calculate the camera position to center the player on the screen
            float cameraX = player.getX() - width / 2;
            float cameraY = player.getY() - height / 2;

            // Limit the camera position to stay within the map boundaries
            cameraX = constrain(cameraX, 50, tileMap[0].length * 32 - 1100);
            cameraY = constrain(cameraY, 50, tileMap.length * 32 - 550);

            // Apply camera translation
            pushMatrix();
            translate(-cameraX, -cameraY);

            background(0);
            for (int i = 0; i < this.tileMap.length; i++) {
                for (int j = 0; j < this.tileMap[i].length; j++) {
                    if (this.tileMap[i][j] != null) {
                        this.tileMap[i][j].draw(this);
                        if (this.tileMap[i][j] instanceof Wall) {
                            Wall wall = (Wall) this.tileMap[i][j];
                            wall.onCollision(player);
                            for (Enemy enemy : enemies) {
                                wall.onCollision(enemy);
                            }
                        } else if (this.tileMap[i][j] instanceof Beartrap) {
                            Beartrap beartrap = (Beartrap) this.tileMap[i][j];
                            beartrap.onCollision(player);
                        } else if (this.tileMap[i][j] instanceof Lava) {
                            Lava lava = (Lava) this.tileMap[i][j];
                            lava.onCollision(player);
                        } else if (this.tileMap[i][j] instanceof Exit) {
                            Exit exit = (Exit) this.tileMap[i][j];
                            exit.onCollision(player);
                        }
                    }
                }
            }

            player.display(this);
            player.playerController(this);
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                enemy.display(this);
                enemy.moveController(player, currentMap.getMaps());
            }
            ArrayList<Karakter> karakter = new ArrayList<>();
            karakter.add(player);
            for (int i = 0; i < enemies.size(); i++) {
                karakter.add(enemies.get(i));
            }
            // currentMap.updateMap(karakter, 100, 100, 32, this.strMap);

            // Circle overlay
            int radius = 200;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    double distance = Math.pow((j + cameraX - (player.getX() + player.getWidth()/ 2)), 2) + Math.pow((i + cameraY - (player.getY() + player.getHeight() / 2)), 2);
                    if (!player.getFlash()) {
                        if (distance >= Math.pow(radius, 2)) {
                            set(j, i, color(0, 0, 0));
                        }
                    } else {
                        if (player.getLastDirection() == 3 && ((distance >= Math.pow(radius, 2) && (j + cameraX - (player.getX()+player.getWidth()/2) + width/8 <= 5 * Math.abs(i + cameraY - (player.getY()+player.getHeight()/2)))) || j + cameraX > (player.getX()+player.getWidth()/2) + width / 2)) {
                            set(j, i, color(0, 0, 0));
                        } else if (player.getLastDirection() == 2 && ((distance >= Math.pow(radius, 2) && (j + cameraX - (player.getX()+player.getWidth()/2) - width/8 >= -5 * Math.abs(i + cameraY - (player.getY()+player.getHeight()/2)))) || j + cameraX < (player.getX()+player.getWidth()/2) - width / 2)) {
                            set(j, i, color(0, 0, 0));
                        } else if (player.getLastDirection() == 1 && ((distance >= Math.pow(radius, 2) && (i + cameraY - (player.getY()+player.getHeight()/2) - height/4 >= -5 * Math.abs(j + cameraX - (player.getX()+player.getWidth()/2)))) || i + cameraY < (player.getY()+player.getHeight()/2) - height / 1.2)) {
                            set(j, i, color(0, 0, 0));
                        } else if (player.getLastDirection() == 0 && ((distance >= Math.pow(radius, 2) && (i + cameraY - (player.getY()+player.getHeight()/2) + height/4 <= 5 * Math.abs(j + cameraX - (player.getX()+player.getWidth()/2)))) || i + cameraY > (player.getY()+player.getHeight()/2) + height / 1.2)) {
                            set(j, i, color(0, 0, 0));
                        }

                        if (player.getLastDirection() == 3 && (j + cameraX - (player.getX()+player.getWidth()/2) + width/8 >= 5 * Math.abs(i + cameraY - (player.getY()+player.getHeight()/2))) && (j + cameraX < (player.getX()+player.getWidth()/2) + width / 2) && (j + cameraX > (player.getX()+player.getWidth()/2))) {
                            for (Enemy enemy : enemies) {
                                if (enemy instanceof EnemyEyeball) {
                                    if (enemy instanceof EnemyEyeball && (enemy.getX()+enemy.getWidth()/2) == j+cameraX && (enemy.getY()+enemy.getHeight()/2) == i + cameraY) {
                                        EnemyEyeball eyeball = (EnemyEyeball) enemy;
                                        if (eyeball.incFlashTick()) {
                                            eyeball.takeDamage(eyeball.getHealth());
                                        } 
                                    }
                                }
                            }
                        } else if (player.getLastDirection() == 2 && (j + cameraX - (player.getX()+player.getWidth()/2) - width/8 <= -5 * Math.abs(i + cameraY - (player.getY()+player.getHeight()/2))) && (j + cameraX > (player.getX()+player.getWidth()/2) - width / 2) && (j + cameraX < (player.getX()+player.getWidth()/2))) {
                            for (Enemy enemy : enemies) {
                                if (enemy instanceof EnemyEyeball) {
                                    if (enemy instanceof EnemyEyeball && (enemy.getX()+enemy.getWidth()/2) == j+cameraX && (enemy.getY()+enemy.getHeight()/2) == i + cameraY) {
                                        EnemyEyeball eyeball = (EnemyEyeball) enemy;
                                        if (eyeball.incFlashTick()) {
                                            eyeball.takeDamage(eyeball.getHealth());
                                        } 
                                    }
                                }
                            }
                        } else if (player.getLastDirection() == 1 && (i + cameraY - (player.getY()+player.getHeight()/2) - height/4 <= -5 * Math.abs(j + cameraX - (player.getX()+player.getWidth()/2))) && (i + cameraY > (player.getY()+player.getHeight()/2) - height / 1.2) && (i + cameraY < (player.getY()+player.getHeight()/2))) {
                            for (Enemy enemy : enemies) {
                                if (enemy instanceof EnemyEyeball) {
                                    if (enemy instanceof EnemyEyeball && (enemy.getX()+enemy.getWidth()/2) == j+cameraX && (enemy.getY()+enemy.getHeight()/2) == i + cameraY) {
                                        EnemyEyeball eyeball = (EnemyEyeball) enemy;
                                        if (eyeball.incFlashTick()) {
                                            eyeball.takeDamage(eyeball.getHealth());
                                        } 
                                    }
                                }
                            }
                        } else if (player.getLastDirection() == 0 && (i + cameraY - (player.getY()+player.getHeight()/2) + height/4 >= 5 * Math.abs(j + cameraX - (player.getX()+player.getWidth()/2))) && (i + cameraY < (player.getY()+player.getHeight()/2) + height / 1.2) && (i + cameraY > (player.getY()+player.getHeight()/2))) {
                            for (Enemy enemy : enemies) {
                                if (enemy instanceof EnemyEyeball) {
                                    if (enemy instanceof EnemyEyeball && (enemy.getX()+enemy.getWidth()/2) == j+cameraX && (enemy.getY()+enemy.getHeight()/2) == i + cameraY) {
                                        EnemyEyeball eyeball = (EnemyEyeball) enemy;
                                        if (eyeball.incFlashTick()) {
                                            eyeball.takeDamage(eyeball.getHealth());
                                        } 
                                    }
                                }
                            }
                        }
                    }
                    
                    
                    
                }   
            }

            for (int i=enemies.size()-1; i>=0; i--) {
                if (enemies.get(i).getHealth()==0) {
                    enemies.remove(i);
                }
            }


            // Reset the transformations
            popMatrix();

            textSize(32);
            if (currentSecond != second()) {
                time++;
                currentSecond = second();
            }
            text("Health : " + player.getHealth(), 50, 50);
            text("Time : " + time, width - 200, 50);
            if (player.getHealth() <= 0 || player.isAtExit()) {
                run = false;
            }

        } else {
            // if (player.isAtExit()) {
            // win();
            // } else {
            // gameOver();
            // }
        }

    }

    @Override
    public void keyPressed() {
        player.keyPressed(key);
    }

    @Override
    public void keyReleased() {
        player.keyReleased(key);
    }

}
