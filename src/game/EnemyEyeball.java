package src.game;

import processing.core.PApplet;
import processing.core.PImage;
import src.game.Interface.Collidable;

public class EnemyEyeball extends Enemy {
    private PImage image;
    private boolean isFlashed;

    public EnemyEyeball(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        super(x, y, moveSpeed, health, damage, width, height, 7);
        setId(200);
        // TODO Auto-generated constructor stub
    }

    public void setImage(PImage image) {
        this.image = image;
        this.image.resize(this.getWidth() * 8, (int) Math.round(this.getHeight() * 1.0 / 16 * image.height));
    }

    @Override
    public void display(PApplet applet) {
        decreaseInvulTime();
        processImageId();
        applet.image(image, this.getX(), this.getY(), this.getWidth(), this.getHeight(),
                (this.imageIdx) * this.getWidth(), 0, (this.imageIdx + 1) * this.getWidth(), this.getHeight());
    }

    @Override
    public void onCollision(Collidable c) {
        if (c instanceof Player && ((Player) c).getFlash()) {
            if (((Player) c).getFlash()) {
                System.out.println("flash1");
                isFlashed = true;
            }
            System.out.println("flash2");
        }
    }

    public boolean isFlashed() {
        return isFlashed;
    }
}