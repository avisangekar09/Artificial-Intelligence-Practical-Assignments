//DFS

import java.util.*;

public class DFS {
    private ArrayList<ArrayList<Integer>> adj;

    // Constructor to initialize the graph
    DFS(int v) {
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Method to add an edge to the graph
    void addEdge(int v, int w) {
        adj.get(v).add(w);
    }

    // A function used by DFS
    void DFSUtil(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        for (int n : adj.get(v)) {
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    // The function to do DFS traversal. It uses recursive DFSUtil()
    void DFS(int v) {
        // Mark all the vertices as not visited (set as false by default in Java)
        boolean visited[] = new boolean[adj.size()];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        int vertices = scanner.nextInt();

        DFS graph = new DFS(vertices);

        System.out.println("Enter the number of edges:");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (format: start end):");
        for (int i = 0; i < edges; ++i) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            graph.addEdge(start, end);
        }

        System.out.println("Enter the starting vertex for DFS:");
        int startVertex = scanner.nextInt();

        System.out.println("Depth First Traversal starting from vertex " + startVertex + ":");
        graph.DFS(startVertex);

        scanner.close();
    }
}
