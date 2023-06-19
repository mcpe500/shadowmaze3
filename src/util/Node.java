package src.util;

public class Node implements Comparable<Node> {
    private long score;
    private String indentifier;

    public Node(long score, String idx) {
        this.score = score;
        this.indentifier = idx;
    }

    public long getScore() {
        return score;
    }

    public String getIndentifier() {
        return indentifier;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setIndentifier(String idx) {
        this.indentifier = idx;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.score, o.score);
    }
}
