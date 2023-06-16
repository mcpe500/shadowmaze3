package src.game;

import processing.core.PApplet;
import processing.core.PImage;

public class EnemySolid extends Enemy {
    private PImage image;

    public EnemySolid(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        super(x, y, moveSpeed, health, damage, width, height, 4);
        setId(300);
    }

    public void setImage(PImage image) {
        this.image = image;
        this.image.resize(this.getWidth() * 5, this.getHeight());
    }

    @Override
    public void display(PApplet applet) {
        decreaseInvulTime();
        processImageId();   
        applet.image(image, this.getX(), this.getY(), this.getWidth(), this.getHeight(), (this.imageIdx)*this.getWidth(), 0, (this.imageIdx+1)*this.getWidth(), this.getHeight());
    }

}
