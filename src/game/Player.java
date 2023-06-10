package src.game;

import processing.core.PApplet;
import processing.core.PImage;
import src.game.Interface.Collidable;
import java.util.ArrayList;

public class Player extends Karakter implements Collidable {
    private boolean up, down, left, right, flash;
    private int lastDirection;
    private PImage image; 
    private int imageIdx;

    public Player(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        super(x, y, moveSpeed, health, damage, width, height);
        this.flash = false;
        this.lastDirection = 2;
        this.imageIdx = 0;
        this.up = false;
        this.right = false;
        this.down = false;
        this.left = false;
    }

    public void playerController(PApplet parent) {
        if (up) {
            moveUp();
            this.lastDirection = 1;
        }
        if (down) {
            moveDown();
            this.lastDirection = 0;
        }
        if (left) {
            moveLeft();
            this.lastDirection = 2;
        }
        if (right) {
            moveRight();
            this.lastDirection = 3;
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
        } else if (key == 'f') {
            flash = true;
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
        } else if (key == 'f') {
            flash = false;
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

    public boolean getFlash() {
        return flash;
    }

    public int getLastDirection() {
        return lastDirection;
    }

    public ArrayList<int[]> flashRight(int width, int height, float cameraX, float cameraY) {
        ArrayList<int[]> pixels = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j+cameraX-this.getX()>=5*Math.abs(i+cameraY-this.getY()) && j+cameraX<this.getX()+width/5 && j+cameraX>this.getX()) {
                    pixels.add(new int[]{j, i});
                }
            }
        }
        return pixels;
    }

    @Override
    public void display(PApplet applet) {
        applet.image(image, this.getX(), this.getY(), this.getWidth(), this.getHeight(), imageIdx*this.getWidth(), lastDirection*this.getHeight(), this.getWidth(), this.getHeight());
        // this.imageIdx++;
        // this.imageIdx%=3;
        System.out.println(imageIdx*this.getWidth() + " " + lastDirection*this.getHeight());
    }

    public void setImage(PImage image) {
        this.image = image;
    }

}
