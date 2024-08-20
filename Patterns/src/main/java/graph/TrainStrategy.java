package graph;

import lombok.Getter;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class TrainStrategy implements DistanceStrategy {

    @Getter
    private static final String name = "Train Strategy";

    @Override
    public void calculateDistance(Graph graph, Node source) {
        graph.resetVisits();
        bfs(source, graph.getTimeUnit());
    }

    private void bfs(Node source, int timeUnit) {
        Queue<Pair<Node, Integer>> nodes = new LinkedList<>();
        nodes.add(new Pair<>(source, 0));
        while (!nodes.isEmpty()) {
            Pair<Node, Integer> front = nodes.poll();
            Node frontNode = front.getValue0();
            if (!frontNode.isVisited()) {
                frontNode.setVisited(true);
                int distance = front.getValue1();
                frontNode.setDistance(distance);
                nodes.addAll(frontNode.getAvailableNeighbors()
                        .stream()
                        .map(neighbor -> new Pair<>(neighbor, distance + timeUnit))
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        }
    }
}
