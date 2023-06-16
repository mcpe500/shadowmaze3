package src.game.level;

import src.Main;
import src.game.CurrentMap;
import src.game.Enemy;
import src.game.EnemyEyeball;
import src.game.EnemySolid;
import src.game.Karakter;
import src.game.Player;
import src.game.Tile.Beartrap;
import src.game.Tile.Exit;
import src.game.Tile.Lava;
import src.game.Tile.Tile;
import src.game.Tile.Wall;
import src.util.Button;
import src.util.FileManager;
import src.util.MapLoader;
import src.game.Tile.Trapdoor;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.Amplitude;
import processing.sound.SoundFile;

public class Level3 extends Level {
    private String[][] strMap;
    private Tile[][] map;
    private Player player;
    private ArrayList<Enemy> enemies;
    private int width, height;
    private boolean run;
    private Button mainButton;
    private Button restartButton;
    private Button nextButton;
    private PImage gameOver;
    private PImage levelClear;
    private boolean over;
    private int time;
    private int currentSecond;

    public Level3(PApplet parent) {
        super(parent);
        width = 1280;
        height = 720;
        player = new Player(170, 170, 5, 100, 10, 22, 22);
        run = true;
        mainButton = new Button(width / 2 - 200, height / 2, 100, 50, "main");
        restartButton = new Button(width / 2 + 100, height / 2, 100, 50, "restart");
        nextButton = new Button(width / 2 + 100, height / 2, 100, 50, "next");
        enemies = new ArrayList<>();
        over = false;
        time = 0;
        currentSecond = second();
    }

    @Override
    public void settings() {
        size(width, height);
    }

    public void setup() {
        this.strMap = MapLoader.loadMap(parent, "../assets/maps/map3.txt");
        this.map = MapLoader.tileMap(parent, strMap, 32, 100, 100);
        mainButton.setImage(loadImage("../assets/buttons/main_button.png"));
        restartButton.setImage(loadImage("../assets/buttons/restart_button.png"));
        nextButton.setImage(loadImage("../assets/buttons/next_button.png"));
        gameOver = loadImage("../assets/buttons/gameover.png");
        levelClear = loadImage("../assets/buttons/level_clear.png");
        player.setImage(loadImage("../assets/sprites/player.png"));
        EnemyEyeball enemyEyeball = new EnemyEyeball(994, 1218, 2, 100, 10, 22, 22);
        enemyEyeball.setImage(loadImage("../assets/sprites/eyeball.png"));
        enemies.add(enemyEyeball);
        enemyEyeball = new EnemyEyeball(1058, 1218, 2, 100, 10, 22, 22);
        enemyEyeball.setImage(loadImage("../assets/sprites/eyeball.png"));
        enemies.add(enemyEyeball);
        enemyEyeball = new EnemyEyeball(930, 1282, 2, 100, 10, 22, 22);
        enemyEyeball.setImage(loadImage("../assets/sprites/eyeball.png"));
        enemies.add(enemyEyeball);
        enemyEyeball = new EnemyEyeball(866, 1314, 2, 100, 10, 22, 22);
        enemyEyeball.setImage(loadImage("../assets/sprites/eyeball.png"));
        enemies.add(enemyEyeball);
        enemyEyeball = new EnemyEyeball(1090, 1314, 2, 100, 10, 22, 22);
        enemyEyeball.setImage(loadImage("../assets/sprites/eyeball.png"));
        enemies.add(enemyEyeball);

        EnemySolid enemySolid = new EnemySolid(291, 291, 2, 100, 10, 18, 24);
        enemySolid.setImage(loadImage("../assets/sprites/demon.png"));
        enemies.add(enemySolid);
        enemySolid = new EnemySolid(547, 355, 2, 100, 10, 18, 24);
        enemySolid.setImage(loadImage("../assets/sprites/demon.png"));
        enemies.add(enemySolid);
        enemySolid = new EnemySolid(163, 419, 2, 100, 10, 18, 24);
        enemySolid.setImage(loadImage("../assets/sprites/demon.png"));
        enemies.add(enemySolid);
        enemySolid = new EnemySolid(355, 611, 2, 100, 10, 18, 24);
        enemySolid.setImage(loadImage("../assets/sprites/demon.png"));
        enemies.add(enemySolid);
        enemySolid = new EnemySolid(355, 931, 2, 100, 10, 18, 24);
        enemySolid.setImage(loadImage("../assets/sprites/demon.png"));
        enemies.add(enemySolid);
        this.currentMap = new CurrentMap(strMap);
    }

