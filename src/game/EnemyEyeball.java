package src.game;

import processing.core.PApplet;
import processing.core.PImage;
import src.game.Interface.Collidable;

public class EnemyEyeball extends Enemy {
    private PImage image;
    private int flashTick;
    private int lastFlashTick;

    public EnemyEyeball(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        super(x, y, moveSpeed, health, damage, width, height, 7);
        setId(200);
        this.flashTick = 0;
        this.lastFlashTick = 0;
    }

    public void setImage(PImage image) {
        this.image = image;
        this.image.resize(this.getWidth() * 8, (int) Math.round(this.getHeight() * 1.0 / 16 * image.height));
    }

    @Override
    public void display(PApplet applet) {
        decreaseInvulTime();
        processImageId();   
        if (this.flashTick == this.lastFlashTick) {
            this.flashTick = 0;
            this.lastFlashTick = 0;
        } else {
            this.lastFlashTick = this.flashTick;
        }
        applet.image(image, this.getX(), this.getY(), this.getWidth(), this.getHeight(), (this.imageIdx)*this.getWidth(), 0, (this.imageIdx+1)*this.getWidth(), this.getHeight());
    }

    public boolean incFlashTick() {
        this.flashTick++;
        if (this.flashTick>=50) {
            return true;
        }
        return false;
    }

}