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
                if (p.getX() + p.getWidth() >= x && p.getX() <= x + width && p.getY() + p.getHeight() >= y
                        && p.getY() <= y + height) {
                    System.out.println("Lava collision");
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
