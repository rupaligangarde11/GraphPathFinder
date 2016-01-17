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
        graphNode = new GraphNode("A");;

        assertTrue(graphNode.hasPath(graphNode));
    }

    @Test
    public void shouldReturnTrueIfNode1IsReachableToNode2() throws Exception {
        graphNode = new GraphNode("A");
        GraphNode graphnode2 = new GraphNode("B");
        graphNode.connect(graphnode2);

        assertTrue(graphNode.hasPath(graphnode2));

    }

    @Test
    public void shouldReturnTrueIfNeighboursNeighbourIsReached() throws Exception {

        graphNode = new GraphNode("A");
        GraphNode graphnode3 = new GraphNode("B");
        GraphNode graphnode2 = new GraphNode("C");
        graphNode.connect(graphnode2);
        graphNode.connect(graphnode3);
        assertTrue(graphNode.hasPath(graphnode3));

    }

    @Test
    public void shouldReturnFalseWhenNodeIsNotReachable() throws Exception {

        GraphNode graphnode3 = new GraphNode("3");
        GraphNode graphnode2 = new GraphNode("2");
        graphNode = new GraphNode("1");
        graphNode.connect(graphnode3);
        assertFalse(graphnode2.hasPath(graphnode3));

    }

    @Test
    public void shouldNotEnterCycle() throws Exception {

        GraphNode graphnode3 = new GraphNode("3");
        GraphNode graphnode2 = new GraphNode("2");
        graphNode = new GraphNode("1");

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
    @Test
    public void shouldGiveMinimumPathBetweenNodeAAndNodeD() throws NoPathExistException {
        GraphNode graphNodeA = new GraphNode("A");
        GraphNode graphNodeB = new GraphNode("B");
        GraphNode graphNodeC = new GraphNode("C");
        GraphNode graphNodeD = new GraphNode("D");

        graphNodeA.connect(graphNodeB);
        graphNodeB.connect(graphNodeC);
        graphNodeC.connect(graphNodeD);
        List<GraphNode> shortestPath =
                new ArrayList<>(Arrays.asList(graphNodeA,graphNodeB,graphNodeC,graphNodeD));

        assertEquals(shortestPath, graphNodeA.getShortestPath(graphNodeD));
    }

    @Test
    public void shouldGiveMinimumPathBetweenNodeAAndNodeDWithCycle() throws NoPathExistException {
        GraphNode graphNodeA = new GraphNode("A");
        GraphNode graphNodeB = new GraphNode("B");
        GraphNode graphNodeC = new GraphNode("C");
        GraphNode graphNodeD = new GraphNode("D");
        GraphNode graphNodeE = new GraphNode("E");

        graphNodeA.connect(graphNodeB);
        graphNodeB.connect(graphNodeC);
        graphNodeC.connect(graphNodeD);
        graphNodeA.connect(graphNodeE);
        graphNodeE.connect(graphNodeD);
        graphNodeC.connect(graphNodeA);
        List<GraphNode> shortestPath =
                new ArrayList<>(Arrays.asList(graphNodeA,graphNodeE,graphNodeD));

        assertEquals(shortestPath, graphNodeA.getShortestPath(graphNodeD));
    }
}
