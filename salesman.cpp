#include <bits/stdc++.h>
using namespace std;

#define INF INT_MAX
#define V 4  // Number of cities (you can adjust this as per your requirement)

int graph[V][V] = {
    {0, 10, 15, 20},
    {10, 0, 35, 25},
    {15, 35, 0, 30},
    {20, 25, 30, 0}
};

// A utility function to find the minimum cost path using Branch and Bound
int calculateCost(const vector<int>& path) {
    int cost = 0;
    for (int i = 0; i < path.size() - 1; i++) {
        cost += graph[path[i]][path[i + 1]];
    }
    cost += graph[path.back()][path[0]];  // Return to the starting city
    return cost;
}

// Helper function to perform Branch and Bound
void branchBound(int currentPos, int n, vector<int>& path, vector<bool>& visited, int& min_cost, vector<int>& best_path) {
    if (path.size() == n) {  // If all cities are visited
        int cost = calculateCost(path);
        if (cost < min_cost) {
            min_cost = cost;
            best_path = path;
        }
        return;
    }

    for (int nextCity = 0; nextCity < n; nextCity++) {
        if (!visited[nextCity]) {
            path.push_back(nextCity);
            visited[nextCity] = true;
            branchBound(nextCity, n, path, visited, min_cost, best_path);
            visited[nextCity] = false;
            path.pop_back();
        }
    }
}

int tsp(int graph[V][V], int n) {
    vector<int> path;
    vector<bool> visited(n, false);
    int min_cost = INF;
    
    // Store the path for which the minimum cost is found
    vector<int> best_path;
    
    // Start the Branch and Bound process from city 0
    path.push_back(0);
    visited[0] = true;
    branchBound(0, n, path, visited, min_cost, best_path);
    
    cout << "Minimum cost: " << min_cost << endl;
    cout << "Path: ";
    for (int city : best_path) {
        cout << city << " ";
    }
    cout << endl;

    return min_cost;
}

int main() {
    int n = V;
    tsp(graph, n);
    return 0;
}
