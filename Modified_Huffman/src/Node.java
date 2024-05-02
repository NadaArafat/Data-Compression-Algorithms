
public class Node implements Comparable<Node> {
    private final int freq;
    private Node leftnode;
    private Node rightnode;

    public Node(Node l, Node r) {
        freq = l.freq + r.freq;
        leftnode = l;
        rightnode = r;
    }

    public Node(int freq) {
this.freq=freq;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(freq, node.freq);
    }

    public Node getleft() {
        return leftnode;
    }

    public Node getright() {
    return rightnode;
    }
}
