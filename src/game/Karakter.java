package src.game;

import processing.core.PApplet;
import src.game.Interface.Movable;

public abstract class Karakter implements Movable {

    private int x, y, moveSpeed;
    private int health;
    private int damage;
    private int width, height;
    private int id;
    protected int mapPosX;
    protected int mapPosY;

    public Karakter(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        this.x = x;
        this.y = y;
        this.moveSpeed = moveSpeed;
        this.health = health;
        this.damage = damage;
        this.width = width;
        this.height = height;
    }

    public int getMapPosX() {
        return mapPosX;
    }

    public int getMapPosY() {
        return mapPosY;
    }

    public void setMapPosX(int mapPosX) {
        this.mapPosX = mapPosX;
    }

    public void setMapPosY(int mapPosY) {
        this.mapPosY = mapPosY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean canMoveDown(int[][] maps) {
        return maps[mapPosY + 1][mapPosX] == 0;
    }

    public boolean canMoveUp(int[][] maps) {
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps.length; j++) {
                System.out.print(maps[i][j] + " ");
            }
            System.out.println();
        }
        return maps[mapPosY - 1][mapPosX] == 0;
    }

    public boolean canMoveLeft(int[][] maps) {
        return maps[mapPosY][mapPosX - 1] == 0;
    }

    public boolean canMoveRight(int[][] maps) {
        return maps[mapPosY][mapPosX + 1] == 0;
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