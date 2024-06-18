//Best first search 


import java.util.*;

class Node {
    int vertex;
    int heuristic;

    Node(int vertex, int heuristic) {
        this.vertex = vertex;
        this.heuristic = heuristic;
    }
}

public class BestFirstSearch {
    private ArrayList<ArrayList<Node>> adj;
    private int[] heuristicValues;

    // Constructor to initialize the graph
    BestFirstSearch(int v) {
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
        heuristicValues = new int[v];
    }

    // Method to add an edge to the graph
    void addEdge(int v, int w) {
        adj.get(v).add(new Node(w, heuristicValues[w]));
    }

    // Method to perform Best First Search traversal
    void bestFirstSearch(int start) {
        boolean[] visited = new boolean[adj.size()];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));

        // Add the starting node to the priority queue
        pq.add(new Node(start, heuristicValues[start]));
        visited[start] = true;

        while (!pq.isEmpty()) {
            // Get the node with the lowest heuristic value
            Node current = pq.poll();
            int vertex = current.vertex;
            System.out.print(vertex + " ");

            // Iterate over the adjacent nodes
            for (Node neighbor : adj.get(vertex)) {
                if (!visited[neighbor.vertex]) {
                    visited[neighbor.vertex] = true;
                    pq.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        int vertices = scanner.nextInt();

        BestFirstSearch graph = new BestFirstSearch(vertices);

        System.out.println("Enter the heuristic values for each vertex:");
        for (int i = 0; i < vertices; i++) {
            graph.heuristicValues[i] = scanner.nextInt();
        }

        System.out.println("Enter the number of edges:");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (format: start end):");
        for (int i = 0; i < edges; ++i) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            graph.addEdge(start, end);
        }

        System.out.println("Enter the starting vertex for Best First Search:");
        int startVertex = scanner.nextInt();

        System.out.println("Best First Traversal starting from vertex " + startVertex + ":");
        graph.bestFirstSearch(startVertex);

        scanner.close();
    }
}
