import java.util.PriorityQueue;

public class DijkstrasJava {
    public static class Pair implements Comparable<Pair> {
        int node, dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair p2) {
            return Integer.compare(this.dist, p2.dist);
        }
    }

    // Dijkstra from a given source
    public static int[] dijkstra(int[][] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();

            if (!vis[curr.node]) {
                vis[curr.node] = true;

                for (int v = 0; v < V; v++) {
                    if (graph[curr.node][v] != Integer.MAX_VALUE && !vis[v]) {
                        int newDist = dist[curr.node] + graph[curr.node][v];
                        if (newDist < dist[v]) {
                            dist[v] = newDist;
                            pq.add(new Pair(v, dist[v]));
                        }
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;

        int[][] graph = {
                { 0, 500, 300, INF, INF, INF },
                { 500, 0, 200, 600, 1200, INF },
                { 300, 200, 0, 400, INF, 900 },
                { INF, 600, 400, 0, 800, INF },
                { INF, 1200, INF, 800, 0, 200 },
                { INF, INF, 900, INF, 200, 0 }
        };

        String[] nodes = { "College", "Library", "Park", "Mall", "Bus Station", "Hospital" };

        int V = graph.length;
        int[][] allPairsDist = new int[V][V];

        // Run Dijkstra from each node
        for (int i = 0; i < V; i++) {
            allPairsDist[i] = dijkstra(graph, i);
        }

        // Print the updated shortest path matrix
        System.out.println("Updated Shortest Distance Matrix:");
        System.out.print("          ");
        for (String node : nodes) {
            System.out.printf("%-14s", node);
        }
        System.out.println();

        for (int i = 0; i < V; i++) {
            System.out.printf("%-10s", nodes[i]);
            for (int j = 0; j < V; j++) {
                if (allPairsDist[i][j] == Integer.MAX_VALUE) {
                    System.out.printf("%-14s", "INF");
                } else {
                    System.out.printf("%-14d", allPairsDist[i][j]);
                }
            }
            System.out.println();
        }
    }
}
