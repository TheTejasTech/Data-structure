#include <iostream>
#include <vector>
#include <climits>

using namespace std;

#define V 5  

void bellmanFord(const vector<vector<int>>& graph, int start, vector<int>& dist) {
    dist.assign(V, INT_MAX);  // Initialize distances
    dist[start] = 0;

    for (int i = 1; i <= V - 1; i++) {
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != INT_MAX && dist[u] != INT_MAX && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
    }

    // Check for negative weight cycles
    for (int u = 0; u < V; u++) {
        for (int v = 0; v < V; v++) {
            if (graph[u][v] != INT_MAX && dist[u] != INT_MAX && dist[u] + graph[u][v] < dist[v]) {
                cout << "Graph contains a negative weight cycle\n";
                return;
            }
        }
    }
}

int main() {
    vector<vector<int>> graph = {
        {0, 1, 4, INT_MAX, INT_MAX},
        {INT_MAX, 0, 4, 2, 7},
        {INT_MAX, INT_MAX, 0, 3, 5},
        {INT_MAX, INT_MAX, INT_MAX, 0, 1},
        {INT_MAX, INT_MAX, INT_MAX, INT_MAX, 0}
    };

    // Node names for display
    string nodes[] = {"A", "B", "C", "D", "E"};  // Node labels

    // Result matrix to store distances
    vector<vector<int>> shortest_paths(V, vector<int>(V, INT_MAX));

    // Run Bellman-Ford from each node
    for (int i = 0; i < V; i++) {
        vector<int> dist;
        bellmanFord(graph, i, dist);
        for (int j = 0; j < V; j++) {
            shortest_paths[i][j] = dist[j];
        }
    }

    // Display the distance table
    cout << "Shortest Path Distance Table (in meters):\n";
    cout << "From \\ To\t";
    for (int i = 0; i < V; i++) {
        cout << nodes[i] << "\t";
    }
    cout << "\n";

    for (int i = 0; i < V; i++) {
        cout << nodes[i] << "\t\t";
        for (int j = 0; j < V; j++) {
            if (shortest_paths[i][j] == INT_MAX) {
                // Display the original distance from the graph
                if (graph[i][j] == INT_MAX) {
                    cout << "NP\t";
                } else {
                    cout << graph[i][j] << "\t";
                }
            } else {
                cout << shortest_paths[i][j] << "\t";
            }
        }
        cout << "\n";
    }
    return 0;
}
