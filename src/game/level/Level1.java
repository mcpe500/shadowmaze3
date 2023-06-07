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

    public Level1(PApplet parent) {
        this.parent = parent;
        player = new Player(170, 170, 5, 100, 10, 16, 16);
    }

    @Override
    public void settings() {
        size(1280, 720);
    }

    public void setup() {
        String[][] strMap = MapLoader.loadMap(parent, "../assets/maps/map1.txt");
        this.map = MapLoader.tileMap(parent, strMap, 32, 100, 100);
    }

    public void draw() {
        // Calculate the camera position to center the player on the screen
        float cameraX = player.getX() / 2;
        float cameraY = player.getY() / 2;

        // Limit the camera position to stay within the map boundaries
        cameraX = constrain(cameraX, 0, map[0].length * 32 - width);
        cameraY = constrain(cameraY, 0, map.length * 32 - height);

        // Apply camera translation
        pushMatrix();
        translate(-cameraX, -cameraY);

        background(255);
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