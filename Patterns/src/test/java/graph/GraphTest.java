package graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class GraphTest {

    @Test
    public void testOneWayConnections() {
        Node cityA = new Node();
        Node cityB = new Node();
        Edge.createEdge(cityA, cityB, false, 5);

        Graph graph = new Graph(new ArrayList<>(List.of(cityA, cityB)));

        Assertions.assertFalse(cityA.getEdges().get(0).isDirected());
        Assertions.assertEquals(cityA.getEdges().get(0).getNodes().getValue0(), cityA);
        Assertions.assertEquals(cityA.getEdges().get(0).getNodes().getValue1(), cityB);

        Assertions.assertFalse(cityB.getEdges().get(0).isDirected());
        Assertions.assertEquals(cityB.getEdges().get(0).getNodes().getValue0(), cityA);
        Assertions.assertEquals(cityB.getEdges().get(0).getNodes().getValue1(), cityB);

        graph.makeAllConnectionsOneWay();
        // After the operation, CityA should have a directed edge to CityB, but CityB should not have a directed edge back to CityA

        Assertions.assertTrue(cityA.getEdges().get(0).isDirected());
        Assertions.assertEquals(cityA.getEdges().get(0).getNodes().getValue0(), cityA);
        Assertions.assertEquals(cityA.getEdges().get(0).getNodes().getValue1(), cityB);
        Assertions.assertTrue(cityB.getEdges().isEmpty());
    }

}