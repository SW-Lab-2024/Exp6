package graph;

import org.javatuples.Pair;

import java.util.LinkedList;
import java.util.Queue;

public interface DistanceStrategy {
    void calculateDistance(Graph graph, Node source);
}

