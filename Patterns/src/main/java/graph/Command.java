package graph;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        String sourceName = scanner.next();
        System.out.println("Enter destination city name:");
        String destinationName = scanner.next();

        Node source = graph.getNodeByName(sourceName);
        if (source == null) {
            System.out.println("Source does not exist.");
            return;
        }
        Node destination = graph.getNodeByName(destinationName);
        if (destination == null) {
            System.out.println("Destination does not exist.");
            return;
        }
        graph.setDistanceStrategy(new TrainStrategy());
        graph.calculateDistance(source);

        System.out.println("Distance from city " + sourceName + " to city " + destinationName + " by train: " + destination.getDistance());
    }
}

class CalculateBusDistanceCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        System.out.println("Enter source city name:");
        String sourceName = scanner.next();
        System.out.println("Enter destination city name:");
        String destinationName = scanner.next();

        Node source = graph.getNodeByName(sourceName);
        if (source == null) {
            System.out.println("Source does not exist.");
            return;
        }
        Node destination = graph.getNodeByName(destinationName);
        if (destination == null) {
            System.out.println("Destination does not exist.");
            return;
        }

        graph.setDistanceStrategy(new BusStrategy());
        graph.calculateDistance(source);

        System.out.println("Distance from city " + sourceName + " to city " + destinationName + " by bus: " + destination.getDistance());
    }
}

class FindFasterRouteCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        System.out.println("Enter source city name:");
        String sourceName = scanner.next();
        System.out.println("Enter destination city name:");
        String destinationName = scanner.next();

        Node source = graph.getNodeByName(sourceName);
        if (source == null) {
            System.out.println("Source does not exist.");
            return;
        }
        Node destination = graph.getNodeByName(destinationName);
        if (destination == null) {
            System.out.println("Destination does not exist.");
            return;
        }

        String result = graph.findFasterStrategy(source, destination);
        System.out.println(result);
    }
}

class CheckPathAvoidingCityCommand implements Command {
    @Override
    public void execute(Graph graph, Scanner scanner) {
        scanner.nextLine();

        System.out.println("Enter source city name:");
        String sourceName = scanner.nextLine();

        System.out.println("Enter destination city name:");
        String destinationName = scanner.nextLine();

        System.out.println("Enter disliked city names separated by whitespace:");
        String dislikedCityNames = scanner.nextLine();

        Node source = graph.getNodeByName(sourceName);
        Node destination = graph.getNodeByName(destinationName);

        if (source == null) {
            System.out.println("Source city " + sourceName + " doesn't exist.");
            return;
        }
        if (destination == null) {
            System.out.println("Destination city " + destinationName + " doesn't exist.");
            return;
        }

        List<Node> dislikedCities = Arrays.stream(dislikedCityNames.split("\\s+"))
                .map(name -> {
                    Node node = graph.getNodeByName(name);
                    if (node == null) {
                        System.out.println("Disliked city " + name + " doesn't exist.");
                    }
                    return node;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        boolean pathExists = graph.isPathPossibleWithoutDislikedCities(source, destination, dislikedCities);

        if (pathExists) {
            System.out.println("It is possible to reach city " + destinationName + " from " + sourceName + " without going through the disliked cities: " + dislikedCityNames + ".");
        } else {
            System.out.println("It is not possible to reach city " + destinationName + " from " + sourceName + " without going through the disliked cities: " + dislikedCityNames + ".");
        }
    }
}