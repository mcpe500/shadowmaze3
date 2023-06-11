package src.game;

import processing.core.PImage;

public class EnemyEyeball extends Enemy {
    private PImage image;

    public EnemyEyeball(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        super(x, y, moveSpeed, health, damage, width, height);
        // TODO Auto-generated constructor stub
    }

    public void setImage(PImage image) {
        this.image = image;
        this.image.resize(this.getWidth() * 3,
                (int) ((this.getWidth() * 3) * 1.0 / this.image.width) * this.image.height);
    }

}