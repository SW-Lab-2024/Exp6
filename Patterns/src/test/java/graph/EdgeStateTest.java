package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EdgeStateTest {

    private Node cityA;
    private Node cityB;
    private Edge edge;

    @BeforeEach
    void setUp() {
        cityA = new Node();
        cityB = new Node();
        edge = Edge.createEdge(cityA, cityB, false, 5); // Initially non-directed
    }

    @Test
    void testInitialNonDirectedState() {
        assertFalse(edge.isDirected());
        assertTrue(cityA.getEdges().contains(edge));
        assertTrue(cityB.getEdges().contains(edge));
    }

    @Test
    void testTransitionToDirectedState() {
        edge.setDirected(true);
        assertTrue(edge.isDirected());
        assertTrue(cityA.getEdges().contains(edge));
        assertFalse(cityB.getEdges().contains(edge));  // Should be removed from cityB's edges
    }

    @Test
    void testTransitionBackToNonDirectedState() {
        edge.setDirected(true); // Directed first
        edge.setDirected(false); // Then back to non-directed

        assertFalse(edge.isDirected());
        assertTrue(cityA.getEdges().contains(edge));
        assertTrue(cityB.getEdges().contains(edge)); // Should be added back to cityB's edges
    }

    @Test
    void testMultipleEdgesTransition() {
        Node cityC = new Node();
        Edge edge2 = Edge.createEdge(cityB, cityC, false, 7);
        Edge edge3 = Edge.createEdge(cityA, cityC, false, 10);
        Graph graph = new Graph(new ArrayList<>(List.of(cityA, cityB, cityC)));
        graph.makeAllConnectionsOneWay();

        // Verify all edges are directed correctly
        assertTrue(edge.isDirected());
        assertFalse(cityB.getEdges().contains(edge));
        assertTrue(edge2.isDirected());
        assertFalse(cityC.getEdges().contains(edge2));
        assertTrue(edge3.isDirected());
        assertFalse(cityC.getEdges().contains(edge3));
    }
}
