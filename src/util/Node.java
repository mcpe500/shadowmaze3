package src.util;

public class Node {
    private long score;
    private String name;

    public Node(long score, String name) {
        this.score = score;
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }
}
