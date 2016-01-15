import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphNodeTest {
    private GraphNode graphNode;

    @Test
    public void shouldReturnTrueForSameNode() throws Exception {
        graphNode = new GraphNode(null);

        assertTrue(graphNode.hasPath(graphNode));
    }

    @Test
    public void shouldReturnTrueIfNode1IsReachableToNode2() throws Exception {
        graphNode = new GraphNode(null);
        GraphNode graphnode2 = new GraphNode(null);
        graphNode.connect(graphnode2);

        assertTrue(graphNode.hasPath(graphnode2));

    }

    @Test
    public void shouldReturnTrueIfNeighboursNeighbourIsReached() throws Exception {

        graphNode = new GraphNode(null);
        GraphNode graphnode3 = new GraphNode(null);
        GraphNode graphnode2 = new GraphNode(null);
        graphNode.connect(graphnode2);
        graphNode.connect(graphnode3);
        assertTrue(graphNode.hasPath(graphnode3));

    }

    @Test
    public void shouldReturnFalseWhenNodeIsNotReachable() throws Exception {

        GraphNode graphnode3 = new GraphNode(null);
        GraphNode graphnode2 = new GraphNode(null);
        graphNode = new GraphNode(null);
        graphNode.connect(graphnode2);
        assertFalse(graphNode.hasPath(graphnode3));

    }

    @Test
    public void shouldNotEnterCycle() throws Exception {

        GraphNode graphnode3 = new GraphNode(null);
        GraphNode graphnode2 = new GraphNode(null);
        graphNode = new GraphNode(null);

        graphNode.connect(graphnode2);
        graphnode2.connect(graphNode);
        graphnode2.connect(graphnode3);
        assertTrue(graphNode.hasPath(graphnode3));
    }


    @Test
    public void shouldGiveShortestPathForsameNode() throws NoPathExistException {
        GraphNode graphNode = new GraphNode("a");
        List<GraphNode> shortestPath = new ArrayList<>(Arrays.asList(graphNode));
        assertEquals(shortestPath, graphNode.getShortestPath(graphNode));
    }

    @Test(expected = NoPathExistException.class)
    public void shouldThrowExceptionForGiveShortestPathForAdjecentNodesIfNoPathExists() throws NoPathExistException {
        GraphNode graphNodeA = new GraphNode("A");
        GraphNode graphNodeB = new GraphNode("B");
        graphNodeA.getShortestPath(graphNodeB);
    }

    @Test
    public void shouldGiveMinimumPathBetweenNodeAAndItsNeighbourNodeB() throws NoPathExistException {
        GraphNode graphNodeA = new GraphNode("A");
        GraphNode graphNodeB = new GraphNode("B");
        graphNodeA.connect(graphNodeB);
        List<GraphNode> shortestPath = new ArrayList<>(Arrays.asList(graphNodeA,graphNodeB));
        assertEquals(shortestPath, graphNodeA.getShortestPath(graphNodeB));
    }
}
