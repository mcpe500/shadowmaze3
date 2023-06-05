package src.game;

import processing.core.PApplet;
import src.game.Interface.Movable;

public abstract class Karakter implements Movable {

    private int x, y, moveSpeed;
    private int health;
    private int damage;

    public Karakter(int x, int y, int moveSpeed, int health, int damage) {
        this.x = x;
        this.y = y;
        this.moveSpeed = moveSpeed;
        this.health = health;
        this.damage = damage;
    }

    @Override
    public void moveUp() {
        y -= moveSpeed;
    }

    @Override
    public void moveDown() {
        y += moveSpeed;
    }

    @Override
    public void moveLeft() {
        x -= moveSpeed;
    }

    @Override
    public void moveRight() {
        x += moveSpeed;
    }

    public void attack(Karakter target) {
        target.takeDamage(damage);
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            // Karakter telah mati
            // Lakukan tindakan yang sesuai, seperti menghapus karakter dari permainan
        }
    }

    public void display(PApplet applet) {
        applet.rect(x, y, 50, 50);
    }
}