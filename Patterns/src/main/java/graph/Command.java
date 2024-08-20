package graph;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public interface Command {
    void execute(Graph graph, Scanner scanner);
}

class MakeAllConnectionsOneWayCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        graph.makeAllConnectionsOneWay();
        System.out.println("All connections made one-way.");
    }
}

class MakeAllConnectionsTwoWayCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        graph.makeAllConnectionsTwoWay();
        System.out.println("All connections made two-way.");
    }
}

class ChangeTrainTimeUnitCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        System.out.println("Enter new time unit for the train:");
        int newTimeUnit = scanner.nextInt();
        graph.setTimeUnit(newTimeUnit);
        System.out.println("Train time unit changed to " + newTimeUnit);
    }
}

class CalculateTrainDistanceCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        System.out.println("Enter source city name:");
        String sourceName = scanner.nextLine();
        System.out.println("Enter destination city name:");
        String destinationName = scanner.nextLine();

        Node source = graph.getNodeByName(sourceName);
        Node destination = graph.getNodeByName(destinationName);

        graph.setDistanceStrategy(new TrainStrategy());
        graph.calculateDistance(source);

        System.out.println("Distance from city " + sourceName + " to city " + destinationName + " by train: " + destination.getDistance());
    }
}

class CalculateBusDistanceCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        System.out.println("Enter source city name:");
        String sourceName = scanner.nextLine();
        System.out.println("Enter destination city name:");
        String destinationName = scanner.nextLine();

        Node source = graph.getNodeByName(sourceName);
        Node destination = graph.getNodeByName(destinationName);

        graph.setDistanceStrategy(new BusStrategy());
        graph.calculateDistance(source);

        System.out.println("Distance from city " + sourceName + " to city " + destinationName + " by bus: " + destination.getDistance());
    }
}

class FindFasterRouteCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        System.out.println("Enter source city name:");
        String sourceName = scanner.nextLine();
        System.out.println("Enter destination city name:");
        String destinationName = scanner.nextLine();

        Node source = graph.getNodeByName(sourceName);
        Node destination = graph.getNodeByName(destinationName);

        String result = graph.findFasterStrategy(source, destination);
        System.out.println(result);
    }
}

class CheckPathAvoidingCityCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        System.out.println("Enter source city name:");
        String sourceName = scanner.nextLine();
        System.out.println("Enter destination city name:");
        String destinationName = scanner.nextLine();
        System.out.println("Enter disliked city names separated by whitespace:");
        String dislikedCityNames = scanner.nextLine();

        Node source = graph.getNodeByName(sourceName);
        Node destination = graph.getNodeByName(destinationName);

        // Convert dislikedCityNames to a List<Node>
        List<Node> dislikedCities = Arrays.stream(dislikedCityNames.split("\\s+"))
                .map(graph::getNodeByName)
                .collect(Collectors.toList());

        boolean pathExists = graph.isPathPossibleWithoutDislikedCities(source, destination, dislikedCities);

        if (pathExists) {
            System.out.println("It is possible to reach city " + destinationName + " without going through the disliked cities: " + dislikedCityNames + " from " + sourceName + ".");
        } else {
            System.out.println("It is not possible to reach city " + destinationName + " without going through the disliked cities: " + dislikedCityNames + " from " + sourceName + ".");
        }
    }
}
