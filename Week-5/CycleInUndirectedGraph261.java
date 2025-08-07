/**
 * LeetCode #261: Graph Valid Tree
 * 
 * Problem: Given n nodes labeled from 0 to n-1 and a list of undirected edges, 
 * determine if these edges make up a valid tree.
 * 
 * Note: A valid tree is a connected acyclic graph.
 * 
 * Example:
 * Input: n = 5, edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * 
 * Input: n = 5, edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * 
 * Constraints:
 * - 1 <= n <= 2000
 * - 0 <= edges.length <= 5000
 * - edges[i].length == 2
 * - 0 <= edges[i][j] < n
 * - No duplicate edges.
 * - The graph may not be connected initially.
 */

 import java.util.*;
public class CycleInUndirectedGraph261 {

    public static class Edge{
        int src;
        int dest;

        public Edge(int s, int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static ArrayList<Edge>[] createGraph(int v, int edges[][]){

        ArrayList<Edge> graph[] = new ArrayList[v];

        for(int i=0; i<v; i++){
             graph[i] = new ArrayList<>();
        }

        for(int i=0; i<edges.length; i++){

            int src = edges[i][0];
            int dest = edges[i][1];
            
            graph[src].add( new Edge(src, dest));
            graph[dest].add( new Edge(dest, src));
        }

        return graph;
    }

    // DFS
    public static boolean helper(ArrayList<Edge> graph[], boolean vis[], int currNode, int par){
        vis[currNode] = true;

        for(int i=0; i<graph[currNode].size(); i++){
            Edge e = graph[currNode].get(i);

            if(!vis[e.dest]){
                if(helper(graph, vis, e.dest, currNode)) return true;
            }else if( par != e.dest){
                return true;
            }
        }

        return false;

    }
    

    public static class Node{
        int curr;
        int par;

        public Node(int curr, int par){
            this.curr = curr;
            this.par = par;
        }
    }


    //BFS
    public static boolean helper2(ArrayList<Edge> graph[], boolean vis[], int curr){

        Queue<Node>  q= new LinkedList<>();
        Edge e = graph[curr].get(0);
        q.add(new Node(e.src, -1));

        while (!q.isEmpty()){
            Node n = q.remove();
            vis[n.curr] = true;

            for(int i=0; i<graph[n.curr].size(); i++){
                Edge e2 = graph[n.curr].get(i);
                if(!vis[e2.dest]){
                    Node n2 = new Node(e2.dest, n.curr);
                    q.add(n2);
                }else if( n.par != e2.dest) return true;
            }

        }
            
        
        return false;
    }

    // Solution methods will be implemented here by the user

    public boolean hasCycle(int v, int edges[][]){

        if(v <= 0 || edges.length <= 1) return false;

        ArrayList<Edge> graph[] = createGraph(v, edges);
        boolean vis[] = new boolean[v];
        

        for(int currNode=0; currNode<v; currNode++){
            if(!vis[currNode]){
                if(helper2(graph,vis, currNode)) return true;
            }
        }
        return false;
    }

    
    
    public static void main(String[] args) {
        CycleInUndirectedGraph261 solution = new CycleInUndirectedGraph261();

        // Test Case 1: Simple cycle
        int[][] edges1 = {{0,1}, {1,2}, {2,0}};
        System.out.println("Test Case 1: " + solution.hasCycle(3, edges1) + " (Expected: true)");

        // Test Case 2: No cycle, single connected component
        int[][] edges2 = {{0,1}, {1,2}, {2,3}};
        System.out.println("Test Case 2: " + solution.hasCycle(4, edges2) + " (Expected: false)");

        // Test Case 3: Disconnected graph with a cycle
        int[][] edges3 = {{0,1}, {1,2}, {2,0}, {3,4}};
        System.out.println("Test Case 3: " + solution.hasCycle(5, edges3) + " (Expected: true)");

        // Test Case 4: Disconnected graph without a cycle
        int[][] edges4 = {{0,1}, {2,3}};
        System.out.println("Test Case 4: " + solution.hasCycle(4, edges4) + " (Expected: false)");

        // Test Case 5: Single node, no edges
        int[][] edges5 = {};
        System.out.println("Test Case 5: " + solution.hasCycle(1, edges5) + " (Expected: false)");

        // Test Case 6: Two nodes, one edge
        int[][] edges6 = {{0,1}};
        System.out.println("Test Case 6: " + solution.hasCycle(2, edges6) + " (Expected: false)");

        // Test Case 7: Complex graph with multiple cycles
        int[][] edges7 = {{0,1}, {1,2}, {2,0}, {2,3}, {3,4}, {4,2}};
        System.out.println("Test Case 7: " + solution.hasCycle(5, edges7) + " (Expected: true)");

        // Edge Case 1: No nodes
        int[][] edges8 = {};
        System.out.println("Edge Case 1: " + solution.hasCycle(0, edges8) + " (Expected: false)");

        // Edge Case 2: Single node with no edges
        int[][] edges9 = {};
        System.out.println("Edge Case 2: " + solution.hasCycle(1, edges9) + " (Expected: false)");

        // Edge Case 3: Two nodes with one edge
        int[][] edges10 = {{0,1}};
        System.out.println("Edge Case 3: " + solution.hasCycle(2, edges10) + " (Expected: false)");

        // Edge Case 4: Large graph with no cycles
        int[][] edges11 = new int[1999][2];
        for (int i = 0; i < 1999; i++) {
            edges11[i][0] = i;
            edges11[i][1] = i + 1;
        }
        System.out.println("Edge Case 4: " + solution.hasCycle(2000, edges11) + " (Expected: false)");

        // Edge Case 5: Large graph with a cycle
        int[][] edges12 = new int[2000][2];
        for (int i = 0; i < 1999; i++) {
            edges12[i][0] = i;
            edges12[i][1] = i + 1;
        }
        edges12[1999][0] = 1999;
        edges12[1999][1] = 0; // Adding a cycle
        System.out.println("Edge Case 5: " + solution.hasCycle(2000, edges12) + " (Expected: true)");
    }
}
