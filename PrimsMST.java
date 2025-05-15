import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimsMST {
    public static class Edge {
        int src;
        int dest;
        int wt;

        Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    // Create undirected weighted graph
    public static void createGraph(ArrayList<Edge>[] graph, int V) {
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        // Adding edges (undirected)
        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));

        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));

        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));

        graph[3].add(new Edge(3, 0, 30));
        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));
    }

    // Helper class to represent a node with its cost and parent
    public static class Pair implements Comparable<Pair> {
        int node;
        int cost;
        int parent;

        public Pair(int n, int c, int p) {
            this.node = n;
            this.cost = c;
            this.parent = p;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
    }

    // Prim's Algorithm with edge printing
    public static void primsAlgo(ArrayList<Edge>[] graph, int V) {
        boolean[] vis = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] parent = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = -1;
        }

        pq.add(new Pair(0, 0, -1));
        int mstCost = 0;

        System.out.println("Edges in MST:");

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!vis[curr.node]) {
                vis[curr.node] = true;
                mstCost += curr.cost;
                parent[curr.node] = curr.parent;

                if (curr.parent != -1) {
                    System.out.println(curr.parent + " - " + curr.node + " : " + curr.cost);
                }

                for (Edge e : graph[curr.node]) {
                    if (!vis[e.dest]) {
                        pq.add(new Pair(e.dest, e.wt, curr.node));
                    }
                }
            }
        }

        System.out.println("Minimum cost of MST = " + mstCost);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph, V);
        primsAlgo(graph, V);
    }
}
