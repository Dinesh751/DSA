import java.util.*;

/**
 * LeetCode #1971: Find if Path Exists in Graph
 * 
 * Problem Statement:
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1.
 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi]
 * denotes a bi-directional edge between vertex ui and vertex vi.
 * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * 
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 * Given edges and the integers n, source, and destination, return true if there is a valid path from 
 * source to destination, or false otherwise.
 * 
 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * 
 * Example 2:
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 */
public class PathExistInGraph1972 {
    public static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Creates graph as adjacency list
    public static ArrayList<Edge>[] createGraph(int n, int[][] edges) {
        ArrayList<Edge>[] graph = new ArrayList[n];

        // Initialize empty lists for each vertex
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Add edges to the adjacency list (bidirectional)
        for (int[] edge : edges) {
            int src = edge[0], dest = edge[1];
            graph[src].add(new Edge(src, dest));
            graph[dest].add(new Edge(dest, src)); // Bidirectional edge
        }

        return graph;
    }

    // Method 1: DFS approach
    public boolean validPathDFS(int n, int[][] edges, int source, int destination) {
        // Handle edge case - empty graph or source is destination
        if (source == destination) {
            return true;
        }
        
        // Create the graph
        ArrayList<Edge>[] graph = createGraph(n, edges);
        boolean[] visited = new boolean[n];
        
        // Start DFS
        return dfs(graph, source, destination, visited);
    }
    
    private boolean dfs(ArrayList<Edge>[] graph, int current, int destination, boolean[] visited) {
        // Base case: reached the destination
        if (current == destination) {
            return true;
        }
        
        visited[current] = true;
        
        // Check all neighbors
        for (Edge e : graph[current]) {
            if (!visited[e.dest]) {
                if (dfs(graph, e.dest, destination, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // Method 2: BFS approach
    public boolean validPathBFS(int n, int[][] edges, int source, int destination) {
        // Handle edge case - empty graph or source is destination
        if (source == destination) {
            return true;
        }
        
        // Create the graph
        ArrayList<Edge>[] graph = createGraph(n, edges);
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        // Start BFS
        queue.add(source);
        visited[source] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            // Check if we reached destination
            if (current == destination) {
                return true;
            }
            
            // Visit all neighbors
            for (Edge e : graph[current]) {
                if (!visited[e.dest]) {
                    visited[e.dest] = true;
                    queue.add(e.dest);
                }
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        PathExistInGraph1972 solution = new PathExistInGraph1972();
        
        // Test Case 1
        int n1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 0}};
        int source1 = 0;
        int destination1 = 2;
        boolean expected1 = true;
        
        // Test both DFS and BFS approaches
        boolean dfsResult1 = solution.validPathDFS(n1, edges1, source1, destination1);
        boolean bfsResult1 = solution.validPathBFS(n1, edges1, source1, destination1);
        
        System.out.println("Test Case 1:");
        System.out.println("Expected: " + expected1);
        System.out.println("DFS Result: " + dfsResult1);
        System.out.println("BFS Result: " + bfsResult1);
        System.out.println();
        
        // Test Case 2
        int n2 = 6;
        int[][] edges2 = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        int source2 = 0;
        int destination2 = 5;
        boolean expected2 = false;
        
        // Test both DFS and BFS approaches
        boolean dfsResult2 = solution.validPathDFS(n2, edges2, source2, destination2);
        boolean bfsResult2 = solution.validPathBFS(n2, edges2, source2, destination2);
        
        System.out.println("Test Case 2:");
        System.out.println("Expected: " + expected2);
        System.out.println("DFS Result: " + dfsResult2);
        System.out.println("BFS Result: " + bfsResult2);
        System.out.println();
        
        // Test Case 3
        int n3 = 5;
        int[][] edges3 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        int source3 = 0;
        int destination3 = 4;
        boolean expected3 = true;
        
        // Test both DFS and BFS approaches
        boolean dfsResult3 = solution.validPathDFS(n3, edges3, source3, destination3);
        boolean bfsResult3 = solution.validPathBFS(n3, edges3, source3, destination3);
        
        System.out.println("Test Case 3:");
        System.out.println("Expected: " + expected3);
        System.out.println("DFS Result: " + dfsResult3);
        System.out.println("BFS Result: " + bfsResult3);
    }
}
