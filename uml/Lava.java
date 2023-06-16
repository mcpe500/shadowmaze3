package src.game.Tile;

import processing.core.PApplet;
import src.util.AssetLoader;
import src.game.Player;
import src.game.Interface.Collidable;

public class Lava extends Tile implements Collidable{

    public Lava(PApplet parent, float width, float height, int x, int y) {
        super(parent, width, height, new AssetLoader(parent).getLava(), x, y);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean checkCollision(Collidable c) {
         if (c.isCollidable()) {
            if (c instanceof Player) {
                Player p = (Player) c;
                if (p.getX() + p.getWidth() > x+width/3 && p.getX() < x + width*2/3 && p.getY() + p.getHeight() > y + height/3
                        && p.getY() < y + height*2/3) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onCollision(Collidable c) {
        if (checkCollision(c)) {
            if (c instanceof Player) {
                Player p = (Player) c;
                p.takeDamage(100);
            }
        }
    }
}