import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private String nodeName;
    private List<Pair<GraphNode,Integer>> neighboursWithWeight;


    public GraphNode(String nodeName) {
        this.nodeName = nodeName;
        neighboursWithWeight=new ArrayList<>();
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
        for(Pair edgeWithWeight: neighboursWithWeight){

            GraphNode node = (GraphNode)edgeWithWeight.getKey();
            if((!visitedNodes.contains(node)) && ( node).findPath(destination,
                    visitedNodes)){
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

    public void connect(GraphNode graphnode, int weight) {

        neighboursWithWeight.add(new Pair(graphnode,weight));

    }


    public int getShortestPath(GraphNode destination) throws NoPathExistException {
        ArrayList<GraphNode> minPath = new ArrayList<>();
        ArrayList<GraphNode> pathToThisNode = new ArrayList<>();
        if(hasPath(destination)) {
            ArrayList<GraphNode> visitedNodes = new ArrayList<>();
            int costOfOptimalPath=Integer.MAX_VALUE;
            int thisPathCost = 0;
            costOfOptimalPath=calculateMinimumPath(destination, new ArrayList<GraphNode>(pathToThisNode), minPath,
                    visitedNodes, costOfOptimalPath, thisPathCost);
            System.out.println(minPath.toString());
            return costOfOptimalPath;
        }else
            throw new NoPathExistException("No path available between these two nodes");
    }

    private int calculateMinimumPath(GraphNode destination,
                                     ArrayList<GraphNode> pathToThisNode,
                                     ArrayList<GraphNode> minPath, ArrayList<GraphNode> visitedNodes, int
                                             costOfOptimalPath, int thisPathCost) {
        pathToThisNode.add(this);
        if(visitedNodes.contains(this) && !this.equals(destination))
        {
            return costOfOptimalPath;
        }
        visitedNodes.add(this);

        if (this.equals(destination))
        {
            if(minPath.size() == 0)
            {
                minPath.addAll(pathToThisNode);
                costOfOptimalPath=thisPathCost;
                return costOfOptimalPath;
            }
            if(thisPathCost < costOfOptimalPath)
            {
                minPath.clear();
                minPath.addAll(pathToThisNode);
                costOfOptimalPath=thisPathCost;
            }
        }
        for(Pair edgeWithWeight : neighboursWithWeight) {
           GraphNode node=(GraphNode) edgeWithWeight.getKey();
            costOfOptimalPath=node.calculateMinimumPath(destination, new ArrayList<>(pathToThisNode),
                    minPath, visitedNodes, costOfOptimalPath, thisPathCost+(Integer)edgeWithWeight.getValue());

        }
        return costOfOptimalPath;
    }

    @Override
    public String toString() {
        return "nodeName = " + nodeName;
    }
}
