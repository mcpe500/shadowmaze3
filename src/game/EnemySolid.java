package src.game;

public class EnemySolid extends Enemy {

    public EnemySolid(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        super(x, y, moveSpeed, health, damage, width, height, 4);
        setId(300);
    }

}
