package src.game.level;

import src.game.Player;
import src.game.Tile.Tile;
import src.util.MapLoader;
import processing.core.PApplet;

public class Level1 extends PApplet {
    private Tile[][] map;
    private PApplet parent;
    private Player player;

    public Level1(PApplet parent) {
        this.parent = parent;
        player = new Player(0, 0, 5, 100, 10);
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
        background(255);
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                if (this.map[i][j] != null) {
                    this.map[i][j].draw(this);
                }
            }
        }
        player.display(this);
        player.playerController(this);
    }
}
