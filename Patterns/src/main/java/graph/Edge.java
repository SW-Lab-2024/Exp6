package graph;

import lombok.Getter;
import lombok.Setter;
import org.javatuples.Pair;

public class Edge {
    @Getter
    private final Pair<Node, Node> nodes;
    @Setter
    @Getter
    private int weight;
    @Setter
    private EdgeState state;

    private Edge(Node a, Node b, boolean directed, int weight) {
        nodes = new Pair<>(a, b);
        this.weight = weight;
        this.state = directed ? new DirectedState(this) : new NonDirectedState(this);
    }

    public static void createEdge(Node a, Node b, boolean directed, int weight) {
        Edge newEdge = new Edge(a, b, directed, weight);
        a.getEdges().add(newEdge);
        if (!directed) {
            b.getEdges().add(newEdge);  // Non-directed edge should be added to both nodes' edge lists
        }
    }

    public boolean isDirected() {
        return state.isDirected();
    }

    public void setDirected(boolean directed) {
        state.setDirected(directed);
    }

}
