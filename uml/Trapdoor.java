package src.game.Tile;

import processing.core.PApplet;
import src.util.AssetLoader;
import src.game.Player;
import src.game.Interface.Collidable;

public class Trapdoor extends Tile {

    public Trapdoor(PApplet parent, float width, float height, int x, int y) {
        super(parent, width, height, new AssetLoader(parent).getTrapdoor(), x, y);
    }
    
    public boolean isCollidable() {
        return true;
    }


    public boolean checkCollision(Collidable c) {
         if (c.isCollidable()) {
            if (c instanceof Player) {
                Player p = (Player) c;
                if (p.getX() + p.getWidth() > x+width/3 && p.getX() < x + width*2/3 && p.getY() + p.getHeight() > y+width/3
                        && p.getY() < y + height*2/3) {
                    return true;
                }
            }
        }
        return false;
    }


    public void onCollision(Collidable c) {
        if (checkCollision(c)) {
            if (c instanceof Player) {
                Player p = (Player) c;
                p.setCanHide(true);
            }
        } else if (c instanceof Player) {
            Player p = (Player) c;
            if (p.getCanHide()) p.setCanHide(false);
        }
    }
}