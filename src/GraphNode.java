import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private List<GraphNode> neighbours;
    private String nodeName;


    public GraphNode(String nodeName) {
        this.nodeName = nodeName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphNode graphNode = (GraphNode) o;

        return !(nodeName != null ? !nodeName.equals(graphNode.nodeName) : graphNode.nodeName != null);

    }

    @Override
    public int hashCode() {
        return nodeName != null ? nodeName.hashCode() : 0;
    }

    public void connect(GraphNode graphnode) {
        neighbours.add(graphnode);
    }

    public List<GraphNode> getShortestPath(GraphNode destination) throws NoPathExistException {
        ArrayList<GraphNode> minPath = new ArrayList<>();
        ArrayList<GraphNode> pathToThisNode = new ArrayList<>();
        if(hasPath(destination)) {
            ArrayList<GraphNode> visitedNodes = new ArrayList<>();
            calculateMinimumPath(destination, new ArrayList<GraphNode>(pathToThisNode), minPath,visitedNodes);
            return minPath;
        }else
            throw new NoPathExistException("No path available between these two nodes");
    }

    private void calculateMinimumPath(GraphNode destination,
                                      ArrayList<GraphNode> pathToThisNode,
                                      ArrayList<GraphNode> minPath, ArrayList<GraphNode> visitedNodes) {
        pathToThisNode.add(this);
        if(visitedNodes.contains(this) && !this.equals(destination))
        {
            return;
        }
        visitedNodes.add(this);

        if (this.equals(destination))
        {
            if(minPath.size() == 0)
            {
                minPath.addAll(pathToThisNode);
            }

            if(minPath.size() > pathToThisNode.size())
            {
                minPath.clear();
                minPath.addAll(pathToThisNode);
            }
        }
        for(GraphNode node : neighbours)
        node.calculateMinimumPath(destination,new ArrayList<GraphNode>(pathToThisNode),minPath, visitedNodes);
    }

    @Override
    public String toString() {
        return "nodeName = " + nodeName;
    }
}
