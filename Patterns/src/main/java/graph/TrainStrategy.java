package graph;

import lombok.Getter;
import lombok.Setter;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class TrainStrategy implements DistanceStrategy {

    @Getter
    @Setter
    private int timeUnit = 1;

    @Override
    public void calculateDistance(Graph graph, Node source) {
        graph.resetVisits();
        bfs(source);
    }

    private void bfs(Node source) {
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
                        .map(neighbor -> new Pair<>(neighbor, distance + this.getTimeUnit()))
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        }
    }
}
