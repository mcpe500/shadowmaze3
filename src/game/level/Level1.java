package src.game.level;

import src.Main;
import src.game.Enemy;
import src.game.EnemyEyeball;
import src.game.Player;
import src.game.Tile.Beartrap;
import src.game.Tile.Lava;
import src.game.Tile.Tile;
import src.game.Tile.Wall;
import src.util.Button;
import src.util.MapLoader;

import java.util.ArrayList;

import processing.core.PApplet;

public class Level1 extends Level {
    private String[][] strMap;
    private Tile[][] map;
    private Player player;
    private ArrayList<Enemy> enemies;
    private int width, height;
    private boolean run;
    private Button backButton;
    private boolean over;
    private int time;
    private int currentSecond;

    public Level1(PApplet parent) {
        super(parent);
        width = 1280;
        height = 720;
        player = new Player(170, 170, 5, 100, 10, 22, 22);
        run = true;
        backButton = new Button(width / 2, height / 2, 100, 50, "Back");
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
        this.strMap = MapLoader.loadMap(parent, "../assets/maps/map1.txt");
        this.map = MapLoader.tileMap(parent, strMap, 32, 100, 100);
        backButton.setImage(loadImage("../assets/buttons/back_button.png"));
        player.setImage(loadImage("../assets/sprites/player.png"));
        EnemyEyeball enemyEyeball = new EnemyEyeball(180, 180, 5, 100, 10, 22, 22);
        enemyEyeball.setImage(loadImage("../assets/sprites/eyeball.png"));
        enemies.add(enemyEyeball);
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
                        } else if (this.map[i][j] instanceof Beartrap) {
                            Beartrap beartrap = (Beartrap) this.map[i][j];
                            beartrap.onCollision(player);
                        } else if (this.map[i][j] instanceof Lava) {
                            Lava lava = (Lava) this.map[i][j];
                            lava.onCollision(player);
                        }
                    }
                }
            }

            player.display(this);
            player.playerController(this);
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                enemy.display(this);
                enemy.moveController(player);
            }
            ArrayList<int[]> flashPixel = new ArrayList<>();
            if (player.getFlash()) {
                if (player.getLastDirection() == 0) {

                } else if (player.getLastDirection() == 2) {
                    flashPixel = player.flashRight(width, height, cameraX, cameraY);
                } else if (player.getLastDirection() == 4) {

                } else if (player.getLastDirection() == 6) {

                }
            }

            // Circle overlay
            int radius = 200;
            // for (int i = 0; i < height; i++) {
            // for (int j = 0; j < width; j++) {
            // double distance = Math.pow((j + cameraX - (player.getX() + player.getWidth()
            // / 2)), 2)
            // + Math.pow((i + cameraY - (player.getY() + player.getHeight() / 2)), 2);
            // if (distance >= Math.pow(radius, 2)) {
            // set(j, i, color(0, 0, 0));
            // }
            // }
            // }

            // Reset the transformations
            popMatrix();

            textSize(32);
            if (currentSecond != second()) {
                time++;
                currentSecond = second();
            }
            text("Health : " + player.getHealth(), 50, 50);
            text("Time : " + time, width - 200, 50);
            if (player.getHealth() <= 0) {
                run = false;
            }

        } else {
            gameOver();
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
            fill(255, 0, 0, 100); // Red color with alpha value of 100 (partially transparent)
            rect(0, 0, width, height);
            textSize(32);
            textAlign(CENTER, CENTER);
            fill(255);
            text("GAME OVER", width / 2, height / 2 - 100);
            over = true;
        }

        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);
        if (backButton.isClicked()) {
            Main app = new Main(3);
            String[] main = { "Main" };
            PApplet.runSketch(main, app);
            surface.setVisible(false);
            backButton.setEnabled(false);
        }
    }

    public boolean isFlashed(int x, int y, ArrayList<int[]> flashPixel) {
        for (int[] pixel : flashPixel) {
            if (pixel[0] == x && pixel[1] == y) {
                return true;
            }
        }
        return false;
    }
}