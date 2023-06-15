package src.game.Interface;

public interface Pathfinding {
    void pathfind(int[][] map, int direction);

    void moveRandomly(int[][] map);

    int findPlayer(int[][] map);

    int[] getPossibleMoves(int[][] map);
}
