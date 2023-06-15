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
    protected int[][] mapPos;

    public Karakter(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        this.x = x;
        this.y = y;
        this.moveSpeed = moveSpeed;
        this.health = health;
        this.damage = damage;
        this.width = width;
        this.height = height;
        this.mapPos = new int[4][2]; //[leftX - upY - rightX - botY]
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
        System.out.println("down");
        if (mapPos[0][1]+1 < maps.length-1) {
            System.out.println("top left: " + (maps[mapPos[0][1] + 1][mapPos[0][0]] == 0));
            System.out.println("top right: " + (maps[mapPos[1][1] + 1][mapPos[1][0]] == 0));
        }
        return mapPos[0][1]+1 < maps.length-1 && (maps[mapPos[0][1] + 1][mapPos[0][0]] == 0 || maps[mapPos[0][1] + 1][mapPos[0][0]] == id) && (maps[mapPos[1][1] + 1][mapPos[1][0]] == 0 || maps[mapPos[1][1] + 1][mapPos[1][0]] == id);
    }

    public boolean canMoveUp(int[][] maps) {
        System.out.println("up");
        if (mapPos[2][1]-1 > 0) {
            System.out.println("bot left: " + (maps[mapPos[2][1] - 1][mapPos[2][0]] == 0));
            System.out.println("bot right: " + (maps[mapPos[3][1] - 1][mapPos[3][0]] == 0));
        }
        return mapPos[2][1]-1 > 0 && (maps[mapPos[2][1] - 1][mapPos[2][0]] == 0 || maps[mapPos[2][1] - 1][mapPos[2][0]] == id) && (maps[mapPos[3][1] - 1][mapPos[3][0]] == 0 || maps[mapPos[3][1] - 1][mapPos[3][0]] == id);
    }

    public boolean canMoveLeft(int[][] maps) {
        System.out.println("left");
        if (mapPos[1][0]-1 > 0) {
            System.out.println("top right: " + (maps[mapPos[1][1]][mapPos[1][0] - 1] == 0));
            System.out.println("bot right: " + (maps[mapPos[3][1]][mapPos[3][0] - 1] == 0));
        }
        return mapPos[1][0]-1 > 0 && (maps[mapPos[1][1]][mapPos[1][0] - 1] == 0 || maps[mapPos[1][1]][mapPos[1][0] - 1] == id ) && (maps[mapPos[3][1]][mapPos[3][0] - 1] == 0 || maps[mapPos[3][1]][mapPos[3][0] - 1] == id);
    }

    public boolean canMoveRight(int[][] maps) {
        // for (int i = 0; i < maps.length; i++) {
        //     for (int j = 0; j < maps[i].length; j++) {
        //         System.out.print(maps[i][j] + "\t");
        //     }
        //     System.out.println();
        // }
        // System.out.println(mapPosX<maps[0].length-1);
        // System.out.println(maps[mapPosY][mapPosX + 1] == 0);
        // System.out.println(maps[mapPosY][mapPosX + 1]);
        System.out.println("right");
        if (mapPos[0][1]+1 < maps[0].length-1) {
            System.out.println("top left: " + (maps[mapPos[0][1]][mapPos[0][0] + 1] == 0));
            System.out.println("bot left: " + (maps[mapPos[2][1]][mapPos[2][0] + 1] == 0));
        }
        return mapPos[0][1]+1 < maps[0].length-1 && (maps[mapPos[0][1]][mapPos[0][0] + 1] == 0 || maps[mapPos[0][1]][mapPos[0][0] + 1] == id) && (maps[mapPos[2][1]][mapPos[2][0] + 1] == 0 || maps[mapPos[2][1]][mapPos[2][0] + 1] == id);
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