package src.game;

import processing.core.PApplet;
import src.game.Interface.Collidable;

public class Player extends Karakter implements Collidable {
    private boolean up, down, left, right;

    public Player(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        super(x, y, moveSpeed, health, damage, width, height);
    }

    public void playerController(PApplet parent) {
        if (up) {
            moveUp();
        }
        if (down) {
            moveDown();
        }
        if (left) {
            moveLeft();
        }
        if (right) {
            moveRight();
        }
    }

    public void keyPressed(char key) {
        if (key == 'w') {
            up = true;
        } else if (key == 's') {
            down = true;
        } else if (key == 'a') {
            left = true;
        } else if (key == 'd') {
            right = true;
        }
    }

    public void keyReleased(char key) {
        if (key == 'w') {
            up = false;
        } else if (key == 's') {
            down = false;
        } else if (key == 'a') {
            left = false;
        } else if (key == 'd') {
            right = false;
        }
    }

    public int getDirection() {
        if (up) {
            return 0;
        } else if (up && right) {
            return 1;
        } else if (right) {
            return 2;
        } else if (down && right) {
            return 3;
        } else if (down) {
            return 4;
        } else if (down && left) {
            return 5;
        } else if (left) {
            return 6;
        } else if (up && left) {
            return 7;
        }
        return -1;
    }

    public void stopDown() {
        down = false;
    }

    public void stopUp() {
        up = false;
    }

    public void stopLeft() {
        left = false;
    }

    public void stopRight() {
        right = false;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean checkCollision(Collidable c) {
        if (c.checkCollision(this) && c.isCollidable()) {
            return true;
        }
        return false;
    }

    @Override
    public void onCollision(Collidable c) {
        if (c.checkCollision(this)) {
            c.onCollision(this);
        }
    }
}
