package src.game.Interface;

import src.game.Player;

public interface Pathfinding {
    void pathfind();

    void moveRandomly();

    boolean hasLineOfSight(Player player);

    int[] getPossibleMoves();
}
