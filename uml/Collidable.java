package src.game.Interface;

public interface Collidable {
    public boolean isCollidable();
    public boolean checkCollision(Collidable c);
    public void onCollision(Collidable c);
}
