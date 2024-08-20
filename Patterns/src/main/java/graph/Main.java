package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Node cityA = new Node("A");
        Node cityB = new Node("B");
        Node cityC = new Node("C");
        Node cityD = new Node("D");
        Node cityE = new Node("E");
        ArrayList<Node> cities = new ArrayList<>();
        cities.add(cityA);
        cities.add(cityB);
        cities.add(cityC);
        cities.add(cityD);
        cities.add(cityE);
        Graph graph = new Graph(cities);
        //      The graph:
        //           ---------- 20 -----------
        //          |                         |
        //          A --- 20 --- C --- 15 --- D
        //          |            | \
        //          5           10  \
        //          |            |   -- 10 -- E
        //           ----- B ----
        Edge.createEdge(cityA, cityB, false, 5); // Edge A-B with weight 5
        Edge.createEdge(cityB, cityC, false, 10); // Edge B-C with weight 10
        Edge.createEdge(cityA, cityC, false, 20); // Edge A-C with weight 20
        Edge.createEdge(cityA, cityD, false, 20); // Edge A-D with weight 20
        Edge.createEdge(cityC, cityD, false, 15); // Edge C-D with weight 15
        Edge.createEdge(cityC, cityE, false, 10); // Edge C-E with weight 10

        System.out.println("\n");
        System.out.println("-- Welcome to the Graph Application --");
        System.out.println("    The graph:");
        System.out.println("        ---------- 20 -----------");
        System.out.println("        |                         |");
        System.out.println("        A --- 20 --- C --- 15 --- D");
        System.out.println("        |            | \\");
        System.out.println("        5           10  \\");
        System.out.println("        |            |   -- 10 -- E");
        System.out.println("        ----- B ----");


        // Map to store commands
        Map<Integer, Command> commandMap = new HashMap<>();
        commandMap.put(1, new MakeAllConnectionsOneWayCommand());
        commandMap.put(2, new MakeAllConnectionsTwoWayCommand());
        commandMap.put(3, new ChangeTrainTimeUnitCommand());
        commandMap.put(4, new CalculateTrainDistanceCommand());
        commandMap.put(5, new CalculateBusDistanceCommand());
        commandMap.put(6, new FindFasterRouteCommand());
        commandMap.put(7, new CheckPathAvoidingCityCommand());

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1: Make all connections one-way");
            System.out.println("2: Make all connections two-way");
            System.out.println("3: Change train time unit");
            System.out.println("4: Calculate train distance from source city to destination city");
            System.out.println("5: Calculate bus distance from source city to destination city");
            System.out.println("6: Find faster strategy to travel from source city to destination city");
            System.out.println("7: Check if there is a path from source city to destination city without going through disliked cities");
            System.out.println("8: Exit");

            int choice = scanner.nextInt();

            if (choice == 8) {
                break;
            }

            Command command = commandMap.get(choice);
            if (command != null) {
                command.execute(graph, scanner);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
