import java.util.*;

public class Main {
    static class Edge {
        int src, dest, wt;
        Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    // Create the graph with edges
    public static void createg(ArrayList<Edge>[] graph, int V) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 3, 1));
    }

    // BFS traversal
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

    // DFS traversal
    public static void dfs(ArrayList<Edge>[] graph, boolean[] vis, int curr) {
        System.out.print(curr + " ");
        vis[curr] = true;
        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                dfs(graph, vis, e.dest);
            }
        }
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge>[] graph = new ArrayList[V];

        System.out.println("The BFS traversal is:");
        createg(graph, V);
        bfs(graph, V);

        System.out.println("\nThe DFS traversal is:");
        boolean[] vis = new boolean[V]; // fresh visited array for DFS
        dfs(graph, vis, 0); // start DFS from node 0
    }
}
