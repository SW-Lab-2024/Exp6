package graph;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Graph {
    @Getter
    private ArrayList<Node> graph;
    @Setter
    private DistanceStrategy distanceStrategy;
    @Setter
    @Getter
    private int timeUnit = 1;


    public Graph(ArrayList<Node> graph) {
        this.graph = graph;
    }

    public Node getNodeByName(String name) {
        for (Node node : graph) {
            if (node.getName().equals(name))
                return node;
        }
        return null;
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

    public String findFasterStrategy(Node source, Node destination) {
        // Save the current DistanceStrategy of the class
        DistanceStrategy temp = distanceStrategy;

        // Calculate distance using TrainStrategy (BFS)
        this.setDistanceStrategy(new TrainStrategy());
        this.calculateDistance(source);
        int trainDistance = destination.getDistance();

        // Calculate distance using BusStrategy (Dijkstra)
        this.setDistanceStrategy(new BusStrategy());
        this.calculateDistance(source);
        int busDistance = destination.getDistance();

        // Back to the last state before the calling this method
        setDistanceStrategy(temp);

        // Compare the distance and return the best strategy
        if (trainDistance < busDistance) {
            return TrainStrategy.getName();
        } else if (busDistance < trainDistance) {
            return BusStrategy.getName();
        } else {
            return "Both strategy are equally fast";
        }
    }

    public boolean isPathPossibleWithoutDislikedCities(Node source, Node destination, List<Node> dislikedCities) {
        this.resetVisits();

        // Mark disliked cities as visited
        for (Node dislikedCity : dislikedCities) {
            dislikedCity.setVisited(true);
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(source);
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();
            if (current.equals(destination)) {
                return true;
            }
            if (!current.isVisited()) {
                current.setVisited(true);
                nodes.addAll(current.getAvailableNeighbors());
            }
        }
        return false;
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
