package src.game;

import processing.core.PApplet;
import src.game.Interface.Movable;

public abstract class Karakter implements Movable {

    private int x, y, moveSpeed;
    private int health;
    private int damage;
    private int invulTime;
    private int width, height;
    private int id;
    protected int mapPosX;
    protected int mapPosY;
    protected int[][] mapPos;

    public Karakter(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        this.x = x;
        this.y = y;
        this.moveSpeed = moveSpeed;
        this.health = health;
        this.damage = damage;
        this.width = width;
        this.height = height;
        this.mapPos = new int[4][2]; // [topleft - topright - botleft - botright][x - y]
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

    public void setMapPos(int[][] mapPos) {
        this.mapPos = mapPos;
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

    public int getInvulTime() {
        return invulTime;
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

    public void decreaseInvulTime() {
        if (invulTime > 0)
            invulTime--;
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
        return mapPos[0][1] + 1 < maps.length - 1
                && (maps[mapPos[0][1] + 1][mapPos[0][0]] == 0 || maps[mapPos[0][1] + 1][mapPos[0][0]] == id)
                && (maps[mapPos[1][1] + 1][mapPos[1][0]] == 0 || maps[mapPos[1][1] + 1][mapPos[1][0]] == id);
    }

    public boolean canMoveUp(int[][] maps) {
        return mapPos[2][1] - 1 > 0
                && (maps[mapPos[2][1] - 1][mapPos[2][0]] == 0 || maps[mapPos[2][1] - 1][mapPos[2][0]] == id)
                && (maps[mapPos[3][1] - 1][mapPos[3][0]] == 0 || maps[mapPos[3][1] - 1][mapPos[3][0]] == id);
    }

    public boolean canMoveLeft(int[][] maps) {
        return mapPos[1][0] - 1 > 0
                && (maps[mapPos[1][1]][mapPos[1][0] - 1] == 0 || maps[mapPos[1][1]][mapPos[1][0] - 1] == id)
                && (maps[mapPos[3][1]][mapPos[3][0] - 1] == 0 || maps[mapPos[3][1]][mapPos[3][0] - 1] == id);
    }

    public boolean canMoveRight(int[][] maps) {
        return mapPos[0][1] + 1 < maps[0].length - 1
                && (maps[mapPos[0][1]][mapPos[0][0] + 1] == 0 || maps[mapPos[0][1]][mapPos[0][0] + 1] == id)
                && (maps[mapPos[2][1]][mapPos[2][0] + 1] == 0 || maps[mapPos[2][1]][mapPos[2][0] + 1] == id);
    }

    public void attack(Karakter target) {
        if (target.getInvulTime() == 0) {
            target.takeDamage(damage);
        }
    }

    public void takeDamage(int amount) {
        health -= amount;
        invulTime = 100;
        if (health < 0) {
            health = 0;
        }

        if (this instanceof Player) {
            Player p = (Player) this;
            p.setTookDamage(true);
        }
    }

    public void display(PApplet applet) {
        decreaseInvulTime();
        applet.rect(x, y, width, height);
    }
}