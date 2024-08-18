package graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {

    @Test
    public void testOneWayConnections() {
        Node cityA = new Node();
        Node cityB = new Node();
        Edge.createEdge(cityA, cityB, false, 5);

        Graph graph = new Graph(new ArrayList<>(List.of(cityA, cityB)));
        graph.makeAllConnectionsOneWay();

        // CityA should have a directed edge to CityB, but CityB should not have a directed edge back to CityA
        assertTrue(cityA.getEdges().get(0).isDirected());
        assertEquals(cityA.getEdges().get(0).getNodes().getValue0(), cityA);
    }

}