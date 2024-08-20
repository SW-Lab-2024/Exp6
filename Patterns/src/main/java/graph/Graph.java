package graph;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;

public class Graph {
    @Getter
    private ArrayList<Node> graph;
    @Setter
    private DistanceStrategy distanceStrategy;


    public Graph(ArrayList<Node> graph) {
        this.graph = graph;
    }

    public void resetVisits() {
        for (Node v : this.getGraph()) {
            v.setVisited(false);
            v.setDistance(Integer.MAX_VALUE);
        }
    }

    public void calculateDistance(Node source) {
        if (distanceStrategy != null) {
            distanceStrategy.calculateDistance(this, source);
        } else {
            throw new IllegalStateException("Distance strategy not set.");
        }
    }

    public void makeAllConnectionsOneWay() {
        setDirectionForAllEdges(true);
    }

    public void makeAllConnectionsTwoWay() {
        setDirectionForAllEdges(false);
    }

    private void setDirectionForAllEdges(boolean makeDirected) {
        HashSet<Edge> visitedEdges = new HashSet<>();

        for (Node node : this.getGraph()) {
            for (Edge edge : node.getEdges()) {
                if (!visitedEdges.contains(edge)) {
                    if (makeDirected && !edge.isDirected()) {
                        edge.setDirected(true);
                    } else if (!makeDirected && edge.isDirected()) {
                        edge.setDirected(false);
                    }
                    visitedEdges.add(edge);
                }
            }
        }
    }
}
