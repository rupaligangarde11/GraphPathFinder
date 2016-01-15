import java.util.Stack;

public class GraphTracer {
    private final Node startNode;
    private final Stack<Node> nodeStack;

    public GraphTracer(Node startNode, Stack<Node> nodeStack) {

        this.startNode = startNode;
        this.nodeStack = nodeStack;
    }

    public void storeNodeToStack(Node startNode) {
        nodeStack.push(startNode);
    }
}
