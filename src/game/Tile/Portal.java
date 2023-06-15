package src.game.Tile;

import processing.core.PApplet;
import src.game.Player;
import src.game.Interface.Collidable;
import src.util.AssetLoader;

public class Portal extends Tile implements Collidable {
    public Portal(PApplet parent, float width, float height, int x, int y) {
        super(parent, width, height, new AssetLoader(parent).getWall(), x, y);
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
                Player player = (Player) c;
                float playerX = player.getX();
                float playerY = player.getY();

                // Calculate the overlap distances
                float xOverlap = Math.min(playerX + player.getWidth(), x + width) - Math.max(playerX, x);
                float yOverlap = Math.min(playerY + player.getHeight(), y + height) - Math.max(playerY, y);
                if (xOverlap < yOverlap) {
                    if (playerX < x) {
                        // player.setX((int) (x - player.getWidth()));
                        // player.stopRight();
                    } else {
                        // player.setX((int) (x + width));
                        // player.stopLeft();
                    }
                } else {
                    if (playerY < y) {
                        // player.setY((int) (y - player.getHeight()));
                        // player.stopDown();
                    } else {
                        // player.setY((int) (y + height));
                        // player.stopUp();
                    }
                }
            }
        }
    }



}
