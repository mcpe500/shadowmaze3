package src.game;

import java.util.ArrayList;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;
import src.game.Interface.Collidable;
import src.game.Tile.Portal;

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
    private boolean canHide;
    private boolean hiding;
    private boolean tookDamage;
    private boolean pickItem;
    private boolean hasGrenade;
    private boolean throwGrenade;
    private boolean shootPortal;

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
        this.canHide = false;
        this.hiding = false;
        this.tookDamage = false;
        this.pickItem = false;
        this.hasGrenade = false;
        this.throwGrenade = false;
        this.shootPortal = false;
        setId(100);
    }

    public void playerController(PApplet parent) {
        if (!hiding) {
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
            if (this.flashCooldown == 0 && !hiding)
                flash = true;
        } else if (key == 'e') {
            if (this.canHide)
                this.hiding = true;
            if (this.flash)
                this.flash = false;
        } else if (key == 'p') {
            this.pickItem = true;
        } else if (key == 'q') {
            if (this.hasGrenade) {
                this.hasGrenade = false;
                this.throwGrenade = true;
            }
        } else if (key == ' ') {
            this.shootPortal = true;
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
        } else if (key == 'e') {
            if (this.canHide)
                this.hiding = false;
        } else if (key == 'p') {
            this.pickItem = false;
        } else if (key == ' ') {
            this.shootPortal = false;
        }
    }
    public boolean isShootPortal(){
        return this.shootPortal;
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

    public boolean isPickingItem() {
        return this.pickItem;
    }

    public void setHasGrenade(boolean hasGrenade) {
        this.hasGrenade = hasGrenade;
    }

    public boolean getHasGrenade() {
        return this.hasGrenade;
    }

    public boolean getThrowGrenade() {
        return this.throwGrenade;
    }

    public void runGrenade(ArrayList<Enemy> enemies) {
        this.throwGrenade = false;
        for (Enemy enemy : enemies) {
            if (Math.abs(this.mapPosX - enemy.mapPosX) + Math.abs(this.mapPosY - enemy.mapPosY) <= 7) {
                enemy.takeDamage(enemy.getHealth());
            }
        }
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

    public void setCanHide(boolean canHide) {
        this.canHide = canHide;
    }

    public boolean getCanHide() {
        return canHide;
    }

    public boolean isHiding() {
        return hiding;
    }

    public void setTookDamage(boolean tookDamage) {
        this.tookDamage = tookDamage;
    }

    public boolean getTookDamage() {
        return tookDamage;
    }

    public int getLastDirection() {
        return lastDirection;
    }

    public void checkFlash() {
        if (flash) {
            this.flashTick++;
            if (this.flashTick >= 60) {
                this.flash = false;
                this.flashCooldown = 300;
            }
        } else if (this.flashCooldown > 0) {
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

        if (!hiding) {
            applet.image(image, this.getX() - image.width / 3 + this.getWidth(),
                    this.getY() - image.height / 4 + this.getHeight(), image.width / 3, image.height / 4,
                    imageIdx * image.width / 3, lastDirection * image.height / 4, (imageIdx + 1) * image.width / 3,
                    (lastDirection + 1) * image.height / 4);
        }
    }

    public void setImage(PImage image) {
        this.image = image;
        this.image.resize(this.getWidth() * 3,
                (int) ((this.getWidth() * 3) * 1.0 / this.image.width) * this.image.height);
    }

}