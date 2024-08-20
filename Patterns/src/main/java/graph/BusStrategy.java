package graph;

import lombok.Getter;
import org.javatuples.Pair;

import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class BusStrategy implements DistanceStrategy {

    @Getter
    private final String name = "Bus Strategy";

    @Override
    public void calculateDistance(Graph graph, Node source) {
        graph.resetVisits();
        dijkstra(source);
    }

    private void dijkstra(Node s) {
        PriorityQueue<Pair<Integer, Node>> nodes = new PriorityQueue<>();
        nodes.add(new Pair<>(0, s));
        while (!nodes.isEmpty()) {
            Pair<Integer, Node> front = nodes.poll();
            Node frontNode = front.getValue1();
            if (!frontNode.isVisited()) {
                frontNode.setVisited(true);
                int distance = front.getValue0();
                frontNode.setDistance(distance);
                nodes.addAll(frontNode.getAvailableWeightedNeighbors()
                        .stream()
                        .map(neighbor -> new Pair<>(neighbor.getValue1() + distance,
                                neighbor.getValue0()))
                        .collect(Collectors.toCollection(PriorityQueue::new)));
            }
        }
    }
}
