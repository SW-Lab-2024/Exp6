package graph;

public class NonDirectedState implements EdgeState {
    private final Edge edge;

    public NonDirectedState(Edge edge) {
        this.edge = edge;
    }

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public void setDirected(boolean directed) {
        if (directed) {
            edge.setState(new DirectedState(edge));
            Node nodeB = edge.getNodes().getValue1();
            nodeB.getEdges().remove(edge);  // Remove from second node's edge list when converting to directed
        }
    }
}