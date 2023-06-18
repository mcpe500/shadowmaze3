package src.util;

public class Node implements Comparable<Node> {
    private long score;
    private int idx;

    public Node(long score, int idx) {
        this.score = score;
        this.idx = idx;
    }

    public long getScore() {
        return score;
    }

    public int getIdx() {
        return idx;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.score, o.score);
    }
}
