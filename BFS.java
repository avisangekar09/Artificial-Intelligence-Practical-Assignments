//Breadth first search

import java.util.*;

public class BFS {
    private ArrayList<ArrayList<Integer>> adj;

    // Constructor to initialize the graph
    BFS(int v) {
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Method to add an edge to the graph
    void addEdge(int v, int w) {
        adj.get(v).add(w);
    }

    // Method to perform BFS traversal
    void BFS(int s) {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[adj.size()];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If an adjacent has not been visited, then mark it visited and enqueue it
            for (int n : adj.get(s)) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        int vertices = scanner.nextInt();

        BFS graph = new BFS(vertices);

        System.out.println("Enter the number of edges:");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (format: start end):");
        for (int i = 0; i < edges; ++i) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            graph.addEdge(start, end);
        }

        System.out.println("Enter the starting vertex for BFS:");
        int startVertex = scanner.nextInt();

        System.out.println("Breadth First Traversal starting from vertex " + startVertex + ":");
        graph.BFS(startVertex);

        scanner.close();
    }
}
