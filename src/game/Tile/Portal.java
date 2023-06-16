package src.game.Tile;

import processing.core.PApplet;
import processing.core.PImage;
import src.game.Player;
import src.game.Interface.Collidable;
import src.util.AssetLoader;

public class Portal extends Tile implements Collidable {
    private boolean portal1Active;
    private boolean portal2Active;

    public Portal(PApplet parent, float width, float height, int x, int y) {
        super(parent, width, height, new AssetLoader(parent).getPortal1(), x, y);
        this.portal1Active = false;
        this.portal2Active = false;
    }
    

    public PImage getPortalImage() {
        if (portal1Active && portal2Active) {
            return new AssetLoader(parent).getPortal2();
        } else if (portal1Active) {
            return new AssetLoader(parent).getPortal1();
        } else if (portal2Active) {
            return new AssetLoader(parent).getPortal2();
        } else {
            return new AssetLoader(parent).getPortal1();
        }
    }

    public void activatePortal1() {
        this.portal1Active = true;
    }

    public void activatePortal2() {
        this.portal2Active = true;
    }

    public void deactivatePortal1() {
        this.portal1Active = false;
    }

    public void deactivatePortal2() {
        this.portal2Active = false;
    }

    public boolean isPortal1Active() {
        return portal1Active;
    }

    public boolean isPortal2Active() {
        return portal2Active;
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
        if (checkCollision(c) && isPortal1Active() && isPortal2Active()) {
            if (c instanceof Player) {
                Player player = (Player) c;
                float playerX = player.getX();
                float playerY = player.getY();

                // Calculate the overlap distances
                float xOverlap = Math.min(playerX + player.getWidth(), x + width) - Math.max(playerX, x);
                float yOverlap = Math.min(playerY + player.getHeight(), y + height) - Math.max(playerY, y);

                if (xOverlap < yOverlap) {
                    if (playerX < x) {
                        player.setX((int) (x + width));
                        player.stopLeft();
                    } else {
                        player.setX(x - player.getWidth());
                        player.stopRight();
                    }
                } else {
                    if (playerY < y) {
                        player.setY((int) (y + height));
                        player.stopUp();
                    } else {
                        player.setY(y - player.getHeight());
                        player.stopDown();
                    }
                }
            }
        }
    }
}
