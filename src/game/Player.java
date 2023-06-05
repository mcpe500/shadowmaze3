package src.game;

import processing.core.PApplet;

public class Player extends Karakter {

    public Player(int x, int y, int moveSpeed, int health, int damage) {
        super(x, y, moveSpeed, health, damage);
    }

    public void playerController(PApplet parent) {
        if (parent.keyPressed) {
            if (parent.key == 'w') {
                moveUp();
            } else if (parent.key == 's') {
                moveDown();
            } else if (parent.key == 'a') {
                moveLeft();
            } else if (parent.key == 'd') {
                moveRight();
            }
        }
    }
}