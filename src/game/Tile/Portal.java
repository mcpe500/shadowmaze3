package src.game.Tile;

import processing.core.PApplet;
import processing.core.PImage;
import src.game.Interface.Collidable;

public class Portal extends Tile implements Collidable {
    public Portal(PApplet parent, float width, float height, int x, int y) {
        super(parent, width, height, null, x, y);
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean isCollidable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCollidable'");
    }

    @Override
    public boolean checkCollision(Collidable c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkCollision'");
    }

    @Override
    public void onCollision(Collidable c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCollision'");
    }
}
