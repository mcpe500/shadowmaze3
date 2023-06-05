package src.game;

import processing.core.PApplet;

public class Player extends Karakter {
    private boolean up, down, left, right;

    public Player(int x, int y, int moveSpeed, int health, int damage) {
        super(x, y, moveSpeed, health, damage);
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
}
