import java.util.List;

public class Node {
    private List<Node> neighbours;

    public Node(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }
}
