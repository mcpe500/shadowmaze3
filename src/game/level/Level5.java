package src.game.level;

import src.game.Player;
import src.game.Tile.Tile;
import src.game.Tile.Wall;
import src.util.MapLoader;
import processing.core.PApplet;

public class Level5 extends PApplet {
    private Tile[][] map;
    private PApplet parent;
    private Player player;
    private int width, height;

    public Level5(PApplet parent) {
        width = 1280;
        height = 720;
        this.parent = parent;
        player = new Player(170, 170, 5, 100, 10, 16, 16);
    }

    @Override
    public void settings() {
        size(width, height);
    }

    public void setup() {
        String[][] strMap = MapLoader.loadMap(parent, "../assets/maps/map5.txt");
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