    public void draw() {
        if (run) {
            // Calculate the camera position to center the player on the screen
            float cameraX = player.getX() - width / 2;
            float cameraY = player.getY() - height / 2;

            // Limit the camera position to stay within the map boundaries
            cameraX = constrain(cameraX, 50, map[0].length * 32 - 1100);
            cameraY = constrain(cameraY, 50, map.length * 32 - 550);

            // Apply camera translation
            pushMatrix();
            translate(-cameraX, -cameraY);

            background(0);
            for (int i = 0; i < this.map.length; i++) {
                for (int j = 0; j < this.map[i].length; j++) {
                    if (this.map[i][j] != null) {
                        this.map[i][j].draw(this);
                        if (this.map[i][j] instanceof Wall) {
                            Wall wall = (Wall) this.map[i][j];
                            wall.onCollision(player);
                            for (Enemy enemy : enemies) {
                                wall.onCollision(enemy);
                            }
                        } else if (this.map[i][j] instanceof Beartrap) {
                            Beartrap beartrap = (Beartrap) this.map[i][j];
                            beartrap.onCollision(player);
                        } else if (this.map[i][j] instanceof Lava) {
                            Lava lava = (Lava) this.map[i][j];
                            lava.onCollision(player);
                        } else if (this.map[i][j] instanceof Exit) {
                            Exit exit = (Exit) this.map[i][j];
                            exit.onCollision(player);
                        } else if (this.map[i][j] instanceof Trapdoor) {
                            Trapdoor trapdoor = (Trapdoor) this.map[i][j];
                            trapdoor.onCollision(player);
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
            currentMap.updateMap(karakter, 100, 100, 32, this.strMap);

            // Circle overlay
            int radius = 200;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    double distance = Math.pow((j + cameraX - (player.getX() + player.getWidth() / 2)), 2)
                            + Math.pow((i + cameraY - (player.getY() + player.getHeight() / 2)), 2);
                    if (!player.getFlash()) {
                        if (distance >= Math.pow(radius, 2)) {
                            set(j, i, color(0, 0, 0));
                        }
                    } else {
                        if (player.getLastDirection() == 3 && ((distance >= Math.pow(radius, 2)
                                && (j + cameraX - (player.getX() + player.getWidth() / 2) + width / 8 <= 5
                                        * Math.abs(i + cameraY - (player.getY() + player.getHeight() / 2))))
                                || j + cameraX > (player.getX() + player.getWidth() / 2) + width / 2)) {
                            set(j, i, color(0, 0, 0));
                        } else if (player.getLastDirection() == 2 && ((distance >= Math.pow(radius, 2)
                                && (j + cameraX - (player.getX() + player.getWidth() / 2) - width / 8 >= -5
                                        * Math.abs(i + cameraY - (player.getY() + player.getHeight() / 2))))
                                || j + cameraX < (player.getX() + player.getWidth() / 2) - width / 2)) {
                            set(j, i, color(0, 0, 0));
                        } else if (player.getLastDirection() == 1 && ((distance >= Math.pow(radius, 2)
                                && (i + cameraY - (player.getY() + player.getHeight() / 2) - height / 4 >= -5
                                        * Math.abs(j + cameraX - (player.getX() + player.getWidth() / 2))))
                                || i + cameraY < (player.getY() + player.getHeight() / 2) - height / 1.2)) {
                            set(j, i, color(0, 0, 0));
                        } else if (player.getLastDirection() == 0 && ((distance >= Math.pow(radius, 2)
                                && (i + cameraY - (player.getY() + player.getHeight() / 2) + height / 4 <= 5
                                        * Math.abs(j + cameraX - (player.getX() + player.getWidth() / 2))))
                                || i + cameraY > (player.getY() + player.getHeight() / 2) + height / 1.2)) {
                            set(j, i, color(0, 0, 0));
                        }

                        if (player.getLastDirection() == 3
                                && (j + cameraX - (player.getX() + player.getWidth() / 2) + width / 8 >= 5
                                        * Math.abs(i + cameraY - (player.getY() + player.getHeight() / 2)))
                                && (j + cameraX < (player.getX() + player.getWidth() / 2) + width / 2)
                                && (j + cameraX > (player.getX() + player.getWidth() / 2))) {
                            for (Enemy enemy : enemies) {
                                if (enemy instanceof EnemyEyeball) {
                                    if (enemy instanceof EnemyEyeball
                                            && (enemy.getX() + enemy.getWidth() / 2) == j + cameraX
                                            && (enemy.getY() + enemy.getHeight() / 2) == i + cameraY) {
                                        EnemyEyeball eyeball = (EnemyEyeball) enemy;
                                        if (eyeball.incFlashTick()) {
                                            eyeball.takeDamage(eyeball.getHealth());
                                        }
                                    }
                                }
                            }
                        } else if (player.getLastDirection() == 2
                                && (j + cameraX - (player.getX() + player.getWidth() / 2) - width / 8 <= -5
                                        * Math.abs(i + cameraY - (player.getY() + player.getHeight() / 2)))
                                && (j + cameraX > (player.getX() + player.getWidth() / 2) - width / 2)
                                && (j + cameraX < (player.getX() + player.getWidth() / 2))) {
                            for (Enemy enemy : enemies) {
                                if (enemy instanceof EnemyEyeball) {
                                    if (enemy instanceof EnemyEyeball
                                            && (enemy.getX() + enemy.getWidth() / 2) == j + cameraX
                                            && (enemy.getY() + enemy.getHeight() / 2) == i + cameraY) {
                                        EnemyEyeball eyeball = (EnemyEyeball) enemy;
                                        if (eyeball.incFlashTick()) {
                                            eyeball.takeDamage(eyeball.getHealth());
                                        }
                                    }
                                }
                            }
                        } else if (player.getLastDirection() == 1
                                && (i + cameraY - (player.getY() + player.getHeight() / 2) - height / 4 <= -5
                                        * Math.abs(j + cameraX - (player.getX() + player.getWidth() / 2)))
                                && (i + cameraY > (player.getY() + player.getHeight() / 2) - height / 1.2)
                                && (i + cameraY < (player.getY() + player.getHeight() / 2))) {
                            for (Enemy enemy : enemies) {
                                if (enemy instanceof EnemyEyeball) {
                                    if (enemy instanceof EnemyEyeball
                                            && (enemy.getX() + enemy.getWidth() / 2) == j + cameraX
                                            && (enemy.getY() + enemy.getHeight() / 2) == i + cameraY) {
                                        EnemyEyeball eyeball = (EnemyEyeball) enemy;
                                        if (eyeball.incFlashTick()) {
                                            eyeball.takeDamage(eyeball.getHealth());
                                        }
                                    }
                                }
                            }
                        } else if (player.getLastDirection() == 0
                                && (i + cameraY - (player.getY() + player.getHeight() / 2) + height / 4 >= 5
                                        * Math.abs(j + cameraX - (player.getX() + player.getWidth() / 2)))
                                && (i + cameraY < (player.getY() + player.getHeight() / 2) + height / 1.2)
                                && (i + cameraY > (player.getY() + player.getHeight() / 2))) {
                            for (Enemy enemy : enemies) {
                                if (enemy instanceof EnemyEyeball) {
                                    if (enemy instanceof EnemyEyeball
                                            && (enemy.getX() + enemy.getWidth() / 2) == j + cameraX
                                            && (enemy.getY() + enemy.getHeight() / 2) == i + cameraY) {
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

            for (int i = enemies.size() - 1; i >= 0; i--) {
                if (enemies.get(i).getHealth() == 0) {
                    enemies.remove(i);
                }
            }

            if (!(map[player.getMapPosY()][player.getMapPosX()] instanceof Trapdoor) && player.getCanHide()) {
                player.setCanHide(false);
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

            if (player.getTookDamage()) {
                player.setTookDamage(false);
                SoundFile sound = new SoundFile(this, "../assets/sounds/sfx_blood.mp3");
                sound.play();
                Amplitude amp = new Amplitude(this);
                amp.input(sound);
                if (player.getHealth() <= 0 || player.isAtExit()) {
                    run = false;
                    sound.stop();
                }
            }

            if (player.isAtExit()) {
                run = false;
            }

        } else {
            if (player.isAtExit()) {
                win();
            } else {
                gameOver();
            }
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

    public void gameOver() {
        if (!over) {
            fill(255, 0, 0, 100);
            rect(0, 0, width, height);
            over = true;

            SoundFile sound = new SoundFile(this, "../assets/sounds/sfx_gameover.mp3");
            sound.play();
            Amplitude amp = new Amplitude(this);
            amp.input(sound);
        }

        image(gameOver, width / 2 - gameOver.width / 2, height / 2 - 200);
        mainButton.display(this);
        mainButton.update(mouseX, mouseY, mousePressed);
        if (mainButton.isClicked()) {
            Main app = new Main(3);
            String[] main = { "Main" };
            PApplet.runSketch(main, app);
            surface.setVisible(false);
            mainButton.setEnabled(false);
        }
        restartButton.display(this);
        restartButton.update(mouseX, mouseY, mousePressed);
        if (restartButton.isClicked()) {
            String[] levStrings = { "Level3" };
            PApplet.runSketch(levStrings, new Level3(parent));
            surface.setVisible(false);
            restartButton.setEnabled(false);
        }
    }

    public void win() {
        if (!over) {
            fill(0, 255, 0, 100);
            rect(0, 0, width, height);
            over = true;

            SoundFile sound = new SoundFile(this, "../assets/sounds/sfx_win.mp3");
            sound.play();
            Amplitude amp = new Amplitude(this);
            amp.input(sound);

            int[] file = FileManager.openFile();
            if (file[2]<time) file[2] = time;
            FileManager.writeToFile(file);
        }

        image(levelClear, width / 2 - gameOver.width / 2, height / 2 - 200);
        mainButton.display(this);
        mainButton.update(mouseX, mouseY, mousePressed);
        if (mainButton.isClicked()) {
            Main app = new Main(3);
            String[] main = { "Main" };
            PApplet.runSketch(main, app);
            surface.setVisible(false);
            mainButton.setEnabled(false);
        }
        nextButton.display(this);
        nextButton.update(mouseX, mouseY, mousePressed);
        if (nextButton.isClicked()) {
            String[] levStrings = { "Level4" };
            PApplet.runSketch(levStrings, new Level4(parent));
            surface.setVisible(false);
            nextButton.setEnabled(false);
        }
    }
}