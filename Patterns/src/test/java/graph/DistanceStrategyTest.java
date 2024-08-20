package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceStrategyTest {

    private Node cityA;
    private Node cityB;
    private Node cityC;
    private Graph graph;

    @BeforeEach
    void setUp() {
        cityA = new Node();
        cityB = new Node();
        cityC = new Node();

        graph = new Graph(new ArrayList<>(List.of(cityA, cityB, cityC)));

//      The graph:
//          A ------ 20 ------ C
//          |                  |
//           -- 5 -- B -- 10 --
        Edge.createEdge(cityA, cityB, false, 5); // Edge A-B with weight 5
        Edge.createEdge(cityB, cityC, false, 10); // Edge B-C with weight 10
        Edge.createEdge(cityA, cityC, false, 20); // Edge A-C with weight 20
    }

    @Test
    void testTrainDistanceUsingBFS() {
        graph.setDistanceStrategy(new TrainStrategy());
        graph.calculateDistance(cityA);

        // Using BFS, distance from cityA to cityB and cityC should be 1 * time unit (regardless of weights)
        assertEquals(1, cityB.getDistance());
        assertEquals(1, cityC.getDistance());
    }

    @Test
    void testBusDistanceUsingDijkstra() {
        graph.setDistanceStrategy(new BusStrategy());
        graph.calculateDistance(cityA);

        // Using Dijkstra, distance from cityA to cityB should be 5 and from cityA to cityC should be 15 via cityB
        assertEquals(5, cityB.getDistance());
        assertEquals(15, cityC.getDistance()); // A -> B -> C should be the shortest path
    }
}
