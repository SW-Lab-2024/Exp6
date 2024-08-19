package graph;

public class DirectedState implements EdgeState {
    private final Edge edge;

    public DirectedState(Edge edge) {
        this.edge = edge;
    }

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public void setDirected(boolean directed) {
        if (!directed) {
            edge.setState(new NonDirectedState(edge));
            Node nodeB = edge.getNodes().getValue1();
            nodeB.getEdges().add(edge);  // Add to both nodes' edge lists when converting to non-directed
        }
    }
}