package src.game.level;

import src.game.Player;
import src.game.Tile.Tile;
import src.game.Tile.Wall;
import src.util.MapLoader;
import processing.core.PApplet;

public class Level1 extends PApplet {
    private Tile[][] map;
    private PApplet parent;
    private Player player;
    private int width, height;

    public Level1(PApplet parent) {
        width = 1280;
        height = 720;
        this.parent = parent;
        player = new Player(170, 170, 2, 100, 10, 16, 16);
    }

    @Override
    public void settings() {
        size(width, height);
    }

    public void setup() {
        String[][] strMap = MapLoader.loadMap(parent, "../assets/maps/map1.txt");
        this.map = MapLoader.tileMap(parent, strMap, 32, 100, 100);
    }

    public void draw() {
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
                    }
                }
            }
        }
        player.display(this);
        player.playerController(this);

        // Circle overlay
        int radius = 200;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (Math.pow((j+cameraX-player.getX()), 2)+Math.pow((i+cameraY-player.getY()), 2) >= Math.pow(radius, 2)) {
                    set(j, i, color(0, 0, 0));
                }
            }
        }

        // Reset the transformations
        popMatrix();
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