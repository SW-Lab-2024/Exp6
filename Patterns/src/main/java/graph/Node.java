package graph;

import lombok.Data;
import lombok.Setter;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
public class Node implements Comparable<Node> {
    private final ArrayList<Edge> edges;
    private String name;
    @Setter
    private boolean visited;
    @Setter
    private int distance;

    public Node(String name) {
        edges = new ArrayList<>();
        this.name = name;
    }

    public Edge getEdgeByNeighbor(Node neighbor) {
        for (Edge edge : edges) {
            if (edge.getNodes().contains(neighbor))
                return edge;
        }
        return null;
    }

    public ArrayList<Pair<Node, Integer>> getAvailableWeightedNeighbors() {
        return getEdges()
                .stream()
                .filter(edge -> !edge.isDirected() || edge.getNodes().getValue0() == this)
                .map(edge -> new Pair<Node, Integer>(
                        (edge.getNodes().getValue0().equals(this)) ? edge.getNodes().getValue1()
                                : edge.getNodes().getValue0(),
                        edge.getWeight()))
                .filter(pair -> !pair.getValue0().isVisited())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Node> getAvailableNeighbors() {
        return getAvailableWeightedNeighbors()
                .stream()
                .map(pair -> pair.getValue0())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public int compareTo(Node o) {
        return o.hashCode() - hashCode();
    }
}
