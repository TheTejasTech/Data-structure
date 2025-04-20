import java.util.*;

public class GraphDSA {
    static class Edge {
        int src, dest, wt;
        Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    public static void createg(ArrayList<Edge>[] graph, int V) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }
 
        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 3, 1));

    }

    public static void bfs(ArrayList<Edge>[] graph, int V) {
        Queue<Integer> pq = new LinkedList<>();
        boolean[] vis = new boolean[V];

        pq.add(0);      
        vis[0] = true;

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            System.out.print(curr + " ");
            for (Edge e : graph[curr]) {
                if (!vis[e.dest]) {
                    pq.add(e.dest);
                    vis[e.dest] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge>[] graph = new ArrayList[V];

        System.out.println("The BFS traversal is:");
        createg(graph, V);
        bfs(graph, V);
    }
}
