import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private List<GraphNode> neighbours;

    public GraphNode() {
        neighbours = new ArrayList<>();
    }

    public boolean hasPath(GraphNode destination) {

        List<GraphNode> visitedNodes = new ArrayList<>();

        return findPath(destination, visitedNodes);
    }

    private boolean findPath(GraphNode destination, List<GraphNode> visitedNodes) {
        visitedNodes.add(this);
        if(this.equals(destination)){

            return true;
        }
        else{
            if (traverseThroughNeighbours(destination, visitedNodes)) return true;
        }
        return false;
    }

    private boolean traverseThroughNeighbours(GraphNode destination, List<GraphNode> visitedNodes) {
        for(GraphNode node: neighbours){

            if((!visitedNodes.contains(node)) && node.findPath(destination,visitedNodes)){
                return true;
            }
        }
        return false;
    }

    public void connect(GraphNode graphnode) {
        neighbours.add(graphnode);
    }
}
