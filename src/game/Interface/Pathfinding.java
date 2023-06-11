package src.game.Interface;

import src.game.Player;

public interface Pathfinding {
    void pathfind(int[][] map);

    void moveRandomly(int[][] map);

    boolean hasLineOfSight(Player player);

    int[] getPossibleMoves(int[][] map);
}
