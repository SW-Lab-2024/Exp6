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
    private Node cityD;
    private Node cityE;

    @BeforeEach
    public void setUp() {
        cityA = new Node();
        cityB = new Node();
        cityC = new Node();
        cityD = new Node();
        cityE = new Node();
        graph = new Graph(new ArrayList<>(List.of(cityA, cityB, cityC, cityD, cityE)));

        //      The graph:
        //           ---------- 20 -----------
        //          |                         |
        //          A --- 20 --- C --- 15 --- D
        //          |            | \
        //          5           10  \
        //          |            |   -- 10 -- E
        //           ----- B ----
        Edge.createEdge(cityA, cityB, false, 5); // Edge A-B with weight 5
        Edge.createEdge(cityB, cityC, false, 10); // Edge B-C with weight 10
        Edge.createEdge(cityA, cityC, false, 20); // Edge A-C with weight 20
        Edge.createEdge(cityA, cityD, false, 20); // Edge A-D with weight 20
        Edge.createEdge(cityC, cityD, false, 15); // Edge C-D with weight 15
        Edge.createEdge(cityC, cityE, false, 10); // Edge C-E with weight 10
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
        graph.setTimeUnit(1);
        String result = graph.findFasterStrategy(cityA, cityC);

        // Since TrainStrategy should reach C directly with 1 unit per step, it should be faster.
        Assertions.assertEquals("Train Strategy", result);
    }

    @Test
    public void testWhenBusStrategyIsFaster() {
        graph.setTimeUnit(20);
        String result = graph.findFasterStrategy(cityA, cityC);

        // Since TrainStrategy is slow (15 units per step), BusStrategy (using Dijkstra) should be faster
        Assertions.assertEquals("Bus Strategy", result);
    }

    @Test
    public void testWhenBothStrategyAreEquallyFast() {
        graph.setTimeUnit(15);
        String result = graph.findFasterStrategy(cityA, cityC);

        // Since both strategies now should produce equal distances, the result should be "Both are equally fast"
        Assertions.assertEquals("Both strategy are equally fast", result);
    }

    @Test
    public void testPathPossibleWithoutDislikedCity() {
        // City C is disliked
        List<Node> dislikedCities = List.of(cityC);

        // Test path from A to D without going through C
        boolean pathExists = graph.isPathPossibleWithoutDislikedCities(cityA, cityD, dislikedCities);

        // A -> B -> C -> D and A -> C -> D should be avoided, A -> D should be taken directly
        Assertions.assertTrue(pathExists);
    }

    @Test
    public void testPathPossibleWithoutDislikedCityButDirectedWay() {
        // City A is disliked
        List<Node> dislikedCities = List.of(cityA);

        // Make the path between D and C one way
        Edge edge = cityC.getEdgeByNeighbor(cityD);
        Assertions.assertNotNull(edge);
        edge.setDirected(true);
        // C -> D (Accepted) | D -> C (Not Accepted)

        // Test path from D to E without going through C
        boolean pathExists = graph.isPathPossibleWithoutDislikedCities(cityD, cityE, dislikedCities);

        // D -> A -> B -> C -> E should be avoided because of A and D -> C -> E is not permitted because of D -> C
        Assertions.assertFalse(pathExists);
    }

    @Test
    public void testPathNotPossibleDueToDislikedCity() {
        // City C is disliked, making it impossible to reach A from E
        List<Node> dislikedCities = List.of(cityC);

        // Test path from A to E without going through C
        boolean pathExists = graph.isPathPossibleWithoutDislikedCities(cityA, cityE, dislikedCities);

        // No path from A to E is possible without going through C
        Assertions.assertFalse(pathExists);
    }

    @Test
    public void testPathPossibleWithNoDislikedCities() {
        // No cities are disliked, should find a path from A to E
        List<Node> dislikedCities = List.of();

        // Test path from A to E with no disliked cities
        boolean pathExists = graph.isPathPossibleWithoutDislikedCities(cityA, cityE, dislikedCities);

        // A -> C -> E should be taken
        Assertions.assertTrue(pathExists);
    }

    @Test
    public void testPathNotPossibleWithAllDislikedCities() {
        // All cities except A are disliked, making it impossible to reach D
        List<Node> dislikedCities = List.of(cityB, cityC, cityD, cityE);

        // Test path from A to D when all cities are disliked
        boolean pathExists = graph.isPathPossibleWithoutDislikedCities(cityA, cityD, dislikedCities);

        // No path should be possible
        Assertions.assertFalse(pathExists);
    }
}
