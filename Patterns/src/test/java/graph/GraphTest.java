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
    private Node cityC;

    @BeforeEach
    public void setUp() {
        cityA = new Node();
        cityB = new Node();
        cityC = new Node();
        graph = new Graph(new ArrayList<>(List.of(cityA, cityB, cityC)));

        // Set up the edges
        Edge.createEdge(cityA, cityB, false, 5); // Edge A-B with weight 5
        Edge.createEdge(cityB, cityC, false, 10); // Edge B-C with weight 10
        Edge.createEdge(cityA, cityC, false, 20); // Edge A-C with weight 20
    }

    @Test
    public void testOneWayConnections() {
        Edge edge = Edge.createEdge(cityA, cityB, false, 5);

        Assertions.assertFalse(edge.isDirected());
        Assertions.assertEquals(edge.getNodes().getValue0(), cityA);
        Assertions.assertEquals(edge.getNodes().getValue1(), cityB);

        Assertions.assertFalse(edge.isDirected());
        Assertions.assertEquals(edge.getNodes().getValue0(), cityA);
        Assertions.assertEquals(edge.getNodes().getValue1(), cityB);

        graph.makeAllConnectionsOneWay();
        // After the operation, CityA should have a directed edge to CityB, but CityB should not have a directed edge back to CityA

        Assertions.assertTrue(edge.isDirected());
        Assertions.assertEquals(edge.getNodes().getValue0(), cityA);
        Assertions.assertEquals(edge.getNodes().getValue1(), cityB);
        Assertions.assertFalse(cityB.getEdges().contains(edge));
    }

    @Test
    public void testTwoWayConnections() {
        Edge edge = Edge.createEdge(cityA, cityB, true, 5);

        Assertions.assertTrue(edge.isDirected());
        Assertions.assertEquals(edge.getNodes().getValue0(), cityA);
        Assertions.assertEquals(edge.getNodes().getValue1(), cityB);
        Assertions.assertFalse(cityB.getEdges().contains(edge));

        graph.makeAllConnectionsTwoWay();
        // After the operation, CityA and CityB should have an undirected edge to each other

        Assertions.assertFalse(edge.isDirected());
        Assertions.assertEquals(edge.getNodes().getValue0(), cityA);
        Assertions.assertEquals(edge.getNodes().getValue1(), cityB);

        Assertions.assertFalse(edge.isDirected());
        Assertions.assertEquals(edge.getNodes().getValue0(), cityA);
        Assertions.assertEquals(edge.getNodes().getValue1(), cityB);
    }

    @Test
    public void testWhenTrainStrategyIsFaster() {
        String result = graph.findFasterStrategy(cityA, cityC, 1);

        // Since TrainStrategy should reach C directly with 1 unit per step, it should be faster.
        Assertions.assertEquals("Train Strategy", result);
    }

    @Test
    public void testWhenBusStrategyIsFaster() {
        String result = graph.findFasterStrategy(cityA, cityC, 20);

        // Since TrainStrategy is slow (15 units per step), BusStrategy (using Dijkstra) should be faster
        Assertions.assertEquals("Bus Strategy", result);
    }

    @Test
    public void testWhenBothStrategyAreEquallyFast() {
        String result = graph.findFasterStrategy(cityA, cityC, 15);

        // Since both strategies now should produce equal distances, the result should be "Both are equally fast"
        Assertions.assertEquals("Both strategy are equally fast", result);
    }
}
