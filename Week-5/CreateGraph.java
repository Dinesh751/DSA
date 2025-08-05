import java.util.*;

public class CreateGraph {

    public static class Edge{
        int src;
        int dest;

        public Edge(int src, int dest){
            this.src = src;
            this.dest = dest;
        }

    }

    

    public static void main(String args[]){
        int edges[][] = {{0,1}, {0,2}, {1,0}, {1,3}, {2,0}, {2,4}, {3,1},{3,4}, {3,5}, {4,2}, {4,3}, {4,5}, {5,3}, {5,4}, {5,6}, {6,5}};


    //   0
    //  / \
    // 1   2
    //  \   \
    //   3---4
    //    \ /
    //     5
    //     |
    //     6
    
         int vertices = 7;
        ArrayList<Edge> graph[] = new ArrayList[vertices];
        for(int i=0; i<vertices; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : edges){
            int src = edge[0], dest = edge[1];
            graph[src].add(new Edge(src, dest));
        }
        System.out.println("Hello World");
        CreateGraph g = new CreateGraph();
        
        g.bfs(graph);

        boolean vis[] = new boolean[vertices];
        g.dfs(graph,0,vis);

    }

    public void bfs(ArrayList<Edge> graph[]) {
        boolean vis[] = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        vis[0] = true; // Mark source as visited immediately

        while (!q.isEmpty()) {
            int currentNode = q.remove();
            System.out.print(currentNode + "->");

            for (int i = 0; i < graph[currentNode].size(); i++) {
                Edge e = graph[currentNode].get(i);
                if (!vis[e.dest]) {
                    vis[e.dest] = true; // Mark as visited when adding to queue
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
    }
    public void dfs(ArrayList<Edge> graph[], int currNode, boolean vis[]){
      
        System.out.print(currNode + "->");

        vis[currNode] = true;

        for( int i=0; i<graph[currNode].size(); i++){
            Edge e = graph[currNode].get(i);

            if(!vis[e.dest]){
                dfs(graph, e.dest, vis);
            }
        }
        
    }
}

