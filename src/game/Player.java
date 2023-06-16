package src.game;

import processing.core.PApplet;
import processing.core.PImage;
import src.game.Interface.Collidable;

public class Player extends Karakter implements Collidable {
    private boolean up, down, left, right, flash;
    private int lastDirection;
    private PImage image;
    private int imageIdx;
    private int imageDx;
    private int tick;
    private boolean atExit;
    private int flashTick;
    private int lastFlashTick;
    private int flashCooldown;

    public Player(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        super(x, y, moveSpeed, health, damage, width, height);
        this.flash = false;
        this.lastDirection = 2;
        this.imageIdx = 1;
        this.imageDx = 1;
        this.up = false;
        this.right = false;
        this.down = false;
        this.left = false;
        this.atExit = false;
        this.flashTick = 0;
        this.lastFlashTick = 0;
        this.flashCooldown = 0;
        setId(100);
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
        if (up || down || left || right) {
            this.tick++;
            if (this.tick >= 8) {
                this.tick %= 8;
                this.imageIdx += this.imageDx;
                if (this.imageIdx == 0 || this.imageIdx == 2) {
                    this.imageDx *= -1;
                }
            }
        } else if (this.imageIdx != 1) {
            this.imageIdx = 1;
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
            if (this.flashCooldown==0) flash = true;
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

    public void setAtExit(boolean atExit) {
        this.atExit = atExit;
    }

    public boolean isAtExit() {
        return atExit;
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

    public void checkFlash() {
        if (flash) {
            this.flashTick++;
            if (this.flashTick>=80) {
                this.flash = false;
                this.flashCooldown = 200;
            }
        } else if (this.flashCooldown>0) {
            this.flashCooldown--;
        }
        // System.out.println("flashtick: " + this.flashTick);
        // System.out.println("flashcooldown: " + this.flashCooldown);
    }

    @Override
    public void display(PApplet applet) {
        decreaseInvulTime();
        checkFlash();

        if (this.flashTick == this.lastFlashTick) {
            this.flashTick = 0;
            this.lastFlashTick = 0;
        } else {
            this.lastFlashTick = this.flashTick;
        }

        applet.image(image, this.getX() - image.width / 3 + this.getWidth(),
                this.getY() - image.height / 4 + this.getHeight(), image.width / 3, image.height / 4,
                imageIdx * image.width / 3, lastDirection * image.height / 4, (imageIdx + 1) * image.width / 3,
                (lastDirection + 1) * image.height / 4);
    }

    public void setImage(PImage image) {
        this.image = image;
        this.image.resize(this.getWidth() * 3,
                (int) ((this.getWidth() * 3) * 1.0 / this.image.width) * this.image.height);
    }

}