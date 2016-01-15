import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GraphTracerTest {
    private List<Node> graph;
    private GraphTracer graphTracer;


    @Test
    public void shouldReturnTrueForAdjacentNodes() throws Exception {
        Node node1 = Mockito.mock(Node.class);
        graph = new ArrayList<>(Arrays.asList(node1));
        graphTracer = new GraphTracer();
        assertTrue(graphTracer.hasPath(node1,node1));
    }

    @Test
    public void shouldReturnTrueIfNode1IsReachableToNode2() throws Exception {
        Node node1 = Mockito.mock(Node.class);
        Node node2 = Mockito.mock(Node.class);

        when(node1.getNeighbours()).thenReturn(new ArrayList<>(Arrays.asList(node2)));

        graph = new ArrayList<>(Arrays.asList(node1,node2));
        graphTracer = new GraphTracer();
        assertTrue(graphTracer.hasPath(node1,node2));

        verify(node1).getNeighbours();
    }

    @Test
    public void shouldReturnTrueIfNeighboursNeighbourIsReached() throws Exception {
        Node node1 = Mockito.mock(Node.class);
        Node node2 = Mockito.mock(Node.class);
        Node node3 = Mockito.mock(Node.class);

        when(node1.getNeighbours()).thenReturn(new ArrayList<>(Arrays.asList(node2)));
        when(node2.getNeighbours()).thenReturn(new ArrayList<>(Arrays.asList(node3)));

        graph = new ArrayList<>(Arrays.asList(node1,node3));
        graphTracer = new GraphTracer();
        assertTrue(graphTracer.hasPath(node1,node3));

    }


}
