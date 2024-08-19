package graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class GraphTest {
    private Graph graph;
    private Node cityA;
    private Node cityB;

    @BeforeEach
    public void setUp() {
        cityA = new Node();
        cityB = new Node();
        graph = new Graph(new ArrayList<>(List.of(cityA, cityB)));
    }

    @Test
    public void testOneWayConnections() {
        Edge.createEdge(cityA, cityB, false, 5);

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

    @Test
    public void testTwoWayConnections() {
        Edge.createEdge(cityA, cityB, true, 5);

        Assertions.assertTrue(cityA.getEdges().get(0).isDirected());
        Assertions.assertEquals(cityA.getEdges().get(0).getNodes().getValue0(), cityA);
        Assertions.assertEquals(cityA.getEdges().get(0).getNodes().getValue1(), cityB);
        Assertions.assertTrue(cityB.getEdges().isEmpty());

        graph.makeAllConnectionsTwoWay();
        // After the operation, CityA and CityB should have an undirected edge to each other

        Assertions.assertFalse(cityA.getEdges().get(0).isDirected());
        Assertions.assertEquals(cityA.getEdges().get(0).getNodes().getValue0(), cityA);
        Assertions.assertEquals(cityA.getEdges().get(0).getNodes().getValue1(), cityB);

        Assertions.assertFalse(cityB.getEdges().get(0).isDirected());
        Assertions.assertEquals(cityB.getEdges().get(0).getNodes().getValue0(), cityA);
        Assertions.assertEquals(cityB.getEdges().get(0).getNodes().getValue1(), cityB);
    }

}