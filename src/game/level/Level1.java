package src.game.level;

import src.util.MapLoader;
import src.game.Level;
import processing.core.PApplet;

public class Level1 extends Level{
    public Level1(PApplet parent){
        super(parent);
    }
    
    public void setup(){
        String[][] map = MapLoader.loadMap(getParent(), "../assets/maps/map1.txt");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[1].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public void draw(){
        
    }

}
