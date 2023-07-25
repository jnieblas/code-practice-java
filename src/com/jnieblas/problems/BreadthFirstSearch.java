package src.com.jnieblas.problems;

import java.util.*;

public class BreadthFirstSearch {
    private final int vertices; // Number of vertices
    private final LinkedList<Integer>[] adjacencyList; // Adjacency list representation of the graph

    @SuppressWarnings("unchecked")
    public BreadthFirstSearch(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // Function to add an edge to the graph
    public void addEdge(int from, int to) {
        adjacencyList[from].add(to);
    }

    /*
      Gets source node's adjacency list, then visits each node by adding them to the queue.
      Since queue is FIFO, the first found nodes are explored before the next. So, intentionally,
      the queue approach will visit each node in a level before going to the next level.
     */
    public void search(int sourceNode) {
        boolean[] visited = new boolean[vertices]; // To keep track of visited nodes
        LinkedList<Integer> queue = new LinkedList<>(); // Queue for BFS

        visited[sourceNode] = true;
        queue.add(sourceNode);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.println("current: " + current);

            // Visit all neighbors of the current node
            LinkedList<Integer> currentAdjacentNodes = adjacencyList[current];
            System.out.println("adjacent nodes: " + currentAdjacentNodes);
            for (int neighbor : currentAdjacentNodes) {
                System.out.println("visiting neighbor: " + neighbor);
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        BreadthFirstSearch graph = new BreadthFirstSearch(7);

        // Adding edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        System.out.println("BFS traversal starting from node 0:");
        graph.search(0);


        System.out.println("BFS traversal starting from node 2:");
        graph.search(2);
    }
}
