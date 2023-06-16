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
    protected int imageIdx;
    protected int imageDx;
    protected int tick;
    protected int maxImageIdx;

    public Enemy(int x, int y, int moveSpeed, int health, int damage, int width, int height, int maxImageIdx) {
        super(x, y, moveSpeed, health, damage, width, height);
        random = new Random();
        this.moveCooldown = 0;
        this.moveTime = 0;
        this.pickedMove = -1;
        this.imageIdx = 0;
        this.imageDx = 1;
        this.tick = 0;
        this.maxImageIdx = maxImageIdx;
        this.mapPosX = 30;
        this.mapPosY = 30;
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
    public void pathfind(int[][] map, int direction) {
        switch (direction) {
            case 0:
                if (this.canMoveUp(map)) {
                    this.moveUp();
                    processImageId();
                    // System.out.println("up");
                }
                break;
            case 1:
                if (this.canMoveRight(map)) {
                    this.moveRight();
                    processImageId();
                    // System.out.println("right");
                }
                break;
            case 2:
                if (this.canMoveDown(map)) {
                    this.moveDown();
                    processImageId();
                    // System.out.println("down");
                }
                break;
            case 3:
                if (this.canMoveLeft(map)) {
                    this.moveLeft();
                    processImageId();
                    // System.out.println("left");
                }
                break;
        }
    }

    @Override
    public void moveRandomly(int[][] map) {
        if (this.moveCooldown == 0 && this.moveTime == 0) {
            int[] possibleMoves = this.getPossibleMoves(map);
            if (possibleMoves.length > 0) {
                // for (int i = 0; i < possibleMoves.length; i++) {
                //     System.out.print(possibleMoves[i] + " ");
                // }
                // System.out.println();
                this.pickedMove = possibleMoves[random.nextInt(possibleMoves.length)];
                this.moveTime = random.nextInt(200,500);
                // this.moveTime = 100;
                this.moveCooldown = random.nextInt(200,500);
                // this.moveCooldown = 10;
            // } else {
            //     System.out.println("No moves");
            }
        }
        if (this.moveTime>0) {
            this.moveTime--;
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

    public void processImageId() {
        this.tick++;
        if (this.tick>=20) {
            this.tick%=20;
            this.imageIdx+=this.imageDx;

            if (this.imageIdx==0 || this.imageIdx==this.maxImageIdx) {
                this.imageDx*=-1;
            }
        }
    }

    public int getImageIdx() {
        return this.imageIdx;
    }
    

    @Override
    public int findPlayer(int[][] map) {
        // up
        int tempX = getMapPosX();
        int tempY = getMapPosY();
        while (tempY>0 && (map[tempY][tempX] == 0 || map[tempY][tempX] == getId())) {
            tempY--;
        }
        if (map[tempY][tempX] == 100) return 0;
        
        // down
        tempY = getMapPosY()+1;
        while (tempY<map.length && (map[tempY][tempX] == 0 || map[tempY][tempX] == getId())) {
            tempY++;
        }
        if (map[tempY][tempX] == 100) return 2;

        // left 
        tempX = getMapPosX();
        tempY = getMapPosY();
        while (tempX>0 && (map[tempY][tempX] == 0 || map[tempY][tempX] == getId())) {
            tempX--;
        }
        if (map[tempY][tempX] == 100) return 3;

        // right
        tempX = getMapPosX();
        while (tempX<map[0].length && (map[tempY][tempX] == 0 || map[tempY][tempX] == getId())){
            tempX++;
        }
        if (map[tempY][tempX] == 100) return 1;

        return -1;
    }

    @Override
    public int[] getPossibleMoves(int[][] map) {
        int[] possibleMoves = new int[4];
        int idx = 0;
        // System.out.println("getmove");
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
            // System.out.println(result[i]);
        }
        return result;
    }

    public void moveController(Player player, int[][] map) {
        int playerPos = this.findPlayer(map);
        if (playerPos != -1 && !player.isHiding()) {
            this.pathfind(map, playerPos);
        } else {
            this.moveRandomly(map);
        }

        if (Math.abs(player.mapPosX-this.mapPosX) + Math.abs(player.mapPosY-this.mapPosY) <= 1 && !player.isHiding()) {
            this.attack(player);
        }
    }
}