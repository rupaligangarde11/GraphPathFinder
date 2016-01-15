import org.junit.Test;
import org.mockito.Mockito;

import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class GraphTracerTest {
    @Test
    public void shouldStoreTheStartNodeToTheStack() {
        Node startNode = Mockito.mock(Node.class);
        Stack<Node> nodeStack = new Stack<>();
        GraphTracer graphTracer = new GraphTracer(startNode,nodeStack);
        graphTracer.storeNodeToStack(startNode);
        assertTrue(nodeStack.contains(startNode));
    }
}
