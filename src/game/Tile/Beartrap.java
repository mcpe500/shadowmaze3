package src.game.Tile;

import processing.core.PApplet;
import src.util.AssetLoader;
import src.game.Player;
import src.game.Interface.Collidable;

public class Beartrap extends Tile implements Collidable{
    private boolean active;

    public Beartrap(PApplet parent, float width, float height, int x, int y) {
        super(parent, width, height, new AssetLoader(parent).getBeartrap(), x, y);
        this.active = true;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean checkCollision(Collidable c) {
         if (c.isCollidable() && this.active) {
            if (c instanceof Player) {
                Player p = (Player) c;
                if (p.getX() + p.getWidth() > x && p.getX() < x + width && p.getY() + p.getHeight() > y
                        && p.getY() < y + height) {
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
                p.takeDamage(5);
                this.setImage(new AssetLoader(parent).getBeartrapBlood());
                this.active = false;
            }
        }
    }
}