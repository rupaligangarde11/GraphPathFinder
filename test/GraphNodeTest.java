import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphNodeTest {
    private GraphNode graphNode;

    @Test
    public void shouldReturnTrueForSameNode() throws Exception {
        graphNode = new GraphNode();

        assertTrue(graphNode.hasPath(graphNode));
    }

    @Test
    public void shouldReturnTrueIfNode1IsReachableToNode2() throws Exception {
        graphNode = new GraphNode();
        GraphNode graphnode2 = new GraphNode();
        graphNode.connect(graphnode2);

        assertTrue(graphNode.hasPath(graphnode2));

    }

    @Test
    public void shouldReturnTrueIfNeighboursNeighbourIsReached() throws Exception {

        graphNode = new GraphNode();
        GraphNode graphnode3 = new GraphNode();
        GraphNode graphnode2 = new GraphNode();
        graphNode.connect(graphnode2);
        graphNode.connect(graphnode3);
        assertTrue(graphNode.hasPath(graphnode3));

    }

    @Test
    public void shouldReturnFalseWhenNodeIsNotReachable() throws Exception {

        GraphNode graphnode3 = new GraphNode();
        GraphNode graphnode2 = new GraphNode();
        graphNode = new GraphNode();
        graphNode.connect(graphnode2);
        assertFalse(graphNode.hasPath(graphnode3));

    }

    @Test
    public void shouldNotEnterCycle() throws Exception {

        GraphNode graphnode3 = new GraphNode();
        GraphNode graphnode2 = new GraphNode();
        graphNode = new GraphNode();

        graphNode.connect(graphnode2);
        graphnode2.connect(graphNode);
        graphnode2.connect(graphnode3);
        assertTrue(graphNode.hasPath(graphnode3));
    }

}
