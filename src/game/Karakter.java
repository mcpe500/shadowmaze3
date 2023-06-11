package src.game;

import processing.core.PApplet;
import src.game.Interface.Movable;

public abstract class Karakter implements Movable {

    private int x, y, moveSpeed;
    private int health;
    private int damage;
    private int width, height;

    public Karakter(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        this.x = x;
        this.y = y;
        this.moveSpeed = moveSpeed;
        this.health = health;
        this.damage = damage;
        this.width = width;
        this.height = height;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
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

        }
    }

    public void display(PApplet applet) {
        applet.rect(x, y, width, height);
    }
}