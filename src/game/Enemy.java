package src.game;

import java.util.Random;

import src.game.Interface.Collidable;
import src.game.Interface.Pathfinding;
import src.game.Tile.Tile;

public abstract class Enemy extends Karakter implements Collidable, Pathfinding {
    protected Random random;
    protected int pickedMove;
    protected int moveTime;
    protected int moveCooldown;

    public Enemy(int x, int y, int moveSpeed, int health, int damage, int width, int height) {
        super(x, y, moveSpeed, health, damage, width, height);
        random = new Random();
        this.moveCooldown = 0;
        this.moveTime = 0;
        this.pickedMove = -1;
        // TODO Auto-generated constructor stub
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

    @Override
    public void pathfind(int[][] map) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pathfind'");
    }

    @Override
    public void moveRandomly(int[][] map) {
        if (this.moveCooldown == 0 && this.moveTime == 0) {
            int[] possibleMoves = this.getPossibleMoves(map);
            if (possibleMoves.length > 0) {
                for (int i = 0; i < possibleMoves.length; i++) {
                    System.out.print(possibleMoves[i] + " ");
                }
                System.out.println();
                this.pickedMove = possibleMoves[random.nextInt(possibleMoves.length)];
                this.moveTime = random.nextInt(200,500);
                this.moveCooldown = random.nextInt(300,500);
            }
        }
        if (this.moveTime>0) {
            this.moveTime--;
            System.out.println("checkstop");
            switch (this.pickedMove) {
                case 0:
                    this.moveUp();
                    if (!this.canMoveUp(map)) this.moveTime = 0;
                    break;
                case 1:
                    this.moveRight();
                    if (!this.canMoveRight(map)) this.moveTime = 0;
                    break;
                case 2:
                    this.moveDown();
                    if (!this.canMoveDown(map)) this.moveTime = 0;
                    break;
                case 3:
                    this.moveLeft();
                    if (!this.canMoveLeft(map)) this.moveTime = 0;
                    break;
            }
        } else if (this.moveCooldown>0) {
            this.moveCooldown--;
        }
        
            
    }
    

    @Override
    public boolean hasLineOfSight(Player player) {
        return false;
    }

    @Override
    public int[] getPossibleMoves(int[][] map) {
        int[] possibleMoves = new int[4];
        int idx = 0;
        System.out.println("getmove");
        if (this.canMoveUp(map)) {
            possibleMoves[idx] = 0;
            idx++;
        }
        if (this.canMoveRight(map)) {
            possibleMoves[idx] = 1;
            idx++;
        }
        if (this.canMoveDown(map)) {
            possibleMoves[idx] = 2;
            idx++;
        }
        if (this.canMoveLeft(map)) {
            possibleMoves[idx] = 3;
            idx++;
        }
        int[] result = new int[idx];
        for (int i = 0; i < idx; i++) {
            result[i] = possibleMoves[i];
            System.out.println(result[i]);
        }
        return result;
    }

    public void moveController(Player player, int[][] map) {
        if (this.hasLineOfSight(player)) {
            this.pathfind(map);
        } else {
            this.moveRandomly(map);
        }
    }
}