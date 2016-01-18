import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        GraphNode graphNode2 = new GraphNode("B");
        graphNode.connect(graphNode2, 1);

        assertTrue(graphNode.hasPath(graphNode2));

    }

    @Test
    public void shouldReturnTrueIfNeighboursNeighbourIsReached() throws Exception {

        graphNode = new GraphNode("A");
        GraphNode graphnode3 = new GraphNode("B");
        GraphNode graphnode2 = new GraphNode("C");
        graphNode.connect(graphnode2, 1);
        graphNode.connect(graphnode3, 1);
        assertTrue(graphNode.hasPath(graphnode3));

    }

    @Test
    public void shouldReturnFalseWhenNodeIsNotReachable() throws Exception {

        GraphNode graphnode3 = new GraphNode("3");
        GraphNode graphnode2 = new GraphNode("2");
        graphNode = new GraphNode("1");
        graphNode.connect(graphnode3, 1);
        assertFalse(graphnode2.hasPath(graphnode3));

    }

    @Test
    public void shouldNotEnterCycle() throws Exception {

        GraphNode graphnode3 = new GraphNode("3");
        GraphNode graphnode2 = new GraphNode("2");
        graphNode = new GraphNode("1");

        graphNode.connect(graphnode2, 1);
        graphnode2.connect(graphNode, 1);
        graphnode2.connect(graphnode3, 1);
        assertTrue(graphNode.hasPath(graphnode3));
    }


    @Test
    public void shouldGiveShortestPathForsameNode() throws NoPathExistException {
        GraphNode graphNode = new GraphNode("a");
        assertEquals(0, graphNode.getShortestPath(graphNode));
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
        graphNodeA.connect(graphNodeB,1);
        assertEquals(1, graphNodeA.getShortestPath(graphNodeB));
    }
    @Test
    public void shouldGiveMinimumPathBetweenNodeAAndNodeD() throws NoPathExistException {
        GraphNode graphNodeA = new GraphNode("A");
        GraphNode graphNodeB = new GraphNode("B");
        GraphNode graphNodeC = new GraphNode("C");
        GraphNode graphNodeD = new GraphNode("D");

        graphNodeA.connect(graphNodeB, 10);
        graphNodeB.connect(graphNodeC, 15);
        graphNodeC.connect(graphNodeD, 10);
        assertEquals(35, graphNodeA.getShortestPath(graphNodeD));
    }

    @Test
    public void shouldGiveMinimumPathBetweenNodeAAndNodeDWithCycle() throws NoPathExistException {
        GraphNode graphNodeA = new GraphNode("A");
        GraphNode graphNodeB = new GraphNode("B");
        GraphNode graphNodeC = new GraphNode("C");
        GraphNode graphNodeD = new GraphNode("D");
        GraphNode graphNodeE = new GraphNode("E");

        graphNodeA.connect(graphNodeB, 2);
        graphNodeB.connect(graphNodeC, 3);
        graphNodeC.connect(graphNodeD, 4);
        graphNodeA.connect(graphNodeE, 1);
        graphNodeE.connect(graphNodeD, 2);
        graphNodeC.connect(graphNodeA, 5);
        assertEquals(3, graphNodeA.getShortestPath(graphNodeD));
    }

    @Test
    public void shoulGive12AsTheOptimalCostForPathBetweenAtoD() throws NoPathExistException {
        GraphNode graphNodeA = new GraphNode("A");
        GraphNode graphNodeB = new GraphNode("B");
        GraphNode graphNodeC = new GraphNode("C");
        GraphNode graphNodeD = new GraphNode("D");

        graphNodeA.connect(graphNodeB,2);
        graphNodeB.connect(graphNodeC,10);
        graphNodeC.connect(graphNodeD,15);
        graphNodeB.connect(graphNodeD,10);
        assertEquals(12, graphNodeA.getShortestPath(graphNodeD));

    }

    @Test
    public void shouldCoverAllTheCasesInTheGivenGraph() throws NoPathExistException {
        GraphNode graphNodeA = new GraphNode("A");
        GraphNode graphNodeB = new GraphNode("B");
        GraphNode graphNodeC = new GraphNode("C");
        GraphNode graphNodeD = new GraphNode("D");
        GraphNode graphNodeE = new GraphNode("E");
        GraphNode graphNodeF = new GraphNode("F");
        GraphNode graphNodeG = new GraphNode("G");
        GraphNode graphNodeH = new GraphNode("H");

        graphNodeH.connect(graphNodeB,1);
        graphNodeB.connect(graphNodeA,1);
        graphNodeA.connect(graphNodeF,1);
        graphNodeB.connect(graphNodeC,1);
        graphNodeC.connect(graphNodeD,1);
        graphNodeD.connect(graphNodeE,1);
        graphNodeE.connect(graphNodeB,1);
        graphNodeC.connect(graphNodeE,5);
        graphNodeC.connect(graphNodeE,4);

        assertEquals(2,graphNodeC.getShortestPath(graphNodeE));
        assertEquals(3,graphNodeB.getShortestPath(graphNodeE));
        assertEquals(2,graphNodeB.getShortestPath(graphNodeF));
        
    }
}
