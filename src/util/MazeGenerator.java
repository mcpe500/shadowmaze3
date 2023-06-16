package src.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
    private int[][] maze;
    private int width;
    private int height;
    private Random random;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];
        this.random = new Random();
    }

    public int[][] generateMaze() {
        initializeMaze();
        generatePaths(1, 1);
        maze = walledMaze(maze);
        maze = putBeartrap(maze);
        maze = putLava(maze);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        maze[maze.length-2][maze[0].length-2] = 9;
        return maze;
    }


    private int[][] walledMaze(int[][] oMaze){
        int[][] wMaze = new int[oMaze.length+2][oMaze[0].length+2];
        for (int i = 0; i < wMaze.length; i++) {
            for (int j = 0; j < wMaze[i].length; j++) {
                if(i == 0 || j == 0 || i == wMaze.length-1 || j == wMaze[i].length-1){
                    wMaze[i][j] = 1;
                }else{
                    wMaze[i][j] = oMaze[i-1][j-1];
                }
            }
        }
        return wMaze;
    }

    private void initializeMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = 1;
            }
        }
    }

    private void generatePaths(int x, int y) {
        maze[y][x] = 0;

        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP);
        directions.add(Direction.RIGHT);
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);
        Collections.shuffle(directions, random);

        for (Direction direction : directions) {
            int dx = direction.dx;
            int dy = direction.dy;

            int nx = x + dx;
            int ny = y + dy;

            int mx = x + dx / 2;
            int my = y + dy / 2;

            if (nx >= 0 && nx < width && ny >= 0 && ny < height && maze[ny][nx] == 1) {
                maze[my][mx] = 0;
                generatePaths(nx, ny);
            }
        }
    }

    private int[][] putBeartrap(int[][] maze) {
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(1, maze[0].length-2);
            int y = random.nextInt(1, maze.length-2);
            while (!trapValid(maze, x, y)) {
                x = random.nextInt(1, maze[0].length-2);
                y = random.nextInt(1, maze.length-2);
            }
            maze[y][x] = 2;
        }
        return maze;
    }

    private int[][] putLava(int[][] maze) {
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(1, maze[0].length-2);
            int y = random.nextInt(1, maze.length-2);
            while (!trapValid(maze, x, y)) {
                x = random.nextInt(1, maze[0].length-2);
                y = random.nextInt(1, maze.length-2);
            }
            maze[y][x] = 3;
        }
        return maze;
    }

    private boolean trapValid(int[][] maze, int x, int y) {
        if (maze[y][x]!=1) return false;
        int pathCount = 0;
        if (maze[y-1][x] == 0 && maze[y+1][x] == 0) pathCount++;
        if (maze[y][x-1] == 0 && maze[y][x+1] == 0) pathCount++;
        if (pathCount != 1) return false;
        return true;
    }
 
    private enum Direction {
        UP(0, -2),
        RIGHT(2, 0),
        DOWN(0, 2),
        LEFT(-2, 0);

        private int dx;
        private int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }
}
